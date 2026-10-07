import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gerenciador de impressão implementado com o padrão Singleton.
 * Garante que exista uma única fila de documentos durante a execução.
 */
public class GerenciadorImpressao {

    // Única instância da classe. "volatile" garante visibilidade entre threads.
    private static volatile GerenciadorImpressao instancia;

    // Fila de documentos aguardando impressão (representados pelo nome).
    private final List<String> fila;

    // Construtor privado: impede "new GerenciadorImpressao()" fora da classe.
    private GerenciadorImpressao() {
        this.fila = new ArrayList<>();
    }

    /**
     * Ponto de acesso global à instância única.
     * A primeira chamada cria o gerenciador; as seguintes retornam o mesmo objeto.
     * Usa double-checked locking para ser seguro em ambiente com várias threads.
     */
    public static GerenciadorImpressao getInstancia() {
        if (instancia == null) {
            synchronized (GerenciadorImpressao.class) {
                if (instancia == null) {
                    instancia = new GerenciadorImpressao();
                }
            }
        }
        return instancia;
    }

    /** Adiciona um documento ao final da fila. */
    public synchronized void adicionarDocumento(String nomeDocumento) {
        if (nomeDocumento == null || nomeDocumento.isBlank()) {
            throw new IllegalArgumentException("O nome do documento não pode ser vazio.");
        }
        fila.add(nomeDocumento);
        System.out.println("Documento adicionado: " + nomeDocumento);
    }

    /** Exibe os documentos que estão aguardando impressão. */
    public synchronized void visualizarFila() {
        System.out.println("=== FILA DE IMPRESSÃO ===");
        if (fila.isEmpty()) {
            System.out.println("(fila vazia)");
            return;
        }
        for (int i = 0; i < fila.size(); i++) {
            System.out.println((i + 1) + ". " + fila.get(i));
        }
    }

    /** Retorna uma visão somente leitura da fila (útil para testes). */
    public synchronized List<String> getFila() {
        return Collections.unmodifiableList(new ArrayList<>(fila));
    }
}
