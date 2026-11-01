package candidatura;

/**
 * Representa um candidato do processo seletivo.
 *
 * Encapsula o nome e o salario pretendido, alem de pequenas regras de dominio
 * relacionadas ao candidato (classificacao da pretensao salarial).
 */
public class Candidato {

    /** Salario base oferecido pela vaga. Usado como referencia na analise. */
    public static final double SALARIO_BASE = 2000.0;

    private final String nome;
    private final double salarioPretendido;

    public Candidato(String nome, double salarioPretendido) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do candidato e obrigatorio.");
        }
        if (salarioPretendido < 0) {
            throw new IllegalArgumentException("O salario pretendido nao pode ser negativo.");
        }
        this.nome = nome;
        this.salarioPretendido = salarioPretendido;
    }

    public String getNome() {
        return nome;
    }

    public double getSalarioPretendido() {
        return salarioPretendido;
    }

    /**
     * Classifica o candidato comparando a pretensao salarial com o salario base
     * da vaga (fluxo condicional).
     *
     * @return a situacao do candidato de acordo com a pretensao salarial
     */
    public SituacaoSalarial analisarPretensao() {
        if (SALARIO_BASE > salarioPretendido) {
            return SituacaoSalarial.DENTRO_DO_ORCAMENTO;
        } else if (SALARIO_BASE == salarioPretendido) {
            return SituacaoSalarial.EXIGE_CONTRAPROPOSTA;
        } else {
            return SituacaoSalarial.ACIMA_DO_ORCAMENTO;
        }
    }

    @Override
    public String toString() {
        return String.format("%s (pretensão: R$ %.2f)", nome, salarioPretendido);
    }

    /**
     * Situacoes possiveis de um candidato de acordo com a sua pretensao salarial.
     */
    public enum SituacaoSalarial {
        DENTRO_DO_ORCAMENTO("Ligar para o candidato."),
        EXIGE_CONTRAPROPOSTA("Ligar para o candidato com contraproposta."),
        ACIMA_DO_ORCAMENTO("Aguardando o resultado dos demais candidatos.");

        private final String mensagem;

        SituacaoSalarial(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }
    }
}
