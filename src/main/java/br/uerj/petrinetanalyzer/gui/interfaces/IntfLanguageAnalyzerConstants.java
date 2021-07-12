package br.uerj.petrinetanalyzer.gui.interfaces;

/**
 * Define String constantes que serão exibidas na tela
 * para o Editor/Simulador de Redes de Petri.
 * Apenas para a Janela com informações da Análise.
 * 
 * @author Felipe Lino
 * <BR>Data: 26/02/2007
 * <BR>Atualizado: 12/10/2007
 */
public interface IntfLanguageAnalyzerConstants
{
	/* Campos da janela com a sequencia de disparos */
	String anWindowKey="anWindow";
	String anWindow="Informações da Análise/Simulação";
		
	String lbAnalyzerTreeKey="lbAnalyzerTree";
	String lbAnalyzerTree="Seqüência de disparos";
	
	String lbStateInfoKey="lbStateInfo";
	String lbStateInfo="Descrição do Estado";

	String lbPnInfoKey="lbPnInfo";
	String lbPnInfo="Propriedades da Rede de Petri";
	
	
	String AN_INFO_01_KEY="AN_INFO_01";
	String AN_INFO_01 = "Seqüência de disparos:";
	
	String AN_INFO_02_KEY="AN_INFO_02";
	String AN_INFO_02="Marcações dos estados:";
	
	String AN_INFO_03_KEY="AN_INFO_03";
	String AN_INFO_03="Transições disponíveis:";
	
	String AN_INFO_04_KEY="AN_INFO_04";
	String AN_INFO_04="Não há transições habilitadas para disparo.";
	
	String AN_INFO_05_KEY="AN_INFO_05";
	String AN_INFO_05="Estado inicial";
	
	String AN_INFO_06_KEY="AN_INFO_06";
	String AN_INFO_06="Estado duplicado. Igual ao estado:";
	
	String PROP_INFO_01_KEY="PROP_INFO_01";
	String PROP_INFO_01="A rede é viva.";
	
	String PROP_INFO_02_KEY="PROP_INFO_02";
	String PROP_INFO_02="A rede não é viva.";
	
	String PROP_INFO_03_KEY="PROP_INFO_03";
	String PROP_INFO_03="A rede é limitada.";
	
	String PROP_INFO_04_KEY="PROP_INFO_04";
	String PROP_INFO_04="A rede não é limitada.";
	
	String PROP_INFO_05_KEY="PROP_INFO_05";
	String PROP_INFO_05="Limite da rede igual à";
	
	String PROP_INFO_06_KEY="PROP_INFO_06";
	String PROP_INFO_06="A rede é conservativa.";
	
	String PROP_INFO_07_KEY="PROP_INFO_07";
	String PROP_INFO_07="A rede não é conservativa.";
	
	String PROP_INFO_08_KEY="PROP_INFO_08";
	String PROP_INFO_08="Seqüência de disparos que levam a deadlock:";
	
	String PROP_INFO_09_KEY="PROP_INFO_09";
	String PROP_INFO_09="Seqüência";

	String PROP_INFO_10_KEY="PROP_INFO_10";
	String PROP_INFO_10="Nome da Rede de Petri:";
	
	String PROP_INFO_11_KEY="PROP_INFO_11";
	String PROP_INFO_11="Total de fichas da rede:";
	
	String PROP_INFO_12_KEY="PROP_INFO_12";
	String PROP_INFO_12="Leva ao estado:";
	
	String PROP_INFO_13_KEY="PROP_INFO_13";
	String PROP_INFO_13="Matriz de Entrada:";
	
	String PROP_INFO_14_KEY="PROP_INFO_14";
	String PROP_INFO_14="Matriz de Saída:";
	
	String PROP_INFO_15_KEY="PROP_INFO_15";
	String PROP_INFO_15="Matriz de Incidência:";
	
	String AN_ERROR_01_KEY="AN_ERROR_01";
	String AN_ERROR_01="A Rede de Petri é nula";
	
	String AN_ERROR_02_KEY="AN_ERROR_02";
	String AN_ERROR_02="A Rede de Petri não possui lugar";
	
	String AN_ERROR_03_KEY="AN_ERROR_03";
	String AN_ERROR_03="A Rede de Petri não possui transição";
	
	String AN_ERROR_04_KEY="AN_ERROR_04";
	String AN_ERROR_04="A Rede de Petri não posssui arco";
	
	String AN_ERROR_TITLE_KEY="AN_ERROR_TITLE";
	String AN_ERROR_TITLE="Erro ao Analisar Rede";
	
	
	
}
