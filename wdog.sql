/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : wdog

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-04-27 16:01:44
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
  `color` varchar(10) DEFAULT NULL,
  `icon` varchar(30) DEFAULT NULL,
  `sort` int(11) NOT NULL DEFAULT '0',
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('987974185122832386', '角色添加', '987981659066376194', '1', '/system/role/add/**', 'system:role:add', '#19BE6B', '', '0', '2018-04-22 16:39:36');
INSERT INTO `sys_resource` VALUES ('987977834901315586', '首页', null, '0', '/', '', '#19BE6B', 'ios-home-outline', '-1', '2018-04-22 16:54:06');
INSERT INTO `sys_resource` VALUES ('987980763175624706', '角色更新', '987981659066376194', '1', '/system/role/update/**', 'system:role:update', '#19BE6B', '', '0', '2018-04-22 17:05:44');
INSERT INTO `sys_resource` VALUES ('987981277195968513', '系统设置', null, '0', '/system/**', 'system:*', '#19BE6B', 'ios-gear', '0', '2018-04-22 17:07:46');
INSERT INTO `sys_resource` VALUES ('987981486382686210', '用户管理', '987981277195968513', '0', '/system/user/**', 'system:user:*', '#19BE6B', 'person', '0', '2018-04-22 17:08:36');
INSERT INTO `sys_resource` VALUES ('987981659066376194', '角色管理', '987981277195968513', '0', '/system/role/**', 'system:role:*', '#19BE6B', 'compose', '0', '2018-04-22 17:09:17');
INSERT INTO `sys_resource` VALUES ('987982017863917570', '资源管理', '987981277195968513', '0', '/system/resource/**', 'system:resource:*', '#19BE6B', 'lock-combination', '0', '2018-04-22 17:10:43');
INSERT INTO `sys_resource` VALUES ('987985589477351426', '资源添加', '987982017863917570', '1', '/system/resource/add/**', 'system:resource:add', '#19BE6B', '', '0', '2018-04-22 17:24:55');
INSERT INTO `sys_resource` VALUES ('987985700785790978', '资源更新', '987982017863917570', '1', '/system/resource/update/**', 'system:resource:update', '#19BE6B', '', '0', '2018-04-22 17:25:21');
INSERT INTO `sys_resource` VALUES ('987985782880903170', '资源删除', '987982017863917570', '1', '/system/resource/remove/**', 'system:resource:remove', '#EA1A1A', '', '0', '2018-04-22 17:25:41');
INSERT INTO `sys_resource` VALUES ('987985944781037570', '角色删除', '987981659066376194', '1', '/system/role/remove/**', 'system:role:remove', '#ED3F14', '', '0', '2018-04-22 17:26:19');
INSERT INTO `sys_resource` VALUES ('987986018126831617', '用户添加', '987981486382686210', '1', '/system/user/add/**', 'system:user:add', '#19BE6B', '', '0', '2018-04-22 17:26:37');
INSERT INTO `sys_resource` VALUES ('987986318946508801', '用户更新', '987981486382686210', '1', '/system/user/update/**', 'system:user:update', '#19BE6B', '', '0', '2018-04-22 17:27:48');
INSERT INTO `sys_resource` VALUES ('987986542024761345', '用户删除', '987981486382686210', '1', '/system/user/remove/**', 'system:user:remove', '#ED3F14', '', '0', '2018-04-22 17:28:42');
INSERT INTO `sys_resource` VALUES ('988254531404496898', '资源列表', '987982017863917570', '1', '/system/resource/list/**', 'system:resource:list', '#19BE6B', '', '0', '2018-04-23 11:13:35');
INSERT INTO `sys_resource` VALUES ('988602498871316482', '角色列表', '987981659066376194', '1', '/system/role/list/**', 'system:role:list', '#19BE6B', '', '0', '2018-04-24 10:16:17');
INSERT INTO `sys_resource` VALUES ('989417091599802370', '用户列表', '987981486382686210', '1', '/system/user/list/**', 'system:user:list', '#19BE6B', '', '0', '2018-04-26 16:13:12');
INSERT INTO `sys_resource` VALUES ('989417919966453762', '密码重置', '987981486382686210', '1', '/system/user/resetPassword/**', 'system:user:resetPassword', '#19BE6B', '', '0', '2018-04-26 16:16:29');
INSERT INTO `sys_resource` VALUES ('989418114355666946', '锁定用户', '987981486382686210', '1', '/system/user/lock/**', 'system:user:lock', '#FF9900', '', '0', '2018-04-26 16:17:16');
INSERT INTO `sys_resource` VALUES ('989418202087923713', '解锁用户', '987981486382686210', '1', '/system/user/unlock/**', 'system:user:unlock', '#FF9900', '', '0', '2018-04-26 16:17:37');

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
INSERT INTO `sys_role` VALUES ('989416986389880834', '演示用户组');
INSERT INTO `sys_role` VALUES ('989419745311301633', 'test');

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
INSERT INTO `sys_role_resource` VALUES ('989764098990903297', '988623554205990914', '987981277195968513');
INSERT INTO `sys_role_resource` VALUES ('989764099125121026', '988623554205990914', '987981486382686210');
INSERT INTO `sys_role_resource` VALUES ('989764099305476097', '988623554205990914', '987986018126831617');
INSERT INTO `sys_role_resource` VALUES ('989764099410333698', '988623554205990914', '987986318946508801');
INSERT INTO `sys_role_resource` VALUES ('989764099498414081', '988623554205990914', '987986542024761345');
INSERT INTO `sys_role_resource` VALUES ('989764099624243202', '988623554205990914', '989417091599802370');
INSERT INTO `sys_role_resource` VALUES ('989764099729100802', '988623554205990914', '989417919966453762');
INSERT INTO `sys_role_resource` VALUES ('989764099829764098', '988623554205990914', '989418114355666946');
INSERT INTO `sys_role_resource` VALUES ('989764099938816001', '988623554205990914', '989418202087923713');
INSERT INTO `sys_role_resource` VALUES ('989764100043673601', '988623554205990914', '987981659066376194');
INSERT INTO `sys_role_resource` VALUES ('989764100144336898', '988623554205990914', '987974185122832386');
INSERT INTO `sys_role_resource` VALUES ('989764100249194497', '988623554205990914', '987980763175624706');
INSERT INTO `sys_role_resource` VALUES ('989764100349857793', '988623554205990914', '987985944781037570');
INSERT INTO `sys_role_resource` VALUES ('989764100458909698', '988623554205990914', '988602498871316482');
INSERT INTO `sys_role_resource` VALUES ('989764100563767297', '988623554205990914', '987982017863917570');
INSERT INTO `sys_role_resource` VALUES ('989764100672819201', '988623554205990914', '987985589477351426');
INSERT INTO `sys_role_resource` VALUES ('989764100773482497', '988623554205990914', '987985700785790978');
INSERT INTO `sys_role_resource` VALUES ('989764100882534402', '988623554205990914', '987985782880903170');
INSERT INTO `sys_role_resource` VALUES ('989764100979003393', '988623554205990914', '988254531404496898');
INSERT INTO `sys_role_resource` VALUES ('989776552756076545', '989416986389880834', '989417091599802370');
INSERT INTO `sys_role_resource` VALUES ('989776552923848706', '989416986389880834', '987974185122832386');
INSERT INTO `sys_role_resource` VALUES ('989776553028706306', '989416986389880834', '988602498871316482');
INSERT INTO `sys_role_resource` VALUES ('989776553133563906', '989416986389880834', '988254531404496898');
INSERT INTO `sys_role_resource` VALUES ('989419745470685186', '989419745311301633', '987977834901315586');

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
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e9beba9d3954ddd2b2efc950c4449790', '20', '1', '2018-04-18 15:01:04');
INSERT INTO `sys_user` VALUES ('2', 'user', '0b1010c7ce259a10d113369c2ac59d11', '20', '1', '2018-04-18 15:01:08');
INSERT INTO `sys_user` VALUES ('989416849999503361', 'test', '2da88c46ccd04fda15128b1adb82be9e', '0', '1', '2018-04-26 16:12:14');
INSERT INTO `sys_user` VALUES ('989423607472562177', '111222', '51cd3938e35c9dc38a642d1084281f11', '0', '1', '2018-04-26 16:39:05');

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
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '988623554205990914');
INSERT INTO `sys_user_role` VALUES ('989753486051151873', '2', '989416986389880834');
INSERT INTO `sys_user_role` VALUES ('989419854702944258', '989416849999503361', '989419745311301633');
INSERT INTO `sys_user_role` VALUES ('989419854874910722', '989416849999503361', '989416986389880834');
INSERT INTO `sys_user_role` VALUES ('989423607791329281', '989423607472562177', '989419745311301633');
