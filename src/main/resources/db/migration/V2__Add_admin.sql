insert into users (id, username, password, active)
    value(1, 'admin', '$2a$08$R3z5JJhVM0/K6z6YitIqjOilsTRYROOlfkD/.WKqgyutT5AMA1N0e', true);

insert into user_role (user_id, roles)
    values(1, 'USER'), (1, 'ADMIN');