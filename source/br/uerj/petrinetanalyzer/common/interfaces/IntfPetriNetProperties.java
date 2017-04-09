package br.uerj.petrinetanalyzer.common.interfaces;

/**
 * Interface que trata das Propriedades de uma Rede de Petri.
 * 
 * @author Felipe Lino
 * <BR>Data: 09/12/2006
 */
public interface IntfPetriNetProperties
{
	/*
	 * Métodos que retornam um conjunto de propriedades
	 * As propriedades retornadas pela análise da Rede devem ser setadas
	 * usando essa interface, demais propriedades verificadas devem ser
	 * acrescentadas aqui.
	 * 
	 * Outra alternativa a isso seria o retorno de uma String, que deveria
	 * sofrer um parse para exibir as propriedades na tela.
	 */
	
	/**
	 * Retorna o nome da Rede de Petri
	 * @return Nomde da Rede de Petri
	 */
	public String getNomeRedePetri();
	
	/**
	 * Informa se a Rede é ou não limitada.
	 * @return Retorna true caso a Rede seja Limitada.
	 */
	public boolean ehLimitada();
	
	/**
	 * Informa se a Rede é ou não conservativa.
	 * @return Retorna true caso a Rede seja conservativa.
	 */
	public boolean ehConservativa();
	
	/**
	 * Informa se a Rede é ou não viva.
	 * @return Retorna true caso a Rede seja viva.
	 */
	public boolean ehViva();
	
	/**
	 * Retorna o Limite da Rede. Limite é o máximo de fichas
	 * que podem existir na Rede.
	 * @return Retorna um inteiro que é o Limite. Retorna -1 caso
	 * o limite seja Infinito.
	 */
	public int getLimite();
	
	
	/**
	 * String contendo um resumo das propriedades da Rede de Petri.
	 * @return Resumo das propriedades da Rede de Petri.
	 */
	public String toString();
	
}
