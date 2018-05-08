package br.edu.udesc.superfatorial;

import java.math.BigInteger;

//import br.edu.udesc.exceptions.InputException;
/**
 * Assinatura para o SuperFatorial
 *
 * @author Renato, Carlos e Daniela
 *
 */
public interface ISuperFatorial {

    /**
     * Recebe o fatorial do número e retorna o superfatorial dele
     *
     * @param numero para o qual calcularemos o superfatorial
     * @return super fatorial
     * @throws InputException indicando que o valor digitado é inválido
     */
    public BigInteger getSuperFatorial(int numero) throws InputException;

}
