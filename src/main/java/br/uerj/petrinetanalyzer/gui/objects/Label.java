package br.uerj.petrinetanalyzer.gui.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

/**
 * Classe de defini��o dos r�tulos e texto livre a ser colocado no desenho das
 * redes de Petri.
 * 
 * @author Felipe Lino
 * <BR>Data: 20/10/2007
 * <BR>Atualiza��o: 20/10/2007
 * @version 2.0
 */
public class Label
{
	private Point infEsq;
	private String strText = null;
	
	private int posicao;
	
	private static Font font;
	private static Color cor;
	private static Color fontCor;
	
	public static final int LARGURA_LETRA = 5;
	public static final int ALTURA_LETRA  = 10;
	private int largura;
	
	public Label()
	{
		super();
		font = new Font("Arial",Font.BOLD,10);
	}
	
	/**
	 * Seta o texto do r�tulo.
	 * @param texto texto do r�tulo
	 * @since 2.0
	 */
	public void setTexto(String texto)
	{
		this.strText = texto; 
		largura = getTexto().length() * LARGURA_LETRA;
	}
	
	/**
	 * Retorna o texto do r�tulo.
	 * @return texto do r�tulo
	 * @since 2.0
	 */
	public String getTexto()
	{
		return strText;
	}
	
	/**
	 * Retorna o ponto superior esquerdo.
	 * @return ponto superior esquerdo.
	 * @since 2.0
	 */
	public Point getPontoInicial()
	{
		return infEsq;
	}
	
	
	public Point getPontoSupEsquerdo()
	{
		Point p = new Point(infEsq.x , infEsq.y - ALTURA_LETRA);
		
		return p;
	}
		
	/**
	 * Seta a posi��o do r�tulo no array de r�tulos.
	 * @param posicao Posi��o no array de r�tulos.
	 * @since 2.0
	 */
	public void setPosicao(int posicao)
	{
		this.posicao = posicao;
	}
	
	/**
	 * Retorna a posi��o do r�tulo no array de r�tulos.
	 * @return Posi��o do r�tulo no array de r�tulos
	 * @since 2.0
	 */
	public int getPosicao()
	{
		return posicao;
	}
	
	/**
	 * Seta a Fonte a ser usada no texto.
	 * @param font Fonte do texto
	 * @since 2.0
	 */
	public void setFont(Font font)
	{
		this.font = font;
	}
	
	/**
	 * Retorna a fonte usada no texto.
	 * @return Fonte do texto
	 * @since 2.0
	 */
	public Font getFont()
	{
		return font;
	}
	
	/**
	 * Seta a cor do ret�ngulo que envolve o r�tulo.
	 * @param cor Cor 
	 * @since 2.0
	 */
	public void setColor(Color cor)
	{
		this.cor = cor;
	}
	
	/**
	 * Retorna a cor do ret�ngulo que envolve o r�tulo
	 * @return Cor
	 * @since 2.0
	 */
	public Color getColor()
	{
		return cor;
	}
	
	/**
	 * Seta a cor da fonte do texto
	 * @param cor Cor 
	 * @since 2.0
	 */
	public void setFontColor(Color cor)
	{
		this.fontCor = cor;
	}
	
	/**
	 * Retorna a cor da fonte do texto.
	 * @return Cor
	 * @since 2.0
	 */
	public Color getFontColor()
	{
		return fontCor;
	}
	
	public int getLargura()
	{
		return largura;
	}
	
	
	/**
	 * Verifica se o ponto recebido como par�metro est� sobre o r�tulo.
	 * Caso esteja retorna true, caso contr�rio retorna false.
	 * @return Retorna true caso o ponto recebido como par�metro esteja sobre o r�tulo e
	 * retorna false caso contr�rio
	 * @since 2.0
	 */
	public boolean inLabel(int x, int y)
	{
		if( (y >= infEsq.y - ALTURA_LETRA) && (x >= infEsq.x)
		  &&(y <= infEsq.y) && (x <= infEsq.x + getLargura()))
		{		
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Move o ret�ngulo do r�tulo para uma nova posi��o X, Y.
	 * @param infEsqX Coordenada X do ponto inferior esquerdo.
	 * @param infEsqY Coordenada Y do ponto inferior esquerdo.
	 * @since 2.0 
	 */
	public void moveLabel(int infEsqX, int infEsqY)
	{
		infEsq = new Point(infEsqX, infEsqY);
	}

	/**
	 * Sobrescreve o m�todo toString da classe Object.
	 * @return retorna o texto do r�tulo
	 */
	public String toString()
	{
		return this.getTexto();
	}
	
}
