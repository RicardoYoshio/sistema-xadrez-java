package Xadrez;

import tabuleiro.Pe�a;
import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;

public abstract class Pe�adeXadrez extends Pe�a{
	
	private Cor cor;

	public Pe�adeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

	protected boolean pe�aDoOponente(Posi��o posi��o) {
		Pe�adeXadrez p = (Pe�adeXadrez) getTabuleiro().pe�a(posi��o);
		return p != null && p.getCor() != cor;
	}
}
