create table category(
	category_id SERIAL primary key,
	name varchar(100) not null,
	last_update timestamp default current_timestamp
);
create table film(
	film_id SERIAL primary key,
	title varchar(100),
	discription varchar(10000),
	release_year year,
	language_id INT,
	rental_duration TIME(precision),
	rental_rate decimal(10,2),
	length TIME(precision),
	replacment_cost decimal(10,2),
	rating INT,
	last_update timestamp default current_timestamp references category(laste_update),
	special_features varchar(100),
	fill_text varchar(100)
	
);
create table film_category(
	film_id SERIAL primary key references film(film_id),
	category_id SERIAL primary key references category(category_id),
	last_update timestamp default current_timestamp references category(laste_update)
);
create table Inventory(
	inventory_id SERIAL primary key,
	film_id SERIAL references film(film_id),
	store_id SERIAL,
	last_update timestamp default current_timestamp references film(last_update)
);
create table 
