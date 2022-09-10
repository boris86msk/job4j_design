create table role (
    id serial primary key,
    name varchar(255),
);

create table category (
    id serial primary key,
    name varchar(255),
);

create table state (
    id serial primary key,
    name varchar(255),
);

create table rules (
    id serial primary key,
    name varchar(255),
);

create table users (
    id serial primary key,
    name varchar(255),
    role int references role(id)
);

create table item (
    id serial primary key,
    number int,
    user int references users(id),
    category int references category(id),
    state int references state(id)
);

create table comments (
    id serial primary key,
    comment text,
    role int references item(id)
);

create table attachs (
    id serial primary key,
    attach text,
    role int references item(id)
);

create table rules_for_role (
    id serial primary key,
    role_id int references role(id),
    rules_id int reference rules(id)
);