package br.uerj.fileutil;

import java.io.File;

/**
 * Classe com m�todos est�ticos para auxiliar
 * manipula��o de arquivos.
 * 
 * @author Felipe Lino
 * <BR>Data: 09/02/2007
 * <BR>Atualizado: 10/02/2007
 */

public class FileFilterUtil
{
	/**
	 * Retorna a extens�o do arquivo, ou seja, a String ap�s o �ltimo ".".
	 * Caso nao exista extens�o retorna a String vazia.
	 * @param file Arquivo
	 * @return Extens�o do arquivo caso exista, sen�o retorna String vazia.
	 */
	public static String getExtensionOfFile(File file)
	{
		String ext = "";
		String fileName = file.getName();
		if(fileName != null)
		{
			fileName = fileName.trim();
			int pos = fileName.lastIndexOf(".");
			
			if((pos > -1) && (pos+1<=fileName.length()))
			{
				ext = fileName.substring(pos+1).trim();
				if(ext.length() <= 0)
					ext = "";
			}
		}		
		return ext.toLowerCase();
	}
}
