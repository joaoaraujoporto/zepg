package io.pifg.zepg.grafo;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

public class Vertice<T> {
	protected T obj;
	protected int id;
	protected Map<Integer,Vertice<T>> sucessores;
	protected Map<Integer,Vertice<T>> antecessores;

	public Vertice(T obj) {
		this.obj = obj;
		this.id = obj.hashCode();
		sucessores = new HashMap<Integer,Vertice<T>>();
		antecessores = new HashMap<Integer,Vertice<T>>();
	}
	
	public boolean adicionarSucessor(Vertice<T> v) {
		return ligarComVertice(sucessores, v);
	}
	
	public boolean adicionarAntecessor(Vertice<T> v) {
		return ligarComVertice(antecessores, v);
	}
	
	private boolean ligarComVertice(Map<Integer,Vertice<T>> vertices, Vertice<T> v) {
		if (vertices.containsKey(v.hashCode()))
			return false;

		vertices.put(v.hashCode(), v);
		
		return true;
	}
	
	public boolean removerSucessor(Vertice<T> v) {
		return desligarComVertice(sucessores, v);
	}
	
	public boolean removerAntecessor(Vertice<T> v) {
		return desligarComVertice(antecessores, v);
	}
	
	private boolean desligarComVertice(Map<Integer,Vertice<T>> vertices, Vertice<T> v) {
		return vertices.remove(v.hashCode(), v);
	}
	
	public T getObj() {
		return this.obj;
	}
	
	public int hashCode() {
		return this.id;
	}
	
	public Collection<Vertice<T>> getSucessores() {
		return sucessores.values();
	}
	
	public Collection<Vertice<T>> getAntecessores() {
		return antecessores.values();
	}
	
	public Collection<Vertice<T>> getAdjacentes() {
		ArrayList<Vertice<T>> adjacentes;
		
		adjacentes = new ArrayList<Vertice<T>>();
		
		for (Vertice<T> v : getSucessores())
			adjacentes.add(v);
		
		for (Vertice<T> v : getAntecessores())
			adjacentes.add(v);
		
		return adjacentes;
	}
	
	@Override
	public boolean equals(Object o) {
		Vertice<T> w = (Vertice<T>)o;
		
		return hashCode() == w.hashCode();
	}
}
