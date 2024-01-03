create table compra_agendamentos
(
    compra_id       bigint not null
        constraint fk3s5jnue2ebg2i4fymprd9xvy1
            references compra,
    agendamentos_id bigint not null
        constraint uk_fjp8thx16dqmh38s3m1wr5aan
            unique
        constraint fkss4wwccu93s6hcxokqfwbu5q1
            references agendamento
);

alter table compra_agendamentos
    owner to root;

