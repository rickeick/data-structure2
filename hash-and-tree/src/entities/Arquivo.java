package entities;

import java.time.LocalDate;

public class Arquivo {
    public String nome;
    public String tipo;
    public String caminho;
    public Integer tamanho;
    public LocalDate criacao;
    public LocalDate modificacao;
    
    public Arquivo(
        String nome,
        String tipo,
        String caminho,
        Integer tamanho,
        LocalDate criacao,
        LocalDate modificacao) {
        this.nome = nome;
        this.tipo = tipo;
        this.caminho = caminho;
        this.tamanho = tamanho;
        this.criacao = criacao;
        this.modificacao = modificacao;
    }

    @Override
    public String toString() {
        return caminho+" -> "+tamanho;
    }
}
