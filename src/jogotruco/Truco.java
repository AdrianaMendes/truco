package jogoTruco;

import java.util.Scanner;

public class Truco {

	private Jogador[] jogadores;
	private int Pontuacao1 = 0;
	private int Pontuacao2 = 0;
	private int Rodadas = 0;
	private int valorRodada = 2;
	private Baralho baralho;
	private boolean rodadaTerminada = false;
	private int vezRodada = 0;
	private Monte monte;
	private int[] maoTime;
	private int pedirUltimo= -1;

	private Truco() {
		jogadores = new Jogador[4];
		for (int i = 0; i < 4; i++) {
			jogadores[i] = new Jogador();
		}
		this.baralho = Baralho.getInstancia();
		this.monte = Monte.getInstancia();
		this.maoTime = new int[3];
	}

	private static Truco instancia = null;

	public static Truco getInstancia() {
		if (instancia == null) {
			instancia = new Truco();

		}
		return instancia;
	}

	public void sorteio() {

		for (int i = 0; i < 4; i++) { // Embaralha as cartas
			this.jogadores[i].getMaoJogador().recebeCarta(new Carta[] { this.baralho.retornaCarta(),
					this.baralho.retornaCarta(), this.baralho.retornaCarta() });

		}

	}

	public boolean fimDeJogo() {
		return Pontuacao1 >= 12 || Pontuacao2 >= 12;
	}

	public void aumentaRodada() {
		switch (valorRodada) {

		case 4:
			valorRodada = 8;
			break;
		case 8:
			valorRodada = 10;
			break;
		case 10:
			valorRodada = 12;
			break;
		default:
			valorRodada = 4;

		}
	}

	public void incrementaRodada() {
		this.Rodadas++;
		int n = ((int) (this.Rodadas / 4)) * 4;
		this.vezRodada = this.Rodadas - n;
	}

	public int getValorRodada() {
		return valorRodada;
	}

	public Jogador retornaJogador(int x) {
		return this.jogadores[x];

	}

	public void voltarCartas() {
		baralho.limpa();
	}

	public void resetaRodada() {
		valorRodada = 2;
		this.voltarCartas();
		this.monte.limpaMonte();
		rodadaTerminada = false;
		pedirUltimo=-1;
	}

	public boolean isRodadaTerminada() {
		return rodadaTerminada;
	}

	public void setRodadaTerminada(boolean rodadaTerminada) {
		this.rodadaTerminada = rodadaTerminada;
	}

	public int getVezRodada() {
		return vezRodada;
	}

	public void pontuacao(int x) {
		if (x == 0) {
			Pontuacao1 += valorRodada;
		} else
			Pontuacao2 += valorRodada;
	}

	public void verificaVencedor() {
		monte.teste();
		if (!rodadaTerminada) {
			if (monte.retonaSize() == 4) {
				if (monte.checkAmarrado()) {
					int qtdCartas = jogadores[0].getMaoJogador().getQtdCartasMao();
					if (qtdCartas == 0) {
						pontuacao(maoTime[0]);
						rodadaTerminada = true;
						System.out.print("Time " + maoTime[0]+" Fez a primeira e venceu a Rodada");
					} else {
						int x = 0;
						int indice = 0;
						for (int i = 0; i < 4; i++) {
							for (int j = 0; j < jogadores[i].getMaoJogador().getQtdCartasMao(); j++) {
								if (x < Baralho.retornaValor(
										jogadores[i].getMaoJogador().getCartasIndice(j).getNumero(),
										jogadores[i].getMaoJogador().getCartasIndice(j).getNaipe())) {
									x = Baralho.retornaValor(
											jogadores[i].getMaoJogador().getCartasIndice(j).getNumero(),
											jogadores[i].getMaoJogador().getCartasIndice(j).getNaipe());
									indice = i;

								}

							}
						}
						pontuacao(indice % 2);
						rodadaTerminada = true;

					}

				} else {
					vezRodada = monte.maiorCarta();
					System.out.print("Jogador "+ (vezRodada+1)+" Fez a mão ");
					int qtdCartas = jogadores[0].getMaoJogador().getQtdCartasMao();
					maoTime[2 - qtdCartas] = vezRodada % 2;

					if (qtdCartas == 1) {
						if (maoTime[0] == maoTime[1]) {
							pontuacao(maoTime[0]);
							rodadaTerminada = true;
						}

					} else if (qtdCartas == 0) {
						pontuacao(maoTime[2]);
						rodadaTerminada = true;

					}
				}
				monte.limpaMonte();
			}
		}
	}

