package entities;

import java.util.Comparator;

public class ComparaNome implements Comparator<Arquivo> {
    @Override
    public int compare(Arquivo a1, Arquivo a2) {
        String nome1 = a1.nome;
        String nome2 = a2.nome;
        return nome1.compareTo(nome2);
    }
}
