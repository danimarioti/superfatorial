package br.edu.udesc.superfatorial;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Leitura simplificada estilo NIO
 *
 * @author udesc
 *
 */
class FatorialSequentialReaderBasicNIO extends FatorialFileReader {

    private static final String SEPARATOR = ";";
    private final Map<Integer, BigInteger> conteudoSupFat = new HashMap<Integer, BigInteger>();
    private final Map<Integer, BigInteger> conteudoFat = new HashMap<Integer, BigInteger>();
    private int linesFat, linesSupFat;

    /**
     * Realiza a leitura usando java NIO
     */
    @Override
    public void reader(String nome, int key) throws IOException {
        /*Path path = Paths.get(nome+"DisckCached.txt");
        String nomeFat = nome + "Fat";
        Path pathFat = Paths.get(nomeFat);
        super.setKey(key);
        List<String> linhasArquivo = Files.readAllLines(path);
        List<String> linhasArquivoFat = Files.readAllLines(pathFat);
        linesSupFat = linhasArquivo.size();
        linhasArquivo.forEach((linha) -> {
            this.add(linha, conteudoSupFat);
        });
        linhasArquivoFat.forEach((linhas) -> {
            this.add(linhas, conteudoFat);
        });*/
    }

    public int setLines(String nomeFat, String nomeSupFat) {
        Path path = Paths.get("src/br/edu/udesc/superfatorial/" + nomeFat + "DiskCached.txt").toAbsolutePath();
        Path pathSup = Paths.get("src/br/edu/udesc/superfatorial/" + nomeSupFat + "DiskCached.txt").toAbsolutePath();
        List<String> linhasArquivoFat;
        List<String> linhasArquivoSupFat;
        try {
            linhasArquivoFat = Files.readAllLines(path);
        } catch (IOException ex) {
            return 1;
        }
        try {
            linhasArquivoSupFat = Files.readAllLines(path);
        } catch (IOException ex) {
            return 2;
        }
        linesSupFat = linhasArquivoFat.size();
        linesFat = linhasArquivoSupFat.size();
        return 0;
    }

    public int getLineFat() {
        return linesFat;
    }

    public int getLineSupFat() {
        return linesSupFat;
    }

    public BigInteger valueByLine(String nome, int key) {
        Path path = Paths.get("src/br/edu/udesc/superfatorial/" + nome + "DiskCached.txt").toAbsolutePath();
        List<String> linhasArquivo;
        try {
            linhasArquivo = Files.readAllLines(path);
        } catch (IOException ex) {
            System.out.println(ex);
            return null;
        }
        if (key < linhasArquivo.size()) {
            String[] value = linhasArquivo.get(key).split(SEPARATOR);
            return (new BigInteger(value[1]));
        }
        return null;
    }

    private void add(String linha, Map<Integer, BigInteger> conteudo) {
        String[] value = linha.split(SEPARATOR);
        Integer key = Integer.parseInt(value[0]);
        BigInteger data = new BigInteger(value[1]);
        if (key.equals(super.getKey())) {
            super.setValue(data);
            conteudo.put(key, data);
        }
    }
}
