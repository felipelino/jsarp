package br.uerj.petrinetanalyzer.gui.listener;

import br.uerj.petrinetanalyzer.common.ImplPetriNetBase;
import br.uerj.petrinetanalyzer.common.TransitionBase;
import br.uerj.petrinetanalyzer.engine.ImplSimulator;
import br.uerj.petrinetanalyzer.gui.SimulationWindow;
import br.uerj.petrinetanalyzer.gui.objects.PetriNetGraph;
import br.uerj.petrinetanalyzer.gui.objects.TreeNodeState;

/**
 * Classe que implementa a area de desenho da rede de petri.
 * 
 * @author Felipe Lino
 * <BR>Data:10/02/2007
 * <BR>Atualizado: 24/02/2007
 */
public class SimulationAction extends AbstractActionListener
{
	private ImplSimulator simEngine = null;
	
	private PetriNetGraph pn = null;
	
	private TreeNodeState currentState = null;
	private SimulationWindow simWindow = null;
	
	/**
	 * Construtor.
	 * @param pn Rede de Petri a ser simulada
	 */
	public SimulationAction(PetriNetGraph pn)
	{
		this.pn = pn;
		
		simEngine = new ImplSimulator((ImplPetriNetBase)pn);
		
		int 	   arrMarking[] = simEngine.getMarcacoes().clone();
		boolean  arrFireTrans[] = simEngine.getTransicoesDisponiveis().clone();
		
		TreeNodeState rootState = createState(new int[0], arrMarking, arrFireTrans);
		currentState = rootState;
			
		simWindow = new SimulationWindow(pn, rootState);	
	}
	
	/**
	 * Cria um novo Estado
	 * @param arrSequence	Seqüência de Disparos	
	 * @param arrMarcacao	Marcações nos Lugares
	 * @param arrFireTrans	Lista informando se transição está ou não habilitada para disparo.
	 * @return Novo Estado criado
	 */
	public TreeNodeState createState(int arrSequence[], int[] arrMarcacao, boolean[] arrFireTrans)
	{
		TreeNodeState state = new TreeNodeState(arrSequence, arrMarcacao, arrFireTrans);
		return state;
	}
	
	/**
	 * Adiciona um estado novo estado de marcações e transições disparáveis. 
	 * @param arrSequence  Array com a seqüência de transições disparadas.
	 * @param arrMarcacao  Array de marcações de Lugar
	 * @param arrFireTrans Array informando quais transições podem e quais não podem ser disparadas.
	 */
	public void addEstado(int arrSequence[], int[] arrMarcacao, boolean[] arrFireTrans)
	{
		TreeNodeState state = createState(arrSequence, arrMarcacao, arrFireTrans);
		currentState = state;
		simWindow.addTreeNodeState(state);
		simWindow.repaint();
	}
		
	/**
	 * Faz com que a rede seja atualizada para ir para o Estado selecionado.
	 *
	 */
	public void goToEstado()
	{
		currentState = simWindow.getSelectedState();	
		
		simWindow.changeAtualForSelectedState();
		
		simEngine.setState(currentState.getListMarcacao(), currentState.getListTransFire());
	}
	
	/**
	 * Retorna a quantidade de fichas em um determinado lugar, após
	 * o último disparo.
	 * @param placePosition Posição do Lugar na Rede
	 * @return quantidade de fichas no Lugar
	 */
	public int getFichas(int placePosition)
	{
		return currentState.getListMarcacao()[placePosition];
	}
	
	/**
	 * Verifica se determinada transição pode ser disparada, após 
	 * o último disparo.
	 * @param transPosition Posição da Transição na Rede
	 * @return true caso a transição possa ser disparada.
	 */
	public boolean canFireTransition(int transPosition)
	{			
		return currentState.getListTransFire()[transPosition];
	}
	
	/**
	 * Retorna a instância de PetriNetGraph usada para simulação.
	 * @return Rede de Petri da simulação
	 */
	public PetriNetGraph getPetriNetGraph()
	{
		return pn;
	}
	
	/**
	 * tenta disparar a transição verificando primeiramente, se
	 * ela pode ser disparada.
	 * @param trans Transição a ser disparada
	 * @return Retorna true caso consiga disparar a transição,
	 * retorna false caso contrário.
	 */
	public boolean tryFireTransition(TransitionBase trans)
	{
		if(canFireTransition(trans.getPosicao()))
		{	
			simEngine.disparaTransicao(trans.getPosicao());
			int 	[]	 arrMarking = simEngine.getMarcacoes().clone();
			boolean [] arrFireTrans = simEngine.getTransicoesDisponiveis().clone();
			
						
			int arrOldSeq[]	= currentState.getFireSequence();
			int	arrSequence[] = new int[arrOldSeq.length + 1];
			
			for(int i = 0; i < arrOldSeq.length ; i++)
			{
				arrSequence[i] = arrOldSeq[i];
			}
			arrSequence[arrSequence.length - 1] = trans.getPosicao();
			
			addEstado(arrSequence, arrMarking, arrFireTrans);
			
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Atualiza os rótulos da janela de simulação.
	 *
	 */
	public void refreshText()
	{
		if(simWindow != null)
			simWindow.refreshText();
	}
	
	/**
	 * Exibe a janela de simulação.
	 *
	 */
	public void showSimulationWindow()
	{
		SimulationWindow.setDefaultLookAndFeelDecorated(true);
		simWindow.setDefaultCloseOperation(SimulationWindow.DO_NOTHING_ON_CLOSE);
		simWindow.setFocusable(true);
		simWindow.setVisible(true);
	}
	
	/**
	 * Fecha a janela de simulação.
	 */
	public void closeSimulationWindow()
	{
		simWindow.dispose();
	}
}
