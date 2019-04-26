package io.pifg.zepg.grafo;

import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Estrutura de dados para representa��o computacional de grafos.
 * @author joaov.aporto@gmail.com
 * @param <T> tipo de objeto que ser� representado como v�rtice pela estrutura de grafo.
 */
public class Grafo<T> {
	protected static int ordem;
	protected Map<Integer,Vertice<T>> vertices;
	
	public Grafo() {
		ordem = 0;
		vertices = new HashMap<Integer,Vertice<T>>();
	}
	
	/**
	 * Adiciona um novo v�rtice no grafo.
	 * @param obj - objeto que ser� armazenado como um v�rtice do grafo
	 * @return true se v�rtice foi adicionado com sucesso; caso contr�rio, false.
	 */
	public boolean adicionarVertice(T obj) {
		if (vertices.containsKey(obj.hashCode()))
				return false;
		
		Vertice<T> v;
		
		v = new Vertice<T>(obj);
		
		vertices.put(obj.hashCode(), v);		
		ordem++;
		
		return true;
	}

	/**
	 * Adiciona uma aresta ao grafo, ligando dois objetos representados por v�rtices.
	 * @param obj1 - objeto armazenado como v�rtice no grafo (representar� a origem da aresta
	 * se grafo considerado orientado)
	 * @param obj2 - objeto armazenado como v�rtice no grafo (representar� a extremidade da aresta
	 * se grafo considerado orientado)
	 * @return true se aresta foi adicionada com sucesso; caso contr�rio, false.
	 */
	public boolean adicionarAresta(T obj1, T obj2) {
		return adicionarAresta(obj1.hashCode(), obj2.hashCode());
	}
	
	/**
	 * Adiciona uma aresta ao grafo, ligando dois objetos representados por v�rtices.
	 * @param obj1Id - hashCode do objeto armazenado como v�rtice no grafo (representar� a origem da
	 * aresta se grafo considerado orientado)
	 * @param obj2Id - hashCode do objeto armazenado como v�rtice no grafo (representar� a extremidade
	 * da aresta se grafo considerado orientado)
	 * @return true se aresta foi adicionada com sucesso; caso contr�rio, false.
	 */
	public boolean adicionarAresta(int obj1Id, int obj2Id) {
		return adicionarAresta(vertices.get(obj1Id), vertices.get(obj2Id));
	}

	/**
	 * Adiciona uma aresta ao grafo, ligando dois v�rtices.
	 * @param v1 - v�rtice no grafo (representar� a origem da aresta se grafo considerado orientado)
	 * @param v2 - v�rtice no grafo (representar� a extremidade da aresta se grafo considerado orientado)
	 * @return true se aresta foi adicionada com sucesso; caso contr�rio, false.
	 */
	private boolean adicionarAresta(Vertice<T> v1, Vertice<T> v2) {
		if (v1 == null || v2 == null)
			return false;
		
		if (!v1.adicionarSucessor(v2))
			return false;
		
		v2.adicionarAntecessor(v1);
		
		return true;
	}
	
	/**
	 * Remove um v�rtice do grafo, juntamente com todas as conex�es.
	 * @param obj - objeto armazenado como v�rtice no grafo que ser� removido
	 * @return true se o v�rtice foi removido com sucesso; caso contr�rio, false.
	 */
	public boolean removerVertice(T obj) {		
		return removerVertice(obj.hashCode());
	}	
	
	/**
	 * Remove um v�rtice do grafo, juntamente com todas as conex�es.
	 * @param objId - hashCode do objeto armazenado como v�rtice no grafo que ser� removido
	 * @return true se o v�rtice foi removido com sucesso; caso contr�rio, false.
	 */
	public boolean removerVertice(int objId) {		
		return removerVertice(vertices.get(objId));
	}	
	
	/**
	 * Remove um v�rtice do grafo, juntamente com todas as conex�es.
	 * @param v - v�rtice no grafo que ser� removido
	 * @return true se o v�rtice foi removido com sucesso; caso contr�rio, false.
	 */
	protected boolean removerVertice(Vertice<T> v) {
		if (v == null)
			return false;
		
		Collection<Vertice<T>> adjacentes;
		
		adjacentes = v.getSucessores();
		for (Vertice<T> w : adjacentes)
			w.removerAntecessor(v);
		
		adjacentes = v.getAntecessores();
		for (Vertice<T> w : adjacentes)
			w.removerSucessor(v);
		
		return true;
	}
	
