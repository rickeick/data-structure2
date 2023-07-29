package algorithms;

import java.util.Comparator;

public class SISort<T> extends SortStrategy<T> {
    private final int x = 25;

    public SISort(Comparator<T> comparador) {
        c = comparador;
    }

    @Override
    public void ordemCrescente(T[] vetor) {
        int esq = vetor.length*(x/100)-1;
        int dir = vetor.length*((100-2*x)/100)-1;
        for (int i = 0; i<vetor.length; i=inc(i,esq,dir)) {
            int min = i;
            for (int j = i; j<vetor.length; j=inc(j,esq,dir)) {
                nComparacoes++;
                if (c.compare(vetor[min], vetor[j]) > 0) {
                    min = j;
                }
            }
            T temp = vetor[i];
            vetor[i] = vetor[min];
            vetor[min] = temp;
        }
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
        int esq = vetor.length*(x/100)-1;
        int dir = vetor.length*((100-2*x)/100)-1;
        for (int i = 0; i<vetor.length; i=inc(i,esq,dir)) {
            int max = i;
            for (int j = i; j<vetor.length; j=inc(j,esq,dir)) {
                nComparacoes++;
                if (c.compare(vetor[max], vetor[j]) < 0) {
                    max = j;
                }
            }
            T temp = vetor[i];
            vetor[i] = vetor[max];
            vetor[max] = temp;
        }
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

    private int inc(int var, int esq, int dir) {
        if (var != esq) {
            return var+1;
        } else {
            return dir;
        }
    }
}
