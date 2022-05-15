USE `jpaDb`;

ALTER TABLE `customer` AUTO_INCREMENT=1;
INSERT INTO `customer` (`seq`, `created_date`, `enabled`, `password`, `token_expired`, `update_date`, `user_name`) VALUES ('1', '2022-04-16','1','$2a$10$UgZ.HsR5/OoNJde8mwEbYOnSxs7oFKT1h76dRRDvprKw56WlluyrS',0,'2022-04-16','user1');
INSERT INTO `customer` (`seq`, `created_date`, `enabled`, `password`, `token_expired`, `update_date`, `user_name`) VALUES ('2', '2022-04-16','1','$2a$10$UgZ.HsR5/OoNJde8mwEbYOnSxs7oFKT1h76dRRDvprKw56WlluyrS',0,'2022-04-16','user2');
INSERT INTO `customer` (`seq`, `created_date`, `enabled`, `password`, `token_expired`, `update_date`, `user_name`) VALUES ('3', '2022-04-16','1','$2a$10$UgZ.HsR5/OoNJde8mwEbYOnSxs7oFKT1h76dRRDvprKw56WlluyrS',0,'2022-04-16','user3');
ALTER TABLE `customer` AUTO_INCREMENT=4;

/*新增角色*/
ALTER TABLE `role` AUTO_INCREMENT=1;
INSERT INTO `role` (`seq`, `created_date`, `description`, `role_name`, `update_date`) VALUES ('1', '2022-04-16', '管理員', 'admin', '2022-04-16');
INSERT INTO `role` (`seq`, `created_date`, `description`, `role_name`, `update_date`) VALUES ('2', '2022-04-16', '使用者', 'user', '2022-04-16');
ALTER TABLE `role` AUTO_INCREMENT=3;

/*設定使用者角色*/
ALTER TABLE `customer_role` AUTO_INCREMENT=1;
INSERT INTO `customer_role` (`seq`, `customer_seq`, `role_seq`) VALUES ('1', '1', '1');
INSERT INTO `customer_role` (`seq`, `customer_seq`, `role_seq`) VALUES ('2', '2', '2');
ALTER TABLE `customer_role` AUTO_INCREMENT=3;

/* 設定商品*/
ALTER TABLE `item` AUTO_INCREMENT=1;
INSERT INTO `item` (`created_date`, `iname`, `price`, `type`, `update_date`) VALUES ('2022/04/30', '商品A1', '80', '1', '2022/04/30');
INSERT INTO `item` (`created_date`, `iname`, `price`, `type`, `update_date`) VALUES ('2022/04/30', '商品A2', '150', '1', '2022/04/30');
INSERT INTO `item` (`created_date`, `iname`, `price`, `type`, `update_date`) VALUES ('2022/04/30', '商品B1', '95', '2', '2022/04/30');
INSERT INTO `item` (`created_date`, `iname`, `price`, `type`, `update_date`) VALUES ('2022/04/30', '商品B2', '200', '2', '2022/04/30');
INSERT INTO `item` (`created_date`, `iname`, `price`, `type`, `update_date`) VALUES ('2022/04/30', '商品C1', '250', '3', '2022/04/30');
INSERT INTO `item` (`created_date`, `iname`, `price`, `type`, `update_date`) VALUES ('2022/04/30', '商品C2', '300', '3', '2022/04/30');

/* 設定訂單*/
ALTER TABLE `orders` AUTO_INCREMENT=1;
INSERT INTO `orders` (`created_date`, `update_date`, `cid`) VALUES ('2022/04/30', '2022/04/30', '2');
INSERT INTO `orders` (`created_date`, `update_date`, `cid`) VALUES ('2022/04/30', '2022/04/30', '3');
INSERT INTO `orders` (`created_date`, `update_date`, `cid`) VALUES ('2022/04/30', '2022/04/30', '2');
INSERT INTO `orders` (`created_date`, `update_date`, `cid`) VALUES ('2022/04/30', '2022/04/30', '3');

/*設定訂單明細*/
ALTER TABLE `order_detail` AUTO_INCREMENT=1;
INSERT INTO `order_detail` (`qty`, `iid`, `oid`) VALUES ('2', '1', '1');
INSERT INTO `order_detail` (`qty`, `iid`, `oid`) VALUES ('4', '2', '1');
INSERT INTO `order_detail` (`qty`, `iid`, `oid`) VALUES ('6', '3', '1');
INSERT INTO `order_detail` (`qty`, `iid`, `oid`) VALUES ('3', '4', '2');
INSERT INTO `order_detail` (`qty`, `iid`, `oid`) VALUES ('11', '5', '2');
INSERT INTO `order_detail` (`qty`, `iid`, `oid`) VALUES ('6', '1', '3');
INSERT INTO `order_detail` (`qty`, `iid`, `oid`) VALUES ('15', '2', '4');
INSERT INTO `order_detail` (`qty`, `iid`, `oid`) VALUES ('7', '3', '4');
