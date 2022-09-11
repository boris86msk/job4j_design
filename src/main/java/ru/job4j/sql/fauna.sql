create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('Слон', 17800, '1024-01-10');
insert into fauna(name, avg_age, discovery_date) values('Цапля', 2000, '1524-01-01');
insert into fauna(name, avg_age, discovery_date) values('Питон', 3800, '1700-01-01');
insert into fauna(name, avg_age, discovery_date) values('Воробей', 800, '1374-01-01');
insert into fauna(name, avg_age, discovery_date) values('Бабочка', 90, '1964-01-01');
insert into fauna(name, avg_age, discovery_date) values('Жук', 365, '1504-01-01');
insert into fauna(name, avg_age, discovery_date) values('Бактерия', 1, '1900-01-01');
insert into fauna(name, avg_age, discovery_date) values('Вирус', 1, '1974-01-01');
insert into fauna(name, avg_age, discovery_date) values('Тигр', 8000, '1324-01-01');
insert into fauna(name, avg_age, discovery_date) values('Скорпион', 600, '1842-01-01');
insert into fauna(name, avg_age, discovery_date) values('Скат', 900, '1024-01-01');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 AND avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';