-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema BookStore_new
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BookStore_new
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BookStore_new` ;
USE `BookStore_new` ;

-- -----------------------------------------------------
-- Table `BookStore_new`.`Book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BookStore_new`.`Book` (
  `ISBN` VARCHAR(13) NOT NULL,
  `title` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`ISBN`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `BookStore_new`.`Feature`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BookStore_new`.`Feature` (
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `BookStore_new`.`Book_Has_Features`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BookStore_new`.`Book_Has_Features` (
  `Book_ISBN` VARCHAR(13) NOT NULL,
  `Feature_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Book_ISBN`, `Feature_name`),
  INDEX `fk_Book_Has_Features_Feature1_idx` (`Feature_name` ASC) VISIBLE,
  CONSTRAINT `fk_Book_Has_Features_Book1`
    FOREIGN KEY (`Book_ISBN`)
    REFERENCES `BookStore_new`.`Book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Book_Has_Features_Feature1`
    FOREIGN KEY (`Feature_name`)
    REFERENCES `BookStore_new`.`Feature` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `BookStore_new`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BookStore_new`.`Customer` (
  `Name` VARCHAR(50) NOT NULL,
  `Mobile` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`Name`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `BookStore_new`.`Customer_Interests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BookStore_new`.`Customer_Interests` (
  `Customer_Name` VARCHAR(50) NOT NULL,
  `Feature_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Customer_Name`, `Feature_name`),
  INDEX `fk_Customer_Interests_Feature1_idx` (`Feature_name` ASC) VISIBLE,
  CONSTRAINT `fk_Customer_Interests_Customer1`
    FOREIGN KEY (`Customer_Name`)
    REFERENCES `BookStore_new`.`Customer` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_Interests_Feature1`
    FOREIGN KEY (`Feature_name`)
    REFERENCES `BookStore_new`.`Feature` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `BookStore_new`.`Customer_Purchase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BookStore_new`.`Customer_Purchase` (
  `Customer_Name` VARCHAR(50) NOT NULL,
  `Book_ISBN` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`Customer_Name`, `Book_ISBN`),
  INDEX `fk_Customer_Purchase_Book1_idx` (`Book_ISBN` ASC) VISIBLE,
  CONSTRAINT `fk_Customer_Purchase_Customer1`
    FOREIGN KEY (`Customer_Name`)
    REFERENCES `BookStore_new`.`Customer` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_Purchase_Book1`
    FOREIGN KEY (`Book_ISBN`)
    REFERENCES `BookStore_new`.`Book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;