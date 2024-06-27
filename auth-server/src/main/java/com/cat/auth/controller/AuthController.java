package com.cat.auth.controller;

import com.cat.api.file.FileServiceClient;
import com.cat.auth.service.UserService;
import com.cat.common.entity.*;
import com.cat.common.utils.IOUtils;
import com.cat.common.utils.ServletUtils;
import feign.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

/***
 * <TODO description class purpose>
 * @title AuthController
 * @description <TODO description class purpose>
 * @author xiaomaohuifaguang
 * @create 2024/6/24 22:58
 **/
@RestController
@RequestMapping("/auth")
@Tag(name = "鉴权服务")
public class AuthController {

    @Resource
    private FileServiceClient fileServiceClient;

    @Resource
    private UserService userService;

    @Operation(summary = "获取token")
    @RequestMapping(value = "/getToken", method = RequestMethod.POST)
    public HttpResult<String> getToken(@RequestBody LoginInfo loginInfo){
        String token = userService.getToken(loginInfo);
        return HttpResult.back(StringUtils.hasText(token) ? HttpResultStatus.SUCCESS : HttpResultStatus.ERROR, token);
    }

    @Operation(summary = "令牌鉴权")
    @RequestMapping(value = "/getLoginUserByToken", method = RequestMethod.POST)
    public HttpResult<LoginUser> getLoginUser(@RequestBody LoginInfo loginInfo){
        return HttpResult.back(userService.getLoginUserByToken(loginInfo.getToken()));
    }

    @Operation(summary = "测试")
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public HttpResult<?> test(@RequestPart("file") MultipartFile file){
        HttpResult<FileInfo> upload = fileServiceClient.upload(file);
        return HttpResult.back("666");
    }

    @Operation(summary = "测试1")
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public void test1(@RequestParam("fileId") String fileId) throws IOException {

        Response download = fileServiceClient.download(fileId);
        HttpServletResponse response = ServletUtils.getHttpServletResponse();
        Map<String, Collection<String>> headers = download.headers();
        response.setStatus(response.getStatus());
        response.setContentType(headers.get(HttpHeaders.CONTENT_TYPE).stream().toList().get(0));
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,headers.get(HttpHeaders.CONTENT_DISPOSITION).stream().toList().get(0));
        InputStream inputStream = download.body().asInputStream();
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.saveStream(inputStream, outputStream);
    }

}
