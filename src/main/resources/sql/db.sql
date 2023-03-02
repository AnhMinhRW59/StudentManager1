drop database if exists shoppee;
create database shoppee;
use shoppee;
drop table  if exists`Account`;
CREATE TABLE Account (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         username VARCHAR(50) not null unique,
                         role ENUM('CUSTOMER', 'SELLER', 'ADMIN'),
                         password VARCHAR(50) not null,
                         date_of_birth DATE,
                         address VARCHAR(255),
                         full_name VARCHAR(50),
                         phone_number VARCHAR(12) not null,
                         email VARCHAR(50),
                         facebook VARCHAR(255),
                         information VARCHAR(255)
);

drop table if exists Product;
CREATE TABLE Product (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         `name` VARCHAR(255) not  null unique,
                         image VARCHAR(255) not null,
                         price INT,
                         status ENUM('NEW', 'OLD') not null,
                         shipping_unit ENUM('EXPRESS', 'FAST', 'SAVE') not null,
                         type ENUM('PHONE', 'COMPUTER', 'CLOTHES', 'FOOTWEAR') not null,
                         create_date DATE
);

drop table if exists `Order`;
CREATE TABLE `Order` (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         order_date DATE,
                         order_by INT not null,
                         product_id INT not null,
                         quantity INT,
                         status ENUM('PENDING', 'DONE', 'CANCEL'),
                         FOREIGN KEY (order_by)
                             REFERENCES Account (id) on delete cascade,
                         FOREIGN KEY (product_id)
                             REFERENCES Product (id) on delete cascade
);

CREATE TABLE token (
                       id int unsigned NOT NULL AUTO_INCREMENT,
                       token varchar(500) NOT NULL,
                       user_agent varchar(500) NOT NULL,
                       is_black_list tinyint(1) DEFAULT NULL,
                       refresh_time datetime DEFAULT NULL,
                       expiration datetime DEFAULT NULL,
                       PRIMARY KEY (id),
                       UNIQUE KEY token (token)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
