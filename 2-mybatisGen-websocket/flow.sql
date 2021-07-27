DROP TABLE IF EXISTS `flow1`;
CREATE TABLE `flow1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `speed` varchar(32) DEFAULT NULL COMMENT '参数1',
  `create_time` varchar(15) DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `flow2`;
CREATE TABLE `flow2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `speed` varchar(32) DEFAULT NULL COMMENT '参数1',
  `create_time` varchar(15) DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `flow2` VALUES (1,'13.5',default);
INSERT INTO `flow2` VALUES (2,'18.9',default);
INSERT INTO `flow2` VALUES (3,'11.6',default);