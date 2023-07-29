package entities;

import java.util.Comparator;

public class ComparaCondition implements Comparator<Produto> {
    @Override
    public int compare(Produto p1, Produto p2) {
        String codition1 = p1.condition;
        String codition2 = p2.condition;
        return codition1.compareTo(codition2);
    }
}
