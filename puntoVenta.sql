create database puntoVenta;

Drop database puntoVenta;
	
use puntoVenta;

create table user(
id int not null primary key auto_increment,
userName varchar(50) not null,
password varchar(50) not null,
role varchar(50) not null,
status varchar(50) not null
);

create table product(
id int not null auto_increment primary key,
product varchar(50) not null,
author varchar(50) not null,
cost decimal not null,
stock int not null
);

create table sale(
id int not null primary key auto_increment,
sellerName varchar(50) not null,
total decimal not null,
dateSale date not null
);

create table cashout(
id int not null primary key auto_increment,
userName varchar(50) not null,
totalEstimated decimal not null,
total decimal not null,
dateC date not null
);

INSERT INTO user (userName, password, role, status) VALUES
("Danna", "D1234", "SysAdmin", "Active"),
("Angel", "A1234", "Librarian", "Active");

drop table user;

INSERT INTO product (id, product, author, cost, stock) VALUES
(1, 'Libro2', 'Autor2', 25.99, 20),
(121, 'Libro3', 'Autor3', 19.50, 35),
(423, 'Libro4', 'Autor4', 32.75, 10),
(521, 'Libro5', 'Autor5', 15.00, 45),
(621, 'Libro6', 'Autor6', 28.49, 22),
(722, 'Libro7', 'Autor7', 12.95, 18),
(8, 'Libro8', 'Autor8', 40.00, 30),
(9, 'Libro9', 'Autor9', 22.80, 25),
(10, 'Libro10', 'Autor10', 18.75, 15),
(31, 'Libro31', 'Autor31', 27.50, 28),
(32, 'Libro32', 'Autor32', 14.99, 40),
(33, 'Libro33', 'Autor33', 36.25, 12),
(34, 'Libro34', 'Autor34', 19.95, 33),
(35, 'Libro35', 'Autor35', 30.80, 17),
(36, 'Libro36', 'Autor36', 23.50, 24),
(37, 'Libro37', 'Autor37', 42.99, 8),
(38, 'Libro38', 'Autor38', 17.25, 31),
(39, 'Libro39', 'Autor39', 14.50, 20),
(40, 'Libro40', 'Autor40', 29.75, 26);

SELECT * FROM user;

SELECT * FROM product;

SELECT SUM(cost * stock) FROM product;
SELECT SUM(total) FROM sale;

SELECT id FROM sale ORDER BY id DESC LIMIT 1;

SELECT * FROM sale;

SELECT * FROM Cashout;

DELETE FROM sale WHERE id > 0;
