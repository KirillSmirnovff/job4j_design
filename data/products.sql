create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name) values ('СЫР'),('МОЛОКО'),('ШОКОЛАД'),('МОРОЖЕНОЕ');
insert into product(name,type_id,expired_date,price) values ('Сыр плавленный', 1, '2022-06-06', 150),
('Сыр адыгейский', 1, '2022-03-11', 200),('Сыр Альметте', 1, '2022-05-06', 100),
('Сыр Аперифре', 1, '2022-04-03', 500),('Сыр Жерве', 1, '2022-03-22', 399),
('Сыр Маскарпоне', 1, '2022-05-01', 499),('Сыр Моцарелла', 1, '2022-05-23', 299),
('Сыр Рикотта', 1, '2022-05-06', 149),('Сыр Тофу', 1, '2022-04-05', 329),
('Сыр Пармезан', 1, '2022-04-29', 699),('Сыр Чеддер', 1, '2022-03-15', 249),
('Молоко парное', 2, '2022-03-08', 179),('Молоко пастеризованное', 2, '2022-03-12', 139),
('Молоко ультрапастеризованное', 2, '2022-09-08', 99),('Молоко стерилизованное', 2, '2022-12-08', 109),
('Молоко восстановленное', 2, '2022-07-11', 140),('Шоколад молочный', 3, '2022-08-11', 99),
('Шоколад горький', 3, '2022-07-11', 129),('Шоколад темный', 3, '2022-05-12', 149),
('Мороженое Лакомка',4,'2022-02-28',58), ('Мороженое Венеция',4,'2022-03-28',299);

select * from product as p
join type as t
on t.id = p.type_id
where t.name like 'СЫР';

select * from product where name like '%Мороженое%';

select * from product as p where current_date > p.expired_date;

select * from product where price = (select max(price) from product);

select t.name, count(p) from product as p
join type as t
on t.id = p.type_id
group by t.name;

select * from product as p
join type as t
on t.id = p.type_id
where t.name like 'СЫР' or t.name like 'МОЛОКО';

select t.name from type as t
join product as p
on t.id = p.type_id
group by t.name
having count(p) < 10;

select p.name, t.name
from product as p
join type as t
on p.type_id = t.id;



