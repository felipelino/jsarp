package br.uerj.petrinetanalyzer.gui.interfaces;

/**
 * Define String constantes que ser�o exibidas na tela
 * para o Editor/Simulador de Redes de Petri.
 * Apenas para a Janela com informa��es da simula��o.
 * 
 * @author Felipe Lino
 * <BR>Data: 17/01/2007
 * <BR>Atualizado: 17/02/2007
 */
public interface IntfLanguageSimulationConstants
{
	/* Campos da janela com a sequencia de disparos */
	public static final String simWindowKey="simWindow";
	public static final String simWindow="Informa��es da An�lise/Simula��o";
		
	public static final String lbTreeKey="lbTree";
	public static final String lbTree="Seq��ncia de disparos";
	
	public static final String lbInfoKey="lbInfo";
	public static final String lbInfo="Descri��o do Estado";
	
	public static final String SIM_INFO_01_KEY="SIM_INFO_01";
	public static final String SIM_INFO_01 = "Seq��ncia de disparos:";
	
	public static final String SIM_INFO_02_KEY="SIM_INFO_02";
	public static final String SIM_INFO_02="Marca��es dos estados:";
	
	public static final String SIM_INFO_03_KEY="SIM_INFO_03";
	public static final String SIM_INFO_03="Transi��es dispon�veis:";
	
	public static final String SIM_INFO_04_KEY="SIM_INFO_04";
	public static final String SIM_INFO_04="N�o h� transi��es habilitadas para disparo.";
	
	public static final String SIM_INFO_05_KEY="SIM_INFO_05";
	public static final String SIM_INFO_05="Estado inicial";
}
