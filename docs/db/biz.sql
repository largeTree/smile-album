-- 创建相册表
CREATE TABLE `lq_album` (
	`id` INT(11) PRIMARY KEY,
	`name` VARCHAR(32) NOT NULL COMMENT'相册名称',
	`only_self` TINYINT(2) DEFAULT 0 COMMENT'是否私有相册',
	`desc` VARCHAR(64) NOT NULL COMMENT'相册描述',
	`status` TINYINT(2) DEFAULT 1 COMMENT'状态，1：有效，0：无效',
	`created_by` INT(11) NOT NULL COMMENT'创建人',
	`created_date` DATETIME NOT NULL COMMENT'创建时间',
	`updated_by` INT(11) NOT NULL COMMENT'更新时间',
	`updated_date` DATETIME NOT NULL COMMENT'更新时间',
);