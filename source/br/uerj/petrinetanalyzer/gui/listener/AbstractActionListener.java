package br.uerj.petrinetanalyzer.gui.listener;

import br.uerj.petrinetanalyzer.gui.MainWindow;
import br.uerj.petrinetanalyzer.gui.interfaces.IntfMainConstants;

/**
 * Classe para auxiliar no tratamento de eventos do programa.
 * @author Felipe Lino
 * <BR>Data: 10/01/2007
 * <BR>Atualizado: 03/03/2007
 */
public abstract class AbstractActionListener
implements IntfMainConstants
{
	/* Vari�veis para controle de funcionamento do programa. */
	public static int modo = MODO_EDICAO;
	public static int sub_modo = EDIT_MOUSE;
	
	protected static MainWindow window;
	
	/**
	 * Seta a inst�ncia de MainWindow.
	 * @param w Janela Principal
	 */
	public void setWindow(MainWindow w)
	{
		window = w;
	}
	
	/**
	 * Retorna a inst�ncia de MainWindow.
	 * @return Janela Principal
	 */
	public MainWindow getWindow()
	{
		return window;
	}
	
	/**
	 * Desabilita alguns dos bot�es de simula��o.
	 *
	 */
	public void disableSimulationButton()
	{
		window.btSimStop.setEnabled(false);
		window.btSimBack.setEnabled(false);
		window.m2Back.setEnabled(false);
		window.m2Stop.setEnabled(false);
	}
	
	/**
	 * Habilita os bot�es de simula��o.
	 */
	public void enableSimulationButton()
	{
		window.btSimStop.setEnabled(true);
		window.btSimBack.setEnabled(true);
		window.m2Back.setEnabled(true);
		window.m2Stop.setEnabled(true);
	}
	
	/**
	 * Desabilita bot�es de edi��o.
	 *
	 */
	public void disableEditButton()
	{
		window.btEditArc.setEnabled(false);
		window.btEditDelete.setEnabled(false);
		window.btEditPlace.setEnabled(false);
		window.btEditSeta.setEnabled(false);
		window.btEditTrans.setEnabled(false);
		window.btEditLabel.setEnabled(false);
		
		window.m1Open.setEnabled(false);
		window.m1New.setEnabled(false);
		window.m1Save.setEnabled(false);
		window.m1SaveAs.setEnabled(false);
		
		window.btSimStart.setEnabled(false);
		window.m2Start.setEnabled(false);
	}
	
	/**
	 * Habilita bot�es de edi��o.
	 *
	 */
	public void enableEditButton()
	{
		window.btEditArc.setEnabled(true);
		window.btEditDelete.setEnabled(true);
		window.btEditPlace.setEnabled(true);
		window.btEditSeta.setEnabled(true);
		window.btEditTrans.setEnabled(true);
		window.btEditLabel.setEnabled(true);
		
		window.m1Open.setEnabled(true);
		window.m1New.setEnabled(true);
		window.m1Save.setEnabled(true);
		window.m1SaveAs.setEnabled(true);
		
		window.btSimStart.setEnabled(true);
		window.m2Start.setEnabled(true);
	}
	
	/**
	* Limpa os pain�is de informa��o.
	*/
	public void cleanInfoPanel()
	{
		window.fieldPlaceNome.setText("");
		window.spinnerPlaceFichas.setValue(0);
		
		window.fieldTransName.setText("");
		window.fieldTransSeft.setText("");
		window.fieldTransSlft.setText("");
		window.boxTransCurvaDensidade.setSelectedIndex(0);
		
		window.fieldArcPlace.setText("");
		window.fieldArcTrans.setText("");
		window.spinnerArcPeso.setValue(0);
	}
	
	
}
