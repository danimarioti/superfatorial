/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.udesc.superfatorial;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

/**
 *
 * @author lx
 */
public class SuperFatorialDiskCached extends SuperFatorial {

    private static final String SUPERFATORIAL = "superFatorial";
    private static final String FATORIAL = "fatorial";
    private final HashMap<Integer, BigInteger> diskCachedFat;
    private final HashMap<Integer, BigInteger> diskCachedSupFat;
    private final FatorialSequentialReaderBasicNIO reader;
    private final FatorialFileWriter writer;

    public SuperFatorialDiskCached() {
        this.diskCachedFat = new HashMap();
        this.diskCachedSupFat = new HashMap();
        this.reader = new FatorialSequentialReaderBasicNIO();
        this.writer = new FatorialFileWriter();
    }

    public HashMap<Integer, BigInteger> getDiskCachedSupFat() {
        return diskCachedSupFat;
    }

    public HashMap<Integer, BigInteger> getDiskCachedFat() {
        return diskCachedFat;
    }

    @Override
    public BigInteger getSuperFatorial(int numero) throws InputException {
        //procura no cache disco, se existir retorna o valor
        //se nao existir calcula e adicona no cache;
        if (reader.setLines(FATORIAL, SUPERFATORIAL) != 0) {//as linhas não foram lidas com sucesso
            writer.write(SUPERFATORIAL, 0, new BigInteger("1"));
            writer.write(FATORIAL, 0, new BigInteger("1"));
            reader.setLines(FATORIAL, SUPERFATORIAL);
        }
        if (numero < reader.getLineSupFat()) {//O Superfatorial já foi calculado
            //sempre deve-se procurar por tamArquivo +1, pois se você quer o SuperFatorial de 5
            //você está procurando a linha 6 do arquivo ( pois existe o Superfatorial de 0 na linha 1.
            return reader.valueByLine(SUPERFATORIAL, numero);
        }
        //caso não tenha entrado no if, o superfatorial não foi calculado
        //então deve-se pegar o ultimo superfatorial calculado e calcular os que faltam
        BigInteger result = reader.valueByLine(SUPERFATORIAL, reader.getLineSupFat() - 1);
        if (result == null) {
            result = new BigInteger("1");
        }
        for (int i = reader.getLineSupFat(); i <= numero; i++) {
            result = result.multiply(getFatorial(i));
            writer.write(SUPERFATORIAL, i, result); // adiciona o novo superfatorial calculado no arquivo;
            reader.setLines(FATORIAL, SUPERFATORIAL);
        }
        return result;
    }

    @Override
    protected BigInteger getFatorial(int numero) {
        if (numero < reader.getLineFat()) {
            return reader.valueByLine(FATORIAL, numero);
        }
        BigInteger result = reader.valueByLine(FATORIAL, reader.getLineFat() - 1);
        if (result == null) {
            result = new BigInteger("1");
        }
        for (int i = reader.getLineFat(); i <= numero; i++) {
            result = result.multiply(new BigInteger(Integer.toString(i)));
            writer.write(FATORIAL, i, result);
            reader.setLines(FATORIAL, SUPERFATORIAL);
        }
        return result;
    }

}
