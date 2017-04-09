package br.uerj.petrinetanalyzer.gui.listener;

import javax.swing.JOptionPane;

import br.uerj.language.LanguageTool;
import br.uerj.petrinetanalyzer.common.ImplPetriNetBase;
import br.uerj.petrinetanalyzer.engine.PetriNetAnalyzer;
import br.uerj.petrinetanalyzer.gui.AnalyzerWindow;
import br.uerj.petrinetanalyzer.gui.SimulationWindow;
import br.uerj.petrinetanalyzer.gui.interfaces.IntfLanguageAnalyzerConstants;

/**
 * Classe que implementa a area de desenho da rede de petri.
 * 
 * @author Felipe Lino
 * <BR>Data:26/02/2007
 * <BR>Atualizado: 26/02/2007
 */
public class AnalyzerAction extends AbstractActionListener
implements IntfLanguageAnalyzerConstants
{
	private PetriNetAnalyzer analyzerEngine = null;
	
	private ImplPetriNetBase pn = null;
	
	private AnalyzerWindow anWindow = null;
	
	/**
	 * Construtor.
	 * @param pn Rede de Petri a ser simulada
	 */
	public AnalyzerAction(ImplPetriNetBase pn)
	{
		if(verifyPn(pn) == true)
		{
			try
			{
				this.pn = pn;
				analyzerEngine = new PetriNetAnalyzer(this.pn);
				analyzerEngine.geraArvoreAlcancabilidade();
				anWindow = new AnalyzerWindow(this.pn, analyzerEngine.getTree(), analyzerEngine.getProperties());	
			}
			catch(Exception exc)
			{
				if(anWindow != null)
				{
					anWindow.dispose();
					anWindow = null;
				}
				
				JOptionPane.showMessageDialog(null, exc.toString(), LanguageTool.getString(AN_ERROR_TITLE_KEY), JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Verifica se a Rede de Petri é uma rede válida para ser analisada.
	 * @param pn Rede de Petri
	 * @return Retorna true caso a Rede possa ser analisada. Retorna false caso contrário.
	 */
	public static boolean verifyPn(ImplPetriNetBase pn)
	{
		String strError = null;
		String strTitle = null;
		boolean error = false;
		if(pn == null)
		{
			strError = LanguageTool.getString(AN_ERROR_01_KEY);
			strTitle = LanguageTool.getString(AN_ERROR_TITLE_KEY);
			error = true;
		}
		else if(pn.getNumLugar() == 0)
		{
			strError = LanguageTool.getString(AN_ERROR_02_KEY);
			strTitle = LanguageTool.getString(AN_ERROR_TITLE_KEY);
			error = true;
		}
		else if(pn.getNumTransicao() == 0)
		{
			strError = LanguageTool.getString(AN_ERROR_03_KEY);
			strTitle = LanguageTool.getString(AN_ERROR_TITLE_KEY);
			error = true;
		}	
		else if(pn.getNumArco() == 0)
		{
			strError = LanguageTool.getString(AN_ERROR_04_KEY);
			strTitle = LanguageTool.getString(AN_ERROR_TITLE_KEY);
			error = true;
		}
		
		if(error == true)
		{
			JOptionPane.showMessageDialog(null, strError, strTitle, JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Exibe a janela de Análise.
	 *
	 */
	public void showSimulationWindow()
	{
		AnalyzerWindow.setDefaultLookAndFeelDecorated(true);
		anWindow.setDefaultCloseOperation(SimulationWindow.DISPOSE_ON_CLOSE);
		anWindow.setFocusable(true);
		anWindow.setVisible(true);
	}
	
	/**
	 * Atualiza os rótulos e o texto da janela de Analise.
	 *
	 */
	public void refreshText()
	{
		if((anWindow != null)&&(anWindow.isShowing()))
			anWindow.refreshText();
	}
	
	
		
}
