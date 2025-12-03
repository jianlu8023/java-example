package com.github.jianlu8023.multidbexample.web.db2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jianlu8023.multidbexample.web.db2.entity.File;
import com.github.jianlu8023.multidbexample.web.db2.service.FileService;
import com.github.jianlu8023.multidbexample.web.db2.mapper.FileMapper;
import org.springframework.stereotype.Service;

/**
* @author root
* @description 针对表【file(文件)】的数据库操作Service实现
* @createDate 2024-04-15 16:47:32
*/
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File>
    implements FileService{

}




