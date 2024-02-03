package hashtables;

import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class HashExterno<K,V> extends TabelaHash<K,V> {
    private LinkedList<Entrada<K,V>>[] tabela;
    
    public HashExterno(int capacidade) {
        this.tabela = new LinkedList[capacidade];
        this.chaves = new LinkedList<>();
        this.capacidade = capacidade;
        this.tamanho = 0;
    }

    private static class Entrada<K,V> {
        public K chave;
        public V valor;
        Entrada(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }

    @Override
    public void inserir(K chave, V valor) {
        int indice = hash(chave);
        if ((float)tamanho/(float)capacidade >= 0.8) {
            redimensionar();
        }
        if (tabela[indice] == null) {
            tabela[indice] = new LinkedList<>();
            tamanho++;
        }
        LinkedList<Entrada<K,V>> lista = tabela[indice];
        for (Entrada<K, V> entrada : lista) {
            if (entrada.chave.equals(chave)) {
                entrada.valor = valor;
                return;
            }
        }
        lista.add(new Entrada<>(chave, valor));
        chaves.add(chave);
    }

    @Override
    public V buscar(K chave) {
        int indice = hash(chave);
        LinkedList<Entrada<K,V>> lista = tabela[indice];
        if (lista != null) {
            for (Entrada<K, V> entrada : lista) {
                if (entrada.chave.equals(chave)) {
                    return entrada.valor;
                }
            }
        }
        return null;
    }

    @Override
    public V remover(K chave) {
        int indice = hash(chave);
        LinkedList<Entrada<K,V>> lista = tabela[indice];
        if (lista != null) {
            for (Entrada<K, V> entrada : lista) {
                if (entrada.chave.equals(chave)) {
                    chaves.remove(chave);
                    lista.remove(entrada);
                    if (lista.isEmpty()) {
                        tamanho--;
                    }
                    return entrada.valor;
                }
            }
        }
        return null;
    }

    @Override
    protected void redimensionar() {
        tamanho = 0;
        capacidade *= 2;
        LinkedList<Entrada<K,V>>[] antiga = tabela.clone();
        tabela = new LinkedList[capacidade];
        chaves = new LinkedList<>();
        for (LinkedList<Entrada<K,V>> lista : antiga) {
            if (lista != null) {
                for (Entrada<K,V> entrada : lista) {
                    if (entrada != null) {
                        inserir(entrada.chave, entrada.valor);
                    }
                }
            }
        }
    }
}
