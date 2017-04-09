package br.uerj.petrinetanalyzer.common.interfaces;


/**
 * 
 * Esta Interface trata dos principais métodos que a engine
 * do simulador deve ter disponível para a interação com a
 * interface gráfica.
 * 
 * @author Felipe Lino
 * <BR>Data: 20/02/2007
 * 
 */

public interface IntfSimulator
{
	// Construtor que receba IntfPetriNetBase como parâmetro
	// public ImplSimulator(IntfPetriNetBase redePetri);
	
	/**
	 * Array boleano informando se a transição está ou não habilitada.
	 * @return Array Boleano informando se a transição especificada pela posição está ou
	 * não habilitada.
	 */
	public boolean[] getTransicoesDisponiveis();
	
	/**
	 * Dispara transição indicada pela posição.
	 * @param posicao Posição da Transição.
	 */
	public void disparaTransicao(int posicao);
	
	/**
	 * Retorna array com as marcações dos lugares, após o disparo de uma transição
	 * @return Array com um inteiro indicando as marcações dos Lugares.
	 */
	public int[] getMarcacoes();
	
	/**
	 * Verifica se existe alguma transição disponível para ser disparada.
	 * @return Retorna true caso tenha alguma transição habilitada para disparo,
	 * retorna false caso contrário.
	 */
	public boolean temTransicaoDisponivel();
	
}
