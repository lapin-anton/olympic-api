create database db_olympic;

create table country (
    id serial unique not null,
    trigger varchar(8) not null,
    name varchar(128) not null,
    primary key (id)
);

create table athlete (
    id serial unique not null,
    country_id integer not null,
    name varchar(128) not null,
    surname varchar(128),
    gender varchar(16) not null,
    url varchar(256),
    primary key (id),
    foreign key (country_id)
        references country(id)
);

create table game (
    id serial unique not null,
    type varchar(64) not null,
    year integer not null,
    primary key (id)
);

create table sport (
    id serial unique not null,
    name varchar(256) not null,
    primary key (id)
);

create table result (
    id serial not null,
    game_id integer not null,
    sport_id integer not null,
    athlete_id integer not null,
    athlete_rank integer,
    athlete_age integer,
    gold integer not null,
    silver integer not null,
    bronze integer not null,
    total integer not null,
    foreign key (game_id) references game(id),
    foreign key (sport_id) references sport(id),
    foreign key (athlete_id) references athlete(id)
);

create table game_info(
    id serial not null,
    game_id integer not null,
    city varchar(256) not null,
    thumbnail_url text not null,
    description text,
    foreign key (game_id) references game(id)
);

-- drop table result;
--
-- drop table athlete;
--
-- drop table country;
--
-- drop table sport;
--
-- drop table game;