package com.home.work.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {
    @Id
    private String id;
    private String password;
    private String name;
    private String nickName;
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING) // 추가
    private MemberGender gender; // 남, 여
    private LocalDateTime registerDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority("ROLE_USER"));
        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() { // 계정이 만료되지 않았는지
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정이 잠겨있지 않았는지
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호가 만료되지 않았는지
        return true;
    }

    @Override
    public boolean isEnabled() { // 계정이 활성화(사용가능)인지 리턴
        return true;
    }
}
