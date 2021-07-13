package br.uerj.petrinetanalyzer.common;

/**
 * Esta classe � a classe base para defini��o de Lugares (Places)
 * em uma Rede de Petri.
 *  
 * @author Felipe Lino
 * <BR>Data: 09/12/2006
 * <BR>Atualizado: 09/02/2007
 */
public class PlaceBase
{
	/**
	 * Nome do Lugar (Opcional)
	 */
	private String nome = null;
	/**
	 * Informa a posi��o do Lugar. Para auxiliar a representa��o
	 * da Rede de Petri em Matriz.
	 */
	private int posicao = 0;
	/**
	 * Informa o n�mero de fichas no Lugar.
	 */
	private int fichas = 0;
	
	/**
	 * Para ser usado no lugar do nome. Possui o formato: "Pn"
	 */
	private String identificador = null;
	
	public PlaceBase()
	{
		nome = "";
		posicao = 0;
		fichas = 0;
		identificador = "P0";
	}
	
	/**
	 * Construtor de PlaceBase.
	 * 
	 * @param nome    nome do Lugar  
	 * @param fichas  n�mero de fichas do Lugar
	 * @param posicao posi��o na matriz
	 * 
	 */	
	public PlaceBase(String nome, int fichas, int posicao)
	{
		this.posicao = posicao;
		this.identificador = "P" + posicao;
		this.fichas  = fichas;

		if(nome != null)
			this.nome = nome.trim();
		else
			nome = null;
	
	}
	
	/**
	 * Construtor de PlaceBase.
	 * <BR><BR>
	 * N�o sendo fornecido o nome, um identificado ser� usado
	 * em seu lugar, ser� nomeado da sguinte forma: "Pn", onde
	 * n � a posi��o.
	 * 
	 * @param fichas  n�mero de fichas no Lugar
	 * @param posicao posi��o na matriz
	 */
	public PlaceBase(int fichas, int posicao)
	{
		this.posicao = posicao;
		this.identificador = "P" + posicao;
		this.fichas = fichas;
		this.nome = null;
	}
	
	/**
	 * Construtor de PlaceBase. 
	 * <BR><BR>
	 * N�mero de fichas default � Zero.
	 * O identificador usado no lugar do nome � da forma
	 * "Pn", onde n � a posi��o na matriz.
	 * 
	 * @param posicao posi��o na matriz.
	 */
	public PlaceBase(int posicao)
	{
		this.posicao = posicao;
		this.identificador = "P" + posicao;
		this.fichas = 0;
		this.nome = null;
	}
	
	/**
	 * Retorna o nome do Lugar.
	 * @return nome do Lugar, ou identificador se nenhum
	 * nome foi fornecido.
	 */
	public String getNome() 
	{
		if((nome == null) || ( "".equals(nome.trim()) ))
			return getIdentificador();
		else
			return nome;
	}
	
	/**
	 * Retorna a posi��o do Lugar na matriz.
	 * @return posi��o do Lugar na matriz.
	 */
	public int getPosicao()
	{
		return posicao;
	}
	
	/**
	 * Retorna o n�mero de fichas do lugar.
	 * @return n�mero de fichas do lugar
	 */
	public int getFichas()
	{
		return fichas;
	}
	
	/**
	 * Retorna identificador.
	 * @return identificador no formato "Pn"
	 */
	public String getIdentificador()
	{
		return identificador;
	}
	
	/**
	 * Seta o nome do Lugar.
	 * @param nome Nome do Lugar
	 */
	public void setNome(String nome)
	{
		if(nome != null)
			this.nome = nome.trim();
		else
			nome = null;
	}
	
	/**
	 * Seta a posi��o do Lugar na matriz.
	 * @param posicao posi��o do Lugar na matriz
	 */
	public void setPosicao(int posicao)
	{
		this.posicao = posicao;
		this.identificador = "P" + posicao;
	}
	
	/**
	 * Seta a quantidade de fichas do Lugar.
	 * @param fichas n�mero de fichas do Lugar
	 */
	public void setFichas(int fichas)
	{
		this.fichas = fichas;
	}
	
	/**
	 * Retorna String com informa��es do Lugar.
	 * @return informa��es do Lugar
	 */
	public String toString()
	{
		return "        Lugar:["+getNome()         +"]\n"
		      +"Identificador:["+getIdentificador()+"]\n"
		      +"       Fichas:["+getFichas()       +"]\n"
		      +"      Posicao:["+getPosicao()      +"]";
	}
}
