package br.uerj.petrinetanalyzer.gui.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.uerj.fileutil.FileFilterUtil;
import br.uerj.fileutil.FileFilterXML;
import br.uerj.language.LanguageTool;
import br.uerj.petrinetanalyzer.common.ImplPetriNetBase;
import br.uerj.petrinetanalyzer.gui.MainWindow;
import br.uerj.petrinetanalyzer.gui.interfaces.IntfLanguageConstants;
import br.uerj.petrinetanalyzer.gui.interfaces.IntfMainConstants;
import br.uerj.petrinetanalyzer.gui.objects.PetriNetGraph;

import com.thoughtworks.xstream.XStream;

/**
 * Define a Janela Principal para o Editor/Simulador de Redes de Petri
 * Toda a parte gr�fica
 * @author Felipe Lino
 * <BR>Data: 04/01/2007
 * <BR>Atualizado: 20/04/2007
 */
public class MenuActionListener extends AbstractActionListener
implements IntfLanguageConstants, IntfMainConstants
{
	private File file = null;
	
	/**
	 * Construtor.
	 * @param w Janela do Programa principal
	 */
	public MenuActionListener(MainWindow w)
	{
		setWindow(w);
	}
	
	/**
	 * Atrav�s do nome do objeto verifica que a��o deve tomar.
	 * @param item instancia de JMenuItem
	 */
	public void performed(JMenuItem item)
	{
		if(m1PortuguesKey.equalsIgnoreCase(item.getName()))
		{
			LanguageTool.changeLanguage(PROP_LANGUAGE_FILE_PORT_BR);
			window.refreshText();
		}
		else if(m1EnglishKey.equalsIgnoreCase(item.getName()))
		{
			LanguageTool.changeLanguage(PROP_LANGUAGE_FILE_ENGLISH);
			window.refreshText();
		}
		else if(m1SaveAsKey.equalsIgnoreCase(item.getName()))
		{
			saveAsWindow();
		}
		else if(m1SaveKey.equalsIgnoreCase(item.getName()))
		{
			saveOnly();
		}
		else if(m1OpenKey.equalsIgnoreCase(item.getName()))
		{
			openFileWindow();
		}
		else if(m1NewKey.equalsIgnoreCase(item.getName()))
		{
			newFile();
		}
		else if(m1ExitKey.equalsIgnoreCase(item.getName()))
		{
			System.exit(0);
		}
		else if(m2StartKey.equalsIgnoreCase(item.getName()))
		{
			window.btAction.simulationStart();
		}
		else if(m2StopKey.equalsIgnoreCase(item.getName()))
		{
			window.btAction.simulationStop();
		}
		else if(m2BackKey.equalsIgnoreCase(item.getName()))
		{
			window.btAction.simulationBack();
		}
		else if(m3GeralKey.equalsIgnoreCase(item.getName()))
		{
			analyzerPetriNet();
		}
		else if(m4AboutKey.equalsIgnoreCase(item.getName()))
		{
			showAboutMessage();
		}
		
	}
	
	/**
	 * Cria e trata a Janela de di�logo para salvar o arquivo de rede de petri.
	 *
	 */
	private void saveAsWindow() 
	{
		window.fieldStatus.setTextWithKey(STATUS_MSG_11_KEY);
		
		JFileChooser saveDialog = new JFileChooser();
		
		saveDialog.addChoosableFileFilter(new FileFilterXML());
		saveDialog.setAcceptAllFileFilterUsed(false);
		
		if(saveDialog.showSaveDialog(window) == JFileChooser.APPROVE_OPTION)
		{
			file = saveDialog.getSelectedFile();
			
			try
			{
				writeFile();
				showSucessSaveFile();
			}
			catch(IOException exc)
			{
				showErrorSaveFile(exc);
			}
		}
	}
	
	/**
	 * Salva o arquivo se ele j� existir, ou abre caixa
	 * de di�logo caso ele n�o exista.
	 *
	 */
	public void saveOnly()
	{
		if(file == null || file.toString().length()==0)
		{
			saveAsWindow();
		}
		else
		{
			try
			{
				writeFile();
				showSucessSaveFile();
			}
			catch(IOException exc)
			{
				showErrorSaveFile(exc);
			}
		}
	}
	
	/**
	 * Grava o arquivo de rede de petri no formato XML.
	 *
	 */
	private void writeFile() throws IOException
	{		
		String strFileName = null;
		String ext = FileFilterUtil.getExtensionOfFile(file);
		
		if( ext.equalsIgnoreCase(FileFilterXML.EXTENSION_XML) )
			strFileName = file+"";
		else		
			strFileName = file+"."+FileFilterXML.EXTENSION_XML;
		
		if(strFileName != null)
		{
			if(window.pn != null)
			{
				XStream xStream = new XStream();
				xStream.omitField(PetriNetGraph.class, "selectedPlace");
				xStream.omitField(PetriNetGraph.class, "selectedTrans");
				xStream.omitField(PetriNetGraph.class, "selectedArc");
				
				window.pn.setNome(file.getName());
				String xml = xStream.toXML(window.pn);
				
				PrintWriter out = new PrintWriter(new FileWriter(strFileName));
				
				out.print(xml);
				out.close();
				window.setTitle(LanguageTool.getString(titleKey) + " # " + file.getName());
				
			}
			
		}
		
	}
	
	/**
	 * Exibi��o de mensagem de erro caso n�o consiga salvar o arquivo.
	 * @param exc Exce��o IOException
	 */
	private void showErrorSaveFile(IOException exc)
	{
		window.fieldStatus.setTextWithKey(ERROR_MSG_01_KEY);
		
		String errorMsg = LanguageTool.getString(ERROR_MSG_01_KEY);
		String errorTitle = LanguageTool.getString(ERROR_MSG_02_KEY);
		JOptionPane.showMessageDialog(null,errorMsg+"\n"+exc,errorTitle,JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Exibe na barra de status uma mensagem informando que o arquivo
	 * foi salvo com sucesso.
	 */
	private void showSucessSaveFile()
	{
		window.fieldStatus.setTextWithKey(STATUS_MSG_12_KEY);
	}
	
	/**
	 * Cria janela de di�logo para abrir o arquivo.
	 *
	 */
	private void openFileWindow()
	{
		window.fieldStatus.setTextWithKey(STATUS_MSG_13_KEY);
		
		JFileChooser openDialog = new JFileChooser();
		
		openDialog.addChoosableFileFilter(new FileFilterXML());
		openDialog.setAcceptAllFileFilterUsed(false);
		
		if(openDialog.showOpenDialog(window) == JFileChooser.APPROVE_OPTION)
		{
			file = openDialog.getSelectedFile();
			
			try
			{
				openFile();
				showSucessOpenFile();
			}
			catch(IOException exc)
			{
				showErrorOpenFile(exc);
			}
			catch(ClassCastException exc)
			{
				showErrorCastOpenFile(exc);
			}
			catch(Exception exc)
			{
				showErrorOpenFile(exc);
			}
		}
	}
	
	/**
	 * L� o arquivo em formato XML e o converte para Objeto rede
	 * de Petri.
	 * @throws IOException
	 * @throws ClassCastException
	 */
	private void openFile() throws IOException, ClassCastException
	{
		String strFileName = file.toString();
		FileInputStream fileInput = new FileInputStream(strFileName);
		
		XStream xStream = new XStream();
		PetriNetGraph pn = (PetriNetGraph) xStream.fromXML(new InputStreamReader(fileInput));
		
		pn.deselectArc();
		pn.deselectPlace();
		pn.deselectTransition();
		
		window.setTitle(LanguageTool.getString(titleKey) + " # " + file.getName());
		
		window.pn = pn;
		window.editor.repaint();
	}
	
	/**
	 * Exibe mensagem de sucesso na barra de satus 
	 * informando que o arquivo foi aberto com sucesso.
	 *
	 */
	private void showSucessOpenFile()
	{
		window.fieldStatus.setTextWithKey(STATUS_MSG_14_KEY);
	}
	
	/**
	 * Exibe mensagem de erro na tela informando
	 * falha ao tentar ler o arquivo.
	 * @param exc Exce��o
	 */
	private void showErrorOpenFile(Exception exc)
	{
		window.fieldStatus.setTextWithKey(ERROR_MSG_03_KEY);
		
		String errorMsg = LanguageTool.getString(ERROR_MSG_03_KEY);
		String errorTitle = LanguageTool.getString(ERROR_MSG_02_KEY);
		JOptionPane.showMessageDialog(null,errorMsg+"\n"+exc,errorTitle,JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Exibe mensagem na tela informando falha ao tentar
	 * converter o arquivo.
	 * @param exc Exce��o
	 */
	private void showErrorCastOpenFile(ClassCastException exc)
	{
		window.fieldStatus.setTextWithKey(ERROR_MSG_04_KEY);
		
		String errorMsg = LanguageTool.getString(ERROR_MSG_04_KEY);
		String errorTitle = LanguageTool.getString(ERROR_MSG_03_KEY);
		JOptionPane.showMessageDialog(null,errorMsg+"\n"+exc,errorTitle,JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Limpa a janela de edi��o e prepara para come�ar uma nova rede de petri.
	 */
	private void newFile()
	{
		window.pn = new PetriNetGraph();
		window.arcAction.disableSimulationButton();
		window.arcAction.disableArcInfo();
		window.transAction.disableTransitionInfo();
		window.placeAction.disablePlaceInfo();
		
		window.fieldStatus.setTextWithKey(STATUS_MSG_15_KEY);
		window.editor.repaint();
		
		this.file = null;
		window.setTitle(LanguageTool.getString(titleKey));
		
		cleanInfoPanel();
	}
	
	/**
	 * Trata evento de An�lise das Propriedades da Rede de Petri
	 *
	 */
	public void analyzerPetriNet()
	{
		if(AnalyzerAction.verifyPn((ImplPetriNetBase)window.pn) == true)
		{
			window.anAction = new AnalyzerAction((ImplPetriNetBase) window.pn);
			window.anAction.showSimulationWindow();	
		}
		
	}
	
	
	/**
	 * Exibe Mensagem Sobre na Tela
	 *
	 */
	public void showAboutMessage()
	{
		String strMsg01 = LanguageTool.getString(ABOUT_MSG_01_KEY);
		String strMsg02 = LanguageTool.getString(ABOUT_MSG_02_KEY);
		String strMsg03 = LanguageTool.getString(ABOUT_MSG_03_KEY);
		
		JOptionPane.showMessageDialog(window,strMsg01+ "\n\n" + strMsg02, strMsg03, JOptionPane.INFORMATION_MESSAGE);
	}
}
