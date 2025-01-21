/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : estate

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 22/01/2025 00:19:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for balance_records
-- ----------------------------
DROP TABLE IF EXISTS `balance_records`;
CREATE TABLE `balance_records`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `amount` double(19, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '充值金额',
  `date` datetime NOT NULL COMMENT '充值日期',
  `method` int NOT NULL COMMENT '充值方式（0-微信；1-支付宝；2-银行卡）',
  `status` int NOT NULL COMMENT '充值状态(0-充值成功；1-充值失败)',
  `delete_flag` int NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '充值记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of balance_records
-- ----------------------------

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `building` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '栋',
  `unit` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单元',
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '门牌号',
  `user_id` bigint NULL DEFAULT NULL COMMENT '户主id',
  `amount_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '费用名称',
  `price` double(19, 2) NOT NULL COMMENT '费用价格',
  `cost_details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '费用详情',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '缴费状态（0-待缴费；1-缴费成功；2-缴费失败）',
  `delete_flag` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '账单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill
-- ----------------------------

-- ----------------------------
-- Table structure for bill_records
-- ----------------------------
DROP TABLE IF EXISTS `bill_records`;
CREATE TABLE `bill_records`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `building` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '栋',
  `unit` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单元',
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '门牌号',
  `amount_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '费用名称',
  `amount` double(19, 2) NOT NULL COMMENT '缴费金额',
  `date` datetime NOT NULL COMMENT '缴费日期',
  `status` int NOT NULL COMMENT '缴费状态（1-缴费成功；2-缴费失败）',
  `user_id` bigint NOT NULL COMMENT '缴费人id',
  `delete_flag` int NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '缴费记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill_records
-- ----------------------------

-- ----------------------------
-- Table structure for bill_repairs
-- ----------------------------
DROP TABLE IF EXISTS `bill_repairs`;
CREATE TABLE `bill_repairs`  (
  `id` bigint NOT NULL COMMENT '主键',
  `bill_id` bigint NOT NULL COMMENT '账单id',
  `repair_id` bigint NOT NULL COMMENT '维修单id',
  `delete_flag` int NOT NULL DEFAULT 0,
  `create_time` datetime NOT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `bill_id`(`bill_id` ASC, `repair_id` ASC) USING BTREE COMMENT '账单和维修申请是一对一关系'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '维修-账单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill_repairs
-- ----------------------------

-- ----------------------------
-- Table structure for complaints
-- ----------------------------
DROP TABLE IF EXISTS `complaints`;
CREATE TABLE `complaints`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `applicant_id` bigint NOT NULL COMMENT '申请人id',
  `applicant_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申请人姓名',
  `applicant_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申请人电话',
  `reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '投诉申请事由',
  `status` int NOT NULL DEFAULT 1 COMMENT '投诉状态（0-待处理；1-已处理）',
  `result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '处理结果',
  `delete_flag` int NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '投诉表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complaints
-- ----------------------------

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `building` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '栋',
  `unit` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单元',
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '门牌号',
  `area` double(10, 2) NOT NULL COMMENT '面积',
  `price` double(19, 2) NOT NULL COMMENT '房价',
  `user_id` bigint NULL DEFAULT NULL COMMENT '户主id',
  `delete_flag` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `address`(`address` ASC, `building` ASC, `unit` ASC, `number` ASC) USING BTREE COMMENT '住宅地址是唯一的'
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '房产表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES (12, '幸福满庭', '14', '1', '301', 90.00, 1000000.00, 57, 0, '2025-01-21 10:57:54', '2025-01-21 14:58:48');
INSERT INTO `house` VALUES (13, '幸福满庭', '14', '1', '302', 78.00, 800000.00, 58, 0, '2025-01-21 15:03:13', NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单编码(A开头的是下拉菜单)',
  `menu_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名字',
  `menu_level` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单级别',
  `menu_parent_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级菜单编码',
  `menu_click` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '点击触发的函数',
  `menu_right` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限 (0-管理员 1普通用户) 注：可用逗号拼接',
  `menu_component` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `menu_icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `delete_flag` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 'B01', '权限管理', '1', '', 'Admin', '0', 'admin/AdminManage', 'el-icon-s-custom', 0, '2025-01-09 09:30:44', NULL);
