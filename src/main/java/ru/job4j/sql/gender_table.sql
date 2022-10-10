CREATE TABLE teens (
	id serial primary key,
	name varchar,
	gender varchar(5)
);

insert into teens(name, gender) values('Анна', 'woman');
insert into teens(name, gender) values('Ирина', 'woman');
insert into teens(name, gender) values('Ольга', 'woman');
insert into teens(name, gender) values('Татьяна', 'woman');
insert into teens(name, gender) values('Генадий', 'man');
insert into teens(name, gender) values('Артем', 'man');
insert into teens(name, gender) values('Василий', 'man');
insert into teens(name, gender) values('Виталий', 'man');

SELECT CONCAT(t1.name, ' + ', t2.name)
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender != t2.gender AND t1.gender = 'man';