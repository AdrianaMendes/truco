package jogoTruco;

public class Carta {

	private char numero;
	private int naipe;

	Carta(char numero, int naipe) {
		this.numero = numero;
		this.naipe = naipe;

	}

	Carta() {

	}

	Carta(Carta x) {
		this.numero = x.getNumero();
		this.naipe = x.getNaipe();

	}

	public boolean verificaIgual(Carta c) {
		return this.numero == c.getNumero() && this.naipe == c.getNaipe();

	}

	public char getNumero() {
		return numero;
	}

	public void setNumero(char numero) {
		this.numero = numero;
	}

	public int getNaipe() {
		return naipe;
	}

	public void setNaipe(int naipe) {
		this.naipe = naipe;
	}

	public String toString() {
		return numero + " " + getStringNipe(naipe);
	}

	public boolean verificaMaiorCarta(Carta x) {
		return (Baralho.retornaValor(this.numero, this.naipe) > Baralho.retornaValor(x.getNumero(), x.getNaipe()));

	}

	public boolean verificaMesmoValor(Carta x) {
		return (Baralho.retornaValor(this.numero, this.naipe) == Baralho.retornaValor(x.getNumero(), x.getNaipe()));

	}

	private String getStringNipe(int p_Nipe) {
		switch (p_Nipe) {
		case 0:
			return "Paus";
		case 1:
			return "Copas";
		case 2:
			return "Espada";
		case 3:
			return "Ouros";
		default:
			return "Erro";
		}
	}

}
