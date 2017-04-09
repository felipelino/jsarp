package br.uerj.FileFilter;


import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Classe que define um filtro para arquivos XML.
 * 
 * @author Felipe Lino
 * <BR>Data: 09/02/2007
 * <BR>Atualizado: 18/02/2007
 */
public class FileFilterXML extends FileFilter
{
	/**
	 * Descreve extens�o de arquivo XML.
	 */
	public static final String EXTENSION_XML = "xml";

	/**
	 * M�todo que descreve quais tipos de arquivos s�o aceitos
	 * por esse filtro. Aqui apenas arquivos XML.
	 */
    public boolean accept(File file) 
    {
        if (file.isDirectory()) 
        {
            return true;
        }

        String extension = FileFilterUtil.getExtensionOfFile(file);
        if (extension != null) 
        {
            if(extension.equalsIgnoreCase(EXTENSION_XML))
            {
            	return true;
            }
            else 
            {
                return false;
            }
        }

        return false;
    }

    /**
     * Retorna a descri��o para esse tipo de arquivo
     */
    public String getDescription() 
    {
        return "XML File";
    }
}
