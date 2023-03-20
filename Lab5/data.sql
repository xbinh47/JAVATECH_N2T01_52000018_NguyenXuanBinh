SET NAMES utf8mb4 COLLATE utf8mb4_unicode_ci;

SET NAMES utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS product_management;

USE product_management;

CREATE TABLE IF NOT EXISTS user (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS product (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  price DECIMAL(10,2) NOT NULL
) ENGINE=InnoDB;

INSERT INTO user (name, email, password) VALUES
  ('admin', 'admin@gmail.com', '123456'),
  ('user', 'user@gmail.com', '123456');

INSERT INTO product (name, price) VALUES
  ('Macbook Air M1', 1100),
  ('Macbook Pro 2020', 2400);   