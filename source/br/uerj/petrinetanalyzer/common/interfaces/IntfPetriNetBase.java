package br.uerj.petrinetanalyzer.common.interfaces;

import java.util.ArrayList;

import br.uerj.petrinetanalyzer.common.ArcBase;
import br.uerj.petrinetanalyzer.common.PlaceBase;
import br.uerj.petrinetanalyzer.common.TransitionBase;

/**
 * Interface base para definição de Redes de Petri.
 * 
 * @author Felipe Lino
 * <BR>Data: 09/12/2006
 */
public interface IntfPetriNetBase
{
	public void setNome(String nome);
	
	public void addLugar(PlaceBase lugar);
	public void addTransicao(TransitionBase transicao);
	public void addArco(ArcBase arco);
	
	public void removeLugar(String nome);
	public void removeLugar(int posicao);
	public void removeLugar(PlaceBase lugar);
	
	public void removeTransicao(String nome);
	public void removeTransicao(int posicao);
	public void removeTransicao(TransitionBase transicao);
	
	public void removeArco(int posicao);
	public void removeArco(ArcBase arco);
	
	public PlaceBase getLugar(String nome);
	public PlaceBase getLugar(int posicao);
	
	public TransitionBase getTransicao(String nome);
	public TransitionBase getTransicao(int posicao);
	
	public ArrayList getListLugar();
	public ArrayList getListTransicao();
	public ArrayList getListArcos();
	
	public void buildMatrizEntradaAndSaida();
	public void buildMatrizIncidencia();
	
	public int[][] getMatrizEntrada();
	public int[][] getMatrizSaida();
	public int[][] getMatrizIncidencia();
	
	public int getDimensao();
	
	public int getNumLugar();
	public int getNumTransicao();
	public int getNumArco();
	
	public String getNome();
}
