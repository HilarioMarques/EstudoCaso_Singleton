# Estudo de Caso 5 — Singleton (Sistema de Controle de Impressão)

Disciplina: Padrões de Projeto — Ciência da Computação, UESPI (Campus Drª Josefina Demes)

## O que foi feito

Implementação em Java do padrão **Singleton** para um gerenciador de impressão central. Todos os setores (financeiro, RH, administrativo, atendimento) passam a usar a mesma instância e, portanto, a mesma fila de documentos.

### Arquivos

| Arquivo | Descrição |
|---|---|
| `GerenciadorImpressao.java` | Classe Singleton com a fila de documentos |
| `Main.java` | Programa principal que simula os setores e comprova a instância única |
| `Respostas_Desafio_Extra.docx` | Respostas teóricas do desafio extra |

### Como o Singleton foi implementado

- **Construtor privado:** `private GerenciadorImpressao()` impede `new GerenciadorImpressao()` fora da classe.
- **Atributo estático:** `private static volatile GerenciadorImpressao instancia` guarda a única instância.
- **Método de acesso:** `getInstancia()` cria o objeto na primeira chamada e devolve sempre o mesmo nas seguintes (*lazy initialization*).
- **Thread-safety:** `getInstancia()` usa *double-checked locking* com `volatile`, e os métodos que mexem na fila são `synchronized`.
- **Fila:** uma `List<String>` guarda os nomes dos documentos, na ordem de chegada.
- **Métodos:**
  - `adicionarDocumento(String)`: adiciona um documento ao final da fila.
  - `visualizarFila()`: imprime a fila numerada.
  - `getFila()`: devolve uma cópia somente leitura da fila.

### Comprovação de que é Singleton

No `Main`, `gerenciador1` é obtido no início e `gerenciador2` depois que os setores já adicionaram documentos. A comparação `gerenciador1 == gerenciador2` imprime `true`.

## Como executar

Requisito: JDK 11 ou superior (`java -version` para conferir).

Na pasta onde estão os arquivos:

```bash
javac GerenciadorImpressao.java Main.java
java Main
```

### Saída esperada

```
=== SISTEMA DE IMPRESSÃO ===

Setor Financeiro:
Documento adicionado: Relatorio_Financeiro.pdf

Setor RH:
Documento adicionado: Folha_de_Pagamento.pdf

Setor Administrativo:
Documento adicionado: Contrato_Fornecedor.pdf

=== FILA DE IMPRESSÃO ===
1. Relatorio_Financeiro.pdf
2. Folha_de_Pagamento.pdf
3. Contrato_Fornecedor.pdf

As referências apontam para a mesma instância?
true
```

### Testando o bloqueio de criação direta

Para ver o construtor privado em ação, descomente em `Main.java` a linha:

```java
GerenciadorImpressao g = new GerenciadorImpressao();
```

A compilação falha com um erro parecido com: `GerenciadorImpressao() has private access in GerenciadorImpressao`.

## Desafio extra

As respostas estão no arquivo `Respostas_Desafio_Extra.docx`.
