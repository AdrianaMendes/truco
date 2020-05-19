package jogoTruco;

public class Jogador {

	private Mao maoJogador; // mão de cartas de um jogador

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
