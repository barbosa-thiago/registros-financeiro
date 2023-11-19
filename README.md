###Buscador Sintegra
Aplicação que busca informações de uma empresa a partir do seu CNPJ no portal Sintegra-CE, salva <br />
informações no banco de dados e as retorna

As variáveis de ambiente necessárias para rodar a aplicação estão no arquivo **.envExemplo** na raiz do projeto

```
DATABASE_PASSWORD=
DATABASE_USER=
DB_HOST=
DB_PORT=
```

####Banco de Dados

A aplicação está configurada para conexão com **firebirdSQL** de deve estar acessível com host, porta e credenciais especificados nas <br />
variáveis de ambiente

É necessário que seja criada uma sequencia no **firebird** chamada **GEN_CLIENTE_ID**

####Tecnologias

- Maven
- Hibernate
- Jsoup
- Thymeleaf
- FirebirdSQL