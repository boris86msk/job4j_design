create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function tax_10()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + (price * 0.10)
        where id = (select id from inserted);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_10
    after insert
    on products
	referencing new table as inserted
    for each statement
    execute procedure tax_10();

reate or replace function tax_15()
    returns trigger as
$$
    BEGIN
        new.price = new.price + (new.price * 0.15)
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_15
    before insert
    on products
    for each row
    execute procedure tax_15();

create or replace function update_history_of_price()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date) values
        (new.name, new.price, now());
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger update_histori
    after insert
    on products
    for each row
    execute procedure update_history_of_price();

insert into products(name, producer, count, price) values('Фасоль', 'ECO', 5, 200);
insert into products(name, count, price) values('Огурцы', 'ECO', 5, 50);
insert into products(name, count, price) values('Шпинат', 'ECO', 5, 100);
