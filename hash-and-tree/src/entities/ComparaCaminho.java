package entities;

import java.util.Comparator;

public class ComparaCaminho implements Comparator<Arquivo> {
    @Override
    public int compare(Arquivo a1, Arquivo a2) {
        String caminho1 = a1.caminho;
        String caminho2 = a2.caminho;
        return caminho1.compareTo(caminho2);
    }
}
