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
	String simWindowKey="simWindow";
	String simWindow="Informações da Análise/Simulação";
		
	String lbTreeKey="lbTree";
	String lbTree="Seqüência de disparos";
	
	String lbInfoKey="lbInfo";
	String lbInfo="Descrição do Estado";
	
	String SIM_INFO_01_KEY="SIM_INFO_01";
	String SIM_INFO_01 = "Seqüência de disparos:";
	
	String SIM_INFO_02_KEY="SIM_INFO_02";
	String SIM_INFO_02="Marcações dos estados:";
	
	String SIM_INFO_03_KEY="SIM_INFO_03";
	String SIM_INFO_03="Transições disponíveis:";
	
	String SIM_INFO_04_KEY="SIM_INFO_04";
	String SIM_INFO_04="Não há transições habilitadas para disparo.";
	
	String SIM_INFO_05_KEY="SIM_INFO_05";
	String SIM_INFO_05="Estado inicial";
}
