package br.uerj.petrinetanalyzer.gui.interfaces;

/**
 * Define String constantes que ser�o exibidas na tela
 * para o Editor/Simulador de Redes de Petri.
 * Apenas para a Janela com informa��es da An�lise.
 * 
 * @author Felipe Lino
 * <BR>Data: 26/02/2007
 * <BR>Atualizado: 12/10/2007
 */
public interface IntfLanguageAnalyzerConstants
{
	/* Campos da janela com a sequencia de disparos */
	public static final String anWindowKey="anWindow";
	public static final String anWindow="Informa��es da An�lise/Simula��o";
		
	public static final String lbAnalyzerTreeKey="lbAnalyzerTree";
	public static final String lbAnalyzerTree="Seq��ncia de disparos";
	
	public static final String lbStateInfoKey="lbStateInfo";
	public static final String lbStateInfo="Descri��o do Estado";

	public static final String lbPnInfoKey="lbPnInfo";
	public static final String lbPnInfo="Propriedades da Rede de Petri";
	
	
	public static final String AN_INFO_01_KEY="AN_INFO_01";
	public static final String AN_INFO_01 = "Seq��ncia de disparos:";
	
	public static final String AN_INFO_02_KEY="AN_INFO_02";
	public static final String AN_INFO_02="Marca��es dos estados:";
	
	public static final String AN_INFO_03_KEY="AN_INFO_03";
	public static final String AN_INFO_03="Transi��es dispon�veis:";
	
	public static final String AN_INFO_04_KEY="AN_INFO_04";
	public static final String AN_INFO_04="N�o h� transi��es habilitadas para disparo.";
	
	public static final String AN_INFO_05_KEY="AN_INFO_05";
	public static final String AN_INFO_05="Estado inicial";
	
	public static final String AN_INFO_06_KEY="AN_INFO_06";
	public static final String AN_INFO_06="Estado duplicado. Igual ao estado:";
	
	public static final String PROP_INFO_01_KEY="PROP_INFO_01";
	public static final String PROP_INFO_01="A rede � viva.";
	
	public static final String PROP_INFO_02_KEY="PROP_INFO_02";
	public static final String PROP_INFO_02="A rede n�o � viva.";
	
	public static final String PROP_INFO_03_KEY="PROP_INFO_03";
	public static final String PROP_INFO_03="A rede � limitada.";
	
	public static final String PROP_INFO_04_KEY="PROP_INFO_04";
	public static final String PROP_INFO_04="A rede n�o � limitada.";
	
	public static final String PROP_INFO_05_KEY="PROP_INFO_05";
	public static final String PROP_INFO_05="Limite da rede igual �";
	
	public static final String PROP_INFO_06_KEY="PROP_INFO_06";
	public static final String PROP_INFO_06="A rede � conservativa.";
	
	public static final String PROP_INFO_07_KEY="PROP_INFO_07";
	public static final String PROP_INFO_07="A rede n�o � conservativa.";
	
	public static final String PROP_INFO_08_KEY="PROP_INFO_08";
	public static final String PROP_INFO_08="Seq��ncia de disparos que levam a deadlock:";
	
	public static final String PROP_INFO_09_KEY="PROP_INFO_09";
	public static final String PROP_INFO_09="Seq��ncia";

	public static final String PROP_INFO_10_KEY="PROP_INFO_10";
	public static final String PROP_INFO_10="Nome da Rede de Petri:";
	
	public static final String PROP_INFO_11_KEY="PROP_INFO_11";
	public static final String PROP_INFO_11="Total de fichas da rede:";
	
	public static final String PROP_INFO_12_KEY="PROP_INFO_12";
	public static final String PROP_INFO_12="Leva ao estado:";
	
	public static final String PROP_INFO_13_KEY="PROP_INFO_13";
	public static final String PROP_INFO_13="Matriz de Entrada:";
	
	public static final String PROP_INFO_14_KEY="PROP_INFO_14";
	public static final String PROP_INFO_14="Matriz de Sa�da:";
	
	public static final String PROP_INFO_15_KEY="PROP_INFO_15";
	public static final String PROP_INFO_15="Matriz de Incid�ncia:";
	
	public static final String AN_ERROR_01_KEY="AN_ERROR_01";
	public static final String AN_ERROR_01="A Rede de Petri � nula";
	
	public static final String AN_ERROR_02_KEY="AN_ERROR_02";
	public static final String AN_ERROR_02="A Rede de Petri n�o possui lugar";
	
	public static final String AN_ERROR_03_KEY="AN_ERROR_03";
	public static final String AN_ERROR_03="A Rede de Petri n�o possui transi��o";
	
	public static final String AN_ERROR_04_KEY="AN_ERROR_04";
	public static final String AN_ERROR_04="A Rede de Petri n�o posssui arco";
	
	public static final String AN_ERROR_TITLE_KEY="AN_ERROR_TITLE";
	public static final String AN_ERROR_TITLE="Erro ao Analisar Rede";
	
	
	
}
