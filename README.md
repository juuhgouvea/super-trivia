# Super Trivia
> Aplicativo para resolução de quizzes (jogo de perguntas e respostas) feito em Android.

## Funcionalidades desenvolvidas
- [X] Registro de usuários
- [X] Autenticação
- [X] Configurar jogo
- [X] Iniciar jogo
- [X] Responder pergunta
- [X] Finalizar jogo
- [X] Visualizar ranking de jogadores

## API
O estudo da API foi feito utilizando o [Insomnia](https://insomnia.rest/download/).

[![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run/?label=Super%20Trivia%20API&uri=https%3A%2F%2Fraw.githubusercontent.com%2Fjuuhgouvea%2Fsuper-trivia%2Fmaster%2F.github%2Ftrivia_api.json)

## Estrutura de pacotes
Para maior organização da aplicação foi criada a seguinte estrutura (Além da estrutura já fornecida pelo Android):
- ### **adapters/**
  - Utilizados para poder renderizar as recycler views da aplicação.
- ### **dao/**
  - Classes que irão realizar chamadas HTTP para a API usando o Retrofit.
- ### **fragments/**
  - Os fragments da aplicação se encontram nesse pacote.
- ### **models/**
  - Classes de definição de dados
- ### **models/responses/**
  - Classes que irão auxiliar na serialização de JSON, já que a API utiliza o padrão JSend.
- ### **network/services**
  - Interfaces que descrevem as requisiçoes HTTP feitas pelo Retrofit

## Funcionamento básico
A aplicação se utiliza muito do recurso de SharedPreferences. É por meio desses dados armazenados que é possível definir qual fragment deve ser apresentado ao usuário levando em conta seu contexto (Está logado?/ Configurou o jogo?/ Está jogando?/ etc). \
Há 3 principais arquivos de preferences, sendo eles:
- **auth**: Armazena informações do usuário logado, bem como seu token de acesso.
- **settings**: Armazena as configurações do jogo (categoria e dificuldade).
- **game**: Armazena informações do jogo como status, inicio do jogo e etc.

## Recursos utilizados no desenvolvimento
A aplicação foi feita inteiramente em **Android**, utilizando as seguintes bibliotecas:
- **Retrofit** para realizar requisições HTTP
- **Gson** para serialização de JSON
- **Navigation Framework** para navegação na aplicação, utilizando também um BottomNavigationView.
## Screencast

### [Abrir Screencast no Youtube](https://youtu.be/gWP0hBYVy8g)