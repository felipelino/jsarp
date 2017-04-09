package br.uerj.petrinetanalyzer.common.interfaces;


/**
 * Interface base para o Analisador de Redes de Petri
 * 
 * @author Felipe Lino
 * <BR>Data: 09/12/2006
 * 
 */
public interface IntfAnalyzer
{
	/**
	 * Veriica as Propriedades da Rede de Petri
	 * @param redePetri Rede de Petri
	 * @return Objeto contendo as Propriedades da Rede de Petri
	 */
	public IntfPetriNetProperties verifyProperties(IntfPetriNetBase redePetri);
	
	/* Qualquer outro tipo de análise sobre a Rede deve ficar por aqui
	 * Como analise de invariantes, e demais analises que porventura consigamos
	 * implementar.
	 */
	
}
