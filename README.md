# Projeto MC322 - Penguin.fight()

<img src="image-3.png" alt="Alt text" align="left" width="100" height="auto">

Saudações, pinguim! Este projeto foi desenvolvido como parte dos laboratórios da disciplina **MC322 - Programação Orientada a Objetos**, com o objetivo de implementar um jogo inspirado em *Slay the Spire*, no qual o jogador utiliza um **baralho de cartas** para derrotar inimigos em batalhas por turno. O projeto desenvolvido aqui foi inspirado pelo antigo jogo online *Club Penguin*, utilizando a linguagem **Java** e sendo executado via terminal.

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
│  └─ CartaEscudo.java
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

Esse comando compila todos os arquivos `.java` dentro da pasta `src` e coloca os arquivos compilados (`.class`) na pasta `bin`.

# Como Executar o Projeto

Após compilar, execute:

```bash
java -cp bin App
```

Isso iniciará o programa e o sistema de combate será executado no terminal.

# Como Jogar

![alt text](image.png)

O jogo foi inspirado pelo minigame *Desafio Ninja*, do antigo jogo online *Club Penguin*. A dinâmica foi modificada, mas consiste em escolher cartas de dano (golpes) e de defesa (bloqueios) a cada turno, com o objetivo de derrotar o inimigo (assim como no jogo *Slay the Spire*).

No início, digite seu nome para jogar:

![alt text](image-1.png)

Durante o combate:

- O jogador possui um **baralho de cartas**
- No início de cada turno, cartas são compradas para a **mão**
- Cada carta possui um **custo de energia**
- O jogador pode usar cartas enquanto tiver energia disponível
- Ao final do turno do jogador, os **inimigos realizam suas ações**

![alt text](<Captura de tela de 2026-03-12 20-42-45.png>)


O combate termina quando:

- o **herói é derrotado**, ou
- todos os **inimigos são derrotados**

![alt text](image-2.png)

# Tecnologias Utilizadas

- Java 25
- Visual Studio Code
- Git e GitHub

# Autores

Projeto desenvolvido por:

- Primeiro Manuela Daros Misurelli, RA278223
- Segundo Tereza Figueiredo Diniz Zeni, RA278914



