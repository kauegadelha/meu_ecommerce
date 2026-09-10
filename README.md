## Sobre o Projeto

A aplicação desenvolvida é um e-commerce que resolve o problema de um cliente que deseja expandir seu negócio para uma loja online. Ele permite o cadastro de múltiplos estoques e produtos podem ter nomes iguais entre si, sendo diferenciados unicamente pelo código de barras.

As responsabilidades de cada usuário são separadas por níveis de acesso: CLIENT, ADMIN e MANAGER, com regras de autorização que combinam permissão por papel e por posse do recurso — por exemplo, apenas o próprio usuário pode alterar sua senha, independente da role. A aplicação conta com autenticação via JWT e um sistema de cadastro de pedidos.

O objetivo do projeto é reforçar o aprendizado em aplicações que seguem o padrão REST, abrangendo CRUD completo para cada entidade do sistema, modelagem de relacionamentos entre entidades, e o uso de comandos SQL para consultas mais específicas.

## Tecnologias Utilizadas

### Back-End
- Java 23
- Spring Boot 4.1.0
- Spring Security
- Spring Data JPA (Hibernate)
- Bean Validation
- JJWT (JSON Web Token)
- Maven

### Banco de Dados
- PostgreSQL

### Ferramentas de Teste
- Postman

## Endpoints da aplicação
### User
- `POST /user` — Cadastrar um novo usuário (Acesso público)
- `GET /user/me` — Mostrar o usuário atual logado (Acesso restrito para usuário logado)
- `GET /user/name` — Encontrar um usuário pelo seu nome (Acesso restrito para administrador e manager)
- `PUT /user/{id}` — Atualizar os dados de um usuário (Acesso restrito para usuário logado, administrador e manager)
- `PATCH /user/{id}/password` — Atualizar a senha do usuário logado (Acesso restrito para usuário logado)
- `DELETE /user/{id}` — Deletar a conta de um usuário cadastrado (Acesso restrito para usuário logado, administrador e manager)

### Product
- `POST /product` — Cadastrar um novo produto (Acesso restrito para administrador e manager)
- `GET /product/name` — Buscar um produto pelo seu nome (Acesso público)
- `PUT /product/{barcode}` — Atualizar os dados de um produto (Acesso restrito para administrador e manager)
- `DELETE /product/{barcode}` — Deletar um produto cadastrado (Acesso restrito para administrador e manager)

### Stock
- `POST /stock` — Cadastrar um novo estoque (Acesso restrito para administrador e manager)
- `GET /stock/name` — Buscar um estoque pelo seu nome (Acesso restrito para administrador e manager)
- `PUT /stock/{id}` — Atualizar os dados de um estoque (Acesso restrito para administrador e manager)
- `DELETE /stock/{id}` — Deletar um estoque cadastrado (Acesso restrito para administrador e manager)

### StockItem
- `POST /stock/{stockId}/items` — Adicionar um produto e sua quantidade ao estoque; se o produto já existir, incrementa a quantidade existente (Acesso restrito para administrador e manager)
- `DELETE /stock/{stockId}/items/{barcode}` — Deletar um produto ou decrementar sua quantidade no estoque (Acesso restrito para administrador e manager)
- `GET /stock/{stockId}/items` — Listar todos os produtos daquele estoque, com suas respectivas quantidades (Acesso restrito para administrador e manager)

### Order
- `POST /order` — Cadastrar um pedido, adicionando um produto e sua quantidade (Acesso restrito para usuário logado)
- `POST /order/{orderId}/items` — Adicionar um produto na lista de itens do pedido cadastrado, especificando a sua quantidade (Acesso restrito ao dono do pedido)
- `DELETE /order/{orderId}/items/{barcode}` — Remover um produto da lista do pedido ou decrementar a sua quantidade (Acesso restrito ao dono do pedido)
- `POST /order/{orderId}/submit` — Enviar um pedido (Acesso restrito ao dono do pedido)










