package io.pifg.zepg.grafo;

import java.util.ArrayList;
import java.util.Collection;

public class Digrafo<T> extends Grafo<T> {
	/**
	 * Remove aresta do grafo, desligando dois objetos representados por v�rtices.
	 * @param v1 - v�rtice no grafo (representa a origem da aresta)
	 * @param v2 - v�rtice no grafo (representa a extremidade da aresta)
	 * @return
	 */ 
	protected boolean removerAresta(Vertice<T> v1, Vertice<T> v2) {
		if (v1 == null || v2 == null)
			return false;
		
		if (!v1.removerSucessor(v2))
			return false;
		
		v2.removerAntecessor(v1);
		
		return true;
	}
	
	/**
	 * Retorna o grau de entrada de um objeto representado como v�rtice no grafo.
	 * @param obj - objeto armazenado como v�rtice no grafo
	 * @return int correpondente ao grau de entrada do v�rtice
	 */
	public int getGrauEntrada(T obj) {
		return getGrauEntrada(obj.hashCode());
	}
	
	/**
	 * Retorna o grau de entrada de um objeto representado como v�rtice no grafo.
	 * @param obj - hashCode do objeto armazenado como v�rtice no grafo
	 * @return int correpondente ao grau de entrada do v�rtice
	 */
	public int getGrauEntrada(int objId) {
		Vertice<T> v;
		
		if ((v = vertices.get(objId)) == null)
			throw new NonVerticeException();
		
		return v.getAntecessores().size();
	}
	
	/**
	 * Retorna o grau de saida de um objeto representado como v�rtice no grafo.
	 * @param obj - objeto armazenado como v�rtice no grafo
	 * @return int correpondente ao grau de saida do v�rtice
	 */
	public int getGrauSaida(T obj) {
		return getGrauSaida(obj.hashCode());
	}
	
	/**
	 * Retorna o grau de saida de um objeto representado como v�rtice no grafo.
	 * @param obj - hashCode do objeto armazenado como v�rtice no grafo
	 * @return int correpondente ao grau de saida do v�rtice
	 */
	public int getGrauSaida(int objId) {
		Vertice<T> v;
		
		if ((v = vertices.get(objId)) == null)
			throw new NonVerticeException();
		
		return v.getSucessores().size();
	}
	
	/**
	 * Retorna uma lista contendo os objetos sucessores de um objeto representado como v�rtice no grafo.
	 * @param obj - objeto armazenado como v�rtice no grafo
	 * @return ArrayList<T> contendo objetos T no grafo.
	 */
	public ArrayList<T> sucessores(T obj) {
		return sucessores(obj.hashCode());
	}
	
	/**
	 * Retorna uma lista contendo os objetos sucessores de um objeto representado como v�rtice no grafo.
	 * @param objId - hashCode do objeto armazenado como v�rtice no grafo
	 * @return ArrayList<T> contendo objetos T no grafo.
	 */
	public ArrayList<T> sucessores(int objId) {
		Vertice<T> v;
		
		if ((v = vertices.get(objId)) == null)
			throw new NonVerticeException();
		
		return verticesParaT(v.getSucessores());
	}
	
	/**
	 * Retorna uma lista contendo os objetos antecessores de um objeto representado como v�rtice no grafo.
	 * @param obj - objeto armazenado como v�rtice no grafo
	 * @return ArrayList<T> contendo objetos T no grafo.
	 */
	public ArrayList<T> antecessores(T obj) {
		return antecessores(obj.hashCode());
	}
	
	/**
	 * Retorna uma lista contendo os objetos antecessores de um objeto representado como v�rtice no grafo.
	 * @param objId - hashCode do objeto armazenado como v�rtice no grafo
	 * @return ArrayList<T> contendo objetos T no grafo.
	 */
	public ArrayList<T> antecessores(int objId) {
		Vertice<T> v;
		
		if ((v = vertices.get(objId)) == null)
			throw new NonVerticeException();
		
		return verticesParaT(v.getAntecessores());
	}
	
	/**
	 * Retorna uma lista contendo os objetos que s�o transitivamente alcanc�veis
	 * partindo-se de um objeto representado como v�rtice no grafo.
	 * @param obj - objeto armazenado como v�rtice no grafo
	 * @return ArrayList<T> contendo objetos T no grafo.
	 */
	public ArrayList<T> fechoTransitivoDireto(T obj) {
		return fechoTransitivoDireto(obj.hashCode());
	}
	
	/**
	 * Retorna uma lista contendo os objetos que s�o transitivamente alcanc�veis
	 * partindo-se de um objeto representado como v�rtice no grafo.
	 * @param obj - hashCode do objeto armazenado como v�rtice no grafo
	 * @return ArrayList<T> contendo objetos T no grafo.
	 */
	public ArrayList<T> fechoTransitivoDireto(int objId) {
		Vertice<T> v;
		
		if ((v = vertices.get(objId)) == null)
			throw new NonVerticeException();
		
		Collection<Vertice<T>> F;
	
		F = new ArrayList<Vertice<T>>();
		
		F.add(v);
		fechoTransitivoDiretoR(F, v);
		
		return verticesParaT(F);
	}
	
	private void fechoTransitivoDiretoR(Collection<Vertice<T>> F, Vertice<T> v) {
		for (Vertice<T> w : v.getSucessores())
			if (!F.contains(w)) {
				F.add(w);
				fechoTransitivoDiretoR(F, w);
			}
	}
	
	/**
	 * Retorna uma lista contendo os objetos que alcan�am transitivamente 
	 * um objeto representado como v�rtice no grafo.
	 * @param obj - objeto armazenado como v�rtice no grafo
	 * @return ArrayList<T> contendo objetos T no grafo.
	 */
	public ArrayList<T> fechoTransitivoIndireto(T obj) {
		return fechoTransitivoIndireto(obj.hashCode());
	}
	
	/**
	 * Retorna uma lista contendo os objetos que alcan�am transitivamente
	 * um objeto representado como v�rtice no grafo.
	 * @param obj - hashCode do objeto armazenado como v�rtice no grafo
	 * @return ArrayList<T> contendo objetos T no grafo.
	 */
	public ArrayList<T> fechoTransitivoIndireto(int objId) {
		Vertice<T> v;
		
		if ((v = vertices.get(objId)) == null)
			throw new NonVerticeException();
		
		Collection<Vertice<T>> F;
		
		F = new ArrayList<Vertice<T>>();
		
		F.add(v);
		fechoTransitivoIndiretoR(F, v);
		
		return verticesParaT(F);
	}
	
	private void fechoTransitivoIndiretoR(Collection<Vertice<T>> F, Vertice<T> v) {
		for (Vertice<T> w : v.getAntecessores())
			if (!F.contains(w)) {
				F.add(w);
				fechoTransitivoIndiretoR(F, w);
			}
	}
	
	public boolean contemArco(int objId1, int objId2) {
		for (T sucessor : sucessores(objId1)) {
			if (sucessor.hashCode() == objId2)
				return true;
		}
		
		return false;
	}
}
