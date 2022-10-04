CREATE TABLE car_bodies (
	id serial primary key,
	name varchar
);

CREATE TABLE car_engines (
	id serial primary key,
	name varchar
);

CREATE TABLE car_transmissions (
	id serial primary key,
	name varchar
);

CREATE TABLE cars (
	id serial primary key,
	name varchar,
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmissions_id int references car_transmissions(id)
);

insert into car_bodies(name) values('sedan'), ('hatchback'), ('liftback'), ('pickup'),
    ('station_wagon'), ('convertible');
insert into car_engines(name) values('1.4 16v'), ('1.6 8v'), ('1.6 16v'), ('1.8 16v'),
    ('2.0 16v'), ('1.4 16v turbo'), ('2.0 16v turbo'), ('2.4 16v'), ('V6 3.2 24v');
insert into car_transmissions(name) values('MT-5 120'), ('MT-6 127'), ('AT-4 110'),
    ('AT-5 119'), ('ATR-4 135'), ('ATV 130'), ('MT-5 200');
insert into cars(name, body_id, engine_id, transmissions_id) values('VAZ 1119', 2, 2, 1);
insert into cars(name, body_id, engine_id, transmissions_id) values('Mazda 3', 5, 3, 3);
insert into cars(name, body_id, engine_id, transmissions_id) values('Toeta Camry', 1, 5, 4);
insert into cars(name, body_id, engine_id, transmissions_id) values('Audi A4', 5, 6, 6);
insert into cars(name, body_id, engine_id, transmissions_id) values('Opel Corsa', 2, 1, 3);
insert into cars(name, body_id, engine_id, transmissions_id) values('Lada Granta', 3, 3, 1);
insert into cars(name, body_id, engine_id, transmissions_id) values('KIA Spotage', 4, 7, 6);
insert into cars(name, body_id, engine_id, transmissions_id) values('Ford F150', 4, 9, 7);
insert into cars(name, body_id, engine_id, transmissions_id) values('Tesla', 1, null, null);


SELECT c.id, c.name, cb.name AS bodies, ce.name AS engines, ct.name AS transmissions
FROM cars c
LEFT JOIN car_bodies cb ON cb.id = c.body_id
LEFT JOIN car_engines ce ON ce.id = c.engine_id
LEFT JOIN car_transmissions ct ON ct.id = c.transmissions_id;

SELECT cb.id, cb.name
FROM car_bodies cb
LEFT JOIN cars c ON cb.id = c.body_id
WHERE c.body_id is null;

SELECT ce.id, ce.name
FROM car_engines ce
LEFT JOIN cars c ON ce.id = c.engine_id
WHERE c.engine_id is null;

SELECT ct.id, ct.name
FROM car_transmissions ct
LEFT JOIN cars c ON ct.id = c.transmissions_id
WHERE c.transmissions_id is null;