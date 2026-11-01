package candidatura;

/**
 * Desafio final: Contador.
 *
 * Calcula a diferenca entre dois parametros inteiros e imprime uma contagem de
 * 1 ate essa diferenca (fluxo de repeticao). Quando os dois parametros sao
 * iguais, a diferenca e zero e nao ha o que contar; nesse caso e lancada a
 * excecao personalizada {@link ContadorException} (fluxo excepcional).
 *
 * Uso pela linha de comando:
 *
 * <pre>
 *   java candidatura.Contador 5 10    # conta de 1 ate 5
 *   java candidatura.Contador 10 10   # lanca ContadorException
 * </pre>
 */
public class Contador {

    public static void main(String[] args) {
        int parametroUm = 5;
        int parametroDois = 10;

        if (args.length == 2) {
            try {
                parametroUm = Integer.parseInt(args[0]);
                parametroDois = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Argumentos inválidos: informe dois números inteiros. "
                        + "Ex.: java candidatura.Contador 5 10");
                return;
            }
        } else if (args.length != 0) {
            System.out.println("Uso: java candidatura.Contador <numero1> <numero2>");
            return;
        }

        try {
            contar(parametroUm, parametroDois);
        } catch (ContadorException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Imprime a contagem de 1 ate o modulo da diferenca entre os dois
     * parametros.
     *
     * @throws ContadorException quando os parametros sao iguais
     */
    public static void contar(int parametroUm, int parametroDois) throws ContadorException {
        if (parametroUm == parametroDois) {
            throw new ContadorException(parametroUm);
        }

        int contagem = Math.abs(parametroDois - parametroUm);
        System.out.printf("Contando de 1 até %d:%n", contagem);

        for (int indice = 1; indice <= contagem; indice++) {
            System.out.println("Imprimindo o número " + indice);
        }
    }
}
