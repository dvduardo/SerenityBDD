# language: pt

Funcionalidade: Teste api basica para aprendizado do serenity BDD
  Eu como qa automatizador
  Quero aprender as funcionalidades do serenity
  Para que possa implementar nos meus testes futuros

  Contexto:
    Dado que "Serenity BDD" esta em andamento

  Cenario:Batendo em um postTo
    Quando eu criar uma requisicao "post" com sucesso
      | title  | titulo |
      | body   | body   |
      | userId | 1      |
    E retorna "created"
    Entao validar os campos
      | title | titulo |
      | body  | body   |


  Esquema do Cenario: Batendo em um getResources
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

  Cenario: Batendo em um getResources com body incorreto
    Quando eu criar uma requisicao "get" com sucesso
      | id | erro |
    Entao retorna "not_found"

  Cenario:Batendo em um patchTo
    Quando eu criar uma requisicao "patch" com sucesso
      | id    | 1     |
      | title | teste |
    E retorna "sucesso"
    Entao validar os campos
      | body | recusandae |

  Cenario:Batendo em um DeleteFrom
    Quando eu criar uma requisicao "delete" com sucesso
      | id | ERRO |
    E retorna "sucesso"


