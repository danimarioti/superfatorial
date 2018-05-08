package br.edu.udesc.superfatorial;

import java.math.BigInteger;

/**
 * Implementação do super fatorial
 *
 * @author udesc
 *
 */
public class SuperFatorial implements ISuperFatorial {

    @Override
    public BigInteger getSuperFatorial(int numero) throws InputException {
        BigInteger total = new BigInteger("1");
        if (numero < 0) {
            throw new NegativeValueEnteredException();
        }
        for (int i = 1; i <= numero; i++) {
            total = total.multiply(getFatorial(i));
            System.out.println("Total: "+ total);
        }
        return total;
    }

    protected BigInteger getFatorial(int numero) {
        BigInteger fat = new BigInteger("1");
        int cont = 1;
        int i;
        do {
            //pegar o numero da janela: num
            for (i = 1; i <= numero; i++) {
                fat = fat.multiply(BigInteger.valueOf(i));
            }
            cont++;

        } while (cont < 2);
        return fat;
    }
}
