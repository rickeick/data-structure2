package entities;

import java.util.Comparator;

public class ComparaDomainID implements Comparator<Produto> {
    @Override
    public int compare(Produto p1, Produto p2) {
        String domainId1 = p1.domain_id;
        String domainId2 = p2.domain_id;
        return domainId1.compareTo(domainId2);
    }
}
