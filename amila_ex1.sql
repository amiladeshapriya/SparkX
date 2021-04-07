-- MySQL Script generated by MySQL Workbench
-- Sun Apr  4 13:45:20 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ex1_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ex1_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ex1_db` DEFAULT CHARACTER SET utf8 ;
USE `ex1_db` ;

-- -----------------------------------------------------
-- Table `ex1_db`.`owner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ex1_db`.`owner` (
  `owner_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `street_address` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `state_full` VARCHAR(45) NOT NULL,
  `zip_code` INT NOT NULL,
  PRIMARY KEY (`owner_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ex1_db`.`pet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ex1_db`.`pet` (
  `pet_id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `kind` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `owner_id` INT NOT NULL,
  PRIMARY KEY (`pet_id`),
  INDEX `owner_id_idx` (`owner_id` ASC) VISIBLE,
  CONSTRAINT `owner_id`
    FOREIGN KEY (`owner_id`)
    REFERENCES `ex1_db`.`owner` (`owner_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ex1_db`.`procedure_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ex1_db`.`procedure_detail` (
  `procedure_type` VARCHAR(45) NOT NULL,
  `procedure_subcode` INT NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `price` DECIMAL(45) NOT NULL,
  PRIMARY KEY (`procedure_type`, `procedure_subcode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ex1_db`.`procedure_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ex1_db`.`procedure_history` (
  `pet_id` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `procedure_type` VARCHAR(45) NOT NULL,
  `procedure_subcode` INT NOT NULL,
  INDEX `procedure_type_idx` (`procedure_type` ASC, `procedure_subcode` ASC) VISIBLE,
  INDEX `pet_id_idx` (`pet_id` ASC) VISIBLE,
  CONSTRAINT `pet_id`
    FOREIGN KEY (`pet_id`)
    REFERENCES `ex1_db`.`pet` (`pet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `procedure_type`
    FOREIGN KEY (`procedure_type` , `procedure_subcode`)
    REFERENCES `ex1_db`.`procedure_detail` (`procedure_type` , `procedure_subcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `procedure_subcode`
    FOREIGN KEY (`procedure_type` , `procedure_subcode`)
    REFERENCES `ex1_db`.`procedure_detail` (`procedure_type` , `procedure_subcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

/* Select all procedure types where the price is above 150 */
SELECT procedure_type FROM ex1_db.procedure_detail WHERE price>150;

/*Find all owners who have a parrot as a pet*/
SELECT o.name FROM ex1_db.pet p JOIN owner o on p.owner_id=o.owner_id WHERE p.kind='Parrot';

/*Find the number of owners who have the same postal code (i.e 10 from 49503, 1 from 48423)*/
SELECT count(owner_id),'from',zip_code FROM ex1_db.owner group by zip_code;

/*Find all pets having gone through a procedure during the month of February 2016*/
SELECT pet_id FROM ex1_db.procedure_history where date BETWEEN '2016-02-01' AND '2016-02-29' group by pet_id;

/*Find the total cost of procedures incurred during the month of March of owners from the postal code 49503*/
SELECT SUM(price) FROM ex1_db.procedure_history ph JOIN procedure_detail pd on ph.procedure_type=pd.procedure_type AND ph.procedure_subcode=pd.procedure_subcode JOIN pet p on ph.pet_id=p.pet_id JOIN owner o on p.owner_id=o.owner_id WHERE date BETWEEN '2016-03-01' AND '2016-03-31' AND o.zip_code=49503;