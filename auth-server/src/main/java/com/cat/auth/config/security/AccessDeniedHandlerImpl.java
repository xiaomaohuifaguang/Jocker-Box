package com.cat.auth.config.security;


import com.cat.common.entity.HttpResult;
import com.cat.common.entity.HttpResultStatus;
import com.cat.common.utils.JSONUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/***
 * 授权失败处理器
 * @title AccessDeniedHandlerImpl
 * @description <TODO description class purpose>
 * @author xiaomaohuifaguang
 * @create 2023/8/8 21:26
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json;utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSONUtils.toJSONString(HttpResult.back(HttpResultStatus.FORBIDDEN)));
        printWriter.flush();
        printWriter.close();
    }
}
