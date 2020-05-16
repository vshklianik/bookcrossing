create table hibernate_sequence (
    next_val bigint
    );

insert into hibernate_sequence values ( 1 );

insert into hibernate_sequence values ( 1 );

create table book (
    id bigint not null,
    author_name varchar(255),
    book_name varchar(255),
    filename varchar(255),
    genre varchar(255),
    user_id bigint,
    primary key (id)
);

create table user_role (
    user_id bigint not null,
    roles varchar(255)
);

create table users (
    id bigint not null,
    activation_code varchar(255),
    active bit not null,
    email varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);

alter table book
    add constraint advert_user_fk
    foreign key (user_id) references users (id);

alter table user_role
    add constraint user_role_user_fk
    foreign key (user_id) references users (id);

