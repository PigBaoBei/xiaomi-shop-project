/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : myshop

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 28/07/2022 16:01:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uid` int NOT NULL,
  `state` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_address_id`(`uid` ASC) USING BTREE,
  CONSTRAINT `fk_address_id` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO `tb_address` VALUES (22, '成都', '猪宝贝', '13123179333', 36, 1);
INSERT INTO `tb_address` VALUES (23, '四川', '东方月初', '13113179799', 37, 1);
INSERT INTO `tb_address` VALUES (25, '四川', '涂山红红', '13113179798', 37, 0);

-- ----------------------------
-- Table structure for tb_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart`  (
  `cid` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL,
  `pid` int NOT NULL DEFAULT 0,
  `num` int NULL DEFAULT NULL,
  `money` int NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE,
  INDEX `fk_cart_pid`(`pid` ASC) USING BTREE,
  INDEX `fk_cart_id`(`uid` ASC) USING BTREE,
  CONSTRAINT `fk_cart_id` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_cart_pid` FOREIGN KEY (`pid`) REFERENCES `tb_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_cart
-- ----------------------------
INSERT INTO `tb_cart` VALUES (36, 36, 7, 1, 11);
INSERT INTO `tb_cart` VALUES (37, 36, 3, 1, 78);

-- ----------------------------
-- Table structure for tb_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pubdate` date NULL DEFAULT NULL,
  `picture` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` int NOT NULL,
  `star` tinyint NOT NULL DEFAULT 0,
  `intro` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `tid` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_typeid`(`tid` ASC) USING BTREE,
  CONSTRAINT `forkey_tid` FOREIGN KEY (`tid`) REFERENCES `tb_goods_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
INSERT INTO `tb_goods` VALUES (1, 'RedmiBook 16 (第十代英特尔酷睿i5-1035G1 16G 512G MX350 2G ', '2017-09-12', '//img14.360buyimg.com/n7/jfs/t1/167146/18/2355/59720/60011621E78c6d5ad/65260d8959d9c48c.jpg', 1, 5, '第一', 4);
INSERT INTO `tb_goods` VALUES (2, '小米6手机', '2017-09-14', '//img14.360buyimg.com/n7/jfs/t1/139560/35/15779/97911/5fbdfbdfE244aee62/b0fe4cfb07693742.jpg', 23, 5, '第二本', 2);
INSERT INTO `tb_goods` VALUES (3, '红米手机', '2017-09-07', '//img10.360buyimg.com/n7/jfs/t1/105181/21/5564/90517/5dee4ac2E1cffccc4/5a6dbbae3533292e.jpg', 78, 4, '第一本html\r\n实例二\r\n\r\n     这次要介绍的就没那么简单了，这个悬停弹出框的效果比第一个实例要复杂很多。弹出框弹出的效果是一样的，不一样的是弹出框的内容，这次的弹出框中不仅要有提示，还要有相应的信息，链接等。小编所做的效果是在弹出框中添加了图片，然后有相应的链接，在点击之后可以跳转到不同的页面。', 2);
INSERT INTO `tb_goods` VALUES (4, '安卓小米手机', '2017-09-02', '//img11.360buyimg.com/n7/jfs/t1/54085/1/13732/194509/5da6d50fE32d0191a/68d0ac29ce4a326d.jpg', 89, 3, '安卓第一本书是从这里开始的', 2);
INSERT INTO `tb_goods` VALUES (5, '小米6', '2018-01-24', '//img13.360buyimg.com/n2/jfs/t1/150903/24/11197/128314/5fdda167Eded4e586/46d60497d936756c.jpg', 2499, 3, '变焦双摄，4 轴防抖 / 骁龙835 旗舰处理器，6GB 大内存，最大可选128GB 闪存 / 5.15\" 护眼屏 / 四曲面玻璃/陶瓷机身', 2);
INSERT INTO `tb_goods` VALUES (6, '小米7mini', '2018-01-10', '//img11.360buyimg.com/n7/jfs/t1/93562/20/15470/87993/5e71cdbdE7606d124/9e5835e6e0f980dc.jpg', 1, 2, '', 2);
INSERT INTO `tb_goods` VALUES (7, 'admin', '2018-01-20', '//img14.360buyimg.com/n7/jfs/t1/132241/22/15707/88342/5fac0c6dE03f9ce6d/b7413ba9294c0d34.jpg', 11, 4, '', 1);
INSERT INTO `tb_goods` VALUES (8, '小米MIX2', '2018-01-24', '//img12.360buyimg.com/n7/jfs/t1/113362/5/3042/58351/5ea55bb5E031e9d04/0a8f07fd89cc14cc.jpg', 1588, 3, '5.99\" 超大屏幕\r\n机身却比 5.5\" 传统手机还小\r\n\r\n5.99\"，你很难找到比它屏幕大，但是尺寸却比它小的手机。\r\n这源于不断进化的全面屏科技，带来更窄的底边、更小的相机、隐藏的距离传感器……\r\n最终将 5.99\" 大屏装进了比 5.5\" 传统手机还小的机身内，握感绝佳。', 2);
INSERT INTO `tb_goods` VALUES (9, 'RedmiBook 14 二代 超轻薄全金属(第十代英特尔酷睿i5-1035G1 16G 512G MX350', '2021-02-16', '//img14.360buyimg.com/n7/jfs/t1/167146/18/2355/59720/60011621E78c6d5ad/65260d8959d9c48c.jpg', 4999, 2, NULL, 4);
INSERT INTO `tb_goods` VALUES (10, '小米Air 12.5英寸 网课 学习全金属超轻薄(英特尔酷睿M3-8100Y 4G 256G 全高清', '2021-02-16', '//img11.360buyimg.com/n7/jfs/t1/138549/39/19686/106202/5fe1936cE44b76d06/439afcb572b27515.jpg', 5999, 5, NULL, 4);
INSERT INTO `tb_goods` VALUES (11, '【学生钜惠】小米(MI)RedmiBook 14英寸红米全金属轻薄商务办公英特尔十代I7笔', '2021-02-16', '//img12.360buyimg.com/n7/jfs/t1/155768/14/9481/145340/60222a05Eb1890079/d90ade7491cacdea.jpg', 4399, 5, NULL, 4);
INSERT INTO `tb_goods` VALUES (12, 'RedmiBook Air 13.3英寸 2.5k超轻薄(第十代英特尔酷睿i5 16G 512G 100%sRGB 紫', '2021-02-16', '//img10.360buyimg.com/n7/jfs/t1/165942/19/2422/53475/60011418E5df91d7f/9e6fdaed83b2b0a4.jpg', 5199, 4, NULL, 4);
INSERT INTO `tb_goods` VALUES (13, 'RedmiBook 13 全面屏超轻薄(第十代英特尔酷睿i5-10210U 8G 512G MX250 2G)银 ', '2021-02-16', '//img11.360buyimg.com/n7/jfs/t1/145411/7/18559/115832/5fd870dfEdb21ca30/ea096824da2374e3.jpg', 4799, 3, NULL, 4);
INSERT INTO `tb_goods` VALUES (14, '小米Pro 15 2020款 网课学习 窄边框轻薄(第十代英特尔酷睿i7-10510U 16G 1T ', '2021-02-16', '//img13.360buyimg.com/n7/jfs/t1/165399/24/3705/94187/600a3fd5E292c9b69/05cab24543da8af0.jpg', 6999, 3, NULL, 4);
INSERT INTO `tb_goods` VALUES (15, '小米Air 12.5英寸 网课 学习全金属超轻薄(英特尔酷睿M3-8100Y 4G 256G 全高清', '2021-02-16', '//img11.360buyimg.com/n7/jfs/t1/138549/39/19686/106202/5fe1936cE44b76d06/439afcb572b27515.jpg', 3999, 5, NULL, 4);
INSERT INTO `tb_goods` VALUES (18, '小米盒子Rpo', '2021-03-26', 'http://localhost:8080/files/3.jpg', 880, 100, '智能盒子, 小米绝版', 4);
INSERT INTO `tb_goods` VALUES (19, '千锋手机', '2022-03-11', 'http://localhost:8080/files/5a5f2a2bN0b8e0994.jpg', 9999, 10, '非常好', 1);
INSERT INTO `tb_goods` VALUES (20, 'Redmi X65 2022款 65英寸', '2022-07-24', 'https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1634637921.97192476.jpg', 3099, 0, '双120Hz高刷屏 | HDMI 2.1接口 | 3GB+32GB', 3);
INSERT INTO `tb_goods` VALUES (21, '小米电视 大师 65英寸OLED', '2022-07-24', 'https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1593671513.90269727.jpg', 8999, 0, 'OLED自发光屏 | 百万级对比度 | 双120Hz高刷', 3);
INSERT INTO `tb_goods` VALUES (22, '\r\n\r\n\r\n\r\n\r\n\r\n\r\n小米电视4A 70英寸', '2022-07-24', 'https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1568199413.36224361.jpg', 3199, 0, '4K超高清 | 人工智能语音系统 | 蓝牙语音遥控器', 3);
INSERT INTO `tb_goods` VALUES (23, '小米全面屏电视Pro 32英寸 E32S', '2022-07-24', 'https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1590207082.40799227.jpg', 799, 0, '全高清画质 | 全面屏设计 | 1GB+8GB', 3);
INSERT INTO `tb_goods` VALUES (24, '\r\n\r\n\r\n\r\n\r\n\r\n\r\n小米全面屏电视Pro 75英寸 E75S', '2022-07-24', 'https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1585881755.76247599.jpg', 4499, 0, '金属全面屏 | 4K超高清 | 2GB+32GB', 3);
INSERT INTO `tb_goods` VALUES (25, '小米全面屏电视65英寸 E65X', '2022-07-24', 'https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1601189808.01459567.jpg', 2599, 0, '4K超高清 | 内置小爱同学 | 海量内容', 3);
INSERT INTO `tb_goods` VALUES (26, 'Xiaomi 11 青春活力版', '2022-07-24', 'https://cdn.cnbj1.fds.api.mi-img.com/product-images/mi11le-5g-nedb12b7/28712.png', 2299, 0, '5.99\" 超大屏幕\r\n机身却比 5.5\" 传统手机还小\r\n\r\n5.99\"，你很难找到比它屏幕大，但是尺寸却比它小的手机。\r\n这源于不断进化的全面屏科技，带来更窄的底边、更小的相机、隐藏的距离传感器……\r\n最终将 5.99\" 大屏装进了比 5.5\" 传统手机还小的机身内，握感绝佳。', 1);
INSERT INTO `tb_goods` VALUES (27, 'Xiaomi 12S Pro', '2022-07-24', 'https://cdn.cnbj1.fds.api.mi-img.com/product-images/mi12s-provuejwm/70.png', 4699, 0, '美妙体验，细致入微', 1);
INSERT INTO `tb_goods` VALUES (28, 'Xiaomi 12S', '2022-07-24', 'https://cdn.cnbj1.fds.api.mi-img.com/product-images/mi12sltli3b/1612.png', 3999, 0, '小米，为发烧而生', 1);
INSERT INTO `tb_goods` VALUES (29, 'Xiaomi 12 Pro 天玑版', '2022-07-24', 'https://cdn.cnbj1.fds.api.mi-img.com/product-images/mi12pro-dimensity14svnx/15190.jpg', 3999, 0, '好快，好稳，好一次强上加稳', 1);
INSERT INTO `tb_goods` VALUES (30, 'Xiaomi 12S Ultra', '2022-07-24', 'https://cdn.cnbj1.fds.api.mi-img.com/product-images/mi12s-ultrartl5tw/872.jpg?x-fds-process=image/resize,q_100,f_webp', 4999, 0, '精密，精简，更经典', 1);
INSERT INTO `tb_goods` VALUES (31, 'Redmi Note 11T Pro+', '2022-07-24', 'https://cdn.cnbj1.fds.api.mi-img.com/product-images/redminote11tpro6prgxe/10381.png', 1999, 0, '天机8100，性能小金刚', 2);
INSERT INTO `tb_goods` VALUES (32, 'Redmi Note 11SE', '2022-07-24', 'https://cdn.cnbj1.fds.api.mi-img.com/product-images/redminote11se781qga/5603.jpg', 999, 0, '5G 小金刚 高清屏长续航 ', 2);
INSERT INTO `tb_goods` VALUES (33, 'Redmi Note 9', '2022-07-24', 'https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1595400896.0831679.jpg', 599, 0, '6.53\"超大护眼屏幕｜ 5000mAh长循环大电量｜ 大音量扬声器 ｜1300万AI相机 ｜人脸解锁｜G25八核处理器', 2);
INSERT INTO `tb_goods` VALUES (34, 'Redmi K50 Pro', '2022-07-24', 'https://cdn.cnbj1.fds.api.mi-img.com/product-images/redmik50proul0wzg/12292.png', 6999, 0, '有点狠的年度高性能\r\n有点狠的 2K 直屏换代\r\n有点狠的神仙充电 CP\r\n有点狠的光学防抖相机\r\n万众期待的年度旗舰\r\n   狠超你的想象！', 2);
INSERT INTO `tb_goods` VALUES (35, 'Redmi K40S', '2022-07-24', 'https://cdn.cnbj1.fds.api.mi-img.com/product-images/redmik40sb4bd68/31.jpg', 2999, 0, '经典的骁龙870\r\n经典的三星 E4 旗舰直屏', 2);

-- ----------------------------
-- Table structure for tb_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_type`;
CREATE TABLE `tb_goods_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `level` int NULL DEFAULT NULL,
  `parent` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_goods_type
-- ----------------------------
INSERT INTO `tb_goods_type` VALUES (1, '小米手机', 1, 0);
INSERT INTO `tb_goods_type` VALUES (2, '红米手机', 1, 0);
INSERT INTO `tb_goods_type` VALUES (3, '电视', 1, 0);
INSERT INTO `tb_goods_type` VALUES (4, '笔记本', 1, 0);
INSERT INTO `tb_goods_type` VALUES (5, '平板', 1, 0);
INSERT INTO `tb_goods_type` VALUES (6, '家电', 2, 0);

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` int NULL DEFAULT NULL,
  `money` int NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `aid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_order_uid`(`uid` ASC) USING BTREE,
  INDEX `fk_order_aid`(`aid` ASC) USING BTREE,
  CONSTRAINT `fk_order_aid` FOREIGN KEY (`aid`) REFERENCES `tb_address` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_order_uid` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('20220727151059821', 37, 78, '0', '2022-07-27 15:10:59', 23);

-- ----------------------------
-- Table structure for tb_orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `tb_orderdetail`;
CREATE TABLE `tb_orderdetail`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `oid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` int NULL DEFAULT NULL,
  `num` int NULL DEFAULT NULL,
  `money` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_order_pid`(`pid` ASC) USING BTREE,
  INDEX `fk_order_id`(`oid` ASC) USING BTREE,
  CONSTRAINT `fk_order_pid` FOREIGN KEY (`pid`) REFERENCES `tb_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_orderdetail
-- ----------------------------
INSERT INTO `tb_orderdetail` VALUES (22, '20220727113731014', 1, 1, 1);
INSERT INTO `tb_orderdetail` VALUES (23, '20220727115505898', 1, 2, 2);
INSERT INTO `tb_orderdetail` VALUES (24, '20220727144746488', 7, 1, 11);
INSERT INTO `tb_orderdetail` VALUES (25, '20220727144807675', 7, 1, 11);
INSERT INTO `tb_orderdetail` VALUES (26, '20220727144807675', 3, 1, 78);
INSERT INTO `tb_orderdetail` VALUES (27, '20220727150139282', 20, 1, 3099);
INSERT INTO `tb_orderdetail` VALUES (28, '20220727150617389', 3, 1, 78);
INSERT INTO `tb_orderdetail` VALUES (29, '20220727150714016', 3, 1, 78);
INSERT INTO `tb_orderdetail` VALUES (30, '20220727150839054', 3, 1, 78);
INSERT INTO `tb_orderdetail` VALUES (31, '20220727151059821', 3, 1, 78);

-- ----------------------------
-- Table structure for tb_parent
-- ----------------------------
DROP TABLE IF EXISTS `tb_parent`;
CREATE TABLE `tb_parent`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '路由编号',
  `parentName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '路由名称',
  `pid` int NULL DEFAULT NULL COMMENT '商品类型路由编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_parent
