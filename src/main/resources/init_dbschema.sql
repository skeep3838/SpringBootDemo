USE `jpaDb`;

/*客戶資訊*/
CREATE TABLE `customer` (
  `seq` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `enabled` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `token_expired` bit(1) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `UK_qfrm64q1g4do60ini1wv5crno` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*角色資訊*/
CREATE TABLE `role` (
  `seq` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `UK_iubw515ff0ugtm28p8g3myt0h` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*功能資訊*/
CREATE TABLE `functions` (
  `seq` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `function_name` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `UK_ewipsirlkmqtg24kn71cylalf` (`description`),
  UNIQUE KEY `UK_qgm2en903xk3dpg0fqshapot4` (`function_name`),
  UNIQUE KEY `UK_eiw3cn8obtjo5lvuyd45pbds8` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*商品資訊*/
CREATE TABLE `item` (
  `seq` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `iname` varchar(255) NOT NULL,
  `price` int NOT NULL,
  `type` varchar(255) NOT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `UK_r4nrsxj4pg3dyh3sdm3lqqv89` (`iname`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*-----------------------------------------------------------------------------------*/
/*角色通能權限*/
CREATE TABLE `role_function` (
  `rid` int NOT NULL,
  `fid` int NOT NULL,
  KEY `FKlw49ec271anqsnk6kt7rys5gf` (`fid`),
  KEY `FK37syffpjda753b24775ogvkwq` (`rid`),
  CONSTRAINT `FK37syffpjda753b24775ogvkwq` FOREIGN KEY (`rid`) REFERENCES `role` (`seq`),
  CONSTRAINT `FKlw49ec271anqsnk6kt7rys5gf` FOREIGN KEY (`fid`) REFERENCES `functions` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*帳號角色設定*/
CREATE TABLE `customer_role` (
  `seq` int NOT NULL,
  `customer_seq` int NOT NULL AUTO_INCREMENT,
  `role_seq` int NOT NULL,
  PRIMARY KEY (`customer_seq`,`role_seq`),
  UNIQUE KEY `UK_tc0waf5h183wcqlerphgmy2ax` (`seq`),
  KEY `FK65rgem2q4126gk2mgv7214isn` (`role_seq`),
  CONSTRAINT `FK65rgem2q4126gk2mgv7214isn` FOREIGN KEY (`role_seq`) REFERENCES `role` (`seq`),
  CONSTRAINT `FK85ymjc10pl46r551lpvl2abbe` FOREIGN KEY (`customer_seq`) REFERENCES `customer` (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*訂單資訊*/
CREATE TABLE `orders` (
  `seq` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `cid` int NOT NULL,
  PRIMARY KEY (`seq`),
  KEY `FKl7d2sjt1svwiocconhxffxfe0` (`cid`),
  CONSTRAINT `FKl7d2sjt1svwiocconhxffxfe0` FOREIGN KEY (`cid`) REFERENCES `customer` (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*訂單明細*/
CREATE TABLE `order_detail` (
  `seq` int NOT NULL AUTO_INCREMENT,
  `qty` int NOT NULL,
  `iid` int NOT NULL,
  `oid` int DEFAULT NULL,
  PRIMARY KEY (`seq`),
  KEY `FK4k0rrgfp2el1xp4f8sfyuxmwa` (`iid`),
  KEY `FKm6an0aai7muf3tij80e3e6jr5` (`oid`),
  CONSTRAINT `FK4k0rrgfp2el1xp4f8sfyuxmwa` FOREIGN KEY (`iid`) REFERENCES `item` (`seq`),
  CONSTRAINT `FKm6an0aai7muf3tij80e3e6jr5` FOREIGN KEY (`oid`) REFERENCES `orders` (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
