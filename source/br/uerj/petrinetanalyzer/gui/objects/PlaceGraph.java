package br.uerj.petrinetanalyzer.gui.objects;


import java.awt.Point;

import br.uerj.petrinetanalyzer.common.PlaceBase;
/**
 * Esta classe é a classe para definição de Lugares
 * (Places) em Redes de Petri. Com os atributos necessários
 * para o desenho da parte gráfica.
 * 
 * @author Felipe Lino
 * <BR>Data: 08/01/2007
 * <BR>Atualização: 21/02/2007
 */
public class PlaceGraph extends PlaceBase
{
	/**
	 * Indica qual o raio do desenho do círculo que representa
	 * o Lugar.
	 */
	public static final int RAIO = 12;
	
	/**
	 * Coordenada X do objeto na tela.
	 */
	private int x;
	
	/**
	 * Coordenada Y do objeto na tela.
	 */
	private int y;
	
	/**
	 * Construtor default.
	 * @since 1.0
	 */
	public PlaceGraph()
	{
		
	}
	
	
	/**
	 * Construtor que recebe as coordenadas X e Y da posição
	 * do objeto na tela e a posição i no Array de Lugares da rede.
	 * @param x Coordenada x
	 * @param y Coordenada y
	 * @param i Posição no Array de Lugares da Rede de Petri.
	 * @since 1.0
	 */
	public PlaceGraph(int x, int y, int i)
	{
		super(i);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Construtor que recebe como parâmetro apenas a posição no Array
	 * de Lugares da Rede.
	 * @param i Posição no Array de Lugares da rede
	 * @since 1.0
	 */
	
	public PlaceGraph(int i)
	{
		super(i);
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Seta a coordenada X.
	 * @param x Coorenada x
	 * @since 1.0
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * Seta a coordenada Y.
	 * @param y Coordenada y
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 * Seta ambas as Coordenadas X e Y.
	 * @param x Coordenada x
	 * @param y Coordenada y
	 * @since 1.0
	 */
	public void setXY(int x, int y)
	{
		setX(x);
		setY(y);
	}
	
	/**
	 * Retorna o valor da coordenada x.
	 * @return Coordenada x
	 * @since 1.0
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Retorna o valor da coordenada y.
	 * @return Coordenada y
	 * @since 1.0
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Verifica se o objeto passado como parâmetro é igual
	 * ao objeto.
	 * 
	 * @return Retorna true caso os atributos dos objetos sejam iguais. 
	 * Caso contrário retorna false
	 * @since 1.0
	 */
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		
		if(!( obj instanceof PlaceGraph))
			return false;
		
		PlaceGraph p = (PlaceGraph) obj;
		if(p == this)
			return true;
		
		if( (this.getX() == p.getX()) &&
		    (this.getY() == p.getY()) &&
		    (this.getFichas() == p.getFichas()) &&
			(this.getIdentificador() == p.getIdentificador()) &&
			(this.getNome() == p.getNome()) &&
			(this.getPosicao() == p.getPosicao()) )
		{
			return true;
		}
		else
			return false;	
	}
	
	/**
	 * Calcula a distância do Lugar para um ponto recebido como 
	 * parâmetro.
	 * 
	 * @param x Coordenada x
	 * @param y Corrdenada y
	 * @return Distância do Lugar para o ponto dado
	 * @since 1.0
	 */
	public double distance(double x, double y)
	{
	    double dx = (this.x - x);
	    double dy = (this.y - y);
	    double dist = Math.sqrt(dx*dx + dy*dy);
	      
	    return dist;
	}
	
	/**
	 * Verifica se o ponto dado está ou não no Lugar.
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 * @return Retorna true caso o ponto dado esteja no Lugar, retorna false caso contrário.
	 * @since 1.0
	 */
	public boolean inPlace(int x, int y)
	{
		if(distance(x,y) < RAIO)
			return true;
		else
			return false;
	}
	
	/**
	 * Retorna o Ponto Extremo (Sul, Norte, Leste ou Oeste), o que for mais próximo 
	 * do ponto recebido como parâmetro.
	 * @param q 
	 * @return Ponto extremo, mais próximo do recebido como parâmetro.
	 * @since 1.0
	 */
	public Point getExtremePoint(Point q)
	{
		/*
		 *       N
		 *       |
		 *       |
		 * O --------- L
		 * 		 |
		 * 		 |
		 * 		 S
		 * 
		 */
		
		Point norte = getExtremeNorte();
		Point sul	= getExtremeSul();
		
		Point oeste = getExtremeOeste();
		Point leste = getExtremeLeste();
		double distNorte = Point.distanceSq(q.x, q.y, norte.x, norte.y);
		double distSul	 = Point.distanceSq(q.x, q.y,   sul.x,   sul.y);
		double distOeste = Point.distanceSq(q.x, q.y, oeste.x, oeste.y);
		double distLeste = Point.distanceSq(q.x, q.y, leste.x, leste.y);
		
		Point ret1 = null;
		Point ret2 = null;
		
		if(distLeste <= distOeste)
			ret1 = leste;
		else
			ret1 = oeste;
		
		if(distNorte <= distSul)
			ret2 = norte;
		else
			ret2 = sul;
		
		double distRet1 = Point.distanceSq(q.x, q.y, ret1.x, ret1.y);
		double distRet2 = Point.distanceSq(q.x, q.y, ret2.x, ret2.y);
		
		if(distRet1 <= distRet2+5)
			return ret1;
		else
			return ret2;
		
	}
	
	/**
	 * Retorna o Ponto Extremo Norte do Lugar.
	 * @return Ponto ao Norte do Lugar
	 * @since 1.0
	 */
	public Point getExtremeNorte()
	{
		return new Point(this.x, this.y - RAIO);
	}
	
	/**
	 * Retorna o Ponto Extremo Sul do Lugar.
	 * @return Ponto ao Sul do Lugar
	 * @since 1.0
	 */
	public Point getExtremeSul()
	{
		return new Point(this.x, this.y + RAIO);
	}
	
	/**
	 * Retorna o Ponto Extremo Oeste do Lugar.
	 * @return Ponto ao Oeste do Lugar
	 * @since 1.0
	 */
	public Point getExtremeOeste()
	{
		return new Point(this.x - RAIO, this.y);
	}
	
	/**
	 * Retorna o Ponto Extremo Leste do Lugar.
	 * @return Ponto ao Leste do Lugar
	 * @since 1.0
	 */
	public Point getExtremeLeste()
	{
		return new Point(this.x + RAIO, this.y);
	}
}
