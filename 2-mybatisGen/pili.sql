DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(32) DEFAULT NULL COMMENT '角色名',
  `user_local` varchar(32) DEFAULT NULL COMMENT '根据地',
  `user_sex` varchar(32) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
`show_name` varchar(32) DEFAULT NULL COMMENT '剧集名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `person` VALUES (1,'吞佛','异度魔界','男','心机吞','剑踪');
INSERT INTO `person` VALUES (2,'宵','十二巅','男','宵宝','刀戟');
INSERT INTO `person` VALUES (3,'苍','玄宗','男','葱花','奇象');

DROP TABLE IF EXISTS `show`;
CREATE TABLE `show` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `show_name` varchar(32) DEFAULT NULL COMMENT '剧集名',
  `show_year` varchar(32) DEFAULT NULL COMMENT '播放时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `show` VALUES (1,'剑踪','2005');
INSERT INTO `show` VALUES (2,'刀戟','2005');
INSERT INTO `show` VALUES (3,'奇象','2006');