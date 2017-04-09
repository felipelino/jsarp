package br.uerj.petrinetanalyzer.gui.interfaces;

/**
 * Define String constantes que serão exibidas na tela
 * para o Editor/Simulador de Redes de Petri
 * 
 * @author Felipe Lino
 * <BR>Data: 04/01/2007
 * <BR>Atualizado: 20/04/2007
 */
public interface IntfLanguageConstants
{
	public static final String menuBarKey="menuBar";
	
	public static final String titleKey="title";
	public static final String title="JSARP - Simulador/Analisador de Redes de Petri";
	
	public static final String menu1FileKey="menu1File";
	public static final String menu1File="Arquivo";
	
	public static final String m1NewKey="m1New";
	public static final String m1New="Novo";
	
	public static final String m1OpenKey="m1Open";
	public static final String m1Open="Abrir";
		
	public static final String m1SaveKey="m1Save";
	public static final String m1Save="Salvar";
	
	public static final String m1SaveAsKey="m1SaveAs";
	public static final String m1SaveAs="Salvar Como";
	
	public static final String m1PortuguesKey="m1Portugues";
	public static final String m1Portugues="Portugues";
	
	public static final String m1EnglishKey="m1English";
	public static final String m1English="Inglês";
		
	public static final String m1LanguageKey="m1Language";
	public static final String m1Language="Idioma";
	
	public static final String	m1ExitKey="m1Exit";
	public static final String	m1Exit="Sair";
	
	public static final String 	menu2SimulatorKey="menu2Simulator";
	public static final String 	menu2Simulator="Simulação";
	
	public static final String 	m2StartKey="m2Start";
	public static final String 	m2Start="Iniciar";
	
	public static final String	m2StopKey="m2Stop";
	public static final String	m2Stop="Parar";
	
	public static final String	m2BackKey="m2Back";
	public static final String	m2Back="Voltar";
	
	public static final String  menu3AnalisysKey="menu3Analisys";
	public static final String  menu3Analisys="Análise";
	
	public static final String	m3GeralKey="m3Geral";
	public static final String	m3Geral="Análise Geral";
	
	public static final String	menu4AboutKey="menu4About";
	public static final String	menu4About = "Ajuda";
	
	public static final String	m4AboutKey = "m4About";
	public static final String	m4About = "Sobre";
	
	
	public static final String btEditSetaKey="btEditSeta";
	public static final String btEditSeta="Mover";
	
	public static final String btEditPlaceKey="btEditPlace";
	public static final String btEditPlace="Lugar";
	
	public static final String btEditTransKey="btEditTrans";
	public static final String btEditTrans="Trans";
	
	public static final String btEditDeleteKey="btEditDelete";
	public static final String btEditDelete="Apagar";
	
	public static final String btEditArcKey="btEditArc";
	public static final String btEditArc="Arco";
	
	public static final String btEditLabelKey="btEditLabel";
	public static final String btEditLabel="Rótulo";
	
	public static final String btSimStartKey="btSimStart";
	public static final String btSimStart="Iniciar";
	
	public static final String btSimStopKey="btSimStop";
	public static final String btSimStop="Parar";
	
	public static final String btSimBackKey="btSimBack";
	public static final String btSimBack="Voltar";

	public static final String lbPlaceNameKey="lbPlaceName";
	public static final String lbPlaceName="Nome:";
	
	public static final String lbPlaceFichasKey="lbPlaceFichas";
	public static final String lbPlaceFichas="Fichas:";
	
	public static final String fieldPlaceNomeKey="fieldPlaceNome";
	
	public static final String spinnerPlaceFichasKey="spinnerPlaceFichas";
	
	public static final String lbTransNameKey="lbTransName";
	public static final String lbTransName="Nome:";
	
	public static final String lbTransSeftKey="lbTransSeft";
	public static final String lbTransSeft="SEFT:";
	
	public static final String lbTransSlftKey="lbTransSlft";
	public static final String lbTransSlft="SLFT:";
	
	public static final String lbTransCurvaDensidadeKey="lbTransCurvaDensidade";
	public static final String lbTransCurvaDensidade="Curva Dens.:";
	
	public static final String fieldTransNameKey="fieldTransName";
	
	public static final String fieldTransSeftKey="fieldTransSeft";
	
	public static final String fieldTransSlftKey="fieldTransSlft";
	
	public static final String boxTransCurvaDensidadeKey="boxTransCurvaDensidade";

	public static final String boxUniformeKey="boxTransDensidadeUniforme";
	public static final String boxTransDensidadeUniforme="Uniforme";
	
	public static final String boxNormalKey="boxTransDensidadeNormal";
	public static final String boxTransDensidadeNormal="Normal";
	
	public static final String boxExponencialKey="boxTransDensidadeExponencial";
	public static final String boxTransDensidadeExponencial="Exponencial";
	
	public static final String lbArcPlaceKey="lbArcPlace";
	public static final String lbArcPlace="Lugar:";
	
	public static final String lbArcTransKey="lbArcTrans";
	public static final String lbArcTrans="Trans.:";
	
	public static final String lbArcPesoKey="lbArcPeso";
	public static final String lbArcPeso="Peso:";
	
	public static final String fieldArcPlaceKey="fieldArcPlace";
	
	public static final String fieldArcTransKey="fieldArcTrans";
	
