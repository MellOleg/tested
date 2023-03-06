create sequence hibernate_sequence start 1 increment 1;

create table address (
    id int8 not null,
    address_line varchar(255),
    city varchar(255),
    create_date timestamp,
    modified_date timestamp,
    postal_code varchar(255),
    country_id int8, primary key (id));

create table country (
    id int8 not null,
    country_code varchar(255),
    name varchar(255),
    primary key (id));

create table request (
    id int4 not null,
    filename varchar(255),
    tag varchar(255),
    text varchar(255),
    users_iq int8,
    primary key (id));

create table user_role (
    user_id int8 not null,
    roles varchar(255));

create table usr (
    id int8 not null,
    active boolean not null,
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    address_id int8,
    primary key (id));

alter table if exists address
    add constraint addres_user_fk
    foreign key (country_id) references country;

alter table if exists request
    add constraint request_user_fk
    foreign key (users_iq) references usr;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr;

alter table if exists usr
    add constraint usr_user_fk
    foreign key (address_id) references address;