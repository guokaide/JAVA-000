# 学习笔记

## 作业1

> 基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。

解答：主要分为四个表：用户表`users`、商品表`products`、订单表`orders` 以及订单产品表`orderitems`，SQL 如下：

```mysql
########################
# Create users table
########################
CREATE TABLE users
(
  user_id int NOT NULL AUTO_INCREMENT,
  user_name char(50) NOT NULL,
  user_phone char(50) NULL,
  user_address char(50) NULL,
  user_zip char(10) NULL,
  PRIMARY KEY (user_id)
) ENGINE=InnoDB;

########################
# Create products table
########################
CREATE TABLE products
(
  prod_id int NOT NULL AUTO_INCREMENT,
  prod_name char(255) NOT NULL,
  prod_desc varchar(255) NULL,
  expire_time bigint NULL,
  PRIMARY KEY (prod_id)
) ENGINE=InnoDB;

########################
# Create orders table
########################
CREATE TABLE orders
(
  order_id int NOT NULL AUTO_INCREMENT,
  user_id int NOT NULL,
  order_date datetime NOT NULL,
  PRIMARY KEY (order_id)
) ENGINE=InnoDB;

##########################
# Create orderitems table
##########################
CREATE TABLE orderitems
(
  order_id int NOT NULL,
  prod_id int NOT NULL,
  prod_quantity int NOT NULL,
  prod_price decimal(8, 2) NOT NULL,
  PRIMARY KEY (order_id, prod_id)
) ENGINE=InnoDB;

```



