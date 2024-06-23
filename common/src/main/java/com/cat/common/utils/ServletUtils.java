package com.cat.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/***
 * ServletUtils工具类
 * @title ServletUtils
 * @description <TODO description class purpose>
 * @author xiaomaohuifaguang
 * @create 2024/6/24 0:49
 **/
public class ServletUtils {

    /***
     * 通过上下文获取 HttpServletRequest
     * @author xiaomaohuifaguang
     * @date 2023/8/7
     **/
    public static HttpServletRequest getHttpServletRequest(){
        ServletRequestAttributes servletRequestAttributes =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        return servletRequestAttributes.getRequest();
    }


    /***
     * 通过上下文获取 HttpServletResponse
     * @author xiaomaohuifaguang
     * @date 2023/8/7
     **/
    public static HttpServletResponse getHttpServletResponse(){
        ServletRequestAttributes servletRequestAttributes =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        return servletRequestAttributes.getResponse();
    }



}
