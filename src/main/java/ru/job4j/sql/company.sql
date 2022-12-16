CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);
CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) VALUES(1, 'Рога и Копыта'), (2, 'Колобок'),
(3, '3 топора'), (4, 'Дед-да-Баба'), (5, 'Царьград'), (6, 'Лукоморье');

INSERT INTO person (id, name, company_id) VALUES (1, 'Попович С.А.', 4);
INSERT INTO person (id, name, company_id) VALUES (2, 'Колобов В.И.', 6);
INSERT INTO person (id, name, company_id) VALUES (3, 'Протасов Л.Д', 1);
INSERT INTO person (id, name, company_id) VALUES (4, 'Круглов А.К.', 2);
INSERT INTO person (id, name, company_id) VALUES (5, 'Юрченко Р.А.', 5);
INSERT INTO person (id, name, company_id) VALUES (6, 'Сафронов В.А.', 1);
INSERT INTO person (id, name, company_id) VALUES (7, 'Терехов П.А.', 2);
INSERT INTO person (id, name, company_id) VALUES (8, 'Зумянцев Г.П.', 6);
INSERT INTO person (id, name, company_id) VALUES (9, 'Анохин А.В.', 2);
INSERT INTO person (id, name, company_id) VALUES (10, 'Волков С.Н.', 5);
INSERT INTO person (id, name, company_id) VALUES (11, 'Давыдов С.А.', 4);
INSERT INTO person (id, name, company_id) VALUES (12, 'Добрый В.И.', 6);
INSERT INTO person (id, name, company_id) VALUES (13, 'Павлов Л.Д', 1);
INSERT INTO person (id, name, company_id) VALUES (14, 'Прочий А.К.', 3);
INSERT INTO person (id, name, company_id) VALUES (15, 'Карпов Р.А.', 5);
INSERT INTO person (id, name, company_id) VALUES (16, 'Пронин В.А.', 1);
INSERT INTO person (id, name, company_id) VALUES (17, 'Астахова П.А.', 2);
INSERT INTO person (id, name, company_id) VALUES (18, 'Громов Г.П.', 5);
INSERT INTO person (id, name, company_id) VALUES (19, 'Качергин А.В.', 2);
INSERT INTO person (id, name, company_id) VALUES (20, 'Славина С.Н.', 1);

SELECT p.name AS Сотрудник, c.name AS Компания
FROM person p INNER JOIN company c ON p.company_id = c.id
WHERE c.id <> 5;

SELECT c.name AS Компания, COUNT(p.company_id) AS Штат
FROM person p INNER JOIN company c ON p.company_id = c.id
GROUP BY p.company_id, c.name
HAVING COUNT(p.company_id) = (
	SELECT COUNT(company_id)
	FROM person
	GROUP BY company_id
	ORDER BY COUNT(company_id) DESC
	LIMIT 1
	);