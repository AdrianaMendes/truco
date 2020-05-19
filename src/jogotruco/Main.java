package jogoTruco;

public class Main {
    public static void main(String args[]) {
        Truco truco = Truco.getInstancia();
        System.out.println("                          ***BEM VINDO AO JOGO***         \n");
        System.out.println("TIME 1 --> Jogador 1 e Jogador 3 \n");
        System.out.println("TIME 2 --> Jogador 2 e Jogador 4 \n");
        do {
            truco.sorteio();
            do {
                truco.menuJogaTruco();
                truco.incrementaVez();
                truco.verificaVencedor();
                System.out.println("\nPontuação Time 1: " + truco.getPontuacao1() + "\nPontuação Time 2: "
                        + truco.getPontuacao2());
            } while (!truco.isRodadaTerminada());
            truco.resetaRodada();
            truco.incrementaRodada();
        } while (!truco.fimDeJogo());
        System.out.println("Jogador " + truco.verificaPontuacao() + " Venceu!");
    }
}
