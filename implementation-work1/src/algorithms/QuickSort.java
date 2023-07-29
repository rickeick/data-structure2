package algorithms;

import java.util.Comparator;

public class QuickSort<T> extends SortStrategy<T> {
    
    public QuickSort(Comparator<T> comparador) {
        c = comparador;
    }

    @Override
    public void ordemCrescente(T[] vetor) {
        ordenaCrescente(vetor, 0, vetor.length-1);
    }

    private void ordenaCrescente(T[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int pos = particionaCrescente(vetor, inicio, fim);
            ordenaCrescente(vetor, inicio, pos-1);
            ordenaCrescente(vetor, pos+1, fim);
        }
    }

    private int particionaCrescente(T[] vetor, int inicio, int fim) {
        T pivo = vetor[inicio];
        int esq = inicio+1, dir = fim;
        while (esq <= dir) {
            nComparacoes++;
            if (c.compare(vetor[esq], pivo) <= 0) {
                esq++;
            } else if (c.compare(vetor[dir], pivo) > 0) {
                nComparacoes++;
                dir--;
            } else {
                T troca = vetor[esq];
                vetor[esq] = vetor[dir];
                vetor[dir] = troca;
                esq++;
                dir--;
            }
        }
        vetor[inicio] = vetor[dir];
        vetor[dir] = pivo;
        return dir;
    }

    @Override
    public void ordemDecrescente(T[] vetor) {
        ordenaDecrescente(vetor, 0, vetor.length-1);
    }

    private void ordenaDecrescente(T[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int pos = particionaDecrescente(vetor, inicio, fim);
            ordenaDecrescente(vetor, inicio, pos-1);
            ordenaDecrescente(vetor, pos+1, fim);
        }
    }

    private int particionaDecrescente(T[] vetor, int inicio, int fim) {
        T pivo = vetor[inicio];
        int esq = inicio+1, dir = fim;
        while (esq <= dir) {
            nComparacoes++;
            if (c.compare(vetor[esq], pivo) >= 0) {
                esq++;
            } else if (c.compare(vetor[dir], pivo) < 0) {
                nComparacoes++;
                dir--;
            } else {
                T troca = vetor[esq];
                vetor[esq] = vetor[dir];
                vetor[dir] = troca;
                esq++;
                dir--;
            }
        }
        vetor[inicio] = vetor[dir];
        vetor[dir] = pivo;
        return dir;
    }
}
