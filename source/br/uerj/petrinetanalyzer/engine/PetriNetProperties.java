package br.uerj.petrinetanalyzer.engine;

import java.util.ArrayList;

import br.uerj.petrinetanalyzer.common.interfaces.IntfEngineConstants;
import br.uerj.petrinetanalyzer.common.interfaces.IntfPetriNetProperties;

/**
 * Possui métodos que retornam um conjunto de propriedades
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
	 * Informa se a rede é ou não viva. 
	 * Uma rede viva é aquela que em todos
	 * os estados possui alguma transição habilitada para disparo.
	 */
	private boolean ehViva = true;
	/**
	 * Informa se a rede é ou não limitada.
	 * Uma rede é limitada quando é possível encontrar um valor máximo de fichas nos Lugares,
	 * ou seja, não existe um acumulo indefinido de fichas em nenhum lugar da rede.
	 */
	private boolean ehLimitada = true;
	/**
	 * Informa se a rede é ou não conservativa. Uma Rede é conservativa quando o número total de
	 * fichas na rede é constante em todos os estados. Uma rede conservativa é também limitada.
	 */
	private boolean ehConservativa = true;
	/**
	 * Array com as seqüências de disparos que levam a deadlock.
	 */
	private ArrayList listDeadLock = null;
	/**
	 * Array com os Estados finais após a seqüência de disparos, estados de deadlock.
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
	 * Informa se a Rede de Petri é ou não viva.
	 * @param ehViva
	 */
	public void setEhViva(boolean ehViva)
	{
		this.ehViva = ehViva;
	}
	
	/**
	 * Informa o Limite da Rede de Petri.
	 * Se Limite for infinito, então rede é Ilimitada.
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
	 * Informa se a Rede é ou não conservativa, ou seja
	 * se o número de tokens é constante em cada
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
	 * Informa se a Rede é ou não limitada.
	 * @return Retorna true caso a Rede seja Limitada.
	 */
	public boolean ehLimitada()
	{
		return ehLimitada;
	}
	
	/**
	 * Informa se a Rede é ou não conservativa.
	 * @return Retorna true caso a Rede seja conservativa.
	 */
	public boolean ehConservativa()
	{
		return ehConservativa;
	}
	
	/**
	 * Informa se a Rede é ou não viva.
	 * @return Retorna true caso a Rede seja viva.
	 */
	public boolean ehViva()
	{
		return ehViva;
	}
	
	/**
	 * Retorna o Limite da Rede. Limite é o máximo de fichas
	 * que podem existir na Rede.
	 * @return Retorna um inteiro que é o Limite. Retorna -1 caso
	 * o limite seja Infinito.
	 */
	public int getLimite()
	{
		return limite;
	}
	
	/**
	 * Retorna o total de fichas na rede quando ela é conservativa. 
	 * @return Total de fichas na rede quando ela é conservativa
	 */
	public int getTotalFichas()
	{
		return totalFichas;
	}
	
	/**
	 * Seta o total de fichas na rede, quando ela é conservativa.
	 * @param totalFichas Total de fichas na rede.
	 */
	public void setTotalFichas(int totalFichas)
	{
		this.totalFichas = totalFichas;
	}
	
	/**
	 * Adiciona uma seqüência de disparos que levam a Rede a um 
	 * estado de DeadLock, e qual o identificador do Estado final.
	 * @param arrfireSequence Array com a Seqüência de disparo
	 * @param idEstado Identificador do Estado final após os disparos
	 */
	public void addDeadLock(int [] arrfireSequence, int idEstado)
	{
		listDeadLock.add(arrfireSequence.clone());
		listDeadLockStates.add(new Integer(idEstado));
	}
	
	/**
	 * Retorna a seqüência de disparos que levam a deadlock especificados pela
	 * posição.
	 * @param pos Posição
	 * @return Seqüência de disparos que levam a deadlock especificados pela posição
	 */
	public int [] getFireSequenceDeadLock(int pos)
	{
		return (int [])listDeadLock.get(pos);
		
	}
	
	/**
	 * Retorna o Estado final de uma seqüência de disparo que levou a rede a deadlock.
	 * @param pos Posição
	 * @return Estado final de uma seqüência de disparo que levou a rede a deadlock
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
