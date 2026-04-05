# Projeto MC322 - Penguin.fight()

<img src="image-3.png" alt="Alt text" align="left" width="100" height="auto">

Saudações, pinguim! Este projeto foi desenvolvido como parte dos laboratórios da disciplina **MC322 - Programação Orientada a Objetos**, com o objetivo de implementar um jogo inspirado em *Slay the Spire*, no qual o jogador utiliza um **baralho de cartas** para derrotar inimigos em batalhas por turno. O projeto desenvolvido aqui foi inspirado pelo antigo jogo online *Club Penguin*, utilizando a linguagem **Java** e sendo executado via terminal

# Estrutura do Projeto

O projeto segue a seguinte estrutura padrão em Java:

```text
.
├─ src/
│  ├─ App.java
│  ├─ Heroi.java
│  ├─ Inimigo.java
│  ├─ Carta.java
│  ├─ CartaDano.java
│  └─ ...
├─ lib/
├─ bin/
├─ .gitignore
└─ README.md

Onde:

- **src** — contém todos os arquivos `.java` do projeto  
- **lib** — pasta reservada para dependências externas (não utilizada neste projeto)  
- **bin** — arquivos `.class` gerados após a compilação  
```

# Como Compilar o Projeto

No diretório raiz do projeto, execute:

```bash
javac -d bin $(find src -name "*.java")
```

Esse comando compila todos os arquivos `.java` dentro da pasta `src` e coloca os arquivos compilados (`.class`) na pasta `bin`

# Como Executar o Projeto

Após compilar, execute:

```bash
java -cp bin App
```

Isso iniciará o programa e o sistema de combate será executado no terminal

# Como Jogar

![alt text](image.png)

O jogo foi inspirado pelo minigame *Desafio Ninja*, do antigo jogo online *Club Penguin*. A dinâmica foi modificada, mas consiste em escolher cartas de dano (golpes) e de defesa (bloqueios) a cada turno, com o objetivo de derrotar o inimigo (assim como no jogo *Slay the Spire*)

O jogo atualmente possui três níveis, cada um com seu inimigo próprio, e só acaba quando os três níveis forem vencidos

No início, digite seu nome para jogar:

![alt text](image-1.png)

Durante o combate:

- O inimigo declara suas ações no início do turno
- O jogador possui um **baralho de cartas**
- No início de cada turno, cartas são compradas para a **mão**
- Cada carta possui um **custo de energia**
- O jogador pode usar cartas enquanto tiver energia disponível
- Ao final do turno do jogador, o **inimigo realiza suas ações**

![alt text](<image-luta.jpg>)


O combate termina quando:

- o **herói é derrotado**, ou
- o **inimigo é derrotado**

![alt text](image-2.png)

## Efeitos

Tanto o jogador quanto o inimigo podem aplicar **efeitos** de combate. O inimigo declara os efeitos que pretende usar a cada rodada e o jogador pode aplicá-los com **cartas de efeito**.
Os efeitos disponíveis para o jogador são:

![alt text](image-faixa-1.jpg)

- **FAIXA** - Permite que o jogador aumente sua faixa de luta, aumentando em 2 pontos seu escudo a cada acúmulo (esse efeito dura todos os rounds até o final de um nível)

- **PEIXE** - Aumenta em X pontos a energia do jogador para o próximo turno

- **NEVASCA** - Reduz o ataque do rival em 50% (a ideia é que a nevasca atrapalha a visibilidade do golpe!)

Já os efeitos disponíveis para os inimigos são:

- **ÁCIDO** (níveis 1 e 2) - Causa X de dano no player (aplicado no final da rodada por Y rodadas)

- **REGENERAÇÃO** (nível 3) - Aumenta em X pontos a vida do inimigo (*spoiler*: caranguejos realmente se regeneram!)

## Rotas

O jogo te dá algumas escolhas por padrão: na tela de início, você pode aceitar ou não a aventura, e no início dos níveis, ao encontrar os inimigos, você pode aceitar confrontá-los ou não. Nesses casos, escolher *"Não..."* não faz o jogo parar, mas apresenta uma mensagem que te manda continuar. Entretanto, negar os conflitos *todas* as vezes te leva a um final com uma mensagem diferente (recomendamos testá-lo!). Essa é a rota relutante, que futuramente deve incluir um nível final com todos os inimigos juntos

Caso o jogador responda *"Sim"* em alguma dessas escolhas críticas, seguirá a rota padrão, com o término normal do jogo 

# Tecnologias Utilizadas

- Java 25
- Visual Studio Code
- Git e GitHub

# Autores

Projeto desenvolvido por:

- Manuela Daros Misurelli, RA278223
- Tereza Figueiredo Diniz Zeni, RA278914



