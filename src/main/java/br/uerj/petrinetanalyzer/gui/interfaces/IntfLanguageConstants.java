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
	String menuBarKey="menuBar";
	
	String titleKey="title";
	String title="JSARP - Simulador/Analisador de Redes de Petri";
	
	String menu1FileKey="menu1File";
	String menu1File="Arquivo";
	
	String m1NewKey="m1New";
	String m1New="Novo";
	
	String m1OpenKey="m1Open";
	String m1Open="Abrir";
		
	String m1SaveKey="m1Save";
	String m1Save="Salvar";
	
	String m1SaveAsKey="m1SaveAs";
	String m1SaveAs="Salvar Como";
	
	String m1PortuguesKey="m1Portugues";
	String m1Portugues="Portugues";
	
	String m1EnglishKey="m1English";
	String m1English="Inglês";
		
	String m1LanguageKey="m1Language";
	String m1Language="Idioma";
	
	String	m1ExitKey="m1Exit";
	String	m1Exit="Sair";
	
	String 	menu2SimulatorKey="menu2Simulator";
	String 	menu2Simulator="Simulação";
	
	String 	m2StartKey="m2Start";
	String 	m2Start="Iniciar";
	
	String	m2StopKey="m2Stop";
	String	m2Stop="Parar";
	
	String	m2BackKey="m2Back";
	String	m2Back="Voltar";
	
	String  menu3AnalisysKey="menu3Analisys";
	String  menu3Analisys="Análise";
	
	String	m3GeralKey="m3Geral";
	String	m3Geral="Análise Geral";
	
	String	menu4AboutKey="menu4About";
	String	menu4About = "Ajuda";
	
	String	m4AboutKey = "m4About";
	String	m4About = "Sobre";
	
	
	String btEditSetaKey="btEditSeta";
	String btEditSeta="Mover";
	
	String btEditPlaceKey="btEditPlace";
	String btEditPlace="Lugar";
	
	String btEditTransKey="btEditTrans";
	String btEditTrans="Trans";
	
	String btEditDeleteKey="btEditDelete";
	String btEditDelete="Apagar";
	
	String btEditArcKey="btEditArc";
	String btEditArc="Arco";
	
	String btEditLabelKey="btEditLabel";
	String btEditLabel="Rótulo";
	
	String btSimStartKey="btSimStart";
	String btSimStart="Iniciar";
	
	String btSimStopKey="btSimStop";
	String btSimStop="Parar";
	
	String btSimBackKey="btSimBack";
	String btSimBack="Voltar";

	String lbPlaceNameKey="lbPlaceName";
	String lbPlaceName="Nome:";
	
	String lbPlaceFichasKey="lbPlaceFichas";
	String lbPlaceFichas="Fichas:";
	
	String fieldPlaceNomeKey="fieldPlaceNome";
	
	String spinnerPlaceFichasKey="spinnerPlaceFichas";
	
	String lbTransNameKey="lbTransName";
	String lbTransName="Nome:";
	
	String lbTransSeftKey="lbTransSeft";
	String lbTransSeft="SEFT:";
	
	String lbTransSlftKey="lbTransSlft";
	String lbTransSlft="SLFT:";
	
	String lbTransCurvaDensidadeKey="lbTransCurvaDensidade";
	String lbTransCurvaDensidade="Curva Dens.:";
	
	String fieldTransNameKey="fieldTransName";
	
	String fieldTransSeftKey="fieldTransSeft";
	
	String fieldTransSlftKey="fieldTransSlft";
	
	String boxTransCurvaDensidadeKey="boxTransCurvaDensidade";

	String boxUniformeKey="boxTransDensidadeUniforme";
	String boxTransDensidadeUniforme="Uniforme";
	
	String boxNormalKey="boxTransDensidadeNormal";
	String boxTransDensidadeNormal="Normal";
	
	String boxExponencialKey="boxTransDensidadeExponencial";
	String boxTransDensidadeExponencial="Exponencial";
	
	String lbArcPlaceKey="lbArcPlace";
	String lbArcPlace="Lugar:";
	
	String lbArcTransKey="lbArcTrans";
	String lbArcTrans="Trans.:";
	
	String lbArcPesoKey="lbArcPeso";
	String lbArcPeso="Peso:";
	
	String fieldArcPlaceKey="fieldArcPlace";
	
	String fieldArcTransKey="fieldArcTrans";
	
	String spinnerArcPesoKey="spinnerArcPeso";
	
	String lbPlaceInfoKey="lbPlaceInfo";
	String lbPlaceInfo="Informações de Lugar";
	
	String lbTransInfoKey="lbTransInfo";
	String lbTransInfo="Informações de Transição";
	
	String lbArcInfoKey="lbArcInfo";
	String lbArcInfo="Informações de Arco";
	
	String fieldStatusKey="fieldStatus";
	
	/* Textos que aparecem na barra de Status */
	String STATUS_MSG_01_KEY = "STATUS_MSG_01";
	String STATUS_MSG_01 = "Editar/Adicionar Lugar";
	
	String STATUS_MSG_02_KEY = "STATUS_MSG_02";
	String STATUS_MSG_02 = "Editar/Adicionar Transição";
	
	String STATUS_MSG_03_KEY = "STATUS_MSG_03";
	String STATUS_MSG_03 = "Edtar/Adicionar Arco";
	
	String STATUS_MSG_04_KEY = "STATUS_MSG_04";
	String STATUS_MSG_04 = "Mover Objeto";
	
	String STATUS_MSG_05_KEY = "STATUS_MSG_05";
	String STATUS_MSG_05 = "Remover Objeto";
	
	String STATUS_MSG_06_KEY = "STATUS_MSG_06";
	String STATUS_MSG_06 = "Editar Lugar";
	
	String STATUS_MSG_07_KEY = "STATUS_MSG_07";
	String STATUS_MSG_07 = "Editar Transição";
	
	String STATUS_MSG_08_KEY = "STATUS_MSG_08";
	String STATUS_MSG_08 = "Editar Arco";
	
	String STATUS_MSG_09_KEY = "STATUS_MSG_09";
	String STATUS_MSG_09 = "Modo de simulação";
	
	String STATUS_MSG_10_KEY = "STATUS_MSG_10";
	String STATUS_MSG_10 = "Selecionar Objeto";
	
	String STATUS_MSG_11_KEY = "STATUS_MSG_11";
	String STATUS_MSG_11 = "Salvando arquivo";
	
	String STATUS_MSG_12_KEY = "STATUS_MSG_12";
	String STATUS_MSG_12 = "Arquivo salvo com sucesso";
	
	String STATUS_MSG_13_KEY = "STATUS_MSG_13";
	String STATUS_MSG_13 = "Abrindo arquivo";
	
	String STATUS_MSG_14_KEY = "STATUS_MSG_14";
	String STATUS_MSG_14 = "Arquivo aberto";
	
	String STATUS_MSG_15_KEY = "STATUS_MSG_15";
	String STATUS_MSG_15 = "Nova rede de petri";
	
	String STATUS_MSG_16_KEY = "STATUS_MSG_16";
	String STATUS_MSG_16 = "Modo de simulação";
	
	String STATUS_MSG_17_KEY = "STATUS_MSG_17";
	String STATUS_MSG_17 = "Modo de edição";
	
	String STATUS_MSG_18_KEY = "STATUS_MSG_18";
	String STATUS_MSG_18 = "Voltou para o estado selecionado";
	
	String STATUS_MSG_19_KEY = "STATUS_MSG_19";
	String STATUS_MSG_19 = "Editar Rótulo";
	
	/* Mensagens de Erro */
	String ERROR_MSG_01_KEY = "ERROR_MSG_01";
	String ERROR_MSG_01 = "Erro ao tentar gravar o arquivo";
	
	String ERROR_MSG_02_KEY = "ERROR_MSG_02";
	String ERROR_MSG_02 = "Erro de entrada e saída";
	
	String ERROR_MSG_03_KEY = "ERROR_MSG_03";
	String ERROR_MSG_03 = "Erro ao tentar ler o arquivo";
	
	String ERROR_MSG_04_KEY = "ERROR_MSG_04_KEY";
	String ERROR_MSG_04 = "O arquivo xml especificado não pode ser convertido em rede de petri";
	
	/* Mensagem Sobre */
	String ABOUT_MSG_01_KEY = "ABOUT_MSG_01"; 
	String ABOUT_MSG_01 = "\"Se, porém, algum de vós necessita de sabedoria, " + "\n" +
											"peça-a a Deus, que a todos dá liberalmente," + "\n" +
											"e nada lhes impropera, e ser-lhe-á concedida\"" +
										    "\n(Tiago 1:5)";
	
	String ABOUT_MSG_02_KEY = "ABOUT_MSG_02";
	String ABOUT_MSG_02 = "JSARP v1.1" + "\n" +
											"Autor: Felipe Lino IME/UERJ" + "\n" +
											"e-mail: felipelino44@gmail.com" + "\n" +
											"Rio de Janeiro, 17 de Novembro de 2007";
	
	String ABOUT_MSG_03_KEY = "ABOUT_MSG_03";
	String ABOUT_MSG_03 = "Sobre JSARP";
}
