import java.util.Comparator;
import java.util.ArrayList;
import java.util.Scanner;

import algorithms.*;
import entities.*;

public class App {
    public static void main(String[] args) throws Exception {
        long inicio = 0, fim = 0;
        Produto[] vetor = Parser.produto();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nAlgoritmo: ");
        System.out.println("[0] SelectSort");
        System.out.println("[1] InsertSort");
        System.out.println("[2] ShellSort");
        System.out.println("[3] MergeSort");
        System.out.println("[4] QuickSort");
        System.out.println("[5] HeapSort");
        System.out.println("[6] QM-Sort");
        System.out.println("[7] SI-Sort");
        System.out.print("Escolha um algoritmo: ");
        int algoritmo = Integer.parseInt(scanner.nextLine());

        System.out.println("\nComparador: ");
        System.out.println("[0] de ItemID");
        System.out.println("[1] de Title");
        System.out.println("[2] de DomainID");
        System.out.println("[3] de ProductID");
        System.out.println("[4] de Price");
        System.out.println("[5] de CategoryID");
        System.out.println("[6] de Condition");
        System.out.print("Escolha um comparador: ");
        int comparador = Integer.parseInt(scanner.nextLine());

        System.out.println("\nOrdem:");
        System.out.println("[0] Crescente");
        System.out.println("[1] Decrescente");
        System.out.print("Escolha uma ordem: ");
        int ordem = Integer.parseInt(scanner.nextLine());

        ArrayList<Comparator<Produto>> comparadores = new ArrayList<>();
        comparadores.add(new ComparaItemId());
        comparadores.add(new ComparaTitle());
        comparadores.add(new ComparaDomainID());
        comparadores.add(new ComparaProductId());
        comparadores.add(new ComparaPrice());
        comparadores.add(new ComparaCategoryId());
        comparadores.add(new ComparaCondition());

        ArrayList<SortStrategy<Produto>> algoritmos = new ArrayList<>();
        algoritmos.add(new SelectSort<>(comparadores.get(comparador)));
        algoritmos.add(new InsertSort<>(comparadores.get(comparador)));
        algoritmos.add(new MergeSort<>(comparadores.get(comparador)));
        algoritmos.add(new QuickSort<>(comparadores.get(comparador)));
        algoritmos.add(new HeapSort<>(comparadores.get(comparador)));
        algoritmos.add(new QMSort<>(comparadores.get(comparador)));
        algoritmos.add(new SISort<>(comparadores.get(comparador)));

        switch (ordem) {
            case 0 -> {
                inicio = System.currentTimeMillis();
                algoritmos.get(algoritmo).ordemDecrescente(vetor);
                fim = System.currentTimeMillis();
            }
            case 1 -> {
                inicio = System.currentTimeMillis();
                algoritmos.get(algoritmo).ordemDecrescente(vetor);
                fim = System.currentTimeMillis();
            }
        }

        long n = algoritmos.get(algoritmo).getNComparacoes();
        System.out.printf("\nNúmero de Comparações: %d\n", n);
        System.out.printf("Tempo de Execução: %d ms\n\n", fim-inicio);

        System.out.println("Os 10 Primeiros:\n");
        for (int i = 0; i<10; i++) {
            System.out.println(vetor[i]);
        }

        System.out.println("Os 10 Últimos:\n");
        for (int i = 99990; i<100000; i++) {
            System.out.println(vetor[i]);
        }

        scanner.close();
    }
}
