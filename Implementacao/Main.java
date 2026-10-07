/**
 * Simula diferentes setores de uma empresa usando o sistema de impressão.
 * Todos obtêm o gerenciador por meio de GerenciadorImpressao.getInstancia().
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE IMPRESSÃO ===");
        System.out.println();

        // Primeira referência, obtida no início do programa.
        GerenciadorImpressao gerenciador1 = GerenciadorImpressao.getInstancia();

        // Setor Financeiro
        System.out.println("Setor Financeiro:");
        GerenciadorImpressao financeiro = GerenciadorImpressao.getInstancia();
        financeiro.adicionarDocumento("Relatorio_Financeiro.pdf");
        System.out.println();

        // Setor RH
        System.out.println("Setor RH:");
        GerenciadorImpressao rh = GerenciadorImpressao.getInstancia();
        rh.adicionarDocumento("Folha_de_Pagamento.pdf");
        System.out.println();

        // Setor Administrativo
        System.out.println("Setor Administrativo:");
        GerenciadorImpressao administrativo = GerenciadorImpressao.getInstancia();
        administrativo.adicionarDocumento("Contrato_Fornecedor.pdf");
        System.out.println();

        // Visualização da fila (qualquer referência mostra a mesma fila).
        gerenciador1.visualizarFila();
        System.out.println();

        // Segunda referência, obtida em outro momento.
        GerenciadorImpressao gerenciador2 = GerenciadorImpressao.getInstancia();

        System.out.println("As referências apontam para a mesma instância?");
        System.out.println(gerenciador1 == gerenciador2);

        // A linha abaixo NÃO compila, pois o construtor é privado:
        // GerenciadorImpressao g = new GerenciadorImpressao();
    }
}
