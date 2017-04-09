package br.uerj.petrinetanalyzer.engine;

import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import br.uerj.petrinetanalyzer.common.ImplPetriNetBase;
import br.uerj.petrinetanalyzer.common.interfaces.IntfEngineConstants;

/**
 * Classe que cuida da engine da Análise da Rede de Petri
 * 
 * @author Felipe Lino
 * <BR>Data: 25/02/2007
 * <BR>Atualizado: 12/10/2007
 * 
 */
public class PetriNetAnalyzer implements IntfEngineConstants
{
	/**
	 * Engine do Simulador
	 */
	private ImplSimulator simulator  = null;
	/**
	 * Propriedades da Rede
	 */
	private PetriNetProperties properties = null;
	/**
	 * Árvore de alcançabilidade
	 */
	private JTree tree = null;
	/**
	 * Nó raíz da árvore de alcançabilidade
	 */
	private DefaultMutableTreeNode root = null;
	/**
	 * Model usado para tratar a árvore de alcançabilidade, apenas prepara
	 * a árvore para ser usada na interface gráfica.
	 */
	private DefaultTreeModel treeModel = null;
	
	/**
	 * Construtor que recebe a Rede de Petri como Parâmetro.
	 * @param pn Rede de Petri a ser analizada
	 */
	public PetriNetAnalyzer(ImplPetriNetBase pn)
	{
		if(pn!=null)
		{
			pn.buildMatrizEntradaAndSaida();
			pn.buildMatrizIncidencia();
			
			simulator  = new ImplSimulator(pn);
			properties = new PetriNetProperties(pn.getNome());
			PetriNetState.zeraTotalState();
			PetriNetState rootState = new PetriNetState(pn.getArrMarking(),
													simulator.getTransicoesDisponiveis(), 
													simulator.temTransicaoDisponivel(),
													new int[0]);
			
			root      = new DefaultMutableTreeNode(rootState);
			treeModel = new DefaultTreeModel(root);
	   
			tree = new JTree(treeModel);
			
		}
		else
			tree = null;
	}
	
	/**
	 * Gera a arvore de alcançabilidade da Rede de Petri e
	 * verifica propriedades.
	 * <PRE>
	 * Algoritmo: 
	 *  root = Estado Inicial;
	 *  processNode(root);
	 *  verifyProperties(root);
	 *  geraArvore(root);
	 * </PRE>
	 */
	public void geraArvoreAlcancabilidade()
	{
		PetriNetState rootState = (PetriNetState) root.getUserObject();
		properties.setTotalFichas(rootState.getTotalMarking());
		
		processNode(root);
		verifyProperties((PetriNetState)root.getUserObject());
		geraArvore(root);	
	}
	
	/**
	 * Função recursiva, que vai varrendo a árvore que vai sendo criada, e processando
	 * seus nós.
	 * <PRE>
	 * Algoritmo: Seja N o nó recebido como parâmetro.
	 *  Para i de 1 até Número de filhos de N Faça
	 *     X = N.getFilho(i);
	 *     processNode(X);
	 *     geraArvore(X);
	 *     verifyProperties(X);
	 *  Fim Para
	 * </PRE>
	 
	 * @param node Nó pai, cujos filhos serão processados.
	 */
	public void geraArvore(DefaultMutableTreeNode node)
	{
		for(int i=0; i < node.getChildCount(); i++)
		{
			processNode((DefaultMutableTreeNode)node.getChildAt(i));
			geraArvore((DefaultMutableTreeNode)node.getChildAt(i));
			PetriNetState stateX = (PetriNetState) ((DefaultMutableTreeNode)node.getChildAt(i)).getUserObject();
			verifyProperties(stateX);
		}
	}
	
