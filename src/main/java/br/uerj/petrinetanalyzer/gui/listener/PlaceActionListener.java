package br.uerj.petrinetanalyzer.gui.listener;

import br.uerj.petrinetanalyzer.gui.MainWindow;
import br.uerj.petrinetanalyzer.gui.objects.PlaceGraph;

/**
 * Classe que Trata dos eventos relacionados ao Painel com Informações de Lugar
 * 
 * @author Felipe Lino
 * <BR>Data: 11/01/2007
 * <BR>Atualização: 10/02/2007
 */
public class PlaceActionListener extends AbstractActionListener
{
	/**
	 * Lugar correntemente selecionado.
	 */
	PlaceGraph place = null;
	
	/**
	 * Construtor.
	 * @param w Janela do Programa Principal
	 */
	public PlaceActionListener(MainWindow w)
	{
		setWindow(w);
	}
	
	/**
	 * Trata eventos relacionados a perda de foco de algum objeto do painel de informações de Lugar.
	 */
	public void focusLostPerformed()
	{
		if((place != null)&&(modo == MODO_EDICAO))
		{
			place.setNome( window.fieldPlaceNome.getText() );
			try
			{
				window.spinnerPlaceFichas.commitEdit();
			}
			catch(Exception exc)
			{
				System.out.println("Erro ao pegar o valor das fichas: "+exc.toString());
			}
			
			Integer iFichas = (Integer) window.spinnerPlaceFichas.getValue(); 
						
			int fichas = iFichas.intValue();
			place.setFichas( fichas );
		}
	}
	
	/**
	 * Seta o Lugar selecionado pelo Mouse.
	 * @param place
	 */
	public void setSelectedPlace(PlaceGraph place)
	{
		if(place != null)
		{
			focusLostPerformed();
		}
		this.place = place;
	}
	
	/**
	 * Exibe na tela as informações do Lugar.
	 *
	 */
	public void showInfo()
	{
		window.fieldPlaceNome.setText((place.getNome()));
		
		int fichas = 0;
		if(modo == MODO_SIMULACAO)
			fichas = window.simAction.getFichas(place.getPosicao());
		else
			fichas = place.getFichas();
		window.spinnerPlaceFichas.setValue(fichas);
	}
	
	/**
	 * Desabilita os botões do painel de informações do Lugar.
	 */
	public void disablePlaceInfo()
	{
		focusLostPerformed();
		window.fieldPlaceNome.setEditable(false);
		window.spinnerPlaceFichas.setEnabled(false);
	}
	
	/**
	 * Habilita os objetos do painel de informações de Lugar.
	 */
	public void enablePlaceInfo()
	{
		window.fieldPlaceNome.setEditable(true);
		window.spinnerPlaceFichas.setEnabled(true);		
	}
}
