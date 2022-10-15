create table coach
(
    id            varchar(255) not null
        primary key,
    name          varchar(255),
    sex           varchar(255),
    surname       varchar(255),
    age           integer      not null
        constraint coach_age_check
            check ((age >= 18) AND (age <= 45)),
    description   varchar(255),
    imageurl      varchar(255),
    status_people varchar(255)
);

alter table coach
    owner to postgres;

create table subscription
(
    id                 varchar(255)     not null
        primary key,
    description        varchar(255),
    imageurl           varchar(255),
    names_subscription varchar(255),
    price              integer not null,
    title              varchar(255)
);

alter table subscription
    owner to postgres;

create table visitor
(
    id              varchar(255) not null
        primary key,
    name            varchar(255),
    sex             varchar(255),
    surname         varchar(255),
    age             integer      not null
        constraint visitor_age_check
            check ((age >= 12) AND (age <= 65)),
    email           varchar(255),
    password        varchar(255),
    status_people   varchar(255),
    coach_id        varchar(255)
        constraint fkmpfs56qk6xcr50j0am4k9t8k8
            references coach,
    subscription_id varchar(255)
        constraint fkj13nd57gnhjhw3ha8bs1e22rs
            references subscription
);

alter table visitor
    owner to postgres;

