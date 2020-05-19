package jogoTruco;

public class Jogador {

    private Mao maoJogador;

    Jogador() {
        this.maoJogador = new Mao();

    }

    public Mao getMaoJogador() {
        return this.maoJogador;
    }

    public void setMaoJogador(Mao maoJogador) {
        this.maoJogador = maoJogador;
    }

}
