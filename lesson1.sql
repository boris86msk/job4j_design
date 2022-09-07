create table birthday(
	id serial primary key,
	name varchar(255),
	age smallint,
	date date
);
insert into birthday(name, age, date) values('Boris Pokidov',36, '1986-05-14');
select * from birthday;
update birthday set name = 'Bob Parker';
select * from birthday;
delete from birthday;
select * from birthday;