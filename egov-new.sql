-- Oracle中的语句
-- drop table t_auth cascade constraints;
-- drop table t_en_inv cascade constraints;
-- drop table t_enterprise cascade constraints;
-- drop table t_invest cascade constraints;
-- drop table t_user cascade constraints;

-- drop sequence seqAuth;
-- drop sequence seqInv;

-- create sequence seqAuth
-- start with 1
-- increment by 1;

-- create sequence seqInv
-- start with 1
-- increment by 1;

use bjpowernode;

drop table if exists t_auth;
drop table if exists t_en_inv;
drop table if exists t_enterprise;
drop table if exists t_invest;
drop table if exists t_user;

/*==============================================================*/
/* Table: t_auth                                                */
/*==============================================================*/
create table t_auth
(
    orgcode    VARCHAR(32) not null,
    remark     VARCHAR(128),
    contactman VARCHAR(32) not null,
    contacttel VARCHAR(32) not null,
    filename   VARCHAR(32),
    fileremark VARCHAR(128),
    authno     int,
    usercode   VARCHAR(32) not null,
    feedback   CHAR(1)     not null,
    # '0 - 未反馈 ， 1 - 已反馈'

    constraint PK_T_AUTH primary key auto_increment (authno)
);

-- comment on column t_auth.feedback is '0 - 未反馈 ， 1 - 已反馈';

/*==============================================================*/
/* Table: t_en_inv                                              */
/*==============================================================*/
create table t_en_inv
(
    orgcode   VARCHAR(32) not null,
    invregnum VARCHAR(32) not null,
    regcap    numeric(9)  not null,
    scale     numeric(3)  not null,
    constraint PK_T_EN_INV primary key (orgcode, invregnum)
);

/*==============================================================*/
/* Table: t_enterprise                                          */
/*==============================================================*/
create table t_enterprise
(
    orgcode    VARCHAR(32) not null,
    regno      VARCHAR(32) not null,
    cnname     VARCHAR(64) not null,
    enname     VARCHAR(64),
    contactman VARCHAR(32),
    contacttel VARCHAR(32),
    regcap     numeric(9)  not null,
    outregcap  numeric(9)  not null,
    regcry     CHAR(3)     not null,
    usercode   VARCHAR(32) not null,
    regdate    CHAR(10)    not null,
    constraint PK_T_ENTERPRISE primary key (orgcode)
);

/*==============================================================*/
/* Table: t_invest                                              */
/*==============================================================*/
create table t_invest
(
    invregnum  int         not null,
    invname    VARCHAR(32) not null,
    cty        CHAR(3)     not null,
    # '000 - 中国， 001-美国, 002-英国 , 003-日本'
    orgcode    VARCHAR(32),
    contactman VARCHAR(32),
    contacttel VARCHAR(32),
    email      VARCHAR(32),
    remark     VARCHAR(128),
    usercode   VARCHAR(32) not null,
    regdate    CHAR(10)    not null,
    # 精确到日期即可
    primary key auto_increment (invregnum)
);

-- comment on column t_invest.cty is '000 - 中国， 001-美国, 002-英国 , 003-日本';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
    usercode VARCHAR(32) not null,
    username VARCHAR(32) not null,
    orgtype  CHAR(1)     not null,
    # 0 - 外汇管理局， 1 - 银行
    userpswd VARCHAR(32) not null,
    regdate  CHAR(20)    not null,
    constraint PK_T_USER primary key (usercode)
);

-- comment on column t_user.orgtype is '0 - 外汇管理局， 1 - 银行';

insert t_user
values ('king', '国王', 0, '123', '2021-03-20 14:35:51');