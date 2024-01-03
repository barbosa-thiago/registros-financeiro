alter table agendamento add
    compra_id      bigint
    constraint agendamento_compra_fk
        references compra,
    add pagamento_id   bigint
        constraint agendamento_pagamento_fk
            references pagamento;