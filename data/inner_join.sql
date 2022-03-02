create table games(
	id serial primary key,
	name varchar(255)
);

create table players(
	id serial primary key,
	name varchar(255),
	games_id int references games(id)
);

insert into games(name) values ('Counter Strike');
insert into games(name) values ('Dota');
insert into games(name) values ('League of Legends');
insert into players(name,games_id) values ('Ivan', 1);
insert into players(name,games_id) values ('Vasyily', 2);
insert into players(name,games_id) values ('Egor', 3);
insert into players(name) values ('Maxim');

select g.name, p.name
from games as g
join players as p
on p.games_id = g.id;

select g.name, p.name
from games as g
join players as p
on p.games_id != g.id;

select g.name, p.name
from games as g
join players as p
on p.games_id is null AND g.name = 'Dota';