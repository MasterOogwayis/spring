/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : spring

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-03-12 10:49:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message_address
-- ----------------------------
DROP TABLE IF EXISTS `message_address`;
CREATE TABLE `message_address` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) NOT NULL,
  `MESSAGE` varchar(1000) NOT NULL,
  `CREATE_TIMESTAMP` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ADDRESS` (`ADDRESS`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
