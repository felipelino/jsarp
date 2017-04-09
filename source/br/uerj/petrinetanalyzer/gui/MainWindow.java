package br.uerj.petrinetanalyzer.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import br.uerj.language.LanguageTool;
import br.uerj.petrinetanalyzer.gui.interfaces.IntfLanguageConstants;
import br.uerj.petrinetanalyzer.gui.interfaces.IntfMainConstants;
import br.uerj.petrinetanalyzer.gui.listener.AnalyzerAction;
import br.uerj.petrinetanalyzer.gui.listener.ArcActionListener;
import br.uerj.petrinetanalyzer.gui.listener.ButtonActionListener;
import br.uerj.petrinetanalyzer.gui.listener.MenuActionListener;
import br.uerj.petrinetanalyzer.gui.listener.PlaceActionListener;
import br.uerj.petrinetanalyzer.gui.listener.SimulationAction;
import br.uerj.petrinetanalyzer.gui.listener.TransActionListener;
import br.uerj.petrinetanalyzer.gui.objects.PetriNetGraph;
import br.uerj.swing.JTextFieldExtended;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Define a Janela Principal para o Editor/Simulador de Redes de Petri
 * Toda a parte gráfica
 * @author Felipe Lino
 * <BR>Data: 04/01/2007
 * <BR>Atualizado: 20/04/2007
 */
