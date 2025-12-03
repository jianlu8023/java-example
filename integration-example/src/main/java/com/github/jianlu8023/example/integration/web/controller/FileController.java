package com.github.jianlu8023.example.integration.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.jianlu8023.example.integration.web.database.pgsql.entity.*;
import com.github.jianlu8023.example.integration.web.database.pgsql.service.*;
import com.github.jianlu8023.format.response.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/file")
public class FileController {
    private static final Logger L = LoggerFactory.getLogger(FileController.class);

    private FileUploadRecordService fileUploadRecordService;

    @Autowired
    public void setFileUploadRecordService(FileUploadRecordService fileUploadRecordService) {
        this.fileUploadRecordService = fileUploadRecordService;
    }


    @RequestMapping("/getOneRecord")
    public ApiResponse<FileUploadRecord> getOneRecord() {

        LambdaQueryWrapper<FileUploadRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(FileUploadRecord::getUid);

        final FileUploadRecord one = fileUploadRecordService.getOne(wrapper);
        return ApiResponse.success(one);
    }

    @RequestMapping("/addOneRecord")
    public ApiResponse<FileUploadRecord> addOneRecord() {

        final FileUploadRecord one = fileUploadRecordService.create();

        return ApiResponse.success(one);
    }
}
