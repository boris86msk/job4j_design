create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--таблица в ДБ уже заполнена ранее для других заданий


--удаление товаров из таблицы по имени
create or replace procedure delite_data(d_name varchar)
language 'plpgsql'
as $$
    BEGIN
        DELETE FROM products
        WHERE name = d_name;
    END;
$$;

call delite_data('фасоль');

--удаление товара если его на складе меньше указанного значения
-- (списание) и вернуть количество оставшихся позиций (строк)
create or replace function f_delite_data(d_count integer)
returns integer
language 'plpgsql'
as
$$
	declare
    result integer;
    begin
        DELETE FROM products
        WHERE count < d_count;
		select into result count from products;
        return result;
    end;
$$;

select f_delite_data(2);
