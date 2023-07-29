package algorithms;

import java.util.Comparator;

public class InsertSort<T> extends SortStrategy<T> {

    public InsertSort(Comparator<T> comparador) {
        c = comparador;
    }

    @Override
    public void ordemCrescente(T[] vetor) {
        for (int i = 1; i<vetor.length; i++) {
            int j = i - 1;
            T key = vetor[i];
            while (j >= 0 && (c.compare(vetor[j], key) > 0)) {
                vetor[j+1] = vetor[j];
                nComparacoes++;
                j--;
            }
            vetor[j+1] = key;
        }
    }

    @Override
    public void ordemDecrescente(T[] vetor) {
        for (int i = 1; i<vetor.length; i++) {
            int j = i - 1;
            T key = vetor[i];
            while (j >= 0 && (c.compare(vetor[j], key) < 0)) {
                vetor[j+1] = vetor[j];
                nComparacoes++;
                j--;
            }
            vetor[j+1] = key;
        }
    }
}
