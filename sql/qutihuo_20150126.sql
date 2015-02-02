-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.16 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 qutihuo 的数据库结构
CREATE DATABASE IF NOT EXISTS `qutihuo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `qutihuo`;


-- 导出  表 qutihuo.tb_coupon 结构
CREATE TABLE IF NOT EXISTS `tb_coupon` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `coupon_number` varchar(13) NOT NULL COMMENT '优惠券码',
  `coupon_password` varchar(16) NOT NULL COMMENT '优惠券密码',
  `coupon_schema_id` int(11) NOT NULL COMMENT '优惠券模板ID',
  `state` smallint(1) NOT NULL COMMENT '状态',
  `use_startdate` datetime NOT NULL COMMENT '开始日期',
  `use_enddate` datetime NOT NULL COMMENT '结束日期',
  `order_number` varchar(20) DEFAULT NULL COMMENT '使用订单号',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  `create_time` datetime NOT NULL COMMENT '生成时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `coupon_number` (`coupon_number`),
  KEY `coupon_schema_id` (`coupon_schema_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券';

-- 正在导出表  qutihuo.tb_coupon 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_coupon` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_coupon` ENABLE KEYS */;


-- 导出  表 qutihuo.tb_coupon_schema 结构
CREATE TABLE IF NOT EXISTS `tb_coupon_schema` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `coupon_name` varchar(256) NOT NULL COMMENT '优惠券名称',
  `icon_url` varchar(256) DEFAULT NULL COMMENT 'icon地址',
  `merchant_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商户ID',
  `exchange_mode` tinyint(4) NOT NULL COMMENT '兑换模式 1 无 2 多选一',
  `exchange_item` varchar(512) DEFAULT NULL COMMENT '兑换选项 json格式，后台设置',
  `use_startdate` datetime NOT NULL COMMENT '开始时间',
  `use_enddate` datetime NOT NULL COMMENT '结束时间',
  `init_activate` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否初始化激活',
  `par_value` decimal(10,2) NOT NULL COMMENT '面值',
  `issue_amount` int(11) NOT NULL COMMENT '发行总量',
  `has_issue_num` int(11) NOT NULL DEFAULT '0' COMMENT '已发行量',
  `has_use_num` int(11) NOT NULL DEFAULT '0' COMMENT '已使用量',
  `has_confusion` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已扰乱',
  `day_limit_num` int(11) NOT NULL COMMENT '日限兑换量',
  `front_day_num` smallint(6) NOT NULL COMMENT '提前预约天数',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `merchant_id` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券方案';

-- 正在导出表  qutihuo.tb_coupon_schema 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_coupon_schema` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_coupon_schema` ENABLE KEYS */;


-- 导出  表 qutihuo.tb_finance_flow_log 结构
CREATE TABLE IF NOT EXISTS `tb_finance_flow_log` (
  `id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `oper_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '操作类型',
  `merchant_id` int(10) unsigned DEFAULT '0' COMMENT '商户ID',
  `operation` varchar(256) NOT NULL DEFAULT '0' COMMENT '操作描述',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `merchant_id` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='财务流水日志表';

-- 正在导出表  qutihuo.tb_finance_flow_log 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_finance_flow_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_finance_flow_log` ENABLE KEYS */;


