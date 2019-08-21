drop database if exists unicorn_db;
create database unicorn_db;

drop table if exists unicorn_db.user;
create table unicorn_db.user(
 id int auto_increment primary key
  comment 'id PK',
  email varchar(255) not null unique
  comment 'email NN UN',
  username varchar(255) not null
  comment 'username NN',
  password varchar(255) not null
  comment 'password NN'
);

drop table if exists unicorn_db.article;
create table unicorn_db.article(
 id int auto_increment primary key
 comment 'id PK',
 title varchar(255) not null
 comment 'title NN',
 content varchar(255) not null
 comment 'content NN'
);

drop table if exists unicorn_db.userInfo;
create table unicorn_db.userInfo(
 id int auto_increment primary key
 comment 'id PK',
 birthday datetime not null
 comment 'birthday NN',
 gender varchar(255) not null
 comment 'gender NN',
 avatar varchar(255) default 'default.png'
 comment 'avatar NN',
 createTime datetime comment 'sign up time',
 updateTime datetime comment 'last sign in time',
 userId     int comment 'user id FK'
);

alter table unicorn_db.userInfo
 add constraint
 user_info_fk_userId
foreign key (userId)
references unicorn_db.user (id);

select *
from unicorn_db.user;

select *
from unicorn_db.article;

select *
from unicorn_db.userInfo;



truncate unicorn_db.user;

truncate unicorn_db.article;