-- 用户表
CREATE TABLE `user` (
	`id` INT(11) PRIMARY KEY,
	`login_id` VARCHAR(16) NOT NULL COMMENT'帐号',
	`password` VARCHAR(32) NOT NULL COMMENT'密码', 
	`name` VARCHAR(8) NOT NULL COMMENT'用户名',
	`phone` INT(11) NOT NULL DEFAULT 0 COMMENT'手机号码',
	`email` VARCHAR(64) NOT NULL DEFAULT '' COMMENT'邮箱',
	`sign` VARCHAR(32) DEFAULT '' COMMENT'个性签名',
	`status` TINYINT(1) DEFAULT 1 COMMENT'状态',
	`role_ids` VARCHAR(32) NOT NULL DEFAULT '2' COMMENT'角色ID',
	`ds_id` VARCHAR(16) NOT NULL COMMENT'数据库ID',
	`created_date` DATETIME NOT NULL COMMENT'创建时间',
	`updated_date` DATETIME NOT NULL COMMENT'更新时间',
	UNIQUE KEY `UK_user_login_id`(`login_id`)
);

-- 创建相册表
CREATE TABLE `album` (
	`id` INT(11) PRIMARY KEY,
	`name` VARCHAR(32) NOT NULL COMMENT'相册名称',
	`only_self` TINYINT(2) DEFAULT 0 COMMENT'是否私有相册',
	`desc` VARCHAR(64) NOT NULL COMMENT'相册描述',
	`status` TINYINT(2) DEFAULT 1 COMMENT'状态，1：有效，0：无效',
	`cover` VARCHAR(1024) DEFAULT '' COMMENT'相册封面', 
	`created_by` INT(11) NOT NULL COMMENT'创建人',
	`created_date` DATETIME NOT NULL COMMENT'创建时间',
	`updated_by` INT(11) NOT NULL COMMENT'更新时间',
	`updated_date` DATETIME NOT NULL COMMENT'更新时间'
);

-- 创建角色表
CREATE TABLE `role` (
	`id` TINYINT(2) PRIMARY KEY AUTO_INCREMENT,
	`code` VARCHAR(8) NOT NULL COMMENT'角色编码',
	`name` VARCHAR(8) NOT NULL COMMENT'角色名',
	UNIQUE KEY `UK_role_code`(`code`)
);
