package com.home.work.config;

import com.home.work.domain.Member;
import com.home.work.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtMemberDetails implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Member> result = memberRepository.findById(s);
        return result.orElseThrow(()->new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}
