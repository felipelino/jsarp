package br.uerj.petrinetanalyzer.gui.objects;


import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import br.uerj.petrinetanalyzer.common.ArcBase;
import br.uerj.petrinetanalyzer.common.PlaceBase;
import br.uerj.petrinetanalyzer.common.TransitionBase;

/**
 * Esta classe é a classe para definição de Arcos
 * (Arcs) em Redes de Petri. Com os atributos necessários
 * para o desenho da parte gráfica.
 * 
 * @author Felipe Lino
 * <BR>Data: 08/01/2007
 * <BR>Atualização: 03/03/2007
 * @version 1.0
 */
public class ArcGraph extends ArcBase
{
	
	private ArrayList listPoint = null;
	private Point  selectedPoint = null;	
	public static final int DiSTANCIA_MAX = 3;
	
	
	private static final int EXTREMO_NORTE = 0;
	private static final int EXTREMO_SUL   = 1;
	private static final int EXTREMO_LESTE = 2;
	private static final int EXTREMO_OESTE = 3;
	
	private int extreme = EXTREMO_NORTE;
		
	/**
	 * Construtor default
	 * @since 1.0
	 */
	public ArcGraph()
	{
		
	}
	
	/**
	 * Define quais os objetos são origem e destino do arco.
	 * @param start Pode ser Lugar ou Transição de início
	 * @param end Pode ser Lugar ou Transição de fim
	 * @since 1.0
	 */
	public void setStartEndObjects(Object start, Object end)
	{
		if( ((start instanceof PlaceBase) && (end instanceof TransitionBase)) 
			|| ((start instanceof TransitionBase) && (end instanceof PlaceBase)) 
		  )
		{
			setStartObject(start);
			setEndObject(end);
		}
	}
	
	/**
	 * Define qual objeto é o de origem do arco.
	 * @param obj Objeto de origem pode ser Lugar ou Transição
	 * @since 1.0
	 */
	public boolean setStartObject(Object obj)
	{
		if(obj instanceof PlaceGraph)
		{
			setLugar((PlaceBase) obj);
			setEntrada();
			
			Point p = getStartPoint();
			listPoint = new ArrayList();
			listPoint.add(0,p);
			
			return true;
		}
		else
		{
			if(obj instanceof TransitionGraph)
			{
				setTransicao((TransitionBase) obj);
				setSaida();
				
				Point p = getStartPoint();
				
				listPoint = new ArrayList();
				listPoint.add(0,p);
				
				return true;
			}
		}    
		
		return false;
	}
	
