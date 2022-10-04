/*
 Navicat Premium Data Transfer

 Source Server         : 39.105.131.90_3306
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 39.105.131.90:3306
 Source Schema         : springboot_vue

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 18/11/2020 16:14:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` int(11) NOT NULL COMMENT '上级菜单id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源图标',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作/访问资源的接口地址',
  `component_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件名称',
  `type` int(11) NOT NULL COMMENT '资源类型1=顶级菜单, 2=二级菜单，3=按钮',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES (1, 0, '系统管理', NULL, 'system', 'system', NULL, 1, NULL, '2020-03-09 22:37:44');
INSERT INTO `tb_menu` VALUES (2, 1, '用户管理', '/system/user/index', 'peoples', 'user', 'User', 2, 'user:list', '2020-03-09 22:38:11');
INSERT INTO `tb_menu` VALUES (3, 1, '角色管理', '/system/role/index', 'role', 'role', 'Role', 2, 'role:list', '2020-03-09 22:38:34');
INSERT INTO `tb_menu` VALUES (4, 1, '菜单管理', '/system/menu/index', 'menu', 'menu', 'Menu', 2, 'menu:list', '2020-03-09 22:38:56');
INSERT INTO `tb_menu` VALUES (5, 2, '添加用户', NULL, NULL, NULL, NULL, 3, 'user:add', '2020-03-09 22:39:34');
INSERT INTO `tb_menu` VALUES (6, 2, '删除用户', NULL, NULL, NULL, NULL, 3, 'user:del', '2020-03-09 22:39:54');
INSERT INTO `tb_menu` VALUES (7, 2, '修改用户', NULL, NULL, NULL, NULL, 3, 'user:edit', '2020-03-09 22:40:23');
INSERT INTO `tb_menu` VALUES (9, 3, '添加角色', NULL, NULL, NULL, NULL, 3, 'role:add', '2020-03-09 22:43:13');
INSERT INTO `tb_menu` VALUES (10, 3, '删除角色', NULL, NULL, NULL, NULL, 3, 'role:del', '2020-03-09 22:43:29');
INSERT INTO `tb_menu` VALUES (11, 3, '修改角色', NULL, NULL, NULL, NULL, 3, 'role:edit', '2020-03-09 22:43:43');
INSERT INTO `tb_menu` VALUES (13, 4, '添加权限', NULL, NULL, NULL, NULL, 3, 'menu:add', '2020-03-09 22:48:08');
INSERT INTO `tb_menu` VALUES (14, 4, '删除权限', NULL, NULL, NULL, NULL, 3, 'menu:del', '2020-03-09 22:47:57');
INSERT INTO `tb_menu` VALUES (15, 4, '修改权限', NULL, NULL, NULL, NULL, 3, 'menu:edit', '2020-03-09 22:48:00');
INSERT INTO `tb_menu` VALUES (18, 0, 'xx管理', NULL, 'other', 'other', NULL, 1, NULL, '2020-03-26 22:24:51');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描述',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, 'ROLE_ADMIN', '管理员', '2020-03-08 15:04:49', '2020-03-08 15:05:07');
INSERT INTO `tb_role` VALUES (2, 'ROLE_USER', '普通用户', '2020-03-08 15:05:30', '2020-03-24 21:19:34');

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE,
  INDEX `menu_id`(`menu_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `tb_role_menu_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `tb_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_role_menu_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和权限绑定表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES (1, 1);
INSERT INTO `tb_role_menu` VALUES (2, 1);
INSERT INTO `tb_role_menu` VALUES (1, 2);
INSERT INTO `tb_role_menu` VALUES (2, 2);
INSERT INTO `tb_role_menu` VALUES (1, 3);
INSERT INTO `tb_role_menu` VALUES (2, 3);
INSERT INTO `tb_role_menu` VALUES (1, 4);
INSERT INTO `tb_role_menu` VALUES (2, 4);
INSERT INTO `tb_role_menu` VALUES (1, 5);
INSERT INTO `tb_role_menu` VALUES (1, 6);
INSERT INTO `tb_role_menu` VALUES (1, 7);
INSERT INTO `tb_role_menu` VALUES (1, 9);
INSERT INTO `tb_role_menu` VALUES (1, 10);
INSERT INTO `tb_role_menu` VALUES (1, 11);
INSERT INTO `tb_role_menu` VALUES (1, 13);
INSERT INTO `tb_role_menu` VALUES (1, 14);
INSERT INTO `tb_role_menu` VALUES (1, 15);
INSERT INTO `tb_role_menu` VALUES (1, 18);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称/名称',
  `gender` tinyint(4) NOT NULL COMMENT '0=女，1=男',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '上一次登录时间',
  `enabled` tinyint(4) NOT NULL DEFAULT 1 COMMENT '用户状态 0=禁用，1=正常',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'admin', '$2a$10$v4O/2TAG7KDtC5Hnt2bDE.fh.HsZtL5jK30o00jMej4FHWTAYlunu', '91907@163.com', '王管理', 1, '2020-03-08 15:08:37', '2020-03-15 14:26:28', NULL, 1);
INSERT INTO `tb_user` VALUES (2, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'wang', '$2a$10$JFOz9jeOsfumWGHwA5EryOB7nxF53uA5OcnjHupMBXwrYU28aEelS', '91908@163.com', '王用户', 1, '2020-03-08 15:09:43', '2020-03-15 14:26:29', NULL, 1);

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `tb_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色绑定表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES (1, 1);
INSERT INTO `tb_user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
