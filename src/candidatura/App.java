package candidatura;

import java.util.List;

import candidatura.CentralDeLigacoes.ResultadoContato;

/**
 * Ponto de entrada da aplicacao.
 *
 * Monta a lista de candidatos e executa, em sequencia, todas as etapas do
 * processo seletivo: analise, selecao, impressao da lista e ligacoes finais
 * para os selecionados.
 */
public class App {

    public static void main(String[] args) {
        List<Candidato> candidatos = List.of(
                new Candidato("Felipe", 1900.0),
                new Candidato("Márcia", 2200.0),
                new Candidato("Júlia", 2000.0),
                new Candidato("Paulo", 1800.0),
                new Candidato("Augusto", 2100.0),
                new Candidato("Mabel", 1950.0),
                new Candidato("Laura", 2000.0),
                new Candidato("Ana", 1700.0),
                new Candidato("Louise", 2300.0),
                new Candidato("Túlio", 1850.0));

        CentralDeLigacoes central = new CentralDeLigacoes();
        ProcessoSeletivo processo = new ProcessoSeletivo(5, central);

        // 1. Analise condicional da pretensao salarial.
        processo.analisarCandidatos(candidatos);

        // 2. Selecao por ligacao ate preencher as vagas.
        List<Candidato> selecionados = processo.selecionarCandidatos(candidatos);

        // 3. Impressao da lista final.
        processo.imprimirSelecionados(selecionados);

        // 4. Ligacoes de confirmacao para os selecionados (fluxo excepcional/repeticao).
        System.out.println("\n===== LIGAÇÕES DE CONFIRMAÇÃO =====");
        for (Candidato candidato : selecionados) {
            ResultadoContato resultado = central.contatar(candidato);
            System.out.println(resultado.descricao());
        }
    }
}
