# 🧑‍💼 Sistema de Validação de Processo Seletivo

Projeto desenvolvido como parte de um desafio da [DIO](https://www.dio.me/) para praticar **fluxos condicionais, de repetição e excepcionais** em Java, aplicando também conceitos de **Programação Orientada a Objetos (POO)**.

O sistema simula as etapas de um processo seletivo de uma empresa que recebeu vários candidatos e precisa analisá-los, selecioná-los e entrar em contato, além de um desafio extra de um contador.

---

## 🎯 O que o sistema faz

| Etapa | Descrição | Fluxo exercitado |
|-------|-----------|------------------|
| **1. Analisar candidatos** | Compara o salário pretendido de cada candidato com o salário base da vaga (R$ 2.000,00) e recomenda uma ação. | Condicional (`if / else if / else`) |
| **2. Selecionar candidatos** | Liga para os candidatos em ordem e seleciona quem atende, até preencher as vagas disponíveis. | Repetição (`while`) |
| **3. Imprimir selecionados** | Lista apenas os candidatos que foram realmente selecionados. | Repetição (`for`) |
| **4. Ligações de confirmação** | Tenta contato com cada selecionado até 3 vezes, parando assim que atende. | Repetição (`do-while`) |
| **5. Contador (desafio final)** | Conta de 1 até a diferença entre dois números. Se forem iguais, lança uma exceção personalizada. | Repetição + Excepcional (`try/catch`, exceção própria) |

---

## 🗂️ Estrutura do projeto

```
Candidatura_System/
├── README.md
├── .gitignore
└── src/
    └── candidatura/
        ├── App.java               # Ponto de entrada: executa todas as etapas
        ├── Candidato.java         # Entidade de domínio (nome, salário, regra de análise)
        ├── CentralDeLigacoes.java # Lógica de contato telefônico (reutilizável)
        ├── ProcessoSeletivo.java  # Orquestra análise, seleção e impressão
        ├── Contador.java          # Desafio final do contador
        └── ContadorException.java # Exceção personalizada do contador
```

### Decisões de design (POO)

- **`Candidato`** encapsula o nome, o salário pretendido e a regra de classificação (`analisarPretensao`), devolvendo um `enum` `SituacaoSalarial` em vez de imprimir diretamente — separando **regra de negócio** de **apresentação**.
- **`CentralDeLigacoes`** isola a simulação de atendimento em um único lugar, eliminando a duplicação que existia antes. Retorna um `record ResultadoContato` com o resultado da tentativa. O método de atendimento é `protected`, permitindo sobrescrita determinística em testes.
- **`ProcessoSeletivo`** recebe suas dependências pelo construtor (injeção de dependência) e devolve a **lista real** de candidatos selecionados — corrigindo um problema da versão anterior, em que a lista impressa não correspondia a quem havia sido de fato selecionado.
- **`ContadorException`** é uma exceção checada personalizada, com mensagem contextualizada (inclui o valor que causou o erro).

---

## ✅ Pré-requisitos

- **JDK 8 ou superior** instalado (o projeto foi validado com o **JDK 17**).
- Uma IDE ou editor de sua preferência (IntelliJ, Eclipse, VS Code...).

Para verificar a instalação:

```bash
java -version
javac -version
```

---

## ▶️ Como executar

A partir da pasta `src`, compile o pacote e rode as classes principais.

### Linux / macOS

```bash
cd src
javac -encoding UTF-8 candidatura/*.java

# Aplicação completa (análise, seleção, lista e ligações)
java candidatura.App

# Desafio do contador
java candidatura.Contador 5 10    # conta de 1 até 5
java candidatura.Contador 10 10   # lança ContadorException (valores iguais)
```

### Windows (PowerShell)

No Windows, para exibir os acentos corretamente, mude o console para UTF-8 antes de rodar:

```powershell
cd src
javac -encoding UTF-8 candidatura\*.java

chcp 65001    # muda o console para UTF-8

java "-Dstdout.encoding=UTF-8" candidatura.App
java "-Dstdout.encoding=UTF-8" candidatura.Contador 5 10
java "-Dstdout.encoding=UTF-8" candidatura.Contador 10 10
```

---

## 🖥️ Exemplos de saída

**Contador com valores diferentes** (`java candidatura.Contador 5 10`):

```
Contando de 1 até 5:
Imprimindo o número 1
Imprimindo o número 2
Imprimindo o número 3
Imprimindo o número 4
Imprimindo o número 5
```

**Contador com valores iguais** (`java candidatura.Contador 10 10`):

```
Os dois parâmetros não podem ter o mesmo valor (ambos valem 10).
```

> As etapas de seleção e de ligação usam sorteio aleatório para simular se o candidato atendeu, então a saída de `App` varia a cada execução.

---

## 📚 Conceitos praticados

- **Condicionais**: `if`, `else if`, `else` e `enum` para classificar a pretensão salarial.
- **Repetição**: `for`, `while` e `do-while` para percorrer candidatos e tentar contato.
- **Exceções**: criação de exceção personalizada (`ContadorException`) e tratamento com `try/catch`.
- **POO**: encapsulamento, injeção de dependência, separação de responsabilidades, `enum` e `record`.

---

## 📝 Licença

Projeto de uso **educacional**, livre para estudo e adaptação.
