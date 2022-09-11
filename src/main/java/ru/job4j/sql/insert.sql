insert into role(name) values('Иван грозный');
insert into category(name) values('Исторические');
insert into state(name) values('На утверждени');
insert into rules(name) values('Наличие бороды');
insert into users(name, role) values('Владислав Петров', 1);
insert into item(number, users, category, state) values(1, 1, 1, 1);
insert into comments(comment, role) values('Не ваше амплуа', 1);
insert into attachs(attach, role) values('D:\photos\petrov.jpeg', 1);
insert into rules_for_role(role_id, rules_id) values(1, 1);