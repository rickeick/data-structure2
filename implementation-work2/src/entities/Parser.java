package entities;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.time.LocalDate;

public class Parser {
    private static final int N = 20000;

    public static Arquivo[] arquivo() throws IOException {
        String linha;
        Arquivo[] arquivos = new Arquivo[N];
        FileReader metadados = new FileReader("data/metadados.csv");
        BufferedReader leitor = new BufferedReader(metadados);
        for (int i = 0; i<N; i++) {
            linha = leitor.readLine();
            String[] dados = linha.split(",");
            Integer tamanho = Integer.parseInt(dados[3]);
            LocalDate criacao = LocalDate.parse(dados[4]);
            LocalDate modificacao = LocalDate.parse(dados[5]);
            Arquivo arquivo = new Arquivo(
                dados[0],
                dados[1],
                dados[2],
                tamanho,
                criacao,
                modificacao
            );
            arquivos[i] = arquivo;
        }
        leitor.close();
        return arquivos;
    }
}
