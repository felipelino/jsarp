package br.uerj.petrinetanalyzer.engine;

import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import br.uerj.petrinetanalyzer.common.ImplPetriNetBase;
import br.uerj.petrinetanalyzer.common.interfaces.IntfEngineConstants;

/**
 * Classe que cuida da engine da An�lise da Rede de Petri
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
	 * �rvore de alcan�abilidade
	 */
	private JTree tree = null;
	/**
	 * N� ra�z da �rvore de alcan�abilidade
	 */
	private DefaultMutableTreeNode root = null;
	/**
	 * Model usado para tratar a �rvore de alcan�abilidade, apenas prepara
	 * a �rvore para ser usada na interface gr�fica.
	 */
	private DefaultTreeModel treeModel = null;
	
	/**
	 * Construtor que recebe a Rede de Petri como Par�metro.
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
	 * Gera a arvore de alcan�abilidade da Rede de Petri e
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
	 * Fun��o recursiva, que vai varrendo a �rvore que vai sendo criada, e processando
	 * seus n�s.
	 * <PRE>
	 * Algoritmo: Seja N o n� recebido como par�metro.
	 *  Para i de 1 at� N�mero de filhos de N Fa�a
	 *     X = N.getFilho(i);
	 *     processNode(X);
	 *     geraArvore(X);
	 *     verifyProperties(X);
	 *  Fim Para
	 * </PRE>
	 
	 * @param node N� pai, cujos filhos ser�o processados.
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
	 * Processa o N� da �rvore de alcan�abilidade
	 * <PRE>
	 * Algoritmo:
	 * Seja o X o n� a ser processado.
	 * 1) Se existe um n� Y na �rvore que n�o � fronteira e tem a mesma marca��o que X.
	 *    ent�o X � um n� DUPLICADO.
	 *
	 *   Para cada n� Y da arvore Fa�a
	 *      Se ((�(Y) = �(X)) E (Y != X) E (Y.tipo != FRONTEIRA)) Ent�o 
	 *         X.tipo = DUPLICADO; 
	 *         retorna;
	 *      Fim Se
	 *   Fim Para.
	 * 
	 * 2) Se n�o existem transi��es sensibilizadas associadas � marca��o �(x),
	 * ou seja {d (�(x),tj) � indefinida PARA todo tj pertencente � T};
	 * ent�o X � um n� TERMINAL.
	 *  
	 *   Se (X.temTransicaoDisponivel == false) Ent�o
	 *      X.tipo = TERMINAL;
	 *      retorna;
	 *   Fim Se
	 *    
	 * 3) Para todas as transi��es tj Pertencente � T, que s�o habilitadas em �[x],
	 * cria-se um novo n� Z, na �rvore de alcan�abilidade, ligados ao primeiro,
	 * atrav�s de um arco ao qual se associa a transi��o disparada.
	 * 
	 * Um arco, designado tj � dirigido do n� X para o n� Z. O n� X � redefinido como n� INTERIOR
	 * e Z, torna-se um n� FRONTEIRA.
	 *
	 *  Para cada transi��o habilitada  T de X Fa�a
	 *     Z = createNewState(X);
	 *     Z.tipo = FRONTEIRA;
	 *     AdicionaN�NaArvore(Z);
	 *  Fim Para
	 *  X.tipo = INTERIOR;
	 *
	 * </PRE>
	 */
	public void processNode(DefaultMutableTreeNode nodeX)
	{
		PetriNetState stateX = (PetriNetState) nodeX.getUserObject();
		
		
		
		/* 1) Se existe um n� Y na �rvore que n�o � fronteira e tem a mesma marca��o que X, �(x) = �(y), 
		 * ent�o x � um n� duplicado.
		 * X � DUPLICADO
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
		
		/* 2) Se n�o existem transi��es sensibilizadas associadas � marca��o �(x),
		 * ou seja {d (�(x),tj) � indefinida PARA todo tj pertencente � T};
		 * ent�o X � um n� TERMINAL.
		 */
		if(! stateX.temTransicaoHabilitada())
		{
			stateX.setStateType(STATE_TERMINAL);
			
			return;
		}
		
		/* 3) Para todas as transi��es tj Pertencente � T, que s�o habilitadas em �[x],
		 * cria-se um novo n� Z, na �rvore de alcan�abilidade, ligados ao primeiro,
		 * atrav�s de um arco ao qual se associa a transi��o disparada.
		 * 
		 * Um arco, designado tj � dirigido do n� X para o n� Z. O n� X � redefinido como n� INTERIOR
		 * e Z, torna-se um n� FRONTEIRA.
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
	 * Cria um Novo Estado para a �rvore de alcan�abilidade.
	 * <BR>A marca��o do novo estado criado, estado Z, segue a seguinte regra:
	 * <BR>Inicialmente marca��o de Z � o resultado do disparo da transi��o tj a partir do estado X.
	 * <BR>Se existe um n� Y no percurso da raiz at� X cuja marca��o de Z seja superior
	 * � marca��o resultante do disparo da transi��o isto �:
	 * <BR> �[z] > �[y] e �[z]i > �[y]i, Ent�o �[z]i = w. 
	 * <BR>O s�mbolo 'w' representa um crescimento de fichas em determinado lugar da rede.
	 * <PRE>
	 * Algoritmo: Seja X o pai de Z na �rvore, e Z o n� a ser processado.
	 * Z = Disparo de Tj a partir de X;
	 * Y = X;
	 * Enquanto ( Y <> null ) Fa�a
	 *   Se Marcacao(Z) > Marcacao(Y) Ent�o
	 *     Para i de 1 at� total de lugares Fa�a
	 *       Se (Z.lugar[i].fichas > Y.lugar[i].fichas) Ent�o
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
	 * @param nodeX N� pai
	 * @param transicao N�mero da transi��o disparada
	 * @return Estado ap�s disparo da transi��o.
	 */
	public PetriNetState createNewState(DefaultMutableTreeNode nodeX, int transicao )
	{
		PetriNetState stateX = (PetriNetState) nodeX.getUserObject();
		simulator.setState(stateX.getArrMarking(), stateX.getArrTransFire());
		
		/* Monta o Array com a seq��ncia de disparos */
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
		 * Se existe um n� Y no percurso da raiz at� X cuja marca��o seja inferior 
		 * � marca��o resultante do disparo da transi��o isto �:
		 * 
		 * Marca��o do estado Y < Marca��o ap�s disparo da transi��o do Estado X
		 * E
		 * Marca��o do Estado Y no Lugar i < Marca��o do Lugar i ap�s disparo da
		 * transi��o do Estado X
		 * Ent�o 
		 * 		Marca��o do Lugar i no Estado Z = w
		 * 
		 * � [y] < d (� [x], tj) e 
		 * � [y]i< d (� [x], tj) i, ent�o d [z] i = w
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
	* M�todo que compara a marca��o de 2 estados recebidos como par�metro.
	* <UL>
	* <LI>Se marca��o do estado A > Marca��o do estado B retorna +1
	* <LI>Se marca��o do estado A = Marca��o do estado B retorna  0
	* <LI>Se marca��o do estado A < Marca��o do estado B retorna -1
	* </UL>
	* <pre>
	* Algoritmo: ComparaMarcacao(A,B) <--> [Marcacao(A) > Marcacao(B)]
	*
	* boolean existe = false;  // Existe um lugar de A que possui fichas > Lugar de B
	* boolean maior  = true;   // Todo lugar de A possui fichas > Todo Lugar de B
	*
	* Para i de 1 at� numero de lugares Fa�a:
	*    Se (A.lugar[i].fichas >= B.lugar[i].fichas) E (maior = true)Ent�o
	*       maior = true;
	*       Se (A.lugar[i].fichas > B.lugar[i].fichas) 
	*           existe = true;
	*    Sen�o
	*       maior = false;
	*    Fim Se
	* Fim Para
	*
	* Se ( maior = true ) E ( existe = true) Ent�o
	*    retorna 1;
	* Se ( maior = true ) E ( existe = false ) Ent�o
	*    retorna 0;
	* Se (maior = false) Ent�o
	*    retorna -1;
	* </pre>
	* @param stateA Estado A
	* @param stateB Estado B
	* @return resultado da compara��o.
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
	 * Adiciona um novo n� na �rvore de seq��ncia de disparos.
	 * @param parent N� pai
	 * @param child  Estado filho
	 * @return N� criado
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
	 * Verifica v�rias propriedades da Rede de Petri.
	 * <OL>
	 * <LI>Limita��o
	 * <LI>Conserva��o
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
	 * Uma rede � limitada, se para todas as marca��es acess�veis, o n�mero de fichas
	 * em qualquer lugar da Rede n�o exceder K (inteiro - limite).
	 * O algoritmo parte do princ�pio que ela � limitada. Se ela em algum momento
	 * deixar de ser limitada, ele p�ra de testar qual o limite. � ilimitada quando
	 * for encontrado em algum Lugar um n�mero infinito de fichas (w).
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
	 * Uma Rede � conservativa se o total de fichas na rede se mant�m.
	 * O algoritmo parte do princ�pio que a rede � conservativa, se ela deixar de ser conservativa
	 * ele p�ra de fazer o teste.
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
	 * Uma rede � viva se pelo menos uma transi��o puder ser disparada, em qualquer
	 * estado, ou seja ela � morta, se chegar a algum estado que n�o tenha transi��es
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
	 * Retorna a �rvore de Alcan�abilidade.
	 * @return �rvore de Alcan�abilidade
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
