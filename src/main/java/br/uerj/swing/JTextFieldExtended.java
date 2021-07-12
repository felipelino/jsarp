
package br.uerj.swing;

import javax.swing.JTextField;

import br.uerj.language.LanguageTool;

/**
 * Herda de JTextField, para usar suporte Multilingue mais facilmente.
 * 
 * @author Felipe Lino
 * <BR>Data: 04/01/2007
 * <BR>Atualizado: 04/01/2007
 */
public class JTextFieldExtended extends JTextField
{
	/* Variável requisitada na herança de JTextField */
	static final long serialVersionUID = 1;
	
	/**
	 * String que representa a chave a ser usada para o conteúdo 
	 * da caixa de texto.
	 */
	private String textKey;
	
	public JTextFieldExtended(String name)
	{
		super(name);
		setName(name);
		setTextWithKey(name);
	}
	
	/**
	 * Seta o valor da chave.
	 * @param textKey Chave
	 */
	public void setTextKey(String textKey)
	{
		this.textKey = textKey;
	}
	
	/**
	 * Seta o conteúdo da caixa de texto usando a chave.
	 *
	 */
	public void setTextWithKey()
	{
		String text = LanguageTool.getString(getTextKey());
		
		super.setText(text);	
	}
	
	/**
	 * Seta o conteúdo da caixa de texto usando a chave recebida como
	 * parâmetro.
	 * @param key
	 */
	public void setTextWithKey(String key)
	{
		setTextKey(key);
		setTextWithKey();
	}
	
	/**
	 * Retorna o valor da chave.
	 * @return chave
	 */
	public String getTextKey()
	{
		return textKey;
	}
}
