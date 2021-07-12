package br.uerj.petrinetanalyzer.gui.objects;

import java.util.ArrayList;

import br.uerj.petrinetanalyzer.common.ArcBase;
import br.uerj.petrinetanalyzer.common.ImplPetriNetBase;
import br.uerj.petrinetanalyzer.common.PlaceBase;
import br.uerj.petrinetanalyzer.common.TransitionBase;

/**
 * Esta classe é a classe base para definição de Redes de Petri.
 * Com os atributos necessários para a parte gráfica.
 * 
 * @author Felipe Lino
 * <BR>Data: 08/01/2007
 * <BR>Atualização: 20/10/2007
 * @version 2.0
 */
public class PetriNetGraph extends ImplPetriNetBase
{
	/**
	 * Informa qual o Lugar Selecionado.
	 */
	private int selectedPlace = -1;
	/**
	 * Informa qual a Transição Selecionada.
	 */
	private int selectedTrans = -1;
	
	/**
	 * Informa qual o Arco Selecionado.
	 */
	private int selectedArc = -1;
	
	/**
	 * Informa qual o Rótulo Selecionado.
	 */
	private int selectedLabel = -1;
	
	private ArrayList listLabel;
	
	/**
	 * Construtor.
	 * @since 1.0
	 */
	public PetriNetGraph()
	{
		super();
		selectedPlace = -1;
		selectedTrans = -1;
		selectedArc	  = -1;
		selectedLabel = -1;

		listLabel = new ArrayList();
	}
	
	/**
	 * Adiciona Lugar.
	 * @param place Lugar
	 * @since 1.0
	 */
	public void addLugar(PlaceGraph place)
	{
		super.addLugar((PlaceBase) place);
	}
	
	/**
	 * Adiciona transição.
	 * @param transicao Transição
	 * @since 1.0
	 */
	public void addTransicao(TransitionGraph transicao)
	{
		super.addTransicao((TransitionBase) transicao);
		
	}
	
	/**
	 * Adiciona Arco.
	 * @param arco Arco
	 * @since 1.0
	 */
	public void addArco(ArcGraph arco)
	{
		super.addArco((ArcBase) arco);
	}
	
	/**
	 * Adiciona Rótulo.
	 * @param label
	 * @since 2.0
	 */
	public void addLabel(Label label)
	{
		if(listLabel == null)
			listLabel = new ArrayList();
		
		listLabel.add(label);
	}
	
	/**
	 * Retorna Lugar através da posição.
	 * @param posicao Posição do Lugar na Rede de Petri
	 * @return Lugar Lugar da posição especificada
	 * @since 1.0
	 */
	public PlaceGraph getPlace(int posicao)
	{
		return (PlaceGraph) super.getLugar(posicao);
	}
	
	/**
	 * Retorna Lugar através do nome.
	 * @param nome	 Nome do Lugar
	 * @return Lugar Lugar com o nome fornecido
	 * @since 1.0
	 */
	public PlaceGraph getPlace(String nome)
	{
		return (PlaceGraph) super.getLugar(nome);
	}
	
	/**
	 * Retorna Transição através da posição.
	 * @param posicao	 Posição da Transição
	 * @return Transição especificada pela posição
	 * @since 1.0
	 */
	public TransitionGraph getTransition(int posicao)
	{
		return (TransitionGraph) super.getTransicao(posicao);
	}
	
	/**
	 * Retorna Transição através do Nome.
	 * @param nome  Nome da Transição 
	 * @return Transição especificada pelo nome.
	 * @since 1.0
	 */
	public TransitionGraph getTransition(String nome)
	{
		return (TransitionGraph) super.getTransicao(nome);
	}
	
	/**
	 * Retorna o Arco através da posição.
	 * @param posicao Posição do Arco na Rede de Petri
	 * @return Arco especificado pela posição.
	 * @since 1.0
	 */
	public ArcGraph getArc(int posicao)
	{
		return (ArcGraph) super.getArc(posicao);
	}
	
	/**
	 * Retorna o Rótulo da posição desejada.
	 * @param posicao
	 * @return Rótulo da posição desejada
	 * @since 2.0
	 */
	public Label getLabel(int posicao)
	{
		if(posicao >= 0 && posicao < getNumLabel())
			return (Label)listLabel.get(posicao);
		else
			return null;
	}
	
