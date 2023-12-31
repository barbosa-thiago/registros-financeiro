create table compra
(
    id          bigint generated by default as identity
        primary key,
    created_at  timestamp,
    updated_at  timestamp,
    a_vista     boolean,
    agendado    boolean,
    data_compra date             not null,
    descricao   varchar(150),
    fornecedor  varchar(150)     not null,
    pago        boolean,
    valor       double precision not null
);

alter table compra
    owner to root;

