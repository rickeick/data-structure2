package hashtables;

import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class HashQuadratico<K,V> extends TabelaHash<K,V> {
    private Entrada<K,V>[] tabela;

    public HashQuadratico(int capacidade) {
        this.chaves = new LinkedList<>();
        this.tabela = new Entrada[capacidade];
        this.capacidade = capacidade;
        this.tamanho = 0;
    }

    private static class Entrada<K,V> {
        public K chave;
        public V valor;
        public boolean deletado;
        Entrada(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
            this.deletado = false;
        }
    }
    
    @Override
    public void inserir(K chave, V valor) {
        int indice = hash(chave);
        if ((float)tamanho/(float)capacidade >= 0.8) {
            redimensionar();
        }
        for (int i=1; tabela[indice] != null; i++) {
            if (tabela[indice].deletado) {
                break;
            }
            if (i >= capacidade-1) {
                redimensionar(); i=1;
                indice = hash(chave);
            } else {
                indice = (indice * i) % capacidade;
            }
        }
        tabela[indice] = new Entrada<>(chave, valor);
        chaves.add(chave);
        tamanho++;
    }

    @Override
    public V buscar(K chave) {
        int inicio = hash(chave);
        int indice = hash(chave);
        for (int i=1; tabela[indice] != null; i++) {
            if (tabela[indice].chave.equals(chave) && tabela[indice].deletado != true) {
                return tabela[indice].valor;
            }
            indice = (indice * i) % capacidade;
            if (inicio == indice) {
                break;
            }
        }
        return null;
    }

    @Override
    public V remover(K chave) {
        int inicio = hash(chave);
        int indice = hash(chave);
        for (int i=1; tabela[indice] != null; i++) {
            if (tabela[indice].chave.equals(chave) && tabela[indice].deletado != true) {
                tamanho--;
                chaves.remove(chave);
                tabela[indice].deletado = true;
                return tabela[indice].valor;
            }
            indice = (indice * i) % capacidade;
            if (inicio == indice) {
                break;
            }
        }
        return null;
    }

    @Override
    protected void redimensionar() {
        tamanho = 0;
        capacidade *= 2;
        Entrada<K,V>[] antiga = tabela;
        tabela = new Entrada[capacidade];
        chaves = new LinkedList<>();
        for (Entrada<K,V> entrada : antiga) {
            if (entrada != null) {
                if (entrada.deletado != true) {
                    inserir(entrada.chave, entrada.valor);
                }
            }
        }
    }
}
