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
	int STATE_FRONTEIRA = 0;
	int STATE_DUPLICADO = 1;
	int STATE_INTERIOR  = 2;
	int STATE_TERMINAL  = 3;
	
	int TOKEN_INFINITO	= Integer.MAX_VALUE/100;
	
	int ERROR_CODE = -999997;
}
