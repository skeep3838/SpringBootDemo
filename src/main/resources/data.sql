/*新增使用者*/
INSERT INTO `jpaDb`.`customer` VALUES (1,'2022-04-16','1','$2a$10$UgZ.HsR5/OoNJde8mwEbYOnSxs7oFKT1h76dRRDvprKw56WlluyrS',0,'2022-04-16','user1');
INSERT INTO `jpaDb`.`customer` VALUES (2,'2022-04-16','1','$2a$10$UgZ.HsR5/OoNJde8mwEbYOnSxs7oFKT1h76dRRDvprKw56WlluyrS',0,'2022-04-16','user2');
ALTER TABLE `jpaDb`.`customer` AUTO_INCREMENT=3;

/*新增角色*/
INSERT INTO `jpaDb`.`role` (`seq`, `created_date`, `description`, `role_name`, `update_date`) VALUES ('1', '2022-04-16', '管理員', 'admin', '2022-04-16');
INSERT INTO `jpaDb`.`role` (`seq`, `created_date`, `description`, `role_name`, `update_date`) VALUES ('2', '2022-04-16', '使用者', 'user', '2022-04-16');
ALTER TABLE `jpaDb`.`role` AUTO_INCREMENT=3;

/*設定使用者角色*/
INSERT INTO `jpaDb`.`customer_role` (`seq`, `customer_seq`, `role_seq`) VALUES ('1', '1', '1');
INSERT INTO `jpaDb`.`customer_role` (`seq`, `customer_seq`, `role_seq`) VALUES ('2', '2', '2');
ALTER TABLE `jpaDb`.`customer_role` AUTO_INCREMENT=3;