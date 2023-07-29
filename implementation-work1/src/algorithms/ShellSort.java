package algorithms;

import java.util.Comparator;

public class ShellSort<T> extends SortStrategy<T> {
    public ShellSort(Comparator<T> comparador) {
        c = comparador;
    }

    @Override
    public void ordemCrescente(T[] vetor) {
        int h, j; T temp;
        for (h = 1; h<vetor.length; h = (h*3)+1);
        while (h > 0) {
            h = (h-1)/3;
            for (int i=h; i<vetor.length; i++) {
                j = i;
                temp = vetor[i];
                while (c.compare(vetor[j-h], temp) > 0) {
                    nComparacoes++;
                    vetor[j] = vetor[j-h];
                    j = j - h;
                    if (j < h) {
                        break;
                    }
                }
                vetor[j] = temp;
            }
        }
    }

    @Override
    public void ordemDecrescente(T[] vetor) {
        int h, j; T temp;
        for (h = 1; h<vetor.length; h = (h*3)+1);
        while (h > 0) {
            h = (h-1)/3;
            for (int i=h; i<vetor.length; i++) {
                j = i;
                temp = vetor[i];
                while (c.compare(vetor[j-h], temp) < 0) {
                    nComparacoes++;
                    vetor[j] = vetor[j-h];
                    j = j - h;
                    if (j < h) {
                        break;
                    }
                }
                vetor[j] = temp;
            }
        }
    }
}
