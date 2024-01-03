create table application_user
(
    id         bigint generated by default as identity
        primary key,
    created_at timestamp,
    updated_at timestamp,
    password   varchar(255) not null,
    role       varchar(255) not null,
    username   varchar(255) not null
);

alter table application_user
    owner to root;

insert into application_user (id, username, password, role, created_at) values (1, 'admin', '$2a$10$Qe3eLGioB30O1sqZar2aBe2ZgbSQOB0jEZ56/rzMm863OuLMTHDhS', 'ADMIN', now());