package br.uerj.petrinetanalyzer.gui.interfaces;

/**
 * Define String constantes que serão exibidas na tela
 * para o Editor/Simulador de Redes de Petri.
 * Apenas para a Janela com informações da simulação.
 * 
 * @author Felipe Lino
 * <BR>Data: 17/01/2007
 * <BR>Atualizado: 17/02/2007
 */
public interface IntfLanguageSimulationConstants
{
	/* Campos da janela com a sequencia de disparos */
	public static final String simWindowKey="simWindow";
	public static final String simWindow="Informações da Análise/Simulação";
		
	public static final String lbTreeKey="lbTree";
	public static final String lbTree="Seqüência de disparos";
	
	public static final String lbInfoKey="lbInfo";
	public static final String lbInfo="Descrição do Estado";
	
	public static final String SIM_INFO_01_KEY="SIM_INFO_01";
	public static final String SIM_INFO_01 = "Seqüência de disparos:";
	
	public static final String SIM_INFO_02_KEY="SIM_INFO_02";
	public static final String SIM_INFO_02="Marcações dos estados:";
	
	public static final String SIM_INFO_03_KEY="SIM_INFO_03";
	public static final String SIM_INFO_03="Transições disponíveis:";
	
	public static final String SIM_INFO_04_KEY="SIM_INFO_04";
	public static final String SIM_INFO_04="Não há transições habilitadas para disparo.";
	
	public static final String SIM_INFO_05_KEY="SIM_INFO_05";
	public static final String SIM_INFO_05="Estado inicial";
}
