package com.cat.auth.config.security;

import com.cat.common.entity.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/***
 * <TODO description class purpose>
 * @title UserDetailsImpl
 * @description <TODO description class purpose>
 * @author xiaomaohuifaguang
 * @create 2024/6/20 0:36
 **/
public class UserDetailsImpl implements UserDetails {

    private final LoginUser loginUser;

    public UserDetailsImpl(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
    }

    @Override
    public String getUsername() {
        return loginUser.getNickname();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
