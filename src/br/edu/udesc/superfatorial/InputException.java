package br.edu.udesc.superfatorial;

/**
 * Exceção gerada para quando os dados são entrados de forma errada
 * @author Renato, Carlos e Daniela
 *
 */
public abstract class InputException extends Exception {

	/**
	 * Mensagem do erro
	 * @param msg
	 */
	public InputException(String msg) {
		super(msg);
	}

}