INSERT INTO `menu` VALUES (2, 'B02', '业主信息管理', '1', '', 'User', '0,1', 'user/UserManage', 'el-icon-user-solid', 0, '2025-01-09 09:30:44', NULL);
INSERT INTO `menu` VALUES (3, 'B03', '房屋管理', '2', 'A01', 'House', '0,1', 'user/HouseManage', '', 0, '2025-01-14 11:29:53', NULL);
INSERT INTO `menu` VALUES (4, 'B04', '车位管理', '2', 'A01', 'Park', '0,1', 'user/ParkManage', '', 0, '2025-01-14 11:37:46', NULL);
INSERT INTO `menu` VALUES (5, 'A01', '房屋及设施管理', '1', NULL, '', '0,1', '', 'el-icon-office-building', 0, '2025-01-14 15:36:32', NULL);
INSERT INTO `menu` VALUES (6, 'A02', '物业费用管理', '1', NULL, NULL, '0,1', NULL, 'el-icon-wallet', 0, '2025-01-15 14:13:18', NULL);
INSERT INTO `menu` VALUES (7, 'B05', '账单管理', '2', 'A02', 'Bill', '0,1', 'user/BillManage', '', 0, '2025-01-15 14:15:33', NULL);
INSERT INTO `menu` VALUES (8, 'B06', '缴费记录', '2', 'A02', 'BillRecords', '0,1', 'user/BillRecordsManage', NULL, 0, '2025-01-15 14:17:44', NULL);
INSERT INTO `menu` VALUES (9, 'B07', '充值记录', '2', 'A02', 'BalanceRecords', '0,1', 'user/BalanceRecordsManage', NULL, 0, '2025-01-15 14:18:26', NULL);
INSERT INTO `menu` VALUES (10, 'B08', '维修管理', '1', NULL, 'Repair', '0,1', 'user/RepairManage', 'el-icon-setting', 0, '2025-01-21 11:02:04', NULL);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '公告内容',
  `delete_flag` int NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for park
-- ----------------------------
DROP TABLE IF EXISTS `park`;
CREATE TABLE `park`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '车位位置',
  `type` int NOT NULL COMMENT '车位类型（0-标准车位；1-微型车位；2-大车位）',
  `price` double(19, 2) NOT NULL COMMENT '车位价格',
  `status` int NOT NULL COMMENT '车位状态（0-未使用；1-使用中）',
  `user_id` bigint NULL DEFAULT NULL COMMENT '业主id',
  `start_time` datetime NULL DEFAULT NULL COMMENT '生效开始日期',
  `end_time` datetime NULL DEFAULT NULL COMMENT '生效截至日期',
  `delete_flag` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '车位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of park
-- ----------------------------

-- ----------------------------
-- Table structure for repairs
-- ----------------------------
DROP TABLE IF EXISTS `repairs`;
CREATE TABLE `repairs`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `applicant_id` bigint NOT NULL COMMENT '申请人id',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申请人地址',
  `building` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '栋',
  `unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单元',
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '门牌号',
  `reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '维修申请事由',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '维修状态（0-待维修；1-维修中；2-维修完成）',
  `start_time` datetime NULL DEFAULT NULL COMMENT '维修开始日期',
  `end_time` datetime NULL DEFAULT NULL COMMENT '维修截至日期',
  `price` double(19, 2) NULL DEFAULT NULL COMMENT '维修费用',
  `delete_flag` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '维修表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repairs
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `sex` int NULL DEFAULT NULL COMMENT '性别（0-女；1-男）',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `balance` double(19, 2) NULL DEFAULT 0.00 COMMENT '余额',
  `role_id` int NOT NULL COMMENT '角色id（0-管理员；1-业主）',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态(0-正常;1-冻结)',
  `delete_flag` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name` ASC) USING BTREE COMMENT '用户名唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (55, '物业总部', '$2a$10$fbvOGolvcyleSKOohkI8MOXexUe8sYP2ZtzYUp87lWyK.MwB5Z3Qm', 41, 0, '19983247646', '19983247636@qq.com', 27.00, 0, 0, 0, '2025-01-21 08:57:06', '2025-01-21 09:18:08');
INSERT INTO `user` VALUES (57, 'test', '$2a$10$8NqLCcpE8NbGdYFCt4e6E.VJU2iD5tXFqYQGfBZpE9GYkYj2Xzq0u', 22, 1, '13335678900', '13335678900@163.com', 2.00, 1, 0, 0, '2025-01-21 09:36:35', '2025-01-21 09:36:49');
INSERT INTO `user` VALUES (58, '业主1', '$2a$10$KIMTcMvSL.sHJH9fFTsQiuPvxaC8AXVKsLo7Zk/zfskIXZvqx1Mie', 23, 1, '15188764435', '13188764435@163.net', 0.00, 1, 0, 0, '2025-01-21 09:39:33', NULL);
INSERT INTO `user` VALUES (59, '业主2', '$2a$10$KMTkCDwwc7Qns2/43dOev./zQArXPiTymn2Cukt1OPHg9IstTjSo6', 43, 1, '13798762343', '13798762343@yeah.net', 100.00, 1, 0, 0, '2025-01-21 09:39:33', NULL);
INSERT INTO `user` VALUES (60, '业主3', '$2a$10$B.zmM/WKPSEhmbok0Ik8neOXIiiP5Waoi1cdhbFc0BuB4W4pljBtO', 34, 0, '18188762767', '18188762767@yeah.net', 0.00, 1, 0, 0, '2025-01-21 09:39:33', NULL);

SET FOREIGN_KEY_CHECKS = 1;
