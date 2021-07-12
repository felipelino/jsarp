package br.uerj.petrinetanalyzer.engine;

import br.uerj.petrinetanalyzer.common.ArcBase;
import br.uerj.petrinetanalyzer.common.ImplPetriNetBase;
import br.uerj.petrinetanalyzer.common.PlaceBase;
import br.uerj.petrinetanalyzer.common.TransitionBase;
import br.uerj.petrinetanalyzer.common.interfaces.IntfSimulator;
import br.uerj.petrinetanalyzer.common.interfaces.IntfEngineConstants;

/**
 * Implementa a engine do funcionamento da simula��o.
 * 
 * @author Felipe Lino
 * <BR>Data: 19/01/2007
 * <BR>Atualiza��o: 24/03/2007
 */
public class ImplSimulator implements IntfSimulator, IntfEngineConstants
{
	/**
	 * Rede de Petri a ser simulada.
	 */
	private ImplPetriNetBase 	  pn = null;
	/**
	 * Array de marca��es de Lugar.
	 */
	private int [] 		  arrMarking = null;
	/**
	 * Array informando se a transi��o est� ou n�o habilitada para disparo.
	 */
	private boolean [] 	arrTransFire = null;
	
	/**
	 * Construtor que recebe como par�metro a Rede de Petri a ser simulada
	 * @param pn Rede de Petri.
	 */
	public ImplSimulator(ImplPetriNetBase pn)
	{
		this.pn 	 = pn;
		arrMarking 	 = new int[pn.getNumLugar()];
		arrTransFire = new boolean[pn.getNumTransicao()];
		
		for(int i=0; i < arrMarking.length; i++)
		{
			arrMarking[i] = pn.getLugar(i).getFichas();
		}
	}
	
	/**
	 * Uma transi��o est� habilitada se, todos os Lugares de Entrada da Transi��o
	 * possuirem fichas maior ou igual ao peso do arco, que liga at� a transi��o.
	 * <pre>
	 * Algoritmo:
	 * Para toda Transicao Fa�a
	 * 		Transi��o.habilitada = true
	 * Fim Para;
	 * Para todo Arco de entrada
	 * 		Se Arco.Lugar.fichas >= arco.peso E Arco.transicao.habilitada = true Ent�o
	 * 			Arco.transicao est� habilitada.
	 * 		Sen�o
	 * 			Arco.transicao est� desabilitada.
	 * 		Fim Se;
	 * Fim Para;
	 * </pre>
	 * @return Array boleano informando se a transi��o est� ou n�o habilitada.
	 */	
	public boolean[] getTransicoesDisponiveis()
	{
		
		for(int i=0; i < arrTransFire.length; i++)
			arrTransFire[i] = true;
		
		for(int i=0; i < pn.getNumArco(); i++)
		{
			ArcBase arc = pn.getArc(i);
			if(arc.verifyEntrada() == true)
			{
				PlaceBase 		place = arc.getLugar();
				TransitionBase  trans = arc.getTransicao();
				int transPos = trans.getPosicao(); 
				int placePos = place.getPosicao();
				if((arrMarking[placePos] >= arc.getPeso()) 
				&& (arrTransFire[transPos] == true)) 
				{
					arrTransFire[transPos] = true;
				}
				else
				{
					arrTransFire[transPos] = false;
				}
			}
		}
		
		return arrTransFire;
	}
	
	/**
	 * Retorna array com as marca��es dos lugares, ap�s o disparo de uma transi��o
	 * @return Array com as marca��es dos Lugares.
	 */
	public int[] getMarcacoes()
	{
		return arrMarking;
	}
	
	
	/**
	 * Dispara transi��o indicada pela posi��o.
	 * <pre>
	 * Algoritmo:
	 * Para todo Lugar de Entrada da Transi��o Fa�a
	 * 		Lugar.fichas = Lugar.fichas - peso;
	 * Fim Para; 
	 * 
     * Para todo Lugar de Sa�da da Transi��o Fa�a
	 * 		Lugar.fichas = Lugar.fichas + peso; 
	 * Fim Para;
	 * </pre>
	 * @param posicao
	 */
	public void disparaTransicao(int posicao)
	{
		TransitionBase trans = pn.getTransicao(posicao);
		for(int i=0; i < pn.getNumArco(); i++)
		{
			ArcBase arc = pn.getArc(i);
			/* Se o Arco est� ligado a Transi��o */
			if(arc.getTransicao().isEqual(trans))
			{
				int pos = arc.getLugar().getPosicao();
				/* Se o Arco � de Entrada (Lugar --> Transi��o) */
				if(arc.verifyEntrada() == true)
				{
					if(arrMarking[pos] != TOKEN_INFINITO)
						arrMarking[pos] = arrMarking[pos] - arc.getPeso();
				}
				else /* Se o Arco � de Sa�da (Transi��o --> Lugar) */ 
				{	 
					if(arrMarking[pos] != TOKEN_INFINITO)
						arrMarking[pos] = arrMarking[pos] + arc.getPeso();
				}
			}
			
		}
	}
		
	/**
	 * Verifica se existe alguma transi��o dispon�vel para ser disparada.
	 * @return Retorna true caso, exista alguma transi��o dispon�vel. Retorna false caso contr�rio.
	 */
	public boolean temTransicaoDisponivel()
	{
		if(arrTransFire == null)
			return false;
		
		boolean canFireTrans = false; 
		for(int i=0; i < arrTransFire.length; i++)
		{
			if(arrTransFire[i] == true)
			{
				canFireTrans = true;
				break;
			}
		}
		
		return canFireTrans;
	}
	
	/**
	 * Seta o estado da simula��o para algum espec�fico. 
	 * @param arrMarking	Array de Marca��es dos Lugares no estado desejado.	
	 * @param arrTransFire	Array informando se a Transi��o est� ou n�o dispon�vel no estado desejado.
	 */
	public void setState(int [] arrMarking, boolean [] arrTransFire)
	{
		this.arrMarking 	= arrMarking.clone();
		this.arrTransFire	= arrTransFire.clone();
	}
}
