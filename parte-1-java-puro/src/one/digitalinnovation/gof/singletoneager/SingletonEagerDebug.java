package one.digitalinnovation.gof.singletoneager;

import one.digitalinnovation.gof.SingletonEager;

/**
 * Classe utilitária para execução e inspeção em modo Debug do padrão Singleton Eager.
 *
 * Sugestão de Debug no IntelliJ IDEA:
 * 1. Insira um Breakpoint na instrução do System.out antes da primeira chamada.
 * 2. Insira um Breakpoint na linha do 'SingletonEager.getInstancia()'.
 * 3. [DICA DE OURO] Coloque também um Breakpoint na linha da variável 'instancia' dentro da classe SingletonEager.
 * 4. Execute via Debug (Shift + F9).
 * 5. Observe no painel "Variables" que a atribuição estática ocorre imediatamente na CARGA da classe.
 *
 * @author Arthur
 */
public class SingletonEagerDebug {

    public static void main(String[] args) {
        System.out.println("=== INÍCIO DO TESTE DE DEBUG: SINGLETON EAGER ===");

        // [BREAKPOINT AQUI] A classe SingletonEager ainda NÃO foi carregada nem inicializada na memória.
        System.out.println("\n1. Ponto de controle: A classe SingletonEager ainda não foi tocada.");

        // [BREAKPOINT AQUI] Primeiro toque na classe:
        // A JVM vai carregar a classe, rodar a atribuição 'static final' (alocar na Heap) e DEPOIS retornar.
        System.out.println("\n2. Solicitando a primeira instância (eager1)...");
        SingletonEager eager1 = SingletonEager.getInstancia();
        System.out.println("   Endereço/Hash de eager1: " + eager1);

        // [BREAKPOINT AQUI] Segunda chamada: A classe já está inicializada, apenas devolve a referência já criada.
        System.out.println("\n3. Solicitando a segunda instância (eager2)...");
        SingletonEager eager2 = SingletonEager.getInstancia();
        System.out.println("   Endereço/Hash de eager2: " + eager2);

        // Validação de Identidade
        System.out.println("\n4. Comparando referências de memória (eager1 == eager2):");
        if (eager1 == eager2) {
            System.out.println("   [SUCESSO] Ambas as variáveis apontam para a MESMA instância!");
        } else {
            System.out.println("   [FALHA] Foram criadas instâncias diferentes!");
        }

        System.out.println("\n=== FIM DO TESTE DE DEBUG ===");
    }
}