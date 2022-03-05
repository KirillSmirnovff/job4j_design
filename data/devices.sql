create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('PS5', 50000), ('Xbox One', 25999), ('Xbox SeriesX', 46999);
insert into people(name) values('Аня'), ('Ваня'), ('Боря');
insert into devices_people(device_id, people_id) values (1,1), (1,2), (1,3);
insert into devices_people(device_id, people_id) values (2,1), (1,3);
insert into devices_people(device_id, people_id) values (3,1), (3,2);

select avg(d.price)
from devices as d;

select p.name, avg(d.price)
from people as p
join devices_people as dp
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from people as p
join devices_people as dp
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;

