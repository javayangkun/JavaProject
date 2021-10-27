/*
 Navicat Premium Data Transfer

 Source Server         : oracle_mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 192.168.205.129:3306
 Source Schema         : rbac

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 28/09/2021 08:47:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门编号',
  `describ` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门描述',
  `pid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级id',
  `p_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级部门名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dept_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_department
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限菜单名称',
  `icon` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `href` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问路径',
  `target` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '打开方式 默认为空或者_self',
  `pid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级菜单id',
  `p_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级菜单名称',
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '排序号',
  `type` int(4) NULL DEFAULT NULL COMMENT '资源类型 0按钮 1菜单 2 目录',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `per_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '常规管理', 'fa fa-address-book', '', '_self', '0', 'root', '1', 2, '2021-09-22 15:37:26', '2021-09-22 15:37:29');
INSERT INTO `sys_permission` VALUES ('11', '主页模板', 'fa fa-home', NULL, '_self', '1', '常规管理', NULL, 1, '2021-09-22 16:36:33', '2021-09-22 16:36:37');
INSERT INTO `sys_permission` VALUES ('111', '主页一', 'fa fa-tachometer', 'page/welcome-1.html', '_self', '1', '常规管理', NULL, 1, '2021-09-22 16:37:43', '2021-09-22 16:37:46');
INSERT INTO `sys_permission` VALUES ('112', '主页二', 'fa fa-tachometer', 'page/welcome-2.html', '_self', '1', '常规管理', NULL, 1, '2021-09-22 16:38:29', '2021-09-22 16:38:31');
INSERT INTO `sys_permission` VALUES ('2', '组件管理', 'fa fa-lemon-o', NULL, '_self', '0', 'root', '2', 2, '2021-09-22 15:38:53', '2021-09-22 15:38:56');
INSERT INTO `sys_permission` VALUES ('3', '其它管理', 'fa fa-slideshare', NULL, '_self', '0', 'root', '3', 2, '2021-09-22 15:45:00', '2021-09-22 15:45:03');
INSERT INTO `sys_permission` VALUES ('4', '系统管理', 'fa fa-cog', NULL, '_self', '0', 'root', '4', 2, '2021-09-22 15:45:10', '2021-09-22 15:45:12');
INSERT INTO `sys_permission` VALUES ('41', '机构管理', 'fa fa-building', NULL, '_self', '4', '系统管理', '1', 1, '2021-09-22 16:42:15', '2021-09-22 16:42:18');
INSERT INTO `sys_permission` VALUES ('42', '用户管理', 'fa fa-user', 'user/main', '_self', '4', '系统管理', '2', 1, '2021-09-22 16:42:55', '2021-09-22 16:42:57');
INSERT INTO `sys_permission` VALUES ('43', '角色管理', 'fa fa-group', 'role/main', '_self', '4', '系统管理', '3', 1, '2021-09-22 16:44:38', '2021-09-22 16:44:40');
INSERT INTO `sys_permission` VALUES ('44', '权限管理', 'fa fa-eye-slash', NULL, '_self', '4', '系统管理', '4', 1, '2021-09-22 16:46:13', '2021-09-22 16:46:16');
INSERT INTO `sys_permission` VALUES ('45', '菜单管理', 'fa fa-folder-o', 'permission/main', NULL, '4', '系统管理', '5', 1, '2021-09-22 16:47:45', '2021-09-22 16:47:48');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `alias` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色别名',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', 'admin', '系统管理员', '2021-09-22 17:17:17', '2021-09-22 17:17:19');
INSERT INTO `sys_role` VALUES ('2', '录入监督岗', 'lrjdg', '录入监督岗', '2021-09-22 17:18:46', '2021-09-22 17:18:49');
INSERT INTO `sys_role` VALUES ('3', '凭证扫描', 'pzsm1', '凭证扫描', '2021-09-22 17:19:17', '2021-09-22 17:19:20');
INSERT INTO `sys_role` VALUES ('7ffb92bb4e1acf9678f947beb6801839', '重点监督', 'zdjd', '对重点业务票据进行人工监督', '2021-09-27 14:55:04', NULL);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限菜单id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('3', '4');
INSERT INTO `sys_role_permission` VALUES ('3', '42');
INSERT INTO `sys_role_permission` VALUES ('3', '43');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '41');
INSERT INTO `sys_role_permission` VALUES ('1', '42');
INSERT INTO `sys_role_permission` VALUES ('1', '43');
INSERT INTO `sys_role_permission` VALUES ('1', '44');
INSERT INTO `sys_role_permission` VALUES ('1', '45');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `role_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色用户关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('3', '2');
INSERT INTO `sys_role_user` VALUES ('1', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户登录名称',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实名称',
  `phone_number` bigint(20) NULL DEFAULT NULL COMMENT '手机号码',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属部门名称',
  `dept_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属部门名称',
  `is_disabled` tinyint(4) NULL DEFAULT NULL COMMENT '是否启用 1启用 0 禁用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '333444', 'root', NULL, NULL, NULL, 1, '2021-09-22 14:45:31', '2021-09-22 14:45:34');
INSERT INTO `sys_user` VALUES ('2', 'yangkun', '333444', '杨坤', NULL, NULL, NULL, 1, '2021-09-22 17:20:07', '2021-09-22 17:20:10');
INSERT INTO `sys_user` VALUES ('dbf2cfa0a19681a23ab98756e11ede6e', 'dsq', '111111', '邓世强', 18329434805, NULL, NULL, 1, '2021-09-27 23:10:16', NULL);

SET FOREIGN_KEY_CHECKS = 1;
