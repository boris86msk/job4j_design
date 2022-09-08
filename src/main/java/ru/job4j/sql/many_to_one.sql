create table planetary_system(
    id serial primary key,
    name varchar(255)
);

create table planet(
    id serial primary key,
    name varchar(255),
    planetary_system int references planetary_system(id)
);

insert into planetary_system(name) values ('Sun');
insert into planet(name, planetary_system) VALUES ('Earth', 1);