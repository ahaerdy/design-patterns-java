package one.digitalinnovation.gof.singletonlazy;

import one.digitalinnovation.gof.SingletonLazy;

/**
 * Classe utilitária para execução e inspeção em modo Debug do padrão Singleton Lazy.
 *
 * Sugestão de Debug no IntelliJ IDEA:
 * 1. Insira um Breakpoint na primeira chamada de 'SingletonLazy.getInstancia()'.
 * 2. Insira um Breakpoint na segunda chamada de 'SingletonLazy.getInstancia()'.
 * 3. Execute via Debug (Shift + F9).
 * 4. Observe o valor das referências de memória no painel "Variables".
 *
 */
public class SingletonLazyDebug {

    public static void main(String[] args) {
        System.out.println("=== INÍCIO DO TESTE DE DEBUG: SINGLETON LAZY ===");

        // [BREAKPOINT AQUI] Primeira chamada: Deve instanciar o objeto na memória Heap
        System.out.println("\n1. Solicitando a primeira instância (lazy1)...");
        SingletonLazy lazy1 = SingletonLazy.getInstancia();
        System.out.println("   Endereço/Hash de lazy1: " + lazy1);

        // [BREAKPOINT AQUI] Segunda chamada: Deve reaproveitar a instância pré-existente
        System.out.println("\n2. Solicitando a segunda instância (lazy2)...");
        SingletonLazy lazy2 = SingletonLazy.getInstancia();
        System.out.println("   Endereço/Hash de lazy2: " + lazy2);

        // Validação de Identidade
        System.out.println("\n3. Comparando referências de memória (lazy1 == lazy2):");
        if (lazy1 == lazy2) {
            System.out.println("   [SUCESSO] Ambas as variáveis apontam para a MESMA instância!");
        } else {
            System.out.println("   [FALHA] Foram criadas instâncias diferentes!");
        }

        System.out.println("\n=== FIM DO TESTE DE DEBUG ===");
    }
}
