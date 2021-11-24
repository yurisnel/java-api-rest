/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : demo_nimitz

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2021-11-24 00:12:51
*/

CREATE DATABASE IF NOT EXISTS demo_nimitz;

USE demo_nimitz;

-- ----------------------------
-- Table structure for history
-- ----------------------------

CREATE TABLE IF NOT EXISTS `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` int(255) NOT NULL,
  `state_id` int(255) NOT NULL,
  `status` enum('ACTIVE','PENDING','INACTIVE','NULL') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=361 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for services
-- ----------------------------

CREATE TABLE IF NOT EXISTS `services` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for states
-- ----------------------------

CREATE TABLE IF NOT EXISTS `states` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;