	/**
	 * Remove aresta do grafo, desligando dois objetos representados por v�rtices.
	 * @param obj1 - objeto armazenado como v�rtice no grafo (representa a origem da aresta
	 * se grafo considerado orientado)
	 * @param obj2 - objeto armazenado como v�rtice no grafo (representa a extremidade da aresta
	 * se grafo considerado orientado)
	 * @return true se a aresta foi removida com sucesso; caso contr�rio, false.
	 */ 
	public boolean removerAresta(T obj1, T obj2) {
		return removerAresta(obj1.hashCode(), obj2.hashCode());
	}
	
	/**
	 * Remove aresta do grafo, desligando dois objetos representados por v�rtices.
	 * @param obj1Id - hashCode do objeto armazenado como v�rtice no grafo (representa a origem da aresta
	 * se grafo considerado orientado)
	 * @param obj2Id - hashCode do objeto armazenado como v�rtice no grafo (representa a extremidade da aresta
	 * se grafo considerado orientado)
	 * @return true se a aresta foi removida com sucesso; caso contr�rio, false.
	 */
	public boolean removerAresta(int obj1Id, int obj2Id) {
		return removerAresta(vertices.get(obj1Id), vertices.get(obj1Id));
	}

	/**
	 * Remove aresta do grafo, desligando dois objetos representados por v�rtices.
	 * @param v1 - v�rtice no grafo (representa a origem da aresta
	 * se grafo considerado orientado)
	 * @param v2 - v�rtice no grafo (representa a extremidade da aresta
	 * se grafo considerado orientado)
	 * @return true se a aresta foi removida com sucesso; caso contr�rio, false. 
	 */
	protected boolean removerAresta(Vertice<T> v1, Vertice<T> v2) {
		if (v1 == null || v2 == null)
			return false;
		
		if (!(v1.removerSucessor(v2) && v2.removerAntecessor(v1)))
			if (!(v2.removerSucessor(v1) && v1.removerAntecessor(v2)))
				return false;
		
		return true;
	}
	
	/**
	 * Retorna o n�mero de v�rtices do grafo.
	 * @return int correspondente ao n�mero de v�rtices do grafo.
	 */
	public int getOrdem() {
		getClass();
		return ordem;
	}
	
	/**
	 * Retorna uma lista contendo os objetos representados como v�rtices no grafo.
	 * @return ArrayList<T> contendo objetos T no grafo.
	 */
	public ArrayList<T> getVertices() {
		return verticesParaT(this.vertices.values());
	}
	
	/**
	 * Retorna um objeto representado por um v�rtice qualquer no grafo.
	 * @return objeto T qualquer no grafo.
	 */
	public T getVerticeQualquer() {
		return (getVertices().get(0));
	}
	
	/**
	 * Retorna o grau de um objeto representado como v�rtice no grafo.
	 * @param obj - objeto armazenado como v�rtice no grafo
	 * @return int correpondente ao grau do v�rtice.
	 */
	public int getGrau(T obj) {
		return getGrau(obj.hashCode());
	}
	
	/**
	 * Retorna o grau de um objeto representado como v�rtice no grafo.
	 * @param obj - hashCode do objeto armazenado como v�rtice no grafo
	 * @return int correpondente ao grau do v�rtice
	 */
	public int getGrau(int objId) {
		Vertice<T> v;
		
		if ((v = vertices.get(objId)) == null)
			throw new NonVerticeException();
		
		return v.getAntecessores().size() + v.getSucessores().size();
	}
	
	/**
	 * Verifica se todos os v�rtices do grafo possuem o mesmo grau.
	 * @return true se grafo � regular; caso contr�rio, false.
	 */
	public boolean ehRegular() {
		Collection<Vertice<T>> vertices;
		T v;
		int grau;
		
		vertices = this.vertices.values();
		v = getVerticeQualquer();
		grau = getGrau(v.hashCode());
		
		for (Vertice<T> w : vertices)
			if (getGrau(w.hashCode()) != grau)
				return false;
		
		return true;
	}
	
	/**
	 * Verifica se cada v�rtice do grafo est� conectado a todos os outros v�rtices.
	 * @return true se grafo � completo; caso contr�rio, false.
	 */
	public boolean ehCompleto() {
		Collection<Vertice<T>> vertices;
		
		vertices = this.vertices.values();
		
		for (Vertice<T> w : vertices)
			if (getGrau(w.hashCode()) != getOrdem() - 1)
				return false;
		
		return true;
	}
	
