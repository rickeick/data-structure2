package entities;

import java.time.LocalDate;
import java.util.Comparator;

public class ComparaCriacao implements Comparator<Arquivo> {
    @Override
    public int compare(Arquivo a1, Arquivo a2) {
        LocalDate criacao1 = a1.criacao;
        LocalDate criacao2 = a2.criacao;
        return criacao1.compareTo(criacao2);
    }
}
