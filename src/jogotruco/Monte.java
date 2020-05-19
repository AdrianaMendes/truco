package jogoTruco;

import java.util.ArrayList;

public class Monte {

    private final ArrayList<Carta> boloCartas;

    private final ArrayList<Integer> idJogadores;

    private Monte() {
        this.boloCartas = new ArrayList<>();
        this.idJogadores = new ArrayList<>();
    }

    private static Monte instancia = null;

    public static Monte getInstancia() {
        if (instancia == null) {
            instancia = new Monte();

        }
        return instancia;
    }

    public void adicionarMonte(Carta x, int y) {
        int i;
        for (i = 0; i < boloCartas.size(); i++) {
            if (!x.verificaMaiorCarta(boloCartas.get(i))) {
                break;
            }
        }
        this.boloCartas.add(i, x);
        this.idJogadores.add(i, y);

    }

    public void teste() {
        for (int i = 0; i < boloCartas.size(); i++) {
            System.out.print(boloCartas.get(i) + " [Jogador" + (idJogadores.get(i) + 1) + "],\t");
        }
        System.out.println();
    }

    public int maiorCarta() {
        if (boloCartas.isEmpty()) {
            return -1;
        }
        return idJogadores.get(idJogadores.size() - 1);
    }

    public boolean checkAmarrado() {

        for (int i = boloCartas.size() - 2; i >= 0; i--) {
            if (boloCartas.get(i).verificaMesmoValor(boloCartas.get(boloCartas.size() - 1))) {

                if (idJogadores.get(i) % 2 != idJogadores.get(idJogadores.size() - 1) % 2) {
                    return true;

                }

            } else {
                return false;
            }
        }
        return false;

    }

    public void limpaMonte() {
        boloCartas.clear();
        idJogadores.clear();
    }

    public int retonaSize() {
        return boloCartas.size();

    }

}