	/**
	 * Retorna uma lista contendo os objetos adjacentes de um objeto representado como v�rtice no grafo.
	 * @param obj - objeto armazenado como v�rtice no grafo
	 * @return ArrayList<T> contendo objetos T no grafo.
	 */
	public ArrayList<T> adjacentes(T obj) {
		return adjacentes(obj.hashCode());
	}
	
	/**
	 * Retorna uma lista contendo os objetos adjacentes de um objeto representado como v�rtice no grafo.
	 * @param objId - hashCode do objeto armazenado como v�rtice no grafo
	 * @return ArrayList<T> contendo objetos T no grafo.
	 */
	public ArrayList<T> adjacentes(int objId) {
		Vertice<T> v;
		
		if ((v = vertices.get(objId)) == null)
			throw new NonVerticeException();
		
		return verticesParaT(v.getAdjacentes());
	}	
	
	/**
	 * Retorna uma lista contendo os objetos que s�o transitivamente alcanc�veis
	 * partindo-se de um objeto representado como v�rtice no grafo.
	 * @param obj - objeto armazenado como v�rtice no grafo
	 * @return ArrayList<T> contendo objetos T no grafo.
	 */
	public ArrayList<T> fechoTransitivo(T obj) {
		return fechoTransitivo(obj.hashCode());
	}
	
	/**
	 * Retorna uma lista contendo os objetos que s�o transitivamente alcanc�veis
	 * partindo-se de um objeto representado como v�rtice no grafo. Orienta��o � ignorada.
	 * @param obj - hashCode do objeto armazenado como v�rtice no grafo
	 * @return ArrayList<T> contendo objetos T no grafo.
	 */
	public ArrayList<T> fechoTransitivo(int objId) {
		Vertice<T> v;
		
		if ((v = vertices.get(objId)) == null)
			throw new NonVerticeException();
		
		Collection<Vertice<T>> F;
		
		F = new ArrayList<Vertice<T>>();
		
		F.add(v);
		fechoTransitivoR(F, v);
		
		return verticesParaT(F);
	}
	
	private void fechoTransitivoR(Collection<Vertice<T>> F, Vertice<T> v) {
		for (Vertice<T> w : v.getAdjacentes())
			if (!F.contains(w)) {
				F.add(w);
				fechoTransitivoR(F, w);
			}
	}
	
	/**
	 * Verifica se existe pelo menos um caminho entre cada par de v�rtices do grafo.
	 * @return true se grafo � conexo; caso contr�rio, false.
	 */
	public boolean ehConexo() {
		T v;
		
		v = getVerticeQualquer();
		
		ArrayList<T> fecho = fechoTransitivo(v.hashCode());
		return fecho.equals(getVertices());
	}
	
	/**
	 * Verifica se o grafo � uma �rvore, ou seja, � conexo e n�o possui ciclos.
	 * @return true se grafo � uma �rvore; caso contr�rio, false.
	 */
	public boolean ehArvore() {
		if (!ehConexo())
			return false;
		
		Vertice<T> v;
		
		ArrayList<Vertice<T>> F;
		v = vertices.get(getVerticeQualquer().hashCode());
		
		F = new ArrayList<Vertice<T>>();
		
		return !haCicloCom(v, v, F);
	}
	
	protected boolean haCicloCom(Vertice<T> u, Vertice<T> v, Collection<Vertice<T>> F) {
		if (F.contains(v))
			return true;
		
		for (Vertice<T> w : v.getAdjacentes())
			if (!w.equals(u))
				if (haCicloCom(v, w, F))
					return true;
				
		F.remove(v);
		return false;
	}
	
	protected ArrayList<T> verticesParaT(Collection<Vertice<T>> vertices) {
		ArrayList<T> objetos;
		
		objetos = new ArrayList<T>();
		
		for (Vertice<T> v : vertices)
			objetos.add(v.getObj());
		
		return objetos;		
	}
	
	public boolean contemVertice(T obj) {
		return contemVertice(obj.hashCode());
	}
	
	public boolean contemVertice(int objId) {
		return vertices.containsKey(objId);
	}
	
	public ArrayList<T> getAdjacentes(T obj) {
		return getAdjacentes(obj.hashCode());
	}
	
	public ArrayList<T> getAdjacentes(int objId) {
		ArrayList<T> adjacentes = new ArrayList<T>();
		
		for (Vertice<T> v : vertices.get(objId).getAdjacentes())
			adjacentes.add(v.getObj());
		
		return adjacentes;
	}
}