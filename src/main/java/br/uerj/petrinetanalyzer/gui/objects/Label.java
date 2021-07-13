package br.uerj.petrinetanalyzer.gui.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

/**
 * Classe de definição dos rótulos e texto livre a ser colocado no desenho das
 * redes de Petri.
 * 
 * @author Felipe Lino
 * <BR>Data: 20/10/2007
 * <BR>Atualização: 20/10/2007
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
	 * Seta o texto do rótulo.
	 * @param texto texto do rótulo
	 * @since 2.0
	 */
	public void setTexto(String texto)
	{
		this.strText = texto; 
		largura = getTexto().length() * LARGURA_LETRA;
	}
	
	/**
	 * Retorna o texto do rótulo.
	 * @return texto do rótulo
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
	 * Seta a posição do rótulo no array de rótulos.
	 * @param posicao Posição no array de rótulos.
	 * @since 2.0
	 */
	public void setPosicao(int posicao)
	{
		this.posicao = posicao;
	}
	
	/**
	 * Retorna a posição do rótulo no array de rótulos.
	 * @return Posição do rótulo no array de rótulos
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
	 * Seta a cor do retângulo que envolve o rótulo.
	 * @param cor Cor 
	 * @since 2.0
	 */
	public void setColor(Color cor)
	{
		this.cor = cor;
	}
	
	/**
	 * Retorna a cor do retângulo que envolve o rótulo
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
	 * Verifica se o ponto recebido como parâmetro está sobre o rótulo.
	 * Caso esteja retorna true, caso contrário retorna false.
	 * @return Retorna true caso o ponto recebido como parâmetro esteja sobre o rótulo e
	 * retorna false caso contrário
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
	 * Move o retângulo do rótulo para uma nova posição X, Y.
	 * @param infEsqX Coordenada X do ponto inferior esquerdo.
	 * @param infEsqY Coordenada Y do ponto inferior esquerdo.
	 * @since 2.0 
	 */
	public void moveLabel(int infEsqX, int infEsqY)
	{
		infEsq = new Point(infEsqX, infEsqY);
	}

	/**
	 * Sobrescreve o método toString da classe Object.
	 * @return retorna o texto do rótulo
	 */
	public String toString()
	{
		return this.getTexto();
	}
	
}
