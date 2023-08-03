package trees;

import java.util.Comparator;

public class ArvoreAVL<T> extends Arvore<T> {
    private Node<T> raiz;

    private class Node<E> {
        private E elemento;
        private Node<E> direita;
        private Node<E> esquerda;
        private int altura;
        public Node(E elemento) {
            this.elemento = elemento;
            this.altura = 1;
        }
    }
    
    public ArvoreAVL(Comparator<T> c) {
        this.raiz = null;
        this.c = c;
    }

    @Override
    public void inOrdem() {
        inOrdem(raiz);
    }

    public void inOrdem(Node<T> node) {
        if (node != null) {
            inOrdem(node.esquerda);
            System.out.println(node.elemento);
            inOrdem(node.direita);
        }
    }

    @Override
    public void inserir(T elemento) {
        if (elemento != null) {
            raiz = inserir(raiz, elemento);
        }
    }
    
    private Node<T> inserir(Node<T> node, T elemento) {
        if (node == null) {
            return new Node<>(elemento);
        }
        if (c.compare(elemento, node.elemento) < 0) {
            node.esquerda = inserir(node.esquerda, elemento);
        } else if (c.compare(elemento, node.elemento) > 0) {
            node.direita = inserir(node.direita, elemento);
        } else {
            return node;
        }
        int fator = getFator(node);
        node.altura = 1 + Math.max(getAltura(node.esquerda), getAltura(node.direita));
        if (fator > 1 && c.compare(elemento, node.esquerda.elemento) < 0) {
            return rotacaoDireita(node);
        }
        if (fator < -1 && c.compare(elemento, node.direita.elemento) > 0) {
            return rotacaoEsquerda(node);
        }
        if (fator > 1 && c.compare(elemento, node.esquerda.elemento) > 0) {
            node.esquerda = rotacaoEsquerda(node.esquerda);
            return rotacaoDireita(node);
        }
        if (fator < -1 && c.compare(elemento, node.direita.elemento) < 0) {
            node.direita = rotacaoDireita(node.direita);
            return rotacaoEsquerda(node);
        }
        return node;
    }
    
    @Override
    public boolean buscar(T elemento) {
        if (elemento == null) {
            return false;
        }
        return buscar(raiz, elemento);
    }
    
    private boolean buscar(Node<T> node, T elemento) {
        if (node == null) {
            return false;
        }
        if (c.compare(elemento, node.elemento) < 0) {
            return buscar(node.esquerda, elemento);
        } else if (c.compare(elemento, node.elemento) > 0) {
            return buscar(node.direita, elemento);
        } else {
            return true;
        }
    }
    
    @Override
    public void remover(T elemento) {
        if (elemento != null) {
            raiz = remover(raiz, elemento);
        }
    }
    
    private Node<T> remover(Node<T> node, T elemento) {
        if (node == null) {
            return null;
        }
        if (c.compare(elemento, node.elemento) < 0) {
            node.esquerda = remover(node.esquerda, elemento);
        } else if (c.compare(elemento, node.elemento) > 0) {
            node.direita = remover(node.direita, elemento);
        } else {
            if (node.esquerda == null || node.direita == null) {
                Node<T> temp = null;
                
                if (node.esquerda == null) {
                    temp = node.direita;
                } else {
                    temp = node.esquerda;
                }
                
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node<T> temp = getMenor(node.direita);
                node.elemento = temp.elemento;
                node.direita = remover(node.direita, temp.elemento);
            }
        }
        if (node == null) {
            return null;
        }
        int fator = getFator(node);
        node.altura = 1 + Math.max(getAltura(node.esquerda), getAltura(node.direita));
        if (fator > 1 && getFator(node.esquerda) >= 0) {
            return rotacaoDireita(node);
        }
        if (fator > 1 && getFator(node.esquerda) < 0) {
            node.esquerda = rotacaoEsquerda(node.esquerda);
            return rotacaoDireita(node);
        }
        if (fator < -1 && getFator(node.direita) <= 0) {
            return rotacaoEsquerda(node);
        }
        if (fator < -1 && getFator(node.direita) > 0) {
            node.direita = rotacaoDireita(node.direita);
            return rotacaoEsquerda(node);
        }
        return node;
    }
    
    private Node<T> getMenor(Node<T> node) {
        Node<T> atual = node;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }
    
    private int getAltura(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return node.altura;
    }
    
    private int getFator(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return getAltura(node.esquerda) - getAltura(node.direita);
    }
    
    private Node<T> rotacaoDireita(Node<T> y) {
        Node<T> x = y.esquerda;
        Node<T> T2 = x.direita;
        x.direita = y;
        y.esquerda = T2;
        y.altura = 1 + Math.max(getAltura(y.esquerda), getAltura(y.direita));
        x.altura = 1 + Math.max(getAltura(x.esquerda), getAltura(x.direita));
        return x;
    }
    
    private Node<T> rotacaoEsquerda(Node<T> x) {
        Node<T> y = x.direita;
        Node<T> T2 = y.esquerda;
        y.esquerda = x;
        x.direita = T2;
        x.altura = 1 + Math.max(getAltura(x.esquerda), getAltura(x.direita));
        y.altura = 1 + Math.max(getAltura(y.esquerda), getAltura(y.direita));
        return y;
    }
}
