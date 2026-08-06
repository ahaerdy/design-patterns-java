```python
import weasyprint

html_content = """
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<style>
  @page {
    size: A4;
    margin: 15mm 12mm;
    background-color: #ffffff;
  }
  *, *::before, *::after {
    box-sizing: border-box;
  }
  body {
    font-family: Arial, Helvetica, sans-serif;
    font-size: 10pt;
    line-height: 1.5;
    color: #24292e;
    margin: 0;
    padding: 0;
  }
  
  .header-banner {
    background-color: #0969da;
    color: #ffffff;
    padding: 20px 15mm;
    margin: -15mm -12mm 20px -12mm;
  }
  .header-banner h1 {
    font-size: 18pt;
    margin: 0 0 5px 0;
    font-weight: bold;
    color: #ffffff;
  }
  .header-banner p {
    font-size: 10pt;
    margin: 0;
    opacity: 0.9;
  }
  
  h1, h2, h3, h4 {
    color: #1f2328;
    page-break-after: avoid;
  }
  h2 {
    font-size: 14pt;
    border-bottom: 2px solid #eaeef2;
    padding-bottom: 4px;
    margin-top: 20px;
    margin-bottom: 12px;
  }
  h3 {
    font-size: 11pt;
    margin-top: 14px;
    margin-bottom: 6px;
    color: #0969da;
  }

  p {
    margin-top: 0;
    margin-bottom: 10px;
  }

  code {
    font-family: "Courier New", Courier, monospace;
    background-color: #eff1f3;
    padding: 2px 4px;
    border-radius: 3px;
    font-size: 9.5pt;
  }

  pre {
    background-color: #f6f8fa;
    border: 1px solid #d0d7de;
    border-radius: 6px;
    padding: 10px 12px;
    font-family: "Courier New", Courier, monospace;
    font-size: 9pt;
    line-height: 1.4;
    overflow-x: auto;
    margin-top: 6px;
    margin-bottom: 12px;
    white-space: pre-wrap;
    word-break: break-all;
  }

  .callout {
    background-color: #f0f7ff;
    border-left: 4px solid #0969da;
    padding: 10px 12px;
    margin: 12px 0;
    border-radius: 0 4px 4px 0;
  }

  .callout-warning {
    background-color: #fff8c5;
    border-left: 4px solid #d97706;
    padding: 10px 12px;
    margin: 12px 0;
    border-radius: 0 4px 4px 0;
  }

  table {
    width: 100%;
    border-collapse: collapse;
    margin: 12px 0;
    font-size: 9.5pt;
  }
  th, td {
    border: 1px solid #d0d7de;
    padding: 6px 10px;
    text-align: left;
  }
  th {
    background-color: #f6f8fa;
    font-weight: bold;
  }
  tr:nth-child(even) {
    background-color: #fcfcfc;
  }

  .img-placeholder {
    border: 2px dashed #0969da;
    background-color: #f6f8fa;
    padding: 12px;
    text-align: center;
    margin: 10px 0 6px 0;
    border-radius: 4px;
    font-weight: bold;
    color: #0969da;
  }

  .img-caption {
    font-size: 8.5pt;
    color: #57606a;
    font-style: italic;
    margin-bottom: 14px;
    text-align: center;
  }

  ul, ol {
    margin-top: 0;
    margin-bottom: 10px;
    padding-left: 20px;
  }
  li {
    margin-bottom: 4px;
  }

  .badge {
    display: inline-block;
    padding: 2px 6px;
    font-size: 8pt;
    font-weight: bold;
    color: #ffffff;
    background-color: #2da44e;
    border-radius: 10px;
  }
</style>
</head>
<body>

<div class="header-banner">
  <h1>Laboratório de Debugging: Singleton Lazy (Preguiçoso)</h1>
  <p>Análise de Execução Passo a Passo e Inspeção de Memória no IntelliJ IDEA</p>
</div>

<div class="callout">
  <strong>Módulo:</strong> <code>parte-1-java-puro</code> &nbsp;|&nbsp; 
  <strong>Padrão:</strong> Singleton (Criacional) &nbsp;|&nbsp; 
  <strong>Variação:</strong> <code>SingletonLazy</code>
</div>

<h2>1. Visão Geral do Padrão</h2>
<p>
  O <strong>Singleton Lazy (Preguiçoso)</strong> adia a instanciação do objeto até o momento exato em que ele é solicitado pela primeira vez através do método <code>getInstancia()</code>. Essa abordagem economiza recursos de memória e processamento na inicialização da aplicação, sendo ideal quando a criação do objeto é dispendiosa e seu uso não é garantido imediatamente.
</p>

<h2>2. Código-Fonte sob Análise</h2>

<h3>2.1. Classe <code>SingletonLazy.java</code></h3>
<pre><code>package one.digitalinnovation.gof;

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
}</code></pre>

<h3>2.2. Classe de Teste (Trecho) <code>Test.java</code></h3>
<pre><code>package one.digitalinnovation.gof;

public class Test {
    public static void main(String[] args) {
        // Testes do Padrão Singleton Lazy
        SingletonLazy lazy1 = SingletonLazy.getInstancia();
        System.out.println(lazy1);
        
        SingletonLazy lazy2 = SingletonLazy.getInstancia();
        System.out.println(lazy2);
    }
}</code></pre>

<h2>3. Passo a Passo do Debugging no IntelliJ IDEA</h2>

<h3>Passo 1: Configuração dos Breakpoints</h3>
<p>
  Para acompanhar a criação da instância e validar a garantia de identidade do Singleton, foram inseridos breakpoints nas seguintes linhas estratégicas:
</p>
<ul>
  <li><code>SingletonLazy.java</code>: Na instrução <code>if (instancia == null)</code> dentro do método <code>getInstancia()</code>.</li>
  <li><code>Test.java</code>: Na chamada inicial <code>SingletonLazy lazy1 = SingletonLazy.getInstancia();</code>.</li>
</ul>

<div class="img-placeholder">
  📷 [PRINT INTELIJ 01: Breakpoints configurados em Test.java e SingletonLazy.java]
</div>
<div class="img-caption">
  Figura 1: Marcação dos pontos de interrupção (red dots) nas linhas de controle e atribuição de referência.
</div>

<h3>Passo 2: Primeira Chamada — <code>lazy1 = SingletonLazy.getInstancia()</code></h3>
<p>
  Ao iniciar a execução em modo <strong>Debug (Shift + F9)</strong>, a linha de execução pausa na primeira chamada de <code>getInstancia()</code> em <code>Test.java</code>. Ao avançar com <code>Step Into (F7)</code>, o controle entra no método da classe <code>SingletonLazy</code>.
</p>

<div class="img-placeholder">
  📷 [PRINT INTELIJ 02: Inspeção do painel Variables — instancia é null]
</div>
<div class="img-caption">
  Figura 2: Avaliação da condição <code>instancia == null</code>. O atributo estático encontra-se com o valor inicial <code>null</code>.
</div>

<p>
  Como a condição avalia para <code>true</code>, a execução entra no bloco condicional e aloca o novo objeto via <code>new SingletonLazy()</code>. O Garbage Collector/JVM atribui um ID de objeto (ex: <code>@452</code>).
</p>

<h3>Passo 3: Atribuição e Retorno de <code>lazy1</code></h3>
<p>
  Ao executar <code>Step Over (F8)</code>, a variável local <code>lazy1</code> no método <code>main</code> recebe a referência gravada em <code>instancia</code>.
</p>

<div class="img-placeholder">
  📷 [PRINT INTELIJ 03: Variável lazy1 populada no painel de Debug]
</div>
<div class="img-caption">
  Figura 3: Inspeção da variável <code>lazy1</code> indicando o endereço/hash de memória <code>one.digitalinnovation.gof.SingletonLazy@452</code>.
</div>

<h3>Passo 4: Segunda Chamada — <code>lazy2 = SingletonLazy.getInstancia()</code></h3>
<p>
  Acompanhando o fluxo até a segunda chamada de <code>getInstancia()</code>, o código retorna à checagem condicional <code>if (instancia == null)</code>.
</p>

<div class="img-placeholder">
  📷 [PRINT INTELIJ 04: Avaliação do `if (instancia == null)` na 2ª execução]
</div>
<div class="img-caption">
  Figura 4: O atributo <code>instancia</code> agora já contém o objeto instanciado anteriormente (<code>@452</code>). A condição avalia para <code>false</code>.
</div>

<p>
  O bloco de inicialização é ignorado, garantindo que <strong>nenhum novo objeto seja criado</strong>. O método imediatamente retorna a instância pré-existente.
</p>

<h3>Passo 5: Validação da Identidade de Memória</h3>
<p>
  Após a conclusão da atribuição de <code>lazy2</code>, inspecionamos o painel <strong>Variables</strong> e o console de saída.
</p>

<div class="img-placeholder">
  📷 [PRINT INTELIJ 05: Inspeção final das variáveis lazy1 e lazy2]
</div>
<div class="img-caption">
  Figura 5: Painel de variáveis demonstrando que ambas apontam exatamente para o mesmo hash de memória (<code>@452</code>).
</div>

<h2>4. Tabela Comparativa de Estado durante a Execução</h2>

<table>
  <thead>
    <tr>
      <th>Etapa</th>
      <th>Instrução Executada</th>
      <th>Estado de <code>instancia</code></th>
      <th>Resultado da Condição</th>
      <th>Referência Retornada</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>1</td>
      <td><code>SingletonLazy.getInstancia()</code> (1ª vez)</td>
      <td><code>null</code></td>
      <td><code>true</code> (Cria novo objeto)</td>
      <td><code>SingletonLazy@452</code></td>
    </tr>
    <tr>
      <td>2</td>
      <td><code>System.out.println(lazy1)</code></td>
      <td><code>SingletonLazy@452</code></td>
      <td>N/A</td>
      <td>Imprime <code>...SingletonLazy@452</code></td>
    </tr>
    <tr>
      <td>3</td>
      <td><code>SingletonLazy.getInstancia()</code> (2ª vez)</td>
      <td><code>SingletonLazy@452</code></td>
      <td><code>false</code> (Reaproveita)</td>
      <td><code>SingletonLazy@452</code></td>
    </tr>
    <tr>
      <td>4</td>
      <td><code>System.out.println(lazy2)</code></td>
      <td><code>SingletonLazy@452</code></td>
      <td>N/A</td>
      <td>Imprime <code>...SingletonLazy@452</code></td>
    </tr>
  </tbody>
</table>

<h2>5. Pontos de Atenção e Boas Práticas</h2>

<div class="callout-warning">
  <strong>Atenção em Ambientes Multi-thread:</strong><br>
  Esta variação básica (<code>SingletonLazy</code>) <strong>não é thread-safe</strong>. Se duas threads acessarem <code>getInstancia()</code> simultaneamente quando <code>instancia == null</code>, ambas podem passar pela checagem e criar duas instâncias distintas na memória.<br><br>
  Para cenários concorrentes, consulte as variações <code>SingletonLazyHolder</code> ou sincronização via bloco/método <code>synchronized</code>.
</div>

<h2>6. Conclusão</h2>
<p>
  O teste prático em modo Debug no IntelliJ IDEA comprova empiricamente a dinâmica do Singleton Lazy:
</p>
<ol>
  <li><strong>Instanciação Sob Demanda:</strong> O objeto só é criado no primeiro acesso.</li>
  <li><strong>Unicidade de Referência:</strong> Chamadas subsequentes reutilizam a mesma instância, economizando memória e mantendo a consistência do estado global.</li>
</ol>

</body>
</html>
"""

with open("README-singleton-lazy.html", "w", encoding="utf-8") as f:
    f.write(html_content)

html_doc = weasyprint.HTML(filename="README-singleton-lazy.html")
html_doc.write_pdf("README-singleton-lazy-debug.pdf")
print("PDF gerado com sucesso!")


```

```text
PDF gerado com sucesso!


```

Seu documento contendo o passo a passo completo da execução com *debug* e a estrutura didática do **README** para a subpasta está pronto.

[file-tag: code-generated-file-1341aca7-4a43-4ec4-9492-f8a4837d2c59]

---

### Estrutura de Pastas Sugerida no Repositório

Para organizar a subpasta e manter os arquivos vinculados corretamente, configure a estrutura do seu projeto da seguinte forma:

```text
parte-1-java-puro/
├── README.md
└── src/
    └── one/digitalinnovation/gof/
        ├── singleton-lazy/                <-- Subpasta criada
        │   ├── README.md                  <-- O README detalhado abaixo
        │   └── docs/                      <-- Imagens das capturas de tela do IntelliJ
        │       ├── print-01-breakpoints.png
        │       ├── print-02-instancia-null.png
        │       ├── print-03-lazy1-atribuido.png
        │       ├── print-04-segunda-chamada.png
        │       └── print-05-painel-variaveis.png
        ├── SingletonLazy.java
        ├── SingletonEager.java
        ├── SingletonLazyHolder.java
        └── Test.java

```

---

### Conteúdo Completo do `README.md` (Para colar dentro da subpasta `singleton-lazy/`)

```markdown
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

```