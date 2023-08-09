-- 切换库
use api;
-- 接口信息
# create table if not exists api.`interface_info`
# (
#     `id`             bigint                             not null auto_increment comment '主键' primary key,
#     `name`           varchar(256)                       not null comment '名称',
#     `description`    varchar(256)                       null comment '描述',
#     `url`            varchar(512)                       not null comment '接口地址',
#     `requestParams`  text                               null comment '请求参数',
#     `requestHeader`  text                               null comment '请求头',
#     `responseHeader` text                               null comment '响应头',
#     `status`         int      default 0                 not null comment '接口状态（0-关闭，1-开启）',
#     `method`         varchar(256)                       not null comment '请求类型',
#     `userId`         bigint                             not null comment '创建人',
#     `createTime`     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
#     `updateTime`     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
#     `isDelete`       tinyint  default 0                 not null comment '是否删除(0-未删, 1-已删)'
# ) comment '接口信息';
#
# create table if not exists user_interface_info
# (
#     id              bigint auto_increment comment 'id' primary key,
#     userId          bigint comment '调用用户 id',
#     interfaceInfoId bigint comment '接口 id',
#     totalNum        int      default 0 comment '总调用次数',
#     leftNum         int      default 0 comment '剩余调用次数',
#     status          int      default 0 comment '0-正常， 1-禁用',
#     createTime      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
#     updateTime      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
#     isDelete        tinyint  default 0                 not null comment '是否删除'
# ) comment '用户调用接口关系表';

create table if not exists system_whitelist
(
    id         bigint auto_increment comment 'id' primary key,
    whiteIp    varchar(256)                       not null comment '白名单',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment 'ip白名单';

create table if not exists system_blacklist
(
    id         bigint auto_increment comment 'id' primary key,
    blackIp    varchar(256)                       not null comment '黑名单',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment 'ip黑名单';