package entities;

import java.util.Comparator;

public class ComparaTamanho implements Comparator<Arquivo> {
    @Override
    public int compare(Arquivo a1, Arquivo a2) {
        Integer tamanho1 = a1.tamanho;
        Integer tamanho2 = a2.tamanho;
        return tamanho1.compareTo(tamanho2);
    }
}
