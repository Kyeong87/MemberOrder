drop table member;
drop table orders;

CREATE TABLE `member` (
      `id` varchar(20) NOT NULL,
      `password` varchar(255) NOT NULL,
      `name` varchar(20) NOT NULL,
      `nick_name` varchar(30) NOT NULL,
      `phone` varchar(20) NOT NULL,
      `email` varchar(100) DEFAULT NULL,
      `gender` enum('MAN','WOMAN') DEFAULT NULL,
      `register_date` datetime DEFAULT NULL,
      PRIMARY KEY (`id`)
);

CREATE TABLE `orders` (
    `id` int (11) NOT NULL AUTO_INCREMENT,
    `order_number` varchar (20) NOT NULL,
    `name` varchar(255) NOT NULL,
    `member_id` varchar(50) NOT NULL,
    `register_date` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
);