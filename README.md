# Teste para empresa Attornatus
## Pessoa Api

Esta é a documentação da API de Pessoa, responsável por gerenciar as informações de pessoas e seus endereços.

Observação: Todas as respostas e requições são feitas utilizando JSON, e para utilizá-las no Postman será necessário identá-las removendo os espaços, pois, foi necessário adicionar espaços para identar todas as requisições e resposta neste README.md

## Tecnologías utilizadas neste projeto:
- Java 11 - Versão do SDK utilizado neste projeto;
- Spring Web - Contém um servidor Tomcat embutido;
- Spring Data JPA - Faz a persistência de dados em um Banco Relacional;
- Spring DevTools - Toda vez que uma alteração é feita, reinicia o projeto de modo automático;
- Lombok - Reduz a quantidade de código boilerplate;
- Maven - Gerenciador de Dependências;
- Postman - é um API Client que facilita criar, compartilhar, testar e documentar APIs;
- H2 - Banco de Dados Relacional em memória:

Configurações do H2:
- JDBC URL = jdbc:h2:mem:testdb;
- User Name = admin;
- Password = admin;

Aplicação está porta 8081;

Caminho para acessar no browser(URL) = localhost:8081/h2 ;

Caminho para acessar no browser(URL) os endpoints = localhost:8081/pessoas


## Endpoints:

### Endpoint da Pessoa:


### Endpoint: GET /pessoas

Resposta de sucesso: 200 OK

Resposta de erro: 400 Bad Request

Listar todas as pessoas

Retorna todas as pessoas cadastradas na API.

Resposta

Se houver pelo menos uma pessoa cadastrada, a resposta será:

{

    "id": 1,
    
    "nome": "Joana",
    
    "dataDeNascimento": "25/05/1975",
    
    "enderecos": [
    
      {
      
            "enderecoId": 1,
            
            "logradouro": "Rua dos Batistas",
            
            "cep": "88450-123",
            
            "numero": 186,
            
            "isPrincipal": true,
            
            "cidade": "Ubatuba"
        
        }
    
    ]

}

### Endpoint: GET /pessoas/{id}

Resposta de sucesso: 200 OK

Resposta de erro: 404 Not Found

Obter uma pessoa por ID

Retorna uma pessoa específica, identificada pelo seu ID.

Resposta de sucesso:

Se a pessoa com o id for encontrada, a resposta será:

{
    
    "id": 1,
    
    "nome": "Joana",
    
    "dataDeNascimento": "25/05/1975",
    
    "enderecos": [
        
        {
            
            "enderecoId": 1,
            
            "logradouro": "Rua dos Batistas",
            
            "cep": "88450-123",
            
            "numero": 186,
            
            "isPrincipal": true,
            
            "cidade": "Ubatuba"
        
        }
    
    ]

}

Resposta de erro:

Pessoa não encontrada;

### Endpoint: POST /pessoas

Resposta de sucesso: 201 Created

Resposta de erro: 400 Bad Request, 500 Internal Server Error

Adiciona uma nova pessoa na API.

OBS:. Mesmo que isPricipal seja false, o primeiro endereço de cada pessoa será o principal;

Exemplo de requisição correta:

{

    "nome": "Joana",
    
    "dataDeNascimento": "25/05/1975",
    
    "enderecos": [
        
        {
            
            "logradouro": "Rua dos Batistas",
            
            "cep": "88450-123",
            
            "numero": 186,
            
            "isPrincipal": false,
            
            "cidade":"Ubatuba"
        
        }
    
    ]

}

Resposta de sucesso:

{
    
    "id": 1,
    
    "nome": "Joana",
    
    "dataDeNascimento": "25/05/1975",
    
    "enderecos": [
        
        {
            
            "enderecoId": 1,
            
            "logradouro": "Rua dos Batistas",
            
            "cep": "88450-123",
            
            
            "numero": 186,
            
            "isPrincipal": true,
            
            "cidade": "Ubatuba"
        
        }
    
    ]

}  

Exemplo de requisão errada:

