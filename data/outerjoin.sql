create table departments(
	id serial primary key,
	name varchar(255)
);

create table employers(
	id serial primary key,
	name varchar(255),
	dep_id int references departments(id)
);

insert into departments(name)
values ('first'),('second'),('third');

insert into employers(name,dep_id)
values ('James',1),('Scott',1),('Jack',1),('John',2),('Jacob',2);

select * from departments d
left join employers e on d.id = e.dep_id;

select * from employers e
right join departments d on e.dep_id = d.id;

select * from departments d f
ull join employers e on d.id = e.dep_id;

select * from departments d
cross join employers e;

select * from departments d
left join employers e on e.dep_id = d.id
where e.dep_id is null;

select * from departments d
left join employers e on d.id = e.dep_id
where e.dep_id is not null;
select * from departments d
right join employers e on e.dep_id = d.id;



create table teens(
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);

insert into teens(name,gender)
values ('Mike','Male'),('John','Male'),('james','Male'),
('Amanda','Female'),('Monica','Female'),('Bella','Female');

select t1.name, t2.name from teens t1
cross join teens t2
where t1.gender != t2.gender;

