/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/12/28 14:33:08                          */
/*==============================================================*/


drop table if exists contract;

drop table if exists data_dictionary;

drop table if exists employees;

drop table if exists flow;

drop table if exists permission;

drop table if exists role;

drop table if exists role_permission;

drop table if exists tbl_flow_line;

drop table if exists tbl_flow_node;

drop table if exists tbl_flow_role_user;

drop table if exists tbl_leave_audit;

drop table if exists user_contract;

drop table if exists user_role;

drop table if exists users;

drop table if exists vehicle;

drop table if exists vehicle_type;

/*==============================================================*/
/* Table: contract                                              */
/*==============================================================*/
create table contract
(
   contract_id          bigint(20) not null comment '编号',
   contract_numbers     varchar(256) comment '合同编号',
   contract_name        varchar(256) comment '合同名称',
   sign_unit            varchar(256) comment '签订单位',
   payment              varchar(20) comment '支付方式',
   principal            bigint(20) comment '合同负责人',
   contact_user_id      bigint(20) comment '客户联系人',
   remark               varchar(256) comment '备注',
   contract_amount      varchar(100) comment '合同金额',
   paid_amount          varchar(100) comment '已付金额',
   sign_time            datetime comment '合同签订时间',
   end_time             datetime comment '合同有效期',
   contract_life        datetime comment '合同到期时间',
   contract_url         varchar(2566) comment '合同路径',
   contract_type        bigint(20) comment '合同类型',
   primary key (contract_id)
);

alter table contract comment '合同表';

/*==============================================================*/
/* Table: data_dictionary                                       */
/*==============================================================*/
create table data_dictionary
(
   type_code            bigint(20) not null comment '主键',
   info                 varchar(256) comment '配置项名字',
   type_name            varchar(256) comment '类型名称',
   dictcode             varchar(256) comment '业务代码',
   primary key (type_code)
);

alter table data_dictionary comment '数据字典';

/*==============================================================*/
/* Table: employees                                             */
/*==============================================================*/
create table employees
(
   code                 bigint(20) not null comment '用户编号',
   sex                  tinyint(2) comment '性别',
   name                 varchar(256) comment '姓名',
   telephone            varchar(20) comment '电话',
   id_card              varchar(50) comment '身份证号',
   title                varchar(256) comment '职称',
   primary key (code)
);

alter table employees comment '员工信息表';

/*==============================================================*/
/* Table: flow                                                  */
/*==============================================================*/
create table flow
(
   flow_id              bigint(20) not null comment '流程编号，主键',
   flow_no              bigint(20) comment '流程号，惟一列',
   flow_name            varchar(256) comment '流程名称',
   remark               varchar(256) comment '备注',
   primary key (flow_id)
);

alter table flow comment '流程表';

/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission
(
   permission_id        bigint(20) not null comment '权限id',
   permission_name      varchar(256) comment '权限名',
   primary key (permission_id)
);

alter table permission comment '权限表';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   bigint(20) not null comment '角色id',
   role_name            varchar(100) comment '角色名',
   primary key (id)
);

alter table role comment '角色表';

/*==============================================================*/
/* Table: role_permission                                       */
/*==============================================================*/
create table role_permission
(
   id                   bigint(20) not null comment '主键',
   role_id              bigint(20) comment '角色id',
   permission_id        bigint(20) comment '权限id',
   primary key (id)
);

alter table role_permission comment '角色权限关联表';

/*==============================================================*/
/* Table: tbl_flow_line                                         */
/*==============================================================*/
create table tbl_flow_line
(
   flow_line_id         bigint(20) not null comment '流程线编号，主键',
   flow_no              bigint(20) comment '流程号，与流程表对应',
   prev_node_id         bigint(20) comment '前一节点编号',
   next_node_id         bigint(20) comment '后一节点编号',
   remark               varchar(256) comment '备注',
   primary key (flow_line_id)
);

