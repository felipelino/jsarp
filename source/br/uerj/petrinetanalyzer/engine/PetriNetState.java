package br.uerj.petrinetanalyzer.engine;

import br.uerj.petrinetanalyzer.common.interfaces.IntfEngineConstants;

/**
 * Classe que representa o Estado de uma Rede 
 * de Petri, para análise.
 * 
 * @author Felipe Lino
 * <BR>Data: 25/02/2007
 * <BR>Atualização: 24/03/2007
 */
public class PetriNetState implements IntfEngineConstants
{
	/**
	 * Array de marcações dos Lugares na rede.
	 */
	private int     [] arrMarking 		= null;
	/**
	 * Array informando se a transição está ou não habilitada para disparo.
	 */
	private boolean [] arrTransFire 	= null;
	/**
	 * Array com a seqüência de disparos.
	 */
	private int 	[] arrFireSequence  = null;
	/**
	 * Informa o tipo de estado: STATE_TERMINAL, STATE_FRONTEIRA, STATE_DUPLICADO ou STATE_INTERIOR 
	 */
	private int stateType = STATE_FRONTEIRA;
	/**
	 * Soma das marcações no Estado.
	 */
	private int totalMarking = 0;
	/**
	 * Informa se tem transição habilitada para disparo.
	 */
	private boolean temTransicaoHabilitada = false;
	/**
	 * Identificador do Estado, recebe o valor de um contador estático que é incrementado a cada
	 * estado criado.
	 */
	private int identificador = 0;
	/**
	 * Máximo de fichas encontrado em algum lugar da rede.
	 */
	private int limite = 0;
	/**
	 * Informa a qual estado é igual se ele for do tipo STATE_DUPLICADO
	 */
	private int igualAoEstado = -1;
	
	/**
	 * Contador estático que é incrementado a cada novo estado criado.
	 */
	private static int totalState = 0;
	
	/**
	 * Construtor que recebe o array de marcações como 
	 * parâmetro.
	 */
	public PetriNetState(int [] arrMarking, boolean [] arrTransFire, boolean temTransicaoHabilitada,int [] arrFireSequence)
	{
		this.stateType = STATE_FRONTEIRA;
		
		this.temTransicaoHabilitada = temTransicaoHabilitada;
		setArrMarking(arrMarking);
		setArrTransFire(arrTransFire);
		this.arrFireSequence = arrFireSequence.clone();
		igualAoEstado = -1;
		
		identificador = totalState;
		totalState++;
	}
	
	/**
	 * Seta o Array boleano informando se a transição está ou não disponível.
	 * @param arrTransFire Array boleano informando se a transição está ou não disponível
	 */
	public void setArrTransFire(boolean [] arrTransFire)
	{
		if(arrTransFire !=null)
			this.arrTransFire = arrTransFire.clone();
	}
	
	/**
	 * Retorna Array boleano informando se a transição está ou não disponível.
	 * @return Array boleano informando se a transição está ou não disponível
	 */
	public boolean [] getArrTransFire()
	{
		return arrTransFire;
	}
	
	/**
	 * Seta o array de marcações
	 * @param arrMarking
	 */
	public void setArrMarking(int [] arrMarking)
	{
		if(arrMarking == null)
			return;
		
		this.arrMarking = arrMarking.clone();
		totalMarking = 0;
		limite = 0;
		for(int i=0; i < this.arrMarking.length; i++)
		{
			totalMarking += this.arrMarking[i];
			if(this.arrMarking[i] > limite)
			{
				limite = this.arrMarking[i];
			}
		}
	}
	
	/**
	 * Seta a marcação de um Lugar na Rede.
	 * @param pos Posição do Lugar
	 * @param value Numero de fichas a ser colocada no Lugar
	 */
	public void setMarking(int pos, int value)
	{
		int remove = getMarking(pos);
		if(remove != ERROR_CODE)
		{
			totalMarking = totalMarking - remove;
			if(value == TOKEN_INFINITO)
			{
				totalMarking = TOKEN_INFINITO;
				arrMarking[pos] = TOKEN_INFINITO; 
				limite = TOKEN_INFINITO;
			}
			else
			{
				totalMarking = totalMarking + value;
				arrMarking[pos] = value;
				if(limite < value)
					limite = value;
			}
		}
	}
	
	/**
	 * Retorna o array de marcações de Lugar.
	 * @return Array com as marcações do Lugar
	 */
	public int [] getArrMarking()
	{
		return arrMarking;
	}
	
	/**
	 * Retorna marcação do Lugar na posição especificada.
	 * @param pos Posição do Lugar, no array de Lugar.
	 * @return Marcação do Lugar na posição especificada.
	 */
	public int getMarking(int pos)
	{
		if(pos >=0 && pos < arrMarking.length)
			return arrMarking[pos];
		
		return ERROR_CODE;
	}
	
