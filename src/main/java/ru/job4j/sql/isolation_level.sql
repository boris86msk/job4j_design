create table phones (
    id serial primary key,
    name varchar(50),
    count integer,
    price integer
);

insert into phones (name, count, price) VALUES ('Nokia', 13, 2550);
insert into phones (name, count, price) VALUES ('Philips', 15, 3200);
insert into phones (name, count, price) VALUES ('Motorola', 18, 1150);
insert into phones (name, count, price) VALUES ('Siemens', 10, 2150);

--Транзакция 1
begin transaction isolation level serializable;
SELECT * FROM phones;
SELECT SUM(price * count) FROM phones;
update phones set price = 3000 where name = 'Nokia';
COMMIT;

--ранзакция 2
begin transaction isolation level serializable;
SELECT * FROM phones;
SELECT SUM(price * count) FROM phones;
update phones set price = 1450 where name = 'Siemens';
COMMIT;