DROP TABLE IF EXISTS `purchase`;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `address`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `user_profile`;

CREATE TABLE `user_profile` (
  `user_profile_id` bigint(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `email_address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `product` (
  `product_id` bigint(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `brand` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `address` (
  `address_id` bigint(11) NOT NULL,
  `user_profile_id` bigint(11) NOT NULL,
  `address_name` varchar(45) DEFAULT NULL,
  `city` varchar(45) NOT NULL,
  `postcode` varchar(45) NOT NULL,
  `address_line_1` varchar(250) NOT NULL,
  `address_line_2` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `address_user_profile_fk_idx` (`user_profile_id`),
  CONSTRAINT `address_user_profile_fk` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`user_profile_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `user_id` bigint(11) NOT NULL,
  `user_profile_id` bigint(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `user_user_profile_fk_idx` (`user_profile_id`),
  CONSTRAINT `user_user_profile_fk` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`user_profile_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order` (
  `order_id` bigint(11) NOT NULL,
  `user_id` bigint(11) NOT NULL,
  `address_id` bigint(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `order_user_fk_idx` (`user_id`),
  KEY `order_addres_fk_idx` (`address_id`),
  CONSTRAINT `order_addres_fk` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `order_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `purchase` (
  `purchase_id` bigint(11) NOT NULL,
  `user_id` bigint(11) DEFAULT NULL,
  `product_id` bigint(11) NOT NULL,
  `order_id` bigint(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`purchase_id`),
  KEY `purchase_user_fk_idx` (`user_id`),
  KEY `purchase_product_fk_idx` (`product_id`),
  KEY `purchase_order_fk_idx` (`order_id`),
  CONSTRAINT `purchase_order_fk` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `purchase_product_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `purchase_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
