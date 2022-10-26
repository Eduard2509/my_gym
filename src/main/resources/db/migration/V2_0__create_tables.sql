create table coach
(
    id            varchar(255) not null
        primary key,
    name          varchar(255),
    sex           varchar(255),
    surname       varchar(255),
    age           integer      not null
        constraint coach_age_check
            check ((age <= 45) AND (age >= 18)),
    description   varchar(255),
    imageurl      varchar(255),
    status_people varchar(255)
);


create table locked_room
(
    id         varchar(255) not null
        primary key,
    condition  varchar(255),
    imageurl   varchar(255),
    sex        varchar(255),
    value      integer      not null,
    visitor_id varchar(255)
);


create table subscription
(
    id                 varchar(255) not null
        primary key,
    description        varchar(255),
    imageurl           varchar(255),
    names_subscription varchar(255),
    price              integer      not null,
    title              varchar(255)
);


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
    locked_id       varchar(255)
        constraint fk4ncf2gdab6kv2agcunius8ddg
            references locked_room,
    subscription_id varchar(255)
        constraint fkj13nd57gnhjhw3ha8bs1e22rs
            references subscription
);


alter table locked_room
    add constraint fkeqwfcte9g478djvpee28w35xm
        foreign key (visitor_id) references visitor;

