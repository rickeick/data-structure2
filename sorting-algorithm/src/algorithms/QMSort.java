package algorithms;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class QMSort<T> extends SortStrategy<T> {
    private final int x = 1024;

    public QMSort(Comparator<T> comparador) {
        c = comparador;
    }

    @Override
    public void ordemCrescente(T[] vetor) {
        quickCrescente(vetor, 0, vetor.length-1);
    }

    private void quickCrescente(T[] vetor, int inicio, int fim) {
        if (inicio > x) {
            int pos = particionaCrescente(vetor, inicio, fim);
            quickCrescente(vetor, inicio, pos-1);
            quickCrescente(vetor, pos+1, fim);
        } else {
            T[] temp = (T[]) new Object[vetor.length];
            mergeCrescente(vetor, temp, 0, vetor.length-1);
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

    private void mergeCrescente(T[] vetor, T[] temp, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeCrescente(vetor, temp, esq, meio);
            mergeCrescente(vetor, temp, meio+1, dir);
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
        quickDecrescente(vetor, 0, vetor.length-1);
    }

    private void quickDecrescente(T[] vetor, int inicio, int fim) {
        if (inicio > x) {
            int pos = particionaDecrescente(vetor, inicio, fim);
            quickDecrescente(vetor, inicio, pos-1);
            quickDecrescente(vetor, pos+1, fim);
        } else {
            T[] temp = (T[]) new Object[vetor.length];
            mergeDecrescente(vetor, temp, 0, vetor.length-1);
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

    private void mergeDecrescente(T[] vetor, T[] temp, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeDecrescente(vetor, temp, esq, meio);
            mergeDecrescente(vetor, temp, meio+1, dir);
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
