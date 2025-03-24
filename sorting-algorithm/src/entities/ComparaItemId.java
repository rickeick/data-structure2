package entities;

import java.util.Comparator;

public class ComparaItemId implements Comparator<Produto> {
    @Override
    public int compare(Produto p1, Produto p2) {
        Integer itemId1 = p1.item_id;
        Integer itemId2 = p2.item_id;
        if (itemId1 < itemId2) {
            return -1;
        } else if (itemId1 > itemId2) {
            return 1;
        } else {
            return 0;
        }
    }
}
