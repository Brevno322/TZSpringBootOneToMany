create table phone_book
(
    id          int auto_increment primary key,
    name        varchar(30) unique not null,
    create_data date               not null,
    name_person varchar(40) unique not null,
    foreign key (name_person) references person (name)
);

create table person
(
    id           int auto_increment primary key,
    name         varchar(40) unique not null,
    age          int                not null,
    city         varchar(40)        not null,
    phone_number varchar(40)        not null
);
create table contact
(
    id              int auto_increment primary key,
    name_phone_book varchar(40),
    foreign key (name_phone_book) references phone_book (name),
    name            varchar(40) not null,
    city            varchar(40) not null,
    age             int         not null
);