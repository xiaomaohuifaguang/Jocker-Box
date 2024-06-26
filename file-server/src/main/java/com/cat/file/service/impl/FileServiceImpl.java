package com.cat.file.service.impl;

import com.cat.common.entity.FileInfo;
import com.cat.common.entity.HttpResult;
import com.cat.common.entity.HttpResultStatus;
import com.cat.common.utils.CatUUID;
import com.cat.common.utils.IOUtils;
import com.cat.common.utils.ServletUtils;
import com.cat.file.config.minio.MinioService;
import com.cat.file.config.security.SecurityUtils;
import com.cat.file.mapper.FileInfoMapper;
import com.cat.file.service.FileService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/***
 * 文件服务业务层接口实现
 * @title FileServiceImpl
 * @description <TODO description class purpose>
 * @author xiaomaohuifaguang
 * @create 2024/6/26 23:24
 **/
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Resource
    private FileInfoMapper fileInfoMapper;

    @Resource
    private MinioService minioService;

    @Override
    public FileInfo upload(MultipartFile uploadFile) throws IOException {
        FileInfo fileInfo = new FileInfo()
                .setId(CatUUID.randomUUID())
                .setFilename(uploadFile.getOriginalFilename())
                .setUserId(Integer.parseInt(SecurityUtils.getLoginUser().getUserId()));
        return StringUtils.hasText(minioService.putObject("cat", fileInfo.getId(), uploadFile.getInputStream(), IOUtils.contentType(fileInfo.getFileType()))) && fileInfoMapper.insert(fileInfo) == 1
                ? fileInfo : null;
    }

    @Override
    public void download(String fileId) throws IOException {
        FileInfo fileInfo = fileInfoMapper.selectById(fileId);
        if(!ObjectUtils.isEmpty(fileInfo)){
            HttpServletResponse response = ServletUtils.getHttpServletResponse();
            InputStream inputStream = minioService.getObject("cat", fileId);
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType(IOUtils.contentType(fileInfo.getFileType()));
            response.setHeader("Content-Disposition", "inline; filename=\""+fileInfo.getFilename()+"\"");
            IOUtils.saveStream(inputStream,outputStream);
        }else {
            ServletUtils.back(HttpResult.back(HttpResultStatus.ERROR));
        }

    }
}
