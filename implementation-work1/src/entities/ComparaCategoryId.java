package entities;

import java.util.Comparator;

public class ComparaCategoryId implements Comparator<Produto> {
    @Override
    public int compare(Produto p1, Produto p2) {
        String categoryId1 = p1.category_id;
        String categoryId2 = p2.category_id;
        return categoryId1.compareTo(categoryId2);
    }
}
