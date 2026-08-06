# 🔍 Detalhamento Prático & Debugging: Singleton Lazy (Preguiçoso)

Este documento apresenta a análise factual do fluxo de execução e a inspeção de memória da variação **Singleton Lazy**, realizada através do depurador (*debugger*) da IDE **IntelliJ IDEA**.

---

## 📌 1. Conceito do Padrão
O **Singleton Lazy** (Preguiçoso) adia a criação do objeto até o exato momento em que ele é requisitado pela primeira vez através do método estático `getInstancia()`. 

- **Vantagem:** Economia de recursos de memória/CPU na inicialização da aplicação.
- **Característica:** Instanciação sob demanda (*lazy initialization*).

---

## 💻 2. Código-Fonte Analisado

### `SingletonLazy.java`
```java
package one.digitalinnovation.gof;

/**
 * Singleton "Preguiçoso" (Lazy)
 * 
 * @author faldev / Arthur
 */
public class SingletonLazy {

    private static SingletonLazy instancia;

    private SingletonLazy() {
        super();
    }

    public static SingletonLazy getInstancia() {
        if (instancia == null) {
            instancia = new SingletonLazy();
        }
        return instancia;
    }
}

```

---

## 🛠️ 3. Passo a Passo do Debugging no IntelliJ IDEA

### Passo 1: Configuração dos Breakpoints

Para acompanhar a criação do objeto e a verificação condicional, foram inseridos pontos de interrupção (*breakpoints*) nas linhas-chave:

* No método `main` da classe `Test.java` (na atribuição de `lazy1`).
* Dentro da classe `SingletonLazy.java`, na linha `if (instancia == null)`.


*Figura 1: Marcação dos pontos de interrupção nas linhas de controle de fluxo.*

---

### Passo 2: Primeira Chamada — `lazy1 = SingletonLazy.getInstancia()`

Ao iniciar o programa em modo **Debug (Shift + F9)**, o controle avança para dentro de `getInstancia()`.


*Figura 2: Inspeção do painel Variables. O atributo estático `instancia` possui valor inicial `null`.*

1. A condição `instancia == null` é avaliada como `true`.
2. O bloco entra na linha `instancia = new SingletonLazy();`.
3. A JVM aloca o novo objeto na memória Heap (ex: endereço/ID `@452`).

---

### Passo 3: Atribuição e Retorno da Primeira Referência

Ao avançar o código (`Step Over - F8`), a variável local `lazy1` recebe o ponteiro do objeto recém-criado.


*Figura 3: Variável `lazy1` apontando para o objeto de ID `@452`.*

---

### Passo 4: Segunda Chamada — `lazy2 = SingletonLazy.getInstancia()`

Na linha seguinte do teste, é solicitada a instância novamente para a variável `lazy2`.


*Figura 4: Avaliação do `if (instancia == null)`. Como `instancia` já contém `@452`, a condição é `false`.*

1. A condição `instancia == null` falha (`false`).
2. O bloco `new SingletonLazy()` **é ignorado**, evitando nova alocação de memória.
3. O método retorna diretamente a referência pré-existente (`instancia`).

---

### Passo 5: Validação da Identidade de Memória

Inspecionando as variáveis locais no encerramento da execução:


*Figura 5: Painel de Debug comprovando que `lazy1` e `lazy2` apontam para o mesmo identificador na Heap.*

---

## 📊 4. Matriz de Rastreamento de Estado

| Etapa | Instrução Executada | Estado de `instancia` | Resultado Condicional | Referência Retornada |
| --- | --- | --- | --- | --- |
| **1** | `SingletonLazy.getInstancia()` (1ª vez) | `null` | `true` (Cria objeto) | `SingletonLazy@452` |
| **2** | `System.out.println(lazy1)` | `SingletonLazy@452` | N/A | Prints `...SingletonLazy@452` |
| **3** | `SingletonLazy.getInstancia()` (2ª vez) | `SingletonLazy@452` | `false` (Reaproveita) | `SingletonLazy@452` |
| **4** | `System.out.println(lazy2)` | `SingletonLazy@452` | N/A | Prints `...SingletonLazy@452` |

---

## ⚠️ 5. Observação Técnica (Concorrência / Threads)

Esta implementação básica **não é thread-safe**. Caso múltiplas *threads* acessem o método `getInstancia()` simultaneamente no estado inicial (`instancia == null`), há risco de condição de corrida (*race condition*), resultando na criação de múltiplos objetos. Para cenários *multithread*, utilize a variação **SingletonLazyHolder** ou controle por concorrência.

```

---

### Link para Inserir no README Principal

Ao final da seção **"Variação 1: SingletonLazy — O Preguiçoso"** do seu `README.md` principal, adicione o seguinte trecho:

```markdown
> 🔍 **Aprofundamento Prático & Debugging:**
> Para conferir o detalhamento da execução passo a passo no IntelliJ IDEA, com capturas de tela do painel de variáveis e análise de alocação de memória, acesse o [README de Debugging do Singleton Lazy](./src/one/digitalinnovation/gof/singleton-lazy/README.md).