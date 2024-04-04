# Play-MySongs-Spring

# Sistema de Gerenciamento de Músicas - Spring Java

## Visão Geral

Este é um sistema de gerenciamento de músicas desenvolvido em Spring Java, com a separação clara entre o back-end e o front-end. O projeto utiliza o Spring Boot para o back-end e tecnologias front-end como HTML, CSS e JavaScript.

## Funcionalidades

### Back-End (Spring Boot)

- Fornecer endpoints RESTful para manipular operações de envio e busca de músicas.
- Implementar autenticação para garantir que apenas usuários autorizados possam enviar músicas.
- Validar e processar os dados recebidos do front-end.

### Front-End (HTML, CSS, JavaScript)

- Criar uma interface de usuário amigável para interagir com o sistema.
- Implementar formulários para enviar informações sobre novas músicas.
- Oferecer uma interface de busca para os usuários procurarem músicas existentes.

## Estrutura do Projeto

O projeto Spring Java é organizado em diferentes pacotes para separar as responsabilidades:

- `controller`: Contém os controladores RESTful que lidam com as solicitações do cliente.
- `service`: Contém a lógica de negócios da aplicação.
- `model`: Contém as classes de modelo que representam os objetos de dados.