{
    
    "nome": "Joana",
    
    "dataDeNascimento": "25/05/1975",
    
    "enderecos": [
        
        {
            
            "logradouro": "Rua dos Batistas",
            
            "cep": "88450-123",
            
            "numero": 186,
            
            "isPrincipal": false,
            
            "cidade":"Ubatuba"
        
        },
        
        {
            
            
            "logradouro": "Rua dos Pereiras",
            
            "cep": "88450-126",
            
            "numero": 186,
            
            "isPrincipal": false,
            
            "cidade":"São Joaquim"
        
        }
    
    ]

}

Retorna erro 400 do tipo Bad Request e a mensagem:

A pessoa só pode adicionar um endereço por vez;

Modificar uma pessoa por ID

Modifica uma pessoa existente, identificada pelo seu ID.Porém não modica o endereço, modifica apenas os dados da pessoa.

### Endpoint: PUT /pessoas/{id}

Resposta de sucesso: 200 OK

Resposta de erro: 400 Bad Request

Modificar uma pessoa por ID

Modifica uma pessoa existente, identificada pelo seu ID.Porém não modica o endereço, modifica apenas os dados da pessoa.

Exemplo de Requisição

{
    
    "nome": "Mario",
    
    "dataDeNascimento": "24/05/1975",
    
    "enderecos": []
 
 }

Resposta de sucesso:

{
    
    "id": 1,
    
    "nome": "Mario",
    
    "dataDeNascimento": "24/05/1975",
    
    "enderecos": [
        
        {
            
            "enderecoId": 1,
            
            "logradouro": "Rua dos Batistas",
            
            "cep": "88450-123",
            
            "numero": 186,
            
            "isPrincipal": true,
            
            "cidade": "Ubatuba"
        
        }
    
    ]

}

### Endpoint: DELETE /pessoas/{id}

Resposta de sucesso: 200 OK

Resposta de erro: 400 Bad Request

Mensagem de sucesso:

Pessoa deletada com sucesso!

### Enpoint do Endereço:

### Endpoint: GET /pessoas/{id}/endereco-principal

Resposta de sucesso: 200 OK

Resposta de erro: 400 Bad Request

Retorna o endereço principal da pessoa.

Resposta de sucesso:

{
    
    "enderecoId": 1,
    
    "logradouro": "Rua dos Batistas",
    
    "cep": "88450-123",
    
    "numero": 186,
    
    "isPrincipal": true,
    
    "cidade": "Ubatuba"

}


### Endpoint: POST /pessoas/{id}/endereco

Resposta de sucesso: 201 Created

Resposta de erro: 400 Bad Request, 409 Conflict

Adicionar um novo endereço

Adiciona um novo endereço para uma pessoa específica, identificada pelo seu ID.

Exemplo de requisição:

{
    
    "logradouro": "Rua dos Pereiras",
    
    "cep": "88450-123",
    
    "numero": 186,
    
    "isPrincipal": false,
    
    "cidade": "Ubatuba"
}

Resposta de sucesso:

{
    
    "enderecoId": 2,
    
    "logradouro": "Rua dos Lima",
    
    "cep": "88450-123",
    
    "numero": 186,
    
    "isPrincipal": true,
    
    "cidade": "Ubatuba"

}

Obs.:Todo novo endereço adicionado sera principal.

Caso a tente adicionar um novo endereço que já exista, ou seja, o logradouro,cep, numero e a cidade forem iguais a outro endereço da pessoa.

Não será possível adicionar este endereço e retornará a seguinte mesagem:

Endereço já existe no banco de dados e retornará um Status HTTP 409.

### Endpoint: PUT /pessoas/{id}/endereco/{enderecoId}

Resposta de sucesso: 200 OK

Resposta de erro: 400 Bad Request

Modificar um endereço por ID

Modifica um endereço existente, identificado pelo seu ID, de uma pessoa específica.

Exemplo de requisição:

{
    
    "logradouro": "Rua dos Lima",
    
    "cep": "88450-123",
    
    "numero": 186,
    
    "isPrincipal": true,
    
    "cidade": "Ubatuba"
    
}

Resposta de sucesso

{
    
    "enderecoId": 1,
    
    "logradouro": "Rua dos Lima",
    
    "cep": "88450-123",
    
    "numero": 186,
    
    "isPrincipal": true,
    
    "cidade": "Ubatuba"

}

Obs:. Não será alterar isPrincipal para false, sem antes alterar um dos outros endereços como principal primeiro;

Resposta de erro:

Não é possivel modificar endereço, pois é necessário que pelo menos um endereço seja principal
