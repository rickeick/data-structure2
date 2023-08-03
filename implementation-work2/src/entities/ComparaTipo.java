package entities;

import java.util.Comparator;

public class ComparaTipo implements Comparator<Arquivo> {
    @Override
    public int compare(Arquivo a1, Arquivo a2) {
        String tipo1 = a1.tipo;
        String tipo2 = a2.tipo;
        return tipo1.compareTo(tipo2);
    }
}
