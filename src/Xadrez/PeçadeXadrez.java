package Xadrez;

import tabuleiro.Peça;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;

public abstract class PeçadeXadrez extends Peça{
	
	private Cor cor;

	public PeçadeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

	protected boolean peçaDoOponente(Posição posição) {
		PeçadeXadrez p = (PeçadeXadrez) getTabuleiro().peça(posição);
		return p != null && p.getCor() != cor;
	}
}
