CREATE TABLE departments (
	id serial primary key,
	name varchar
);

CREATE TABLE employees (
	id serial primary key,
	name varchar,
	dep_id int references departments(id)
);

insert into departments(name) values('logistic'), ('distribution'), ('IT'), ('lawyer'), ('finance'), ('support');
insert into employees(name, dep_id) values('Laura Callahan', 1);
insert into employees(name, dep_id) values('Nancy Davolio', 2);
insert into employees(name, dep_id) values('Andrew Fuller', 5);
insert into employees(name, dep_id) values('Janet Lever', 1);
insert into employees(name, dep_id) values('Margaret Peacock', 4);
insert into employees(name, dep_id) values('Steven Michael', 1);
insert into employees(name, dep_id) values('Anne Dodsworth', 5);
insert into employees(name, dep_id) values('Nancy King', 5);
insert into employees(name, dep_id) values('Robert King', 3);
insert into employees(name, dep_id) values('Laura Wozovsky', 2);
insert into employees(name, dep_id) values('Jon Smitt', 3);
insert into employees(name, dep_id) values('Jen Soll', 2);
insert into employees(name, dep_id) values('Jennifer Clarke', 1);
insert into employees(name, dep_id) values('Pauline Allen', 3);
insert into employees(name, dep_id) values('Alan Jones', 5);
insert into employees(name, dep_id) values('Linda Morris', 2);
insert into employees(name, dep_id) values('Kelly Salevan', null);

SELECT d.id, d.name, e.name
FROM departments d
LEFT JOIN employees e ON e.dep_id = d.id;

SELECT d.id, d.name, e.name
FROM departments d
RIGHT JOIN employees e ON e.dep_id = d.id;

SELECT d.id, d.name, e.name
FROM departments d
FULL JOIN employees e ON e.dep_id = d.id;

SELECT d.id, d.name, e.name
FROM departments d
CROSS JOIN employees e;

SELECT d.id, d.name
from departments d
LEFT JOIN employees e ON e.dep_id = d.id
WHERE e.dep_id IS NULL;

SELECT d.id, d.name, e.name
from departments d
LEFT JOIN employees e ON e.dep_id = d.id;

SELECT d.id, d.name, e.name
from employees e
RIGHT JOIN departments d ON e.dep_id = d.id;