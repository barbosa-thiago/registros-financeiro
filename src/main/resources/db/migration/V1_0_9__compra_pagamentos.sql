create table compra_pagamentos
(
    compra_id     bigint not null
        constraint fkk7qnduqyfvu4axsvmcyluq3bm
            references compra,
    pagamentos_id bigint not null
        constraint uk_b6f1mofc9svufyvm8c73yyrso
            unique
        constraint fkehe66anfim19ryfb612g3q42i
            references pagamento
);

alter table compra_pagamentos
    owner to root;

