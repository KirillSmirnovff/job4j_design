create table body(
	id serial primary key,
	name varchar(255)
);

create table engine(
	id serial primary key,
	name varchar(255)
);

create table transmission(
	id serial primary key,
	name varchar(255)
);

create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body(name)
values('Sedan'),('Minivan'),('Hatchback'),('Coupe');

insert into engine(name)
values('Gas engine'),('Diesel engine');

insert into transmission(name)
values ('Auto'),('Manual');

insert into car(name,body_id, engine_id,transmission_id)
values('Renault', 1, 1, 1),('BMW',4,1,1),('VolksWagen',3,1,1);

select c.name, b.name, e.name, t.name from car c
join body b
on c.body_id = b.id
join engine e
on c.engine_id = e.id
join transmission t
on c.transmission_id = t.id;

select b.name from engine e
join car c
on c.body_id = e.id
where c.body_id is null;

select e.name from engine e
left join car c
on e.id = c.engine_id
where c.engine_id is null;

select t.name from transmission t
left join car c
on c.transmission_id = t.id
where c.transmission_id is null;
