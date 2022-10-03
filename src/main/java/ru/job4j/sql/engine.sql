create table engine(
    id serial primary key,
    volume int,
    valve int,
    turbo bool
);
create table car(
    id serial primary key,
    name varchar(255),
    engine int references engine(id)
  );

 insert into engine(volume, valve, turbo) VALUES (1.4, 16, false);
 insert into engine(volume, valve, turbo) VALUES (1.6, 8, false);
 insert into engine(volume, valve, turbo) VALUES (1.6, 16, false);
 insert into engine(volume, valve, turbo) VALUES (1.8, 16, false);
 insert into engine(volume, valve, turbo) VALUES (2.0, 16, false);
 insert into engine(volume, valve, turbo) VALUES (2.0, 16, true);
 insert into engine(volume, valve, turbo) VALUES (1.4, 16, true);

 insert into car(name, engine) VALUES ('Lada Kalina', 2);
 insert into car(name, engine) VALUES ('Lada Priora', 3);
 insert into car(name, engine) VALUES ('Lada Vesta', 4);
 insert into car(name, engine) VALUES ('Mazda 3MPS', 5);
 insert into car(name, engine) VALUES ('Toyota Camry', 6);
 insert into car(name, engine) VALUES ('WV Tiguan', 7);
 insert into car(name, engine) VALUES ('Kia Rio', 3);

 select * from car join engine
 on car.engine = engine.id
 where engine.volume < 1.8;

 select name as "Марка", volume as "объем мотора"
 from car join engine on car.engine = engine.id;

 select c.name, ee.volume, ee.turbo from car
 as c join engine as ee on c.engine = ee.id;

 select c.name, ee.volume, ee.turbo as "Турбо-мотор"
 from car as c join engine as ee on c.engine = ee.id
 where ee.turbo = true;