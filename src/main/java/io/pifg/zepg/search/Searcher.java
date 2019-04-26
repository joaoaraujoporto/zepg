package io.pifg.zepg.search;

import java.util.ArrayList;

import io.pifg.zepg.grafo.*;

public class Searcher {
	private static <T> Vertice<T> dfs(Grafo<T> G, Vertice<T> v, Vertice<T> w) {
		ArrayList<Vertice<T>> vis = new ArrayList<Vertice<T>>();
		return dfs(G, v, w, vis);
	}
	
	private static <T> Vertice<T> dfs(Grafo<T> G, Vertice<T> v, Vertice<T> w, ArrayList<Vertice<T>> vis) {		
		Vertice<T> result = null;
		
		if (v.equals(w))
			return v;
		
		vis.add(v);
		
		for (Vertice<T> u : v.getAdjacentes()) {
			if (vis.contains(u))
				continue;
			
			
			
			if ((result = dfs(G, u, w, vis)) != null)
				return result;
		}

		return result;
	}
	
	public static <T> T dfs(Grafo<T> G, T v, T w) {
		ArrayList<T> vis = new ArrayList<T>();
		return dfs(G, v, w, vis);
	}
	
	public static <T> T dfs(Grafo<T> G, T v, T w, ArrayList<T> vis) {		
		T result = null;
		
		if (v.equals(w))
			return v;
		
		vis.add(v);
		
		for (T u : G.getAdjacentes(v)) {
			if (vis.contains(u))
				continue;
			
			System.out.println("From " + v.toString() + " to " + u.toString());
			
			if ((result = dfs(G, u, w, vis)) != null)
				return result;
		}

		return result;
	}
}