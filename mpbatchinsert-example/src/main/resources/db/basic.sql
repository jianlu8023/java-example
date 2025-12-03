DROP TABLE IF EXISTS user;

create table user(
    uid        bigint auto_increment  primary key,
    userName   varchar(100) charset utf8mb4       not null comment '用户名',
    userNick   varchar(100)                       not null comment '用户昵称',
    userAge    int                                not null comment '用户年龄',
    userEmail  varchar(100)                       not null comment '用户emial',
    userGender tinyint                            not null comment '用户性别',
    userId     varchar(100)                       not null comment '用户身份证号',
    createTime datetime default CURRENT_TIMESTAMP not null comment '用户创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null comment '更新信息时间'
) comment '用户信息表' collate = utf8mb4_general_ci;