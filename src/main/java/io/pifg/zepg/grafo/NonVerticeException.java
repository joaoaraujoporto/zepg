package io.pifg.zepg.grafo;

public class NonVerticeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NonVerticeException() {
		super("Vertice n�o est� no grafo.");
	}
}
