package com.faw.usertestall.controller;

import com.faw.usertestall.domain.common.ErrorCode;
import com.faw.usertestall.domain.common.Result;
import com.faw.usertestall.exception.BusinessException;
import com.faw.usertestall.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/upload")
    public Result<String> upload(@NotNull MultipartFile file) {
        try {

            fileService.upload(file.getInputStream(),file.getOriginalFilename());

        } catch (IOException e) {

            logger.error("上传失败！", e);

            throw new BusinessException(ErrorCode.FILE_UPLOAD_FAILURE);
        }
        return Result.success(file.getOriginalFilename());
    }

}
