# Projeto de Software 
Projeto da disciplina de Projeto de Software <br/>
Orientadores: **Dr. Dalton Serey** (@daltonserey) e **Dra. Raquel Lopes** (@raquelvl) <br/>
Discentes: **Dayvid Silva** (@dayvidds) e **João Espindula** (@pedroespindula)

>**AJuDE - AquiJUntosDoandoEsperança** <br/>
O AJuDE é uma plataforma para financiamento coletivo onde as causas mais populares ganham mais visibilidade. Cada projeto (ou campanha) será atendido quando conseguir arrecadar uma quantidade de doações igual ou superior à meta. O sistema permite que usuários classifiquem os projetos e escrevam comentários sobre os mesmos. Os usuários da aplicação criam projetos com suas metas. A partir desse momento o sistema começa a arrecadação e permite classificar, dar like/dislike nos projetos e ainda escrever comentários sobre eles. 

## Explicação resumida da api
Para fazer cumprir os padrões de projeto e as boas práticas de programação foi decidido separar em view, controller e model (MVC). Como o projeto é pode ser dividido em duas grandes partes e recursos principais, que são os usuários e campanhas, foi necessário criar vários modelos e controladores para manter uma boa coesão.
O controlador de campanha ficou responsável por tarefas que envolvam ela como por exemplo criar campanhas, adicionar comentários e dar uma curtida.
O controlador de usuário ficou responsável apenas no cadastro de usuário e no retorno de usuários, como o login envolve várias manipulações e criações de token, achou melhor criar um controlador específico  para login fazendo com que reduza mais a coesão.
Em relação ao token, foi definido o tempo de duas horas pois como não é um sistema que o usuário irá passar muito tempo durante o dia não terá o incômodo de ficar se autenticando várias vezes, ao mesmo tempo como possui informações sensíveis como número do cartão de crédito, motivo pelo qual não pode-se ter um tempo de token muito grande.
Os controladores são responsáveis apenas por receber um comando a ser executado, para isso ele irá designar a tarefa para os serviços específicos.
De forma a otimizar a comunicação foi adotado a criação de DTOs, pois seria ineficiente ficar a todo momento transferindo todas as informações.


[Vídeo de demonstração da aplicação](https://www.youtube.com/watch?v=Awu26ElxPM0) <br/>
[Documentação da API gerada pelo swagger](https://api-ajude.herokuapp.com/swagger-ui.html) <br/>
[Aplicação](https://dayvidds.github.io/psoft-ajude-front-20192/#/) <br/>

