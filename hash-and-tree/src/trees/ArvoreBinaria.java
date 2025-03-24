package trees;

import java.util.Comparator;

public class ArvoreBinaria<T> extends Arvore<T> {
    private Node<T> raiz;

    private class Node<E> {
        E elemento;
        Node<E> direita;
        Node<E> esquerda;
        Node(E elemento) {
            this.elemento = elemento;
            this.esquerda = null;
            this.direita = null;
        }
    }

    public ArvoreBinaria(Comparator<T> c) {
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
            return new Node<T>(elemento);
        }
        if (c.compare(elemento, node.elemento) < 0) {
            node.esquerda = inserir(node.esquerda, elemento);
        } else if (c.compare(elemento, node.elemento) > 0) {
            node.direita = inserir(node.direita, elemento);
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
        if (c.compare(elemento, node.elemento) == 0) {
            return true;
        } else if (c.compare(elemento, node.elemento) < 0) {
            return buscar(node.esquerda, elemento);
        } else {
            return buscar(node.direita, elemento);
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
            if (node.esquerda == null) {
                return node.direita;
            } else if (node.direita == null) {
                return node.esquerda;
            }
            node.elemento = getMenor(node.direita);
            node.direita = remover(node.direita, node.elemento);
        }
        return node;
    }

    private T getMenor(Node<T> node) {
        while (node.esquerda != null) {
            node = node.esquerda;
        }
        return node.elemento;
    }
}
