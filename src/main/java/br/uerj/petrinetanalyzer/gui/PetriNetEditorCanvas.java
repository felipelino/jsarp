package br.uerj.petrinetanalyzer.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import br.uerj.petrinetanalyzer.common.ArcBase;
import br.uerj.petrinetanalyzer.common.PlaceBase;
import br.uerj.petrinetanalyzer.common.TransitionBase;
import br.uerj.petrinetanalyzer.gui.interfaces.IntfMainConstants;
import br.uerj.petrinetanalyzer.gui.objects.ArcGraph;
import br.uerj.petrinetanalyzer.gui.objects.Label;
import br.uerj.petrinetanalyzer.gui.objects.PlaceGraph;
import br.uerj.petrinetanalyzer.gui.objects.TransitionGraph;

/**
 * Classe que implementa a area de desenho da rede de petri.
 * 
 * @author Felipe Lino
 * <BR>Data:11/01/2007
 * <BR>Atualizado: 20/10/2007
 */
public class PetriNetEditorCanvas extends JTabbedPane
implements IntfMainConstants, MouseListener
{
	/** Variável requisitada na herança de JTabbedPane */
	static final long serialVersionUID = 1;
	
	private int modo;
	private int subModo;
	private MainWindow window;
	
	
	/** Objeto correntemente selecionado */
	private Object selectObj = null;
	/** Objeto a ser movido */
	private Object moveObj = null;
	
	/** Arco a ser desenhado */
	private ArcGraph arco = null;
	
	/**
	 * Construtor.
	 * @param w Janela do Programa Principal
	 * @since 1.0
	 */
	PetriNetEditorCanvas(MainWindow w)
	{
		this.window = w;
		setBackground(Color.WHITE);
		setAutoscrolls(true);
	}
	
	/**
	 * Limpar o arco que está sendo desenhado.
	 * @since 1.0
	 */
	public void eraseArc()
	{
		arco = null;
	}
	
	/**
	 * Seta o modo de operação do programa.
	 * @param modo Modo de Operação do Programa.
	 * @since 1.0
	 */
	public void setModo(int modo)
	{
		this.modo = modo;
	}
	
	/**
	 * Seta o submodo de operação do programa.
	 * @param subModo Sub-modo de operação do programa.
	 * @since 1.0
	 */
	public void setSubModo(int subModo)
	{
		this.subModo = subModo;
	}
	
	/**
	 * Seta o modo e submodo de operação do programa.
	 * @param modo		Modo de Operação do Programa.
	 * @param subModo	Sub-modo de operação do Programa.
	 * @since 1.0
	 */
	public void setModo(int modo, int subModo)
	{
		this.modo = modo;
		this.subModo = subModo;
	}
	
	/**
	 * Retorna o modo de operação do programa.
	 * @return Modo de Operação do programa.
	 * @since 1.0
	 */
	public int getModo()
	{
		return modo;
	}
	
	/**
	 * Retorna Sub-modo de operação do programa.
	 * @return Sub-modo de operação do programa.
	 * @since 1.0
	 */
	public int getSubModo()
	{
		return subModo;
	}
	
	/**
	 * Invocado quando o mouse é clicado na área de desenho.
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 * @since 1.0
	 */
	public void mouseClicked(MouseEvent evt)
	{
	
		
	}
    
	/**
	 * Invocado quando o mouse entra na área de desenho
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 * @since 1.0
	 */
	public void	mouseEntered(MouseEvent evt)
	{
		repaint();
	}
    
	/**
	 * Invocado quando o mouse deixa a área de desenho
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 * @since 1.0
	 */
	public void	mouseExited(MouseEvent evt)
	{
		repaint();
	}
    
	/**
	 * Invocado quando o mouse é pressionado na área de desenho.
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 * @since 1.0
	 */
	public void	mousePressed(MouseEvent evt)
	{
		switch(subModo)
		{
			case EDIT_LUGAR	: addPlace(evt); 		break;
			case EDIT_TRANS	: addTrans(evt); 		break;
			case EDIT_LABEL : addLabel(evt);		break;
			case EDIT_ARC	: setStartArc(evt);		break;
			case EDIT_MOUSE	: selectObject(evt);	break;
			case EDIT_DELETE: deleteObject(evt); 	break;
			case DRAW_ARC	: addPointArc(evt);		break;
						
			case SIM_START	: showInfoObjectAndFireTransition(evt); 	break;
		
		}
		
		repaint();
		
	}
    
	/**
	 * Invocado quando o mouse é largado na área de desenho.
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 * @since 1.0
	 */
	public void	mouseReleased(MouseEvent evt)
	{
		switch(subModo)
		{
			case EDIT_MOUSE: moveObject(evt); break;
		}
		repaint();
		
	}
    
	/**
	 * Adiciona um Lugar caso o ponto clicado esteja fora de algum
	 * objeto existente.
	 * @param evt Evento do Mouse
	 * @since 1.0
	 */
	public void addPlace(MouseEvent evt)
	{
		int x = evt.getX();
		int y = evt.getY();
		
		if(window.pn.verifyPosition(x,y) == false)
		{
			PlaceGraph place = new PlaceGraph(x, y, window.pn.getNumLugar());
			window.pn.addLugar(place);
		}
		else
			selectObject(evt);
	}
	
	/**
	 * Adiciona uma Transição caso o ponto clicado esteja fora de algum objeto existente.
	 * @param evt Evento do Mouse
	 * @since 1.0
	 */
	public void addTrans(MouseEvent evt)
	{
		int x = evt.getX();
		int y = evt.getY();
		
		if(window.pn.verifyPosition(x,y) == false)
		{
			TransitionGraph trans = new TransitionGraph(x, y, window.pn.getNumTransicao());
			trans.setOrientation(TransitionGraph.ORIENTATION_HORIZONTAL);
			window.pn.addTransicao(trans);
		}
		else
			selectObject(evt);
	}
	
	/**
	 * Adiciona um rótulo caso o ponto clicado esteja fora de algum objeto existente.
	 * @param evt Evento do Mouse
	 * @since 2.0
	 */
	public void addLabel(MouseEvent evt)
	{
		int x = evt.getX();
		int y = evt.getY();
		
		if(window.pn.verifyPosition(x,y) == false)
		{
			String strText = inputTextLabel();
			if(strText != null)
			{
				Label label = new Label();
				label.moveLabel(x,y);
				label.setPosicao(window.pn.getNumLabel());
				label.setTexto(strText);
				
				window.pn.addLabel(label);
			}
		}
		else
			selectObject(evt);
	}
	
	/**
	 * Seta qual o objeto da rede de petri é o início do arco.
	 * @param evt Evento do Mouse
	 * @since 1.0
	 */
	public void setStartArc(MouseEvent evt)
	{
		Object obj = window.pn.getObjectPosition(evt.getX(), evt.getY());
		if(obj != null)
		{
			if(obj instanceof PlaceBase)
				window.pn.deselectTransition();
			else
				window.pn.deselectPlace();
			
			arco = new ArcGraph();
			if(arco.setStartObject(obj) == true)
			{
				setSubModo(DRAW_ARC);
			}
			else
				selectObject(evt);
		}
	}
	
	/**
	 * Seta um ponto intermediário do arco.
	 * @param evt Evento do Mouse
	 * @since 1.0
	 */
	public void addPointArc(MouseEvent evt)
	{
		Object obj = window.pn.getObjectPosition(evt.getX(), evt.getY());
		if(obj != null)
		{
			setEndArc(evt, obj);
		}
		else
		{
			arco.addPoint(evt.getX(), evt.getY());
		}
	}
	
	/**
	 * Seta o objeto de rede de petri, final do arco.
	 * @param evt Evento do Mouse
	 * @param obj Objeto da Rede de Petri
	 * @since 1.0
	 */
	public void setEndArc(MouseEvent evt, Object obj)
	{
		if(arco.setEndObject(obj) == true)
		{
			if(obj instanceof PlaceBase)
				window.pn.deselectTransition();
			else
				window.pn.deselectPlace();
			
			setSubModo(EDIT_ARC);
			arco.setPosicao(window.pn.getNumArco());
			window.pn.addArco(arco);
			arco = null;
		}
	}
	
	
	/**
	 * Seleciona o objeto clicado pelo mouse.
	 * @param evt Evento do Mouse
	 * @since 1.0
	 */
	public void selectObject(MouseEvent evt)
	{
		Object obj = window.pn.getObjectPosition(evt.getX(), evt.getY());
		if(obj != null)
		{
			selectObj = obj;
			if(selectObj instanceof PlaceGraph)
			{
				window.placeAction.setSelectedPlace((PlaceGraph) selectObj);
				window.placeAction.enablePlaceInfo();
				window.placeAction.showInfo();
				window.transAction.disableTransitionInfo();
				window.arcAction.disableArcInfo();
				window.pn.deselectTransition();
				window.pn.deselectArc();
			}
			else if(selectObj instanceof TransitionGraph)
			{
				window.transAction.setSelectedTransition((TransitionGraph) selectObj);
				window.transAction.enableTransInfo();
				window.transAction.showInfo();
				window.arcAction.disableArcInfo();
				window.placeAction.disablePlaceInfo();
				window.pn.deselectPlace();
				window.pn.deselectArc();
				
				if(evt.getButton() != MouseEvent.BUTTON1) 
				{
					TransitionGraph t = (TransitionGraph) selectObj;
					t.cycleOrientation();
				}

			}
			else if(selectObj instanceof ArcGraph)
			{
				window.arcAction.setSelectedArc((ArcGraph) selectObj );
				window.arcAction.enableArcInfo();
				window.arcAction.showInfo();
				window.transAction.disableTransitionInfo();
				window.placeAction.disablePlaceInfo();
				window.pn.deselectPlace();
				window.pn.deselectTransition();
				
				if(evt.getButton() != MouseEvent.BUTTON1)
				{
					ArcGraph arc = window.pn.getSelectedArc();
					arc.cycleExtremePoint();
					arc.refreshEndPoints();
					arc = null;
					
				}
			}
			else if(selectObj instanceof Label)
			{
				window.transAction.disableTransitionInfo();
				window.placeAction.disablePlaceInfo();
				window.pn.deselectPlace();
				window.pn.deselectTransition();
				
				if(evt.getButton() != MouseEvent.BUTTON1)
				{
					Label label = window.pn.getSelectedLabel();
					String strText = inputTextLabel();
					if(strText != null)
						label.setTexto(strText);					
				}
			}
		
			moveObj = selectObj;	
		}
		else
		{
			window.arcAction.focusLostPerformed();
			window.placeAction.focusLostPerformed();
			window.transAction.focusLostPerformed();
			
			window.pn.deselectPlace();
			window.pn.deselectTransition();
			window.pn.deselectArc();
			
			moveObj = null;
			selectObj = null;
		}
	}
	
	/**
	 * Move o objeto clicado e liberado pelo mouse.
	 * @param evt Evento do Mouse
	 * @since 1.0
	 */
	public void moveObject(MouseEvent evt)
	{
		boolean bolRefresh = false;
		if((moveObj != null) && (evt.getButton() == MouseEvent.BUTTON1))
		{
			int newX = evt.getX();
			int newY = evt.getY();
			
			if(moveObj instanceof PlaceGraph)
			{
				PlaceGraph p = (PlaceGraph) moveObj;
				p.setXY(newX,newY);
				bolRefresh = true;
			}
			else if(moveObj instanceof TransitionGraph)
			{
				TransitionGraph t = (TransitionGraph) moveObj;
				t.setXY(newX,newY);
				bolRefresh = true;
			}
			else if(moveObj instanceof ArcGraph)
			{
				ArcGraph arc = (ArcGraph) moveObj;
				arc.moveSelectedPoint(newX, newY);
				bolRefresh = true;
			}
			else if(moveObj instanceof Label)
			{
				Label label = (Label) moveObj;
				label.moveLabel(newX, newY);
				bolRefresh = true;
			}
			
			
			if(bolRefresh)
			{
				moveObj = null;
				for(int i=0; i <  window.pn.getNumArco(); i++)
					window.pn.getArc(i).refreshEndPoints();
			}
		}
			
	}
	
	/**
	 * Deleta objeto de rede de petri desenhado na tela.
	 * @param evt Evento do Mouse
	 * @since 1.0
	 */
	public void deleteObject(MouseEvent evt)
	{
		Object obj = window.pn.getObjectPosition(evt.getX(), evt.getY());
		if(obj != null)
		{
			selectObj = obj;
			if(selectObj instanceof PlaceGraph)
			{
				window.pn.deselectPlace();
				window.pn.removeLugar((PlaceBase) selectObj);
			}
			else if(selectObj instanceof TransitionGraph)
			{
				window.pn.deselectTransition();
				window.pn.removeTransicao(((TransitionBase) selectObj).getPosicao() );
			}	
			else if(selectObj instanceof ArcGraph)
			{
				window.pn.deselectArc();
				window.pn.removeArco((ArcBase) selectObj);
			}
			else if(selectObj instanceof Label)
			{
				window.pn.deselectLabel();
				window.pn.removeLabel((Label) selectObj);
			}
		}
	}
	
	/**
	 * Mostra as informações do objeto selecionado, quando está no modo Simulação.
	 * @param evt Evento do Mouse
	 * @since 1.0
	 */
	public void showInfoObjectAndFireTransition(MouseEvent evt)
	{
		Object obj = window.pn.getObjectPosition(evt.getX(), evt.getY());
		if(obj != null)
		{
			selectObj = obj;
			if(selectObj instanceof PlaceGraph)
			{
				window.placeAction.setSelectedPlace((PlaceGraph) selectObj);
				window.placeAction.showInfo();
				window.pn.deselectTransition();
				window.pn.deselectArc();
				window.pn.deselectLabel();
			}
			else if(selectObj instanceof TransitionGraph)
			{
				TransitionGraph trans = (TransitionGraph) selectObj;
				window.transAction.setSelectedTransition(trans);
				window.transAction.showInfo();
				window.pn.deselectPlace();
				window.pn.deselectArc();
				window.pn.deselectLabel();
				/* Tenta disparar transição, se o botão do mouse for o esquerdo */
				if(evt.getButton() == MouseEvent.BUTTON1)
					window.simAction.tryFireTransition((TransitionBase)trans);
			}
			else if(selectObj instanceof ArcGraph)
			{
				window.arcAction.setSelectedArc((ArcGraph) selectObj );
				window.arcAction.showInfo();
				window.pn.deselectPlace();
				window.pn.deselectTransition();
				window.pn.deselectLabel();
			}
			else if(selectObj instanceof Label)
			{
				window.pn.deselectPlace();
				window.pn.deselectTransition();
				window.pn.deselectArc();
			}	
		}
		else
		{
			selectObj = null;
		}
	}
	
	/**
	 * Sobrescreve o método update
	 * @param g Primitivas gráficas
	 * @since 1.0
	 */
    public void update(Graphics g) 
    {	
    	if(modo == MODO_SIMULACAO)
    	{
    		Color cor = new Color(245,245,245);
    		setBackground(cor);    		
    	}
    	else
    	{
    		setBackground(Color.WHITE);
    	}
    	g.setColor(getBackground());
    	g.fillRect(0,0,getWidth(), getHeight());
    	desenhaLugares(g);
    	desenhaTransicao(g);
    	desenhaArcos(g);
    	desenhaRotulos(g);
    }
    
    /**
     * Sobrescreve o método paint.
     * @param g Primitivas gráficas
	 * @since 1.0
     */
    public void paint(Graphics g) 
    {
    	if(modo == MODO_SIMULACAO)
    	{
    		Color cor = new Color(240,240,240);
    		setBackground(cor);	
    	}
    	else
    	{	
    		setBackground(Color.WHITE);
    	}
    	
    	g.setColor(getBackground());
    	g.fillRect(0,0,getWidth(), getHeight());
    	desenhaLugares(g);
    	desenhaTransicao(g);
    	desenhaArcos(g);
    	desenhaRotulos(g);
    }

    /**
     * Método que desenha os Lugares da Rede de Petri na tela.
     * @param g Primitivas gráficas
	 * @since 1.0
     */
    private void desenhaLugares(Graphics g) 
    { 	
    	for(int i=0; i < window.pn.getNumLugar(); i++ )
    	{
    		PlaceGraph p = (PlaceGraph) window.pn.getLugar(i);
    		
    		if(window.pn.verifyPlaceSelected(p) == true)
    			g.setColor(Color.GREEN);
    		else
    			g.setColor(Color.BLUE);
    		
    		g.drawOval(	p.getX() - PlaceGraph.RAIO,
    					p.getY() - PlaceGraph.RAIO,
    					2 * PlaceGraph.RAIO,
    					2 * PlaceGraph.RAIO);
    		 
     		int fichas = 0;
    		if(modo == MODO_SIMULACAO)
    			fichas = window.simAction.getFichas(i);
    		else
    			fichas = p.getFichas();
    		
    		g.setColor(Color.RED);
    		if(fichas > 1 && fichas < 10)
    		{
    			Font f = g.getFont();
    		    int size = f.getSize();
    			int x = (int) (p.getX() - (size / 4));
    		    int y = (int) (p.getY() + (size / 2.5f));
				g.drawString(Integer.toString(fichas), x, y);
    		}
    		else
    		{
    			if(fichas >= 10)
    			{
    				Font f = g.getFont();
    				int size = f.getSize();
    				int x = (int) (p.getX() - (size / 1.5f));
    				int y = (int) (p.getY() + (size / 2.5f));
    	            g.drawString(Integer.toString(fichas), x, y);
    			}
    			else
    			{
    				g.drawOval(p.getX() - PlaceGraph.RAIO/5,
 					 	p.getY() - PlaceGraph.RAIO/5,
 					 	2 * PlaceGraph.RAIO/5,
 					 	2 * PlaceGraph.RAIO/5);
    		
    				if(fichas == 1)
    				{
    					
    					g.fillOval(p.getX() - PlaceGraph.RAIO/5,
    						    p.getY() - PlaceGraph.RAIO/5,
    						    	2 * PlaceGraph.RAIO/5,
    	    					 	2 * PlaceGraph.RAIO/5);
    				}
    			}
    		}
       	}
    }

    /**
     * Método que desenha as Transições da Rede de Petri na tela.
     * @param g Primitivas gráficas
	 * @since 1.0
     */
    private void desenhaTransicao(Graphics g) 
    { 	
    	for(int i=0; i < window.pn.getNumTransicao(); i++ )
    	{
    		TransitionGraph t = (TransitionGraph) window.pn.getTransicao(i);
    		
    		
    		if(window.pn.verifyTransitionSelected(t) == true)
				g.setColor(Color.GREEN);
    		else
    			g.setColor(Color.BLACK);
    		
    		if(modo == MODO_SIMULACAO)
			{
				if(window.simAction.canFireTransition(i))
					g.setColor(Color.RED);
			}	
    		
    		int x =0 , y =0;
    		int lx=0 , ly=0;
    		switch(t.getOrientation())
    		{
    			case TransitionGraph.ORIENTATION_VERTICAL:
    				{
	    				x = t.getX() - TransitionGraph.ALTURA/2;
	        			y = t.getY() - TransitionGraph.COMPRIMENTO/2;
	        			
	        			lx = TransitionGraph.ALTURA;
	        			ly = TransitionGraph.COMPRIMENTO;
	        			break;
	    			}
    			default:
	    			{
	    				// ORIENTATION_HORIZONTAL
	            		x = t.getX() - TransitionGraph.COMPRIMENTO/2;
	            		y = t.getY() - TransitionGraph.ALTURA/2;
	            		
	            		lx = TransitionGraph.COMPRIMENTO;
	            		ly = TransitionGraph.ALTURA;
	    			}
    		}
    		
    		g.drawRect(	x, y, lx, ly);
			g.fillRect(	x, y, lx, ly);
       	} 
    }
    
    /**
     * Desenha os arcos da rede de petri.
     * @param g Primitivas gráficas
	 * @since 1.0
     */
    private void desenhaArcos(Graphics g)
    {
    	if(arco != null)
    	{
    		desenhaArco(g, arco);
    	}
    	
    	for(int i=0; i < window.pn.getNumArco(); i++)
    	{
    		ArcGraph a = (ArcGraph) window.pn.getArc(i);
    		desenhaArco(g, a);
    		
    	}
    }

    /**
     * Desenha cada arco da rede de petri.
     * @param g Primitivas gráficas
     * @param a Arco a ser desenhado
	 * @since 1.0
     */
    private void desenhaArco(Graphics g, ArcGraph a)
    {
    	if(a.getNumPoints() < 2)
    		return;
    	
    	Point start=null, end=null;
    	if(window.pn.verifyArcSelected(a) == true)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GRAY);
    	
		int j=0;
		for(j=0; j < a.getNumPoints()-1; j++)
		{
			start = a.getPoint(j);
			end	  = a.getPoint(j+1);
			if(start != null && end != null)
			{
				g.drawLine(start.x, start.y, end.x, end.y);
			}
		}
		
		  end = a.getPoint(a.getNumPoints()-1);
		start = a.getPoint(a.getNumPoints()-2);
		
		Point strPoint = ArcGraph.getAuxPoint(start, end, 1, 2);
		Point auxPoint = ArcGraph.getAuxPoint(start, end, 14);
		
		if(a.getPeso() > 1)
		{
			 g.drawString("" + a.getPeso(), strPoint.x, strPoint.y);
		}
		 
		desenhaPontaSeta(g,auxPoint.x, auxPoint.y, end.x, end.y );
    }
    
    /**
     * Desenha a ponta da Seta do arco.
     * @param g Primitivas gráficas
     * @param xS Coordenada X, onde começa a ponta da seta
     * @param yS Coordenada Y, onde começa a ponta da seta 
     * @param xE Coordenada X, onde termina a ponta da seta
     * @param yE Coordenada Y, onde termina a ponta da seta
	 * @since 1.0
     */
    private static void desenhaPontaSeta(Graphics g, int xS, int yS, int xE, int yE) 
    {
        int xP[] = new int[3];
        int yP[] = new int[3];

        if (xE < 0.0)
            xP[0] = (int) (xE - 1.0);
        else
            xP[0] = (int) (xE + 1.0);

        if (yE < 0.0)
            yP[0] = (int) (yE - 1.0);
        else
            yP[0] = (int) (yE + 1.0);

        double  dx = xS - xE;
        double  dy = yS - yE;

        double  length = Math.sqrt(dx * dx + dy * dy);

        double  xAdd = 9.0 * dx / length;
        double  yAdd = 9.0 * dy / length;

        xP[1] = (int) Math.round (xE + xAdd - (yAdd / 3.0));
        yP[1] = (int) Math.round (yE + yAdd + (xAdd / 3.0));
        xP[2] = (int) Math.round (xE + xAdd + (yAdd / 3.0));
        yP[2] = (int) Math.round (yE + yAdd - (xAdd / 3.0));

        g.drawLine(xS, yS, xP[0], yP[0]);
        g.fillPolygon(xP, yP, 3);
    }
    
	/**
	 * Método que desenha o rótulo na janela de desenho
	 * <p>
	 * @param g Primitivas gaficas
	 * @since 2.0
	 */
	public void desenhaRotulos(Graphics g)
	{

		for(int i=0; i < window.pn.getNumLabel(); i++)
		{
			
    		Label l = (Label) window.pn.getLabel(i);
    		    		
    		Point p = l.getPontoInicial();
    		
    		g.setFont(l.getFont());
    		g.setColor(Color.BLACK);
    		g.drawString(l.getTexto() , p.x, p.y);
    		
    		
		}
	}
	
	public String inputTextLabel()
	{
		String strText = JOptionPane.showInputDialog(null, "Texto do Rótulo:", "Rótulo",JOptionPane.INFORMATION_MESSAGE);
		if((strText != null) && (strText.trim().length() > 0))
			return strText;
		else
			return null;
	}
}
