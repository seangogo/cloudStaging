/*
Navicat MySQL Data Transfer

Source Server         : asus
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : security

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-07 17:58:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account_old_passwords`
-- ----------------------------
DROP TABLE IF EXISTS `account_old_passwords`;
CREATE TABLE `account_old_passwords` (
  `account_id` varchar(36) NOT NULL,
  `old_passwords` varchar(255) DEFAULT NULL,
  KEY `FKhn7b5cj3kq2wcebkt2dj3ukip` (`account_id`),
  CONSTRAINT `FKhn7b5cj3kq2wcebkt2dj3ukip` FOREIGN KEY (`account_id`) REFERENCES `opt_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of account_old_passwords
-- ----------------------------

-- ----------------------------
-- Table structure for `opt_account`
-- ----------------------------
DROP TABLE IF EXISTS `opt_account`;
CREATE TABLE `opt_account` (
  `id` varchar(36) NOT NULL,
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
  `user_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKniqdafy1me4ahiomhss5vamef` (`user_id`),
  CONSTRAINT `FKniqdafy1me4ahiomhss5vamef` FOREIGN KEY (`user_id`) REFERENCES `opt_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_account
-- ----------------------------
INSERT INTO `opt_account` VALUES ('413ce8d6-fd4a-42e6-a519-05a84fcc681a', '1', '2017-11-06 23:14:31', '0', '3', '2017-11-06 23:14:36', '0', '1', '12333', '2017-12-01 23:14:48', '$2a$10$grP1xXWtw2Ogki7nXDaQxeiR5GqIl49LBZwSyX35PptSDWJVDC0Uq', '0', '2017-11-17 23:15:10', 'admin', '413ce8d6-fd4a-42e6-a519-05a84fcc681f');
INSERT INTO `opt_account` VALUES ('9a0300c5-595f-406a-a687-92c0531b08c6', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 00:18:59', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', null, '0', '0', '0', null, '$2a$10$D4pEc/EymQLCte/32FBGz.dQVZ5V50pR0VL8FPyA4Lcg9yG7R.2.2', '0', null, 'seangogo', '942d9d0e-831a-4b61-9e2c-70cf8670261c');

-- ----------------------------
-- Table structure for `opt_brand`
-- ----------------------------
DROP TABLE IF EXISTS `opt_brand`;
CREATE TABLE `opt_brand` (
  `id` varchar(36) NOT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
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
  `id` varchar(36) NOT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
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
  `id` varchar(36) NOT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
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
  `id` varchar(36) NOT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
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
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `sort` bigint(36) DEFAULT NULL,
  `type` int(4) DEFAULT NULL,
  `parent_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkc9swfye2y7fc7gilcuel3syh` (`parent_id`),
  CONSTRAINT `FKkc9swfye2y7fc7gilcuel3syh` FOREIGN KEY (`parent_id`) REFERENCES `opt_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_resource
-- ----------------------------
INSERT INTO `opt_resource` VALUES ('3696deb1-f0ac-4176-af80-f8194eb47982', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 17:52:13', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', null, '0', 'user/delete', 'string', 'string', '用户删除', '备2', '1510048329998', '1', 'fe289a7c-3260-47d0-8acd-17beda69ae7c');
INSERT INTO `opt_resource` VALUES ('413ce8d6-fd4a-42e6-a519-05a84fcc681g', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 11:17:21', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', null, '0', 'root', null, null, '根节点', '所有资源的根菜单', '1', '0', null);
INSERT INTO `opt_resource` VALUES ('49ada543-b200-42a8-920f-009493f88a6c', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 17:40:05', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 17:54:01', '1', 'user/delete', 'string', 'string', '用户sete', '备2', '1510047605296', '1', 'fe289a7c-3260-47d0-8acd-17beda69ae7c');
INSERT INTO `opt_resource` VALUES ('5a802403-6d58-4f56-a216-f20c7b0280c4', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 17:40:41', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', null, '0', 'user/delete', 'string', 'string', '用户删除', '备注222', '1510047640781', '1', 'fe289a7c-3260-47d0-8acd-17beda69ae7c');
INSERT INTO `opt_resource` VALUES ('758e28d1-ae53-46d2-ae4a-aa9fce2438af', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 17:43:31', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', null, '0', 'user/delete', 'string', 'string', '用户删除', '备2', '1510047810549', '1', 'fe289a7c-3260-47d0-8acd-17beda69ae7c');
INSERT INTO `opt_resource` VALUES ('9d75bfd4-4f60-466f-b4ca-9675170d5db3', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 17:54:35', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', null, '0', 'user/delete', 'string', 'string', '用户sete', '备2', '1510048471124', '1', 'fe289a7c-3260-47d0-8acd-17beda69ae7c');
INSERT INTO `opt_resource` VALUES ('9e02da60-c2ff-4b17-9234-64db248e6e72', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 17:47:55', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', null, '0', 'user/delete', 'string', 'string', '用户删除', '备2', '1510047939168', '1', 'fe289a7c-3260-47d0-8acd-17beda69ae7c');
INSERT INTO `opt_resource` VALUES ('a2bb24f2-78b9-4570-85fd-fcba8b8a2e1e', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 17:48:11', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', null, '0', 'user/delete', 'string', 'string', '用户删除', '备2', '1510048082298', '1', 'fe289a7c-3260-47d0-8acd-17beda69ae7c');
INSERT INTO `opt_resource` VALUES ('ce85456a-4949-4f02-859c-882eacff8586', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 13:03:27', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', null, '0', 'project:model', 'icon', 'project/list', '项目模块菜单', '项目模块菜单', '1510031006477', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681g');
INSERT INTO `opt_resource` VALUES ('fe289a7c-3260-47d0-8acd-17beda69ae7c', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 11:32:18', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', null, '0', 'userMenu', 'icon', 'user/list', '用户模块', '用户模块简介', '1510025537796', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681g');

-- ----------------------------
-- Table structure for `opt_role`
-- ----------------------------
DROP TABLE IF EXISTS `opt_role`;
CREATE TABLE `opt_role` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `project_id` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_role
-- ----------------------------
INSERT INTO `opt_role` VALUES ('413ce8d6-fd4a-42e6-a519-05a84fcc681g', '1', '2017-11-24 23:55:22', '0', '1', '2017-11-06 23:55:27', '0', '123', 'sdd', '1', 'ddd');

-- ----------------------------
-- Table structure for `opt_role_account`
-- ----------------------------
DROP TABLE IF EXISTS `opt_role_account`;
CREATE TABLE `opt_role_account` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `account_id` varchar(36) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK63cxq12c4iix8o2y9rt2ioqtb` (`account_id`),
  KEY `FKqbl36h5goj1x4obof8d16ln28` (`role_id`),
  CONSTRAINT `FK63cxq12c4iix8o2y9rt2ioqtb` FOREIGN KEY (`account_id`) REFERENCES `opt_account` (`id`),
  CONSTRAINT `FKqbl36h5goj1x4obof8d16ln28` FOREIGN KEY (`role_id`) REFERENCES `opt_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_role_account
-- ----------------------------
INSERT INTO `opt_role_account` VALUES ('413ce8d6-fd4a-42e6-a519-05a84fcc681s', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 00:14:51', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 00:14:59', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '413ce8d6-fd4a-42e6-a519-05a84fcc681g');

-- ----------------------------
-- Table structure for `opt_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `opt_role_resource`;
CREATE TABLE `opt_role_resource` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `resource_id` varchar(36) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5v7jjr9qemkd7pi5nsoojaemo` (`resource_id`),
  KEY `FKnpi9icpm6c29vnjr8pc62ln2l` (`role_id`),
  CONSTRAINT `FK5v7jjr9qemkd7pi5nsoojaemo` FOREIGN KEY (`resource_id`) REFERENCES `opt_resource` (`id`),
  CONSTRAINT `FKnpi9icpm6c29vnjr8pc62ln2l` FOREIGN KEY (`role_id`) REFERENCES `opt_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `opt_user`
-- ----------------------------
DROP TABLE IF EXISTS `opt_user`;
CREATE TABLE `opt_user` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(36) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(36) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `brand_id` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `organize_id` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `sex` int(11) NOT NULL,
  `id_card` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`sex`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of opt_user
-- ----------------------------
INSERT INTO `opt_user` VALUES ('413ce8d6-fd4a-42e6-a519-05a84fcc681f', '1', '2017-11-06 23:21:17', '0', '2', '2017-11-06 23:21:22', '0', '2', '949247328@qq.com', 'url', '1', '13997951656', '1', '肖军', '0', '429006198610115173');
INSERT INTO `opt_user` VALUES ('8683c937-8186-456c-aa8b-23788d4cfdc1', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 16:32:09', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', null, '0', null, '13@qqc.om', 'string', null, '13997951656', '1', 'string', '0', 'string');
INSERT INTO `opt_user` VALUES ('942d9d0e-831a-4b61-9e2c-70cf8670261c', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 00:18:59', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', null, '0', null, '94927328@qq.com', 'url', null, '13997956178', '1', 'seangogo', '0', '429006198610115172');
INSERT INTO `opt_user` VALUES ('9561688e-29b2-46b3-bcf4-af26df21758a', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 16:19:29', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', null, '0', null, '1111@qq.com', 'string', null, '13555555555', '1', 'string', '0', 'string');
INSERT INTO `opt_user` VALUES ('ad99a026-95c5-4128-8b1c-132217fe65a1', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 16:17:11', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', null, '0', null, '1111@qq.com', 'string', null, '13555555555', '1', 'string', '0', 'string');
INSERT INTO `opt_user` VALUES ('be00a952-54b7-43f8-96f1-fa646f4d34c6', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 13:38:46', '0', '413ce8d6-fd4a-42e6-a519-05a84fcc681a', '2017-11-07 16:33:00', '2', null, '13@qqc.om', 'string', null, '13997951656', '1', 'string', '0', '身份证');

-- ----------------------------
-- Table structure for `resource_urls`
-- ----------------------------
DROP TABLE IF EXISTS `resource_urls`;
CREATE TABLE `resource_urls` (
  `resource_id` varchar(36) NOT NULL,
  `urls` varchar(255) DEFAULT NULL,
  KEY `FKfy9jeatj0lu9fpp0txs06cbu0` (`resource_id`),
  CONSTRAINT `FKfy9jeatj0lu9fpp0txs06cbu0` FOREIGN KEY (`resource_id`) REFERENCES `opt_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of resource_urls
-- ----------------------------
INSERT INTO `resource_urls` VALUES ('fe289a7c-3260-47d0-8acd-17beda69ae7c', 'user/save');
INSERT INTO `resource_urls` VALUES ('ce85456a-4949-4f02-859c-882eacff8586', 'project/save');
INSERT INTO `resource_urls` VALUES ('5a802403-6d58-4f56-a216-f20c7b0280c4', 'user/delete');
INSERT INTO `resource_urls` VALUES ('758e28d1-ae53-46d2-ae4a-aa9fce2438af', 'user/delete');
INSERT INTO `resource_urls` VALUES ('9e02da60-c2ff-4b17-9234-64db248e6e72', 'user/delete');
INSERT INTO `resource_urls` VALUES ('a2bb24f2-78b9-4570-85fd-fcba8b8a2e1e', 'user/delete');
INSERT INTO `resource_urls` VALUES ('3696deb1-f0ac-4176-af80-f8194eb47982', 'user/delete');
INSERT INTO `resource_urls` VALUES ('49ada543-b200-42a8-920f-009493f88a6c', 'user/delete');
INSERT INTO `resource_urls` VALUES ('9d75bfd4-4f60-466f-b4ca-9675170d5db3', 'user/delete');
