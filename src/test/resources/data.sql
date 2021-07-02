create table topic
(
    id          serial not null constraint topic_pk primary key,
    name        varchar,
    description varchar
);

create table topic_items
(
    id       serial not null constraint topic_items_pk primary key,
    name     varchar,
    "order"  integer,
    topic_id integer constraint topic_items_topic_id_fk references topic on update cascade on delete cascade
);