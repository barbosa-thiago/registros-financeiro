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

A aplicação está configurada para conexão com **PostgreSQL** de deve estar acessível com host, porta e credenciais especificados nas <br />
variáveis de ambiente

#### Tecnologias

- Maven
- Spring boot
- Hibernate
- Jsoup
- Thymeleaf
- PostgreSQL
- Jquery
- Flyway
- Spring Security

#### Subindo a aplicação no docker

Apenas rode o comando ```docker-compose up``` da raiz do projeto

O usuário:password inicial da aplicação é admin:admin 