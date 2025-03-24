package algorithms;

import java.util.Comparator;

public abstract class SortStrategy<T> {
    
    protected Comparator<T> c;
    protected long nComparacoes = 0;

    public abstract void ordemCrescente(T[] vetor);

    public abstract void ordemDecrescente(T[] vetor);

    public long getNComparacoes() {
        return nComparacoes;
    }
}
