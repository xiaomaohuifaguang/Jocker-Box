package com.cat.auth.config.security;

import com.cat.common.entity.HttpResult;
import com.cat.common.entity.HttpResultStatus;
import com.cat.common.utils.JSONUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/***
 * 认证失败处理器
 * @title AuthenticationEntryPointImpl
 * @description <TODO description class purpose>
 * @author xiaomaohuifaguang
 * @create 2023/8/8 21:23
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSONUtils.toJSONString(HttpResult.back(HttpResultStatus.UNAUTHORIZED)));
        printWriter.flush();
        printWriter.close();
    }
}
