package jogoTruco;

import java.util.ArrayList;

public class Baralho {
    private final char c[] = {'A', '2', '3', '4', '5', '6', '7', 'J', 'Q', 'K'};
    private final ArrayList<Carta> utilizadas;

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

    public Carta retornaCarta() {
        Carta X = new Carta();
        boolean flag;
        do {
            flag = false;
            int k = (int) (Math.random() * 10);
            int n = (int) (Math.random() * 4);
            X.setNumero(c[k]);
            X.setNaipe(n);
            for (int i = 0; i < utilizadas.size(); i++) {
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

    public static int retornaValor(char numero, int naipe) {
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
        if (numero == '2') {
            valor = 8;
        } else if (numero == '3') {
            valor = 9;
        } else if (numero == '4' && naipe == 0) {
            valor = 13;
        } else if (numero == '7' && naipe == 1) {
            valor = 12;
        } else if (numero == 'A' && naipe == 2) {
            valor = 11;
        } else if (numero == '7' && naipe == 3) {
            valor = 10;
        }
        return valor;
    }
}
