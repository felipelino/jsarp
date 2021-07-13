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
	String anWindowKey="anWindow";
	String anWindow="Informa��es da An�lise/Simula��o";
		
	String lbAnalyzerTreeKey="lbAnalyzerTree";
	String lbAnalyzerTree="Seq��ncia de disparos";
	
	String lbStateInfoKey="lbStateInfo";
	String lbStateInfo="Descri��o do Estado";

	String lbPnInfoKey="lbPnInfo";
	String lbPnInfo="Propriedades da Rede de Petri";
	
	
	String AN_INFO_01_KEY="AN_INFO_01";
	String AN_INFO_01 = "Seq��ncia de disparos:";
	
	String AN_INFO_02_KEY="AN_INFO_02";
	String AN_INFO_02="Marca��es dos estados:";
	
	String AN_INFO_03_KEY="AN_INFO_03";
	String AN_INFO_03="Transi��es dispon�veis:";
	
	String AN_INFO_04_KEY="AN_INFO_04";
	String AN_INFO_04="N�o h� transi��es habilitadas para disparo.";
	
	String AN_INFO_05_KEY="AN_INFO_05";
	String AN_INFO_05="Estado inicial";
	
	String AN_INFO_06_KEY="AN_INFO_06";
	String AN_INFO_06="Estado duplicado. Igual ao estado:";
	
	String PROP_INFO_01_KEY="PROP_INFO_01";
	String PROP_INFO_01="A rede � viva.";
	
	String PROP_INFO_02_KEY="PROP_INFO_02";
	String PROP_INFO_02="A rede n�o � viva.";
	
	String PROP_INFO_03_KEY="PROP_INFO_03";
	String PROP_INFO_03="A rede � limitada.";
	
	String PROP_INFO_04_KEY="PROP_INFO_04";
	String PROP_INFO_04="A rede n�o � limitada.";
	
	String PROP_INFO_05_KEY="PROP_INFO_05";
	String PROP_INFO_05="Limite da rede igual �";
	
	String PROP_INFO_06_KEY="PROP_INFO_06";
	String PROP_INFO_06="A rede � conservativa.";
	
	String PROP_INFO_07_KEY="PROP_INFO_07";
	String PROP_INFO_07="A rede n�o � conservativa.";
	
	String PROP_INFO_08_KEY="PROP_INFO_08";
	String PROP_INFO_08="Seq��ncia de disparos que levam a deadlock:";
	
	String PROP_INFO_09_KEY="PROP_INFO_09";
	String PROP_INFO_09="Seq��ncia";

	String PROP_INFO_10_KEY="PROP_INFO_10";
	String PROP_INFO_10="Nome da Rede de Petri:";
	
	String PROP_INFO_11_KEY="PROP_INFO_11";
	String PROP_INFO_11="Total de fichas da rede:";
	
	String PROP_INFO_12_KEY="PROP_INFO_12";
	String PROP_INFO_12="Leva ao estado:";
	
	String PROP_INFO_13_KEY="PROP_INFO_13";
	String PROP_INFO_13="Matriz de Entrada:";
	
	String PROP_INFO_14_KEY="PROP_INFO_14";
	String PROP_INFO_14="Matriz de Sa�da:";
	
	String PROP_INFO_15_KEY="PROP_INFO_15";
	String PROP_INFO_15="Matriz de Incid�ncia:";
	
	String AN_ERROR_01_KEY="AN_ERROR_01";
	String AN_ERROR_01="A Rede de Petri � nula";
	
	String AN_ERROR_02_KEY="AN_ERROR_02";
	String AN_ERROR_02="A Rede de Petri n�o possui lugar";
	
	String AN_ERROR_03_KEY="AN_ERROR_03";
	String AN_ERROR_03="A Rede de Petri n�o possui transi��o";
	
	String AN_ERROR_04_KEY="AN_ERROR_04";
	String AN_ERROR_04="A Rede de Petri n�o posssui arco";
	
	String AN_ERROR_TITLE_KEY="AN_ERROR_TITLE";
	String AN_ERROR_TITLE="Erro ao Analisar Rede";
	
	
	
}
