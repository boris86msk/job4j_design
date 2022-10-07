create table students (
    id serial primary key,
    name varchar(50)
);

create table authors (
    id serial primary key,
    name varchar(50)
);

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id)
);

create table orders (
    id serial primary key,
    active boolean default true,
    start_date date,
    end_date date,
    book_id integer references books(id),
    student_id integer references students(id)
);

insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');
insert into students (name) values ('Олег Квант');

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');
insert into authors (name) values ('Лев Толстой');

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);
insert into books (name, author_id) values ('Война и мир', 3);
insert into books (name, author_id) values ('Анна Каренина', 3);

insert into orders (book_id, student_id, start_date, end_date) values (1, 1,
    '2020-10-14', '2020-12-01');
insert into orders (book_id, student_id, start_date, end_date) values (3, 1,
    '2021-04-05', '2021-04-15');
insert into orders (book_id, student_id, start_date, end_date) values (5, 2,
    '2022-03-03', '2022-04-05');
insert into orders (book_id, student_id, start_date, end_date) values (4, 1,
    '2021-09-05', '2021-09-27');
insert into orders (book_id, student_id, start_date, end_date) values (2, 2,
    '2022-05-14', '2022-07-01');
insert into orders (book_id, student_id, start_date, end_date) values (6, 3,
    '2022-01-14', '2022-03-01');
insert into orders (book_id, student_id, start_date, end_date) values (7, 3,
    '2021-11-14', '2022-01-01');

create view show_students_who_have_any_book_40_days_more
    as SELECT s.name as student_name, a.name as autoros_name, b.name as books_name,
       o.end_date - o.start_date as total_days
       FROM students s
       JOIN orders o on s.id = o.student_id
       JOIN books b on o.book_id = b.id
       JOIN authors a on b.author_id = a.id
       WHERE (o.end_date - o.start_date) > 40
       ORDER BY s.name;

SELECT * FROM show_students_who_have_any_book_40_days_more;
