package entities;

import java.io.FileReader;
import java.io.BufferedReader;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import java.io.IOException;

public class Parser {
    public static Produto[] produto() throws IOException {
        int i = 0;
        String linha;
        Produto[] produtos = new Produto[100000];
        FileReader arquivo = new FileReader("data/dados.txt");
        BufferedReader leitor = new BufferedReader(arquivo);
        GsonBuilder construtor = new GsonBuilder();
        Gson gson = construtor.create();
        while ((linha = leitor.readLine()) != null) {
            Produto produto = gson.fromJson(linha, Produto.class);
            produtos[i] = produto;
            i++;
        }
        leitor.close();
        return produtos;
    }
}
