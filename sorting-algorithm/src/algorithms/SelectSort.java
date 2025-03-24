package algorithms;

import java.util.Comparator;

public class SelectSort<T> extends SortStrategy<T> {

    public SelectSort(Comparator<T> comparador) {
        c = comparador;
    }

    @Override
    public void ordemCrescente(T[] vetor) {
        for (int i = 0; i<vetor.length; i++) {
            int min = i;
            for (int j = i; j<vetor.length; j++) {
                nComparacoes++;
                if (c.compare(vetor[min], vetor[j]) > 0) {
                    min = j;
                }
            }
            T temp = vetor[i];
            vetor[i] = vetor[min];
            vetor[min] = temp;
        }
    }

    @Override
    public void ordemDecrescente(T[] vetor) {
        for (int i = 0; i<vetor.length; i++) {
            int max = i;
            for (int j = i; j<vetor.length; j++) {
                nComparacoes++;
                if (c.compare(vetor[max], vetor[j]) < 0) {
                    max = j;
                }
            }
            T temp = vetor[i];
            vetor[i] = vetor[max];
            vetor[max] = temp;
        }
    }
}