	/**
	 * Retorna true caso exista algum objeto que ocupe as proximidades
	 * da posição x, y fornecida.
	 * @param x Coordenada x
	 * @param y Coordenada y
	 * @return true caso exista algum objeto que ocupe as proximidades
	 * do ponto fornecido, e false caso contrário.
	 * @since 1.0
	 */
	public boolean verifyPosition(int x, int y)
	{
		for(int i=0; i < getNumLugar(); i++)
		{
			PlaceGraph p = getPlace(i);
			if(p.inPlace(x,y))
				return true;
		}
		
		for(int i=0; i < getNumTransicao(); i++)
		{
			TransitionGraph t = getTransition(i);
			if(t.inTransition(x,y))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Retorna o objeto nas proximidades da posição dada, caso exista
	 * senão retorna null.
	 * @param x Coordenada x
	 * @param y Coordenada y
	 * @return Objeto nas proximidades da posição dada
	 * @since 1.0
	 */
	public Object getObjectPosition(int x, int y)
	{
		for(int i=0; i < getNumLugar(); i++)
		{
			PlaceGraph p = getPlace(i);
			if(p.inPlace(x,y))
			{
				selectedPlace = i;
				return p;
			}
		}
		
		for(int i=0; i < getNumTransicao(); i++)
		{
			TransitionGraph t = getTransition(i);
			if(t.inTransition(x,y))
			{
				selectedTrans = i;
				return t;
			}
		}
		
		for(int i=0; i < getNumArco(); i++)
		{
			ArcGraph a = getArc(i);
			if(a.inArc(x,y))
			{
				selectedArc = i;
				return a;
			}
		}
		
		for(int i=0; i < getNumLabel(); i++ )
		{
			Label l = getLabel(i);
			if(l.inLabel(x,y))
			{
				selectedLabel = i;
				return l;
			}
		}
		
		return null;
	}
	
	/**
	 * Verifica se o Lugar passado como parâmetro foi o último selecionado.
	 * @param place Lugar 
	 * @return Retorna True caso o Lugar passado tenha sido selecionado.
	 * @since 1.0
	 */
	public boolean verifyPlaceSelected(PlaceGraph place)
	{
		if(place.getPosicao() == selectedPlace)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica se a Transição passada como parâmetro foi a última selecionada.
	 * @param trans Transição
	 * @return Retorna True caso a Transição passada tenha sido selecionada.
	 * @since 1.0
	 */
	public boolean verifyTransitionSelected(TransitionGraph trans)
	{
		if(trans.getPosicao() == selectedTrans)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica se o arco está ligado a um objeto selecionado.
	 * @param arc Arco
	 * @return retorna true se o objeto origem do arco estiver selecionado.
	 * @since 1.0
	 */
	public boolean verifyArcSelected(ArcGraph arc)
	{
		if(arc.getPosicao() == selectedArc)
			return true;
		else
			return false;
		
	}
	
	/**
	 * Verifica se o rótulo passado como parâmetro foi o último selecionado.
	 * @param label Rótulo
	 * @return Retorna true caso o rótulo passado como parâmetro tenha sido selecionado.
	 */
	public boolean verifyLabelSelected(Label label)
	{
		if(label.getPosicao() == selectedLabel)
			return true;
		else
			return false;
	}
	
	/**
	 * Deseleciona o Lugar.
	 * @since 1.0
	 */
	public void deselectPlace()
	{
		selectedPlace = -1;
	}
	
	/**
	 * Deseleciona a Transição.
	 * @since 1.0
	 */
	public void deselectTransition()
	{
		selectedTrans = -1;
	}
	
	/**
	 * Deseleciona o Arco.
	 * @since 1.0
	 */
	public void deselectArc()
	{
		selectedArc = -1;
	}
	
	/**
	 * Deseleciona o Rótulo.
	 * @since 2.0
	 */
	public void deselectLabel()
	{
		selectedLabel = -1;
	}
	
	/**
	 * Retorna o Arco correntemente selecionado
	 * @return Arco
	 * @since 1.0
	 */
	public ArcGraph getSelectedArc()
	{
		if(selectedArc > -1)
		{
			return (ArcGraph) this.getArc(selectedArc);
		}
		else
			return null;
	}
	
	public Label getSelectedLabel()
	{
		if(selectedLabel > -1)
			return (Label) this.getLabel(selectedLabel);
		else
			return null;
	}
	
	/**
	 * Retorna o total de rótulos existentes no array de rótulos.
	 * @return total de rótulos existentes no array de rótulos
	 * @since 2.0
	 */
	public int getNumLabel()
	{
		if(listLabel != null)
			return listLabel.size();
		else
			return 0;
	}
	
	/**
	 * Remove o rótulo do array de rótulos.
	 * @param label Rótulo
	 * @since 2.0
	 */
	public void removeLabel(Label label)
	{
		if(listLabel != null)
		{
			if(listLabel.contains(label))
			{
				listLabel.remove(label);
				for(int i=0; i < listLabel.size(); i++)
				{
					((Label) listLabel.get(i)).setPosicao(i);
				}
			}
		}
	}
}
