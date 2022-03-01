create table people(
    id serial primary key,
    name varchar(255)
);

create table insurance_policy(
    id serial primary key,
    number varchar(255),
    people_id int references people(id) unique
);

insert into people(name) values ('Smirnov K.M.');
insert into insurance_policy(number,people_id) values('5554442223115-2344', 1);