public class MainWindow extends JFrame
implements IntfMainConstants, IntfLanguageConstants,
ActionListener, FocusListener
{
	/**
	 * Variável requisitada na herança de JFrame
	 */
	static final long serialVersionUID = 1;
	
	/**
	 * Rede de Petri
	 */
	public PetriNetGraph pn;
		
	/* Variáveis relacionadas a parte gráfica */
	
	/* Menu */
	private JMenuBar menuBar;
	
	private JMenu 		menu1File;
	public JMenuItem	m1New;
	public JMenuItem	m1Open;
	public JMenuItem	m1Save;
	public JMenuItem	m1SaveAs;
	public JMenuItem	m1Exit;
	
	private JMenu	 	m1Language;
	public JMenuItem 	m1Portugues;
	public JMenuItem 	m1English;
	
	private JMenu 		menu2Simulator;
	public JMenuItem 	m2Start;
	public JMenuItem	m2Stop;
	public JMenuItem	m2Back;
	
	private JMenu		menu3Analisys;
	public JMenuItem	m3Geral;
	
	private JMenu		menu4About;
	public JMenuItem	m4About;
	
	/* Variáveis relacionadas a parte gráfica do painel */
	private Container cp; 
	private DefaultFormBuilder builder;
	private FormLayout layout;
	private CellConstraints cc;
	
	
	/* Botões de Edição */
	public JButton	btEditSeta;
	public JButton	btEditPlace;
	public JButton	btEditTrans;
	public JButton	btEditDelete;
	public JButton	btEditArc;
	public JButton	btEditLabel;
	
	/* Botões do Simulador */
	public JButton 	btSimStart;
	public JButton	btSimStop;
	public JButton	btSimBack;
	
	/* Painel de Informações de Lugar de Rede de Petri*/
	private JLabel	lbPlaceInfo;
	private JLabel	lbPlaceName;
	private JLabel	lbPlaceFichas;
	
	public JTextField	fieldPlaceNome;
	public JSpinner 	spinnerPlaceFichas;
	
	/* Painel de Informações de Transição de Rede de Petri */
	private JLabel		lbTransInfo;
	private JLabel		lbTransName;
	private JLabel		lbTransSeft;
	private JLabel		lbTransSlft;
	private JLabel		lbTransCurvaDensidade;
	
	public JTextField	fieldTransName;
	public JTextField	fieldTransSeft;
	public JTextField	fieldTransSlft;
	public JComboBox	boxTransCurvaDensidade;
	
	/* Painel de Informações de Arco de Rede de Petri */
	private JLabel		lbArcInfo;
	private JLabel		lbArcPlace;
	private JLabel		lbArcTrans;
	private JLabel		lbArcPeso;
	
	public JTextField	fieldArcPlace;
	public JTextField	fieldArcTrans;
	public JSpinner	spinnerArcPeso;
	
	/* Painel de Desenho de Redes de Petri */
	public PetriNetEditorCanvas editor;
	
	/* Barra de Status */
	public JTextFieldExtended fieldStatus;
	
	/* Objetos para tratar eventos da janela principal */
	public ArcActionListener 	arcAction;
	public PlaceActionListener 	placeAction;
	public TransActionListener 	transAction;
	public ButtonActionListener	btAction;
	public MenuActionListener	menuAction;
	public SimulationAction		simAction;
	public AnalyzerAction		anAction;
	
	/**
	 * Construtor da Classe, monta a janela principal.
	 *
	 */
	MainWindow()
	{
		/* Definições de tamanho e posição da janela */
		setSize(775,550);
		setLocation(0,0);
		setResizable(true);
		
		LanguageTool.setLanguageDefault(PROP_LANGUAGE_FILE_DEFAULT);
		
		
		
		LanguageTool.loadPropertiesLanguage();
		this.setTitle(LanguageTool.getString(titleKey));
		
		arcAction 	= new ArcActionListener(this);
		menuAction	= new MenuActionListener(this);
		placeAction	= new PlaceActionListener(this);
		transAction	= new TransActionListener(this);
		btAction	= new ButtonActionListener(this);
		
		montaMenu();
		
		cp = this.getContentPane();
		
		createButtons();
		createPlaceInfoPanel();
		createTransitionPanel();
		createArcPanel();
		createStatusBar();
		createEditorPanel();
		
		cp.add(montaPainel());	
		placeAction.disablePlaceInfo();
		transAction.disableTransitionInfo();
		arcAction.disableArcInfo();
		placeAction.disableSimulationButton();
		
		this.fieldStatus.setTextWithKey(STATUS_MSG_15_KEY);
		
		refreshText();
	}
	
	/**
	 * Monta o Menu da Janela Principal
	 *
	 */
	private void montaMenu()
	{
		LanguageTool.addString(titleKey       , IntfLanguageConstants.title);           
		LanguageTool.addString(menu1FileKey   , IntfLanguageConstants.menu1File);       
		LanguageTool.addString(m1NewKey       , IntfLanguageConstants.m1New);           
		LanguageTool.addString(m1OpenKey      , IntfLanguageConstants.m1Open);          
		LanguageTool.addString(m1SaveKey      , IntfLanguageConstants.m1Save);          
		LanguageTool.addString(m1SaveAsKey    , IntfLanguageConstants.m1SaveAs);        
		LanguageTool.addString(m1PortuguesKey , IntfLanguageConstants.m1Portugues);     
		LanguageTool.addString(m1EnglishKey   , IntfLanguageConstants.m1English);       
		LanguageTool.addString(m1LanguageKey  , IntfLanguageConstants.m1Language);      
		LanguageTool.addString(m1ExitKey      , IntfLanguageConstants.m1Exit);          
		
		LanguageTool.addString(menu2SimulatorKey, IntfLanguageConstants.menu2Simulator);
		LanguageTool.addString(m2StartKey       , IntfLanguageConstants.m2Start);       
		LanguageTool.addString(m2StopKey        , IntfLanguageConstants.m2Stop);        
		LanguageTool.addString(m2BackKey        , IntfLanguageConstants.m2Back);        
		
		LanguageTool.addString(menu3AnalisysKey, IntfLanguageConstants.menu3Analisys);  
		LanguageTool.addString(m3GeralKey      , IntfLanguageConstants.m3Geral);        
		                                         
		LanguageTool.addString(menu4AboutKey, IntfLanguageConstants.menu4About);         
		LanguageTool.addString(m4AboutKey   , IntfLanguageConstants.m4About);            
		
		m1New		= new JMenuItem(LanguageTool.getString(m1NewKey));
		m1New.addActionListener(this);
		m1New.setName(m1NewKey);
		
		m1Open		= new JMenuItem(LanguageTool.getString(m1OpenKey));
		m1Open.addActionListener(this);
		m1Open.setName(m1OpenKey);
		
		m1Save		= new JMenuItem(LanguageTool.getString(m1SaveKey));
		m1Save.addActionListener(this);
		m1Save.setName(m1SaveKey);
		
		m1SaveAs	= new JMenuItem(LanguageTool.getString(m1SaveAsKey));
		m1SaveAs.addActionListener(this);
		m1SaveAs.setName(m1SaveAsKey);
		
		m1Portugues	= new JMenuItem(LanguageTool.getString(m1PortuguesKey));
		m1Portugues.addActionListener(this);
		m1Portugues.setArmed(true);
		m1Portugues.setName(m1PortuguesKey);
		
		m1English	= new JMenuItem(LanguageTool.getString(m1EnglishKey));
		m1English.addActionListener(this);
		m1English.setName(m1EnglishKey);
		
		m1Language	= new JMenu(LanguageTool.getString(m1LanguageKey));
		m1Language.add(m1English);
		m1Language.add(m1Portugues);
		m1Language.addActionListener(this);
		m1Language.setName(m1LanguageKey);
		
		m1Exit		= new JMenuItem(LanguageTool.getString(m1ExitKey));
		m1Exit.addActionListener(this);
		m1Exit.setName(m1ExitKey);
	
		menu1File 	= new JMenu(LanguageTool.getString(menu1FileKey));
		menu1File.setName(menu1FileKey);
		menu1File.addActionListener(this);
		menu1File.add(m1New);
		menu1File.add(m1Open);
		menu1File.add(m1Save);
		menu1File.add(m1SaveAs);
		menu1File.add(m1Language);
		menu1File.addSeparator();
		menu1File.add(m1Exit);
		
		m2Start		= new JMenuItem(LanguageTool.getString(m2StartKey));
		m2Start.addActionListener(this);
		m2Start.setName(m2StartKey);
		
		m2Stop		= new JMenuItem(LanguageTool.getString(m2StopKey));
		m2Stop.addActionListener(this);
		m2Stop.setName(m2StopKey);
		
		m2Back		= new JMenuItem(LanguageTool.getString(m2BackKey));
		m2Back.addActionListener(this);
		m2Back.setName(m2BackKey);
		
		menu2Simulator = new JMenu(LanguageTool.getString(menu2SimulatorKey));
		menu2Simulator.add(m2Start);
		menu2Simulator.add(m2Stop);
		menu2Simulator.add(m2Back);
		menu2Simulator.addActionListener(this);
		menu2Simulator.setName(menu2SimulatorKey);
		
		m3Geral		= new JMenuItem(LanguageTool.getString(m3GeralKey));
		m3Geral.addActionListener(this);
		m3Geral.setName(m3GeralKey);
		
		menu3Analisys = new JMenu(LanguageTool.getString(menu3AnalisysKey));
		menu3Analisys.add(m3Geral);
		menu3Analisys.addActionListener(this);
		menu3Analisys.setName(menu3AnalisysKey);
		
		m4About 	= new JMenuItem(LanguageTool.getString(m4AboutKey));
		m4About.addActionListener(this);
		m4About.setName(m4AboutKey);
		
		menu4About = new JMenu(LanguageTool.getString(menu4AboutKey));
		menu4About.add(m4About);
		menu4About.addActionListener(this);
		menu4About.setName(menu4AboutKey);
							
		menuBar = new JMenuBar();
		menuBar.add(menu1File);
		menuBar.add(menu2Simulator);
		menuBar.add(menu3Analisys);
		menuBar.add(menu4About);
		menuBar.setName(menuBarKey);
		
		this.setJMenuBar(menuBar);
	}
	
	/**
	 * Método que cria os botões da janela principal
	 *
	 */
	private void createButtons()
	{
		btEditLabel		= createJButton(btEditLabelKey  , IntfLanguageConstants.btEditLabel );
		btEditSeta		= createJButton(btEditSetaKey   , IntfLanguageConstants.btEditSeta  );
		btEditDelete	= createJButton(btEditDeleteKey , IntfLanguageConstants.btEditDelete);
		btEditPlace		= createJButton(btEditPlaceKey  , IntfLanguageConstants.btEditPlace );
		btEditTrans		= createJButton(btEditTransKey  , IntfLanguageConstants.btEditTrans );
		btEditArc 		= createJButton(btEditArcKey    , IntfLanguageConstants.btEditArc   );
		btSimStart		= createJButton(btSimStartKey   , IntfLanguageConstants.btSimStart  );
		btSimStop		= createJButton(btSimStopKey    , IntfLanguageConstants.btSimStop   );
		btSimBack		= createJButton(btSimBackKey    , IntfLanguageConstants.btSimBack   );
		
	}
	

	/**
	 * Cria os objetos do painel de informações da Lugar
	 *
	 */
	private void createPlaceInfoPanel()
	{
		lbPlaceInfo		= createJLabelTitle(lbPlaceInfoKey,   IntfLanguageConstants.lbPlaceInfo );
		
		lbPlaceName 	= createJLabelDefault(lbPlaceNameKey,   IntfLanguageConstants.lbPlaceName );
		lbPlaceFichas 	= createJLabelDefault(lbPlaceFichasKey, IntfLanguageConstants.lbPlaceFichas);
		
		fieldPlaceNome	= createJTextFieldDefault(fieldPlaceNomeKey);
						
		SpinnerNumberModel spConfig = new SpinnerNumberModel(0,0,Integer.MAX_VALUE, 1);
		spinnerPlaceFichas = new JSpinner(spConfig);
		spinnerPlaceFichas.setName(spinnerPlaceFichasKey);
		spinnerPlaceFichas.addFocusListener(this);
		
	}
	
	/**
	 * Cria os objetos do painel de informações de Transição
	 *
	 */
	private void createTransitionPanel()
	{
		lbTransInfo = createJLabelTitle(lbTransInfoKey, IntfLanguageConstants.lbTransInfo);
		
		lbTransName = createJLabelDefault(lbTransNameKey, IntfLanguageConstants.lbTransName);
		lbTransSeft = createJLabelDefault(lbTransSeftKey, IntfLanguageConstants.lbTransSeft);
		lbTransSlft = createJLabelDefault(lbTransSlftKey, IntfLanguageConstants.lbTransSlft);
		lbTransCurvaDensidade = createJLabelDefault(lbTransCurvaDensidadeKey, IntfLanguageConstants.lbTransCurvaDensidade);
		
		fieldTransName = createJTextFieldDefault(fieldTransNameKey);
		fieldTransSeft = createJTextFieldDefault(fieldTransSeftKey);
		fieldTransSlft = createJTextFieldDefault(fieldTransSlftKey);
		
		LanguageTool.addString(boxUniformeKey   , IntfLanguageConstants.boxTransDensidadeUniforme);    
		LanguageTool.addString(boxNormalKey     , IntfLanguageConstants.boxTransDensidadeNormal);
		LanguageTool.addString(boxExponencialKey, IntfLanguageConstants.boxTransDensidadeExponencial);
		
		String arrDens[] = new String[3];
		arrDens[0] = LanguageTool.getString(boxUniformeKey);
		arrDens[1] = LanguageTool.getString(boxNormalKey);
		arrDens[2] = LanguageTool.getString(boxExponencialKey);
		
		boxTransCurvaDensidade = new JComboBox(arrDens);
		boxTransCurvaDensidade.setName(boxTransCurvaDensidadeKey);
		boxTransCurvaDensidade.setSelectedIndex(0);
				
	}
	
	/**
	 * Cria os objetos do painel de informações de Arco
	 *
	 */
	private void createArcPanel()
	{
		lbArcInfo = createJLabelTitle(lbArcInfoKey, IntfLanguageConstants.lbArcInfo);
		
		lbArcPlace = createJLabelDefault(lbArcPlaceKey,IntfLanguageConstants.lbArcPlace );
		lbArcTrans = createJLabelDefault(lbArcTransKey,IntfLanguageConstants.lbArcTrans );
		lbArcPeso  = createJLabelDefault(lbArcPesoKey ,IntfLanguageConstants.lbArcPeso  );
		
		fieldArcPlace = createJTextFieldDefault(fieldArcPlaceKey);
		fieldArcTrans = createJTextFieldDefault(fieldArcTransKey);
		
		fieldArcPlace.setEditable(false);		
		fieldArcTrans.setEditable(false);
		
		SpinnerNumberModel spConfig = new SpinnerNumberModel(0,0,Integer.MAX_VALUE, 1);
		spinnerArcPeso = new JSpinner(spConfig);
		spinnerArcPeso.setName(spinnerArcPesoKey);
		spinnerArcPeso.addFocusListener(this);
					
	}
	
	/**
	 * Método que cria a Barra de Status
	 *
	 */
	private void createStatusBar()
	{
		fieldStatus = new JTextFieldExtended(fieldStatusKey);
		
		fieldStatus.setBackground(Color.LIGHT_GRAY);
		fieldStatus.setEditable(false);
	}
	
	/**
	 * Cria os objetos referentes ao Painel de desenho de Redes de Petri
	 *
	 */
	private void createEditorPanel()
	{
		pn = new PetriNetGraph();
		
		editor = new PetriNetEditorCanvas(this);
		editor.addMouseListener(editor);
		
	}
	
	/**
	 * Método que desenha a janela principal
	 * @return Painel com a janela
	 */
	private Component montaPainel()
	{
		
		layout = new FormLayout(
		// Colunas
		"fill:pref," +
		"fill:3dlu," +
		"fill:pref," +
		"fill:3dlu," +
		"fill:pref," +
		"fill:3dlu," +
		"fill:250dlu:grow", 
		// Linhas
		"pref," + 		// 1
		"3dlu," + 		// 2
		"pref," + 		// 3
		"3dlu," + 		// 4
		"pref," +		// 5
		"10dlu," +		// 6
		"pref," +		// 7  - Info Lugar 
		"3dlu," +		// 8 
		"pref," +		// 9
		"3dlu," +		// 10
		"pref," +		// 11
		"10dlu," +		// 12
		"pref,"+		// 13 - Info Trans
		"3dlu," +		// 14
		"pref," +		// 15
		"3dlu," +		// 16
		"pref," +		// 17
		"3dlu," +		// 18
		"pref," +		// 19
		"3dlu," +		// 20
		"pref," +		// 21
		"10dlu," +		// 22
		"pref," +		// 23 - Info Arco
		"3dlu," +		// 24
		"pref," +		// 25
		"3dlu," +		// 26
		"pref," +		// 27
		"3dlu," +		// 28
		"pref," +		// 29
		"pref:grow," +	// 30
		"3dlu," +		// 31
		"pref"			// 32 - Barra Status
		);
		
		cc = new CellConstraints();
		
		//builder = new DefaultFormBuilder(layout, new FormDebugPanel());
		builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();
		
		// Coluna , Linha, Celulas
		// Adicionando Botoes
		builder.add(btEditLabel,  cc.xyw(1,1,1));
		builder.add(btEditSeta,   cc.xyw(3,1,1));
		builder.add(btEditPlace,  cc.xyw(1,3,1));
		builder.add(btEditTrans,  cc.xyw(3,3,1));
		builder.add(btEditArc,	  cc.xyw(5,3,1));
		builder.add(btEditDelete, cc.xyw(5,1,1));
		builder.add(btSimStart,   cc.xyw(1,5,1));
		builder.add(btSimStop,    cc.xyw(3,5,1));
		builder.add(btSimBack,    cc.xyw(5,5,1));
		
		// Adicionando Painel de Lugar
		builder.add(lbPlaceInfo,        cc.xyw(1,7 ,5));
		builder.add(lbPlaceName,        cc.xyw(1,9 ,1));
		builder.add(lbPlaceFichas,      cc.xyw(1,11,1));
		builder.add(fieldPlaceNome,     cc.xyw(3,9 ,3));
		builder.add(spinnerPlaceFichas, cc.xyw(3,11,3));
		
		// Adicionando Painel de Transição
		builder.add(lbTransInfo, 			cc.xyw(1,13,5));
		builder.add(lbTransName, 			cc.xyw(1,15,1));
		builder.add(lbTransSeft,			cc.xyw(1,17,1));
		builder.add(lbTransSlft, 			cc.xyw(1,19,1));
		builder.add(lbTransCurvaDensidade, 	cc.xyw(1,21,1));
		builder.add(fieldTransName,			cc.xyw(3,15,3));
		builder.add(fieldTransSeft, 		cc.xyw(3,17,3));
		builder.add(fieldTransSlft, 		cc.xyw(3,19,3));
		builder.add(boxTransCurvaDensidade, cc.xyw(3,21,3));
		
		// Adicionando Painel de Arco
		builder.add(lbArcInfo,			cc.xyw(1,23,5));
		builder.add(lbArcPlace, 		cc.xyw(1,25,1));
		builder.add(lbArcTrans,			cc.xyw(1,27,1));
		builder.add(lbArcPeso,			cc.xyw(1,29,1));
		builder.add(fieldArcPlace, 		cc.xyw(3,25,3));
		builder.add(fieldArcTrans,  	cc.xyw(3,27,3));
		builder.add(spinnerArcPeso, 	cc.xyw(3,29,3));
		
		JScrollPane scroll = new JScrollPane(editor);
		//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		builder.add(scroll, cc.xywh(7,1,1,30));
		
		builder.add(fieldStatus,		cc.xyw(1,32,7));
		
		return builder.getPanel();
	}
	
	/**
	 * Atualiza os rótulos de todos os textos que aparecem na Janela Principal.
	 *
	 */
	public void refreshText()
	{
		String strTitle = LanguageTool.getString(titleKey);
		int pos = this.getTitle().lastIndexOf('#');
		if(pos > -1)
		{
			strTitle += " " + this.getTitle().substring(pos); 
		}
		this.setTitle(strTitle);
		
		menu1File.setText(LanguageTool.getString(menu1FileKey));
		menu2Simulator.setText(LanguageTool.getString(menu2SimulatorKey));
		menu3Analisys.setText(LanguageTool.getString(menu3AnalisysKey));
		menu4About.setText(LanguageTool.getString(menu4AboutKey));
		
		m1New.setText(LanguageTool.getString(m1NewKey));
		m1Open.setText(LanguageTool.getString(m1OpenKey));
		m1Save.setText(LanguageTool.getString(m1SaveKey));
		m1SaveAs.setText(LanguageTool.getString(m1SaveAsKey));
		m1Language.setText(LanguageTool.getString(m1LanguageKey));
		m1Portugues.setText(LanguageTool.getString(m1PortuguesKey));
		m1English.setText(LanguageTool.getString(m1EnglishKey));
		m1Exit.setText(LanguageTool.getString(m1ExitKey));
		
		m2Start.setText(LanguageTool.getString(m2StartKey));
		m2Stop.setText(LanguageTool.getString(m2StopKey));
		m2Back.setText(LanguageTool.getString(m2BackKey));
		
		m3Geral.setText(LanguageTool.getString(m3GeralKey));
		
		m4About.setText(LanguageTool.getString(m4AboutKey));
		
		setNameButon(btEditDelete);
		setNameButon(btEditPlace);
		setNameButon(btEditSeta);
		setNameButon(btEditTrans);
		setNameButon(btEditArc);
		setNameButon(btEditLabel);
		setNameButon(btSimBack);
		setNameButon(btSimStart);
		setNameButon(btSimStop);
		
		setNameJLabel(lbArcInfo);
		setNameJLabel(lbArcPeso);
		setNameJLabel(lbArcPlace);
		setNameJLabel(lbArcTrans);
		setNameJLabel(lbPlaceFichas);
		setNameJLabel(lbPlaceInfo);
		setNameJLabel(lbPlaceName);
		setNameJLabel(lbTransCurvaDensidade);
		setNameJLabel(lbTransInfo);
		setNameJLabel(lbTransName);
		setNameJLabel(lbTransSeft);
		setNameJLabel(lbTransSlft);
				
		String arrDens[] = new String[3];
		arrDens[0] = LanguageTool.getString(boxUniformeKey);
		arrDens[1] = LanguageTool.getString(boxNormalKey);
		arrDens[2] = LanguageTool.getString(boxExponencialKey);
		
		int boxSelected = boxTransCurvaDensidade.getSelectedIndex();
		boxTransCurvaDensidade.removeAllItems();
		boxTransCurvaDensidade.insertItemAt(arrDens[0], 0);
		boxTransCurvaDensidade.insertItemAt(arrDens[1], 1);
		boxTransCurvaDensidade.insertItemAt(arrDens[2], 2);
		boxTransCurvaDensidade.setSelectedIndex(boxSelected);
		
		fieldStatus.setTextWithKey();
		if(simAction != null)
			simAction.refreshText();
		
		if(anAction != null)
			anAction.refreshText();
	}
	
	/**
	 * Seta o rótulo do Botão recebido como parâmetro.
	 * @param bt Botão
	 */
	private void setNameButon(JButton bt)
	{
		String key = bt.getName();
		bt.setText(LanguageTool.getString(key));
	}
	
	/**
	 * Seta o rótulo do Label recebido como parâmetro.
	 * @param lb Rótulo
	 */
	private void setNameJLabel(JLabel lb)
	{
		String key = lb.getName();
		lb.setText(LanguageTool.getString(key));
	}
	
	/**
	 * Cria um JLabel com configuração default.
	 * @param strKey Chave a ser usada pelo JLabel
	 * @return Instância de JLabel
	 */
	public static JLabel createJLabelDefault(String strKey, String strLabel)
	{
		JLabel lb = new JLabel(strLabel);
		lb.setName(strKey);
		lb.setHorizontalAlignment(JLabel.RIGHT);
		lb.setHorizontalTextPosition(JLabel.RIGHT);
		
		LanguageTool.addString(strKey, strLabel);
		
		return lb;
	}
	
	/**
	 * Cria um JLabel com configuração de default para título.
	 * @param strKey Chave a ser usada pelo JLabel
	 * @return Instãncia de JLabel
	 */
	public static JLabel createJLabelTitle(String strKey, String strLabel)
	{
		JLabel lb = new JLabel(strLabel);
		lb.setName(strKey);
		lb.setHorizontalAlignment(JLabel.CENTER);
		lb.setHorizontalTextPosition(JLabel.CENTER);
		lb.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		
		LanguageTool.addString(strKey, strLabel);
		
		return lb;
	}
	
	/**
	 * Cria JTextField com configuração default.
	 * @param strKey Chave a ser usada pelo JTextField
	 * @return Instância de JTextField
	 */
	public JTextField createJTextFieldDefault(String strKey)
	{
		JTextField field = new JTextField();
		field.setName(strKey);
		field.addActionListener(this);
		field.addFocusListener(this);
		
		return field;
	}
	
	/**
	 * Cria um JButton com a chave passada como parâmetro e o
	 * adiciona a uma Map.
	 * @param strKey Chave a ser usada no botão criado
	 * @param strLabel Rótulo default a ser usado no botão
	 */
	private JButton createJButton(String strKey,String strLabel)
	{
		JButton bt = new JButton();
		bt.setName(strKey);
		bt.setText(strLabel);
		bt.addActionListener(this);
	
		LanguageTool.addString(strKey , strLabel);
	
		return bt;
	}
	
	/**
	 * Implementação da Interface ActionListener. Responsável
	 * por controlar os eventos dos objetos da janela principal.
	 *  
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 * @param evt Evento
	 */
	public void actionPerformed(ActionEvent evt)
	{
		Object source = evt.getSource();
		if(source instanceof JMenuItem)
		{
			menuAction.performed((JMenuItem) source);
		}
		else if(source instanceof JButton)
		{			
			btAction.performed((JButton) source);
		}
				
	}
	
	/**
	 * Implementa a interface FocusListener. Responsável por tratar eventos
	 * de ganho de foco em um objeto.
	 * 
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 * 
	 * @param evt 
	 */
	public void focusGained(FocusEvent evt)
	{
		
	}
	
	/**
	 * Implementa a interface FocusListener. Responsável por tratar eventos
	 * de perda de foco em um objeto.
	 * 
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 * 
	 * @param evt 
	 */
	public void focusLost(FocusEvent evt)
	{
		Object source = evt.getSource();
		if(evt.getSource() == spinnerPlaceFichas)
		{
			placeAction.focusLostPerformed();
		}
		if(source instanceof Component)
		{
			Component src = (Component) source;
			if(src.getName().contains("Arc"))
			{
				arcAction.focusLostPerformed();
			}
			else if(src.getName().contains("Place"))
			{
				placeAction.focusLostPerformed();
			}
			else if(src.getName().contains("Trans"))
			{
				transAction.focusLostPerformed();
			}
			
		}
	
	}	
}
