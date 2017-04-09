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
	public static final String anWindowKey="anWindow";
	public static final String anWindow="Informações da Análise/Simulação";
		
	public static final String lbAnalyzerTreeKey="lbAnalyzerTree";
	public static final String lbAnalyzerTree="Seqüência de disparos";
	
	public static final String lbStateInfoKey="lbStateInfo";
	public static final String lbStateInfo="Descrição do Estado";

	public static final String lbPnInfoKey="lbPnInfo";
	public static final String lbPnInfo="Propriedades da Rede de Petri";
	
	
	public static final String AN_INFO_01_KEY="AN_INFO_01";
	public static final String AN_INFO_01 = "Seqüência de disparos:";
	
	public static final String AN_INFO_02_KEY="AN_INFO_02";
	public static final String AN_INFO_02="Marcações dos estados:";
	
	public static final String AN_INFO_03_KEY="AN_INFO_03";
	public static final String AN_INFO_03="Transições disponíveis:";
	
	public static final String AN_INFO_04_KEY="AN_INFO_04";
	public static final String AN_INFO_04="Não há transições habilitadas para disparo.";
	
	public static final String AN_INFO_05_KEY="AN_INFO_05";
	public static final String AN_INFO_05="Estado inicial";
	
	public static final String AN_INFO_06_KEY="AN_INFO_06";
	public static final String AN_INFO_06="Estado duplicado. Igual ao estado:";
	
	public static final String PROP_INFO_01_KEY="PROP_INFO_01";
	public static final String PROP_INFO_01="A rede é viva.";
	
	public static final String PROP_INFO_02_KEY="PROP_INFO_02";
	public static final String PROP_INFO_02="A rede não é viva.";
	
	public static final String PROP_INFO_03_KEY="PROP_INFO_03";
	public static final String PROP_INFO_03="A rede é limitada.";
	
	public static final String PROP_INFO_04_KEY="PROP_INFO_04";
	public static final String PROP_INFO_04="A rede não é limitada.";
	
	public static final String PROP_INFO_05_KEY="PROP_INFO_05";
	public static final String PROP_INFO_05="Limite da rede igual à";
	
	public static final String PROP_INFO_06_KEY="PROP_INFO_06";
	public static final String PROP_INFO_06="A rede é conservativa.";
	
	public static final String PROP_INFO_07_KEY="PROP_INFO_07";
	public static final String PROP_INFO_07="A rede não é conservativa.";
	
	public static final String PROP_INFO_08_KEY="PROP_INFO_08";
	public static final String PROP_INFO_08="Seqüência de disparos que levam a deadlock:";
	
	public static final String PROP_INFO_09_KEY="PROP_INFO_09";
	public static final String PROP_INFO_09="Seqüência";

	public static final String PROP_INFO_10_KEY="PROP_INFO_10";
	public static final String PROP_INFO_10="Nome da Rede de Petri:";
	
	public static final String PROP_INFO_11_KEY="PROP_INFO_11";
	public static final String PROP_INFO_11="Total de fichas da rede:";
	
	public static final String PROP_INFO_12_KEY="PROP_INFO_12";
	public static final String PROP_INFO_12="Leva ao estado:";
	
	public static final String PROP_INFO_13_KEY="PROP_INFO_13";
	public static final String PROP_INFO_13="Matriz de Entrada:";
	
	public static final String PROP_INFO_14_KEY="PROP_INFO_14";
	public static final String PROP_INFO_14="Matriz de Saída:";
	
	public static final String PROP_INFO_15_KEY="PROP_INFO_15";
	public static final String PROP_INFO_15="Matriz de Incidência:";
	
	public static final String AN_ERROR_01_KEY="AN_ERROR_01";
	public static final String AN_ERROR_01="A Rede de Petri é nula";
	
	public static final String AN_ERROR_02_KEY="AN_ERROR_02";
	public static final String AN_ERROR_02="A Rede de Petri não possui lugar";
	
	public static final String AN_ERROR_03_KEY="AN_ERROR_03";
	public static final String AN_ERROR_03="A Rede de Petri não possui transição";
	
	public static final String AN_ERROR_04_KEY="AN_ERROR_04";
	public static final String AN_ERROR_04="A Rede de Petri não posssui arco";
	
	public static final String AN_ERROR_TITLE_KEY="AN_ERROR_TITLE";
	public static final String AN_ERROR_TITLE="Erro ao Analisar Rede";
	
	
	
}
