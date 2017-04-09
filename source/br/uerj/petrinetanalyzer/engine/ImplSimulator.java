package br.uerj.petrinetanalyzer.engine;

import br.uerj.petrinetanalyzer.common.ArcBase;
import br.uerj.petrinetanalyzer.common.ImplPetriNetBase;
import br.uerj.petrinetanalyzer.common.PlaceBase;
import br.uerj.petrinetanalyzer.common.TransitionBase;
import br.uerj.petrinetanalyzer.common.interfaces.IntfSimulator;
import br.uerj.petrinetanalyzer.common.interfaces.IntfEngineConstants;

/**
 * Implementa a engine do funcionamento da simulação.
 * 
 * @author Felipe Lino
 * <BR>Data: 19/01/2007
 * <BR>Atualização: 24/03/2007
 */
public class ImplSimulator implements IntfSimulator, IntfEngineConstants
{
	/**
	 * Rede de Petri a ser simulada.
	 */
	private ImplPetriNetBase 	  pn = null;
	/**
	 * Array de marcações de Lugar.
	 */
	private int [] 		  arrMarking = null;
	/**
	 * Array informando se a transição está ou não habilitada para disparo.
	 */
	private boolean [] 	arrTransFire = null;
	
	/**
	 * Construtor que recebe como parâmetro a Rede de Petri a ser simulada
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
	 * Uma transição está habilitada se, todos os Lugares de Entrada da Transição
	 * possuirem fichas maior ou igual ao peso do arco, que liga até a transição.
	 * <pre>
	 * Algoritmo:
	 * Para toda Transicao Faça
	 * 		Transição.habilitada = true
	 * Fim Para;
	 * Para todo Arco de entrada
	 * 		Se Arco.Lugar.fichas >= arco.peso E Arco.transicao.habilitada = true Então
	 * 			Arco.transicao está habilitada.
	 * 		Senão
	 * 			Arco.transicao está desabilitada.
	 * 		Fim Se;
	 * Fim Para;
	 * </pre>
	 * @return Array boleano informando se a transição está ou não habilitada.
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
	 * Retorna array com as marcações dos lugares, após o disparo de uma transição
	 * @return Array com as marcações dos Lugares.
	 */
	public int[] getMarcacoes()
	{
		return arrMarking;
	}
	
	
	/**
	 * Dispara transição indicada pela posição.
	 * <pre>
	 * Algoritmo:
	 * Para todo Lugar de Entrada da Transição Faça
	 * 		Lugar.fichas = Lugar.fichas - peso;
	 * Fim Para; 
	 * 
     * Para todo Lugar de Saída da Transição Faça
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
			/* Se o Arco está ligado a Transição */
			if(arc.getTransicao().isEqual(trans))
			{
				int pos = arc.getLugar().getPosicao();
				/* Se o Arco é de Entrada (Lugar --> Transição) */
				if(arc.verifyEntrada() == true)
				{
					if(arrMarking[pos] != TOKEN_INFINITO)
						arrMarking[pos] = arrMarking[pos] - arc.getPeso();
				}
				else /* Se o Arco é de Saída (Transição --> Lugar) */ 
				{	 
					if(arrMarking[pos] != TOKEN_INFINITO)
						arrMarking[pos] = arrMarking[pos] + arc.getPeso();
				}
			}
			
		}
	}
		
	/**
	 * Verifica se existe alguma transição disponível para ser disparada.
	 * @return Retorna true caso, exista alguma transição disponível. Retorna false caso contrário.
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
	 * Seta o estado da simulação para algum específico. 
	 * @param arrMarking	Array de Marcações dos Lugares no estado desejado.	
	 * @param arrTransFire	Array informando se a Transição está ou não disponível no estado desejado.
	 */
	public void setState(int [] arrMarking, boolean [] arrTransFire)
	{
		this.arrMarking 	= arrMarking.clone();
		this.arrTransFire	= arrTransFire.clone();
	}
}
