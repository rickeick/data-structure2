package trees;

import java.util.Comparator;

public class ArvoreRubroNegra<T> extends Arvore<T>{
    private Node<T> raiz;
    private static final boolean PRETO = false;
    private static final boolean VERMELHO = true;

    private class Node<E> {
        E elemento;
        boolean cor;
        Node<E> pai;
        Node<E> direita;
        Node<E> esquerda;        
        Node(E elemento) {
            this.elemento = elemento;
            this.cor = VERMELHO;
        }
    }

    public ArvoreRubroNegra(Comparator<T> c) {
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
        if (elemento == null) {
            return;
        }
        Node<T> pai = null;
        Node<T> atual = raiz;
        Node<T> node = new Node<>(elemento);
        while (atual != null) {
            pai = atual;
            if (c.compare(elemento, atual.elemento) < 0) {
                atual = atual.esquerda;
            } else if (c.compare(elemento, atual.elemento) > 0) {
                atual = atual.direita;
            } else {
                return;
            }
        }
        node.pai = pai;
        if (pai == null) {
            raiz = node;
        } else if (c.compare(elemento, pai.elemento) < 0) {
            pai.esquerda = node;
        } else {
            pai.direita = node;
        }
        consertarInsercao(node);
    }

    private void consertarInsercao(Node<T> node) {
        while (isVermelho(node.pai)) {
            if (node.pai == node.pai.pai.esquerda) {
                Node<T> tio = node.pai.pai.direita;
                if (isVermelho(tio)) {
                    tio.cor = PRETO;
                    node.pai.cor = PRETO;
                    node.pai.pai.cor = VERMELHO;
                    node = node.pai.pai;
                } else {
                    if (node == node.pai.direita) {
                        node = node.pai;
                        rotacaoEsquerda(node);
                    }
                    node.pai.cor = PRETO;
                    node.pai.pai.cor = VERMELHO;
                    rotacaoDireita(node.pai.pai);
                }
            } else {
                Node<T> tio = node.pai.pai.esquerda;
                if (isVermelho(tio)) {
                    tio.cor = PRETO;
                    node.pai.cor = PRETO;
                    node.pai.pai.cor = VERMELHO;
                    node = node.pai.pai;
                } else {
                    if (node == node.pai.esquerda) {
                        node = node.pai;
                        rotacaoDireita(node);
                    }
                    node.pai.cor = PRETO;
                    node.pai.pai.cor = VERMELHO;
                    rotacaoEsquerda(node.pai.pai);
                }
            }
        }
        raiz.cor = PRETO;
    }

    @Override
    public boolean buscar(T elemento) {
        if (elemento == null) {
            return false;
        }
        return (retornarNode(elemento) != null);
    }

    private Node<T> retornarNode(T elemento) {
        Node<T> atual = raiz;
        while (atual != null) {
            if (c.compare(elemento, atual.elemento) == 0) {
                return atual;
            } else if (c.compare(elemento, atual.elemento) < 0) {
                atual = atual.esquerda;
            } else {
                atual = atual.direita;
            }
        }
        return null;
    }

    @Override
    public void remover(T elemento) {
        if (elemento == null) {
            return;
        }
        Node<T> node = retornarNode(elemento);
        if (node == null) {
            return;
        }
        remover(node);
    }

    private void remover(Node<T> node) {
        boolean cor;
        Node<T> filho, pai;
        if (node.esquerda != null && node.direita != null) {
            Node<T> sucessor = getMaximo(node.esquerda);
            node.elemento = sucessor.elemento;
            node = sucessor;
        }
        if (node.direita == null) {
            filho = node.esquerda;
        } else {
            filho = node.direita;
        }
        pai = node.pai;
        cor = node.cor;
        if (filho != null) {
            filho.pai = pai;
        }
        if (pai == null) {
            raiz = filho;
        } else if (node == pai.esquerda) {
            pai.esquerda = filho;
        } else {
            pai.direita = filho;
        }
        if (cor == PRETO) {
            consertarRemocao(filho);
        }
    }

    private void consertarRemocao(Node<T> node) {
        Node<T> irmao;
        Node<T> pai = node.pai;
        while (node != raiz && !isVermelho(node)) {
            if (node == pai.esquerda) {
                irmao = pai.direita;
                if (isVermelho(irmao)) {
                    irmao.cor = PRETO;
                    pai.cor = VERMELHO;
                    rotacaoEsquerda(pai);
                    irmao = pai.direita;
                }
                if (!isVermelho(irmao.esquerda) && !isVermelho(irmao.direita)) {
                    irmao.cor = VERMELHO;
                    node = pai;
                    pai = node.pai;
                } else {
                    if (!isVermelho(irmao.direita)) {
                        irmao.esquerda.cor = PRETO;
                        irmao.cor = VERMELHO;
                        rotacaoDireita(irmao);
                        irmao = pai.direita;
                    }
                    irmao.cor = pai.cor;
                    pai.cor = PRETO;
                    irmao.direita.cor = PRETO;
                    rotacaoEsquerda(pai);
                    node = raiz;
                    break;
                }
            } else {
                irmao = pai.esquerda;
                if (isVermelho(irmao)) {
                    irmao.cor = PRETO;
                    pai.cor = VERMELHO;
                    rotacaoDireita(pai);
                    irmao = pai.esquerda;
                }
                if (!isVermelho(irmao.direita) && !isVermelho(irmao.esquerda)) {
                    irmao.cor = VERMELHO;
                    node = pai;
                    pai = node.pai;
                } else {
                    if (!isVermelho(irmao.esquerda)) {
                        irmao.direita.cor = PRETO;
                        irmao.cor = VERMELHO;
                        rotacaoEsquerda(irmao);
                        irmao = pai.esquerda;
                    }
                    irmao.cor = pai.cor;
                    pai.cor = PRETO;
                    irmao.esquerda.cor = PRETO;
                    rotacaoDireita(pai);
                    node = raiz;
                    break;
                }
            }
        }
        if (node != null) {
            node.cor = PRETO;
        }
    }

    private boolean isVermelho(Node<T> node) {
        if (node == null) {
            return false;
        }
        return node.cor == VERMELHO;
    }

    private Node<T> getMaximo(Node<T> node) {
        while (node.direita != null) {
            node = node.direita;
        }
        return node;
    }

    private void rotacaoEsquerda(Node<T> node) {
        Node<T> filhoDireito = node.direita;
        node.direita = filhoDireito.esquerda;
        if (filhoDireito.esquerda != null) {
            filhoDireito.esquerda.pai = node;
        }
        filhoDireito.pai = node.pai;
        if (node.pai == null) {
            raiz = filhoDireito;
        } else if (node == node.pai.esquerda) {
            node.pai.esquerda = filhoDireito;
        } else {
            node.pai.direita = filhoDireito;
        }
        filhoDireito.esquerda = node;
        node.pai = filhoDireito;
    }

    private void rotacaoDireita(Node<T> node) {
        Node<T> filhoEsquerdo = node.esquerda;
        node.esquerda = filhoEsquerdo.direita;
        if (filhoEsquerdo.direita != null) {
            filhoEsquerdo.direita.pai = node;
        }
        filhoEsquerdo.pai = node.pai;
        if (node.pai == null) {
            raiz = filhoEsquerdo;
        } else if (node == node.pai.direita) {
            node.pai.direita = filhoEsquerdo;
        } else {
            node.pai.esquerda = filhoEsquerdo;
        }
        filhoEsquerdo.direita = node;
        node.pai = filhoEsquerdo;
    }
}
