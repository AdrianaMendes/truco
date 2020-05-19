package jogoTruco;

import java.util.ArrayList; //Uma estrutura de dados de tamanho variavel

public class Baralho {

	private char c[] = { 'A', '2', '3', '4', '5', '6', '7', 'J', 'Q', 'K' };
	private ArrayList<Carta> utilizadas; // Marca as cartas utilizadas

	private Baralho() {
		utilizadas = new ArrayList<>();

	}

	private static Baralho instancia = null;

	public static Baralho getInstancia() {
		if (instancia == null) {
			instancia = new Baralho();

		}
		return instancia;
	}

	public Carta retornaCarta() { // Vai gerar uma carta aleatória
		Carta X = new Carta();

		boolean flag;

		do {
			flag = false;

			int k = (int) (Math.random() * 10); // Indice aleatório do vetor c
			int n = (int) (Math.random() * 4); // Naipe aleatório
			X.setNumero(c[k]);
			X.setNaipe(n);

			for (int i = 0; i < utilizadas.size(); i++) { // Verfica se existe
															// na Arraylist de
															// utilizados
				if (utilizadas.get(i).verificaIgual(X)) {
					flag = true;
					break;
				}

			}

		} while (flag);
		utilizadas.add(X);

		return new Carta(X.getNumero(), X.getNaipe());
	}

	public void limpa() {
		utilizadas.clear();
	}

	public static int retornaValor(char numero, int naipe) { // Associa o peso
																// de cada carta
		int valor = numero - 52;
		if (numero == 'A' || numero == 'J' || numero == 'Q' || numero == 'K') {
			switch (numero) {
			case 'Q':
				valor = 4;
				break;
			case 'J':
				valor = 5;
				break;
			case 'K':
				valor = 6;
				break;
			case 'A':
				valor = 7;
				break;

			}
		}
		if (numero == '2')
			valor = 8;
		else if (numero == '3')
			valor = 9;
		else if (numero == '4' && naipe == 0) // Paus
			valor = 13;
		else if (numero == '7' && naipe == 1)// Copas
			valor = 12;
		else if (numero == 'A' && naipe == 2)// Espada
			valor = 11;
		else if (numero == '7' && naipe == 3)// Ouro
			valor = 10;

		return valor;
	}
}
