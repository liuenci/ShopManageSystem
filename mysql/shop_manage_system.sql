/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : shop_manage_system

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-14 19:24:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_employee`
-- ----------------------------
DROP TABLE IF EXISTS `tb_employee`;
CREATE TABLE `tb_employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_password` varchar(20) NOT NULL,
  `emp_name` varchar(20) NOT NULL,
  `emp_sex` tinyint(4) NOT NULL,
  `emp_position_id` int(11) NOT NULL,
  `emp_phone` varchar(11) NOT NULL,
  `emp_birthday` date DEFAULT NULL,
  `emp_salary` int(11) NOT NULL,
  `emp_status` tinyint(4) NOT NULL,
  `emp_mark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `fk_type_position` (`emp_position_id`),
  CONSTRAINT `fk_type_position` FOREIGN KEY (`emp_position_id`) REFERENCES `tb_position` (`posi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=154083015 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_employee
-- ----------------------------
INSERT INTO `tb_employee` VALUES ('154083005', '15408300322', '熊晨晨', '1', '1', '15408300311', '2017-08-01', '10000', '1', null);
INSERT INTO `tb_employee` VALUES ('154083006', '15575040693', '潘忠辉', '0', '2', '15575040693', '2017-08-26', '1000000', '1', null);
INSERT INTO `tb_employee` VALUES ('154083007', '123456', '刘恩赐', '1', '3', '13376549865', '2017-08-08', '200000', '1', null);
INSERT INTO `tb_employee` VALUES ('154083008', '111111', '天天', '0', '1', '15365498756', '2017-08-06', '2000', '1', null);
INSERT INTO `tb_employee` VALUES ('154083009', '222222', '向上', '0', '2', '13869875546', '2017-08-21', '1000', '1', null);
INSERT INTO `tb_employee` VALUES ('154083010', '333333', '南南哥', '1', '1', '13965412398', '2017-08-25', '200', '1', null);
INSERT INTO `tb_employee` VALUES ('154083011', '444444', '小楠楠', '0', '2', '13965478965', '2017-08-14', '3000', '1', null);
INSERT INTO `tb_employee` VALUES ('154083012', '555555', '小禁禁', '1', '3', '13547896325', '2017-08-13', '6000', '1', null);
INSERT INTO `tb_employee` VALUES ('154083013', '666666', '松帅', '0', '3', '13698654123', '2017-08-08', '10000', '1', null);
INSERT INTO `tb_employee` VALUES ('154083014', '777777', '小盼盼', '1', '3', '13798654321', '2017-08-15', '5000', '1', null);

-- ----------------------------
-- Table structure for `tb_good`
-- ----------------------------
DROP TABLE IF EXISTS `tb_good`;
CREATE TABLE `tb_good` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(20) NOT NULL,
  `goods_units` varchar(20) DEFAULT NULL,
  `goods_size` varchar(10) DEFAULT NULL,
  `goods_purPrice` double NOT NULL,
  `goods_sellPrice` double NOT NULL,
  `goods_number` int(11) NOT NULL,
  `goods_stoId` int(11) NOT NULL,
  `goods_keepDays` int(11) NOT NULL,
  `goods_minNumber` int(11) NOT NULL,
  `goods_mark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `fk_type_storage` (`goods_stoId`),
  CONSTRAINT `fk_type_storage` FOREIGN KEY (`goods_stoId`) REFERENCES `tb_storage` (`sto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10022 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_good
-- ----------------------------
INSERT INTO `tb_good` VALUES ('10001', '桃子', '个', '100g', '2', '3.5', '3', '1', '10', '5', '甜的不行');
INSERT INTO `tb_good` VALUES ('10002', '李子', '个', '200g', '2', '2.5', '1', '4', '3', '5', '');
INSERT INTO `tb_good` VALUES ('10003', '卫龙', '包', '200g', '1', '1.5', '2', '4', '360', '30', '');
INSERT INTO `tb_good` VALUES ('10004', '拉菲', '瓶', '500ml', '1000', '2000', '4', '1', '180', '2', '');
INSERT INTO `tb_good` VALUES ('10005', '西瓜', '个', '500g', '5', '10', '14', '4', '10', '2', '');
INSERT INTO `tb_good` VALUES ('10006', '辣条', '包', '100g', '2', '2.5', '201', '4', '360', '20', '');
INSERT INTO `tb_good` VALUES ('10007', '鸡尾酒', '瓶', '200ml', '8', '10', '95', '3', '360', '5', '');
INSERT INTO `tb_good` VALUES ('10008', '香蕉', '个', '500g', '2', '2.5', '121', '4', '5', '3', '');
INSERT INTO `tb_good` VALUES ('10009', '苹果', '个', '800g', '1', '1.5', '164', '1', '20', '20', '');
INSERT INTO `tb_good` VALUES ('10010', '辣子鱼', '包', '50g', '2', '2.5', '201', '4', '360', '5', '');
INSERT INTO `tb_good` VALUES ('10011', '浏阳河', '瓶', '500ml', '500', '1000', '19', '1', '360', '5', '');
INSERT INTO `tb_good` VALUES ('10012', '哇哈哈', '瓶', '500ml', '2', '2.5', '52', '2', '360', '5', '');
INSERT INTO `tb_good` VALUES ('10013', '冰红茶', '瓶', '500ml', '3', '3.5', '99', '2', '360', '3', '');
INSERT INTO `tb_good` VALUES ('10014', '红牛', '瓶', '50ml', '5', '6', '50', '2', '360', '6', '');
INSERT INTO `tb_good` VALUES ('10015', '牛奶', '箱', '500ml', '2.5', '3', '62', '2', '50', '9', '');
INSERT INTO `tb_good` VALUES ('10016', '菠萝', '个', '500g', '5', '6', '100', '1', '5', '6', '');
INSERT INTO `tb_good` VALUES ('10017', '椰子糖', '包', '500ml', '6', '7', '108', '4', '360', '6', '');
INSERT INTO `tb_good` VALUES ('10019', '旺仔小馒头', '个', '100g', '1', '2', '100', '1', '100', '5', '');
INSERT INTO `tb_good` VALUES ('10021', '可乐', '瓶', '500ml', '2', '2.5', '2', '2', '360', '22', '');

-- ----------------------------
-- Table structure for `tb_position`
-- ----------------------------
DROP TABLE IF EXISTS `tb_position`;
CREATE TABLE `tb_position` (
  `posi_id` int(11) NOT NULL AUTO_INCREMENT,
  `posi_name` varchar(20) NOT NULL,
  `posi_introduction` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`posi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_position
-- ----------------------------
INSERT INTO `tb_position` VALUES ('1', '采购员', '负责采购进货');
INSERT INTO `tb_position` VALUES ('2', '销售员', '负责销售商品');
INSERT INTO `tb_position` VALUES ('3', '仓管员', '负责管理仓库');
INSERT INTO `tb_position` VALUES ('4', '管理员', '负责所有');

-- ----------------------------
-- Table structure for `tb_purchaseorder`
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchaseorder`;
CREATE TABLE `tb_purchaseorder` (
  `pur_id` int(11) NOT NULL AUTO_INCREMENT,
  `pur_supplyId` int(11) NOT NULL,
  `pur_date` date NOT NULL,
  `pur_pay` double NOT NULL,
  `pur_empId` int(11) NOT NULL,
  `pur_status` tinyint(4) NOT NULL,
  `pur_mark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pur_id`),
  KEY `fk_type_supply` (`pur_supplyId`),
  KEY `fk_type_employee2` (`pur_empId`),
  CONSTRAINT `fk_type_employee2` FOREIGN KEY (`pur_empId`) REFERENCES `tb_employee` (`emp_id`),
  CONSTRAINT `fk_type_supply` FOREIGN KEY (`pur_supplyId`) REFERENCES `tb_supply` (`sup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20171039 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_purchaseorder
-- ----------------------------
INSERT INTO `tb_purchaseorder` VALUES ('20171001', '1', '2017-08-25', '2', '154083005', '1', null);
INSERT INTO `tb_purchaseorder` VALUES ('20171011', '1', '2017-08-26', '20200', '154083005', '1', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171013', '1', '2017-08-24', '200', '154083005', '1', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171014', '1', '2017-08-25', '720', '154083005', '1', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171015', '1', '2017-08-23', '280', '154083005', '1', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171016', '1', '2017-08-22', '10008', '154083005', '3', '桃子不要');
INSERT INTO `tb_purchaseorder` VALUES ('20171017', '1', '2017-08-26', '0', '154083005', '1', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171018', '1', '2017-08-24', '4', '154083005', '1', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171019', '1', '2017-08-25', '672', '154083005', '0', '红牛不要');
INSERT INTO `tb_purchaseorder` VALUES ('20171020', '1', '2017-08-22', '18', '154083005', '2', '西瓜不要');
INSERT INTO `tb_purchaseorder` VALUES ('20171021', '1', '2017-08-23', '590', '154083005', '1', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171022', '1', '2017-08-24', '5010', '154083005', '3', '全退');
INSERT INTO `tb_purchaseorder` VALUES ('20171023', '1', '2017-08-25', '40', '154083005', '3', '牛奶不要');
INSERT INTO `tb_purchaseorder` VALUES ('20171024', '1', '2017-08-26', '9', '154083005', '1', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171025', '3', '2017-08-26', '15', '154083005', '1', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171026', '1', '2017-08-26', '16', '154083010', '0', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171027', '3', '2017-08-26', '49', '154083008', '0', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171028', '1', '2017-08-26', '16', '154083010', '0', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171029', '1', '2017-08-26', '2028', '154083005', '0', '退全部');
INSERT INTO `tb_purchaseorder` VALUES ('20171030', '1', '2017-08-26', '559', '154083010', '3', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171031', '1', '2017-08-26', '511', '154083008', '0', '全部退');
INSERT INTO `tb_purchaseorder` VALUES ('20171032', '1', '2017-08-26', '0', '154083005', '0', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171033', '1', '2017-08-26', '56', '154083008', '1', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171034', '1', '2017-08-26', '18', '154083005', '3', '全部退货');
INSERT INTO `tb_purchaseorder` VALUES ('20171035', '7', '2017-08-26', '2010', '154083005', '1', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171036', '2', '2017-08-26', '2547', '154083008', '1', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171037', '1', '2017-08-26', '2004', '154083005', '3', '');
INSERT INTO `tb_purchaseorder` VALUES ('20171038', '1', '2017-08-26', '0', '154083005', '0', '');

-- ----------------------------
-- Table structure for `tb_purchaseplan`
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchaseplan`;
CREATE TABLE `tb_purchaseplan` (
  `plan_id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_date` date NOT NULL,
  `plan_empId` int(11) NOT NULL,
  `plan_mark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`plan_id`),
  KEY `fk_type_employee1` (`plan_empId`),
  CONSTRAINT `fk_type_employee1` FOREIGN KEY (`plan_empId`) REFERENCES `tb_employee` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20173018 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_purchaseplan
-- ----------------------------
INSERT INTO `tb_purchaseplan` VALUES ('20173002', '2017-08-25', '154083007', '');
INSERT INTO `tb_purchaseplan` VALUES ('20173003', '2017-08-26', '154083007', '');
INSERT INTO `tb_purchaseplan` VALUES ('20173004', '2017-08-24', '154083012', '');
INSERT INTO `tb_purchaseplan` VALUES ('20173005', '2017-08-25', '154083013', '');
INSERT INTO `tb_purchaseplan` VALUES ('20173006', '2017-08-26', '154083014', '');
INSERT INTO `tb_purchaseplan` VALUES ('20173007', '2017-08-14', '154083007', null);
INSERT INTO `tb_purchaseplan` VALUES ('20173008', '2017-08-26', '154083013', '');
INSERT INTO `tb_purchaseplan` VALUES ('20173009', '2017-08-14', '154083007', null);
INSERT INTO `tb_purchaseplan` VALUES ('20173010', '2017-08-14', '154083007', null);
INSERT INTO `tb_purchaseplan` VALUES ('20173011', '2017-08-26', '154083007', '');
INSERT INTO `tb_purchaseplan` VALUES ('20173012', '2017-08-14', '154083007', null);
INSERT INTO `tb_purchaseplan` VALUES ('20173013', '2017-08-26', '154083007', '');
INSERT INTO `tb_purchaseplan` VALUES ('20173014', '2017-08-26', '154083007', '');
INSERT INTO `tb_purchaseplan` VALUES ('20173015', '2017-08-26', '154083007', '');
INSERT INTO `tb_purchaseplan` VALUES ('20173016', '2017-08-14', '154083007', null);
INSERT INTO `tb_purchaseplan` VALUES ('20173017', '2017-08-26', '154083007', '');

-- ----------------------------
-- Table structure for `tb_purdetail`
-- ----------------------------
DROP TABLE IF EXISTS `tb_purdetail`;
CREATE TABLE `tb_purdetail` (
  `pDet_id` int(11) NOT NULL AUTO_INCREMENT,
  `pDet_purId` int(11) NOT NULL,
  `pDet_goodId` int(11) NOT NULL,
  `pDet_number` int(11) NOT NULL,
  `pDet_goodPrice` double NOT NULL,
  `pDet_status` tinyint(4) NOT NULL,
  `pDet_mark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pDet_id`),
  KEY `fk_type_purchaseOrder` (`pDet_purId`),
  KEY `fk_type_purchaseOrder2` (`pDet_goodId`),
  CONSTRAINT `fk_type_purchaseOrder` FOREIGN KEY (`pDet_purId`) REFERENCES `tb_purchaseorder` (`pur_id`),
  CONSTRAINT `fk_type_purchaseOrder2` FOREIGN KEY (`pDet_goodId`) REFERENCES `tb_good` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_purdetail
-- ----------------------------
INSERT INTO `tb_purdetail` VALUES ('3', '20171001', '10001', '1', '2', '0', '无备注');
INSERT INTO `tb_purdetail` VALUES ('4', '20171011', '10002', '50', '100', '0', '');
INSERT INTO `tb_purdetail` VALUES ('5', '20171011', '10003', '100', '100', '0', '');
INSERT INTO `tb_purdetail` VALUES ('6', '20171011', '10004', '20', '20000', '0', '');
INSERT INTO `tb_purdetail` VALUES ('7', '20171013', '10006', '100', '200', '0', '');
INSERT INTO `tb_purdetail` VALUES ('8', '20171014', '10007', '50', '400', '0', '');
INSERT INTO `tb_purdetail` VALUES ('9', '20171014', '10008', '60', '120', '0', '');
INSERT INTO `tb_purdetail` VALUES ('10', '20171015', '10009', '80', '80', '0', '');
INSERT INTO `tb_purdetail` VALUES ('11', '20171015', '10010', '100', '200', '0', '');
INSERT INTO `tb_purdetail` VALUES ('12', '20171016', '10011', '20', '10000', '2', '');
INSERT INTO `tb_purdetail` VALUES ('13', '20171016', '10001', '4', '8', '2', '甜的不行');
INSERT INTO `tb_purdetail` VALUES ('14', '20171018', '10001', '1', '2', '0', '甜的不行');
INSERT INTO `tb_purdetail` VALUES ('15', '20171018', '10003', '1', '1', '0', '');
INSERT INTO `tb_purdetail` VALUES ('16', '20171018', '10009', '1', '1', '0', '');
INSERT INTO `tb_purdetail` VALUES ('17', '20171019', '10001', '1', '2', '1', '甜的不行');
INSERT INTO `tb_purdetail` VALUES ('18', '20171019', '10013', '90', '270', '1', '');
INSERT INTO `tb_purdetail` VALUES ('19', '20171019', '10014', '50', '250', '2', '');
INSERT INTO `tb_purdetail` VALUES ('20', '20171019', '10015', '60', '150', '1', '');
INSERT INTO `tb_purdetail` VALUES ('21', '20171020', '10005', '2', '10', '1', '');
INSERT INTO `tb_purdetail` VALUES ('22', '20171020', '10012', '4', '8', '1', '');
INSERT INTO `tb_purdetail` VALUES ('23', '20171021', '10016', '50', '250', '0', '');
INSERT INTO `tb_purdetail` VALUES ('24', '20171021', '10017', '55', '330', '0', '');
INSERT INTO `tb_purdetail` VALUES ('25', '20171021', '10001', '5', '10', '0', '甜的不行');
INSERT INTO `tb_purdetail` VALUES ('26', '20171022', '10004', '5', '5000', '2', '');
INSERT INTO `tb_purdetail` VALUES ('27', '20171022', '10010', '5', '10', '2', '');
INSERT INTO `tb_purdetail` VALUES ('28', '20171023', '10016', '5', '25', '2', '');
INSERT INTO `tb_purdetail` VALUES ('29', '20171023', '10015', '6', '15', '2', '');
INSERT INTO `tb_purdetail` VALUES ('30', '20171024', '10012', '2', '4', '0', '');
INSERT INTO `tb_purdetail` VALUES ('31', '20171024', '10015', '2', '5', '0', '');
INSERT INTO `tb_purdetail` VALUES ('32', '20171025', '10005', '2', '10', '0', '');
INSERT INTO `tb_purdetail` VALUES ('33', '20171025', '10003', '5', '5', '0', '');
INSERT INTO `tb_purdetail` VALUES ('34', '20171026', '10005', '2', '10', '1', '');
INSERT INTO `tb_purdetail` VALUES ('35', '20171026', '10013', '2', '6', '1', '');
INSERT INTO `tb_purdetail` VALUES ('36', '20171027', '10007', '5', '40', '1', '');
INSERT INTO `tb_purdetail` VALUES ('37', '20171027', '10013', '3', '9', '1', '');
INSERT INTO `tb_purdetail` VALUES ('38', '20171028', '10017', '1', '6', '1', '');
INSERT INTO `tb_purdetail` VALUES ('39', '20171028', '10016', '2', '10', '1', '');
INSERT INTO `tb_purdetail` VALUES ('40', '20171029', '10003', '2', '2', '2', '');
INSERT INTO `tb_purdetail` VALUES ('41', '20171029', '10011', '4', '2000', '2', '');
INSERT INTO `tb_purdetail` VALUES ('42', '20171029', '10013', '2', '6', '2', '');
INSERT INTO `tb_purdetail` VALUES ('43', '20171029', '10014', '2', '10', '2', '');
INSERT INTO `tb_purdetail` VALUES ('44', '20171029', '10010', '3', '6', '2', '');
INSERT INTO `tb_purdetail` VALUES ('45', '20171029', '10009', '4', '4', '2', '');
INSERT INTO `tb_purdetail` VALUES ('46', '20171030', '10005', '11', '55', '2', '');
INSERT INTO `tb_purdetail` VALUES ('47', '20171030', '10011', '1', '500', '2', '');
INSERT INTO `tb_purdetail` VALUES ('48', '20171030', '10001', '1', '2', '2', '甜的不行');
INSERT INTO `tb_purdetail` VALUES ('49', '20171030', '10010', '1', '2', '2', '');
INSERT INTO `tb_purdetail` VALUES ('50', '20171031', '10006', '1', '2', '2', '');
INSERT INTO `tb_purdetail` VALUES ('51', '20171031', '10011', '1', '500', '2', '');
INSERT INTO `tb_purdetail` VALUES ('52', '20171031', '10009', '1', '1', '2', '');
INSERT INTO `tb_purdetail` VALUES ('53', '20171031', '10007', '1', '8', '2', '');
INSERT INTO `tb_purdetail` VALUES ('54', '20171033', '10003', '1', '1', '0', '');
INSERT INTO `tb_purdetail` VALUES ('55', '20171033', '10001', '1', '2', '0', '甜的不行');
INSERT INTO `tb_purdetail` VALUES ('56', '20171033', '10003', '1', '1', '0', '');
INSERT INTO `tb_purdetail` VALUES ('57', '20171033', '10006', '1', '2', '0', '');
INSERT INTO `tb_purdetail` VALUES ('58', '20171033', '10019', '50', '50', '0', '');
INSERT INTO `tb_purdetail` VALUES ('59', '20171034', '10006', '1', '2', '2', '');
INSERT INTO `tb_purdetail` VALUES ('60', '20171034', '10007', '1', '8', '2', '');
INSERT INTO `tb_purdetail` VALUES ('61', '20171034', '10009', '1', '1', '2', '');
INSERT INTO `tb_purdetail` VALUES ('62', '20171034', '10012', '1', '2', '2', '');
INSERT INTO `tb_purdetail` VALUES ('63', '20171034', '10006', '1', '2', '2', '');
INSERT INTO `tb_purdetail` VALUES ('64', '20171034', '10008', '1', '2', '2', '');
INSERT INTO `tb_purdetail` VALUES ('65', '20171034', '10009', '1', '1', '2', '');
INSERT INTO `tb_purdetail` VALUES ('66', '20171035', '10001', '1', '2', '0', '甜的不行');
INSERT INTO `tb_purdetail` VALUES ('67', '20171035', '10004', '1', '1000', '0', '');
INSERT INTO `tb_purdetail` VALUES ('68', '20171035', '10008', '1', '2', '0', '');
INSERT INTO `tb_purdetail` VALUES ('69', '20171035', '10010', '1', '2', '0', '');
INSERT INTO `tb_purdetail` VALUES ('70', '20171035', '10001', '1', '2', '0', '甜的不行');
INSERT INTO `tb_purdetail` VALUES ('71', '20171035', '10002', '1', '2', '0', '');
INSERT INTO `tb_purdetail` VALUES ('72', '20171035', '10004', '1', '1000', '0', '');
INSERT INTO `tb_purdetail` VALUES ('73', '20171036', '10005', '2', '10', '0', '');
INSERT INTO `tb_purdetail` VALUES ('74', '20171036', '10009', '2', '2', '0', '');
INSERT INTO `tb_purdetail` VALUES ('75', '20171036', '10021', '2', '4', '2', '');
INSERT INTO `tb_purdetail` VALUES ('76', '20171036', '10004', '1', '1000', '0', '');
INSERT INTO `tb_purdetail` VALUES ('77', '20171036', '10009', '1', '1', '0', '');
INSERT INTO `tb_purdetail` VALUES ('78', '20171036', '10011', '1', '500', '0', '');
INSERT INTO `tb_purdetail` VALUES ('79', '20171036', '10013', '10', '30', '0', '');
INSERT INTO `tb_purdetail` VALUES ('80', '20171036', '10004', '1', '1000', '0', '');
INSERT INTO `tb_purdetail` VALUES ('81', '20171037', '10004', '2', '2000', '2', '');
INSERT INTO `tb_purdetail` VALUES ('82', '20171037', '10010', '2', '4', '2', '');

-- ----------------------------
-- Table structure for `tb_purplandetail`
-- ----------------------------
DROP TABLE IF EXISTS `tb_purplandetail`;
CREATE TABLE `tb_purplandetail` (
  `planDet_id` int(11) NOT NULL AUTO_INCREMENT,
  `planDet_purId` int(11) NOT NULL,
  `planDet_goodId` int(11) NOT NULL,
  `planDet_number` int(11) NOT NULL,
  `planDet_goodPrice` double NOT NULL,
  `planDet_mark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`planDet_id`),
  KEY `fk_type_purchasePlan` (`planDet_purId`),
  KEY `fk_type_purchasePlan2` (`planDet_goodId`),
  CONSTRAINT `fk_type_purchasePlan` FOREIGN KEY (`planDet_purId`) REFERENCES `tb_purchaseplan` (`plan_id`),
  CONSTRAINT `fk_type_purchasePlan2` FOREIGN KEY (`planDet_goodId`) REFERENCES `tb_good` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_purplandetail
-- ----------------------------
INSERT INTO `tb_purplandetail` VALUES ('1', '20173002', '10003', '2', '2', null);
INSERT INTO `tb_purplandetail` VALUES ('2', '20173002', '10011', '4', '2000', null);
INSERT INTO `tb_purplandetail` VALUES ('3', '20173002', '10013', '2', '6', null);
INSERT INTO `tb_purplandetail` VALUES ('4', '20173003', '10013', '4', '12', null);
INSERT INTO `tb_purplandetail` VALUES ('5', '20173003', '10015', '4', '10', null);
INSERT INTO `tb_purplandetail` VALUES ('6', '20173004', '10005', '4', '20', null);
INSERT INTO `tb_purplandetail` VALUES ('7', '20173004', '10015', '2', '5', null);
INSERT INTO `tb_purplandetail` VALUES ('8', '20173004', '10002', '2', '4', null);
INSERT INTO `tb_purplandetail` VALUES ('9', '20173005', '10014', '2', '10', null);
INSERT INTO `tb_purplandetail` VALUES ('10', '20173005', '10010', '3', '6', null);
INSERT INTO `tb_purplandetail` VALUES ('11', '20173005', '10009', '2', '2', null);
INSERT INTO `tb_purplandetail` VALUES ('12', '20173006', '10007', '10', '80', null);
INSERT INTO `tb_purplandetail` VALUES ('13', '20173006', '10012', '1', '2', null);
INSERT INTO `tb_purplandetail` VALUES ('14', '20173008', '10005', '11', '55', null);
INSERT INTO `tb_purplandetail` VALUES ('15', '20173008', '10011', '1', '500', null);
INSERT INTO `tb_purplandetail` VALUES ('16', '20173011', '10001', '1', '2', null);
INSERT INTO `tb_purplandetail` VALUES ('17', '20173011', '10007', '1', '8', null);
INSERT INTO `tb_purplandetail` VALUES ('18', '20173011', '10011', '1', '500', null);
INSERT INTO `tb_purplandetail` VALUES ('19', '20173011', '10009', '1', '1', null);
INSERT INTO `tb_purplandetail` VALUES ('20', '20173011', '10005', '1', '5', null);
INSERT INTO `tb_purplandetail` VALUES ('21', '20173013', '10006', '1', '2', null);
INSERT INTO `tb_purplandetail` VALUES ('22', '20173013', '10009', '1', '1', null);
INSERT INTO `tb_purplandetail` VALUES ('23', '20173014', '10006', '1', '2', null);
INSERT INTO `tb_purplandetail` VALUES ('24', '20173014', '10008', '1', '2', null);
INSERT INTO `tb_purplandetail` VALUES ('25', '20173014', '10009', '1', '1', null);
INSERT INTO `tb_purplandetail` VALUES ('26', '20173015', '10001', '1', '2', null);
INSERT INTO `tb_purplandetail` VALUES ('27', '20173015', '10002', '1', '2', null);
INSERT INTO `tb_purplandetail` VALUES ('28', '20173015', '10004', '1', '1000', null);
INSERT INTO `tb_purplandetail` VALUES ('29', '20173017', '10004', '1', '1000', null);
INSERT INTO `tb_purplandetail` VALUES ('30', '20173017', '10009', '1', '1', null);
INSERT INTO `tb_purplandetail` VALUES ('31', '20173017', '10011', '1', '500', null);
INSERT INTO `tb_purplandetail` VALUES ('32', '20173017', '10013', '10', '30', null);

-- ----------------------------
-- Table structure for `tb_selldetail`
-- ----------------------------
DROP TABLE IF EXISTS `tb_selldetail`;
CREATE TABLE `tb_selldetail` (
  `sDet_id` int(11) NOT NULL AUTO_INCREMENT,
  `sDet_sellId` int(11) NOT NULL,
  `sDet_goodId` int(11) NOT NULL,
  `sDet_number` int(11) NOT NULL,
  `sDet_goodPrice` double NOT NULL,
  `sDet_status` tinyint(4) NOT NULL,
  `sDet_mark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sDet_id`),
  KEY `fk_type_sellOrder` (`sDet_sellId`),
  KEY `fk_type_sellOrder2` (`sDet_goodId`),
  CONSTRAINT `fk_type_sellOrder` FOREIGN KEY (`sDet_sellId`) REFERENCES `tb_sellorder` (`sell_id`),
  CONSTRAINT `fk_type_sellOrder2` FOREIGN KEY (`sDet_goodId`) REFERENCES `tb_good` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_selldetail
-- ----------------------------
INSERT INTO `tb_selldetail` VALUES ('1', '20172002', '10007', '5', '50', '0', '');
INSERT INTO `tb_selldetail` VALUES ('2', '20172002', '10011', '2', '2000', '0', '');
INSERT INTO `tb_selldetail` VALUES ('3', '20172002', '10013', '1', '3.5', '0', '');
INSERT INTO `tb_selldetail` VALUES ('4', '20172003', '10004', '1', '2000', '0', '');
INSERT INTO `tb_selldetail` VALUES ('5', '20172003', '10002', '2', '5', '0', '');
INSERT INTO `tb_selldetail` VALUES ('6', '20172004', '10002', '2', '5', '0', '');
INSERT INTO `tb_selldetail` VALUES ('7', '20172004', '10017', '2', '14', '0', '');
INSERT INTO `tb_selldetail` VALUES ('8', '20172006', '10002', '24', '60', '1', '');
INSERT INTO `tb_selldetail` VALUES ('9', '20172006', '10003', '12', '18', '1', '');
INSERT INTO `tb_selldetail` VALUES ('10', '20172006', '10004', '24', '48000', '1', '');
INSERT INTO `tb_selldetail` VALUES ('11', '20172006', '10005', '12', '120', '1', '');
INSERT INTO `tb_selldetail` VALUES ('12', '20172007', '10001', '26', '91', '0', '');
INSERT INTO `tb_selldetail` VALUES ('13', '20172007', '10002', '96', '240', '0', '');
INSERT INTO `tb_selldetail` VALUES ('14', '20172007', '10003', '206', '309', '0', '');
INSERT INTO `tb_selldetail` VALUES ('15', '20172007', '10004', '39', '78000', '0', '');
INSERT INTO `tb_selldetail` VALUES ('16', '20172007', '10007', '1', '10', '1', '');
INSERT INTO `tb_selldetail` VALUES ('17', '20172008', '10003', '2', '3', '1', '');
INSERT INTO `tb_selldetail` VALUES ('18', '20172008', '10006', '1', '2.5', '1', '');

-- ----------------------------
-- Table structure for `tb_sellorder`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sellorder`;
CREATE TABLE `tb_sellorder` (
  `sell_id` int(11) NOT NULL AUTO_INCREMENT,
  `sell_empId` int(11) NOT NULL,
  `sell_date` date NOT NULL,
  `sell_profit` double NOT NULL,
  `sell_status` int(11) NOT NULL,
  `sell_mark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sell_id`),
  KEY `fk_type_employee3` (`sell_empId`),
  CONSTRAINT `fk_type_employee3` FOREIGN KEY (`sell_empId`) REFERENCES `tb_employee` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20172009 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sellorder
-- ----------------------------
INSERT INTO `tb_sellorder` VALUES ('20172002', '154083006', '2017-08-26', '2053.5', '0', '');
INSERT INTO `tb_sellorder` VALUES ('20172003', '154083006', '2017-08-26', '2005', '0', '');
INSERT INTO `tb_sellorder` VALUES ('20172004', '154083006', '2017-08-26', '19', '0', '');
INSERT INTO `tb_sellorder` VALUES ('20172006', '154083009', '2017-08-26', '48198', '1', '');
INSERT INTO `tb_sellorder` VALUES ('20172007', '154083006', '2017-08-26', '78650', '0', '');
INSERT INTO `tb_sellorder` VALUES ('20172008', '154083006', '2017-08-26', '5.5', '1', '');

-- ----------------------------
-- Table structure for `tb_storage`
-- ----------------------------
DROP TABLE IF EXISTS `tb_storage`;
CREATE TABLE `tb_storage` (
  `sto_id` int(11) NOT NULL AUTO_INCREMENT,
  `sto_name` varchar(20) NOT NULL,
  `sto_empId` int(11) NOT NULL,
  `sto_address` varchar(20) DEFAULT NULL,
  `sto_mark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sto_id`),
  KEY `fk_type_employee` (`sto_empId`),
  CONSTRAINT `fk_type_employee` FOREIGN KEY (`sto_empId`) REFERENCES `tb_employee` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_storage
-- ----------------------------
INSERT INTO `tb_storage` VALUES ('1', '主仓库', '154083007', '美国纽约', '小金库');
INSERT INTO `tb_storage` VALUES ('2', '饮料库', '154083007', '美国纽约', '大仓库');
INSERT INTO `tb_storage` VALUES ('3', '酒库', '154083007', '美国纽约', '中仓库');
INSERT INTO `tb_storage` VALUES ('4', '零食库', '154083007', '美国纽约', '小仓库');

-- ----------------------------
-- Table structure for `tb_supply`
-- ----------------------------
DROP TABLE IF EXISTS `tb_supply`;
CREATE TABLE `tb_supply` (
  `sup_id` int(11) NOT NULL AUTO_INCREMENT,
  `sup_name` varchar(20) NOT NULL,
  `sup_address` varchar(20) DEFAULT NULL,
  `sup_linkMan` varchar(20) DEFAULT NULL,
  `sup_phone` varchar(11) DEFAULT NULL,
  `sup_status` tinyint(4) NOT NULL,
  `sup_mark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_supply
-- ----------------------------
INSERT INTO `tb_supply` VALUES ('1', '阿里巴巴供应商', '浙江杭州', '马云', '15096661111', '0', '阿里巴巴首席执行官');
INSERT INTO `tb_supply` VALUES ('2', '百度供应商', '北京朝阳区', '李彦宏', '15096662222', '0', '百度CEO');
INSERT INTO `tb_supply` VALUES ('3', '腾讯供应商', '广东深圳', '马化腾', '15096663333', '0', '腾讯CEO');
INSERT INTO `tb_supply` VALUES ('4', '京东供应商', '北京朝阳区', '刘强东', '15096663333', '0', '京东大老板');
INSERT INTO `tb_supply` VALUES ('5', '淘宝供应商', '浙江杭州', '马云', '13965432178', '0', '货源丰富');
INSERT INTO `tb_supply` VALUES ('6', '阿里云', '北京朝阳区', '马云云', '13846523145', '0', '服务器大佬');
INSERT INTO `tb_supply` VALUES ('7', '蘑菇街供应商', '北京', '刘老板', '13798456255', '0', '有钱任性');
INSERT INTO `tb_supply` VALUES ('8', '码头一把手供应商', '上海', '潘总', '13986541234', '1', '上海扛把子');
