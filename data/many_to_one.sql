create table game(
    id serial primary key,
    name varchar(255)
);

create table serial_number(
    id serial primary key,
    value varchar(255),
    game_id int references game(id)
);

insert into game(name) values ('Witcher 3');
insert into serial_number(value,game_id) values('KGHT1-KFNVT-23JLF', 1);