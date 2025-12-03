package com.github.jianlu8023.example.integration.web.database.pgsql.service;

import com.baomidou.mybatisplus.extension.service.*;
import com.github.jianlu8023.example.integration.web.database.pgsql.entity.*;

public interface FileUploadRecordService extends IService<FileUploadRecord> {

    FileUploadRecord create();
}
