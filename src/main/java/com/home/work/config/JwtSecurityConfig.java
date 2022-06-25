package com.home.work.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    RedisTemplate redisTemplate;

    // 암호화에 필요한 PasswordEncoder 를 Bean 등록합니다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // authenticationManager를 Bean 등록합니다.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/swagger-ui/index.html","/h2-console/**", "/login/**"
                        ,"/join/**","/swagger-ui.html","/swagger/**", "/swagger-resources/**",  "/webjars/**"
                ,"/v2/api-docs").permitAll()
                .anyRequest().hasRole("USER");

        http.httpBasic().disable();     // rest api 고려, 기본설정 해제
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// 토큰 기반이므로 세션 사용하지 않음.
        http.csrf().disable(); // csrf 해제
        http.formLogin().disable(); // form 로그인 해제
        http.headers().frameOptions().disable(); // h2console 접속

        // jwt authentication filter 추가
        http.addFilterBefore(new JwtAuthFilter(jwtTokenProvider, redisTemplate), UsernamePasswordAuthenticationFilter.class);
    }
}