	/**
	 * Processa o Nó da árvore de alcançabilidade
	 * <PRE>
	 * Algoritmo:
	 * Seja o X o nó a ser processado.
	 * 1) Se existe um nó Y na árvore que não é fronteira e tem a mesma marcação que X.
	 *    então X é um nó DUPLICADO.
	 *
	 *   Para cada nó Y da arvore Faça
	 *      Se ((µ(Y) = µ(X)) E (Y != X) E (Y.tipo != FRONTEIRA)) Então 
	 *         X.tipo = DUPLICADO; 
	 *         retorna;
	 *      Fim Se
	 *   Fim Para.
	 * 
	 * 2) Se não existem transições sensibilizadas associadas à marcação µ(x),
	 * ou seja {d (µ(x),tj) é indefinida PARA todo tj pertencente à T};
	 * então X é um nó TERMINAL.
	 *  
	 *   Se (X.temTransicaoDisponivel == false) Então
	 *      X.tipo = TERMINAL;
	 *      retorna;
	 *   Fim Se
	 *    
	 * 3) Para todas as transições tj Pertencente à T, que são habilitadas em µ[x],
	 * cria-se um novo nó Z, na árvore de alcançabilidade, ligados ao primeiro,
	 * através de um arco ao qual se associa a transição disparada.
	 * 
	 * Um arco, designado tj é dirigido do nó X para o nó Z. O nó X é redefinido como nó INTERIOR
	 * e Z, torna-se um nó FRONTEIRA.
	 *
	 *  Para cada transição habilitada  T de X Faça
	 *     Z = createNewState(X);
	 *     Z.tipo = FRONTEIRA;
	 *     AdicionaNóNaArvore(Z);
	 *  Fim Para
	 *  X.tipo = INTERIOR;
	 *
	 * </PRE>
	 */
	public void processNode(DefaultMutableTreeNode nodeX)
	{
		PetriNetState stateX = (PetriNetState) nodeX.getUserObject();
		
		
		
		/* 1) Se existe um nó Y na árvore que não é fronteira e tem a mesma marcação que X, µ(x) = µ(y), 
		 * então x é um nó duplicado.
		 * X é DUPLICADO
		*/
		Enumeration conjNode = root.breadthFirstEnumeration();
		while(conjNode.hasMoreElements())
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)conjNode.nextElement();
			PetriNetState stateY = (PetriNetState) node.getUserObject();
			