	/**
	 * Retorna a soma das marcações dos Lugares.
	 * @return Soma das marcações dos Lugares.
	 */
	public int getTotalMarking()
	{
		return totalMarking;
	}
	
	/**
	 * Retorna o tipo de Estado 
	 * 	<UL>
	 * 		<LI>STATE_FRONTEIRA
	 * 		<LI>STATE_DUPLICADO
	 * 		<LI>STATE_INTERIOR
	 * 		<LI>STATE_TERMINAL
	 * 	</UL>
	 * @return tipo de Estado
	 */
	public int getStateType()
	{
		return stateType;
	}
	
	/**
	 * Seta o estado. Que pode ser:
	 * 	<UL>
	 * 		<LI>STATE_FRONTEIRA
	 * 		<LI>STATE_DUPLICADO
	 * 		<LI>STATE_INTERIOR
	 * 		<LI>STATE_TERMINAL
	 * 	</UL>
	 * @param newStateType Novo tipo de Estado
	 */
	public void setStateType(int newStateType)
	{
		stateType = newStateType;
	}
	
	/**
	* Seta o total de estados.
	* @param total Total de estados
	*/
	public static void setTotalState(int total)
	{
		totalState = total;
	}
	
	/**
	* Retorna o total de Estados.
	* @return total de estados
	*/
	public static int getTotalState()
	{
		return totalState;
	}
	
	/**
	 * Retorna o Identificador.
	 * @return Identificador
	 */
	public int getIdentificador()
	{
		return identificador;
	}
	
	/**
	 * Seta a informação se tem ou não transição habilitada para o estado.
	 * @param temTransicaoHabilitada Informa se tem ou não transição habilitada para disparao no Estado.
	 */
	public void setTransicaoHabilitada(boolean temTransicaoHabilitada)
	{
		this.temTransicaoHabilitada = temTransicaoHabilitada;
	}
	
	/**
	 * Retorna se tem ou não transição habilitada para disparo no Estado.
	 * @return true caso tenha transição habilitada para disparo no Estado, e false
	 * caso contrário.
	 */
	public boolean temTransicaoHabilitada()
	{
		return temTransicaoHabilitada;
	}
	
	/**
	 * Caso o Estado seja STATE_DUPLICADO, indica a qual estado é igual.
	 * @param idEstado identificador do estado duplicado.
	 */
	public void setIgualAoEstado(int idEstado)
	{
		igualAoEstado = idEstado;
	}
	
	/**
	 * Retorna qual o Estado do qual este é duplicata. 
	 * @return Identificador do Estado Duplicado.
	 */
	public int getIgualAoEstado()
	{
		return igualAoEstado;
	}
	
	/**
	 * Retorna o maximo de fichas encontrados em algum Lugar da Rede.
	 * @return Máximo de fichas encontrados em algum Lugar da Rede.
	 */
	public int getLimite()
	{
		return limite;
	}
	
	/**
	 * Retorna o Array com a Seqüência de disparos.
	 * @return Array com a Seqüência de disparos.
	 */
	public int [] getArrFireSequence()
	{
		return arrFireSequence;
	}
	
	/**
	 * Zera a variável que conta o total de Estados.
	 *
	 */
	public static void zeraTotalState()
	{
		totalState = 0;
	}
	
	/**
	 * Verifica se o objeto recebido como parâmetro
	 * possui estado igual ao chamador.
	 * @param obj Objeto da comparação
	 */
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		
		if(obj instanceof PetriNetState)
		{
			PetriNetState objState = (PetriNetState) obj;
			int [] arrMarkingObj = objState.getArrMarking();
			
			if(this.arrMarking.length != arrMarkingObj.length)
				return false;
			
			for(int i = 0; i < this.arrMarking.length; i++)
			{
				if(arrMarking[i] != arrMarkingObj[i])
					return false;
			}
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Sobrescreve o método toString()
	 * @return String que identifica o Estado
	 */
	public String toString()
	{
		String str = "State "+getIdentificador();
		
		/* Monta assinatura */
		if((arrMarking !=null)&&(arrMarking.length>1))
		{
			String strMarking ="[";
			for(int i=0; i < arrMarking.length; i++ )
			{
				if(arrMarking[i] >= TOKEN_INFINITO)
					strMarking += "w,";
				else
					strMarking +=arrMarking[i]+",";
			}
			strMarking  = strMarking.substring(0, strMarking.lastIndexOf(','));
			strMarking += "]";
		
			str += " " + strMarking;
		}
		
		return str;
	}
}