-- ----------------------------
INSERT INTO `tb_parent` VALUES (1, '一级', 0);
INSERT INTO `tb_parent` VALUES (2, '二级', 1);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` int NULL DEFAULT NULL,
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (10, '秦琼', '202cb962ac59075b964b07152d234b70', '303109654@qq.com', '男', '1', 0, '2021032410544805317f');
INSERT INTO `tb_user` VALUES (34, '一叶知秋', '$2a$10$7H3DiHaTqCGOB8A372N3e.w3zeOs7TcMX7l5LhipH4hEENM/jO.dW', '2739175034@qq.com', '男', '1', 1, 'ce8061fc8bc2859fd6b8eb1484ae7310');
INSERT INTO `tb_user` VALUES (35, '大漠孤烟', '$2a$10$lV961fcwbsvB2QgcXE3I9uSH28CVDV/1LRXQYqmvnRvNJw1gkp6g6', '2739175034@qq.com', '男', '1', 1, '674aded46b0eb5f511a4ba45942c9337');
INSERT INTO `tb_user` VALUES (36, '索克萨尔', '$2a$10$PGK4ReARyuLve4JHYF8LZeqwnq7bRGw2b1jkt6yCnyA7QoVqitqN2', '2739175034@qq.com', '男', '0', 1, 'cb1d64de0b942a8e4dc2970676b45ff8');
INSERT INTO `tb_user` VALUES (37, '东方月初', '$2a$10$CcIgfAhd5BimYqrqFn3IRe/Bm0eQhy6u1oPxBvppIOzXgx6BG8m5W', '2739175034@qq.com', '男', '1', 1, 'da219bdcdb115318eaadb4c0e30e987d');

SET FOREIGN_KEY_CHECKS = 1;
