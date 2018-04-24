/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : wdog

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-04-24 17:23:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  `parent_id` varchar(30) DEFAULT NULL,
  `type` int(1) NOT NULL DEFAULT '0',
  `url` varchar(100) DEFAULT NULL,
  `permission` varchar(50) DEFAULT NULL,
  `icon` varchar(30) DEFAULT NULL,
  `sort` int(11) NOT NULL DEFAULT '0',
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('987974185122832386', '角色添加', '987981659066376194', '1', '/system/role/add/**', 'system:role:add', '', '0', '2018-04-22 16:39:36');
INSERT INTO `sys_resource` VALUES ('987977834901315586', '首页', null, '0', '/', '', 'ios-home-outline', '-1', '2018-04-22 16:54:06');
INSERT INTO `sys_resource` VALUES ('987980763175624706', '角色更新', '987981659066376194', '1', '/system/role/update/**', 'system:role:update', '', '0', '2018-04-22 17:05:44');
INSERT INTO `sys_resource` VALUES ('987981277195968513', '系统设置', null, '0', '/system/**', 'system:*', 'ios-gear', '0', '2018-04-22 17:07:46');
INSERT INTO `sys_resource` VALUES ('987981486382686210', '用户管理', '987981277195968513', '0', '/system/user/**', 'system:user:*', 'person', '0', '2018-04-22 17:08:36');
INSERT INTO `sys_resource` VALUES ('987981659066376194', '角色管理', '987981277195968513', '0', '/system/role/**', 'system:role:*', 'compose', '0', '2018-04-22 17:09:17');
INSERT INTO `sys_resource` VALUES ('987982017863917570', '资源管理', '987981277195968513', '0', '/system/resource/**', 'system:resource:*', 'lock-combination', '0', '2018-04-22 17:10:43');
INSERT INTO `sys_resource` VALUES ('987985589477351426', '资源添加', '987982017863917570', '1', '/system/resource/add/**', 'system:resource:add', '', '0', '2018-04-22 17:24:55');
INSERT INTO `sys_resource` VALUES ('987985700785790978', '资源更新', '987982017863917570', '1', '/system/resource/update/**', 'system:resource:update', '', '0', '2018-04-22 17:25:21');
INSERT INTO `sys_resource` VALUES ('987985782880903170', '资源删除', '987982017863917570', '1', '/system/resource/remove/**', 'system:resource:remove', '', '0', '2018-04-22 17:25:41');
INSERT INTO `sys_resource` VALUES ('987985944781037570', '角色删除', '987981659066376194', '1', '/system/role/remove/**', 'system:role:remove', '', '0', '2018-04-22 17:26:19');
INSERT INTO `sys_resource` VALUES ('987986018126831617', '用户添加', '987981486382686210', '1', '/system/user/add/**', 'system:user:add', '', '0', '2018-04-22 17:26:37');
INSERT INTO `sys_resource` VALUES ('987986318946508801', '用户更新', '987981486382686210', '1', '/system/user/update/**', 'system:user:update', '', '0', '2018-04-22 17:27:48');
INSERT INTO `sys_resource` VALUES ('987986542024761345', '用户删除', '987981486382686210', '1', '/system/user/remove/**', 'system:user:remove', '', '0', '2018-04-22 17:28:42');
INSERT INTO `sys_resource` VALUES ('988254531404496898', '资源列表', '987982017863917570', '1', '/system/resource/list/**', 'system:resource:list', '', '0', '2018-04-23 11:13:35');
INSERT INTO `sys_resource` VALUES ('988602498871316482', '角色列表', '987981659066376194', '1', '/system/role/list/**', 'system:role:list', '', '0', '2018-04-24 10:16:17');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(30) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('988623554205990914', '管理员');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` varchar(30) NOT NULL,
  `rid` varchar(30) NOT NULL,
  `pid` varchar(30) NOT NULL,
  PRIMARY KEY (`id`,`rid`,`pid`),
  KEY `rid` (`rid`),
  KEY `pid` (`pid`),
  CONSTRAINT `sys_role_resource_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_resource_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `sys_resource` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('988679371458461698', '988623554205990914', '987981277195968513');
INSERT INTO `sys_role_resource` VALUES ('988679371567513601', '988623554205990914', '987981486382686210');
INSERT INTO `sys_role_resource` VALUES ('988679371672371202', '988623554205990914', '987986018126831617');
INSERT INTO `sys_role_resource` VALUES ('988679371781423105', '988623554205990914', '987986318946508801');
INSERT INTO `sys_role_resource` VALUES ('988679371882086401', '988623554205990914', '987986542024761345');
INSERT INTO `sys_role_resource` VALUES ('988679371991138306', '988623554205990914', '987981659066376194');
INSERT INTO `sys_role_resource` VALUES ('988679372091801601', '988623554205990914', '987974185122832386');
INSERT INTO `sys_role_resource` VALUES ('988679372200853506', '988623554205990914', '987980763175624706');
INSERT INTO `sys_role_resource` VALUES ('988679372301516801', '988623554205990914', '987985944781037570');
INSERT INTO `sys_role_resource` VALUES ('988679372406374401', '988623554205990914', '988602498871316482');
INSERT INTO `sys_role_resource` VALUES ('988679372511232001', '988623554205990914', '987982017863917570');
INSERT INTO `sys_role_resource` VALUES ('988679372616089601', '988623554205990914', '987985589477351426');
INSERT INTO `sys_role_resource` VALUES ('988679372716752897', '988623554205990914', '987985700785790978');
INSERT INTO `sys_role_resource` VALUES ('988679372825804802', '988623554205990914', '987985782880903170');
INSERT INTO `sys_role_resource` VALUES ('988679372930662402', '988623554205990914', '988254531404496898');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(30) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `status` int(1) NOT NULL COMMENT '1',
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`,`username`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'b9d11b3be25f5a1a7dc8ca04cd310b28', '20', '1', '2018-04-18 15:01:04');
INSERT INTO `sys_user` VALUES ('2', 'user', 'ad42c83ac4d3b86de14f207c46a0df0e', '20', '1', '2018-04-18 15:01:08');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(30) NOT NULL,
  `uid` varchar(30) NOT NULL,
  `rid` varchar(30) NOT NULL,
  PRIMARY KEY (`id`,`uid`,`rid`),
  KEY `user_role_ibfk_2` (`uid`),
  KEY `user_role_ibfk_1` (`rid`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '988623554205990914');
