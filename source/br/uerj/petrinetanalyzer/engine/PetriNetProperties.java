package br.uerj.petrinetanalyzer.engine;

import java.util.ArrayList;

import br.uerj.petrinetanalyzer.common.interfaces.IntfEngineConstants;
import br.uerj.petrinetanalyzer.common.interfaces.IntfPetriNetProperties;

/**
 * Possui m�todos que retornam um conjunto de propriedades
 * verificadas na Rede de Petri
 * 
 * @author Felipe Lino
 * <BR>Data: 25/02/2007
 * <BR>Atualizado: 25/02/2007
 */
public class PetriNetProperties implements 
IntfPetriNetProperties, IntfEngineConstants
{
	/**
	 * Nome da Rede de Petri
	 */
	private String  nome = null;
	/**
	 * Informa se a rede � ou n�o viva. 
	 * Uma rede viva � aquela que em todos
	 * os estados possui alguma transi��o habilitada para disparo.
	 */
	private boolean ehViva = true;
	/**
	 * Informa se a rede � ou n�o limitada.
	 * Uma rede � limitada quando � poss�vel encontrar um valor m�ximo de fichas nos Lugares,
	 * ou seja, n�o existe um acumulo indefinido de fichas em nenhum lugar da rede.
	 */
	private boolean ehLimitada = true;
	/**
	 * Informa se a rede � ou n�o conservativa. Uma Rede � conservativa quando o n�mero total de
	 * fichas na rede � constante em todos os estados. Uma rede conservativa � tamb�m limitada.
	 */
	private boolean ehConservativa = true;
	/**
	 * Array com as seq��ncias de disparos que levam a deadlock.
	 */
	private ArrayList listDeadLock = null;
	/**
	 * Array com os Estados finais ap�s a seq��ncia de disparos, estados de deadlock.
	 */
	private ArrayList listDeadLockStates = null;
	/**
	 * Limite da rede, caso ela seja limitada.
	 */
	private int limite = 0;
	/**
	 * Total de fichas na rede 
	 */
	private int totalFichas = 0;
	
	/**
	 * Construtor que seta inicialmente 
	 * os valores com as caracteristicas da Rede de Petri.
	 *
	 */
	public PetriNetProperties()
	{
		ehConservativa = true;
		ehLimitada = true;
		ehViva = true;
		limite = 0;
		totalFichas = 0;

		listDeadLock = new ArrayList();
		listDeadLockStates = new ArrayList();
	}
	
	/**
	 * Construtor que seta inicialmente 
	 * os valores com as caracteristicas da Rede de Petri.
	 * @param nome Nome da Rede de Petri.
	 */
	public PetriNetProperties(String nome)
	{
		this.nome = nome;
		ehConservativa = true;
		ehLimitada = true;
		ehViva = true;
	
		listDeadLock = new ArrayList();
		listDeadLockStates = new ArrayList();
		
		limite = 0;
		totalFichas = 0;
	}
	
	
	/**
	 * Seta o nome da Rede de Petri.
	 * @param nome Nome da Rede de Petri.
	 */
	public void setNomeRedePetri(String nome)
	{
		this.nome = nome;
	}
	
	/**
	 * Informa se a Rede de Petri � ou n�o viva.
	 * @param ehViva
	 */
	public void setEhViva(boolean ehViva)
	{
		this.ehViva = ehViva;
	}
	
	/**
	 * Informa o Limite da Rede de Petri.
	 * Se Limite for infinito, ent�o rede � Ilimitada.
	 * @param limite Limite de Tokens na Rede
	 */
	public void setLimite(int limite)
	{
		if(limite == TOKEN_INFINITO)
		{
			this.limite = TOKEN_INFINITO;
			ehLimitada = false;
		}
		else
		{
			this.limite = limite;
			ehLimitada = true;
		}
	}
	
	/**
	 * Informa se a Rede � ou n�o conservativa, ou seja
	 * se o n�mero de tokens � constante em cada
	 * estado.
	 * @param ehConservativa
	 */
	public void setEhConservativa(boolean ehConservativa)
	{
		this.ehConservativa = ehConservativa;
	}
	
	/**
	 * Retorna o nome da Rede de Petri
	 * @return Nomde da Rede de Petri
	 */
	public String getNomeRedePetri()
	{
		if(nome != null)
			return nome;
		else
			return "";
	}
	
	/**
	 * Informa se a Rede � ou n�o limitada.
	 * @return Retorna true caso a Rede seja Limitada.
	 */
	public boolean ehLimitada()
	{
		return ehLimitada;
	}
	
	/**
	 * Informa se a Rede � ou n�o conservativa.
	 * @return Retorna true caso a Rede seja conservativa.
	 */
	public boolean ehConservativa()
	{
		return ehConservativa;
	}
	
	/**
	 * Informa se a Rede � ou n�o viva.
	 * @return Retorna true caso a Rede seja viva.
	 */
	public boolean ehViva()
	{
		return ehViva;
	}
	
	/**
	 * Retorna o Limite da Rede. Limite � o m�ximo de fichas
	 * que podem existir na Rede.
	 * @return Retorna um inteiro que � o Limite. Retorna -1 caso
	 * o limite seja Infinito.
	 */
	public int getLimite()
	{
		return limite;
	}
	
	/**
	 * Retorna o total de fichas na rede quando ela � conservativa. 
	 * @return Total de fichas na rede quando ela � conservativa
	 */
	public int getTotalFichas()
	{
		return totalFichas;
	}
	
	/**
	 * Seta o total de fichas na rede, quando ela � conservativa.
	 * @param totalFichas Total de fichas na rede.
	 */
	public void setTotalFichas(int totalFichas)
	{
		this.totalFichas = totalFichas;
	}
	
	/**
	 * Adiciona uma seq��ncia de disparos que levam a Rede a um 
	 * estado de DeadLock, e qual o identificador do Estado final.
	 * @param arrfireSequence Array com a Seq��ncia de disparo
	 * @param idEstado Identificador do Estado final ap�s os disparos
	 */
	public void addDeadLock(int [] arrfireSequence, int idEstado)
	{
		listDeadLock.add(arrfireSequence.clone());
		listDeadLockStates.add(new Integer(idEstado));
	}
	
	/**
	 * Retorna a seq��ncia de disparos que levam a deadlock especificados pela
	 * posi��o.
	 * @param pos Posi��o
	 * @return Seq��ncia de disparos que levam a deadlock especificados pela posi��o
	 */
	public int [] getFireSequenceDeadLock(int pos)
	{
		return (int [])listDeadLock.get(pos);
		
	}
	
	/**
	 * Retorna o Estado final de uma seq��ncia de disparo que levou a rede a deadlock.
	 * @param pos Posi��o
	 * @return Estado final de uma seq��ncia de disparo que levou a rede a deadlock
	 */
	public int getDeadLockState(int pos)
	{
		Integer id = (Integer) listDeadLockStates.get(pos);
		return id.intValue();

	}
	
	/**
	 * Retorna o total de estados de deadlock
	 * @return Total de estados de deadlock
	 */
	public int getTotalDeadLockStates()
	{
		if(listDeadLockStates != null)
			return listDeadLockStates.size();
		else
			return 0;
	}
	
}
