package br.uerj.petrinetanalyzer.gui.objects;


/**
 * Define um estado da Rede de Petri. Um estado � caracterizado pela marca��o dos lugares,
 * transi��es dispon�veis, e seq��ncia de disparos para chegar ao estado em determinado
 * momento.
 * @author Felipe Lino
 * <BR>Data: 11/02/2007
 * <BR>Atualizado: 01/03/2007
 */
public class TreeNodeState
{
	/**
	 * Vari�vel requisitada na heran�a de DefaultMutableTreeNode
	 */
	static final long serialVersionUID = 1;
	
	/** 
	 * Lista de Lugares
	 */
	private int marcacao[] = null;
	
	/**
	 * Lista booleana informando quais transi��es podem ser
	 * diparadas.
	 */
	private boolean	listTransFire[] = null;
	
	/**
	 * Lista com a sequ�ncia de disparos
	 */
	private int  fireSequence[] = null;
	
	/**
	 * Construtor.
	 * @param fireSequence  Seq��ncia de disparos
	 * @param marcacao      Marca��o dos lugares
	 * @param listTransFire Lista informando se a transi��o est� ou n�o habilitada para disparo
	 */
	public TreeNodeState(int fireSequence[], int marcacao[],boolean listTransFire[])
	{
		this.fireSequence = fireSequence;
		this.marcacao	  = marcacao;
		this.listTransFire= listTransFire;
	}	
	
	/**
	 * Pega lista de marca��es dos Lugares.
	 * @return Lista de marca��es dos lugares.
	 */
	public int[] getListMarcacao()
	{
		return marcacao;
	}
	
	/**
	 * Retorna seq��ncia de disparos.
	 * @return Seq��ncia de disparos.
	 */
	public int[] getFireSequence()
	{
		return fireSequence;
	}
	
	/**
	 * Pega Lista de Transi��es habilitadas para disparo
	 * @return Lista de Transi��es habilitadas
	 */
	public boolean[] getListTransFire()
	{
		return listTransFire;
	}
	
	/**
	 * Sobrescreve o m�todo toString. � o que ir�
	 * aparecer no r�tulo da �rvore de disparos.
	 */
	public String toString()
	{
		String ret = "";
		if(fireSequence == null || fireSequence.length < 1)
		{
			ret = "Initial State";
		}
		else
		{
			/* Retorna apenas a �ltima transi��o da seq��ncia */
			ret = "T" + fireSequence[fireSequence.length-1];
		}
		
		/* Monta assinatura */
		if((marcacao !=null)&&(marcacao.length>1))
		{
			String strMarking ="[";
			for(int i=0; i < marcacao.length; i++ )
			{
				strMarking += marcacao[i]+",";
			}	
			strMarking  = strMarking.substring(0, strMarking.lastIndexOf(','));
			strMarking += "]";
			
			ret += " " +strMarking;
		}
		
		
		return ret.trim();
	}
	
	/**
	 * Sobrescreve o m�todo equals. Verifica se o objeto recebido
	 * como par�metro � igual ao objeto que invoca o m�todo.
	 */
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		
		if(obj == this)
			return true;
		
		if(obj instanceof TreeNodeState)
		{
			TreeNodeState state = (TreeNodeState) obj;
			int [] objArrMarking = state.getListMarcacao();
			boolean [] objArrTransFire = state.getListTransFire();
			int [] objArrFireSequence = state.getFireSequence();
			
			if(objArrFireSequence.length != this.fireSequence.length)
				return false;
			
			if(objArrTransFire.length != this.listTransFire.length)
				return false;
			
			for(int i=0; i<objArrMarking.length; i++)
			{
				if(objArrMarking[i] != this.marcacao[i])
					return false;
			}
			
			for(int i=0; i<objArrTransFire.length; i++)
			{
				if(objArrTransFire[i] != this.listTransFire[i])
					return false;
			}
			
			for(int i=0; i<objArrFireSequence.length; i++)
			{
				if(objArrFireSequence[i] != this.fireSequence[i])
					return false;
			}
			
			return true; 
		}
		else
			return false;
	}
}

