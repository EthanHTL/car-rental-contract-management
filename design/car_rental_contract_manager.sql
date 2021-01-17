/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : car_rental_contract_manager

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 18/01/2021 00:25:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract`  (
  `contract_id` bigint NOT NULL COMMENT '编号',
  `contract_numbers` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合同编号',
  `contract_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同名称',
  `sign_unit` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签订单位',
  `payment` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式',
  `principal` bigint NULL DEFAULT NULL COMMENT '合同负责人',
  `contact_user_id` bigint NULL DEFAULT NULL COMMENT '客户联系人',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `contract_amount` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同金额',
  `paid_amount` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已付金额',
  `sign_time` datetime NULL DEFAULT NULL COMMENT '合同签订时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '合同有效期',
  `contract_life` datetime NULL DEFAULT NULL COMMENT '合同到期时间',
  `contract_url` varchar(2566) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同路径',
  `contract_type` bigint NULL DEFAULT NULL COMMENT '合同类型',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`contract_id`, `contract_numbers`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '合同表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES (1, '1', '1', '1', '1', 1, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `contract` VALUES (2, '2', '2', '2', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees`  (
  `code` bigint NOT NULL COMMENT '用户编号',
  `sex` tinyint NULL DEFAULT NULL COMMENT '性别',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `id_card` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employees
-- ----------------------------

