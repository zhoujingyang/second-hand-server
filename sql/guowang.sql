CREATE DATABASE `guowang` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

create table user(
    id int(4) not null primary key auto_increment,
    username varchar(20) not null
);

alter table user add column create_time datetime not null;
alter table user add column update_time datetime not null;
alter table user add column nick_name varchar(50);
alter table user add column university varchar(50);
alter table user add column telphone varchar(20);


CREATE TABLE `feedback` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `content` varchar(500) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table university(
    id int(4) not null primary key auto_increment,
    name varchar(20) not null

)



CREATE TABLE `news` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `pageId` int(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `author` varchar(10) DEFAULT NULL,
  `html` varchar(100000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


