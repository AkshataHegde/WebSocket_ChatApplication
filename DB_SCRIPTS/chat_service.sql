create database chat;

create table chat.user(u_id serial, u_name varchar(255), u_email varchar(255), u_phone_number varchar(20), u_username varchar(100), u_password varchar(100), u_created_date timestamp, u_is_session_active boolean, u_is_active boolean);

create table chat.chat_room(cr_id serial, cr_name varchar(200), cr_type varchar(200), cr_created_date timestamp, cr_is_active boolean);

create table chat.chat_message(cm_id serial, cm_sender bigint, cm_content longtext, cm_type varchar(100), cm_cr_id bigint, cm_created_date timestamp, cm_is_deleted boolean);

create table chat.chat_room_has_user(cru_id serial, cru_cr_id bigint, cru_cu_id bigint, cru_is_active boolean);