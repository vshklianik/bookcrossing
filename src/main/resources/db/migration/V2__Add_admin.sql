insert into users (id, username, password, active)
    value(1, 'admin', 'admin', true);

insert into user_role (user_id, roles)
    values(1, 'USER'), (1, 'ADMIN');