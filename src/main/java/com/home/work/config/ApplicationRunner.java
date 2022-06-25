package com.home.work.config;

import com.home.work.domain.Member;
import com.home.work.domain.MemberGender;
import com.home.work.domain.Orders;
import com.home.work.repository.MemberRepository;
import com.home.work.repository.OrderRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Log
@Component
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args){

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thDaysAgo = now.minusDays(3); // 3일 전
        LocalDateTime twoDaysAgo = now.minusDays(2); // 2일 전
        LocalDateTime oneDaysAgo = now.minusDays(1); // 1일 전

        // member 테이블에 데이터 insert
        String passwordEncode = passwordEncoder.encode("test1234"); //암호화처리
        Member member = new Member("pyk", passwordEncode,"박연경","sponge","01098598733","pykpyk8733@gmail.com",MemberGender.WOMAN, thDaysAgo);
        memberRepository.save(member);

        String passwordEncode1 = passwordEncoder.encode("test12345"); //암호화처리
        Member member1 = new Member("minions", passwordEncode1,"미니언즈","minions","01098598733","pykpyk8733@gmail.com",MemberGender.MAN, thDaysAgo);
        memberRepository.save(member1);

        String passwordEncode2 = passwordEncoder.encode("test12346"); //암호화처리
        Member member2 = new Member("rupee", passwordEncode2,"루피","rupee","01098598733","pykpyk8733@gmail.com",MemberGender.WOMAN, thDaysAgo);
        memberRepository.save(member2);

        String passwordEncode3 = passwordEncoder.encode("test12347"); //암호화처리
        Member member3 = new Member("mac", passwordEncode3,"맥이","macbook","01012341234","pykpyk@test.com",MemberGender.MAN, thDaysAgo);
        memberRepository.save(member3);

        // order 테이블 데이터 insert
        Orders order = new Orders(1,"20220622-N5WW","상품1", "pyk", twoDaysAgo);
        orderRepository.save(order);

        Orders order1 = new Orders(2,"20220623-N5WW","상품2", "pyk", oneDaysAgo);
        orderRepository.save(order1);

        Orders order2 = new Orders(3,"20220623-N5WW","상품3", "rupee", oneDaysAgo);
        orderRepository.save(order2);

        log.info("member, order data insert!");
    }
}
