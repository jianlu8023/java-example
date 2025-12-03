package com.github.jianlu8023.multidbexample.pagehelper.dialect;


import com.github.pagehelper.dialect.helper.*;
import com.github.pagehelper.parser.*;
import com.github.pagehelper.util.*;

import java.util.*;

public class LocalMysqlDialect extends MySqlDialect {
    @Override
    public void setProperties(Properties properties) {
        this.countSqlParser = ClassUtil.newInstance(properties.getProperty("countSqlParser"), CountSqlParser.class, properties, CountJSqlParser45::new);
        this.orderBySqlParser = ClassUtil.newInstance(properties.getProperty("orderBySqlParser"), OrderBySqlParser.class, properties, OrderByJSqlParser45::new);
    }
}
