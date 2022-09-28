CREATE DATABASE product_db;

CREATE TABLE type (
	id serial primary key,
	name varchar
);

CREATE TABLE product (
	id serial primary key,
	name varchar,
	type_id int references type(id),
	expired_data date,
	price int
);

insert into type(name) values('Сыр'), ('Молоко'), ('Конфеты'), ('х/б изделия');
insert into product(name, type_id, expired_data, price) values('Дружба', 1, '14-09-2022', 15);
insert into product(name, type_id, expired_data, price) values('Орбита', 1, '14-10-2022', 16);
insert into product(name, type_id, expired_data, price) values('Омичка', 1, '15-11-2022', 25);
insert into product(name, type_id, expired_data, price) values('Сыр творожный Valio', 1, '28-11-2022', 115);
insert into product(name, type_id, expired_data, price) values('President Сыр Сливочный', 1, '20-11-2022', 215);
insert into product(name, type_id, expired_data, price) values('Сыр Брест-Литовск', 1, '28-10-2022', 155);
insert into product(name, type_id, expired_data, price) values('Valio Viola', 1, '15-10-2022', 76);
insert into product(name, type_id, expired_data, price) values('Nemoloko напиток овсяный', 2, '01-11-2022', 77);
insert into product(name, type_id, expired_data, price) values('Parmalat молоко', 2, '01-10-2022', 85);
insert into product(name, type_id, expired_data, price) values('Кефир Легкий вечер 1%', 2, '05-10-2022', 65);
insert into product(name, type_id, expired_data, price) values('Сметана Домик в деревне 15%', 2, '01-11-2022', 80);
insert into product(name, type_id, expired_data, price) values('Творожок Даниссимо Браво', 2, '01-09-2022', 45);
insert into product(name, type_id, expired_data, price) values('Сливки стерилизованные', 2, '30-10-2022', 245);
insert into product(name, type_id, expired_data, price) values('Каракум', 3, '01-12-2022', 215);
insert into product(name, type_id, expired_data, price) values('Му-Му', 3, '05-12-2022', 245);
insert into product(name, type_id, expired_data, price) values('Step', 3, '11-10-2022', 315);
insert into product(name, type_id, expired_data, price) values('Халва в шоколаде', 3, '01-11-2022', 450);
insert into product(name, type_id, expired_data, price) values('Ирис', 3, '25-10-2022', 150);
insert into product(name, type_id, expired_data, price) values('Барбарис', 3, '31-12-2022', 200);
insert into product(name, type_id, expired_data, price) values('Булачка с маком', 4, '20-09-2022', 25);
insert into product(name, type_id, expired_data, price) values('Батон нарезной', 4, '15-09-2022', 45);
insert into product(name, type_id, expired_data, price) values('Хлеб Барадино', 4, '19-09-2022', 35);
insert into product(name, type_id, expired_data, price) values('Батон Плетенка', 4, '24-09-2022', 48);
insert into product(name, type_id, expired_data, price) values('Булочки сдобные', 4, '21-09-2022', 35);
insert into product(name, type_id, expired_data, price) values('Мороженое ягодное', 2, '30-10-2022', 25);
insert into product(name, type_id, expired_data, price) values('Мороженое Липецкое', 2, '30-10-2022', 35);