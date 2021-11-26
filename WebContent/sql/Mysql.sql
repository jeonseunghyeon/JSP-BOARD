CREATE SCHEMA `jsp_web_commu` 
CREATE TABLE `jsp_web_commu`.`member` (
  `u_NUM` INT NOT NULL,
  `u_ID` VARCHAR(10) NOT NULL,
  `u_Password` VARCHAR(12) NOT NULL,
  `u_name` VARCHAR(10) NOT NULL
  PRIMARY KEY (`u_NUM`),
  UNIQUE INDEX `u_ID_UNIQUE` (`u_ID` ASC),(`u_Name` ASC)VISIBLE);