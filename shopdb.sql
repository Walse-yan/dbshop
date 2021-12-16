/*
 Navicat Premium Data Transfer

 Source Server         : 远程数据库
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 120.76.250.115:3306
 Source Schema         : shopdb

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 16/12/2021 17:46:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `m_name` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `m_account` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `m_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`m_name`, `m_account`, `m_password`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES ('颜经全', '201930344244', '201930344244');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `order_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_nums` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`, `p_id`) USING BTREE,
  INDEX `p_id`(`p_id`) USING BTREE,
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`p_id`) REFERENCES `products` (`p_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('000000000000', '000000000009', 50);
INSERT INTO `orderitem` VALUES ('000000000000', '000000000010', 36);
INSERT INTO `orderitem` VALUES ('000000000001', '000000000009', 50);
INSERT INTO `orderitem` VALUES ('000000000001', '000000000010', 36);
INSERT INTO `orderitem` VALUES ('000000000002', '000000000009', 50);
INSERT INTO `orderitem` VALUES ('000000000002', '000000000010', 36);
INSERT INTO `orderitem` VALUES ('000000000003', '000000000009', 50);
INSERT INTO `orderitem` VALUES ('000000000003', '000000000010', 36);
INSERT INTO `orderitem` VALUES ('000000000004', '000000000009', 50);
INSERT INTO `orderitem` VALUES ('000000000004', '000000000010', 36);
INSERT INTO `orderitem` VALUES ('000000000005', '000000000009', 50);
INSERT INTO `orderitem` VALUES ('000000000005', '000000000010', 36);
INSERT INTO `orderitem` VALUES ('000000000006', '000000000009', 50);
INSERT INTO `orderitem` VALUES ('000000000006', '000000000010', 36);
INSERT INTO `orderitem` VALUES ('000000000007', '000000000009', 50);
INSERT INTO `orderitem` VALUES ('000000000007', '000000000010', 36);
INSERT INTO `orderitem` VALUES ('000000000008', '000000000003', 6);
INSERT INTO `orderitem` VALUES ('000000000008', '000000000005', 3);
INSERT INTO `orderitem` VALUES ('000000000008', '000000000009', 49);
INSERT INTO `orderitem` VALUES ('000000000008', '000000000010', 35);
INSERT INTO `orderitem` VALUES ('000000000009', '000000000003', 6);
INSERT INTO `orderitem` VALUES ('000000000009', '000000000005', 3);
INSERT INTO `orderitem` VALUES ('000000000009', '000000000009', 49);
INSERT INTO `orderitem` VALUES ('000000000009', '000000000010', 36);
INSERT INTO `orderitem` VALUES ('000000000010', '000000000003', 6);
INSERT INTO `orderitem` VALUES ('000000000010', '000000000005', 3);
INSERT INTO `orderitem` VALUES ('000000000010', '000000000009', 49);
INSERT INTO `orderitem` VALUES ('000000000010', '000000000010', 36);
INSERT INTO `orderitem` VALUES ('000000000011', '000000000001', 1);
INSERT INTO `orderitem` VALUES ('000000000011', '000000000002', 1);
INSERT INTO `orderitem` VALUES ('000000000012', '000000000000', 1);
INSERT INTO `orderitem` VALUES ('000000000012', '000000000004', 1);
INSERT INTO `orderitem` VALUES ('000000000012', '000000000005', 1);
INSERT INTO `orderitem` VALUES ('000000000015', '000000000000', 1);
INSERT INTO `orderitem` VALUES ('000000000016', '000000000001', 1);
INSERT INTO `orderitem` VALUES ('000000000017', '000000000001', 1);
INSERT INTO `orderitem` VALUES ('000000000017', '000000000004', 1);
INSERT INTO `orderitem` VALUES ('000000000018', '000000000000', 1);
INSERT INTO `orderitem` VALUES ('000000000018', '000000000001', 1);
INSERT INTO `orderitem` VALUES ('000000000019', '000000000002', 1);
INSERT INTO `orderitem` VALUES ('000000000019', '000000000003', 1);
INSERT INTO `orderitem` VALUES ('000000000020', '000000000000', 1);
INSERT INTO `orderitem` VALUES ('000000000020', '000000000001', 1);
INSERT INTO `orderitem` VALUES ('000000000021', '000000000003', 1);
INSERT INTO `orderitem` VALUES ('000000000022', '000000000000', 1);
INSERT INTO `orderitem` VALUES ('000000000022', '000000000002', 1);
INSERT INTO `orderitem` VALUES ('000000000023', '000000000002', 1);
INSERT INTO `orderitem` VALUES ('000000000023', '000000000003', 1);
INSERT INTO `orderitem` VALUES ('000000000026', '000000000000', 10);
INSERT INTO `orderitem` VALUES ('000000000026', '000000000002', 1);
INSERT INTO `orderitem` VALUES ('000000000026', '000000000009', 1);
INSERT INTO `orderitem` VALUES ('000000000027', '000000000002', 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `userName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sum_price` int(11) NULL DEFAULT NULL,
  `order_status` tinyint(1) NULL DEFAULT NULL,
  `order_time` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `userName`(`userName`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userName`) REFERENCES `users` (`userName`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('颜经全', '000000000000', 10578, 1, '20211203094610');
INSERT INTO `orders` VALUES ('颜经全', '000000000001', 10578, 1, '20211203094921');
INSERT INTO `orders` VALUES ('颜经全', '000000000002', 10578, 1, '20211203095005');
INSERT INTO `orders` VALUES ('颜经全', '000000000003', 10578, 1, '20211203095108');
INSERT INTO `orders` VALUES ('颜经全', '000000000004', 10578, 1, '20211203095208');
INSERT INTO `orders` VALUES ('颜经全', '000000000005', 10578, 1, '20211203095308');
INSERT INTO `orders` VALUES ('颜经全', '000000000006', 10578, 1, '20211203095311');
INSERT INTO `orders` VALUES ('颜经全', '000000000007', 10578, 1, '20211203095314');
INSERT INTO `orders` VALUES ('颜经全', '000000000008', 11439, 1, '20211204115209');
INSERT INTO `orders` VALUES ('颜经全', '000000000009', 11562, 1, '20211204115526');
INSERT INTO `orders` VALUES ('颜经全', '000000000010', 11562, 1, '20211204115818');
INSERT INTO `orders` VALUES ('颜经全', '000000000011', 246, 1, '20211204120129');
INSERT INTO `orders` VALUES ('颜经全', '000000000012', 369, 1, '20211204120439');
INSERT INTO `orders` VALUES ('颜经全', '000000000013', 0, 1, '20211204120507');
INSERT INTO `orders` VALUES ('颜经全', '000000000014', 0, 1, '20211204120516');
INSERT INTO `orders` VALUES ('颜经全', '000000000015', 123, 1, '20211204120551');
INSERT INTO `orders` VALUES ('颜经全', '000000000016', 123, 1, '20211204120611');
INSERT INTO `orders` VALUES ('颜经全', '000000000017', 246, 1, '20211204120656');
INSERT INTO `orders` VALUES ('颜经全', '000000000018', 246, 1, '20211204124935');
INSERT INTO `orders` VALUES ('颜经全', '000000000019', 246, 1, '20211204125146');
INSERT INTO `orders` VALUES ('颜经全', '000000000020', 246, 1, '20211204125304');
INSERT INTO `orders` VALUES ('颜经全', '000000000021', 123, 1, '20211204125358');
INSERT INTO `orders` VALUES ('颜经全', '000000000022', 246, 1, '20211204125555');
INSERT INTO `orders` VALUES ('颜经全', '000000000023', 246, 1, '20211209105037');
INSERT INTO `orders` VALUES ('颜经全', '000000000024', 0, 1, '20211209125342');
INSERT INTO `orders` VALUES ('颜经全', '000000000025', 0, 1, '20211213024649');
INSERT INTO `orders` VALUES ('dffs', '000000000026', 1476, 1, '20211214022848');
INSERT INTO `orders` VALUES ('walse', '000000000027', 123, 1, '20211216051710');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `p_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_photo` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_price` int(11) NOT NULL,
  `p_nums` int(11) NOT NULL,
  `p_category` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_describe` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_time` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`p_id`) USING BTREE,
  UNIQUE INDEX `p_id`(`p_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('000000000000', '数学书', 'C:/123.jpg', 123, 10, '教科书', '这是一本学习语文的课本', '20211127114906');
INSERT INTO `products` VALUES ('000000000001', '语文书', 'C:/123.jpg', 123, 10, '教科书', '这是一本学习语文的课本', '20211127041627');
INSERT INTO `products` VALUES ('000000000002', '语文书', 'C:/123.jpg', 123, 10, '教科书', '这是一本学习语文的课本', '20211127041849');
INSERT INTO `products` VALUES ('000000000003', '语文书', 'C:/123.jpg', 123, 10, '教科书', '这是一本学习语文的课本', '20211127042453');
INSERT INTO `products` VALUES ('000000000004', '语文书', 'C:/123.jpg', 123, 10, '教科书', '这是一本学习语文的课本', '20211127043523');
INSERT INTO `products` VALUES ('000000000005', '语文书', 'C:/123.jpg', 123, 10, '教科书', '这是一本学习语文的课本', '20211127043528');
INSERT INTO `products` VALUES ('000000000006', '语文书', 'C:/123.jpg', 123, 10, '教科书', '这是一本学习语文的课本', '20211127043530');
INSERT INTO `products` VALUES ('000000000007', '语文书', 'C:/123.jpg', 123, 10, '教科书', '这是一本学习语文的课本', '20211127043530');
INSERT INTO `products` VALUES ('000000000008', '语文书', 'C:/123.jpg', 123, 10, '教科书', '这是一本学习语文的课本', '20211127043531');
INSERT INTO `products` VALUES ('000000000009', '语文书', 'C:/123.jpg', 123, 10, '教科书', '这是一本学习语文的课本', '20211127044429');
INSERT INTO `products` VALUES ('000000000010', '语文书', 'C:/123.jpg', 123, 10, '教科书', '000000000010', '20211127044711');
INSERT INTO `products` VALUES ('000000000011', '英语书', 'C:/123.jpg', 123, 10, '教科书', '这是一本学习英语的课本', '20211202175800');
INSERT INTO `products` VALUES ('000000000012', '1', '23', 1, 1, '1', '1', '20211211111656');
INSERT INTO `products` VALUES ('000000000013', '生物', '/www/server/tomcat/img/max.png', 12, 12, '12', '12', '20211213020424');

-- ----------------------------
-- Table structure for shopcart
-- ----------------------------
DROP TABLE IF EXISTS `shopcart`;
CREATE TABLE `shopcart`  (
  `userName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_nums` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`userName`, `p_id`) USING BTREE,
  INDEX `p_id`(`p_id`) USING BTREE,
  CONSTRAINT `shopcart_ibfk_1` FOREIGN KEY (`userName`) REFERENCES `users` (`userName`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `shopcart_ibfk_2` FOREIGN KEY (`p_id`) REFERENCES `products` (`p_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopcart
-- ----------------------------
INSERT INTO `shopcart` VALUES ('walseYan', '000000000000', 10);
INSERT INTO `shopcart` VALUES ('walseYan', '000000000001', 15);
INSERT INTO `shopcart` VALUES ('颜经全', '000000000002', 1);
INSERT INTO `shopcart` VALUES ('颜经全', '000000000004', 2);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `userName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` smallint(6) NULL DEFAULT NULL,
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `joinTime` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userName`, `phone`, `password`) USING BTREE,
  INDEX `userName`(`userName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('dffs', '19860204396', '123456', NULL, NULL, '78740186@qq.com', '20211214022804');
INSERT INTO `users` VALUES ('walse', '13591673911', '123456', NULL, NULL, '1159167391@qq.com', '20211216050818');
INSERT INTO `users` VALUES ('walseYan', '13415173601', '0716YAN', NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES ('YJQ', '123456', '123456', NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES ('王彦龙', '18680619604', '123456', NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES ('颜经全', '15975526586', '123456', NULL, NULL, '1159167391@qq.com', '20211130052858');

SET FOREIGN_KEY_CHECKS = 1;