			if((stateX.equals(stateY)) && (stateX.getIdentificador() != stateY.getIdentificador())
			&& (stateY.getStateType() != STATE_FRONTEIRA))
			{
				stateX.setStateType(STATE_DUPLICADO);
				stateX.setIgualAoEstado(stateY.getIdentificador());
				
				return;
			}
		}
		
		/* 2) Se não existem transições sensibilizadas associadas à marcação µ(x),
		 * ou seja {d (µ(x),tj) é indefinida PARA todo tj pertencente à T};
		 * então X é um nó TERMINAL.
		 */
		if(! stateX.temTransicaoHabilitada())
		{
			stateX.setStateType(STATE_TERMINAL);
			
			return;
		}
		
		/* 3) Para todas as transições tj Pertencente à T, que são habilitadas em µ[x],
		 * cria-se um novo nó Z, na árvore de alcançabilidade, ligados ao primeiro,
		 * através de um arco ao qual se associa a transição disparada.
		 * 
		 * Um arco, designado tj é dirigido do nó X para o nó Z. O nó X é redefinido como nó INTERIOR
		 * e Z, torna-se um nó FRONTEIRA.
		 */
		boolean [] arrTransFire = stateX.getArrTransFire();
		for(int i=0; i < arrTransFire.length; i++)
		{
			if(arrTransFire[i] == true)
			{
				PetriNetState stateZ = createNewState(nodeX, i);
				stateZ.setStateType(STATE_FRONTEIRA);
				addObject(nodeX, stateZ);
			}
		}
		
		stateX.setStateType(STATE_INTERIOR);

	}
	
	/**
	 * Cria um Novo Estado para a árvore de alcançabilidade.
	 * <BR>A marcação do novo estado criado, estado Z, segue a seguinte regra:
	 * <BR>Inicialmente marcação de Z é o resultado do disparo da transição tj a partir do estado X.
	 * <BR>Se existe um nó Y no percurso da raiz até X cuja marcação de Z seja superior
	 * à marcação resultante do disparo da transição isto é:
	 * <BR> µ[z] > µ[y] e µ[z]i > µ[y]i, Então µ[z]i = w. 
	 * <BR>O símbolo 'w' representa um crescimento de fichas em determinado lugar da rede.
	 * <PRE>
	 * Algoritmo: Seja X o pai de Z na árvore, e Z o nó a ser processado.
	 * Z = Disparo de Tj a partir de X;
	 * Y = X;
	 * Enquanto ( Y <> null ) Faça
	 *   Se Marcacao(Z) > Marcacao(Y) Então
	 *     Para i de 1 até total de lugares Faça
	 *       Se (Z.lugar[i].fichas > Y.lugar[i].fichas) Então
	 *         Z.lugar[i].fichas = w;
	 *     Fim Para
	 *   Fim Se
	 *		
	 *   Y = Y.getPai();
	 *		
	 * Fim Enquanto
	 *
	 * retorna Z;
	 * </PRE>
	 * @param nodeX Nó pai
	 * @param transicao Número da transição disparada
	 * @return Estado após disparo da transição.
	 */
	public PetriNetState createNewState(DefaultMutableTreeNode nodeX, int transicao )
	{
		PetriNetState stateX = (PetriNetState) nodeX.getUserObject();
		simulator.setState(stateX.getArrMarking(), stateX.getArrTransFire());
		
		/* Monta o Array com a seqüência de disparos */
		int [] arrFireSequenceX= stateX.getArrFireSequence();
		int [] arrFireSequence = new int[arrFireSequenceX.length+1];
		for(int i=0; i < arrFireSequenceX.length; i++ )
		{
			arrFireSequence[i] = arrFireSequenceX[i];
		}
		arrFireSequence[arrFireSequence.length-1] = transicao;
		
		simulator.disparaTransicao(transicao);
		PetriNetState stateZ = new PetriNetState(simulator.getMarcacoes(), 
												 simulator.getTransicoesDisponiveis(), 
												 simulator.temTransicaoDisponivel(),
												 arrFireSequence);
												 
		int totalState = PetriNetState.getTotalState();
		
		PetriNetState copyZ = new PetriNetState(simulator.getMarcacoes(), 
												 simulator.getTransicoesDisponiveis(), 
												 simulator.temTransicaoDisponivel(),
												 arrFireSequence);
												 
		PetriNetState.setTotalState(totalState);
		
		/*
		 * Se existe um nó Y no percurso da raiz até X cuja marcação seja inferior 
		 * à marcação resultante do disparo da transição isto é:
		 * 
		 * Marcação do estado Y < Marcação após disparo da transição do Estado X
		 * E
		 * Marcação do Estado Y no Lugar i < Marcação do Lugar i após disparo da
		 * transição do Estado X
		 * Então 
		 * 		Marcação do Lugar i no Estado Z = w
		 * 
		 * µ [y] < d (µ [x], tj) e 
		 * µ [y]i< d (µ [x], tj) i, então d [z] i = w
		 * 	 
		 */ 			
		DefaultMutableTreeNode nodeY = nodeX;
		PetriNetState stateA = (PetriNetState) nodeY.getUserObject();
		
		while(nodeY != null)
		{
			PetriNetState stateY = (PetriNetState) nodeY.getUserObject();
			if(( compareMarking(copyZ, stateY) > 0))
			{
				for(int i=0; i <stateZ.getArrMarking().length; i++ )
				{
					if(copyZ.getMarking(i) > stateY.getMarking(i))
						stateZ.setMarking(i, TOKEN_INFINITO);
				}
			}
			
			nodeY = (DefaultMutableTreeNode) nodeY.getParent();
		} 
		
		return stateZ;
	
	}
	
	/**
	* Método que compara a marcação de 2 estados recebidos como parâmetro.
	* <UL>
	* <LI>Se marcação do estado A > Marcação do estado B retorna +1
	* <LI>Se marcação do estado A = Marcação do estado B retorna  0
	* <LI>Se marcação do estado A < Marcação do estado B retorna -1
	* </UL>
	* <pre>
	* Algoritmo: ComparaMarcacao(A,B) <--> [Marcacao(A) > Marcacao(B)]
	*
	* boolean existe = false;  // Existe um lugar de A que possui fichas > Lugar de B
	* boolean maior  = true;   // Todo lugar de A possui fichas > Todo Lugar de B
	*
	* Para i de 1 até numero de lugares Faça:
	*    Se (A.lugar[i].fichas >= B.lugar[i].fichas) E (maior = true)Então
	*       maior = true;
	*       Se (A.lugar[i].fichas > B.lugar[i].fichas) 
	*           existe = true;
	*    Senão
	*       maior = false;
	*    Fim Se
	* Fim Para
	*
	* Se ( maior = true ) E ( existe = true) Então
	*    retorna 1;
	* Se ( maior = true ) E ( existe = false ) Então
	*    retorna 0;
	* Se (maior = false) Então
	*    retorna -1;
	* </pre>
	* @param stateA Estado A
	* @param stateB Estado B
	* @return resultado da comparação.
	*/
	public static int compareMarking(PetriNetState stateA, PetriNetState stateB)
	{
		
		boolean existe = false;  
		boolean maior  = true; 

		int [] arrMarkingA = stateA.getArrMarking();
		int [] arrMarkingB = stateB.getArrMarking();
		
		for(int i=0; ((i < arrMarkingA.length )&&(maior==true)); i++)
		{
			if( (arrMarkingA[i] >= arrMarkingB[i])&&( maior == true) )
	    {
	       maior = true;
	       if(arrMarkingA[i] > arrMarkingB[i])
	           existe = true;
	    }
	    else
	    {
	       maior = false;
	    }
		}
			
		if ((maior == true) && (existe == true))
			return 1;
	
		if ((maior == true) && (existe == false))
			return 0;
			
	  return -1;
	}
	
	/**
	 * Adiciona um novo nó na árvore de seqüência de disparos.
	 * @param parent Nó pai
	 * @param child  Estado filho
	 * @return Nó criado
	 */
	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) 
	{
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
		
		if (parent == null) 
		{
			parent = root;
		}
		
		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
		
		tree.scrollPathToVisible(new TreePath(childNode.getPath()));
				
		return childNode;
	}
	
	/**
	 * Verifica várias propriedades da Rede de Petri.
	 * <OL>
	 * <LI>Limitação
	 * <LI>Conservação
	 * <LI>Vivacidade
	 * </OL> 
	 * @param stateX Estado a ser verificado
	 */
	public void verifyProperties(PetriNetState stateX)
	{
		
		verifyLimite(stateX);
		verifyConservativa(stateX);
		verifyLimite(stateX);
		verifyVivacidade(stateX);
	}
	
	/**
	 * Uma rede é limitada, se para todas as marcações acessíveis, o número de fichas
	 * em qualquer lugar da Rede não exceder K (inteiro - limite).
	 * O algoritmo parte do princípio que ela é limitada. Se ela em algum momento
	 * deixar de ser limitada, ele pára de testar qual o limite. É ilimitada quando
	 * for encontrado em algum Lugar um número infinito de fichas (w).
	 * 
	 * @param stateX Estado a ser verificado.
	 */
	public void verifyLimite(PetriNetState stateX)
	{
		if(properties.ehLimitada())
		{
			if(properties.getLimite() < stateX.getLimite())
			{
				properties.setLimite(stateX.getLimite());
			}
		}
	}
	
	/**
	 * Uma Rede é conservativa se o total de fichas na rede se mantém.
	 * O algoritmo parte do princípio que a rede é conservativa, se ela deixar de ser conservativa
	 * ele pára de fazer o teste.
	 * @param stateX Estado a ser verificado.
	 */
	public void verifyConservativa(PetriNetState stateX)
	{
		if(properties.ehConservativa())
		{
			if(properties.getTotalFichas() != stateX.getTotalMarking())
			{
				properties.setEhConservativa(false);
			}
		}
	}
	
	/**
	 * Uma rede é viva se pelo menos uma transição puder ser disparada, em qualquer
	 * estado, ou seja ela é morta, se chegar a algum estado que não tenha transições
	 * para disparar.
	 * @param stateX Estado a ser verificado.
	 */
	public void verifyVivacidade(PetriNetState stateX)
	{
		if(stateX.temTransicaoHabilitada() == false)
		{
			properties.addDeadLock(stateX.getArrFireSequence(), stateX.getIdentificador());
			properties.setEhViva(false);
		}
	}
	
	/**
	 * Retorna a Árvore de Alcançabilidade.
	 * @return Árvore de Alcançabilidade
	 */
	public JTree getTree()
	{
		return tree;
	}
	
	/**
	 * Retorna as Propriedades encontradas na Rede.
	 * @return Propriedades da Rede de Petri.
	 */
	public PetriNetProperties getProperties()
	{
		return properties;
	}
}
