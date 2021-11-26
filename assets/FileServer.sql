/*
 Navicat Premium Data Transfer

 Source Server         : TEST
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 152.32.135.110:3306
 Source Schema         : FileServer

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 26/11/2021 22:01:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info`  (
  `file_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
  `file_uid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'UUID',
  `file_size` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件大小',
  `file_type` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件类型',
  `file_creat_time` datetime NULL DEFAULT NULL COMMENT '文件创建时间',
  `file_path` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件保存目录地址',
  PRIMARY KEY (`file_uid`) USING BTREE,
  UNIQUE INDEX `file_udi`(`file_uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_info
-- ----------------------------
INSERT INTO `file_info` VALUES ('CopyrightReceipt', '4b855fb9-615e-45c5-aa64-c6763e9623d4', '19499', 'pdf', '2021-11-26 21:20:31', 'C:\\Users\\10507\\Desktop\\Code\\JAVA\\FileServer\\src\\test\\resources\\receive\\files\\20211126');
INSERT INTO `file_info` VALUES ('相关概念整理', 'c5b63e1f-881e-4a89-96d2-63278fb1add2', '418908', 'pdf', '2021-11-26 21:15:00', 'C:\\Users\\10507\\Desktop\\Code\\JAVA\\FileServer\\src\\test\\resources\\receive\\files\\20211126');

SET FOREIGN_KEY_CHECKS = 1;
