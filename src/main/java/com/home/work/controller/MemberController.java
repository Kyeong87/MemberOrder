package com.home.work.controller;

import io.swagger.annotations.*;
import com.home.work.config.JwtTokenProvider;
import com.home.work.domain.Member;
import com.home.work.domain.MemberSetDto;
import com.home.work.domain.UserGroup;
import com.home.work.repository.MemberRepository;
import com.home.work.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Log
@RestController
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    UserGroupService userGroupService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<Map<String, String>> join(@RequestBody Member join){
        String passwordEncode = passwordEncoder.encode(join.getPassword()); //암호화처리
        String emailPattern = "[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        String namePattern = "^[ㄱ-ㅎ가-힣a-zA-Z]*$";
        String nickPattern = "^[a-z]*$";

        boolean bolEmail = Pattern.matches(emailPattern, join.getEmail());
        if(bolEmail) new IllegalArgumentException("이메일을 확인해주세요.");
        boolean bolName = Pattern.matches(namePattern, join.getName());
        if(bolName) new IllegalArgumentException("이름을 확인해주세요.");
        boolean bolNick = Pattern.matches(nickPattern, join.getNickName());
        if(bolNick) new IllegalArgumentException("닉네임을 확인해주세요.");

        LocalDateTime now = LocalDateTime.now();
        Member member = Member.builder()
                .id(join.getId())
                .password(passwordEncode)
                .name(join.getName())
                .nickName(join.getNickName())
                .phone(join.getPhone())
                .email(join.getEmail())
                .gender(join.getGender())
                .registerDate(now)
                .build();
        String result = memberRepository.save(member).getId();

        Map<String, String> response = new HashMap<>();
        response.put("result", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody MemberSetDto login){
        Member member = memberRepository.findById(login.getId()).orElseThrow(()->
                new IllegalArgumentException("아이디를 확인해주세요."));

        //비밀번호 암호화
        if(!passwordEncoder.matches(login.getPassword(), member.getPassword())){
            throw new IllegalArgumentException("비밀번호를 확인해주세요.");
        }

        Map<String, String> response = new HashMap<>();
        // 토큰 생성
        response.put("token", jwtTokenProvider.createToken(member.getId()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "로그아웃")
    @RequestMapping(value = "/user-logout", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest req) {
        String token = jwtTokenProvider.resolveToken(req);
        System.out.println(req);
        // 토큰이 유효한지 체크
        if(token != null && jwtTokenProvider.validateToken(token)){
            Long expiration = jwtTokenProvider.getExpiration(token);
                    redisTemplate.opsForValue()
                .set(token, "logout", expiration, TimeUnit.MILLISECONDS);

        }
        Map<String, String> response = new HashMap<>();
        response.put("msg", "로그아웃 되었습니다.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "단일 회원 상세")
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Member>> memberDetail(@PathVariable("memberId") String memberId) {
        List<Member> result = userGroupService.getMemberDetail(memberId);

        if(result.size() == 0){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "여러 회원 목록 조회 - 마지막 주문일 포함")
    @GetMapping("/userGroup")
    public ResponseEntity<Map<String,Object>> getUserGroupList(@RequestBody MemberSetDto memberSetDto) {

        UserGroup userGroup = userGroupService.getUserGroupList(memberSetDto);

        Map<String,Object> result = new HashMap<>();
        result.put("data", userGroup);
        return ResponseEntity.ok().body(result);
    }
}