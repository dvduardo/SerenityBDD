# language: pt

Funcionalidade: Teste api basica para aprendizado do serenity BDD
  Eu como qa automatizador
  Quero aprender as funcionalidades do serenity
  Para que possa implementar nos meus testes futuros

  Contexto:
    Dado que "Serenity BDD" esta em andamento

  Cenario:Executar uma requisicao POST
    Quando eu criar uma requisicao "post" com sucesso
      | title  | titulo |
      | body   | body   |
      | userId | 1      |
    E retorna "created"
    E validar o campo "id" contem o valor inteiro "101"
    Entao validar os campos
      | title | titulo |
      | body  | body   |


  Esquema do Cenario: Executar uma requisicao GET
    Quando eu criar uma requisicao "get" com sucesso
      | id | <value> |
    E retorna "sucesso"
    Entao validar o campo "id" contem o valor inteiro "<value>"

    Exemplos:
      | value |
      | 1     |
      | 2     |
      | 3     |
      | 4     |

  Cenario: Executar uma requisicao GET com body incorreto
    Quando eu criar uma requisicao "get" com sucesso
      | id | erro |
    Entao retorna "not_found"

  Cenario:Executar uma requisicao PATCH
    Quando eu criar uma requisicao "patch" com sucesso
      | id    | 1     |
      | title | teste |
    E retorna "sucesso"
    Entao validar os campos
      | body | recusandae |

  Cenario:Executar uma requisicao DELETE
    Quando eu criar uma requisicao "delete" com sucesso
      | id | ERRO |
    E retorna "sucesso"

  Cenario:Recuperar json file e utilizando em uma requisicao
    Quando eu recuperar o arquivo e enviar a requisicao
    E retorna "created"
    Entao validar os campos
      | title     | Happy Potter |
      | duration  | 120          |
      | cast.girl | Hermione     |

  Cenario:Recuperar arquivo json e modificar valores dos campos existentes
    Quando eu recuperar o arquivo
    E modificar o campo
      | title    | Novo Filme                  |
      | body     | Nova descricao para o filme |
      | duration | 57                          |
      | userId   | 2                           |
    E eu modificar o campo "cast"."girl" para o valor "Garota da capa vermelha"
    E eu modificar o campo "cast"."scar_boy" para o valor "Jorge"
    E eu enviar a requisicao
    E retorna "created"
    Entao validar os campos
      | title         | Novo Filme                  |
      | body          | Nova descricao para o filme |
      | duration      | 57                          |
      | userId        | 2                           |
      | cast.girl     | Garota da capa vermelha     |
      | cast.scar_boy | Jorge                       |

  Cenario:Recuper arquivo json e remover campos existentes
    Quando eu recuperar o arquivo
    E remover o campo
      | body     |
      | duration |
    E remover o campo cascateado
      | cast | girl        |
      | cast | redhead_boy |
    E eu enviar a requisicao
    E retorna "created"

  Cenario:Recuperar arquivo json e adicionar campos na requisicao
    Quando eu recuperar o arquivo
    E eu adicionar o campo
      | release_year | 2000 |
      | has_sequel   | yes  |
    E eu criar o campo "teste"."subteste" com o valor "1"
    E eu enviar a requisicao
    E retorna "created"
    Entao validar os campos
      | release_year   | 2000 |
      | has_sequel     | yes  |
      | teste.subteste | 1    |