alter table tbl_flow_line comment '流程线表';

/*==============================================================*/
/* Table: tbl_flow_node                                         */
/*==============================================================*/
create table tbl_flow_node
(
   flow_node_id         bigint(20) not null comment '流程节点编号，主键',
   flow_no              bigint(20) comment '流程号，与流程表对应',
   flow_node_name       varchar(256) comment '流程节点名称',
   flow_node_role       varchar(256) comment '流程角色',
   remark               varchar(256) comment '备注',
   primary key (flow_node_id)
);

alter table tbl_flow_node comment '流程节点表';

/*==============================================================*/
/* Table: tbl_flow_role_user                                    */
/*==============================================================*/
create table tbl_flow_role_user
(
   id                   bigint(20) not null comment '主键',
   flow_role_name       varchar(256) comment '流程角色名称',
   user_id              bigint(20) comment '员工编号',
   dept_id              bigint(20) comment '部门编号',
   primary key (id)
);

alter table tbl_flow_role_user comment '流程角色_员工表';

/*==============================================================*/
/* Table: tbl_leave_audit                                       */
/*==============================================================*/
create table tbl_leave_audit
(
   audit_id             bigint(20) not null comment '审批编号，主键',
   leave_id             bigint(20) comment '合同编号，与请假表对应',
   flow_node_id         bigint(20) comment '节点编号',
   user_id              bigint(20) comment '审批人编号',
   user_name            varchar(100) comment '审批人姓名',
   audit_info           varchar(256) comment '审批意见',
   audit_date           varchar(256) comment '审批日期',
   primary key (audit_id)
);

alter table tbl_leave_audit comment '审批表';

/*==============================================================*/
/* Table: user_contract                                         */
/*==============================================================*/
create table user_contract
(
   id                   bigint(20) not null comment '主键',
   user_id              bigint(20) comment '用户编号',
   vehicle_number       bigint(20) comment '租车车辆编号',
   contract_id          bigint(20) comment '合同编号',
   flow_no              bigint(20) comment '流程号',
   status               int(4) comment '合同状态',
   current_node         bigint(20) comment '当前节点编号',
   primary key (id)
);

alter table user_contract comment '用户车辆合同管理表';

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role
(
   id                   bigint(20) not null comment '主键',
   user_id              bigint(20) comment '用户编号',
   role_id              bigint(20) comment '角色id',
   primary key (id)
);

alter table user_role comment '用户角色关联表';

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users
(
   code                 bigint(20) not null comment '用户编号',
   sex                  tinyint(2) comment '性别',
   name                 varchar(256) comment '姓名',
   addr                 varchar(256) comment '住址',
   telephone            varchar(20) comment '电话',
   id_card              varchar(50) comment '身份证号',
   reputation           int(5) comment '信誉分数',
   status               tinyint comment '状态',
   primary key (code)
);

alter table users comment '用户表';

/*==============================================================*/
/* Table: vehicle                                               */
/*==============================================================*/
create table vehicle
(
   id                   bigint(20) not null comment '编号',
   vehicle_number       varchar(256) comment '车辆编号',
   vehicle_name         varchar(100) comment '车辆名',
   vehicle_type         varchar(5) comment '车辆类型',
   pic                  varchar(256) comment '车辆照片',
   is_accident          tinyint comment '是否出过事故',
   is_rent_out          tinyint comment '是否出租',
   primary key (id)
);

alter table vehicle comment '车辆信息表';

/*==============================================================*/
/* Table: vehicle_type                                          */
/*==============================================================*/
create table vehicle_type
(
   id                   bigint(20) not null comment '编号',
   vehicle_no           bigint(20) comment '车辆类型编号',
   vehicle_type_name    varchar(256) comment '类型名称',
   inventory            int comment '库存',
   rent_out_number      int comment '出租数量',
   remark               varchar(256) comment '备注',
   primary key (id)
);

alter table vehicle_type comment '车辆类型表';

