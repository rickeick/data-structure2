package hashtables;

import java.util.LinkedList;

public abstract class TabelaHash<K,V> {
    private final double a = 0.6180339887;
    protected LinkedList<K> chaves;
    protected int capacidade;
    protected int tamanho;

    protected int hash(K chave) {
        double valor = chave.hashCode() * a;
        double fracao = valor - (int)valor;
        return Math.abs((int)(capacidade*fracao));
    }

    public LinkedList<K> getChaves() {
        return chaves;
    }

    public abstract void inserir(K chave, V valor);

    public abstract V buscar(K chave);

    public abstract V remover(K chave);

    protected abstract void redimensionar();
}
