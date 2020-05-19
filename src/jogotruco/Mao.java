package jogoTruco;

import java.util.ArrayList;

public class Mao {

    private final ArrayList<Carta> cartasMao;

    private int qtdCartasMao;

    Mao() {
        cartasMao = new ArrayList<>();

    }

    public Carta getCartasIndice(int v) {
        return this.cartasMao.get(v);

    }

    public void descartar(int c) {
        cartasMao.remove(c);
        qtdCartasMao--;

    }

    public void recebeCarta(Carta[] a) {
        qtdCartasMao = 3;
        cartasMao.clear();
        cartasMao.add(a[0]);
        cartasMao.add(a[1]);
        cartasMao.add(a[2]);

    }

    public int getQtdCartasMao() {
        return qtdCartasMao;
    }

}
