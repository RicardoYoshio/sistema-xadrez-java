package tabuleiro;

public class Pe�a {
	protected Posi��o posi��o;
	private Tabuleiro tabuleiro;
	public Pe�a(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posi��o = null;
	}
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	
	
	}


