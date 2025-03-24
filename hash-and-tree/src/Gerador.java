import java.util.Random;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class Gerador {
    private static final Random RANDOM = new Random();
    private static final LocalDate INICIO = LocalDate.of(2022,1,1);
    private static final LocalDate ONTEM = LocalDate.now().minusDays(1);
    private static final File PASTA = new File("C:\\Users\\rickeick\\Projetos");
    
    public static void main(String[] args) throws Exception {
        (new File("data")).mkdir();
        FileWriter metadados = new FileWriter("data/metadados.csv");
        BufferedWriter escritor = new BufferedWriter(metadados);
        visitar(PASTA, escritor);
        escritor.close();
    }
    
    private static void visitar(File diretorio, BufferedWriter escritor) throws Exception {
        File[] conteudo = diretorio.listFiles();
        if (conteudo != null) {
            for (File node : conteudo) {
                String nome = getNome(node);
                if (node.isFile() && nome.charAt(0) != '.') {
                    String tipo = getTipo(node);
                    String caminho = node.getPath();
                    Integer tamanho = gerarTamanho();
                    LocalDate criacao = gerarData(INICIO, ONTEM);
                    LocalDate modificacao = gerarData(criacao, ONTEM);
                    String linha = 
                        nome+","+
                        tipo+","+
                        caminho+","+
                        tamanho+","+
                        criacao+","+
                        modificacao+"\n";
                    escritor.write(linha);
                } else {
                    visitar(node, escritor);
                }
            }
        }
    }
    
    private static String getNome(File arquivo) {
        String[] caminho = arquivo.getPath().split("/");
        return caminho[caminho.length-1];
    }
    
    private static String getTipo(File arquivo) {
        String[] caminho = arquivo.getPath().split("/");
        String nome = caminho[caminho.length-1];
        if (nome.contains(".")) {
            String[] split = nome.split("\\.");
            if (split.length != 0) {
                return split[split.length-1];
            }
        }
        return null;
    }
    
    private static LocalDate gerarData(LocalDate inicio, LocalDate fim) {
        long dias = ChronoUnit.DAYS.between(inicio, fim);
        return inicio.plusDays(RANDOM.nextInt((int)dias));
    }
    
    private static int gerarTamanho() {
        return RANDOM.nextInt(1048576)+1;
    }
}
