package br.uerj.petrinetanalyzer.common.interfaces;


/**
 * 
 * Esta Interface trata dos principais m�todos que a engine
 * do simulador deve ter dispon�vel para a intera��o com a
 * interface gr�fica.
 * 
 * @author Felipe Lino
 * <BR>Data: 20/02/2007
 * 
 */

public interface IntfSimulator
{
	// Construtor que receba IntfPetriNetBase como par�metro
	// public ImplSimulator(IntfPetriNetBase redePetri);
	
	/**
	 * Array boleano informando se a transi��o est� ou n�o habilitada.
	 * @return Array Boleano informando se a transi��o especificada pela posi��o est� ou
	 * n�o habilitada.
	 */
	public boolean[] getTransicoesDisponiveis();
	
	/**
	 * Dispara transi��o indicada pela posi��o.
	 * @param posicao Posi��o da Transi��o.
	 */
	public void disparaTransicao(int posicao);
	
	/**
	 * Retorna array com as marca��es dos lugares, ap�s o disparo de uma transi��o
	 * @return Array com um inteiro indicando as marca��es dos Lugares.
	 */
	public int[] getMarcacoes();
	
	/**
	 * Verifica se existe alguma transi��o dispon�vel para ser disparada.
	 * @return Retorna true caso tenha alguma transi��o habilitada para disparo,
	 * retorna false caso contr�rio.
	 */
	public boolean temTransicaoDisponivel();
	
}
