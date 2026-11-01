package candidatura;

import java.util.ArrayList;
import java.util.List;

import candidatura.Candidato.SituacaoSalarial;
import candidatura.CentralDeLigacoes.ResultadoContato;

/**
 * Orquestra as etapas do processo seletivo:
 *
 * <ol>
 *   <li>Analisar os candidatos de acordo com a pretensao salarial.</li>
 *   <li>Selecionar candidatos ate o preenchimento das vagas disponiveis.</li>
 *   <li>Imprimir a lista dos candidatos realmente selecionados.</li>
 * </ol>
 *
 * Reune os fluxos condicional e de repeticao usando objetos de dominio
 * ({@link Candidato}) e delegando o contato telefonico a
 * {@link CentralDeLigacoes}.
 */
public class ProcessoSeletivo {

    private final int limiteVagas;
    private final CentralDeLigacoes central;

    public ProcessoSeletivo(int limiteVagas, CentralDeLigacoes central) {
        if (limiteVagas <= 0) {
            throw new IllegalArgumentException("O numero de vagas deve ser positivo.");
        }
        this.limiteVagas = limiteVagas;
        this.central = central;
    }

    /**
     * Analisa cada candidato e imprime a recomendacao de acordo com a pretensao
     * salarial (fluxo condicional, encapsulado em {@link Candidato}).
     */
    public void analisarCandidatos(List<Candidato> candidatos) {
        System.out.println("===== ANÁLISE DOS CANDIDATOS =====");
        for (Candidato candidato : candidatos) {
            SituacaoSalarial situacao = candidato.analisarPretensao();
            System.out.printf("%-28s -> %s%n", candidato, situacao.getMensagem());
        }
    }

    /**
     * Percorre a lista ligando para os candidatos e seleciona os que atenderem,
     * ate preencher as vagas disponiveis (fluxo de repeticao com while).
     *
     * @return a lista dos candidatos efetivamente selecionados
     */
    public List<Candidato> selecionarCandidatos(List<Candidato> candidatos) {
        System.out.println("\n===== SELEÇÃO DE CANDIDATOS (" + limiteVagas + " vaga(s)) =====");

        List<Candidato> selecionados = new ArrayList<>();
        int indice = 0;

        while (selecionados.size() < limiteVagas && indice < candidatos.size()) {
            Candidato candidato = candidatos.get(indice);
            ResultadoContato resultado = central.contatar(candidato);

            if (resultado.atendeu()) {
                selecionados.add(candidato);
                System.out.printf("%s ATENDEU e foi SELECIONADO.%n", candidato.getNome());
            } else {
                System.out.printf("%s não atendeu a ligação.%n", candidato.getNome());
            }
            indice++;
        }

        if (selecionados.size() < limiteVagas) {
            System.out.printf("Atenção: apenas %d de %d vaga(s) preenchida(s).%n",
                    selecionados.size(), limiteVagas);
        }
        return selecionados;
    }

    /**
     * Imprime a lista dos candidatos selecionados (fluxo de repeticao com for).
     */
    public void imprimirSelecionados(List<Candidato> selecionados) {
        System.out.println("\n===== CANDIDATOS SELECIONADOS =====");
        if (selecionados.isEmpty()) {
            System.out.println("Nenhum candidato foi selecionado.");
            return;
        }
        for (int i = 0; i < selecionados.size(); i++) {
            System.out.printf("O candidato de n. %d é %s%n", i + 1, selecionados.get(i).getNome());
        }
    }
}
