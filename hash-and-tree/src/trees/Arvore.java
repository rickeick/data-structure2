package trees;

import java.util.Comparator;

public abstract class Arvore<T> {
    protected Comparator<T> c;

    public abstract void inOrdem();

    public abstract void inserir(T elemento);

    public abstract boolean buscar(T elemento);

    public abstract void remover(T elemento);
}
