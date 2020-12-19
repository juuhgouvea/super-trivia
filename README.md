# Super Trivia

Desenvolva uma aplicativo para resolução de quizzes (jogo de perguntas e respostas).

Uma API foi integralmente desenvolvida para tal: https://super-trivia-server.herokuapp.com. Utilize o Postman ou software semelhante para estudar e entender a API.

No primeiro acesso, o usuário vê uma tela de login (E-mail, Senha) e uma opção para cadastrar-se (Nome, E-mail, Senha e Confirmar Senha).

Obs: Uma vez autenticado, das próximas vezes que for usar o aplicativo, o usuário não deve ver o login.

Ao autenticar-se, o usuário pode começar a jogar, mas antes escolhe uma dificuldade (difícil, médio ou fácil) e uma categoria. Ele tem a opção de não escolher, desta forma a API irá aleatorizar as perguntas. Utilizar RecyclerView para categorias que devem ser carregadas da API.

O aplicativo apresenta uma pergunta por vez de acordo com as seleções que o usuário fez no início. Cada questão é apresentada a ele com uma pergunta e suas quatro respostas.

Obs: a API já se responsabiliza por gerar perguntas aleatórios, com suas opções de respostas embaralhadas.

Ao selecionar uma das respostas, o aplicativo deve apresentar ao usuário, de maneira clara, se houve erro ou acerto, bem como a suas pontuação atualizada.

Após cada pergunta, o jogo deve solicitar ao usuário se ele deseja uma próxima pergunta ou se ele deseja encerrar a partida.

Ao selecionar a segunda opção, uma tela de resumo da partida do usuário deve ser apresentada.

Obs: Nem todas estas informações são fornecidas pelo servidor. Você deve arquivos dados localmente para quando este momento chegar.

A qualquer momento o usuário pode acessar a tela de configurações mostrada no início da partida para alterar a dificuldade e a categoria das próximas perguntas (utilizar bottom navigation ou componente semelhante).

A qualquer momento o usuário pode acessar o ranking, que mostra as 20 melhores pontuações da história do jogo dele e dos demais usuários (utilizar bottom navigation ou componente semelhante).

## Observações

- Deve ser feito individualmente (cópias não serão toleradas).
- Apesar de existir armazenamento local, o jogo deve funcionar apenas quando houver conexão com o servidor (mostrar erro "bonito").
- Apesar de o idioma das perguntas ser em inglês (consultado em tempo-real do [Open Trivia DB](https://opentdb.com)), o restante da aplicação deve ser internacionalizável (padrão: inglês).
- Caprichar nos layouts.
- Utilizar ConstraintLayout.
- Utilizar Fragments, Bottom Navigation (ou outro) e Navigation Framework.
- Não trave a aplicação do usuário.
- Aplicar boas práticas de Android e programação: indentação, formatação, convenções de escrita de código (CameCase, etc), estrutura de pacotes organizada, código limpo, ...
- __Analise bem as requisições e os JSON de resposta de cada API usando o Postman.__

## Desafio (bônus, caso tenha cumprido os demais requisitos)

- Procure uma API de tradução e traduza as perguntas, respostas e categorias em tempo real para o idioma do usuário.

## Explicação da API

Aplicação executando em https://super-trivia-server.herokuapp.com

Código-fonte: https://github.com/seccomiro/trivia-server

[**Explicação completo dos endpoints da API**](https://github.com/seccomiro/trivia-server#explica%C3%A7%C3%A3o-da-api)