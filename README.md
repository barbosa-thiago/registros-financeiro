### Gerenciador de Custos Financeiro
Gerenciamento de compras, pagamentos, agendamentos de custo, além de buscar informações de uma  <br />
empresa a partir do CNPJ no portal Sintegra-CE, salva informações no banco de dados e as retorna

As variáveis de ambiente necessárias para rodar a aplicação estão no arquivo **.envExemplo** na raiz do projeto

```
DATABASE_PASSWORD=
DATABASE_USER=
DB_HOST=
DB_PORT=
```

#### Banco de Dados

A aplicação está configurada para conexão com **firebirdSQL** de deve estar acessível com host, porta e credenciais especificados nas <br />
variáveis de ambiente

É necessário que sejam criadas sequencias no **firebird**, pois o Hibernate nao cria automaticamente para esse banco <br />
chamada **GEN_CLIENTE_ID, GEN_PAGAMENTO_ID, GEN_COMPRA_ID, GEN_PRODUTO_ID, GEN_AGENDAMENTO_ID**

#### Tecnologias

- Maven
- Hibernate
- Jsoup
- Thymeleaf
- FirebirdSQL
- Jquery