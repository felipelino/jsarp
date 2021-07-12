
package br.uerj.petrinetanalyzer.common;

import java.util.ArrayList;
import br.uerj.petrinetanalyzer.common.interfaces.IntfPetriNetBase;

/**
 * Classe base para definição de Redes de Petri.
 * 
 * @author Felipe Lino
 * <BR>Data:11/01/2007
 * <BR>Atualizado: 12/10/2007
 */
public class ImplPetriNetBase implements IntfPetriNetBase
{
	private String		nome				= null;

	private ArrayList	listLugar			= null;
	private ArrayList	listTransicao		= null;
	private ArrayList	listArco			= null;

	private int[][]		matrizEntrada		= null; // Matriz I
	private int[][]		matrizSaida			= null; // Matriz O
	private int[][]		matrizIncidencia	= null; // Matriz D = O - I
	
	public static final String NOME_REDE = "NewPetriNet";
	
	/**
	 * Construtor default
	 *
	 */
	public ImplPetriNetBase()
	{
		nome = NOME_REDE;
	}
	
	/**
	 * Seta o nome da Rede de Petri.
	 * @param nome Nome da Rede de Petri
	 */
	public void setNome(String nome)
	{
		this.nome = nome;	
	}
	
	/**
	 * Adiciona um Lugar a Rede de Petri.
	 * @param lugar Lugar de Rede de Petri
	 */
	public void addLugar(PlaceBase lugar)
	{
		if(listLugar == null)
		{
			listLugar = new ArrayList();
		}
		lugar.setPosicao(getNumLugar());
		listLugar.add(lugar);
	}

	/**
	 * Adiciona uma Transicao a Rede de Petri.
	 * @param transicao Transicao de Rede de Petri
	 */
	public void addTransicao(TransitionBase transicao)
	{
		if(listTransicao == null)
		{
			listTransicao = new ArrayList();
		}
		transicao.setPosicao(getNumTransicao());
		listTransicao.add(transicao);
	}

	/**
	 * Adiciona um Arco a Rede de Petri.
	 * @param arco Arco de Rede de Petri
	 */
	public void addArco(ArcBase arco)
	{
		if(listArco == null)
		{
			listArco = new ArrayList();
		}
		listArco.add(arco);
	}

	/**
	 * Remove um Lugar da Rede de Petri especificado pelo nome
	 * @param nome Nome do Lugar
	 */
	public void removeLugar(String nome)
	{
		boolean sinal = false;
		PlaceBase lugar = null;
		for(int i=0; i < listLugar.size(); i++)
		{
			lugar = (PlaceBase)listLugar.get(i);
			if(lugar.getNome().equalsIgnoreCase(nome))
			{
				listLugar.remove(i);
				sinal = true;
				break;
			}
		}
		
		if(sinal)
		{
			removeArcoAssociadoLugar(lugar);
			
			for(int i=0; i< listLugar.size(); i++)
			{
				((PlaceBase) listLugar.get(i)).setPosicao(i);
			}
		}
	}

	/**
	 * Remove um Lugar da Rede de Petri especificado pela sua posicao no 
	 * ArrayList que contem os Lugares da Rede de Petri.
	 * @param posicao Posicao do Lugar 
	 */
	public void removeLugar(int posicao)
	{
		boolean sinal = false;
		PlaceBase lugar = null;
		if( posicao <= getNumLugar() )
		{
			lugar = (PlaceBase) listLugar.remove(posicao);
			sinal = true;
		}
		
		if(sinal)
		{
			removeArcoAssociadoLugar(lugar);
			
			for(int i=0; i< listLugar.size(); i++)
			{
				((PlaceBase) listLugar.get(i)).setPosicao(i);
			}
		}
	}

	/**
	 * Remove um Lugar da Rede de Petri especificado pela sua instância.
	 * @param lugar Lugar da Rede de Petri
	 */
	public void removeLugar(PlaceBase lugar)
	{
		boolean sinal = false;
		if(listLugar.contains(lugar))
		{
			listLugar.remove(lugar);
			sinal = true;
		}
		
		if(sinal)
		{
			removeArcoAssociadoLugar(lugar);
			
			for(int i=0; i< listLugar.size(); i++)
			{
				((PlaceBase) listLugar.get(i)).setPosicao(i);
			}
		}
	}

	/**
	 * Remove uma transição da Rede de Petri especificada pelo seu nome.
	 * @param nome Nome da Transição
	 */
	public void removeTransicao(String nome)
	{
		boolean sinal = false;
		TransitionBase trans = null;
		for(int i=0; i < getNumTransicao(); i++)
		{
			trans = (TransitionBase)listTransicao.get(i);
			if(trans.getNome().equalsIgnoreCase(nome))
			{
				listTransicao.remove(i);
				sinal = true;
			}
		}
		
		if(sinal)
		{
			removeArcoAssociadoTransicao(trans);
			
			for(int i=0; i< listTransicao.size(); i++)
			{
				((TransitionBase) listTransicao.get(i)).setPosicao(i);
			}
		}
	}

