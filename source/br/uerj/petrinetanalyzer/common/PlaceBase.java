package br.uerj.petrinetanalyzer.common;

/**
 * Esta classe é a classe base para definição de Lugares (Places)
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
	 * Informa a posição do Lugar. Para auxiliar a representação
	 * da Rede de Petri em Matriz.
	 */
	private int posicao = 0;
	/**
	 * Informa o número de fichas no Lugar.
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
	 * @param fichas  número de fichas do Lugar
	 * @param posicao posição na matriz
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
	 * Não sendo fornecido o nome, um identificado será usado
	 * em seu lugar, será nomeado da sguinte forma: "Pn", onde
	 * n é a posição.
	 * 
	 * @param fichas  número de fichas no Lugar
	 * @param posicao posição na matriz
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
	 * Número de fichas default é Zero.
	 * O identificador usado no lugar do nome é da forma
	 * "Pn", onde n é a posição na matriz.
	 * 
	 * @param posicao posição na matriz.
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
	 * Retorna a posição do Lugar na matriz.
	 * @return posição do Lugar na matriz.
	 */
	public int getPosicao()
	{
		return posicao;
	}
	
	/**
	 * Retorna o número de fichas do lugar.
	 * @return número de fichas do lugar
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
	 * Seta a posição do Lugar na matriz.
	 * @param posicao posição do Lugar na matriz
	 */
	public void setPosicao(int posicao)
	{
		this.posicao = posicao;
		this.identificador = "P" + posicao;
	}
	
	/**
	 * Seta a quantidade de fichas do Lugar.
	 * @param fichas número de fichas do Lugar
	 */
	public void setFichas(int fichas)
	{
		this.fichas = fichas;
	}
	
	/**
	 * Retorna String com informações do Lugar.
	 * @return informações do Lugar
	 */
	public String toString()
	{
		return "        Lugar:["+getNome()         +"]\n"
		      +"Identificador:["+getIdentificador()+"]\n"
		      +"       Fichas:["+getFichas()       +"]\n"
		      +"      Posicao:["+getPosicao()      +"]";
	}
}
