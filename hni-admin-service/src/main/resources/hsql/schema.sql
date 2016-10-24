SET MODE MySQL;

-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NULL,
  `last_name` VARCHAR(255) NULL,
  `gender_code` VARCHAR(1) NULL,
  `mobile_phone` VARCHAR(45) NULL,
  `email` VARCHAR(255) NULL,
  `deleted` INT NULL,
  `password` VARCHAR(255) NULL,
  `salt` VARCHAR(255) NULL,
  `created` DATETIME NULL,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `organizations`
-- -----------------------------------------------------
CREATE TABLE `organizations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `created` DATETIME NULL,
  `created_by` INT NULL,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE `role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_organization_role`
-- -----------------------------------------------------
CREATE TABLE `user_organization_role` (
  `user_id` INT NOT NULL,
  `organization_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `organization_id`, `role_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `providers`
-- -----------------------------------------------------
CREATE TABLE `providers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `created` DATETIME NOT NULL,
  `created_by` INT NOT NULL,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `provider_locations`
-- -----------------------------------------------------
CREATE TABLE `provider_locations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `provider_id` INT NOT NULL,
  `hours` VARCHAR(45) NULL,
  `created` VARCHAR(45) NOT NULL,
  `created_by` INT NOT NULL,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `customer_orders`
-- -----------------------------------------------------
CREATE TABLE `customer_orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `provider_location_id` INT NOT NULL,
  `order_date` DATETIME NOT NULL,
  `ready_date` DATETIME NULL,
  `pickup_date` DATETIME NULL,
  `sub_total` DECIMAL(10,2) NULL,
  `tax` DECIMAL(10,2) NULL,
  `total_amount` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menus`
-- -----------------------------------------------------
CREATE TABLE `menus` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `provider_id` INT NOT NULL,
  `start_hour_avail` INT NULL,
  `end_hour_avail` INT NULL,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu_items`
-- -----------------------------------------------------
CREATE TABLE `menu_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `menu_id` INT NOT NULL,
  `name` VARCHAR(255) NULL,
  `description` TEXT NULL,
  `price` DECIMAL(10,2) NULL,
  `expires` DATETIME NULL,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `customer_order_items`
-- -----------------------------------------------------
CREATE TABLE `customer_order_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_order_id` INT NOT NULL,
  `menu_item_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `amount` DECIMAL(10,2) NULL,
  `sub_total` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `addresses`
-- -----------------------------------------------------
CREATE TABLE `addresses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `address_line1` VARCHAR(255) NULL,
  `address_line2` VARCHAR(255) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(2) NULL,
  `zip` VARCHAR(15) NULL,
  `longitude` VARCHAR(45) NULL,
  `latitude` VARCHAR(45) NULL,
  `timezone` VARCHAR(45) NULL,
  `ref_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `organization_tokens`
-- -----------------------------------------------------
CREATE TABLE `organization_tokens` (
  `token` VARCHAR(255) NOT NULL,
  `organization_id` INT NOT NULL,
  `created` VARCHAR(45) NULL,
  PRIMARY KEY (`token`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_provider_role`
-- -----------------------------------------------------
CREATE TABLE `user_provider_role` (
  `user_id` INT NOT NULL,
  `provider_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `provider_id`, `role_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `provider_addresses`
-- -----------------------------------------------------
CREATE TABLE `provider_addresses` (
  `provider_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`provider_id`, `address_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `provider_location_addresses`
-- -----------------------------------------------------
CREATE TABLE `provider_location_addresses` (
  `provider_location_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`provider_location_id`, `address_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `organization_addresses`
-- -----------------------------------------------------
CREATE TABLE `organization_addresses` (
  `organization_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`organization_id`, `address_id`) )
ENGINE = InnoDB;
