package entities;

import java.time.LocalDate;
import java.util.Comparator;

public class ComparaModificacao implements Comparator<Arquivo> {
    @Override
    public int compare(Arquivo a1, Arquivo a2) {
        LocalDate modificacao1 = a1.modificacao;
        LocalDate modificacao2 = a2.modificacao;
        return modificacao1.compareTo(modificacao2);
    }
}
