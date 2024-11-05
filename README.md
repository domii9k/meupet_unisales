
# 📝 Instruções de execução do código - MeuPet Unisales

Este README aborda algumas instruções para a execução do código da API MeuPet Unisales (back-end). O projeto tem como objetivo gerenciar dados de animais, proprietários, vacinação, histórico de peso e altura, entre outros.

## ✔ Considerações:

1) O projeto está configurado para rodar localmente.
2) Ainda não há front-end integrado ao projeto, mas ele pode ser utilizado via ferramentas de requisição como Insomnia ou Postman.
3) O sistema de autenticação (login e registro de usuários) está incluído e funcional.
4) **Novas funcionalidades e melhorias estão planejadas para serem implementadas ao longo do semestre.**

## 🛠 Ferramentas recomendadas

Para testar a API, utilize o **Insomnia** ou **Postman** para realizar requisições. Navegadores também podem ser usados para testar as requisições GET. O **Firefox** é recomendado por apresentar as respostas de forma mais visual.

## Banco de dados:

O projeto utiliza o **MySQL** para armazenamento dos dados. Um script SQL será disponibilizado com algumas inserções de exemplo para facilitar o teste.

### Passos para configurar o banco de dados:

1) Instale o MySQL.
2) Crie o banco de dados utilizando o script fornecido.
3) No arquivo `application.properties`, substitua as variáveis de configuração para refletir o nome, usuário e senha do banco que você criou.

## Executando Localmente:

Link da API do projeto: https://localhost:8080

> Esta rota não renderiza uma página no momento, apenas retorna uma mensagem de erro padrão do Spring Boot. Todas as requisições podem ser testadas através de ferramentas como Insomnia ou Postman.

### 🔁 GET

Para buscar dados já cadastrados no sistema, faça uma requisição GET para os seguintes endpoints:

> - https://localhost:8080/animais
> - https://localhost:8080/proprietarios
> - https://localhost:8080/historico-peso-altura
> - https://localhost:8080/vacinacoes
> - https://localhost:8080/usuarios
>
> Para detalhes de um item específico, adicione o ID ao final da URL. Exemplo: https://localhost:8080/animais/1

### ⬆ POST

Para adicionar novos registros, utilize uma requisição POST e insira o link correspondente:

> - https://localhost:8080/animais/novo-animal
> - https://localhost:8080/proprietarios/novo-proprietario
> - https://localhost:8080/historico-peso-altura/novo-historico
> - https://localhost:8080/vacinacoes/nova-vacinacao
> - https://localhost:8080/vacinas/nova-vacina
> - https://localhost:8080/auth/registro

Nos bodies dessas requisições, insira o JSON com os dados necessários conforme o exemplo para cada entidade. Veja o exemplo abaixo para cadastro de um animal:

```json
{
  "nome": "Rex",
  "idade": 5,
  "sexo": "M",
  "especie": "Cão",
  "raca": "Labrador",
  "proprietario": {
    "id": 3
  }
}
```

### 📝 PUT

Para atualizar um registro existente, utilize uma requisição PUT com a URL + ID do item que deseja atualizar. Exemplo:

> - https://localhost:8080/animais/1
>
> Inclua o JSON atualizado no corpo da requisição.

### 🗑 DELETE

Para deletar um registro, utilize uma requisição DELETE com a URL + ID do item que deseja remover. Exemplo:

> - https://localhost:8080/animais/1

### 🔐 Autenticação

Para realizar login, utilize o endpoint de login:

> - https://localhost:8080/auth/login

No corpo da requisição, passe o seguinte JSON:

```json
{
    "email": "email@exemplo.com",
    "senha": "senha123"
}
```

O sistema retornará um token JWT que deverá ser utilizado no cabeçalho das próximas requisições autenticadas.

Para acesso administrador, o login e senha padrão é:

```json
{
    "email": "melissa@exemplo.com",
    "senha": "senha123"
}
```

Este usuário ADM é um usuário teste para caso queira realizar requisições de registros de usuários ou requisições avançadas.

### ✔️ Testes

Adicionado as classes testes das Services do sistema (NOV/2024). Ferramentas utilizadas:

- **JUnit5** 
- **Mockito**

## Permissões de Endpoints por Tipo de Usuário

| Tipo de Usuário               | Método | Endpoints                                                                                         | Descrição                          |
|-------------------------------|--------|--------------------------------------------------------------------------------------------------|------------------------------------|
| **Público (ADMIN, SEC, VET)** | POST   | `/auth/login`, `/auth/registro`                                                                  | Acesso para login e registro       |
| **Secretário**                | GET    | `/animais`, `/usuarios`, `/proprietarios`, `/historico-peso-altura`                              | Listar dados de animais, usuários, proprietários e histórico |
|                               | GET    | `/animais/{id}`, `/usuarios/{id}`, `/proprietarios/{id}`, `/historico-peso-altura/{id}`          | Consultar detalhes específicos de animais, usuários, proprietários e histórico |
|                               | POST   | `/animais/novo-animal`, `/proprietarios/novo-proprietario`                                       | Cadastrar novos animais e proprietários |
|                               | PUT    | `/usuarios/{id}`, `/animais/{id}`, `/proprietarios/{id}`                                         | Atualizar dados de animais, usuários e proprietários |
|                               | DELETE | `/animais/{id}`, `/usuarios/{id}`, `/proprietarios/{id}`                                         | Excluir dados de animais, usuários e proprietários |
| **Veterinário**               | GET    | `/animais`, `/usuarios`, `/proprietarios`, `/historico-peso-altura`                              | Listar dados de animais, usuários, proprietários e histórico |
|                               | GET    | `/animais/{id}`, `/usuarios/{id}`, `/proprietarios/{id}`, `/historico-peso-altura/{id}`          | Consultar detalhes específicos de animais, usuários, proprietários e histórico |
|                               | GET    | `/vacinacoes`, `/vacinas`                                                                        | Listar vacinas e vacinações       |
|                               | GET    | `/vacinacoes/{id}`, `/vacinas/{id}`                                                              | Consultar detalhes específicos de vacinações e vacinas |
|                               | POST   | `/animais/novo-animal`, `/proprietarios/novo-proprietario`                                       | Cadastrar novos animais e proprietários |
|                               | POST   | `/vacinas/nova-vacina`, `/historico-peso-altura/novo-historico`, `/vacinacoes/nova-vacinacao`    | Registrar novas vacinas, histórico e vacinações |
|                               | PUT    | `/usuarios/{id}`, `/animais/{id}`, `/proprietarios/{id}`                                         | Atualizar dados de animais, usuários e proprietários |
|                               | PUT    | `/vacinacoes/{id}`, `/vacinas/{id}`                                                              | Atualizar dados de vacinações e vacinas |
|                               | DELETE | `/animais/{id}`, `/usuarios/{id}`, `/proprietarios/{id}`                                         | Excluir dados de animais, usuários e proprietários |
|                               | DELETE | `/vacinacoes/{id}`, `/vacinas/{id}`, `/historico-peso-altura/{id}`                               | Excluir dados de vacinações, vacinas e histórico |

### Observação
- **Administrador**: possui acesso completo a todos os endpoints e métodos sem restrições.

#### Tipo de usuários (ENUM):

- ADMIN
- VETERINARIO
- SECRETARIO
