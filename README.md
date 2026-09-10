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