	/**
	 * Remove uma Transição da Rede de Petri especificada pela sua posição no 
	 * ArrayList que contem as Transições da Rede.
	 * @param posicao Posicao da Transição
	 */
	public void removeTransicao(int posicao)
	{
		boolean sinal = false;
		TransitionBase trans = null;
		if( posicao <= listTransicao.size()-1 )
		{
			trans = (TransitionBase) listTransicao.remove(posicao);
			sinal = true;
		}
		
		if(sinal)
		{
			removeArcoAssociadoTransicao(trans);
			
			for(int i=0; i< listTransicao.size(); i++)
			{
				((TransitionBase) listTransicao.get(i)).setPosicao(i);
			}
		}
	}
	
	/**
	 * Remove uma Transição da Rede de Petri especificada por sua instância.
	 * @param transicao Transicao da Rede de Petri  
	 */
	public void removeTransicao(TransitionBase transicao)
	{
		boolean sinal = false;
		if(listTransicao.contains(transicao))
		{
			listTransicao.remove(transicao);
			sinal = true;
		}
		
		if(sinal)
		{
			removeArcoAssociadoTransicao(transicao);
			
			for(int i=0; i< listTransicao.size(); i++)
			{
				((TransitionBase) listTransicao.get(i)).setPosicao(i);
			}
		}
	}
	
	/**
	 * Remove Arco associado a um Lugar.
	 * @param place Lugar da Rede.
	 */
	public void removeArcoAssociadoLugar(PlaceBase place)
	{
		for(int i=getNumArco()-1; i>=0 ; i--)
		{
			ArcBase arc = getArc(i);
			if(arc.getLugar().equals(place))
				removeArco(i);
		}
	}
	
	/**
	 * Remove Arco associado a uma Transição.
	 * @param trans Transição da Rede.
	 */
	public void removeArcoAssociadoTransicao(TransitionBase trans)
	{
		for(int i=getNumArco()-1; i>=0; i--)
		{
			ArcBase arc = getArc(i);
			if(arc.getTransicao().isEqual(trans))	
				removeArco(i);
		}	
	}

	/**
	 * Remove um Arco da Rede de Petri especificado pela sua posição no 
	 * ArrayList que contem os Arcos.
	 * @param posicao Posicao do Arco
	 */
	public void removeArco(int posicao)
	{
		if( posicao <= listArco.size()-1 )
		{
			listArco.remove(posicao);
			for(int i=0; i< listArco.size(); i++)
			{
				((ArcBase) listArco.get(i)).setPosicao(i);
			}
		}
	}

	/**
	 * Remove um Arco da Rede de Petri especificado por sua instância.
	 * @param arco Arco da Rede de Petri
	 */
	public void removeArco(ArcBase arco)
	{
		if(listArco.contains(arco))
		{
			listArco.remove(arco);
			for(int i=0; i< listArco.size(); i++)
			{
				((ArcBase) listArco.get(i)).setPosicao(i);
			}
		}
	}

	/**
	 * Retorna instância do Lugar especificado pelo nome se existir, caso contrário retorna null.
	 * @param nome Nome do Lugar
	 * @return Lugar
	 */
	public PlaceBase getLugar(String nome)
	{
		PlaceBase retorno = null;
		for(int i=0; i < listLugar.size(); i++)
		{
			PlaceBase lugar = (PlaceBase)listLugar.get(i);
			if(lugar.getNome().equalsIgnoreCase(nome))
			{
				retorno = lugar;
				break;
			}
		}
		
		return retorno;
	}

	/**
	 * Retorna instância do Lugar especificado pela sua posição se existir, caso contrário retorna null.
	 * @param posicao Posição do Lugar
	 * @return Lugar
	 */
	public PlaceBase getLugar(int posicao)
	{
		PlaceBase retorno = null;
		if(posicao <= getNumLugar()-1)
		{
			retorno = (PlaceBase) listLugar.get(posicao);
		}
		
		return retorno;
	}

	/**
	 * Retorna instância da Transição especificada pelo nome se existir, caso contrário retorna null.
	 * @param nome Nome da Transição
	 * @return Transição
	 */
	public TransitionBase getTransicao(String nome)
	{
		TransitionBase retorno = null;
		for(int i=0; i <= getNumTransicao()-1; i++)
		{
			TransitionBase trans = (TransitionBase)listTransicao.get(i);
			if(trans.getNome().equalsIgnoreCase(nome))
			{
				retorno = trans;
				break;
			}
		}
		
		return retorno;
	}

	/**
	 * Retorna instância de Arco especificada pela posição se existir, caso contrário retorna null.
	 * @param posicao posicao no Array de Arcos.
	 * @return Arco
	 */
	public ArcBase getArc(int posicao)
	{
		ArcBase retorno = null;
		if(posicao <= getNumArco() -1)
		{
			retorno = (ArcBase) listArco.get(posicao);
		}
		
		return retorno;
	}
	