	public void menuJogaTruco() {

		boolean flag;
		do {
			System.out.println("\n---Jogador " + (vezRodada + 1) + "----");
			for (int i = 0; i < jogadores[vezRodada].getMaoJogador().getQtdCartasMao(); i++) {
				System.out.println(jogadores[vezRodada].getMaoJogador().getCartasIndice(i));
			}
			System.out.print("\n");

			System.out.println("1.Jogue a Carta");
			System.out.println("2.Pedir " + this.estadoRodada());
			Scanner entrada = new Scanner(System.in);
			int a = entrada.nextInt();

			flag = false;

			try {
				switch (a) {

				case 1:
					this.menuImprimirCartas();
					break;
				case 2:
					if(pedirUltimo==vezRodada%2 && pedirUltimo!=-1){
						System.out.println("O seu Time não pode pedir " +this.estadoRodada());
						throw new OpcaoInvalidaException();
					}
					this.menuAceitaRecusa();
					
					break;
				default:
					throw new OpcaoInvalidaException();
				}
			} catch (OpcaoInvalidaException e) {
				System.out.println(e.getMessage());
				flag = true;
			}

		} while (flag);

	}

	private void menuAceitaRecusa() {

		boolean flag;
		do {
			if(vezRodada==3){
				System.out.println("Jogador 1");
			}else
			System.out.println("Jogador " + (vezRodada + 2));
			System.out.println("1.Aceita");
			System.out.println("2.Recusa");
			Scanner entrada = new Scanner(System.in);
			int s = entrada.nextInt();

			flag = false;
			try {
				switch (s) {

				case 1:
					this.aumentaRodada();
					this.menuImprimirCartas();
					pedirUltimo=vezRodada%2;
					break;
				case 2:
					System.out.println("Time " + ((this.vezRodada % 2) + 1) + " ganhou!");
					this.pontuacao(this.vezRodada % 2);
					this.rodadaTerminada = true;
					break;
				default:
					throw new OpcaoInvalidaException();

				}

			} catch (OpcaoInvalidaException e) {
				System.out.println(e.getMessage());
				flag = true;

			}

		} while (flag);

	}

	private void menuImprimirCartas() {

		boolean flag;
		do {
			System.out.println("Jogador " + (vezRodada + 1));
			for (int i = 0; i < this.jogadores[vezRodada].getMaoJogador().getQtdCartasMao(); i++) {
				System.out
						.println(i + 1 + "." + this.jogadores[vezRodada].getMaoJogador().getCartasIndice(i).toString());

			}
			Scanner entrada = new Scanner(System.in);
			int s = entrada.nextInt() - 1;
			flag = false;
			try {
				if (s < 0 || s > this.jogadores[vezRodada].getMaoJogador().getQtdCartasMao() - 1) {
					throw new OpcaoInvalidaException();

				}

				Carta c1 = this.jogadores[vezRodada].getMaoJogador().getCartasIndice(s);
				monte.adicionarMonte(new Carta(c1), vezRodada);
				this.jogadores[vezRodada].getMaoJogador().descartar(s);

			} catch (OpcaoInvalidaException e) {
				System.out.println(e.getMessage());
				flag = true;
			}

		} while (flag);

	}

	public int verificaPontuacao() {
		if (Pontuacao1 > Pontuacao2) {
			return 1;
		} else
			return 2;
	}

	private String estadoRodada() {

		String y;

		switch (this.valorRodada) {
		case 4:
			y = "seis";
			break;
		case 8:
			y = "nove";
			break;
		case 10:
			y = "doze";
			break;
		default:
			y = "truco";

		}
		return y;
	}

	public int getPontuacao1() {
		return Pontuacao1;
	}

	public int getPontuacao2() {
		return Pontuacao2;
	}

	public void incrementaVez() {
		vezRodada++;
		if (vezRodada == 4) {
			vezRodada = 0;
		}
	}

}
