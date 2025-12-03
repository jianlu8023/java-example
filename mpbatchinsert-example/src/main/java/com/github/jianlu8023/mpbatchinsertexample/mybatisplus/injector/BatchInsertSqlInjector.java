package com.github.jianlu8023.mpbatchinsertexample.mybatisplus.injector;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.injector.*;
import com.baomidou.mybatisplus.core.metadata.*;
import com.baomidou.mybatisplus.extension.injector.methods.*;

import java.util.*;


public class BatchInsertSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        methodList.add(new InsertBatchSomeColumn(t -> t.getFieldFill() != FieldFill.UPDATE));
        return methodList;
    }
}
