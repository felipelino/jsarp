package br.uerj.petrinetanalyzer.gui;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import br.uerj.language.LanguageTool;
import br.uerj.petrinetanalyzer.common.PlaceBase;
import br.uerj.petrinetanalyzer.common.TransitionBase;
import br.uerj.petrinetanalyzer.gui.interfaces.IntfLanguageSimulationConstants;
import br.uerj.petrinetanalyzer.gui.objects.PetriNetGraph;
import br.uerj.petrinetanalyzer.gui.objects.TreeNodeState;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Define a janela com a árvore mostrando a evolução
 * da simulação.
 * @author Felipe Lino
 * <BR>Data: 11/02/2007
 * <BR>Atualizado: 03/01/2007
 */
public class SimulationWindow extends JFrame
implements IntfLanguageSimulationConstants,
TreeSelectionListener
{
	/** Variável requisitada na herança de JFrame */
	static final long serialVersionUID = 1;
	
	/* Variáveis relacionadas a parte gráfica do painel */
	private Container cp; 
	private DefaultFormBuilder builder;
	private FormLayout layout;
	private CellConstraints cc;
		
	private JLabel lbTree;
	private JLabel lbInfo;
	
	private JTextArea txtInfo;
	private JTree	  tree;
	private JScrollPane scrollTree;
	private JScrollPane scrollInfo;
	
	/* Variáveis relacionadas a funcionalidade da janela, exibição de informações */
	private PetriNetGraph pn = null;
	
	private DefaultMutableTreeNode rootNode = null;
	private DefaultMutableTreeNode atual    = null;
	
	private DefaultTreeModel treeModel = null;
	
	/**
	 * Construtor recebendo a rede de petri, de onde serão extraídas informações para
	 * serem exibidas. E o nó principal.
	 * @param pn Rede de Petri
	 * @param rootState Estado Inicial
	 */
	public SimulationWindow(PetriNetGraph pn, TreeNodeState rootState)
	{
		setSize(700, 330);
		
		cp = this.getContentPane();
		
		this.setTitle(LanguageTool.getString(simWindowKey));
		
		createPanelInfo();
		createPanelTree(rootState);
		
		cp.add(montaPainel());
		
		this.pn = pn;
		
		refreshText();
	}
	
	/**
	 * Monta a janela.
	 * @return	Painel com a Janela
	 */
	private Component montaPainel()
	{
		layout = new FormLayout(
				// Colunas
				"fill:200dlu:grow," +
				"fill:3dlu," +
				"fill:130dlu:grow", 
				// Linhas
				"pref," + 	// 1
				"3dlu,"	+	// 2
				"fill:110dlu:grow" 	// 3
			);
		
		cc = new CellConstraints();
		
		//builder = new DefaultFormBuilder(layout, new FormDebugPanel());
		builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();
		
		/* Coluna , Linha, Celulas */
		builder.add(lbTree, cc.xy(1,1));
		builder.add(lbInfo, cc.xy(3,1));
		
		builder.add(scrollTree, cc.xy(1,3));
		builder.add(scrollInfo, cc.xy(3,3));
		
		return builder.getPanel();
	
	}
	
	/**
	 * Cria o painel com a árvore mostrando a seqüência de disparos.
	 * @param rootState Nó Inicial
	 */
	private void createPanelTree(TreeNodeState rootState)
	{
		lbTree = MainWindow.createJLabelDefault(lbTreeKey, IntfLanguageSimulationConstants.lbTree);
		lbTree.setHorizontalAlignment(JLabel.LEFT);
		
		rootNode  = new DefaultMutableTreeNode(rootState);
		treeModel = new DefaultTreeModel(rootNode);
	   
		tree = new JTree(treeModel);
		tree.setEditable(true);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);
		tree.addTreeSelectionListener(this);
		
		atual = rootNode;
		
		scrollTree = new JScrollPane(tree);
		scrollTree.setAutoscrolls(true);
		scrollTree.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollTree.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	/**
	 * Cria painel com as informações do estado selecionado.
	 *
	 */
	private void createPanelInfo()
	{
		lbInfo = MainWindow.createJLabelDefault(lbInfoKey, IntfLanguageSimulationConstants.lbInfo);
		lbInfo.setHorizontalAlignment(JLabel.CENTER);
		
		txtInfo = new JTextArea();
		txtInfo.setEditable(false);
		
		scrollInfo = new JScrollPane(txtInfo);
		scrollInfo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollInfo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	/**
	 * Adiciona um novo nó a árvore de seqüência de disparos.
	 * O novo estado será adicionado ao último estado a ter sido disparado.
	 * @param state Nó estado da rede a ser acrescentado
	 */
	public void addTreeNodeState(TreeNodeState state)
	{
		DefaultMutableTreeNode node = null;
		TreeNodeState test = null;
		boolean exists = false;
		
		for(int i=0; i < atual.getChildCount(); i++)
		{
			node = (DefaultMutableTreeNode) atual.getChildAt(i);
			test = (TreeNodeState) node.getUserObject();
			if(test.equals(state))
			{
				exists = true;
				atual = node;
				break;
			}
		}
		
		if(! exists)
			atual = addObject(atual, state);
		
		tree.setSelectionPath(new TreePath(atual.getPath()));
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
			parent = rootNode;
		}
		
		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
		
		tree.scrollPathToVisible(new TreePath(childNode.getPath()));
				
		return childNode;
	}
	
	/**
	 * Volta um nó na árvore de seqüência de disparos.
	 * @return Nó Estado selecionado.
	 */
	public TreeNodeState backTreeStateNode()
	{
		atual = atual.getPreviousNode();
		tree.setSelectionPath(new TreePath(atual.getPath()));
		
		return (TreeNodeState)atual.getUserObject();
	}
	
	/**  
	 * Requerido pela interface TreeSelectionListener.
	 * @param evt Evento da árvore de disparos
	 */
    public void valueChanged(TreeSelectionEvent evt) 
    {
    	DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if(node != null)
        {
	        TreeNodeState state = (TreeNodeState)node.getUserObject();
	        if(state != null)
	        {
	        	txtInfo.setText(getStateInfo(state));
	        }
	        else
	        	txtInfo.setText("state is null");
        }
        else
        	txtInfo.setText("node is null");
    }
    
    /**
     * Retorna o Estado selecionado.
     * @return Estado Selecionado
     */
    public TreeNodeState getSelectedState()
    {
    	DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        TreeNodeState state = (TreeNodeState)node.getUserObject();
        
        return state;
    }
    
    /**
     * Altera o Estado atual para o selecionado.
     *
     */
    public void changeAtualForSelectedState()
    {
    	atual = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
    }
    
    /**
     * Retorna String com as características do Estado.
     */
    public String getStateInfo(TreeNodeState state)
    {
    	String SPACE = "   ";
    	String strTitle01 = LanguageTool.getString(SIM_INFO_01_KEY);
    	String strTitle02 = LanguageTool.getString(SIM_INFO_02_KEY);
    	String strTitle03 = LanguageTool.getString(SIM_INFO_03_KEY);
    	
    	/* Cria String com a Seqüência de Disparos */
    	String strFireSeq = strTitle01 + "\n";
    	int [] fireSeq = state.getFireSequence();
    	for(int i=0; i < fireSeq.length; i++)
    	{
    		TransitionBase trans = (TransitionBase) pn.getTransicao(fireSeq[i]);
    		strFireSeq += SPACE + (i+1) + "º - " + trans.getIdentificador() + " - " + trans.getNome() + "\n";
    	}
    	
    	if(fireSeq.length < 1)
    		strFireSeq += SPACE + LanguageTool.getString(SIM_INFO_05_KEY) + "\n";
    	
    	
    	/* Cria String com as marcações dos lugares */
    	String strMarking = strTitle02 + "\n";
    	int [] lstMarking = state.getListMarcacao();
    	for(int i=0; i < pn.getNumLugar(); i++)
    	{
    		PlaceBase place = (PlaceBase) pn.getPlace(i);
    		strMarking += SPACE + place.getNome() + " = " + lstMarking[i] + "\n"; 
    	}
    	
    	/* Cria String com a lista de Transições disponíveis */
    	String strTrans = strTitle03 + "\n";
    	boolean [] lstTransFire = state.getListTransFire();
    	boolean flag = false;
    	int cont = 0;
    	for(int i=0; i < pn.getNumTransicao(); i++)
    	{
    		if(lstTransFire[i] == true)
    		{
    			flag = true;
    			cont++;
    			TransitionBase trans = (TransitionBase) pn.getTransicao(i);
    			strTrans += SPACE + cont + ") " + trans.getIdentificador() + " - " + trans.getNome() + "\n";
    		}
    	}
    	
    	/* Se nenhuma transição estiver habilitada no estado */
    	if(flag == false)
    	{
    		strTrans = LanguageTool.getString(SIM_INFO_04_KEY);
    		strTrans += "\n";
    	}
    	
    	/* String unindo todas as informações */
    	String strInfo = strFireSeq + "\n" + strMarking + "\n" + strTrans;
    	
    	return strInfo;
    }
    
    /**
     * Atualiza o texto na Janela 
     *
     */
    public void refreshText()
    {
    	this.setTitle(LanguageTool.getString(simWindowKey));
    	lbInfo.setText(LanguageTool.getString(lbInfoKey));
    	lbTree.setText(LanguageTool.getString(lbTreeKey));
    	
    	DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
    	TreeNodeState state = null;
    	if(node != null)
    		state = (TreeNodeState)node.getUserObject();
    	
        if(state != null)
        {
        	txtInfo.setText(getStateInfo(state));
        }
        else
        	txtInfo.setText("");
    }
}
