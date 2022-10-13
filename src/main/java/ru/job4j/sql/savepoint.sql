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

SELECT * FROM phones;
begin transaction;
insert into phones(name, count, price) values('Samsung', 5, 3300);
SAVEPOINT point1;
UPDATE phones SET price = 2000 WHERE name = 'Motorola';
DELETE FROM phones WHERE name = 'Siemens';
SAVEPOINT point2;
SELECT * FROM phones;
DELETE FROM phones;
DROP TABLE phones;
SELECT * FROM phones;
ROLLBACK TO point2;
SELECT * FROM phones;
ROLLBACK TO point1;