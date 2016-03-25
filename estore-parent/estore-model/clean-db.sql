DELETE FROM `purchase` WHERE purchase_id >= 0;
DELETE FROM `order` WHERE order_id >= 0;
DELETE FROM `user` WHERE user_id >= 0;
DELETE FROM `address` WHERE address_id >= 0;
DELETE FROM `product` WHERE product_id >= 0;
DELETE FROM `user_profile` WHERE user_profile_id >= 0;

INSERT INTO `product` (product_id, name, brand, description, price, stock) 
	VALUES (0, 'prod0', 'brand0', 'prod0 desc', 2.34, 14);
INSERT INTO `user_profile` (user_profile_id, name, surname, phone_number, email_address)
	VALUES (0, 'name0', 'surname0', '000000000', 'email0@test.com');
INSERT INTO `user` (user_id, user_profile_id, username, password)
	VALUES (0, 0, 'user0', 'pass0');
INSERT INTO `address` (address_id, user_profile_id, address_name, city, postcode, address_line_1, address_line_2)
	VALUES (0, 0, 'addr name 0', 'city0', '00000', 'addr line 1_0', 'addr line 2_0');
INSERT INTO `order` (order_id, user_id, address_id)
	VALUES(0, 0, 0);
INSERT INTO `purchase` (purchase_id, user_id, product_id, order_id, quantity)
	VALUES(0, 0, 0, 0, 4);