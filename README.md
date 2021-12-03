<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/dvduardo/SerenityBDDBasic">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/dvduardo/SerenityBDDBasic">

   <a href="https://github.com/dvduardo/SerenityBDDBasic/stargazers">
    <img alt="Stargazers" src="https://img.shields.io/github/stars/dvduardo/SerenityBDDBasic?style=social">
  </a>

  <a href="https://www.linkedin.com/in/dvduardo/">
    <img alt="Feito pela David" src="https://img.shields.io/badge/feito%20por-David-%237519C1">
  </a>

</p>
<h1 align="center">
    <img alt="SerenityBDD" title="#SerenityBDD" src="./images.png" />
</h1>

<h4 align="center"> 
	🚧 EM CONSTRUÇÂO 🚧
</h4>

## Um pouco sobre Serenity e o projeto desenvolvido

[Serenity_BDD](https://serenity-bdd.github.io/theserenitybook/latest/index.html#) é uma biblioteca de código aberto que
visa tornar a ideia de documentação viva uma realidade, ajudando você a escrever testes automatizados de aceitação e
regressão mais limpos e mais fáceis de manter com mais rapidez. O Serenity também usa os resultados do teste para
produzir relatórios narrativos ilustrados que documentam e descrevem o que seu aplicativo faz e como funciona. Ele
informa não apenas quais testes foram executados, mas, o que é mais importante, quais requisitos foram testados.

Esse projeto foi desenvolvido para ajudar outros QA's e DEV a entender um pouco mais de como funciona o Serenity BDD
juntamente com o [ScreenPlay Partner](https://blog.onedaytesting.com.br/screenplay-pattern/) para o desenvolvimento de
testes automatizados para backend.

---

## ⚙️ Funcionalidades

Basicamente os testes desenvolvidos são bem simples apenas para mostrar a funcionalidade da ferramenta batendo em
endpoints publicos disponibilizados pela [jsonplaceholder](https://jsonplaceholder.typicode.com/guide/). Temos os
seguintes cenarios de teste, que podem ser facilmente encontrados na
pasta [features](https://github.com/dvduardo/SerenityBDDBasic/blob/master/src/test/resources/features/Test.feature):
<br><br />
<b>Atualização 1.0 ↠ </b>  Foram adicionados novos cenarios para recuperar e modificar um arquivo json.

```bash
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
```

---

## 🚀 Como executar o projeto

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Java](https://www.java.com/pt-BR/), [Maven](https://maven.apache.org),[Git](https://gitforwindows.org). Além disto é
bom ter um editor para trabalhar com o código como [IntelliJ](https://www.jetbrains.com/pt-br/idea/)

#### AGORA VAMOS EXECUTAR DE VERDADE 😀

```bash
# Clone este repositório
$ git clone git@github.com:dvduardo/SerenityBDDBasic.git

# Acesse a pasta do projeto no terminal/cmd
$ cd SerenityBDDBasic

# Instale as dependências e execute os testes
$ mvn clean verify

# Apos a execução com sucesso, acesse a pasta do report do serenity
$ cd target\site\serenity

# Abra o arquivo index.html
$ index.html

```

---

[comment]: <> (## 👨‍💻 Contribuidores)

[comment]: <> (<table>)

[comment]: <> (  <tr>)

[comment]: <> (    <td align="center"><a href="https://www.instagram.com/dvduardo/"><img style="border-radius: 50%;" src="https://shortest.link/18iL" width="100px;" alt=""/><br /><sub><b>David Silva</b></sub></a><br /><a href="https://www.instagram.com/dvduardo/" title="Contribuidor"></a></td>)

[comment]: <> (  </tr>)

[comment]: <> (</table>)

## 💪 Como contribuir para o projeto

1. Faça um **fork** do projeto.
2. Crie uma nova branch com as suas alterações: `git checkout -b my-feature`
3. Salve as alterações e crie uma mensagem de commit: `git commit -m "feature: mensagem"`
4. Envie as suas alterações: `git push origin my-feature`

---

## 🦸 Autor

<a  align="center" href="https://www.linkedin.com/in/dvduardo/">
 <img align="center" style="border-radius: 50%;" src="https://user-images.githubusercontent.com/12902233/144551077-c4283258-c24a-4fa6-8b90-8ab0109bfc5e.png" width="200px;"/>
 <br />
<sub aria-label="center"><b>David Silva</b></sub></a> <a href="https://www.instagram.com/dvduardo/" title="Autor"></a>
 <br />
 <br />

[![Twitter Badge](https://img.shields.io/badge/-@dvd_uardo-1ca0f1?style=flat-square&labelColor=1ca0f1&logo=twitter&logoColor=white&link=https://twitter.com/dvd_uardo)](https://twitter.com/dvd_uardo) [![Linkedin Badge](https://img.shields.io/badge/-David-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/dvduardo/)](https://www.linkedin.com/in/dvduardo/)
[![Gmail Badge](https://img.shields.io/badge/-dvduardo@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:dvduardo@gmail.com)](mailto:dvduardo@gmail.com)

---

## 📝 Licença

Template do README feito pelo [Thiago Marinho](https://www.linkedin.com/in/tgmarinho/), disponibilizado pela equipe
maravilhosa da [Rocketseat](https://blog.rocketseat.com.br/como-fazer-um-bom-readme/)

---
