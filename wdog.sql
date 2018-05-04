/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : wdog

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-04-28 11:28:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(30) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `uid` varchar(30) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `ajax` int(1) DEFAULT NULL,
  `uri` varchar(250) DEFAULT NULL,
  `params` varchar(500) DEFAULT NULL,
  `http_method` varchar(20) DEFAULT NULL,
  `class_method` varchar(100) DEFAULT NULL,
  `action_name` varchar(30) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('990069578229415938', 'admin', '1', '127.0.0.1', '1', '/system/role/add', '[{\"name\":\"5\",\"resources\":[{\"children\":[],\"color\":\"#19BE6B\",\"createDate\":1524387246000,\"icon\":\"ios-home-outline\",\"id\":\"987977834901315586\",\"name\":\"首页\",\"permission\":\"\",\"sort\":-1,\"type\":0,\"url\":\"/\"}]}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.add()', '添加角色', '2018-04-28 11:25:56');
INSERT INTO `sys_log` VALUES ('990069560680448002', 'admin', '1', '127.0.0.1', '1', '/system/role/add/allResource', '[]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.getAddAllResource()', '获取添加角色的时可用角色列表', '2018-04-28 11:25:52');
INSERT INTO `sys_log` VALUES ('990069456972087297', 'admin', '1', '127.0.0.1', '1', '/system/user/list', '[{\"asc\":false,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.UserController.get()', '分页获取用户数据', '2018-04-28 11:25:27');
INSERT INTO `sys_log` VALUES ('990069486143471618', 'admin', '1', '127.0.0.1', '1', '/system/role/list', '[{\"asc\":false,\"hasResource\":true,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.get()', '分页获取所有角色列表', '2018-04-28 11:25:34');
INSERT INTO `sys_log` VALUES ('990069493496086530', 'admin', '1', '127.0.0.1', '1', '/system/role/add/allResource', '[]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.getAddAllResource()', '获取添加角色的时可用角色列表', '2018-04-28 11:25:36');
INSERT INTO `sys_log` VALUES ('990069504237699074', 'admin', '1', '127.0.0.1', '1', '/system/role/add', '[{\"name\":\"1\",\"resources\":[{\"children\":[],\"color\":\"#19BE6B\",\"createDate\":1524387246000,\"icon\":\"ios-home-outline\",\"id\":\"987977834901315586\",\"name\":\"首页\",\"permission\":\"\",\"sort\":-1,\"type\":0,\"url\":\"/\"}]}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.add()', '添加角色', '2018-04-28 11:25:39');
INSERT INTO `sys_log` VALUES ('990069504619380738', 'admin', '1', '127.0.0.1', '1', '/system/role/list', '[{\"asc\":false,\"hasResource\":true,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.get()', '分页获取所有角色列表', '2018-04-28 11:25:39');
INSERT INTO `sys_log` VALUES ('990069509744820226', 'admin', '1', '127.0.0.1', '1', '/system/role/add/allResource', '[]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.getAddAllResource()', '获取添加角色的时可用角色列表', '2018-04-28 11:25:40');
INSERT INTO `sys_log` VALUES ('990069520536764417', 'admin', '1', '127.0.0.1', '1', '/system/role/add', '[{\"name\":\"2\",\"resources\":[{\"children\":[],\"color\":\"#19BE6B\",\"createDate\":1524387246000,\"icon\":\"ios-home-outline\",\"id\":\"987977834901315586\",\"name\":\"首页\",\"permission\":\"\",\"sort\":-1,\"type\":0,\"url\":\"/\"}]}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.add()', '添加角色', '2018-04-28 11:25:42');
INSERT INTO `sys_log` VALUES ('990069520956194818', 'admin', '1', '127.0.0.1', '1', '/system/role/list', '[{\"asc\":false,\"hasResource\":true,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.get()', '分页获取所有角色列表', '2018-04-28 11:25:43');
INSERT INTO `sys_log` VALUES ('990069524949172226', 'admin', '1', '127.0.0.1', '1', '/system/role/add/allResource', '[]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.getAddAllResource()', '获取添加角色的时可用角色列表', '2018-04-28 11:25:44');
INSERT INTO `sys_log` VALUES ('990069540929470466', 'admin', '1', '127.0.0.1', '1', '/system/role/add', '[{\"name\":\"3\",\"resources\":[{\"children\":[],\"color\":\"#19BE6B\",\"createDate\":1524387246000,\"icon\":\"ios-home-outline\",\"id\":\"987977834901315586\",\"name\":\"首页\",\"permission\":\"\",\"sort\":-1,\"type\":0,\"url\":\"/\"}]}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.add()', '添加角色', '2018-04-28 11:25:47');
INSERT INTO `sys_log` VALUES ('990069541147574273', 'admin', '1', '127.0.0.1', '1', '/system/role/list', '[{\"asc\":false,\"hasResource\":true,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.get()', '分页获取所有角色列表', '2018-04-28 11:25:47');
INSERT INTO `sys_log` VALUES ('990069544213610497', 'admin', '1', '127.0.0.1', '1', '/system/role/add/allResource', '[]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.getAddAllResource()', '获取添加角色的时可用角色列表', '2018-04-28 11:25:48');
INSERT INTO `sys_log` VALUES ('990069556532281346', 'admin', '1', '127.0.0.1', '1', '/system/role/add', '[{\"name\":\"4\",\"resources\":[{\"children\":[],\"color\":\"#19BE6B\",\"createDate\":1524387246000,\"icon\":\"ios-home-outline\",\"id\":\"987977834901315586\",\"name\":\"首页\",\"permission\":\"\",\"sort\":-1,\"type\":0,\"url\":\"/\"}]}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.add()', '添加角色', '2018-04-28 11:25:51');
INSERT INTO `sys_log` VALUES ('990069556993654786', 'admin', '1', '127.0.0.1', '1', '/system/role/list', '[{\"asc\":false,\"hasResource\":true,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.get()', '分页获取所有角色列表', '2018-04-28 11:25:51');
INSERT INTO `sys_log` VALUES ('990069612048089089', 'admin', '1', '127.0.0.1', '1', '/system/role/add', '[{\"name\":\"7\",\"resources\":[{\"children\":[],\"color\":\"#19BE6B\",\"createDate\":1524387246000,\"icon\":\"ios-home-outline\",\"id\":\"987977834901315586\",\"name\":\"首页\",\"permission\":\"\",\"sort\":-1,\"type\":0,\"url\":\"/\"}]}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.add()', '添加角色', '2018-04-28 11:26:04');
INSERT INTO `sys_log` VALUES ('990069597208641537', 'admin', '1', '127.0.0.1', '1', '/system/role/add/allResource', '[]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.getAddAllResource()', '获取添加角色的时可用角色列表', '2018-04-28 11:26:01');
INSERT INTO `sys_log` VALUES ('990069593609928705', 'admin', '1', '127.0.0.1', '1', '/system/role/list', '[{\"asc\":false,\"hasResource\":true,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.get()', '分页获取所有角色列表', '2018-04-28 11:26:00');
INSERT INTO `sys_log` VALUES ('990069578397188098', 'admin', '1', '127.0.0.1', '1', '/system/role/list', '[{\"asc\":false,\"hasResource\":true,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.get()', '分页获取所有角色列表', '2018-04-28 11:25:56');
INSERT INTO `sys_log` VALUES ('990069581597442049', 'admin', '1', '127.0.0.1', '1', '/system/role/add/allResource', '[]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.getAddAllResource()', '获取添加角色的时可用角色列表', '2018-04-28 11:25:57');
INSERT INTO `sys_log` VALUES ('990069593232441345', 'admin', '1', '127.0.0.1', '1', '/system/role/add', '[{\"name\":\"6\",\"resources\":[{\"children\":[],\"color\":\"#19BE6B\",\"createDate\":1524387246000,\"icon\":\"ios-home-outline\",\"id\":\"987977834901315586\",\"name\":\"首页\",\"permission\":\"\",\"sort\":-1,\"type\":0,\"url\":\"/\"}]}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.add()', '添加角色', '2018-04-28 11:26:00');
INSERT INTO `sys_log` VALUES ('990070050101198849', 'admin', '1', '127.0.0.1', '1', '/system/role/list', '[{\"asc\":false,\"hasResource\":true,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.get()', '分页获取所有角色列表', '2018-04-28 11:27:49');
INSERT INTO `sys_log` VALUES ('990069612530434050', 'admin', '1', '127.0.0.1', '1', '/system/role/list', '[{\"asc\":false,\"hasResource\":true,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.get()', '分页获取所有角色列表', '2018-04-28 11:26:04');
INSERT INTO `sys_log` VALUES ('990069622751952898', 'admin', '1', '127.0.0.1', '1', '/system/role/add/allResource', '[]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.getAddAllResource()', '获取添加角色的时可用角色列表', '2018-04-28 11:26:07');
INSERT INTO `sys_log` VALUES ('990069641768927233', 'admin', '1', '127.0.0.1', '1', '/system/role/add', '[{\"name\":\"8\",\"resources\":[{\"children\":[],\"color\":\"#19BE6B\",\"createDate\":1524387246000,\"icon\":\"ios-home-outline\",\"id\":\"987977834901315586\",\"name\":\"首页\",\"permission\":\"\",\"sort\":-1,\"type\":0,\"url\":\"/\"}]}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.add()', '添加角色', '2018-04-28 11:26:11');
INSERT INTO `sys_log` VALUES ('990069642234494977', 'admin', '1', '127.0.0.1', '1', '/system/role/list', '[{\"asc\":false,\"hasResource\":true,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.get()', '分页获取所有角色列表', '2018-04-28 11:26:11');
INSERT INTO `sys_log` VALUES ('990069849416335362', 'admin', '1', '127.0.0.1', '1', '/system/role/list', '[{\"asc\":false,\"hasResource\":true,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.get()', '分页获取所有角色列表', '2018-04-28 11:27:01');
INSERT INTO `sys_log` VALUES ('990069870761148417', 'admin', '1', '127.0.0.1', '1', '/system/resource/list', '[]', 'POST', 'cn.licoy.wdog.core.controller.system.ResourceController.list()', '获取所有的资源列表', '2018-04-28 11:27:06');
INSERT INTO `sys_log` VALUES ('990069879242031105', 'admin', '1', '127.0.0.1', '1', '/system/user/list', '[{\"asc\":false,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.UserController.get()', '分页获取用户数据', '2018-04-28 11:27:08');
INSERT INTO `sys_log` VALUES ('990069888926679041', 'admin', '1', '127.0.0.1', '1', '/system/role/list', '[{\"asc\":false,\"hasResource\":true,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.get()', '分页获取所有角色列表', '2018-04-28 11:27:10');
INSERT INTO `sys_log` VALUES ('990069934082555905', 'admin', '1', '127.0.0.1', '1', '/system/role/list', '[{\"asc\":false,\"hasResource\":true,\"page\":1,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.get()', '分页获取所有角色列表', '2018-04-28 11:27:21');
INSERT INTO `sys_log` VALUES ('990070045101588482', 'admin', '1', '127.0.0.1', '1', '/system/role/list', '[{\"asc\":false,\"hasResource\":true,\"page\":2,\"pageSize\":10}]', 'POST', 'cn.licoy.wdog.core.controller.system.RoleController.get()', '分页获取所有角色列表', '2018-04-28 11:27:48');

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
INSERT INTO `sys_resource` VALUES ('987974185122832386', '角色添加', '987981659066376194', '1', '/system/role/add', 'system:role:add', '#19BE6B', '', '0', '2018-04-22 16:39:36');
INSERT INTO `sys_resource` VALUES ('987977834901315586', '首页', null, '0', '/', '', '#19BE6B', 'ios-home-outline', '-1', '2018-04-22 16:54:06');
INSERT INTO `sys_resource` VALUES ('987980763175624706', '角色更新', '987981659066376194', '1', '/system/role/update', 'system:role:update', '#19BE6B', '', '0', '2018-04-22 17:05:44');
INSERT INTO `sys_resource` VALUES ('987981277195968513', '系统设置', null, '0', '/system', 'system', '#19BE6B', 'ios-gear', '0', '2018-04-22 17:07:46');
INSERT INTO `sys_resource` VALUES ('987981486382686210', '用户管理', '987981277195968513', '0', '/system/user', 'system:user', '#19BE6B', 'person', '0', '2018-04-22 17:08:36');
INSERT INTO `sys_resource` VALUES ('987981659066376194', '角色管理', '987981277195968513', '0', '/system/role', 'system:role', '#19BE6B', 'compose', '0', '2018-04-22 17:09:17');
INSERT INTO `sys_resource` VALUES ('987982017863917570', '资源管理', '987981277195968513', '0', '/system/resource', 'system:resource', '#19BE6B', 'lock-combination', '0', '2018-04-22 17:10:43');
INSERT INTO `sys_resource` VALUES ('987985589477351426', '资源添加', '987982017863917570', '1', '/system/resource/add', 'system:resource:add', '#19BE6B', '', '0', '2018-04-22 17:24:55');
INSERT INTO `sys_resource` VALUES ('987985700785790978', '资源更新', '987982017863917570', '1', '/system/resource/update', 'system:resource:update', '#19BE6B', '', '0', '2018-04-22 17:25:21');
INSERT INTO `sys_resource` VALUES ('987985782880903170', '资源删除', '987982017863917570', '1', '/system/resource/remove', 'system:resource:remove', '#EA1A1A', '', '0', '2018-04-22 17:25:41');
INSERT INTO `sys_resource` VALUES ('987985944781037570', '角色删除', '987981659066376194', '1', '/system/role/remove', 'system:role:remove', '#ED3F14', '', '0', '2018-04-22 17:26:19');
INSERT INTO `sys_resource` VALUES ('987986018126831617', '用户添加', '987981486382686210', '1', '/system/user/add', 'system:user:add', '#19BE6B', '', '0', '2018-04-22 17:26:37');
INSERT INTO `sys_resource` VALUES ('987986318946508801', '用户更新', '987981486382686210', '1', '/system/user/update', 'system:user:update', '#19BE6B', '', '0', '2018-04-22 17:27:48');
INSERT INTO `sys_resource` VALUES ('987986542024761345', '用户删除', '987981486382686210', '1', '/system/user/remove', 'system:user:remove', '#ED3F14', '', '0', '2018-04-22 17:28:42');
INSERT INTO `sys_resource` VALUES ('988254531404496898', '资源列表', '987982017863917570', '1', '/system/resource/list', 'system:resource:list', '#19BE6B', '', '0', '2018-04-23 11:13:35');
INSERT INTO `sys_resource` VALUES ('988602498871316482', '角色列表', '987981659066376194', '1', '/system/role/list', 'system:role:list', '#19BE6B', '', '0', '2018-04-24 10:16:17');
INSERT INTO `sys_resource` VALUES ('989417091599802370', '用户列表', '987981486382686210', '1', '/system/user/list', 'system:user:list', '#19BE6B', '', '0', '2018-04-26 16:13:12');
INSERT INTO `sys_resource` VALUES ('989417919966453762', '密码重置', '987981486382686210', '1', '/system/user/resetPassword', 'system:user:resetPassword', '#19BE6B', '', '0', '2018-04-26 16:16:29');
INSERT INTO `sys_resource` VALUES ('989418114355666946', '锁定用户', '987981486382686210', '1', '/system/user/lock', 'system:user:lock', '#FF9900', '', '0', '2018-04-26 16:17:16');
INSERT INTO `sys_resource` VALUES ('989418202087923713', '解锁用户', '987981486382686210', '1', '/system/user/unlock', 'system:user:unlock', '#FF9900', '', '0', '2018-04-26 16:17:37');
INSERT INTO `sys_resource` VALUES ('990058578210021378', '系统日志', '987981277195968513', '0', '/system/log', 'system:log', '#19BE6B', 'leaf', '0', '2018-04-28 10:42:14');
INSERT INTO `sys_resource` VALUES ('990058692110540801', '日志查看', '990058578210021378', '1', '/system/log/list', 'system:log:list', '#19BE6B', '', '0', '2018-04-28 10:42:41');
INSERT INTO `sys_resource` VALUES ('990058791586848769', '日志删除', '990058578210021378', '0', '/system/log/remove', 'system:log:remove', '#ED3F14', '', '0', '2018-04-28 10:43:04');

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
INSERT INTO `sys_role` VALUES ('990069503935709185', '1');
INSERT INTO `sys_role` VALUES ('990069520104751106', '2');
INSERT INTO `sys_role` VALUES ('990069540665229314', '3');
INSERT INTO `sys_role` VALUES ('990069556293206018', '4');
INSERT INTO `sys_role` VALUES ('990069578007117825', '5');
INSERT INTO `sys_role` VALUES ('990069592976588801', '6');
INSERT INTO `sys_role` VALUES ('990069611821596673', '7');
INSERT INTO `sys_role` VALUES ('990069641525657602', '8');

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
INSERT INTO `sys_role_resource` VALUES ('990059718301540353', '988623554205990914', '987981277195968513');
INSERT INTO `sys_role_resource` VALUES ('990059718406397953', '988623554205990914', '987981486382686210');
INSERT INTO `sys_role_resource` VALUES ('990059718511255553', '988623554205990914', '987986018126831617');
INSERT INTO `sys_role_resource` VALUES ('990059718620307457', '988623554205990914', '987986318946508801');
INSERT INTO `sys_role_resource` VALUES ('990059718720970754', '988623554205990914', '987986542024761345');
INSERT INTO `sys_role_resource` VALUES ('990059718830022657', '988623554205990914', '989417091599802370');
INSERT INTO `sys_role_resource` VALUES ('990059718930685954', '988623554205990914', '989417919966453762');
INSERT INTO `sys_role_resource` VALUES ('990059719035543553', '988623554205990914', '989418114355666946');
INSERT INTO `sys_role_resource` VALUES ('990059719140401154', '988623554205990914', '989418202087923713');
INSERT INTO `sys_role_resource` VALUES ('990059719249453058', '988623554205990914', '987981659066376194');
INSERT INTO `sys_role_resource` VALUES ('990059719350116353', '988623554205990914', '987974185122832386');
INSERT INTO `sys_role_resource` VALUES ('990059719459168257', '988623554205990914', '987980763175624706');
INSERT INTO `sys_role_resource` VALUES ('990059719559831553', '988623554205990914', '987985944781037570');
INSERT INTO `sys_role_resource` VALUES ('990059719668883458', '988623554205990914', '988602498871316482');
INSERT INTO `sys_role_resource` VALUES ('990059719769546753', '988623554205990914', '987982017863917570');
INSERT INTO `sys_role_resource` VALUES ('990059719878598657', '988623554205990914', '987985589477351426');
INSERT INTO `sys_role_resource` VALUES ('990059719983456257', '988623554205990914', '987985700785790978');
INSERT INTO `sys_role_resource` VALUES ('990059720084119554', '988623554205990914', '987985782880903170');
INSERT INTO `sys_role_resource` VALUES ('990059720193171458', '988623554205990914', '988254531404496898');
INSERT INTO `sys_role_resource` VALUES ('990059720298029057', '988623554205990914', '990058578210021378');
INSERT INTO `sys_role_resource` VALUES ('990059720398692354', '988623554205990914', '990058692110540801');
INSERT INTO `sys_role_resource` VALUES ('990059720558075906', '988623554205990914', '990058791586848769');
INSERT INTO `sys_role_resource` VALUES ('989776552756076545', '989416986389880834', '989417091599802370');
INSERT INTO `sys_role_resource` VALUES ('989776552923848706', '989416986389880834', '987974185122832386');
INSERT INTO `sys_role_resource` VALUES ('989776553028706306', '989416986389880834', '988602498871316482');
INSERT INTO `sys_role_resource` VALUES ('989776553133563906', '989416986389880834', '988254531404496898');
INSERT INTO `sys_role_resource` VALUES ('989419745470685186', '989419745311301633', '987977834901315586');
INSERT INTO `sys_role_resource` VALUES ('990069504074121218', '990069503935709185', '987977834901315586');
INSERT INTO `sys_role_resource` VALUES ('990069520402546690', '990069520104751106', '987977834901315586');
INSERT INTO `sys_role_resource` VALUES ('990069540816224257', '990069540665229314', '987977834901315586');
INSERT INTO `sys_role_resource` VALUES ('990069556410646530', '990069556293206018', '987977834901315586');
INSERT INTO `sys_role_resource` VALUES ('990069578124558338', '990069578007117825', '987977834901315586');
INSERT INTO `sys_role_resource` VALUES ('990069593127583745', '990069592976588801', '987977834901315586');
INSERT INTO `sys_role_resource` VALUES ('990069611934842881', '990069611821596673', '987977834901315586');
INSERT INTO `sys_role_resource` VALUES ('990069641659875330', '990069641525657602', '987977834901315586');

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
INSERT INTO `sys_user` VALUES ('2', 'user', '0b1010c7ce259a10d113369c2ac59d11', '20', '1', '2018-04-18 15:01:08');
INSERT INTO `sys_user` VALUES ('989416849999503361', 'test', '2da88c46ccd04fda15128b1adb82be9e', '0', '1', '2018-04-26 16:12:14');
INSERT INTO `sys_user` VALUES ('989423607472562177', '111222', 'e30817d8112257be8c5870c2ff53aefb', '0', '1', '2018-04-26 16:39:05');

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