-- 导出  表 qutihuo.tb_merchant 结构
CREATE TABLE IF NOT EXISTS `tb_merchant` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '商户ID',
  `merchant_name` varchar(128) NOT NULL DEFAULT '0' COMMENT '商户名称',
  `business_line` varchar(256) NOT NULL DEFAULT '0' COMMENT '主营业务',
  `merchant_level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '商户等级',
  `open_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开通时间',
  `expire_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '到期时间',
  `available_sms_num` int(11) NOT NULL DEFAULT '0' COMMENT '可用短信数量',
  `record_number` varchar(50) DEFAULT '0' COMMENT '备案号',
  `telephone` varchar(32) DEFAULT '0' COMMENT '联系电话',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户表';

-- 正在导出表  qutihuo.tb_merchant 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_merchant` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_merchant` ENABLE KEYS */;


-- 导出  表 qutihuo.tb_order_main 结构
CREATE TABLE IF NOT EXISTS `tb_order_main` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_main_no` varchar(20) NOT NULL COMMENT '主订单号',
  `coupon_number` varchar(20) NOT NULL COMMENT '优惠券码',
  `merchant_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商户ID',
  `state` tinyint(4) NOT NULL COMMENT '订单状态',
  `pay_amount` decimal(10,2) NOT NULL COMMENT '订单金额',
  `order_date` varchar(10) NOT NULL COMMENT '预定日期',
  `consignee` varchar(200) NOT NULL COMMENT '收货人',
  `telephone` varchar(20) NOT NULL COMMENT '联系电话',
  `province_code` varchar(20) NOT NULL COMMENT '省编码',
  `city_code` varchar(20) NOT NULL COMMENT '市编码',
  `area_code` varchar(20) NOT NULL COMMENT '区编码',
  `detail_address` varchar(200) NOT NULL COMMENT '详细地址',
  `logistics_company` varchar(50) DEFAULT NULL COMMENT '物流公司',
  `express_order_no` varchar(50) DEFAULT NULL COMMENT '快递单号',
  `deliver_time` datetime DEFAULT NULL COMMENT '发货时间',
  `is_send_sms` tinyint(4) DEFAULT NULL COMMENT '是否已发短信',
  `is_print_express` tinyint(4) DEFAULT '0' COMMENT '是否已打印快递单',
  `source` tinyint(4) DEFAULT '0' COMMENT '订单来源 1 后台下单 2 pc下单 3 wap下单',
  `remark` varchar(512) DEFAULT '0' COMMENT '订单备注',
  `create_time` datetime DEFAULT NULL COMMENT '下单时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_order_main_no` (`order_main_no`),
  KEY `index_order_date` (`order_date`),
  KEY `merchant_id` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 ROW_FORMAT=DYNAMIC COMMENT='订单表';

-- 正在导出表  qutihuo.tb_order_main 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_order_main` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_order_main` ENABLE KEYS */;


