package entities;

import java.util.Comparator;

public class ComparaPrice implements Comparator<Produto> {
    @Override
    public int compare(Produto p1, Produto p2) {
        Float price1 = p1.price;
        Float price2 = p2.price;
        if (price1 < price2) {
            return -1;
        } else if (price1 > price2) {
            return 1;
        } else {
            return 0;
        }
    }
}
