CREATE SCHEMA `jsp_web_commu` 
CREATE TABLE `jsp_web_commu`.`member` (
  `u_NUM` INT NOT NULL AUTO_INCREMENT,
  `u_Id` VARCHAR(12) NULL,
  `u_Password` VARCHAR(20) NULL,
  `u_name` VARCHAR(10) NULL,
  PRIMARY KEY (`u_NUM`));
