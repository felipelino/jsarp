package br.uerj.fileutil;

import java.io.File;

/**
 * Classe com métodos estáticos para auxiliar
 * manipulação de arquivos.
 * 
 * @author Felipe Lino
 * <BR>Data: 09/02/2007
 * <BR>Atualizado: 10/02/2007
 */

public class FileFilterUtil
{
	/**
	 * Retorna a extensão do arquivo, ou seja, a String após o último ".".
	 * Caso nao exista extensão retorna a String vazia.
	 * @param file Arquivo
	 * @return Extensão do arquivo caso exista, senão retorna String vazia.
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
