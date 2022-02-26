create table games(
    id serial primary key,
    name varchar(255)
);

create table steam_accounts(
    id serial primary key,
    login varchar(255),
);

create table games_steam_accounts(
    id serial primary key,
    games_id int references games(id),
    steam_accounts_id int references steam_accounts(id)
);

insert into games(name) values ('Witcher 3');
insert into games(name) values ('RDR 2');
insert into games(name) values ('Elden Ring');
insert into steam_accounts(login) values('Fulltime_gamer');
insert into steam_accounts(login) values('NewbieStrikesAgain');
insert into steam_accounts(login) values('Hardcore_in_my_veins');

insert into games_steam_accounts(games_id, steam_accounts_id) values(1,1);
insert into games_steam_accounts(games_id, steam_accounts_id) values(2,1);
insert into games_steam_accounts(games_id, steam_accounts_id) values(3,1);
insert into games_steam_accounts(games_id, steam_accounts_id) values(1,2);
insert into games_steam_accounts(games_id, steam_accounts_id) values(2,2);
insert into games_steam_accounts(games_id, steam_accounts_id) values(3,3);