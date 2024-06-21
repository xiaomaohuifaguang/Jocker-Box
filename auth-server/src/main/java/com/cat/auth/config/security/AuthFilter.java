package com.cat.auth.config.security;

import com.cat.common.entity.LoginUser;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/***
 * 登录过滤器
 * @title AuthFilter
 * @description <TODO description class purpose>
 * @author xiaomaohuifaguang
 * @create 2024/6/20 0:48
 **/
@Component
public class AuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 这里实际应该从request 中取得 请求的参数（包括请求头等） 去完成鉴权保存用户信息 到SecurityContextHolder
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId("admin");
        loginUser.setUsername("admin");
        loginUser.setNickname("超级管理员");

        UserDetailsImpl userDetails = new UserDetailsImpl(loginUser);

        // 保存用户信息 到SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);

    }
}
