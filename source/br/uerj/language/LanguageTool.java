package br.uerj.language;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;


/**
 * Classe Auxiliar para suporte multilingue.
 *  
 * @author Felipe Lino
 * <BR>Data: 04/01/2007
 * <BR>Atualizado: 01/03/2007
 */
public class LanguageTool
{
	/**
	 * Nome do arquivo Properties com o mapeamento Chave e Conteúdo
	 */
	private static String propLanguageFileName = null;
	/**
	 * Objeto Properties com o mapeamento Chave e Conteúdo
	 */
	private static Properties langProperties = null;
	/**
	 * Map que faz o mapeamento Chave e Conteúdo default
	 * Usada quando o properties não puder ser carregado ou alguma chave não for encontrada
	 * no properties
	 */
	private static HashMap map = null;
	
	/**
	 * Seta o idioma default a ser usado logo no início do programa.
	 * Passando o nome do arquivo properties a ser consultado.
	 * @param strFile Nome do arquivo Properties 
	 */
	public static void setLanguageDefault(String strFile)
	{
		propLanguageFileName = strFile;
	}
	
	/**
	 * Seta o idioma a ser usado passando o nome do arquivo properties a ser consultado.
	 * @param strFile Nome do arquivo Properties
	 */
	public static void changeLanguage(String strFile)
	{
		propLanguageFileName = strFile;
		loadPropertiesLanguage();
	}
	/**
	 * Carrega o arquivo properties com o idioma escolhido	 
	 */
	public static void loadPropertiesLanguage()
	{
		InputStream input = null;
		langProperties  = null;
		try
		{ 
			if(propLanguageFileName == null)
				throw new FileNotFoundException("File name is null");
				
			input = new java.io.FileInputStream(propLanguageFileName);
			if(input != null)
			{
				langProperties = new Properties();
				langProperties.load(input);
			}
		}
		catch(FileNotFoundException exc)
		{
			langProperties = null;
		}
		catch(IOException exc)
		{
			langProperties = null;
		}
	}
	
	/**
	 * Adiciona o par Chave e Conteúdo a Map. O conteúdo será a String
	 * default a ser usada caso o properties não possa ser carregado ou a chave
	 * não seja encontrada.
	 * @param strKey Chave
	 * @param strContent Conteúdo
	 */
	public static void addString(String strKey, String strContent)
	{
		if(map == null)
			map = new HashMap();
			
		map.put(strKey, strContent);
	}
		
	/**
	 * Retorna String para chave fornecida, buscando no arquivo de properties
	 * do idioma selecionado.
	 * @param strKey Chave a ser buscada no arquivo de properties
	 * @return String do arquivo de properties do idioma selecionado, ou default caso
	 * não seja encontrado a chave no arquivo properties.
	 */
	public static String getString(String strKey)
	{
		if(map != null)
		{
			
			if(langProperties == null)
				return (String) map.get(strKey);
			else
				return langProperties.getProperty(strKey, (String)map.get(strKey));
		}
		else
		{
			if(langProperties != null)
			{
				return langProperties.getProperty(strKey);
			}
			else
				return null;
		}
	}

}
