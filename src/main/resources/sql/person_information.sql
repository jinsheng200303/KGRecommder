DROP TABLE IF EXISTS person;
CREATE TABLE person(
                       `user_id` INT NOT NULL AUTO_INCREMENT  COMMENT '用户ID' ,
                       `user_name` VARCHAR(90) NOT NULL   COMMENT '用户名' ,
                       `id_card` VARCHAR(255)    COMMENT '身份证;18位身份证' ,
                       `name` VARCHAR(90)    COMMENT '真实姓名' ,
                       `password` VARCHAR(255) NOT NULL   COMMENT '密码' ,
                       `phone_number` VARCHAR(255) NOT NULL   COMMENT '手机号;11位手机号' ,
                       `gender` VARCHAR(32)   DEFAULT 'M' COMMENT '性别;性别说明' ,
                       `birth` DATETIME    COMMENT '出生日期' ,
                       `politics` VARCHAR(32)   DEFAULT '10' COMMENT '政治面貌' ,
                       `profession` VARCHAR(90)    COMMENT '职业' ,
                       `school` VARCHAR(90)    COMMENT '学校' ,
                       `age` INT    COMMENT '年龄' ,
                       `avatar` VARCHAR(255)    COMMENT '头像' ,
                       `synopsis` VARCHAR(255)    COMMENT '简介' ,
                       `permission` VARCHAR(90) NOT NULL   COMMENT '权限' ,
                       PRIMARY KEY (user_id)
)  COMMENT = '个人信息';

DROP TABLE IF EXISTS permission;
CREATE TABLE permission(
                           `permission_name` VARCHAR(255)    COMMENT '权限名' ,
                           `permission_id` INT NOT NULL AUTO_INCREMENT  COMMENT '权限ID' ,
                           PRIMARY KEY (permission_id)
)  COMMENT = '权限';

DROP TABLE IF EXISTS person_role;
CREATE TABLE person_role(
                            `person_role_id` INT NOT NULL AUTO_INCREMENT  COMMENT '个人角色关联ID' ,
                            `role_id` VARCHAR(255)    COMMENT '角色ID' ,
                            `user_id` VARCHAR(255)    COMMENT '用户ID' ,
                            PRIMARY KEY (person_role_id)
)  COMMENT = '个人角色关联表';

DROP TABLE IF EXISTS role;
CREATE TABLE role(
                     `role_id` INT NOT NULL AUTO_INCREMENT  COMMENT '角色ID' ,
                     `role_name` VARCHAR(32)   COMMENT '角色名' ,
                     PRIMARY KEY (role_id)
)  COMMENT = '角色';

DROP TABLE IF EXISTS role_permission;
CREATE TABLE role_permission(
                                `role_permission_id` INT NOT NULL AUTO_INCREMENT  COMMENT '角色权限关联ID' ,
                                `role_id` VARCHAR(255)    COMMENT '角色ID' ,
                                `permission_id` VARCHAR(255)    COMMENT '权限ID' ,
                                PRIMARY KEY (role_permission_id)
)  COMMENT = '角色权限关联表';

