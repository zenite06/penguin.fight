# Projeto MC322 - Penguin.fight()

Saudações, pinguim! Este projeto foi desenvolvido como parte dos laboratórios da disciplina **MC322 - Programação Orientada a Objetos**, com o objetivo de implementar um jogo inspirado em *Slay the Spire*, no qual o jogador utiliza um **baralho de cartas** para derrotar inimigos em batalhas por turno. O projeto desenvolvido aqui foi inspirado pelo antigo jogo online *Club Penguin*, utilizando a linguagem **Java** e sendo executado via terminal.

Lute com as cartas!    
      V
  -=(o`'. _¬
    '.-.\// 
    /|  \\
    '|===||  
     _\_):,_

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
└─ README.md

Onde:

- **src** — contém todos os arquivos `.java` do projeto  
- **lib** — pasta reservada para dependências externas (não utilizada neste projeto)  
- **bin** — arquivos `.class` gerados após a compilação  

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

O jogo foi inspirado pelo minigame *Desafio Ninja*, do antigo jogo online *Club Penguin*. A dinâmica foi modificada, mas consiste em escolher cartas de dano (golpes) e de defesa (bloqueios) a cada turno, com o objetivo de derrotar o inimigo (assim como no jogo *Slay the Spire*).

Durante o combate:

- O jogador possui um **baralho de cartas**
- No início de cada turno, cartas são compradas para a **mão**
- Cada carta possui um **custo de energia**
- O jogador pode usar cartas enquanto tiver energia disponível
- Ao final do turno do jogador, os **inimigos realizam suas ações**

O combate termina quando:

- o **herói é derrotado**, ou
- todos os **inimigos são derrotados**

# Tecnologias Utilizadas

- Java 25
- Visual Studio Code
- Git e GitHub

# Autores

Projeto desenvolvido por:

- Primeiro Manuela Daros Misurelli, RA123456
- Segundo Tereza Figueiredo Diniz Zeni, RA278914

¨_ .'´o)=-
 \\/.-.'     Obrigado por jogar!
  //  |\     
  ||  |' 
_,:(_/_ 