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
	String simWindowKey="simWindow";
	String simWindow="Informa��es da An�lise/Simula��o";
		
	String lbTreeKey="lbTree";
	String lbTree="Seq��ncia de disparos";
	
	String lbInfoKey="lbInfo";
	String lbInfo="Descri��o do Estado";
	
	String SIM_INFO_01_KEY="SIM_INFO_01";
	String SIM_INFO_01 = "Seq��ncia de disparos:";
	
	String SIM_INFO_02_KEY="SIM_INFO_02";
	String SIM_INFO_02="Marca��es dos estados:";
	
	String SIM_INFO_03_KEY="SIM_INFO_03";
	String SIM_INFO_03="Transi��es dispon�veis:";
	
	String SIM_INFO_04_KEY="SIM_INFO_04";
	String SIM_INFO_04="N�o h� transi��es habilitadas para disparo.";
	
	String SIM_INFO_05_KEY="SIM_INFO_05";
	String SIM_INFO_05="Estado inicial";
}
