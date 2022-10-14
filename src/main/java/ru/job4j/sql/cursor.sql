--транзакция с курсором для таблицы "products" из задания
--    "5. Курсор в SQL [#504816]"
begin transaction;
DECLARE curs1 SCROLL CURSOR FOR SELECT * FROM products;
FETCH LAST FROM curs1;
FETCH BACKWARD 4 FROM curs1;
FETCH BACKWARD FROM curs1;
MOVE BACKWARD FROM curs1;
MOVE BACKWARD 5  FROM curs1;
FETCH BACKWARD FROM curs1;
FETCH FIRST FROM curs1;
CLOSE curs1;
COMMIT;