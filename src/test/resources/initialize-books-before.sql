SET FOREIGN_KEY_CHECKS = 0;
delete from genre;
delete from book;

SET FOREIGN_KEY_CHECKS = 1;

insert into book(id, book_name, author_name, user_id) values
(1, 'Синтонимы. Одиин из них мертв', 'Медина Мирай', 1),
(2, 'Перелетные снайперы', 'Мэдисон Дэрек', 1),
(3, 'Красный терорист', 'Джеф Андерсен', 1),
(4, 'Клетчатый кубик', 'Тони Сетт', 2),
(5, 'Перечеркнутый квадрат', 'Адриан Тоун', 2),
(6, 'Красно-синее дежавю', 'Айзек Локалов', 2);

insert into genre(book_id, genre) values
(1, 'FANTASY'),
(2, 'ROMANCE'),
(2, 'DETECTIVE'),
(3, 'COMEDY'),
(3, 'ROMANCE'),
(4, 'HORROR'),
(4, 'CRIME'),
(4, 'DETECTIVE'),
(5, 'ROMANCE'),
(5, 'FANTASY'),
(6, 'CYBERPUNK'),
(6, 'SCIENCE');

update hibernate_sequence set next_val=10 where next_val=1;