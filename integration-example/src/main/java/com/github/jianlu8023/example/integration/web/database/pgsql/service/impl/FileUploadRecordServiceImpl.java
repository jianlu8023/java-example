package com.github.jianlu8023.example.integration.web.database.pgsql.service.impl;

import com.baomidou.dynamic.datasource.annotation.*;
import com.baomidou.mybatisplus.extension.service.impl.*;
import com.github.jianlu8023.example.integration.middleware.exceptions.*;
import com.github.jianlu8023.example.integration.web.database.pgsql.entity.*;
import com.github.jianlu8023.example.integration.web.database.pgsql.mapper.*;
import com.github.jianlu8023.example.integration.web.database.pgsql.service.*;
import com.github.jianlu8023.mock.generator.pojo.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;


@Service
@DS("pgsql")
public class FileUploadRecordServiceImpl extends ServiceImpl<FileUploadRecordMapper, FileUploadRecord>
        implements FileUploadRecordService {
    private static final Logger L = LoggerFactory.getLogger(FileUploadRecordServiceImpl.class);

    private PojoGenerator<FileUploadRecord> generator;

    @Autowired
    public void setGenerator(PojoGenerator<FileUploadRecord> generator) {
        this.generator = generator;
    }

    @Override
    public FileUploadRecord create() {
        final FileUploadRecord generate = generator.generate(FileUploadRecord.class);
        L.debug("generate: {}", generate);
        final boolean save = save(generate);
        if (save) {
            return generate;
        } else {
            throw new CreateFailException("create fail");
        }
    }
}




