CREATE SCHEMA `jsp_web_commu` 
CREATE TABLE `jsp_web_commu`.`member` (
  `MemberNUM` INT NOT NULL,
  `MemberID` VARCHAR(10) NOT NULL,
  `MemberPassword` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`MemberNUM`),
  UNIQUE INDEX `MemberID_UNIQUE` (`MemberID` ASC) VISIBLE);
