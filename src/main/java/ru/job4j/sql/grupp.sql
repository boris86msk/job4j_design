create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people(name) VALUES ('Василий');
insert into people(name) VALUES ('Владислав');
insert into people(name) VALUES ('Ирина');
insert into people(name) VALUES ('Олег');
insert into people(name) VALUES ('Ксения');

insert into devices(name, price) VALUES ('RuPhone', 10000);
insert into devices(name, price) VALUES ('Watch', 2500);
insert into devices(name, price) VALUES ('fitness bracelet', 1999);
insert into devices(name, price) VALUES ('laptop', 25000);
insert into devices(name, price) VALUES ('headphones', 1550);

insert into devices_people(device_id, people_id) values (2, 1), (4, 1);
insert into devices_people(device_id, people_id) values (1, 2), (5, 2);
insert into devices_people(device_id, people_id) values (1,3), (3, 3);
insert into devices_people(device_id, people_id) values (1,4), (4, 4), (5,4);
insert into devices_people(device_id, people_id) values (2,5);

select avg(price) from devices; --средняя всех девайсов

select p.name, avg(d.price) as Средняя_цена  --Средняя стоимость девайсов по людям
from people as p
join devices_people dp on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name;

having avg(d.price) > 5000; -- с условием что она > 5000

select p.name, sum(d.price) as Суммарная_стоимость -- всех имеющихся девайсов у человека
from people as p
join devices_people dp on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name;