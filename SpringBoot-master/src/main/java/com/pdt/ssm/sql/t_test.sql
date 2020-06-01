/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : mydb

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 21/10/2019 00:15:01
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_test
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test`  (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` char(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `USER_PASSWORD` char(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `USER_EMAIL` char(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE,
  INDEX `IDX_NAME`(`USER_NAME`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_test
-- ----------------------------
INSERT INTO `t_test` VALUES (1, '林炳文', '1234567@', 'ling20081005@126.com');
INSERT INTO `t_test` VALUES (2, 'evan', '123', 'fff@126.com');
INSERT INTO `t_test` VALUES (3, 'kaka', 'cadg', 'fwsfg@126.com');
INSERT INTO `t_test` VALUES (4, 'simle', 'cscs', 'fsaf@126.com');
INSERT INTO `t_test` VALUES (5, 'arthur', 'csas', 'fsaff@126.com');
INSERT INTO `t_test` VALUES (6, '小德', 'yuh78', 'fdfas@126.com');
INSERT INTO `t_test` VALUES (7, '小小', 'cvff', 'fsaf@126.com');
INSERT INTO `t_test` VALUES (8, '林林之家', 'gvv', 'lin@126.com');
INSERT INTO `t_test` VALUES (9, '林炳文Evankaka', 'dfsc', 'ling2008@126.com');
INSERT INTO `t_test` VALUES (10, 'apple', 'uih6', 'ff@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
