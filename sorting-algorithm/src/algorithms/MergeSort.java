package algorithms;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class MergeSort<T> extends SortStrategy<T> {
    
    public MergeSort(Comparator<T> comparador) {
        c = comparador;
    }

    @Override
    public void ordemCrescente(T[] vetor) {
        T[] temp = (T[]) new Object[vetor.length];
        ordenaCrescente(vetor, temp, 0, vetor.length-1);
    }

    private void ordenaCrescente(T[] vetor, T[] temp, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            ordenaCrescente(vetor, temp, esq, meio);
            ordenaCrescente(vetor, temp, meio+1, dir);
            intercalaCrescente(vetor, temp, esq, meio+1, dir);
        }
    }

    private void intercalaCrescente(T[] vetor, T[] temp, int esqPos, int dirPos, int dirFim) {
        int tempPos = esqPos;
        int esqFim = dirPos - 1;
        int tamanho = dirFim - esqPos + 1;
        while (esqPos <= esqFim && dirPos <= dirFim) {
            nComparacoes++;
            if (c.compare(vetor[esqPos], vetor[dirPos]) <= 0) {
                temp[tempPos++] = vetor[esqPos++];
            } else {
                temp[tempPos++] = vetor[dirPos++];
            }
        }
        while (esqPos <= esqFim) {
            temp[tempPos++] = vetor[esqPos++];
        }
        while (dirPos <= dirFim) {
            temp[tempPos++] = vetor[dirPos++];
        }
        for (int i=0; i<tamanho; i++, dirFim--) {
            vetor[dirFim] = temp[dirFim];
        }
    }

    @Override
    public void ordemDecrescente(T[] vetor) {
        T[] temp = (T[]) new Object[vetor.length];
        ordenaDecrescente(vetor, temp, 0, vetor.length-1);
    }

    private void ordenaDecrescente(T[] vetor, T[] temp, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            ordenaDecrescente(vetor, temp, esq, meio);
            ordenaDecrescente(vetor, temp, meio+1, dir);
            intercalaDecrescente(vetor, temp, esq, meio+1, dir);
        }
    }

    private void intercalaDecrescente(T[] vetor, T[] temp, int esqPos, int dirPos, int dirFim) {
        int tempPos = esqPos;
        int esqFim = dirPos - 1;
        int tamanho = dirFim - esqPos + 1;
        while (esqPos <= esqFim && dirPos <= dirFim) {
            nComparacoes++;
            if (c.compare(vetor[esqPos], vetor[dirPos]) >= 0) {
                temp[tempPos++] = vetor[esqPos++];
            } else {
                temp[tempPos++] = vetor[dirPos++];
            }
        }
        while (esqPos <= esqFim) {
            temp[tempPos++] = vetor[esqPos++];
        }
        while (dirPos <= dirFim) {
            temp[tempPos++] = vetor[dirPos++];
        }
        for (int i=0; i<tamanho; i++, dirFim--) {
            vetor[dirFim] = temp[dirFim];
        }
    }
}
