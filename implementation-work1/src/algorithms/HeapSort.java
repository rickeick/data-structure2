package algorithms;

import java.util.Comparator;

public class HeapSort<T> extends SortStrategy<T> {
    
    public HeapSort(Comparator<T> comparador) {
        c = comparador;
    }

    @Override
    public void ordemCrescente(T[] vetor) {
        int n = vetor.length;
        for (int i=n/2-1; i>=0; i--) {
            MaxHeapify(vetor, i, n);
        }
        for (int i=n-1; i>=0; i--) {
            T troca = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = troca;
            MaxHeapify(vetor, 0, i);
        }
    }

    private void MaxHeapify(T[] vetor, int i, int n) {
        int maior = i;
        int l = 2*i+1;
        int r = 2*i+2;
        if (l < n && (c.compare(vetor[l], vetor[maior]) > 0)) {
            maior = l;
        }
        if (r < n && (c.compare(vetor[r], vetor[maior]) > 0)) {
            maior = r;
        }
        nComparacoes += 2;
        if (maior != i) {
            T troca = vetor[i];
            vetor[i] = vetor[maior];
            vetor[maior] = troca;
            MaxHeapify(vetor, maior, n);
        }
    }

    @Override
    public void ordemDecrescente(T[] vetor) {
        int n = vetor.length;
        for (int i=n/2-1; i>=0; i--) {
            MinHeapify(vetor, i, n);
        }
        for (int i=n-1; i>=0; i--) {
            T troca = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = troca;
            MinHeapify(vetor, 0, i);
        }
    }

    private void MinHeapify(T[] vetor, int i, int n) {
        int menor = i;
        int l = 2*i+1;
        int r = 2*i+2;
        if (l < n && (c.compare(vetor[l], vetor[menor]) < 0)) {
            menor = l;
        }
        if (r < n && (c.compare(vetor[r], vetor[menor]) < 0)) {
            menor = r;
        }
        nComparacoes += 2;
        if (menor != i) {
            T troca = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = troca;
            MinHeapify(vetor, menor, n);
        }
    }
}
