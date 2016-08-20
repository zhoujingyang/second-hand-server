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


create table feedback(
    id int(4) not null primary key auto_increment,
    username varchar(20) not null,
    content varchar(500) not null
)

create table university(
    id int(4) not null primary key auto_increment,
    name varchar(20) not null

)