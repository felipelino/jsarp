package br.uerj.petrinetanalyzer.gui.listener;

import br.uerj.petrinetanalyzer.gui.MainWindow;
import br.uerj.petrinetanalyzer.gui.objects.TransitionGraph;
/**
 * Classe que Trata dos eventos relacionados ao Painel com Informações de
 * Transição
 * 
 * @author Felipe Lino
 * <BR>Data: 11/01/2007
 * <BR>Atualização: 18/03/2007
 */
public class TransActionListener extends AbstractActionListener
{
	/**
	 * Transição correntemente selecionada.
	 */
	TransitionGraph trans = null;

	/**
	 * Construtor.
	 * @param w Janela do Programa Principal
	 */
	public TransActionListener(MainWindow w)
	{
		setWindow(w);
	}
	
	/**
	 * Trata eventos relacionados a perda de foco de algum objeto do painel de informações de Lugar.
	 */
	public void focusLostPerformed()
	{
		if((trans != null)&&(modo == MODO_EDICAO))
		{
			trans.setNome( window.fieldTransName.getText() );
			double seft = 0;
			double slft = 0;
			try
			{
				seft = Double.parseDouble(window.fieldTransSeft.getText());
				slft = Double.parseDouble(window.fieldTransSlft.getText());
			}
			catch(Exception exc)
			{
				seft = 0;
				slft = 0;
			}
			
			trans.setSEFT( seft );
			trans.setSLFT( slft );
			trans.setCurvaDensidade( window.boxTransCurvaDensidade.getSelectedIndex() );
		}
	}
	
	/**
	 * Seta a Transição selecionada pelo mouse.
	 * @param trans Transição
	 */
	public void setSelectedTransition(TransitionGraph trans)
	{
		if(trans != null)
		{
			focusLostPerformed();
		}
		this.trans = trans;
	}
	
	
	/**
	 * Exibe na tela as informações da Transição.
	 *
	 */
	public void showInfo()
	{
		window.fieldTransName.setText(trans.getNome());
		window.fieldTransSeft.setText(""+trans.getSEFT());
		window.fieldTransSlft.setText(""+trans.getSLFT());
		window.boxTransCurvaDensidade.setSelectedIndex(trans.getCurvaDensidade());
	}
	
	/**
	 * Desabilita os botões do painel de informações de Transição.
	 */
	public void disableTransitionInfo()
	{
		focusLostPerformed();
		window.fieldTransName.setEditable(false);
		window.fieldTransSeft.setEditable(false);
		window.fieldTransSlft.setEditable(false);
		window.boxTransCurvaDensidade.setEnabled(false);
		window.boxTransCurvaDensidade.setEditable(false);
	}
	
	/**
	 * Habilita os objetos do painel de informações de Transição.
	 */
	public void enableTransInfo()
	{
		window.fieldTransName.setEditable(true);
		window.fieldTransSeft.setEditable(true);
		window.fieldTransSlft.setEditable(true);
		window.boxTransCurvaDensidade.setEnabled(true);
		window.boxTransCurvaDensidade.setEditable(true);
	}
}