	/**
	 * Retorna instância da Transição especificada pela posição se existir, caso contrário retorna null.
	 * @param posicao Posição da Transição
	 * @return Transição
	 */
	public TransitionBase getTransicao(int posicao)
	{
		TransitionBase retorno = null;
		if(posicao <= listTransicao.size())
		{
			retorno = (TransitionBase) listTransicao.get(posicao);
		}
		return retorno;
	}

	/**
	 * Retorna o ArrayList com todos os Lugares da Rede de Petri.
	 * @return ArrayList com os Lugares da da Rede de Petri
	 */
	public ArrayList getListLugar()
	{
		return listLugar;
	}

	/**
	 * Retorna o ArrayList com todas as Transições da Rede de Petri.
	 * @return ArrayList com as Transições da da Rede de Petri
	 */
	public ArrayList getListTransicao()
	{
		return listTransicao;
	}
	
	/**
	 * Retorna o ArrayList com todos os Arcos da Rede de Petri.
	 * @return ArrayList com os Arcos da da Rede de Petri
	 */
	public ArrayList getListArcos()
	{
		return listArco;
	}
	
	/**
	 * Retorna o array de marcações na forma de Array de inteiros.
	 * @return Array de inteiros com as marcações dos Lugares.
	 */
	public int [] getArrMarking()
	{
		if(listLugar != null)
		{
			int [] arrMarking = new int[getNumLugar()];
			
			for(int i =0; i < getNumLugar(); i++)
				arrMarking[i] = getLugar(i).getFichas();
			
			return arrMarking;	
		}
		return null;
	}

	/**
	 * Constrói as Matrizes de Entrada e Saída.
	 */
	public void buildMatrizEntradaAndSaida()
	{
		if(listArco != null)
		{
			matrizEntrada = new int[getNumTransicao()][getNumLugar()];
			matrizSaida   = new int[getNumTransicao()][getNumLugar()];
			
			for(int i = 0; i < listArco.size(); i++)
			{
				ArcBase arco = (ArcBase) listArco.get(i);
				TransitionBase transicao = (TransitionBase) arco.getTransicao();
				PlaceBase lugar = (PlaceBase) arco.getLugar();
				int l = transicao.getPosicao();
				int c = lugar.getPosicao();

				if(arco.verifyEntrada())
				{
					matrizEntrada[l][c] = arco.getPeso();
				}
				else
				{
					matrizSaida[l][c] = arco.getPeso();
				}
			}
		}
	}


	/**
	 * Constrói a Matriz de Incidência a partir das matrizes de Entrada e Saída. Casos estas últimas
	 * não existam, serão constrúidas também.
	 */
	public void buildMatrizIncidencia()
	{
		matrizIncidencia = new int[getNumTransicao()][getNumLugar()];
		
		if((matrizEntrada == null )||(matrizSaida == null)) 
		{
			buildMatrizEntradaAndSaida();
		}
		
		for(int l = 0; l < getNumTransicao(); l++ )
		{
			for(int c = 0; c < getNumLugar(); c++)
			{
				matrizIncidencia[l][c] = matrizSaida[l][c] - matrizEntrada[l][c];
			}
		}
	}


	/**
	 * Retorna a matriz de Entrada.
	 * @return Matriz de Entrada
	 */
	public int[][] getMatrizEntrada()
	{
		if(matrizEntrada == null)
			buildMatrizEntradaAndSaida();
		
		return matrizEntrada;
	}

	/**
	 * Retorna a matriz de Saída.
	 * @return Matriz de Saída
	 */
	public int[][] getMatrizSaida()
	{
		if(matrizSaida == null)
			buildMatrizEntradaAndSaida();
		
		return matrizSaida;
	}

	/**
	 * Retorna matriz de Incidência.
	 * @return Matriz de Incidência
	 */
	public int[][] getMatrizIncidencia()
	{
		if(matrizIncidencia == null)
			buildMatrizIncidencia();
		
		return matrizIncidencia;
	}

	/**
	 * Retorna dimensao das matrizes que representam a Rede de Petri.
	 * @return Dimensão das Matrizes que representam a Rede de Petri
	 */
	public int getDimensao()
	{
		if(getNumTransicao() > getNumLugar())
			return getNumTransicao();
		else
			return getNumLugar();
	}

	/**
	 * Retorna o número de Lugares da Rede de Petri.
	 * @return Número de Lugares da Rede de Petri
	 */
	public int getNumLugar()
	{
		if(listLugar == null)
			return 0;
		else
			return listLugar.size();
	}

	/**
	 * Retorna o número de Transições da Rede de Petri.
	 * @return Número de Transições da Rede de Petri
	 */
	public int getNumTransicao()
	{
		if(listTransicao == null)
			return 0;
		else
			return listTransicao.size();
	}

	/**
	 * Retorna o número de Arcos da Rede de Petri.
	 * @return Número de Arcos da Rede de Petri
	 */
	public int getNumArco()
	{
		if(listArco == null)
			return 0;
		else
			return listArco.size();
	}
	
	/**
	 * Retorna o nome da Rede de Petri.
	 * @return Nome da Rede de Petri
	 */
	public String getNome()
	{
		return nome;
	}
	
}
