import java.util.LinkedList;
import java.util.Comparator;
import java.util.Scanner;

import hashtables.*;
import entities.*;
import trees.*;

public class App {
    public static void main(String[] args) throws Exception {
        int contador = 0, somatorio = 0;
        long inicio, fim, tempo1, tempo2;
        Arquivo[] arquivos = Parser.arquivo();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nTabela Hash:");
        System.out.println("[0] Hash Quadratico");
        System.out.println("[1] Hash Encadeado Externo");
        System.out.print("Escolha uma tabela hash: ");
        int hashtable = Integer.parseInt(scanner.nextLine());

        LinkedList<TabelaHash<String,Arquivo>> tabelashash = new LinkedList<>();
        tabelashash.add(new HashQuadratico<>(625));
        tabelashash.add(new HashExterno<>(625));

        inicio = System.currentTimeMillis();
        for (Arquivo arquivo : arquivos) {
            String chave = arquivo.caminho;
            tabelashash.get(hashtable).inserir(chave, arquivo);
        }
        fim = System.currentTimeMillis();
        tempo1 = fim - inicio;

        System.out.print("\nPalavra Chave da Pesquisa: ");
        String pesquisa = scanner.nextLine();

        System.out.println("\nÁrvore para a Pesquisa:");
        System.out.println("[0] Árvore de Busca Binária");
        System.out.println("[1] Árvore Rubro-Negra");
        System.out.println("[2] Árvore AVL");
        System.out.print("Escolha uma árvore: ");
        int arvore = Integer.parseInt(scanner.nextLine());

        System.out.println("\nOrdenar Pesquisa por: ");
        System.out.println("[0] Nome");
        System.out.println("[1] Tipo");
        System.out.println("[2] Caminho");
        System.out.println("[3] Tamanho");
        System.out.println("[4] Data de Criação");
        System.out.println("[5] Data de Modificação");
        System.out.print("Escolha uma ordem: ");
        int comparador = Integer.parseInt(scanner.nextLine());

        LinkedList<Comparator<Arquivo>> comparadores = new LinkedList<>();
        comparadores.add(new ComparaNome());
        comparadores.add(new ComparaTipo());
        comparadores.add(new ComparaCaminho());
        comparadores.add(new ComparaTamanho());
        comparadores.add(new ComparaCriacao());
        comparadores.add(new ComparaModificacao());

        LinkedList<Arvore<Arquivo>> arvores = new LinkedList<>();
        arvores.add(new ArvoreBinaria<>(comparadores.get(comparador)));
        arvores.add(new ArvoreRubroNegra<>(comparadores.get(comparador)));
        arvores.add(new ArvoreAVL<>(comparadores.get(comparador)));

        inicio = System.currentTimeMillis();
        for (String chave : tabelashash.get(hashtable).getChaves()) {
            if (chave.contains(pesquisa)) {
                long inicioBusca = System.currentTimeMillis();
                Arquivo arquivo = tabelashash.get(hashtable).buscar(chave);
                long fimBusca = System.currentTimeMillis();
                arvores.get(arvore).inserir(arquivo);
                somatorio += fimBusca-inicioBusca;
                contador++;
            }
        }
        fim = System.currentTimeMillis();
        tempo2 = fim - inicio;
        
        arvores.get(arvore).inOrdem();

        System.out.println("\nRelatório da TabelaHash: ");
        System.out.printf("Quantidade de Inserções: %d\n", arquivos.length);
        System.out.printf("Tempo Total de Buscas: %d ms\n", somatorio);
        System.out.printf("Tempo de Inserção: %d ms\n", tempo1);
        
        System.out.println("\nRelatório da Árvore:");
        System.out.printf("Quantidade de Inserções: %d\n", contador);
        System.out.printf("Tempo de Inserção: %d ms\n\n", tempo2);
        
        scanner.close();
    }
}