-- 导出  表 qutihuo.tb_order_repl 结构
CREATE TABLE IF NOT EXISTS `tb_order_repl` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_repl_no` varchar(20) NOT NULL COMMENT '补货订单号',
  `order_main_no` varchar(20) NOT NULL COMMENT '主订单号',
  `state` tinyint(4) NOT NULL COMMENT '订单状态',
  `pay_amount` decimal(10,2) NOT NULL COMMENT '订单金额',
  `order_date` varchar(10) NOT NULL COMMENT '预定日期',
  `consignee` varchar(200) NOT NULL COMMENT '收货人',
  `mobile_phone` varchar(20) NOT NULL COMMENT '联系电话',
  `province_code` varchar(20) NOT NULL COMMENT '省编码',
  `city_code` varchar(20) NOT NULL COMMENT '市编码',
  `area_code` varchar(20) NOT NULL COMMENT '区编码',
  `detail_address` varchar(200) NOT NULL COMMENT '详细地址',
  `logistics_company` varchar(50) DEFAULT NULL COMMENT '物流公司',
  `express_order_no` varchar(50) DEFAULT NULL COMMENT '快递单号',
  `deliver_time` datetime DEFAULT NULL COMMENT '发货时间',
  `is_send_sms` tinyint(4) DEFAULT NULL COMMENT '是否已发短信',
  `is_print_express` tinyint(4) DEFAULT '0' COMMENT '是否已打印快递单',
  `source` tinyint(4) DEFAULT '0' COMMENT '订单来源 1 后台下单 2 pc下单 3 wap下单',
  `remark` varchar(512) DEFAULT '0' COMMENT '订单备注',
  `create_time` datetime DEFAULT NULL COMMENT '下单时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_order_repl_no` (`order_repl_no`),
  KEY `fk_order_main_no` (`order_main_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 ROW_FORMAT=DYNAMIC COMMENT='补货单表';

-- 正在导出表  qutihuo.tb_order_repl 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_order_repl` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_order_repl` ENABLE KEYS */;


-- 导出  表 qutihuo.tb_system_action 结构
CREATE TABLE IF NOT EXISTS `tb_system_action` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `action_name` varchar(64) NOT NULL COMMENT '操作名',
  `action_type` tinyint(4) NOT NULL COMMENT '节点类型 1 目录 2 菜单 3 操作',
  `parent_id` int(11) NOT NULL COMMENT '父节点ID',
  `url` varchar(256) DEFAULT NULL COMMENT 'url',
  `icon_css` varchar(256) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL COMMENT '操作描述',
  `weight` int(11) NOT NULL COMMENT '权重',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='功能表';

-- 正在导出表  qutihuo.tb_system_action 的数据：~19 rows (大约)
/*!40000 ALTER TABLE `tb_system_action` DISABLE KEYS */;
INSERT INTO `tb_system_action` (`id`, `action_name`, `action_type`, `parent_id`, `url`, `icon_css`, `remark`, `weight`, `create_time`) VALUES
	(1, 'Home', 2, 0, '/index.jhtml', 'menu-icon fa fa-dashboard', 'Home', 999999, '2014-12-03 12:20:20'),
	(2, '用户管理', 1, 0, '', 'menu-icon fa  fa-user', '用户管理根节点', 999999, '2014-12-03 12:20:20'),
	(3, '用户列表', 2, 2, '/system/userList.jhtml', ' ', '用户列表', 999999, '2014-12-03 12:20:20'),
	(4, '角色管理', 2, 2, '/system/roleList.jhtml', NULL, '角色列表', 999999, '2014-12-03 12:20:20'),
	(6, '礼品卡管理', 1, 0, '', 'menu-icon glyphicon glyphicon-gift', '礼品卡根节点', 999999, '2014-12-03 12:20:20'),
	(7, '礼品卡列表', 2, 6, '/roleList.jhtml', NULL, '礼品卡列表', 999999, '2014-12-03 12:20:20'),
	(8, '商户管理', 1, 0, '', 'menu-icon fa fa-desktop', '商户管理根节点', 999999, '2014-12-03 12:20:20'),
	(9, '商户信息', 2, 8, '/roleList.jhtml', NULL, '商户信息', 999999, '2014-12-03 12:20:20'),
	(10, '账号安全', 2, 8, '/roleList.jhtml', NULL, '账号安全', 999999, '2014-12-03 12:20:20'),
	(11, '订单管理', 1, 0, '', 'menu-icon fa fa-bar-chart-o', '订单管理根节点', 999999, '2014-12-03 12:20:20'),
	(12, '订单列表', 2, 11, '/roleList.jhtml', NULL, '订单列表', 999999, '2014-12-03 12:20:20'),
	(13, '补货单列表', 2, 11, '/roleList.jhtml', NULL, '补货单列表', 999999, '2014-12-03 12:20:20'),
	(14, '系统管理', 1, 0, '', 'menu-icon fa fa-cog', '系统管理根节点', 999999, '2014-12-03 12:20:20'),
	(15, '系统配置', 2, 14, '/system/configList.jhtml', NULL, '系统配置', 999999, '2014-12-03 12:20:20'),
	(16, '操作日志', 2, 14, '/roleList.jhtml', NULL, '操作日志', 999999, '2014-12-03 12:20:20'),
	(17, '菜单列表', 2, 14, '/system/actionList.jhtml', NULL, '菜单列表', 999999, '2014-12-03 12:20:20'),
	(18, '财务流水', 2, 14, '/roleList.jhtml', NULL, '财务流水', 999999, '2014-12-03 12:20:20'),
	(19, '菜单详情', 3, 17, '/system/actionDetail.jhtml', '', '菜单详情', 999999, '2014-12-03 12:20:20');
/*!40000 ALTER TABLE `tb_system_action` ENABLE KEYS */;


-- 导出  表 qutihuo.tb_system_config 结构
CREATE TABLE IF NOT EXISTS `tb_system_config` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `config_name` varchar(128) NOT NULL COMMENT '名称',
  `config_key` varchar(128) NOT NULL COMMENT '配置项key',
  `config_value` text COMMENT '配置项值',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `config_key` (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置表';

-- 正在导出表  qutihuo.tb_system_config 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_system_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_system_config` ENABLE KEYS */;


-- 导出  表 qutihuo.tb_system_operate_log 结构
CREATE TABLE IF NOT EXISTS `tb_system_operate_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '系统日志ID',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `oper_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '操作类型',
  `merchant_id` int(10) unsigned DEFAULT '0' COMMENT '商户ID',
  `operation` varchar(256) NOT NULL DEFAULT '0' COMMENT '操作描述',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `merchant_id` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统操作日志表';

-- 正在导出表  qutihuo.tb_system_operate_log 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_system_operate_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_system_operate_log` ENABLE KEYS */;


-- 导出  表 qutihuo.tb_system_privilage 结构
CREATE TABLE IF NOT EXISTS `tb_system_privilage` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '系统权限ID',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色ID',
  `action_id` int(10) unsigned NOT NULL COMMENT '操作ID',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限表';

-- 正在导出表  qutihuo.tb_system_privilage 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_system_privilage` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_system_privilage` ENABLE KEYS */;


-- 导出  表 qutihuo.tb_system_role 结构
CREATE TABLE IF NOT EXISTS `tb_system_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '角色类型 1 系统默认 2 自定义',
  `merchant_id` int(10) unsigned DEFAULT '0' COMMENT '商户ID',
  `role_name` varchar(64) NOT NULL DEFAULT '0' COMMENT '角色名称',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- 正在导出表  qutihuo.tb_system_role 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `tb_system_role` DISABLE KEYS */;
INSERT INTO `tb_system_role` (`id`, `role_type`, `merchant_id`, `role_name`, `create_time`) VALUES
	(1, 1, NULL, '超级管理员', '2014-12-03 12:20:20'),
	(4, 2, NULL, '测试角色2', '2015-01-25 16:55:03'),
	(5, 2, NULL, '测试角色1', '2015-01-25 16:58:00'),
	(6, 2, NULL, '测试角色3', '2015-01-25 16:58:10'),
	(7, 2, NULL, '测试角色4', '2015-01-25 16:58:21'),
	(8, 2, NULL, '测试角色5', '2015-01-25 16:58:32'),
	(9, 2, NULL, '测试角色7', '2015-01-25 16:58:44'),
	(10, 2, NULL, '测试角色6', '2015-01-25 16:58:54'),
	(11, 2, NULL, '测试角色8', '2015-01-25 16:59:11');
/*!40000 ALTER TABLE `tb_system_role` ENABLE KEYS */;


-- 导出  表 qutihuo.tb_system_user 结构
CREATE TABLE IF NOT EXISTS `tb_system_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_name` varchar(32) NOT NULL DEFAULT '0' COMMENT '登录名',
  `passwd` varchar(32) NOT NULL DEFAULT '0' COMMENT '密码',
  `nickname` varchar(32) DEFAULT '0' COMMENT '昵称',
  `telephone` varchar(20) NOT NULL DEFAULT '0' COMMENT '手机号码',
  `user_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户类型 1 系统管理员 2 商户',
  `user_title` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户头衔 1 超级管理员 2 户主',
  `merchant_id` int(10) unsigned DEFAULT '0' COMMENT '商户ID',
  `picture_url` varchar(256) NOT NULL DEFAULT '0' COMMENT '用户头像',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `role_ids` varchar(256) DEFAULT '0' COMMENT '用户角色IDS',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`),
  UNIQUE KEY `telephone` (`telephone`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- 正在导出表  qutihuo.tb_system_user 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `tb_system_user` DISABLE KEYS */;
INSERT INTO `tb_system_user` (`id`, `login_name`, `passwd`, `nickname`, `telephone`, `user_type`, `user_title`, `merchant_id`, `picture_url`, `state`, `role_ids`, `create_time`, `update_time`) VALUES
	(1, 'parcel', '123456', '大漠飞鹰', '18682481905', 1, 1, NULL, '0', 1, '0', '2014-12-03 12:20:20', '2014-12-12 12:30:30'),
	(2, 'luozj', '123456', '罗正加', '18682481906', 1, 1, NULL, '0', 1, '0', '2014-12-03 12:20:20', '2014-12-12 12:30:30'),
	(3, 'kakaxi', '123456', '卡卡西', '18682481907', 1, 1, NULL, '0', 1, '0', '2014-12-03 12:20:20', '2014-12-12 12:30:30'),
	(4, 'mingren', '123456', '鸣人', '18682481908', 1, 1, NULL, '0', 1, '0', '2014-12-03 12:20:20', '2014-12-12 12:30:30'),
	(5, 'xiaoli', '123456', '小李', '18682481909', 1, 1, NULL, '0', 1, '0', '2014-12-03 12:20:20', '2014-12-12 12:30:30'),
	(6, 'luwan', '123456', '鹿丸', '18682481910', 1, 1, NULL, '0', 1, '0', '2014-12-03 12:20:20', '2014-12-12 12:30:30'),
	(7, 'woailuo', '123456', '我爱罗', '18682481911', 1, 1, NULL, '0', 1, '0', '2014-12-03 12:20:20', '2014-12-12 12:30:30'),
	(8, 'xiao', '123456', '晓', '18682481912', 1, 1, NULL, '0', 1, '0', '2014-12-03 12:20:20', '2014-12-12 12:30:30'),
	(9, 'fengqy', '123456', '风清扬', '18682481913', 1, 1, NULL, '0', 1, '0', '2014-12-03 12:20:20', '2014-12-12 12:30:30'),
	(10, 'jlfw', '123456', '金轮法王', '18682481914', 1, 1, NULL, '0', 1, '0', '2014-12-03 12:20:20', '2014-12-12 12:30:30');
/*!40000 ALTER TABLE `tb_system_user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
