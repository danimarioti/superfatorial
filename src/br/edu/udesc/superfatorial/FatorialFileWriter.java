package br.edu.udesc.superfatorial;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe base para os programas de leitura
 *
 * @author udesc
 *
 */
public class FatorialFileWriter {

    protected static final String SEPARATOR = ";";

    public void write(String nome, int key, BigInteger value) {
        String contentToAppend = key + ";" + value + "\n";
        nome = "src/br/edu/udesc/superfatorial/" + nome + "DiskCached.txt";
        Path path = Paths.get(nome).toAbsolutePath();
        if (Files.exists(path)) {
            try {
                Files.write(path, contentToAppend.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ioExceptionObj) {
                System.out.println("Problema para escrever no arquivo= " + ioExceptionObj.getMessage());
            }
        } else {
            try {
                Files.createFile(Paths.get(nome).toAbsolutePath());
                Files.write(path, contentToAppend.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                System.out.println("Erro para criar o arquivo");
            }
        }

    }

}
