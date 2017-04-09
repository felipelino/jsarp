
package br.uerj.petrinetanalyzer.gui.listener;

import br.uerj.petrinetanalyzer.gui.MainWindow;
import br.uerj.petrinetanalyzer.gui.objects.ArcGraph;
/**
 * Classe que Trata dos eventos relacionados ao Painel com Informações
 * de Arco.
 * 
 * @author Felipe Lino
 * <BR>Data: 11/01/2007
 * <BR>Atualização: 10/02/2007
 */
public class ArcActionListener extends AbstractActionListener
{
	private ArcGraph arc = null;
	
	/**
	 * Construtor.
	 * @param w Janela do Programa Principal.
	 */
	public ArcActionListener(MainWindow w)
	{
		setWindow(w);
	}
	
	/**
	 * Trata eventos relacionados a perda de foco de algum objeto do painel de informações de Lugar.
	 */
	public void focusLostPerformed()
	{
		if((arc != null)&&(modo == MODO_EDICAO))
		{
			try
			{
				window.spinnerArcPeso.commitEdit();
			}
			catch(Exception exc)
			{
				System.out.println("Erro ao pegar o valor do peso do arco: "+exc.toString());
			}
			
			Integer iPeso = (Integer) window.spinnerArcPeso.getValue(); 
						
			int peso = iPeso.intValue();
			arc.setPeso(peso);
		}
	}
	
	/**
	 * Seta o Arco selecionado pelo Mouse.
	 * @param arc Arco 
	 */
	public void setSelectedArc(ArcGraph arc)
	{
		if(arc != null)
		{
			focusLostPerformed();
		}
		this.arc = arc;
	}
	
	/**
	 * Exibe na tela as informações da Transição.
	 *
	 */
	public void showInfo()
	{
		window.fieldArcPlace.setText(arc.getLugar().getNome());
		window.fieldArcTrans.setText(arc.getTransicao().getNome());
		window.spinnerArcPeso.setValue(arc.getPeso());
	}
	
	/**
	 * Desabilita os botões do painel de informações de Arco.
	 */
	public void disableArcInfo()
	{
		focusLostPerformed();
		window.fieldArcPlace.setEditable(false);
		window.fieldArcTrans.setEditable(false);
		window.spinnerArcPeso.setEnabled(false);
	}
	
	public void enableArcInfo()
	{
		window.fieldArcPlace.setEditable(true);
		window.fieldArcTrans.setEditable(true);
		window.spinnerArcPeso.setEnabled(true);
	}
}
