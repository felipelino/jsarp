package br.uerj.petrinetanalyzer.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import br.uerj.language.LanguageTool;
import br.uerj.petrinetanalyzer.common.ImplPetriNetBase;
import br.uerj.petrinetanalyzer.common.PlaceBase;
import br.uerj.petrinetanalyzer.common.TransitionBase;
import br.uerj.petrinetanalyzer.engine.PetriNetProperties;
import br.uerj.petrinetanalyzer.engine.PetriNetState;
import br.uerj.petrinetanalyzer.gui.interfaces.IntfLanguageAnalyzerConstants;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Define a janela com o resultado da verificação
 * das propriedades da Rede de Petri.
 * 
 * @author Felipe Lino
 * <BR>Data: 26/02/2007
 * <BR>Atualizado: 12/10/2007
 */
public class AnalyzerWindow extends JFrame
implements IntfLanguageAnalyzerConstants,
TreeSelectionListener
{
	/** Variável requisitada na herança de JFrame */
	static final long serialVersionUID = 1;
	
	private static final Font defaultFont = new Font("Courier",Font.PLAIN,11);
	public static int TABULACAO = 3;
	
	/* Variáveis relacionadas a parte gráfica do painel */
	private Container cp; 
	private DefaultFormBuilder builder;
	private FormLayout layout;
	private CellConstraints cc;
		
	private JLabel lbAnalyzerTree;
	private JLabel lbStateInfo;
	private JLabel lbPnInfo;
	
	private JTextArea txtStateInfo;
	private JTextArea txtPnInfo;
	private JTree	  tree;
	
	private JScrollPane scrollTree;
	private JScrollPane scrollStateInfo;
	private JScrollPane scrollPnInfo;
	
	
	private ImplPetriNetBase pn;
	private PetriNetProperties pnProp;
	
	/**
	 * Construtor recebendo a rede de petri, de onde serão extraídas informações para
	 * serem exibidas. E a árvore de alcançabilidade.
	 * @param pn Rede de Petri
	 * @param tree Árvore de Alcançabilidade
	 */
	public AnalyzerWindow(ImplPetriNetBase pn, JTree tree, PetriNetProperties prop)
	{
		setSize(700, 530);
		
		cp = this.getContentPane();
				
		this.setTitle(anWindow);
		this.pnProp = prop;
		this.pn = pn;
		
		createStatePanelInfo();
		createPnPanelInfo();
		createPanelTree(tree);
		
		cp.add(montaPainel());
		
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
				"10dlu," + 	// 1
				"3dlu,"	+	// 2
				"fill:110dlu:grow," + 	// 3
				"3dlu,"  +	// 4
				"pref,"  +	// 5
				"3dlu,"  +	// 6
				"fill:150dlu:grow"	// 7
			);
		
		cc = new CellConstraints();
		
		//builder = new DefaultFormBuilder(layout, new FormDebugPanel());
		builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();
		
		/* Coluna , Linha, Celulas */
		builder.add(lbAnalyzerTree, cc.xy(1,1));
		builder.add(lbStateInfo, 	cc.xy(3,1));
		builder.add(lbPnInfo, 		cc.xyw(1,5,3));
		
		builder.add(scrollTree,		cc.xy(1,3));
		builder.add(scrollStateInfo,cc.xy(3,3));
		builder.add(scrollPnInfo, 	cc.xyw(1,7,3));
		
		return builder.getPanel();
	
	}
	
	/**
	 * Cria o painel com a árvore mostrando a seqüência de disparos.
	 * @param tree Nó Inicial
	 */
	private void createPanelTree(JTree tree)
	{
		lbAnalyzerTree = MainWindow.createJLabelDefault(lbAnalyzerTreeKey, lbAnalyzerTreeKey);
		lbAnalyzerTree.setHorizontalAlignment(JLabel.LEFT);
		this.tree = tree;
		this.tree.setEditable(true);
		this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.tree.setShowsRootHandles(true);
		this.tree.addTreeSelectionListener(this);
		
		scrollTree = new JScrollPane(this.tree);
		scrollTree.setAutoscrolls(true);
		scrollTree.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollTree.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	/**
	 * Cria painel com as informações do estado selecionado.
	 *
	 */
	private void createStatePanelInfo()
	{
		lbStateInfo = MainWindow.createJLabelDefault(lbStateInfoKey,IntfLanguageAnalyzerConstants.lbStateInfo);
		lbStateInfo.setHorizontalAlignment(JLabel.CENTER);
		
		txtStateInfo = new JTextArea();
		txtStateInfo.setEditable(false);
		
		scrollStateInfo = new JScrollPane(txtStateInfo);
		scrollStateInfo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollStateInfo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	/**
	 * Cria painel com as informações do estado selecionado.
	 *
	 */
	private void createPnPanelInfo()
	{
		lbPnInfo = MainWindow.createJLabelDefault(lbPnInfoKey, IntfLanguageAnalyzerConstants.lbPnInfo);
		lbPnInfo.setHorizontalAlignment(JLabel.CENTER);
		
		txtPnInfo = new JTextArea();
		txtPnInfo.setEditable(false);
		
		scrollPnInfo = new JScrollPane(txtPnInfo);
		scrollPnInfo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPnInfo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		txtPnInfo.setFont(defaultFont);
		txtPnInfo.setText(getStrProperties());
		
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
        	PetriNetState state = (PetriNetState) node.getUserObject();
	    
	        if(state != null)
	        {
	        	txtStateInfo.setText(getStateInfo(state));
	        }
	        else
	        	txtStateInfo.setText("state is null");
        }
        else
        	txtStateInfo.setText("node is null");
    }
    
    /**
     * Retorna String com as características do Estado.
     */
    public String getStateInfo(PetriNetState state)
    {
    	String SPACE = "   ";
    	String strTitle01 = LanguageTool.getString(AN_INFO_01_KEY);
    	String strTitle02 = LanguageTool.getString(AN_INFO_02_KEY);
    	String strTitle03 = LanguageTool.getString(AN_INFO_03_KEY);
    	
    	/* Cria String com a Seqüência de Disparos */
    	String strFireSeq = strTitle01 + "\n";
    	int [] fireSeq = state.getArrFireSequence();
    	for(int i=0; i < fireSeq.length; i++)
    	{
    		TransitionBase trans = (TransitionBase) pn.getTransicao(fireSeq[i]);
    		strFireSeq += SPACE + (i+1) + "º - " + trans.getIdentificador() + " - " + trans.getNome() + "\n";
    	}
    	
    	if(fireSeq.length < 1)
    		strFireSeq += SPACE + LanguageTool.getString(AN_INFO_05_KEY) + "\n";
    	
    	
    	/* Cria String com as marcações dos lugares */
    	String strMarking = strTitle02 + "\n";
    	int [] lstMarking = state.getArrMarking();
    	for(int i=0; i < pn.getNumLugar(); i++)
    	{
    		PlaceBase place = (PlaceBase) pn.getLugar(i);
    		if(lstMarking[i] >= PetriNetState.TOKEN_INFINITO)
    			strMarking += SPACE + "P" + i + " - " + place.getNome() + " = W \n";
    		else
    			strMarking += SPACE + "P" + i + " - " + place.getNome() + " = " + lstMarking[i] + "\n"; 
    	}
    	
    	/* Cria String com a lista de Transições disponíveis */
    	String strTrans = strTitle03 + "\n";
    	if(state.temTransicaoHabilitada() == true)
    	{
	    	boolean [] lstTransFire = state.getArrTransFire();
	       	int cont = 0;
	    	for(int i=0; i < pn.getNumTransicao(); i++)
	    	{
	    		if(lstTransFire[i] == true)
	    		{
	      			cont++;
	    			TransitionBase trans = (TransitionBase) pn.getTransicao(i);
	    			strTrans += SPACE + cont + ") " + trans.getIdentificador() + " - " + trans.getNome() + "\n";
	    		}
	    	}
    	}	
    	else
    	{
    		strTrans = LanguageTool.getString(AN_INFO_04_KEY);
    		strTrans += "\n";
    	}
    	
    	String strEquals = "";
    	if(state.getIgualAoEstado() > -1)
    	{
    		strEquals += LanguageTool.getString(AN_INFO_06_KEY) + " " + state.getIgualAoEstado();
    	}
    	
    	/* String unindo todas as informações */
    	String strInfo = strFireSeq + "\n" + strMarking + "\n" + strTrans + "\n" + strEquals;
    	
    	return strInfo;
    }
    
    /**
     * Pega as Propriedades da Rede de Petri.
     * @return String com o resumo das propriedades da Rede de Petri
     */
    public String getStrProperties()
    {
    	String SPACE = "   ";
    	
    	String strProp = "";
    	
    	String strNome = LanguageTool.getString(PROP_INFO_10_KEY);
    	strNome += " " + pnProp.getNomeRedePetri();
    	if( pnProp.getNomeRedePetri().toLowerCase().indexOf(".xml") > 0)
    		strNome = strNome.substring(0, strNome.toLowerCase().indexOf(".xml")); 
    	
    	String strLimit = null;
    	if(pnProp.ehLimitada() == true)
    	{
    		strLimit  = LanguageTool.getString(PROP_INFO_03_KEY);
    		strLimit += "\n" + LanguageTool.getString(PROP_INFO_05_KEY);
    		strLimit += " " + pnProp.getLimite() + ".";
    	}
    	else
    	{
    		strLimit = LanguageTool.getString(PROP_INFO_04_KEY);
    	}
    	
    	String strConserva = null;
    	if(pnProp.ehConservativa() == true)
    	{
    		strConserva  = LanguageTool.getString(PROP_INFO_06_KEY);
    		strConserva += "\n" + LanguageTool.getString(PROP_INFO_11_KEY);
    		strConserva += " " + pnProp.getTotalFichas() + ".";
    	}
    	else
    	{
    		strConserva = LanguageTool.getString(PROP_INFO_07_KEY);
    	}
    	
    	String strViva = null;
    	String strSeq = "";
    	if(pnProp.ehViva() == true)
    	{
    		strViva = LanguageTool.getString(PROP_INFO_01_KEY);
    	}
    		
    	else
    	{
    		strViva  = LanguageTool.getString(PROP_INFO_02_KEY);
    		
    		strSeq = LanguageTool.getString(PROP_INFO_08_KEY);
    		for(int i=0; i < pnProp.getTotalDeadLockStates(); i++)
    		{
    			strSeq += "\n" + LanguageTool.getString(PROP_INFO_09_KEY) + " " +(i+1) + ":";
    			int [] arrSeq = pnProp.getFireSequenceDeadLock(i);
    			for(int j=0; j < arrSeq.length; j++)
	    		{
	    			TransitionBase trans = (TransitionBase) pn.getTransicao(arrSeq[j]);
	    			strSeq +="\n" + SPACE + (j+1) + "º - T" + arrSeq[j] + " - " + trans.getNome();
	    		}
    			
    			strSeq +="\n" + LanguageTool.getString(PROP_INFO_12_KEY) +" "+ pnProp.getDeadLockState(i);
    			strSeq +="\n";
    		}
    	}
    	
    	/* Exibe as Matrizes de Entrada, Saída e Inciência */
    	String strAux="";
    	int max = 0;
    	int tam = 0;
    	for(int i=0; i < pn.getNumArco(); i++)
    	{
    		strAux = "" + pn.getArc(i).getPeso();
    		tam = strAux.length();
    		if(tam > max)
    			max = tam;
    	}
    	TABULACAO = max + 4;
    	String strMatrizEntrada = LanguageTool.getString(PROP_INFO_13_KEY)
    							+ "\n"+	getMatrizEntrada(pn.getMatrizEntrada(), pn.getNumTransicao(), pn.getNumLugar());
    	
    	String strMatrizSaida	= LanguageTool.getString(PROP_INFO_14_KEY)
								+ "\n"+ getMatrizSaida(pn.getMatrizSaida()	, pn.getNumTransicao(), pn.getNumLugar());
    	
    	String strMatrizIncid	= LanguageTool.getString(PROP_INFO_15_KEY)
								+ "\n"+ getMatrizIncidencia(pn.getMatrizIncidencia(), pn.getNumTransicao(), pn.getNumLugar());
    	
    	strProp = strNome + "\n\n" + strViva + "\n\n" + strLimit + "\n\n" + strConserva 
    			+ "\n\n" + strMatrizEntrada + "\n\n" + strMatrizSaida + "\n\n" + strMatrizIncid
    			+ "\n\n" + strSeq;
    	
    	return strProp;
    }
    
    /**
     * Atualiza o texto na Janela 
     *
     */
    public void refreshText()
    {
    	this.setTitle(LanguageTool.getString(anWindowKey));
    	lbStateInfo.setText(LanguageTool.getString(lbStateInfoKey));
    	lbPnInfo.setText(LanguageTool.getString(lbPnInfoKey));
    	lbAnalyzerTree.setText(LanguageTool.getString(lbAnalyzerTreeKey));
    	
    	DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
    	PetriNetState state = null;
    	if(node != null)
    	{
    		state = (PetriNetState)node.getUserObject();
    	}
        if(state != null)
        {
        	txtStateInfo.setText(getStateInfo(state));
        }
        
        txtPnInfo.setText(getStrProperties());
    }   
    
    
    
    /**
     * Transforma a Matriz de Entrada para o formato de String
     * @param matrizEntrada Matriz de Entrada
     * @param numTrans Número de Transições
     * @param numPlace Número de Lugares
     * @return Matriz de Entrada no formato String
     */
    public String getMatrizEntrada(int[][] matrizEntrada, int numTrans, int numPlace)
    {
    	return matrizToString(matrizEntrada, numTrans, numPlace);
    }
    
    /**
     * Transforma a Matriz de Saída para o formato de String
     * @param matrizSaida Matriz de Saída
     * @param numTrans Número de Transições
     * @param numPlace Número de Lugares
     * @return Matriz de Saída no formato String
     */
    public String getMatrizSaida(int[][] matrizSaida, int numTrans, int numPlace)
    {
    	return matrizToString(matrizSaida, numTrans, numPlace);
    }
    
    /**
     * Transforma a Matriz de Incidência para o formato de String
     * @param matrizIncidencia Matriz de Incidência
     * @param numTrans Número de Transições
     * @param numPlace Número de Lugares
     * @return Matriz de Incidência no formato String
     */
    public String getMatrizIncidencia(int[][] matrizIncidencia, int numTrans, int numPlace)
    {
    	return matrizToString(matrizIncidencia, numTrans, numPlace);
    }
    
    /**
     * Transforma a Matriz recebida como parâmetro para forma de String
     * @param matriz Matriz
     * @param numLin Total de Linhas
     * @param numCol Total de Colunas
     * @return Matriz no formato de String
     */
    public static String matrizToString(int[][] matriz, int numLin, int numCol)
    {
    	String strReturn = "";
	   	
	   	/*
	   	 * Cada Linha representa uma transição enquanto cada coluna representa um Lugar
	   	 * A iteração linha/coluna representa o arco 
	   	 */
	  	   	
	   	for(int coluna=0; coluna < numCol; coluna++)
	   	{
	   		if(coluna == 0)
	   			strReturn += completeWithSpaces("", TABULACAO+1);
	   		strReturn += completeWithSpaces("P"+coluna, TABULACAO);
	   		
	   	}
	   	
	   	for(int linha=0; linha < numLin; linha++)
	   	{
	   		strReturn += "\n";
	   		strReturn += completeWithSpaces("T"+linha , TABULACAO);
	   		
	   		for(int coluna=0; coluna < numCol; coluna++)
	   		{
	   			strReturn += completeWithSpaces(""+matriz[linha][coluna], TABULACAO);
	   		}
	   		
	   		
	   	}
	   	
	   	return strReturn;
    }
    
    /**
     * Método que completa com espaços à esquerda a String recebida como parâmetro.
     * <p>
     * @param strIn String a ser completada com espaços
     * @param qtdSpaces Quantidade de caracteres
     * @return String com espaços à esquerda
     */
    public static String completeWithSpaces(String strIn, int qtdSpaces)
    {
    	String strReturn = "";
    	
    	int totalSpaces = qtdSpaces - strIn.length();
    	if(totalSpaces <0 )
    		totalSpaces = 0;
    	
    	while(totalSpaces > 0)
    	{
    		strReturn +=" ";
    		totalSpaces--;
    	}
    	
    	strReturn += strIn ;
    	return strReturn;
    	
    }
    
}
