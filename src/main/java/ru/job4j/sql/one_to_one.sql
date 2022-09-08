create table automobil (
    id serial primary key,
    vin int,
);

create table technical passport(
    id serial primary key,
    seria int,
    automobil int references automobil(id) unique
);