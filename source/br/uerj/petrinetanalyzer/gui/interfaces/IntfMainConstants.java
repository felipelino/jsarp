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
	public static final String PROP_LANGUAGE_FILE_ENGLISH = "resources/jsarp_eng_us.properties";
	public static final String PROP_LANGUAGE_FILE_PORT_BR = "resources/jsarp_pt_br.properties";
	public static final String PROP_LANGUAGE_FILE_DEFAULT = PROP_LANGUAGE_FILE_ENGLISH;
	
	/**
	 * Constantes dos modos de funcionamento do programa
	 */
	public static final int MODO_EDICAO 	= 1;
	public static final int MODO_SIMULACAO 	= 2;
	public static final int MODO_ANALISE	= 3;
	
	/**
	 * Constantes referentes ao objeto selecionado na edicao
	 */
	public static final int FILE_SAVE	= 10;
	public static final int EDIT_MOUSE 	= 11;
	public static final int EDIT_LUGAR 	= 12;
	public static final int EDIT_TRANS 	= 13;
	public static final int EDIT_ARC	= 14;	
	public static final int EDIT_DELETE = 15;
	public static final int EDIT_LABEL	= 16;
	
	/**
	 * Constantes referentes ao desenho do arco
	 */
	public static final int DRAW_ARC	= 141; 
	
	/**
	 * Constantes referentes ao objeto selecionado para simulação
	 */
	public static final int SIM_START	= 20;
	public static final int SIM_STOP	= 21;
	public static final int SIM_BACK	= 22;
}
