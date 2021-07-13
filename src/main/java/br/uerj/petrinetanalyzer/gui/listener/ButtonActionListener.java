
package br.uerj.petrinetanalyzer.gui.listener;

import javax.swing.JButton;

import br.uerj.petrinetanalyzer.gui.MainWindow;
import br.uerj.petrinetanalyzer.gui.interfaces.IntfLanguageConstants;

/**
 * Trata os eventos de bot�es.
 * @author Felipe Lino
 * <BR>Data: 11/01/2007
 * <BR>Atualizado: 24/02/2007
 */
public class ButtonActionListener extends AbstractActionListener
implements IntfLanguageConstants
{
	/**
	 * Construtor que recebe a janela que possui os eventos a serem processados.
	 * @param w Janela Principal
	 */
	public ButtonActionListener(MainWindow w)
	{
		setWindow(w);
	}
	
	/**
	 * Invoca o m�todo adequado dependendo de qual bot�o foi acionado
	 * @param bt Bot�o
	 */
	public void performed(JButton bt)
	{
		String btKey = bt.getName();
		if(btEditSetaKey.equalsIgnoreCase(btKey))
		{
			editMouse();
		}
		else if(btEditPlaceKey.equalsIgnoreCase(btKey))
		{
			editPlace();
		}
		else if(btEditTransKey.equalsIgnoreCase(btKey))
		{
			editTransition();
		}
		else if(btEditArcKey.equalsIgnoreCase(btKey))
		{
			editArc();
		}
		else if(btEditDeleteKey.equalsIgnoreCase(btKey))
		{
			editDelete();
		}
		else if(btEditLabelKey.equalsIgnoreCase(btKey))
		{
			editLabel();
		}
		else if(btSimStartKey.equalsIgnoreCase(btKey))
		{
			simulationStart();
		}
		else if(btSimStopKey.equalsIgnoreCase(btKey))
		{
			simulationStop();
		}
		else if(btSimBackKey.equalsIgnoreCase(btKey))
		{
			simulationBack();
		}
	}
	
	/**
	 * Trata o evento vindo do bot�o de edi��o Seta.
	 */
	public void editMouse()
	{
		disableSimulationButton();
		modo	 = MODO_EDICAO;
		sub_modo = EDIT_MOUSE;
		window.fieldStatus.setTextWithKey(STATUS_MSG_04_KEY);
		window.editor.setModo(modo, sub_modo);
		window.editor.eraseArc();
		window.editor.repaint();
	}
	
	/**
	 * Trata o evento vindo do bot�o de edi��o Lugar.
	 */
	public void editPlace()
	{
		disableSimulationButton();
		modo 	 = MODO_EDICAO;
		sub_modo = EDIT_LUGAR;
		window.fieldStatus.setTextWithKey(STATUS_MSG_01_KEY);
		window.editor.setModo(modo, sub_modo);
		window.editor.eraseArc();
		window.editor.repaint();
	}
	
	/**
	 * Trata o evento vindo do bot�o de edi��o Transi��o.
	 */
	public void editTransition()
	{
		disableSimulationButton();
		modo 	 = MODO_EDICAO;
		sub_modo = EDIT_TRANS;
		window.fieldStatus.setTextWithKey(STATUS_MSG_02_KEY);
		window.editor.setModo(modo, sub_modo);
		window.editor.eraseArc();
		window.editor.repaint();
	}
	
	/**
	 * Trata o evento vindo do bot�o de edi��o Arco.
	 */
	public void editArc()
	{
		disableSimulationButton();
		modo	 = MODO_EDICAO;
		sub_modo = EDIT_ARC;
		window.fieldStatus.setTextWithKey(STATUS_MSG_03_KEY);
		window.editor.setModo(modo, sub_modo);
		window.editor.eraseArc();
		window.editor.repaint();
	}
	
	/**
	 * Trata o evento vindo do bot�o de edi��o Apagar.
	 */
	public void editDelete()
	{
		disableSimulationButton();
		modo 	 = MODO_EDICAO;
		sub_modo = EDIT_DELETE;
		window.fieldStatus.setTextWithKey(STATUS_MSG_05_KEY);
		window.editor.setModo(modo, sub_modo);
		window.editor.eraseArc();
		window.arcAction.disableArcInfo();
		window.placeAction.disablePlaceInfo();
		window.transAction.disableTransitionInfo();
		window.editor.repaint();
	}
	
	/**
	 * Trata o evento vindo do bot�o de edi��o R�tulo.
	 */
	public void editLabel()
	{
		disableSimulationButton();
		modo	 = MODO_EDICAO;
		sub_modo = EDIT_LABEL;
		window.fieldStatus.setTextWithKey(STATUS_MSG_19_KEY);
		window.editor.setModo(modo, sub_modo);
		window.editor.eraseArc();
		window.editor.repaint();
	}
	
	/**
	 * Trata evento de �nicio de simula��o.
	 *
	 */
	public void simulationStart()
	{
		enableSimulationButton();
		disableEditButton();
		
		window.arcAction.disableArcInfo();
		window.placeAction.disablePlaceInfo();
		window.transAction.disableTransitionInfo();
		
		modo	 = MODO_SIMULACAO;
		sub_modo = SIM_START;
		window.simAction = new SimulationAction(window.pn);
		window.editor.setModo(modo, sub_modo);
		window.editor.repaint();
		
		window.simAction.showSimulationWindow();
		window.fieldStatus.setTextWithKey(STATUS_MSG_16_KEY);
	}
	
	/**
	 * Trata evento de Parada de simula��o.
	 */
	public void simulationStop()
	{
		disableSimulationButton();
		enableEditButton();
		
		modo	 = MODO_EDICAO;
		sub_modo = EDIT_MOUSE;
		window.simAction.closeSimulationWindow();
		window.simAction = null;
		window.editor.setModo(modo, sub_modo);
		window.editor.repaint();
		window.fieldStatus.setTextWithKey(STATUS_MSG_17_KEY);
	
	}
	
	/**
	 * Trata evento de Volta da Simula��o.
	 */
	public void simulationBack()
	{	
		window.simAction.goToEstado();
		window.editor.repaint();
		window.fieldStatus.setTextWithKey(STATUS_MSG_18_KEY);
	}
	
}