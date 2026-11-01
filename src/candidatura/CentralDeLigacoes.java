package candidatura;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Centraliza a logica de tentativas de contato telefonico com os candidatos.
 *
 * Isola a simulacao aleatoria de atendimento em um unico lugar, evitando
 * duplicacao e permitindo que a mesma regra seja reutilizada em toda a
 * aplicacao (fluxo de repeticao).
 */
public class CentralDeLigacoes {

    /** Numero maximo de tentativas de contato por candidato. */
    public static final int MAX_TENTATIVAS = 3;

    /**
     * Resultado de uma rodada de ligacoes para um candidato.
     *
     * @param candidato  o candidato contatado
     * @param atendeu    indica se o candidato atendeu dentro do limite de tentativas
     * @param tentativas quantidade de tentativas efetivamente realizadas
     */
    public record ResultadoContato(Candidato candidato, boolean atendeu, int tentativas) {

        public String descricao() {
            if (atendeu) {
                return String.format("Conseguimos contato com %s após %d tentativa(s).",
                        candidato.getNome(), tentativas);
            }
            return String.format("Não conseguimos contato com %s após %d tentativas.",
                    candidato.getNome(), tentativas);
        }
    }

    /**
     * Tenta contatar o candidato ate que ele atenda ou ate atingir o numero
     * maximo de tentativas (fluxo de repeticao com do-while).
     */
    public ResultadoContato contatar(Candidato candidato) {
        int tentativas = 0;
        boolean atendeu = false;

        do {
            tentativas++;
            atendeu = atendeuTelefonema();
        } while (!atendeu && tentativas < MAX_TENTATIVAS);

        return new ResultadoContato(candidato, atendeu, tentativas);
    }

    /**
     * Simula, de forma aleatoria, se o candidato atendeu ao telefonema.
     * Protegido para permitir sobrescrita deterministica em testes.
     */
    protected boolean atendeuTelefonema() {
        return ThreadLocalRandom.current().nextInt(1, 3) == 1;
    }
}
