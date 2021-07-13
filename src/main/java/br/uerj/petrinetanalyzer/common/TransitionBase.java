package br.uerj.petrinetanalyzer.common;

/**
 * Esta classe � a classe base para defini��o de transi��es
 * (transitions) em Redes de Petri.
 * 
 * @author Felipe Lino
 * <BR>Data: 09/12/2006
 * <BR>Atualizado: 07/02/2006
 */
public class TransitionBase
{
	/**
	 * Informa o nome da Transi��o (opcional)
	 */
	private String nome = null;
	/**
	 * Informa a posi��o da Transi��o. Para auxiliar
	 * na forma��o da representa��o da Rede de Petri em Matriz.
	 */
	private int posicao;
	
	/**
	 * Para ser usado no lugar do nome. Possui o formato: "Tn"
	 */
	private String identificador = null; 
	
	/**
	 * Static Earliest Firing Time (temporiza��o segundo o modelo de Merlin) 
	 */
	private double seft = 0.0;
	
	/**
	 * Static Latest Firing Time (temporiza��o segundo o modelo de Merlin)
	 */
	private double slft = 0.0;
	
	/**
	 * Curva de Densidade de probabilidade: Altera o modo como ser� interpretado os valores de SEFT e SLFT
	 *	Seu dom�nio �: UNIFORME=0, NORMAL=1 e EXPONENCIAL=2.
	 */
	private int curvaDensidade;
	
	public final static int UNIFORME    = 0;
	public final static int NORMAL      = 1;
	public final static int EXPONENCIAL = 2; 
	
	/**
	 * Construtor default.
	 *
	 */
	public TransitionBase()
	{
		
	}
	
	
	/**
	 * Construtor de TransitionBase.
	 * @param nome    nome da Transi��o
	 * @param posicao posi��o na matriz
	 */
	public TransitionBase(String nome, int posicao)
	{
		if(nome != null)
			this.nome = nome.trim();
		else
			nome = null;
		
		this.posicao = posicao;
		this.identificador = "T" + posicao;
	}
	
	/**
	 * Construtor de TransitionBase.
	 * Na aus�ncia do nome, o identificador "Tn" ser� usado 
	 * em seu lugar.
	 * @param posicao posi��o da Transi��o na matriz
	 */
	public TransitionBase(int posicao)
	{
		this.nome = null;
		this.posicao = posicao;
		this.identificador = "T" + posicao;
	}
	
	/**
	 * Retorna o nome da Transi��o.
	 * @return nome da Transi��o, ou identificador se nenhum
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
	 * Retorna a posi��o da Transi��o na matriz.
	 * @return posi��o da Transi��o na matriz.
	 */
	public int getPosicao()
	{
		return posicao;
	}
	
	/**
	 * Retorna identificador.
	 * @return identificador no formato "Tn"
	 */
	public String getIdentificador()
	{
		return identificador;
	}
	
	/**
	 * Retorna o Static Earliest Firing Time.
	 * @return Static Earliest Firing Time
	 */
	public double getSEFT()
	{
		return seft;
	}
	
	/**
	 * Retorna o Static Latest Firing Time.
	 * @return Static Latest Firing Time 
	 */
	public double getSLFT()
	{
		return slft;
	}
	
	/**
	 * Retorna a Curva de Densidade de Probabilidade.
	 * @return Curva de Densidade de Probabilidade
	 */
	public int getCurvaDensidade()
	{
		return curvaDensidade;
	}
	
	/**
	 * Seta o nome da Transi��o.
	 * @param nome Nome da Transi��o
	 */
	public void setNome(String nome)
	{
		if(nome != null)
			this.nome = nome.trim();
		else
			nome = null;
	}
	
	/**
	 * Seta a posi��o da Transi��o na matriz.
	 * @param posicao posi��o da Transi��o na matriz
	 */
	public void setPosicao(int posicao)
	{
		this.posicao = posicao;
		this.identificador = "T" + posicao;
	}
	
	/**
	 * Seta o valor de Static Earliest Firing Time.
	 * @param seft Static Earliest Firing Time
	 */
	public void setSEFT(double seft)
	{
		this.seft = seft;
	}
	
	/**
	 * Seta o valor de Static Latest Firing Time.
	 * @param slft Static Latest Firing Time
	 */
	public void setSLFT(double slft)
	{
		this.slft = slft;
	}
	
	/**
	 * Seta o valor da Curva de Densidade de Probabilidade. O valor deve ser
	 * 0 - Uniforme, 1 - Normal ou 2 - Exponencial.
	 * @param curvaDensidade
	 */
	public void setCurvaDensidade(int curvaDensidade)
	{
		if(curvaDensidade >= 0 && curvaDensidade <= 2)
			this.curvaDensidade = curvaDensidade;
		else
			this.curvaDensidade = UNIFORME;
	}
	
	/**
	 * Retorna String com informa��es da Transi��o.
	 * @return informa��es da Transi��o
	 */
	public String toString()
	{
		return "    Transicao:["+getNome()         +"]\n"
		      +"Identificador:["+getIdentificador()+"]\n"
		      +"      Posicao:["+getPosicao()      +"]";
	}
	
	/**
	 * Verifica se o objeto recebido como par�metro � igual 
	 * ao objeto chamador do m�todo.
	 * 
	 * @return True caso os objetos sejam iguais, false caso sejam 
	 * diferentes.
	 */
	public boolean isEqual(Object obj)
	{	
		if(obj == this)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(obj instanceof TransitionBase)
		{
			TransitionBase trans = (TransitionBase)obj;
			if((trans.curvaDensidade == this.curvaDensidade)
			&& (trans.identificador.equals(this.identificador))
			&& (trans.nome.equals(this.nome))
			&& (trans.posicao == this.posicao)
			&& (trans.seft == this.seft)
			&& (trans.slft == this.slft))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Verifica se o objeto recebido como par�metro � igual 
	 * ao objeto chamador do m�todo.
	 * 
	 * @return True caso os objetos sejam iguais, false caso sejam 
	 * diferentes.
	 */
	public boolean equals(Object obj)
	{
		return isEqual(obj);
	}

}
