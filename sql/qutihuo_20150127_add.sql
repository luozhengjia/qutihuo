CREATE TABLE `tb_logistics_company` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '物流公司ID',
  `lc_name` VARCHAR(256) NOT NULL  COMMENT '物流公司名称',
  `lc_code` VARCHAR(256) NOT NULL  COMMENT '物流公司编码',
  `express_template` VARCHAR(4000) COMMENT '快递模板',
  `contact` VARCHAR(32) DEFAULT '0' COMMENT '联系人',
  `telephone` VARCHAR(32) DEFAULT '0' COMMENT '联系电话',
  `create_time` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='物流公司表';