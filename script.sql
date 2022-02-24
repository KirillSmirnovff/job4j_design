create table games(
	id serial primary key,
	name varchar(255),
	publisher text,
	PG18 boolean
);
insert into games(name, publisher, PG18) values('The Witcher 3','CD Projekt RED',false);
update games set PG18 = true;
delete from games;