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
	void setNome(String nome);
	
	void addLugar(PlaceBase lugar);
	void addTransicao(TransitionBase transicao);
	void addArco(ArcBase arco);
	
	void removeLugar(String nome);
	void removeLugar(int posicao);
	void removeLugar(PlaceBase lugar);
	
	void removeTransicao(String nome);
	void removeTransicao(int posicao);
	void removeTransicao(TransitionBase transicao);
	
	void removeArco(int posicao);
	void removeArco(ArcBase arco);
	
	PlaceBase getLugar(String nome);
	PlaceBase getLugar(int posicao);
	
	TransitionBase getTransicao(String nome);
	TransitionBase getTransicao(int posicao);
	
	ArrayList getListLugar();
	ArrayList getListTransicao();
	ArrayList getListArcos();
	
	void buildMatrizEntradaAndSaida();
	void buildMatrizIncidencia();
	
	int[][] getMatrizEntrada();
	int[][] getMatrizSaida();
	int[][] getMatrizIncidencia();
	
	int getDimensao();
	
	int getNumLugar();
	int getNumTransicao();
	int getNumArco();
	
	String getNome();
}
