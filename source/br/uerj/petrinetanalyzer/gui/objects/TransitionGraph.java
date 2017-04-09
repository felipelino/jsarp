package br.uerj.petrinetanalyzer.gui.objects;

import br.uerj.petrinetanalyzer.common.TransitionBase;

/**
 * Esta classe é a classe para definição de transições
 * (transitions) em Redes de Petri. Com os atributos necessários
 * para o desenho da parte gráfica.
 * 
 * @author Felipe Lino
 * <BR>Data: 08/01/2007
 * <BR>Atualização: 09/02/2007
 */
public class TransitionGraph extends TransitionBase
{
	/**
	 * Constantes de Orientação do Desenho da Transição
	 */
	public static final int ORIENTATION_VERTICAL   	= 1;
    public static final int ORIENTATION_DIAGONAL1  	= 2;
    public static final int ORIENTATION_HORIZONTAL 	= 3;
    public static final int ORIENTATION_DIAGONAL2  	= 4;
    public static final int ORIENTATION_ALL 		= 5;

	public static final int ALTURA = 6;
	public static final int COMPRIMENTO = 26; 
	
	private int orientation;
	private int x;
	private int y;
	
	/**
	 * Construtor default.
	 */
	public TransitionGraph()
	{
		
	}
	
	/**
	 * Construtor que recebe as coordenadas X e Y da posição
	 * do objeto na tela e a posição i no Array de Transições da rede.
	 * @param x Coordenada x
	 * @param y Coordenada y
	 * @param i Posição no Array de Transições da Rede de Petri.
	 */
	public TransitionGraph(int x, int y, int i)
	{
		super(i);
		this.x = x;
		this.y = y;
		this.orientation = ORIENTATION_VERTICAL;
	}
	
	/**
	 * Construtor que recebe como parâmetro apenas a posição no Array
	 * de transição da Rede.
	 * @param i Posição no Array de transição da rede
	 */
	public TransitionGraph(int i)
	{
		super(i);
		this.x = 0;
		this.y = 0;
		this.orientation = ORIENTATION_VERTICAL;
	}
	
	/**
	 * Seta a coordenada X.
	 * @param x Coorenada x
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
	 */
	public void setXY(int x, int y)
	{
		setX(x);
		setY(y);
	}

	/**
	 * Retorna o valor da coordenada x.
	 * @return Coordenada x
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Retorna o valor da coordenada y.
	 * @return Coordenada y
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Retorna a Orientação.
	 * @return orientação
	 */
	public int getOrientation()
	{
		return orientation;
	}
	
	/**
	 * Seta a orinentação.
	 * @param orientation orientação
	 */
	 public void setOrientation(int orientation)
	 {
		 this.orientation = orientation;
	 }
	 
	 /**
	  * Rotaciona a orientação.
	  *
	  */
	 public void cycleOrientation() 
	 {
	        switch (orientation)
	        {/*
	        	case ORIENTATION_VERTICAL:
	        		orientation = ORIENTATION_DIAGONAL1;
	        		break;
	        	case ORIENTATION_DIAGONAL1:
	        		orientation = ORIENTATION_HORIZONTAL;
	        		break;
	        	case ORIENTATION_HORIZONTAL:
	        		orientation = ORIENTATION_DIAGONAL2;
	        		break;
	        	case ORIENTATION_DIAGONAL2:
	        		orientation = ORIENTATION_ALL;
	        		break;
	        	case ORIENTATION_ALL:
	        		orientation = ORIENTATION_VERTICAL;
	        		break; */
	        	case ORIENTATION_VERTICAL:
	        		orientation = ORIENTATION_HORIZONTAL;
	        		break;
	        	case ORIENTATION_HORIZONTAL:
	        		orientation = ORIENTATION_VERTICAL;
	        		break;
	        }
	    }
	 
	 /**
	  * Calcula a distância da transição até um 
	  * ponto fornecido como parâmetro.
	  * @param x Coordenada x do ponto 
	  * @param y Coordenada y do ponto
	  * @return Distância do ponto até a transição
	  */
	 public double distance(double x, double y)
	 {
		 double dist;
		 double dx;
		 double dy;
		 dx = (this.x - x);
		 dy = (this.y - y);
		 dist = Math.sqrt(dx*dx + dy*dy);
		 return dist;
	}
	 
	 /**
	  * Verifica se o ponto está na transição.
	  * @param x Coordenada X
	  * @param y Coordenada Y
	  * @return Retorna true se o ponto está na transição, e false caso contrário.
	  */
	public boolean inTransition(int x, int y)
	{
		/*
		int supEsqX = this.x - COMPRIMENTO/2;
		int supEsqY = this.y - ALTURA/2;
		
		int infDirX = this.x + COMPRIMENTO/2;
		int infDirY = this.y + ALTURA/2;
		
		System.out.println("( "+x+" , "+y+" )" +
				"\n S( "+supEsqX+" , "+supEsqY+" )" +
				"\n I( "+infDirX+" , "+infDirY+" )"); 
		
		if( x >= supEsqX &&
			x <= infDirX &&
			y <= supEsqY &&
			y >= infDirY)
			return true;
		else
			return false; */
		
		if(distance(x,y) < COMPRIMENTO/2)
			return true;
		else
			return false;
	}
	 
	/**
	 *  Verifica se o objeto passado como parâmetro é igual
	 * ao objeto.
	 * 
	 * @return Retorna true caso os atributos dos objetos sejam iguais. 
	 * Caso contrário retorna false
	 */
	 public boolean equals(Object obj)
	 {
		 if(obj == null)
			return false;
			
		if(!( obj instanceof PlaceGraph))
			return false;
			
		TransitionGraph t = (TransitionGraph) obj;
		if(t == this)
			return true;
			
		if( (this.getX() == t.getX()) &&
			(this.getY() == t.getY()) &&
			(this.getOrientation() == t.getOrientation()) &&
			(this.getCurvaDensidade() == t.getCurvaDensidade()) &&
			(this.getIdentificador() == t.getIdentificador()) &&
			(this.getNome() == t.getNome()) &&
			(this.getPosicao() == t.getPosicao() ) &&
			(this.getSEFT() == t.getSEFT()) &&
			(this.getSLFT() == t.getSLFT()) )
		{
			return true;
		}
		else
			return false;
	 }
}
