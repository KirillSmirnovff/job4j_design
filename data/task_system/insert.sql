insert into rules(function) values('Create');
insert into rules(function) values('Edit');
insert into role(name) values('User');
insert into role_rules(role_id, rules_id) values(1, 1);
insert into role_rules(role_id, rules_id) values(1, 2);
insert into users(name, role_id) values('Smirnov K.M.', 1);
insert into category(title) values('Task');
insert into state(title) values('New');
insert into item(title, body, users_id, category_id, state_id) values('New keyboard', 'Please, can i get new keyboard? Mine was broken.', 1, 1, 1);
insert into comments(body, item_id) values('Yes, you can, 10 mins plz', 1);
insert into attachs(file, item_id) values('Broken_keyboard.jpg', 1);

