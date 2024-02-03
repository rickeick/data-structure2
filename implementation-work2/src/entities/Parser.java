package entities;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.time.LocalDate;

public class Parser {
    public static Arquivo[] arquivo() throws IOException {
        int i = 0;
        String linha;
        Arquivo[] arquivos = new Arquivo[10000];
        FileReader metadados = new FileReader("data/metadados.csv");
        BufferedReader leitor = new BufferedReader(metadados);
        while ((linha = leitor.readLine()) != null) {
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
            arquivos[i++] = arquivo;
        }
        leitor.close();
        return arquivos;
    }
}
