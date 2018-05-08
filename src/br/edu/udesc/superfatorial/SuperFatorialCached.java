package br.edu.udesc.superfatorial;

import java.math.BigInteger;
import java.util.HashMap;
//3
/**
 * Antes de calcular o fatorial busca no cache
 *
 * @author Renato, Carlos e Daniela
 *
 */
public class SuperFatorialCached extends SuperFatorial {

    private final HashMap<Integer, BigInteger> cacheFat;

    public HashMap<Integer, BigInteger> getCacheFat() {
        return cacheFat;
    }

    public HashMap<Integer, BigInteger> getCacheSupFat() {
        return cacheSupFat;
    }
    private final HashMap<Integer, BigInteger> cacheSupFat;

    public SuperFatorialCached() {
        this.cacheFat = new HashMap();
        this.cacheSupFat = new HashMap();
        this.cacheFat.put(0,new BigInteger("1"));
        this.cacheSupFat.put(0,new BigInteger("1"));
    }

    @Override
    public BigInteger getSuperFatorial(int numero) throws InputException {
        BigInteger total;
        int minSup;
        if (numero < 0) {
            throw new NegativeValueEnteredException();
        }
        if (!cacheSupFat.containsKey(numero)) { //não possui o Superfatorial no número
            for (minSup = numero; minSup > 0; minSup--) { //Verifica se existe algum superfatorial já calculado para acelerar o processamento
                if (cacheSupFat.containsKey(minSup)) {// foi encontrado algum Superfatorial previamente calculado.
                    break;
                }
            }
            total = cacheSupFat.get(minSup);
            for (int i = minSup + 1; i <= numero; i++) { //calcula o superfatorial iniciando no minsup.
                total=total.multiply(getFatorial(i));
                cacheSupFat.put(i, total);
            }
        } else {
            total = cacheSupFat.get(numero);
        }
        return total;
    }

    @Override
    protected BigInteger getFatorial(int numero) {
        BigInteger fat;
        int cont = 1;
        int minFat;
        if (!cacheFat.containsKey(numero)) { //não possui o fatorial no número
            for (minFat = numero; minFat > 0; minFat--) { //Verifica se existe algum fatorial já calculado para acelerar o processamento
                if (cacheFat.containsKey(minFat)) {// foi encontrado algum fatorial previamente calculado.
                    break;
                }
            }
            fat = cacheFat.get(minFat);
            for (int i = minFat + 1; i <= numero; i++) { //calcula o fatorial iniciando no minfat.
                fat=fat.multiply(new BigInteger(Integer.toString(i)));
                cacheFat.put(i, fat);
            }
        } else {
            fat = cacheFat.get(numero);
        }
        return fat;
    }

}