-- ----------------------------
-- Table structure for flow
-- ----------------------------
DROP TABLE IF EXISTS `flow`;
CREATE TABLE `flow`  (
  `flow_id` bigint NOT NULL COMMENT '流程编号，主键',
  `flow_no` bigint NULL DEFAULT NULL COMMENT '流程号，惟一列',
  `flow_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程名称',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`flow_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flow
-- ----------------------------

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_used` timestamp NOT NULL,
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for sys_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_api`;
CREATE TABLE `sys_api`  (
  `id` bigint NOT NULL,
  `api_pid` bigint NULL DEFAULT NULL,
  `api_pids` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_leaf` tinyint(1) NOT NULL,
  `api_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int NULL DEFAULT NULL,
  `level` int NULL DEFAULT NULL,
  `flag` tinyint(1) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `creator_id` bigint NULL DEFAULT NULL,
  `updator_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `is_leaf`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_api
-- ----------------------------
INSERT INTO `sys_api` VALUES (1, 0, '[0]', 0, '系统管理接口', '/api/v1/car/contract/find/page', 1, 1, 1, '2021-01-13 23:50:06', '2021-01-13 23:50:09', NULL, NULL);
INSERT INTO `sys_api` VALUES (2, 1, '[0],[1]', 0, '系统管理模块', '/api/v1/car/contract/find/page', 1, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_api` VALUES (3, 2, '[0],[1],[2]', 1, '用户信息接口', '/api/v1/car/contract/find/page', 1, 2, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint NOT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置项名字',
  `descript` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'sex', '性别', '性别的数据字典', NULL);
INSERT INTO `sys_dict` VALUES (2, 'payment', '支付方式', '支付方式', NULL);
INSERT INTO `sys_dict` VALUES (3, 'file', '文件', '文件类型', NULL);

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail`  (
  `id` bigint NOT NULL,
  `dict_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES (1, '1', '0', '男');
INSERT INTO `sys_dict_detail` VALUES (2, '1', '1', '女');
INSERT INTO `sys_dict_detail` VALUES (3, '1', '2', '外星人');
INSERT INTO `sys_dict_detail` VALUES (4, '2', '1', '支付宝');
INSERT INTO `sys_dict_detail` VALUES (5, '2', '2', '微信');
INSERT INTO `sys_dict_detail` VALUES (6, '3', 'contract', '合同');
INSERT INTO `sys_dict_detail` VALUES (7, '3', 'head_img', '头像');
INSERT INTO `sys_dict_detail` VALUES (8, '3', 'thumbnail', '缩略图');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL,
  `menu_pid` bigint NULL DEFAULT NULL,
  `menu_pids` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_leaf` tinyint(1) NULL DEFAULT NULL,
  `menu_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int NULL DEFAULT NULL,
  `level` int NULL DEFAULT NULL,
  `flag` tinyint(1) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `creator_id` bigint NULL DEFAULT NULL,
  `updator_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '[0]', 0, '系统根目录', '/', NULL, 1, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (2, 1, '[0],[1]', 0, '系统管理', '/system', NULL, 1, 2, 1, '2021-01-13 23:09:22', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (3, 2, '[0],[1],[2]', 1, '用户管理', '/home/user', NULL, 1, 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (4, 0, '[0]', 0, '合同', '/contract', NULL, 1, 1, 1, '2021-01-13 23:09:19', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (5, 4, NULL, 0, '合同子1', '/c/01', NULL, 1, 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (6, 5, NULL, 1, '合同子子1', '/c/01/01', NULL, 1, 3, 1, '2021-01-13 23:11:02', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (7, 2, NULL, 1, '字典管理', '/dict', NULL, 1, 2, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` bigint NOT NULL COMMENT ' ',
  `old_filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '旧名字',
  `new_filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新名字',
  `img` mediumblob NULL COMMENT '图片',
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `ext` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '后缀',
  `size` double NULL DEFAULT NULL COMMENT '大小',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `dict_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `flag` tinyint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `creator_id` bigint NULL DEFAULT NULL,
  `updator_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, '合同1.docx', '123.docx', NULL, '<p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\"><br/> </span><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">出租方(甲方)： 身份证号码：</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　承租方(乙方)： 身份证号码：</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　甲、乙双方通过友好协商，就档口租赁事宜达成协议如下：</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　一、租赁地址：</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　二、租用期限及其约定：</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　1.租用期限：自年月日起年月日;</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　2.档口租金：每月元人民币;</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　3.付款方式：按月支付，另付押金元，租房终止，甲方鸡蛋无误后，将押金退还乙方，不计利息;</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　4.租赁期内的水、电、煤气、卫生治安费由乙方支付;</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　5.租用期内，乙方有下列情形之一的甲方可终止合同，收回档口使用权，乙方需承担全部责任，并赔偿甲方损失。</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　(1)乙方擅自将档口转租，转让或转借的;</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　(2)乙方利用承租档口进行非法活动损害公共利益的;</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　(3)乙方无故拖欠档口租金达天;</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　(4)连续3个月不付所有费用的。</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　四、双方责任及义务：</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　1.乙方须按时交纳水、电、煤、电话等费用，并务必将以上费用帐单交给甲方，甲方须监督检查以上费用。</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　2.无论在任何情况下，乙方都不能将押金转换为档口租金;</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　3.租用期满后，乙方如需继续使用，应提前1个月提出，甲方可根据实际情况，在同等条件下给予优先;</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　4.在租赁期内，甲乙双方如有一方有特殊情况需解除协议的，必须提前一个月通知对方，协商后解除本协议;</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　5.乙方入住该物业应保持周围环境整洁做好防火防盗工作，如发生事故乙方应负全部责任;</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　五、其它未尽事宜，由甲乙双方协商解决，协商不成按有关现行法规办理或提交有关仲裁机关进行仲裁。本协议一式两份，甲、乙双主各执一份，签字后即行生效。</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　甲方(公章)：_________ &nbsp; &nbsp; &nbsp; &nbsp;乙方(公章)：_________</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　法定代表人(签字)：_________ &nbsp; &nbsp; 法定代表人(签字)：_________</span></p><p style=\"text-align:left;line-height:30px\"><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　_________年____月____日 &nbsp; &nbsp; &nbsp; _________年____月____日</span></p><p>&nbsp;</p><p><br/></p>', NULL, NULL, NULL, 'contract', NULL, NULL, 1, NULL, '2021-01-17 14:41:14', NULL, NULL);
INSERT INTO `sys_resource` VALUES (2, '合同2.docx', '1234.docx', NULL, '<h1 label=\"Title left\" name=\"tl\" style=\"border-bottom-color:#cccccc;border-bottom-width:2px;border-bottom-style:solid;padding:0px 4px 0px 0px;margin:0px 0px 10px;\"><span style=\"color:#e36c09;\" class=\"ue_t\">[此处键入简历标题]</span></h1><p><span style=\"color:#e36c09;\"><br/></span></p><table width=\"100%\" border=\"1\" bordercolor=\"#95B3D7\" style=\"border-collapse:collapse;\"><tbody><tr class=\"firstRow\"><td width=\"200\" style=\"text-align:center;\" class=\"ue_t\">【此处插入照片】</td><td><p><br/></p><p>联系电话：<span class=\"ue_t\">[键入您的电话]</span></p><p><br/></p><p>电子邮件：<span class=\"ue_t\">[键入您的电子邮件地址]</span></p><p><br/></p><p>家庭住址：<span class=\"ue_t\">[键入您的地址]</span></p><p><br/></p></td></tr></tbody></table><h3><span style=\"color:#e36c09;font-size:20px;\">目标职位</span></h3><p style=\"text-indent:2em;\" class=\"ue_t\">[此处键入您的期望职位]</p><h3><span style=\"color:#e36c09;font-size:20px;\">学历</span></h3><p><br/></p><ol style=\"list-style-type: decimal;\" class=\" list-paddingleft-2\"><li><p><span class=\"ue_t\">[键入起止时间]</span> <span class=\"ue_t\">[键入学校名称] </span> <span class=\"ue_t\">[键入所学专业]</span> <span class=\"ue_t\">[键入所获学位]</span></p></li><li><p><span class=\"ue_t\">[键入起止时间]</span> <span class=\"ue_t\">[键入学校名称]</span> <span class=\"ue_t\">[键入所学专业]</span> <span class=\"ue_t\">[键入所获学位]</span></p></li></ol><h3><span style=\"color:#e36c09;font-size:20px;\" class=\"ue_t\">工作经验</span></h3><ol style=\"list-style-type: decimal;\" class=\" list-paddingleft-2\"><li><p><span class=\"ue_t\">[键入起止时间]</span> <span class=\"ue_t\">[键入公司名称]</span> <span class=\"ue_t\">[键入职位名称]</span></p></li><ol style=\"list-style-type: lower-alpha;\" class=\" list-paddingleft-2\"><li><p><span class=\"ue_t\">[键入负责项目]</span> <span class=\"ue_t\">[键入项目简介]</span></p></li><li><p><span class=\"ue_t\">[键入负责项目]</span> <span class=\"ue_t\">[键入项目简介]</span></p></li></ol><li><p><span class=\"ue_t\">[键入起止时间]</span> <span class=\"ue_t\">[键入公司名称]</span> <span class=\"ue_t\">[键入职位名称]</span></p></li><ol style=\"list-style-type: lower-alpha;\" class=\" list-paddingleft-2\"><li><p><span class=\"ue_t\">[键入负责项目]</span> <span class=\"ue_t\">[键入项目简介]</span></p></li></ol></ol><p><span style=\"color:#e36c09;font-size:20px;\">掌握技能</span></p><p style=\"text-indent:2em;\">&nbsp;<span class=\"ue_t\">[这里可以键入您所掌握的技能]</span><br/></p><p style=\"text-indent:2em;\"><span class=\"ue_t\"><br/></span></p><p style=\"text-indent:2em;\"><span class=\"ue_t\">特殊</span></p><p><br/></p>', NULL, NULL, NULL, 'contract', NULL, NULL, 1, NULL, '2021-01-17 14:43:10', NULL, NULL);
INSERT INTO `sys_resource` VALUES (3, '合同3.docx', '123.docx', NULL, '<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\"><br/> </span><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">出租方(甲方)： 身份证号码：</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　承租方(乙方)： 身份证号码：</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　甲、乙双方通过友好协商，就档口租赁事宜达成协议如下：</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　一、租赁地址：</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　二、租用期限及其约定：</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　1.租用期限：自年月日起年月日;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　2.档口租金：每月元人民币;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　3.付款方式：按月支付，另付押金元，租房终止，甲方鸡蛋无误后，将押金退还乙方，不计利息;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　4.租赁期内的水、电、煤气、卫生治安费由乙方支付;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　5.租用期内，乙方有下列情形之一的甲方可终止合同，收回档口使用权，乙方需承担全部责任，并赔偿甲方损失。</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　(1)乙方擅自将档口转租，转让或转借的;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　(2)乙方利用承租档口进行非法活动损害公共利益的;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　(3)乙方无故拖欠档口租金达天;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　(4)连续3个月不付所有费用的。</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　四、双方责任及义务：</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　1.乙方须按时交纳水、电、煤、电话等费用，并务必将以上费用帐单交给甲方，甲方须监督检查以上费用。</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　2.无论在任何情况下，乙方都不能将押金转换为档口租金;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　3.租用期满后，乙方如需继续使用，应提前1个月提出，甲方可根据实际情况，在同等条件下给予优先;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　4.在租赁期内，甲乙双方如有一方有特殊情况需解除协议的，必须提前一个月通知对方，协商后解除本协议;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　5.乙方入住该物业应保持周围环境整洁做好防火防盗工作，如发生事故乙方应负全部责任;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　6.乙方不得擅自改变室内结构，并爱惜使用室内敲诈，若人为损坏的将给予甲方相应赔偿;如发生自然损坏，应及时通知甲方，并配合甲方及时给予修复。</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　五、其它未尽事宜，由甲乙双方协商解决，协商不成按有关现行法规办理或提交有关仲裁机关进行仲裁。本协议一式两份，甲、乙双主各执一份，签字后即行生效。</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　甲方(公章)：_________ &nbsp; &nbsp; &nbsp; &nbsp;乙方(公章)：_________</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　法定代表人(签字)：_________ &nbsp; &nbsp; 法定代表人(签字)：_________</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　_________年____月____日 &nbsp; &nbsp; &nbsp; _________年____月____日</span>\r\n</p>\r\n<p>\r\n    &nbsp;\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>', NULL, NULL, NULL, 'contract', NULL, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (4, '合同4.docx', '1234.docx', NULL, '<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\"><br/> </span><span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">出租方(甲方)： 身份证号码：</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　承租方(乙方)： 身份证号码：</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　甲、乙双方通过友好协商，就档口租赁事宜达成协议如下：</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　一、租赁地址：</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　二、租用期限及其约定：</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　1.租用期限：自年月日起年月日;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　2.档口租金：每月元人民币;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　3.付款方式：按月支付，另付押金元，租房终止，甲方鸡蛋无误后，将押金退还乙方，不计利息;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　4.租赁期内的水、电、煤气、卫生治安费由乙方支付;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　5.租用期内，乙方有下列情形之一的甲方可终止合同，收回档口使用权，乙方需承担全部责任，并赔偿甲方损失。</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　(1)乙方擅自将档口转租，转让或转借的;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　(2)乙方利用承租档口进行非法活动损害公共利益的;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　(3)乙方无故拖欠档口租金达天;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　(4)连续3个月不付所有费用的。</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　四、双方责任及义务：</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　1.乙方须按时交纳水、电、煤、电话等费用，并务必将以上费用帐单交给甲方，甲方须监督检查以上费用。</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　2.无论在任何情况下，乙方都不能将押金转换为档口租金;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　3.租用期满后，乙方如需继续使用，应提前1个月提出，甲方可根据实际情况，在同等条件下给予优先;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　4.在租赁期内，甲乙双方如有一方有特殊情况需解除协议的，必须提前一个月通知对方，协商后解除本协议;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　5.乙方入住该物业应保持周围环境整洁做好防火防盗工作，如发生事故乙方应负全部责任;</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　6.乙方不得擅自改变室内结构，并爱惜使用室内敲诈，若人为损坏的将给予甲方相应赔偿;如发生自然损坏，应及时通知甲方，并配合甲方及时给予修复。</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　五、其它未尽事宜，由甲乙双方协商解决，协商不成按有关现行法规办理或提交有关仲裁机关进行仲裁。本协议一式两份，甲、乙双主各执一份，签字后即行生效。</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　甲方(公章)：_________ &nbsp; &nbsp; &nbsp; &nbsp;乙方(公章)：_________</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　法定代表人(签字)：_________ &nbsp; &nbsp; 法定代表人(签字)：_________</span>\r\n</p>\r\n<p style=\"text-align:left;line-height:30px\">\r\n    <span style=\"font-size:16px;font-family:&#39;微软雅黑&#39;,sans-serif;color:#333333\">　　_________年____月____日 &nbsp; &nbsp; &nbsp; _________年____月____日</span>\r\n</p>\r\n<p>\r\n    &nbsp;\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>', NULL, NULL, NULL, 'contract', NULL, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1101100756216558, '2ae2a32fcdac5cafaf5f537d382f50f4.jpg', '202101100051030d1df24131aabe752724563e71.jpg', NULL, '', '.jpg', 181659, 'image/jpeg', NULL, '/static/files/sys_head_img', NULL, 1, '2021-01-10 00:51:04', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1101100976277714, '6a688d37a1d107ea46c87def5f515dc14691a129_raw.jpg', '20210110005103491d304a1facd27eaedac8360c.jpg', NULL, NULL, '.jpg', 519229, 'image/jpeg', NULL, '/static/files/sys_head_img', NULL, 1, '2021-01-10 00:51:04', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL COMMENT '角色id',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `role_ZH` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `updator_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `creator_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', '系统管理员', '1', NULL, '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (2, 'common', '普通用户', '2', NULL, '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (3, 'employee', '员工', '3', '公司员工', '1', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_api`;
CREATE TABLE `sys_role_api`  (
  `role_id` bigint NOT NULL,
  `api_id` bigint NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_api
-- ----------------------------
INSERT INTO `sys_role_api` VALUES (1, 3);
INSERT INTO `sys_role_api` VALUES (1, 2);
INSERT INTO `sys_role_api` VALUES (2, 3);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 3);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户编号',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1101011673114033, 1);
INSERT INTO `sys_user_role` VALUES (1101011673114033, 2);
INSERT INTO `sys_user_role` VALUES (1101020356922407, 2);
INSERT INTO `sys_user_role` VALUES (1101021813661465, 2);
INSERT INTO `sys_user_role` VALUES (1101120406450508, 1);
INSERT INTO `sys_user_role` VALUES (1101121505827021, 1);
INSERT INTO `sys_user_role` VALUES (1101121586259532, 1);
INSERT INTO `sys_user_role` VALUES (1101121631152492, 1);
INSERT INTO `sys_user_role` VALUES (1101011673114033, 3);
INSERT INTO `sys_user_role` VALUES (1101020356922407, 3);
INSERT INTO `sys_user_role` VALUES (1101021813661465, 3);
INSERT INTO `sys_user_role` VALUES (1101110410767877, 3);
INSERT INTO `sys_user_role` VALUES (1101120406450508, 3);
INSERT INTO `sys_user_role` VALUES (1101121505827021, 3);
INSERT INTO `sys_user_role` VALUES (1101121586259532, 3);
INSERT INTO `sys_user_role` VALUES (1101121603322469, 3);
INSERT INTO `sys_user_role` VALUES (1101121631152492, 3);

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users`  (
  `id` bigint NOT NULL,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号',
  `nickname` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` int NULL DEFAULT NULL COMMENT '性别',
  `addr` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住址',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `id_card` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `reputation_score` int NULL DEFAULT NULL COMMENT '信誉分数',
  `flag` int NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES (1101011673114033, '23233', 'htl', '$2a$10$CZjO.etRLGvRKGkqIjX5R.XtZ2bstjobnxIqjBD23br.QJuEvkSCe', 1, '重庆市', '12345678911', '500229199905138990', NULL, 1, '2021-01-01 09:57:11', NULL, NULL);
INSERT INTO `sys_users` VALUES (1101020356922407, 'admin', 'htl', '$2a$10$270Rr.d4SA/YGLO8yY.wJ.5FpbEOKVthvSTUAJyITVZUDkJrY7e/m', 1, '重庆市', '12345678911', '500229199905138990', NULL, 1, '2021-01-02 09:12:09', NULL, NULL);
INSERT INTO `sys_users` VALUES (1101021813661465, '1111', 'htl', '$2a$10$Kh1M8EJN.AMM.2Lrbs/wkeM.0gddhm6K0kYiuBIeywsr8H0XPP5mC', 2, '重庆市', '12345678911', '500229199905138990', NULL, 1, '2021-01-02 08:30:32', NULL, NULL);
INSERT INTO `sys_users` VALUES (1101110410767877, 'admin2', '张三', '1', 2, '重庆市', '12345678911', '500229199905138990', NULL, 1, '2021-01-11 23:43:32', NULL, '');
INSERT INTO `sys_users` VALUES (1101120406450508, 'admin12', '张三', '$2a$10$I25D1tTx3qJcccXH0MDBiO85.jmK6/TggXnal4pkTywarcLecccDe', 2, '重庆市', '12345678911', '500229199905138990', NULL, 1, '2021-01-12 00:16:06', NULL, '');
INSERT INTO `sys_users` VALUES (1101121505827021, 'admin223', '张三', '$2a$10$gcU52m62qI/pUg/HOA8Z/eWApuIOTuBtvnim5md6Th5sD7K2uOp2u', 1, '重庆市', '12345678911', '500229199905138990', NULL, 1, '2021-01-12 00:22:01', NULL, '');
INSERT INTO `sys_users` VALUES (1101121586259532, 'admin122', '张三', '$2a$10$4TtieI9sxah6Ydw9JYMWMu1wE9q26zINVJ5X04AbG2pT.uyUfKt..', 1, '重庆市', '12345678911', '500229199905138990', NULL, 1, '2021-01-12 00:22:57', NULL, '');
INSERT INTO `sys_users` VALUES (1101121603322469, 'admin1', '张三', '$2a$10$AOHZKezvNRXQ5I4VRAJWyuZwbfvnujDAa6djiaQl7xHBOY1TdeyaO', 2, '重庆市', '12345678911', '500229199905138990', NULL, 1, '2021-01-12 00:00:54', NULL, '');
INSERT INTO `sys_users` VALUES (1101121631152492, 'admin556', '张三', '$2a$10$3se6RRG0EBkDxr9fjDmjTexrWvQYBH5rlrFeTC8m7rWrUnPIUsYM.', 2, '重庆市', '12345678911', '500229199905138990', NULL, 1, '2021-01-12 00:23:28', NULL, '');

-- ----------------------------
-- Table structure for tbl_flow_line
-- ----------------------------
DROP TABLE IF EXISTS `tbl_flow_line`;
CREATE TABLE `tbl_flow_line`  (
  `flow_line_id` bigint NOT NULL COMMENT '流程线编号，主键',
  `flow_no` bigint NULL DEFAULT NULL COMMENT '流程号，与流程表对应',
  `prev_node_id` bigint NULL DEFAULT NULL COMMENT '前一节点编号',
  `next_node_id` bigint NULL DEFAULT NULL COMMENT '后一节点编号',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`flow_line_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程线表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_flow_line
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_flow_node
-- ----------------------------
DROP TABLE IF EXISTS `tbl_flow_node`;
CREATE TABLE `tbl_flow_node`  (
  `flow_node_id` bigint NOT NULL COMMENT '流程节点编号，主键',
  `flow_no` bigint NULL DEFAULT NULL COMMENT '流程号，与流程表对应',
  `flow_node_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程节点名称',
  `flow_node_role` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程角色',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`flow_node_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程节点表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_flow_node
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_flow_role_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_flow_role_user`;
CREATE TABLE `tbl_flow_role_user`  (
  `id` bigint NOT NULL COMMENT '主键',
  `flow_role_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程角色名称',
  `user_id` bigint NULL DEFAULT NULL COMMENT '员工编号',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门编号',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程角色_员工表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_flow_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_leave_audit
-- ----------------------------
DROP TABLE IF EXISTS `tbl_leave_audit`;
CREATE TABLE `tbl_leave_audit`  (
  `audit_id` bigint NOT NULL COMMENT '审批编号，主键',
  `leave_id` bigint NULL DEFAULT NULL COMMENT '合同编号，与请假表对应',
  `flow_node_id` bigint NULL DEFAULT NULL COMMENT '节点编号',
  `user_id` bigint NULL DEFAULT NULL COMMENT '审批人编号',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批人姓名',
  `audit_info` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批意见',
  `audit_date` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批日期',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`audit_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审批表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_leave_audit
-- ----------------------------

-- ----------------------------
-- Table structure for user_contract
-- ----------------------------
DROP TABLE IF EXISTS `user_contract`;
CREATE TABLE `user_contract`  (
  `id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户编号',
  `vehicle_number` bigint NULL DEFAULT NULL COMMENT '租车车辆编号',
  `contract_id` bigint NULL DEFAULT NULL COMMENT '合同编号',
  `flow_no` bigint NULL DEFAULT NULL COMMENT '流程号',
  `status` int NULL DEFAULT NULL COMMENT '合同状态',
  `current_node` bigint NULL DEFAULT NULL COMMENT '当前节点编号',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户车辆合同管理表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_contract
-- ----------------------------

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle`  (
  `id` bigint NOT NULL COMMENT '编号',
  `vehicle_number` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆编号',
  `vehicle_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆名',
  `vehicle_type` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆类型',
  `pic` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆照片',
  `is_accident` int NULL DEFAULT NULL COMMENT '是否出过事故',
  `is_rent_out` int NULL DEFAULT NULL COMMENT '是否出租',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of vehicle
-- ----------------------------

-- ----------------------------
-- Table structure for vehicle_type
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_type`;
CREATE TABLE `vehicle_type`  (
  `id` bigint NOT NULL COMMENT '编号',
  `vehicle_no` bigint NULL DEFAULT NULL COMMENT '车辆类型编号',
  `vehicle_type_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `inventory` int NULL DEFAULT NULL COMMENT '库存',
  `rent_out_number` int NULL DEFAULT NULL COMMENT '出租数量',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of vehicle_type
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