	public static final String spinnerArcPesoKey="spinnerArcPeso";
	
	public static final String lbPlaceInfoKey="lbPlaceInfo";
	public static final String lbPlaceInfo="Informações de Lugar";
	
	public static final String lbTransInfoKey="lbTransInfo";
	public static final String lbTransInfo="Informações de Transição";
	
	public static final String lbArcInfoKey="lbArcInfo";
	public static final String lbArcInfo="Informações de Arco";
	
	public static final String fieldStatusKey="fieldStatus";
	
	/* Textos que aparecem na barra de Status */
	public static final String STATUS_MSG_01_KEY = "STATUS_MSG_01";
	public static final String STATUS_MSG_01 = "Editar/Adicionar Lugar";
	
	public static final String STATUS_MSG_02_KEY = "STATUS_MSG_02";
	public static final String STATUS_MSG_02 = "Editar/Adicionar Transição";
	
	public static final String STATUS_MSG_03_KEY = "STATUS_MSG_03";
	public static final String STATUS_MSG_03 = "Edtar/Adicionar Arco";
	
	public static final String STATUS_MSG_04_KEY = "STATUS_MSG_04";
	public static final String STATUS_MSG_04 = "Mover Objeto";
	
	public static final String STATUS_MSG_05_KEY = "STATUS_MSG_05";
	public static final String STATUS_MSG_05 = "Remover Objeto";
	
	public static final String STATUS_MSG_06_KEY = "STATUS_MSG_06";
	public static final String STATUS_MSG_06 = "Editar Lugar";
	
	public static final String STATUS_MSG_07_KEY = "STATUS_MSG_07";
	public static final String STATUS_MSG_07 = "Editar Transição";
	
	public static final String STATUS_MSG_08_KEY = "STATUS_MSG_08";
	public static final String STATUS_MSG_08 = "Editar Arco";
	
	public static final String STATUS_MSG_09_KEY = "STATUS_MSG_09";
	public static final String STATUS_MSG_09 = "Modo de simulação";
	
	public static final String STATUS_MSG_10_KEY = "STATUS_MSG_10";
	public static final String STATUS_MSG_10 = "Selecionar Objeto";
	
	public static final String STATUS_MSG_11_KEY = "STATUS_MSG_11";
	public static final String STATUS_MSG_11 = "Salvando arquivo";
	
	public static final String STATUS_MSG_12_KEY = "STATUS_MSG_12";
	public static final String STATUS_MSG_12 = "Arquivo salvo com sucesso";
	
	public static final String STATUS_MSG_13_KEY = "STATUS_MSG_13";
	public static final String STATUS_MSG_13 = "Abrindo arquivo";
	
	public static final String STATUS_MSG_14_KEY = "STATUS_MSG_14";
	public static final String STATUS_MSG_14 = "Arquivo aberto";
	
	public static final String STATUS_MSG_15_KEY = "STATUS_MSG_15";
	public static final String STATUS_MSG_15 = "Nova rede de petri";
	
	public static final String STATUS_MSG_16_KEY = "STATUS_MSG_16";
	public static final String STATUS_MSG_16 = "Modo de simulação";
	
	public static final String STATUS_MSG_17_KEY = "STATUS_MSG_17";
	public static final String STATUS_MSG_17 = "Modo de edição";
	
	public static final String STATUS_MSG_18_KEY = "STATUS_MSG_18";
	public static final String STATUS_MSG_18 = "Voltou para o estado selecionado";
	
	public static final String STATUS_MSG_19_KEY = "STATUS_MSG_19";
	public static final String STATUS_MSG_19 = "Editar Rótulo";
	
	/* Mensagens de Erro */
	public static final String ERROR_MSG_01_KEY = "ERROR_MSG_01";
	public static final String ERROR_MSG_01 = "Erro ao tentar gravar o arquivo";
	
	public static final String ERROR_MSG_02_KEY = "ERROR_MSG_02";
	public static final String ERROR_MSG_02 = "Erro de entrada e saída";
	
	public static final String ERROR_MSG_03_KEY = "ERROR_MSG_03";
	public static final String ERROR_MSG_03 = "Erro ao tentar ler o arquivo";
	
	public static final String ERROR_MSG_04_KEY = "ERROR_MSG_04_KEY";
	public static final String ERROR_MSG_04 = "O arquivo xml especificado não pode ser convertido em rede de petri";
	
	/* Mensagem Sobre */
	public static final String ABOUT_MSG_01_KEY = "ABOUT_MSG_01"; 
	public static final String ABOUT_MSG_01 = "\"Se, porém, algum de vós necessita de sabedoria, " + "\n" +
											"peça-a a Deus, que a todos dá liberalmente," + "\n" +
											"e nada lhes impropera, e ser-lhe-á concedida\"" +
										    "\n(Tiago 1:5)";
	
	public static final String ABOUT_MSG_02_KEY = "ABOUT_MSG_02";
	public static final String ABOUT_MSG_02 = "JSARP v1.1" + "\n" +
											"Autor: Felipe Lino IME/UERJ" + "\n" +
											"e-mail: felipelino44@gmail.com" + "\n" +
											"Rio de Janeiro, 17 de Novembro de 2007";
	
	public static final String ABOUT_MSG_03_KEY = "ABOUT_MSG_03";
	public static final String ABOUT_MSG_03 = "Sobre JSARP";
}
