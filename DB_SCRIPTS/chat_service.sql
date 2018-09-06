create database chat;

create table chat.user(u_id bigint, u_name varchar(255), u_email varchar(255), u_phone_number varchar(20), u_username varchar(100), u_password varchar(100),u_createdDate timestamp, u_is_session_active boolean, u_is_active boolean, primary key(u_id));

create table chat.chat_room(cr_id bigint, cr_name varchar(200), cr_type varchar(200), cr_created_date timestamp, cr_is_active boolean, primary key(cr_id));