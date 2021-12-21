-- create schema book_app;

-- select 'drop table ' || table_name || ' cascade;' from information_schema.tables where table_schema='book_app';

drop table book cascade;

create table book (
	id serial primary key,
	ibn varchar(30) not null,
	title varchar(50) not null,
	author varchar(30) not null,
	price float not null,
	status varchar(1) not null
	);

);

-- alter table person add favorite_color varchar(10) not null;
-- drop table person cascade;
-- truncate table person;