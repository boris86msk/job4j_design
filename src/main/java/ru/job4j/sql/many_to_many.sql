create table human(
     id serial primary key,
     name varchar(255)
 );

 create table language(
     id serial primary key,
     name varchar(255)
 );

 create table human_language(
     id serial primary key,
     human_id int references human(id),
     language_id int references language(id)
 );

insert into human(name) values ('Ivan');
insert into human(name) values ('Tom');

insert into language(name) values ('Russian');
insert into language(name) values ('English');

insert into human_language(human_id, language_id) values (1, 1);
insert into human_language(human_id, language_id) values (2, 2);