package br.uerj.petrinetanalyzer.gui.interfaces;

/**
 * Define o Programa Principal.
 * 
 * @author Felipe Lino
 * <BR>Data: 04/01/2007
 * <BR>Atualizado: 20/10/2007
 * @version 2.0
 */
public interface IntfMainConstants
{
	/**
	 * Arquivos properties com os idiomas suportados
	 */
	String PROP_LANGUAGE_FILE_ENGLISH = "jsarp_eng_us.properties";
	String PROP_LANGUAGE_FILE_PORT_BR = "jsarp_pt_br.properties";
	String PROP_LANGUAGE_FILE_DEFAULT = PROP_LANGUAGE_FILE_ENGLISH;
	
	/**
	 * Constantes dos modos de funcionamento do programa
	 */
	int MODO_EDICAO 	= 1;
	int MODO_SIMULACAO 	= 2;
	int MODO_ANALISE	= 3;
	
	/**
	 * Constantes referentes ao objeto selecionado na edicao
	 */
	int FILE_SAVE	= 10;
	int EDIT_MOUSE 	= 11;
	int EDIT_LUGAR 	= 12;
	int EDIT_TRANS 	= 13;
	int EDIT_ARC	= 14;	
	int EDIT_DELETE = 15;
	int EDIT_LABEL	= 16;
	
	/**
	 * Constantes referentes ao desenho do arco
	 */
	int DRAW_ARC	= 141; 
	
	/**
	 * Constantes referentes ao objeto selecionado para simulação
	 */
	int SIM_START	= 20;
	int SIM_STOP	= 21;
	int SIM_BACK	= 22;
}
