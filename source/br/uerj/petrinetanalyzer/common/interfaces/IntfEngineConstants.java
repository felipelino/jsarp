package br.uerj.petrinetanalyzer.common.interfaces;

/**
 * Classe com constantes a serem usadas para a Engine
 * de Analise.
 * 
 * @author Felipe Lino
 * <BR>Data: 25/02/2007
 * <BR>Atualização: 25/02/2007
 */
public interface IntfEngineConstants
{
	public static final int STATE_FRONTEIRA = 0;
	public static final int STATE_DUPLICADO = 1;
	public static final int STATE_INTERIOR  = 2;
	public static final int STATE_TERMINAL  = 3;
	
	public static final int TOKEN_INFINITO	= Integer.MAX_VALUE/100;
	
	public static final int ERROR_CODE = -999997;
}
