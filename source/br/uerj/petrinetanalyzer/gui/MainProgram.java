package br.uerj.petrinetanalyzer.gui;

import br.uerj.language.LanguageTool;
import br.uerj.petrinetanalyzer.gui.interfaces.IntfLanguageAnalyzerConstants;
import br.uerj.petrinetanalyzer.gui.interfaces.IntfLanguageConstants;
import br.uerj.petrinetanalyzer.gui.interfaces.IntfLanguageSimulationConstants;



/**
 * Classe que implementa o método main, que instância
 * a Janela Principal.
 * 
 * @author Felipe Lino
 * <BR>Data: 04/01/2007
 * <BR>Atualizado: 12/10/2007
 */
public class MainProgram implements IntfLanguageConstants, IntfLanguageAnalyzerConstants,
IntfLanguageSimulationConstants
{


	/**
	 * Método main. Instância a janela principal do programa.
	 * @param args argumentos recebidos pela linha de comando 
	 * (não será usado).
	 */
	public static void main(String[] args)
	{
		LanguageTool.addString(STATUS_MSG_01_KEY, STATUS_MSG_01);
		LanguageTool.addString(STATUS_MSG_02_KEY, STATUS_MSG_02);
		LanguageTool.addString(STATUS_MSG_03_KEY, STATUS_MSG_03);
		LanguageTool.addString(STATUS_MSG_04_KEY, STATUS_MSG_04);
		LanguageTool.addString(STATUS_MSG_05_KEY, STATUS_MSG_05);
		LanguageTool.addString(STATUS_MSG_06_KEY, STATUS_MSG_06);
		LanguageTool.addString(STATUS_MSG_07_KEY, STATUS_MSG_07);
		LanguageTool.addString(STATUS_MSG_08_KEY, STATUS_MSG_08);
		LanguageTool.addString(STATUS_MSG_09_KEY, STATUS_MSG_09);
		LanguageTool.addString(STATUS_MSG_10_KEY, STATUS_MSG_10);
		LanguageTool.addString(STATUS_MSG_11_KEY, STATUS_MSG_11);
		LanguageTool.addString(STATUS_MSG_12_KEY, STATUS_MSG_12);
		LanguageTool.addString(STATUS_MSG_13_KEY, STATUS_MSG_13);
		LanguageTool.addString(STATUS_MSG_14_KEY, STATUS_MSG_14);
		LanguageTool.addString(STATUS_MSG_15_KEY, STATUS_MSG_15);
				
		LanguageTool.addString(ERROR_MSG_01_KEY, ERROR_MSG_01);
		LanguageTool.addString(ERROR_MSG_02_KEY, ERROR_MSG_02);
		LanguageTool.addString(ERROR_MSG_03_KEY, ERROR_MSG_03);
		LanguageTool.addString(ERROR_MSG_04_KEY, ERROR_MSG_04);
		
		LanguageTool.addString(ABOUT_MSG_01_KEY, ABOUT_MSG_01);
		LanguageTool.addString(ABOUT_MSG_02_KEY, ABOUT_MSG_02);
		LanguageTool.addString(ABOUT_MSG_03_KEY, ABOUT_MSG_03);
		
		LanguageTool.addString(AN_ERROR_01_KEY, AN_ERROR_01);
		LanguageTool.addString(AN_ERROR_02_KEY, AN_ERROR_02);
		LanguageTool.addString(AN_ERROR_03_KEY, AN_ERROR_03);
		LanguageTool.addString(AN_ERROR_04_KEY, AN_ERROR_04);
		LanguageTool.addString(AN_ERROR_TITLE_KEY, AN_ERROR_TITLE);
		
		LanguageTool.addString(anWindowKey	 , anWindow);
		LanguageTool.addString(AN_INFO_01_KEY, AN_INFO_01);
		LanguageTool.addString(AN_INFO_02_KEY, AN_INFO_02);
		LanguageTool.addString(AN_INFO_03_KEY, AN_INFO_03);
		LanguageTool.addString(AN_INFO_04_KEY, AN_INFO_04);
		LanguageTool.addString(AN_INFO_05_KEY, AN_INFO_05);
		LanguageTool.addString(AN_INFO_06_KEY, AN_INFO_06);
		
		LanguageTool.addString(PROP_INFO_01_KEY, PROP_INFO_01);
		LanguageTool.addString(PROP_INFO_02_KEY, PROP_INFO_02);
		LanguageTool.addString(PROP_INFO_03_KEY, PROP_INFO_03);
		LanguageTool.addString(PROP_INFO_04_KEY, PROP_INFO_04);
		LanguageTool.addString(PROP_INFO_05_KEY, PROP_INFO_05);
		LanguageTool.addString(PROP_INFO_06_KEY, PROP_INFO_06);
		LanguageTool.addString(PROP_INFO_07_KEY, PROP_INFO_07);
		LanguageTool.addString(PROP_INFO_08_KEY, PROP_INFO_08);
		LanguageTool.addString(PROP_INFO_09_KEY, PROP_INFO_09);
		LanguageTool.addString(PROP_INFO_10_KEY, PROP_INFO_10);
		LanguageTool.addString(PROP_INFO_11_KEY, PROP_INFO_11);
		LanguageTool.addString(PROP_INFO_12_KEY, PROP_INFO_12);
		LanguageTool.addString(PROP_INFO_13_KEY, PROP_INFO_13);
		LanguageTool.addString(PROP_INFO_14_KEY, PROP_INFO_14);
		LanguageTool.addString(PROP_INFO_15_KEY, PROP_INFO_15);
		
		LanguageTool.addString(simWindowKey, simWindow);
		
		LanguageTool.addString(SIM_INFO_01_KEY, SIM_INFO_01);
		LanguageTool.addString(SIM_INFO_02_KEY, SIM_INFO_02);
		LanguageTool.addString(SIM_INFO_03_KEY, SIM_INFO_03);
		LanguageTool.addString(SIM_INFO_04_KEY, SIM_INFO_04);
		LanguageTool.addString(SIM_INFO_05_KEY, SIM_INFO_05);
		
		MainWindow jMain;
		MainWindow.setDefaultLookAndFeelDecorated(true);
		jMain = new MainWindow();
		
		jMain.setDefaultCloseOperation(MainWindow.EXIT_ON_CLOSE);
		jMain.setFocusable(true);
		jMain.setVisible(true);
	}

}
