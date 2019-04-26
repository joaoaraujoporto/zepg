package io.pifg.zepg.grafo;

import java.util.ArrayList;

public class DAG<T> extends Digrafo<T> {
	public ArrayList<T> ordenacaoTopologica() {
		ArrayList<T> lista = new ArrayList<T>();
		ArrayList<T> fontes = new ArrayList<T>();
		ArrayList<T> visitados = new ArrayList<T>();
		
		for (T obj : this.getVertices()) {
			if (this.antecessores(obj).isEmpty())
				fontes.add(obj);
		}
		
		for (T obj : fontes)
			ordernacaoTopologica(lista, visitados, obj);
		
		return lista;
	}

	private void ordernacaoTopologica(ArrayList<T> lista, ArrayList<T> visitados, T obj) {
		if (!visitados.contains(obj)) {
			visitados.add(obj);
			
			for (T w : this.sucessores(obj))
				ordernacaoTopologica(lista, visitados, w);
			
			lista.add(0, obj);
		}
	}
}
