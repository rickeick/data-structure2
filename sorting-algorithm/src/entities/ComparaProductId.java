package entities;

import java.util.Comparator;

public class ComparaProductId implements Comparator<Produto> {
    @Override
    public int compare(Produto p1, Produto p2) {
        Integer productId1 = p1.product_id;
		Integer productId2 = p2.product_id;
		if (productId1 < productId2) {
            return -1;
        } else if (productId1 > productId2) {
            return 1;
        } else {
            return 0;
        }
    }
}
