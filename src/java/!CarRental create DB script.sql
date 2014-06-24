/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     29.03.2014 15:50:52                          */
/*==============================================================*/


drop table if exists orders;

drop table if exists passports;

drop table if exists users;

drop table if exists usertypes;

drop table if exists vehicles;

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders
(
   order_id             int not null auto_increment,
   vehicle_id           int not null,
   user_id              int not null,
   passport_id          int not null,
   pick_up_date         timestamp not null,
   drop_off_date        timestamp not null,
   rent_cost            decimal(10,2) not null,
   processed            bool not null,
   rejected             bool not null,
   reject_desc          varchar(100),
   picked               bool not null,
   returned             bool not null,
   damaged              bool not null,
   damage_desc          varchar(100),
   damage_cost          decimal(10,2),
   paid                 bool not null,
   primary key (order_id)
)
CHARACTER SET utf8;

/*==============================================================*/
/* Table: passports                                             */
/*==============================================================*/
create table passports
(
   passport_id          int not null auto_increment,
   last_name            varchar(50) not null,
   first_name           varchar(50) not null,
   patronymic           varchar(50),
   birthday             date not null,
   p_series             char(2) not null,
   p_number             char(6) not null,
   who_issued           varchar(50) not null,
   when_issued          date not null,
   primary key (passport_id)
)
CHARACTER SET utf8;

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users
(
   user_id              int not null auto_increment,
   usertype_id          int not null,
   login                varchar(50) not null,
   password             varchar(50) not null,
   primary key (user_id)
)
CHARACTER SET utf8;

/*==============================================================*/
/* Table: usertypes                                             */
/*==============================================================*/
create table usertypes
(
   usertype_id          int not null auto_increment,
   usertype             varchar(50) not null,
   primary key (usertype_id)
)
CHARACTER SET utf8;

/*==============================================================*/
/* Table: vehicles                                              */
/*==============================================================*/
create table vehicles
(
   vehicle_id           int not null auto_increment,
   make                 varchar(50) not null,
   model                varchar(50) not null,
   auto_gearbox         bool not null,
   air_conditioner      bool not null,
   seats                int not null,
   daily_price          decimal(10,2) not null,
   primary key (vehicle_id)
)
CHARACTER SET utf8;

alter table orders add constraint FK_order_passport foreign key (passport_id)
      references passports (passport_id) on delete restrict on update restrict;

alter table orders add constraint FK_order_user foreign key (user_id)
      references users (user_id) on delete restrict on update restrict;

alter table orders add constraint FK_order_vehicle foreign key (vehicle_id)
      references vehicles (vehicle_id) on delete restrict on update restrict;

alter table users add constraint FK_usertype_user foreign key (usertype_id)
      references usertypes (usertype_id) on delete restrict on update restrict;

