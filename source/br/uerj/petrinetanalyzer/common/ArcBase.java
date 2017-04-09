package br.uerj.petrinetanalyzer.common;

/**
 * Esta classe é a classe base para definição de Arcos (Arcs)
 * em Redes de Petri.
 * 
 * @author Felipe Lino
 * <BR>Data: 09/12/2006
 * <BR>Atualizado: 28/01/2007
 */
public class ArcBase
{
	/**
	 * Informa o peso do arco, o default é 1. Só admite
	 * valores positivos.
	 */
	private int peso = 1;
	
	/**
	 * Variável que informa se o arco é de entrada. Um arco 
	 * de entrada é aquele que sai de um Lugar e entra
	 * em uma Transição.
	 */
	private boolean ehEntrada;
	
	/**
	 * Posição do Arco na Lista de Arcos
	 */
	private int posicao;
	/**
	 * Lugar ligado ao Arco.
	 */
	private PlaceBase lugar;
	
	/**
	 * Transição ligada ao Arco. 
	 */
	private TransitionBase transicao;
	
	/**
	 * Construtor de ArcBase.
	 * @param peso      peso do Arco
	 * @param lugar     Lugar ligado ao Arco
	 * @param transicao Transição ligada ao Arco
	 * @param ehEntrada Informa se o arco sai de uma Transição e entra em um Lugar ou não.
	 */
	public ArcBase(int peso, PlaceBase lugar, TransitionBase transicao, boolean ehEntrada)
	{
		this.peso  = peso;
		this.lugar = lugar;
		this.transicao = transicao;
		this.ehEntrada = ehEntrada;
	}
	
	/**
	 * Construtor de ArcBase.
	 * Peso default será 1.
	 * @param lugar     Lugar ligado ao Arco
	 * @param transicao Transição ligada ao Arco
	 * @param ehEntrada Informa se o arco sai de uma Transição e entra em um Lugar ou não.
	 */
	public ArcBase(PlaceBase lugar, TransitionBase transicao, boolean ehEntrada)
	{
		this.peso  = 1;
		this.lugar = lugar;
		this.transicao = transicao;
		this.ehEntrada = ehEntrada;
	}
	
	/**
	 * Construtor default de ArcBase.
	 */
	public ArcBase()
	{
		this.peso = 1;
		this.lugar = null;
		this.transicao = null;
		this.ehEntrada = true;
	}
	
	/**
	 * Seta a posicao do Arco na Lista de Arcos
	 * @param posicao
	 */
	public void setPosicao(int posicao)
	{
		this.posicao = posicao;
	}
	
	/**
	 * Retorna a posição do Arco na Lista de Arcos
	 * @return Posicao
	 */
	public int getPosicao()
	{
		return this.posicao;
	}
	
	/**
	 * Retorna o peso do Arco.
	 * @return peso do Arco
	 */
	public int getPeso()
	{
		return peso;
	}
	
	/**
	 * Retorna uma instância de PlaceBase que é o Lugar ligado ao Arco.
	 * @return Lugar ligado ao Arco
	 */
	public PlaceBase getLugar()
	{
		return lugar;
	}
	
	/**
	 * Retorna uma instância de TransitionBase que é a Transição ligada ao Arco.
	 * @return Transição ligada ao Arco
	 */
	public TransitionBase getTransicao()
	{
		return transicao;
	}
	
	/**
	 * Retorna true caso o arco saia de um Lugar e entre em uma Transição.
	 * E retorna false caso o arco saia de uma Transição e entre em um Lugar.
	 * 
	 * @return true caso o arco seja de entrada em uma Transição e false, caso
	 * seja de saída de uma Transição.
	 */
	public boolean verifyEntrada()
	{
		return ehEntrada;
	}
	
	/**
	 * Retorna false caso o arco saia de um Lugar e entre em uma Transição.
	 * E retorna true caso o arco saia de uma Transição e entre em um Lugar.
	 * 
	 * @return false caso o arco seja de saida de um Lugar e true, caso
	 * seja de entrada de um Lugar.
	 */
	
	public boolean verifySaida()
	{
		return (! ehEntrada);
	}
	
	/**
	 * Seta o peso do Arco.
	 * @param peso peso do Arco
	 */
	public void setPeso(int peso)
	{
		this.peso = peso;
	}
	
	/**
	 * Seta o Lugar ligado ao Arco.
	 * @param lugar Lugar ligado ao Arco
	 */
	public void setLugar(PlaceBase lugar)
	{
		this.lugar = lugar;
	}
	
	/**
	 * Seta a Transição ligada ao Arco.
	 * @param transicao Transição ligada ao Arco
	 */
	public void setTransicao(TransitionBase transicao)
	{
		this.transicao = transicao;
	}
	
	/**
	 * Informa que o Arco é de entrada em uma Transição.
	 */
	public void setEntrada()
	{
		ehEntrada = true;
	}
	
	/**
	 * Informa que o Arco é de saída de uma Transição.
	 *
	 */
	public void setSaida()
	{
		ehEntrada = false;
	}
	
	/**
	 * Retorna String com informações do Arco.
	 * @return informações do Arco
	 */
	public String toString()
	{
		String retorno = "Arco";
		
		if(verifyEntrada())
			retorno += " de entrada\n";
		else
			retorno += " de saida\n";
		
		retorno += "    Lugar:["+getLugar().getNome()    +"]\n"
		        +  "Transicao:["+getTransicao().getNome()+"]";
		
		return retorno;
	}
	
	
}
