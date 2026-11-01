package candidatura;

/**
 * Excecao personalizada lancada quando os dois parametros do contador sao
 * iguais, situacao em que nao ha intervalo para contar.
 */
public class ContadorException extends Exception {

    public ContadorException(int parametro) {
        super(String.format(
                "Os dois parâmetros não podem ter o mesmo valor (ambos valem %d).", parametro));
    }
}
