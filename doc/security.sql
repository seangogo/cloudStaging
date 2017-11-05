/*
Navicat MySQL Data Transfer

Source Server         : asus
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : security

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-05 23:13:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `opt_account`
-- ----------------------------
DROP TABLE IF EXISTS `opt_account`;
CREATE TABLE `opt_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `account_way` int(11) DEFAULT NULL,
  `account_lock_time` bigint(20) NOT NULL,
  `expire_time` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_password` datetime DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKniqdafy1me4ahiomhss5vamef` (`user_id`),
  CONSTRAINT `FKniqdafy1me4ahiomhss5vamef` FOREIGN KEY (`user_id`) REFERENCES `opt_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_account
-- ----------------------------
INSERT INTO `opt_account` VALUES ('1', '1', '2017-11-05 15:21:05', '0', '1', '2017-11-05 15:21:10', '0', '1', '100', '2017-12-01 15:21:22', '$2a$10$CKHwdv/VOfwtgtAOrAgEQe4AWQYBB4XGM9HLvaBoY4M2luUpTuBca', '0', '2017-11-05 15:21:31', 'admin', null);
INSERT INTO `opt_account` VALUES ('2', '1', '2017-11-05 15:32:10', '0', '1', '2017-11-05 15:32:21', '0', '1', '100', '2017-12-02 15:32:28', '$2a$10$WkH3KT7XimYBZKkFNjXF.uymeIH7QKI6Ot9esWQoe2xGsvN/GzpZa', '0', '2017-11-05 15:32:40', 'admin1', null);

-- ----------------------------
-- Table structure for `opt_brand`
-- ----------------------------
DROP TABLE IF EXISTS `opt_brand`;
CREATE TABLE `opt_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_brand
-- ----------------------------

-- ----------------------------
-- Table structure for `opt_notice`
-- ----------------------------
DROP TABLE IF EXISTS `opt_notice`;
CREATE TABLE `opt_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `open_status` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_notice
-- ----------------------------

-- ----------------------------
-- Table structure for `opt_organize`
-- ----------------------------
DROP TABLE IF EXISTS `opt_organize`;
CREATE TABLE `opt_organize` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `org_desc` varchar(255) DEFAULT NULL,
  `org_level` varchar(255) DEFAULT NULL,
  `org_name` varchar(255) DEFAULT NULL,
  `org_status` int(11) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `project_id` varchar(255) DEFAULT NULL,
  `seq_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_organize
-- ----------------------------

-- ----------------------------
-- Table structure for `opt_project`
-- ----------------------------
DROP TABLE IF EXISTS `opt_project`;
CREATE TABLE `opt_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `project_code` varchar(255) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_project
-- ----------------------------

-- ----------------------------
-- Table structure for `opt_resource`
-- ----------------------------
DROP TABLE IF EXISTS `opt_resource`;
CREATE TABLE `opt_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `link` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkc9swfye2y7fc7gilcuel3syh` (`parent_id`),
  CONSTRAINT `FKkc9swfye2y7fc7gilcuel3syh` FOREIGN KEY (`parent_id`) REFERENCES `opt_resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_resource
-- ----------------------------
INSERT INTO `opt_resource` VALUES ('1', '1', '2017-11-05 15:37:15', '0', '1', '2017-11-05 15:37:20', '0', 'root', '123', '根节点', '根节点', '1', null, null, null);
INSERT INTO `opt_resource` VALUES ('2', '1', '2017-11-05 15:39:08', '0', '1', '2017-11-05 15:39:12', '0', 'home', '123', '首页', '首页', '1', 'MENU', '1', null);
INSERT INTO `opt_resource` VALUES ('3', '1', '2017-11-05 15:40:03', '0', '1', '2017-11-05 15:40:09', '0', 'desktop', '123', '管理平台', '管理平台', '2', 'MENU', '1', 'desktop');
INSERT INTO `opt_resource` VALUES ('4', '1', '2017-11-05 15:40:56', '0', '1', '2017-11-05 15:41:02', '0', 'roleManage', 'roleManage', '角色管理', '角色管理', '3', 'MENU', '3', 'roleManage');
INSERT INTO `opt_resource` VALUES ('5', '1', '2017-11-05 15:41:34', '0', '1', '2017-11-05 15:41:38', '0', 'adminManage', 'adminManage', '用户管理', '用户管理', '4', 'MENU', '3', 'adminManage');
INSERT INTO `opt_resource` VALUES ('6', '1', '2017-11-05 15:42:14', '0', '1', '2017-11-05 15:42:22', '0', 'admin:save', 'admin:save', '用户新增', '用户新增', '5', 'BUTTON', '5', 'admin:save');

-- ----------------------------
-- Table structure for `opt_role`
-- ----------------------------
DROP TABLE IF EXISTS `opt_role`;
CREATE TABLE `opt_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_role
-- ----------------------------
INSERT INTO `opt_role` VALUES ('1', '1', '2017-11-05 15:33:27', '0', '1', '2017-11-05 15:33:32', '0', 'superadmin', '超级管理员', '1', '超级管理员');
INSERT INTO `opt_role` VALUES ('2', '1', '2017-11-05 15:34:06', '0', '1', '2017-11-05 15:34:11', '0', 'admin', '普通管理员', '2', '普通管理员');

-- ----------------------------
-- Table structure for `opt_role_account`
-- ----------------------------
DROP TABLE IF EXISTS `opt_role_account`;
CREATE TABLE `opt_role_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK63cxq12c4iix8o2y9rt2ioqtb` (`account_id`),
  KEY `FKqbl36h5goj1x4obof8d16ln28` (`role_id`),
  CONSTRAINT `FK63cxq12c4iix8o2y9rt2ioqtb` FOREIGN KEY (`account_id`) REFERENCES `opt_account` (`id`),
  CONSTRAINT `FKqbl36h5goj1x4obof8d16ln28` FOREIGN KEY (`role_id`) REFERENCES `opt_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_role_account
-- ----------------------------
INSERT INTO `opt_role_account` VALUES ('1', '1', '2017-11-05 15:35:14', '0', '1', '2017-11-05 15:35:21', '0', '1', '1');
INSERT INTO `opt_role_account` VALUES ('2', '2', '2017-11-05 15:35:40', '0', '1', '2017-11-05 15:35:49', '0', '1', '2');
INSERT INTO `opt_role_account` VALUES ('3', '3', '2017-11-05 15:36:42', '0', '1', '2017-11-05 15:36:46', '0', '2', '1');

-- ----------------------------
-- Table structure for `opt_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `opt_role_resource`;
CREATE TABLE `opt_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5v7jjr9qemkd7pi5nsoojaemo` (`resource_id`),
  KEY `FKnpi9icpm6c29vnjr8pc62ln2l` (`role_id`),
  CONSTRAINT `FK5v7jjr9qemkd7pi5nsoojaemo` FOREIGN KEY (`resource_id`) REFERENCES `opt_resource` (`id`),
  CONSTRAINT `FKnpi9icpm6c29vnjr8pc62ln2l` FOREIGN KEY (`role_id`) REFERENCES `opt_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_role_resource
-- ----------------------------
INSERT INTO `opt_role_resource` VALUES ('1', '1', '2017-11-05 15:44:29', '0', '1', '2017-11-05 15:44:35', '0', '1', '1');
INSERT INTO `opt_role_resource` VALUES ('2', null, null, null, null, null, null, '2', '1');
INSERT INTO `opt_role_resource` VALUES ('3', null, null, null, null, null, null, '3', '1');
INSERT INTO `opt_role_resource` VALUES ('4', null, null, null, null, null, null, '4', '1');
INSERT INTO `opt_role_resource` VALUES ('5', null, null, null, null, null, null, '5', '1');
INSERT INTO `opt_role_resource` VALUES ('6', null, null, null, null, null, null, '6', '1');
INSERT INTO `opt_role_resource` VALUES ('7', null, null, null, null, null, null, '1', '2');
INSERT INTO `opt_role_resource` VALUES ('8', null, null, null, null, null, null, '2', '2');

-- ----------------------------
-- Table structure for `opt_user`
-- ----------------------------
DROP TABLE IF EXISTS `opt_user`;
CREATE TABLE `opt_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `brand_id` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `organize_id` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_user
-- ----------------------------
INSERT INTO `opt_user` VALUES ('1', '1', '2017-11-15 22:32:15', '0', '11', '2017-11-05 22:32:21', '0', '1', '949247244@qq.com', 'url', '1', '13997956178', '肖军', '0', '1');
INSERT INTO `opt_user` VALUES ('2', '1', '2017-11-30 22:33:12', '0', '12', '2017-11-05 22:33:20', '0', '1', '565656@qq.com', 'url', '1', '13997965656', '修饰', '0', '2');
INSERT INTO `opt_user` VALUES ('3', '1', '2017-11-22 22:33:46', '0', '13', '2017-11-05 22:33:53', '0', '1', '59989@qqc.om', 'uil', '1', '13669656565', 'lisi', '0', '2');
INSERT INTO `opt_user` VALUES ('4', '1', '2017-11-05 22:34:32', '0', '123', '2017-11-25 22:34:37', '0', '1', '12312@qq.com', 'uy5', '1', '13656656578', 'zhansan', '0', '3');

-- ----------------------------
-- Table structure for `persistent_logins`
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for `resource_urls`
-- ----------------------------
DROP TABLE IF EXISTS `resource_urls`;
CREATE TABLE `resource_urls` (
  `resource_id` bigint(20) NOT NULL,
  `urls` varchar(255) DEFAULT NULL,
  KEY `FKfy9jeatj0lu9fpp0txs06cbu0` (`resource_id`),
  CONSTRAINT `FKfy9jeatj0lu9fpp0txs06cbu0` FOREIGN KEY (`resource_id`) REFERENCES `opt_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of resource_urls
-- ----------------------------
INSERT INTO `resource_urls` VALUES ('5', 'manager/**');
INSERT INTO `resource_urls` VALUES ('5', 'manager/user');
INSERT INTO `resource_urls` VALUES ('6', '/user/**');
INSERT INTO `resource_urls` VALUES ('6', '/user/save');
INSERT INTO `resource_urls` VALUES ('6', 'user/login');
INSERT INTO `resource_urls` VALUES ('6', '/user/list');
