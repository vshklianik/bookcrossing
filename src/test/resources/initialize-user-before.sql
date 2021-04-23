SET FOREIGN_KEY_CHECKS = 0;

delete from user_role;
delete from users;

SET FOREIGN_KEY_CHECKS = 1;

insert into users(id, active, password, username, first_name, last_name, email) values
    (1, true, '$2a$08$ZAtfo88N37OQkmSJ0VAhZOSGZS8m6SZfhuE225GoHEjTpD7CPn/sW', 'admin', 'Nikolay', 'Serhel', 'nikolasergel19@gmail.com'),
    (2, true, '$2a$08$tR9frPkZBeb7vGzi0jhFLezPdinWl0rSYZv3d.0LpNspJpUR788TG', 'nikola', 'Belka', 'Micro', 'micro.belka19@gmail.com');

insert into user_role(user_id, roles) values
    (1, 'USER'), (1, 'ADMIN'),
    (2, 'USER');