	/**
	 * Define qual o objeto é o destino do arco.
	 * @param obj Pode ser Lugar ou Transição
	 * @return Retorna true se for possível definir o objeto destino do arco.
	 * @since 1.0
	 */
	public boolean setEndObject(Object obj)
	{
		if( (obj instanceof PlaceGraph) && (verifySaida()) )
		{
			PlaceGraph objeto = (PlaceGraph) obj;
			setLugar((PlaceBase) obj);
			
			Point p = getEndPoint();
			listPoint.add(p);
			
			return true;
		}
		else
		{
			if( (obj instanceof TransitionGraph) && (verifyEntrada()) )
			{
				TransitionGraph objeto = (TransitionGraph) obj;
				setTransicao((TransitionBase) obj);
								
				Point p = new Point(objeto.getX(), objeto.getY());
				listPoint.add(p);
			
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Adiciona um ponto ao arco.
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 * @since 1.0
	 */
	public void addPoint(int x, int y)
	{
		if(listPoint != null )
		{
			Point p = new Point(x,y);
			listPoint.add(p);
		}
	}
	
	/**
	 * Retorna o Objeto que é origem do arco.
	 * @return objeto origem do arco
	 * @since 1.0
	 */
	public Object getObjectStart()
	{
		if( this.verifyEntrada() )
			return getLugar();
		else
			return getTransicao();
	}
	
	/**
	 * Retorna Objeto destino do arco.
	 * @return objeto destino do arco
	 * @since 1.0
	 */
	public Object getObjectEnd()
	{
		if( this.verifySaida() )
			return getLugar();
		else
			return getTransicao();
	}
	
	/**
	 * Retorna o Ponto na posição especificada na Lista de Pontos.
	 * @param posicao
	 * @return Ponto na posição especificada na Lista de Pontos.
	 * @since 1.0
	 */
	public Point getPoint(int posicao)
	{
		if(listPoint != null)
		{
			if((posicao <= listPoint.size() -1)&&(posicao >=0))
				return (Point) listPoint.get(posicao);
		}
		
		return null;
	}
	
	/**
	 * Retorna o número de pontos do arco.
	 * @return Quantidade de pontos que formam o arco.
	 * @since 1.0
	 */
	public int getNumPoints()
	{
		if(listPoint != null)
			return listPoint.size();
		else
			return 0;
	}
	
	/**
	 * Retorna a Lista de Pontos.
	 * @return Lista de Pontos que forma o arco
	 * @since 1.0
	 */
	public ArrayList getListPoint()
	{
		return listPoint;
	}
	
	/**
	 * Verifica seu o Ponto está em no arco
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 * @return True caso o ponto dado, esteja em algum dos segmentos que formam
	 * o arco, retorna false caso contrário.
	 * @since 1.0
	 */
	public boolean inArc(int x, int y)
	{
		Point test = new Point(x,y);
		for(int i=0; i < getNumPoints()-1; i++ )
		{
			Point start = getPoint(i);
			Point end	= getPoint(i+1);
			if(inRect(start,end,test))
			{
				if((i==0)&&(getNumPoints() > 2))
				{
					selectedPoint = end;
				}
				else if((i+1 == getNumPoints()-1) &&(getNumPoints() > 2))
				{
					selectedPoint = start;
				}
				else
				{
					if(getNumPoints() > 3)
					{
						double distStart = Point.distance(start.x, start.y, x,y);
						double distEnd	 = Point.distance(end.x, end.y, x,y);
						if((distStart <= distEnd) && (!start.equals(getPoint(0))))
							selectedPoint = start;
						else
							selectedPoint = end;
					}
					else
						selectedPoint = null;
							
				}
				
				return true;
			}
				
		}
		return false;
	}
	
	/**
	 * Verifica se o Ponto dado está no segmento de reta, que 
	 * compõe o arco.
	 * @param start Inicio do segmento
	 * @param end   Fim do segmento
	 * @param test  Ponto a ser testado 
	 * @return Retorna true caso o ponto dado esteja no segmento
	 * de reta.
	 * @since 1.0
	 */
	private boolean inRect(Point start, Point end, Point test)
	{
		if(end.y < start.y)
		{
			Point aux = end;
			end = start;
			start = aux;
		}
		
		Line2D.Double line = new Line2D.Double(start, end);
		int dist = Integer.MAX_VALUE;
		
		dist = (int) line.ptLineDist(test);
		if(dist < DiSTANCIA_MAX)
		{
			/* Reta perpendicular ao eixo X */			
			if(end.x == start.x)
			{
				if(test.y <=end.y && test.y >= start.y)
					return true;
				else
					return false;
			}
			else
			{
				/* Reta paralela ao eixo X */
				if(end.y == start.y)
				{
					if(end.x < start.x)
					{
						Point aux = end;
						end = start;
						start = aux;
					}
					
					if(test.x <=end.x && test.x >= start.x)
						return true;
					else
						return false;
				}
				else
				{
					/* Reta Oblíqua */
					if(((test.x < end.x) && (test.x > start.x))||
			   		((test.y <= end.y) && (test.y >= start.y)))
						return true;
					else
						return false;
				}
			}	
		}
		else
			return false;
		
	}
	
	/**
	 * Retorna o ponto do objeto inicial do arco.
	 * @return Ponto do objeto inicial do arco.
	 * @since 1.0
	 */
	public Point getStartPoint()
	{
		Point start = new Point();
		Object obj  = null;
		
		obj = this.getObjectStart();
		if(obj instanceof PlaceGraph)
		{
			PlaceGraph objeto = (PlaceGraph) obj;
			
			switch (extreme)
	        {
	        	case EXTREMO_NORTE:
	        		start = objeto.getExtremeNorte();
	        		break;
	        	case EXTREMO_LESTE:
	        		start = objeto.getExtremeLeste();
	        		break;
	        	case EXTREMO_SUL:
	        		start = objeto.getExtremeSul();
	        		break;
	        	case EXTREMO_OESTE:
	        		start = objeto.getExtremeOeste();
	        		break;
	        }
		}
		else
		{
			if(obj instanceof TransitionGraph)
			{
				TransitionGraph objeto = (TransitionGraph) obj;
				start = new Point(objeto.getX(), objeto.getY());
			}
		}
		
		return start;
	}
	
	/**
	 * Retorna o ponto do objeto final do arco.
	 * @return Ponto do objeto final do arco.
	 * @since 1.0
	 */
	public Point getEndPoint()
	{
		Point end = new Point();
		Object obj  = null;
		
		obj = this.getObjectEnd();
		if(obj instanceof PlaceGraph)
		{
			PlaceGraph objeto = (PlaceGraph) obj;
			switch (extreme)
	        {
	        	case EXTREMO_NORTE:
	        		end = objeto.getExtremeNorte();
	        		break;
	        	case EXTREMO_LESTE:
	        		end = objeto.getExtremeLeste();
	        		break;
	        	case EXTREMO_SUL:
	        		end = objeto.getExtremeSul();
	        		break;
	        	case EXTREMO_OESTE:
	        		end = objeto.getExtremeOeste();
	        		break;
	        }
		}
		else
		{
			if(obj instanceof TransitionGraph)
			{
				TransitionGraph objeto = (TransitionGraph) obj;
				end = new Point(objeto.getX(), objeto.getY());
			}
		}
		
		return end;
	}
	
	/**
	 * Rotaciona o Ponto Extremo ao redor do círculo que representa o Lugar.
	 * @since 1.0
	 *
	 */
	public void cycleExtremePoint()
	{
		switch (extreme)
        {
        	case EXTREMO_NORTE:
        		extreme = EXTREMO_LESTE;
        		break;
        	case EXTREMO_LESTE:
        		extreme = EXTREMO_SUL;
        		break;
        	case EXTREMO_SUL:
        		extreme = EXTREMO_OESTE;
        		break;
        	case EXTREMO_OESTE:
        		extreme = EXTREMO_NORTE;
        		break;
        	
        }
	}
	
	/**
	 * Atualiza os pontos iniciais e finais após mover um objeto.
	 * @since 1.0
	 *
	 */
	public void refreshEndPoints()
	{	
		int numPoints = getNumPoints();
		if(numPoints > 0)
		{
			Point start = getStartPoint();
			Point end	= getEndPoint();
			((Point)listPoint.get(0)).x = start.x;
			((Point)listPoint.get(0)).y = start.y;
			
			((Point)listPoint.get(numPoints - 1)).x = end.x;
			((Point)listPoint.get(numPoints - 1)).y = end.y;
		}
	}
	
	/**
	 * Move o ponto Selecionado para a posição X Y
	 * recebida como parâmetro.
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 * @since 1.0
	 */
	public void moveSelectedPoint(int x, int y)
	{
		if(selectedPoint != null)
		{
			selectedPoint.x = x;
			selectedPoint.y = y;
		}
	}
	
	/**
	 * Retorna um Ponto a uma distância Delta do último ponto do segmento de reta
	 * fornecido.
	 * @param start Ponto inicial do segmento de reta
	 * @param end   Ponto final do segmento de reta
	 * @param delta Distância desejada
	 * @return Ponto auxiliar.
	 * @since 1.0
	 */
	public static Point getAuxPoint(Point start, Point end, int delta)
	{
		int dH = (int)Point.distance(start.x, start.y, end.x, end.y);
		return getAuxPoint(start, end, delta, dH);
	}
	
	/**
	 * Retorna um Ponto a uma distância Delta do último ponto do segmento de reta
	 * fornecido. Usando um divisor especificado para achar o delta adequado.
	 * @param start Ponto inicial do segmento de reta
	 * @param end   Ponto final do segmento de reta
	 * @param delta Distância desejada
	 * @return Ponto auxiliar
	 * @since 1.0
	 */
	public static Point getAuxPoint(Point start, Point end, int delta, int razao)
	{
		int dX = end.x - start.x;
		if(dX < 0)
			dX = -dX;
		
		int dY = end.y - start.y;
		if(dY < 0)
			dY = -dY;
		
		int deltaX = delta * dX / razao;
		int deltaY = delta * dY / razao;
		Point aux 	   = new Point();
		
		if((end.x < start.x) && (end.y > start.y))
		{
			aux.x = end.x + deltaX;
			aux.y = end.y - deltaY;
		}
		else if((end.x >= start.x) && (end.y >= start.y))
		{
			aux.x = end.x - deltaX;
			aux.y = end.y - deltaY;
		}
		else if((end.x >= start.x) && (end.y <= start.y))
		{
			aux.x = end.x - deltaX;
			aux.y = end.y + deltaY;
		}
		else if((end.x <= start.x) && (end.y <= start.y))
		{
			aux.x = end.x + deltaX;
			aux.y = end.y + deltaY;
		}
		
		return aux;
	}
	
	/**
	 * Retorna String informando qual o valor do Extremo. Usado para debug.
	 * @return String informando qual o valor do Extremo.
	 * @since 1.0
	 */
	public String getStrExtreme()
	{
		switch (extreme)
        {
        	case EXTREMO_NORTE:
        		return "Norte";
        	case EXTREMO_LESTE:
        		return "Leste";
        	case EXTREMO_SUL:
        		return "Sul";
        	case EXTREMO_OESTE:
        		return "Oeste";
        }
		return "Sem extreme";
	}
	

}
