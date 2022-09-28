SELECT *
FROM product
WHERE type_id = 1;

SELECT *
FROM product
WHERE name LIKE '%Мороженое%';

SELECT *
FROM product
WHERE expired_data < current_date;

SELECT name, MAX(price)
FROM product
GROUP BY name
ORDER BY MAX(price) DESC
LIMIT 1;

SELECT type.name, COUNT(*)
FROM product
INNER JOIN type ON type.id = product.type_id
GROUP BY type.name;

SELECT name, price, expired_data
FROM product
WHERE type_id = 1 OR type_id = 2
ORDER BY price DESC;

SELECT type.name, COUNT(type_id)
FROM product
INNER JOIN type ON type.id = product.type_id
GROUP BY type.name;

SELECT product.name, type.name
FROM product
INNER JOIN type ON type.id = product.type_id

