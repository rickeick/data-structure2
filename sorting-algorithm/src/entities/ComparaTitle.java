package entities;

import java.util.Comparator;

public class ComparaTitle implements Comparator<Produto> {
    @Override
    public int compare(Produto p1, Produto p2) {
        String title1 = p1.title;
        String title2 = p2.title;
        return title1.compareTo(title2);
    }
}
