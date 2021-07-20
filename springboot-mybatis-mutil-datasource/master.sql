DROP TABLE IF EXISTS `city`;
CREATE TABLE user
(
id INT(10) unsigned PRIMARY KEY NOT NULL COMMENT '用户编号' AUTO_INCREMENT,
user_name VARCHAR(25) COMMENT '用户名称',
description VARCHAR(25) COMMENT '描述',
user_city_id INT(10) COMMENT '对应城市编号'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT user VALUES (1 ,'四无君','平风造雨四无君','2');
INSERT user VALUES (2 ,'剑君','剑君十二恨','1');