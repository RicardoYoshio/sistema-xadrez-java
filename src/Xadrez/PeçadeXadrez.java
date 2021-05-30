package Xadrez;

import tabuleiro.Peça;
import tabuleiro.Tabuleiro;

public class PeçadeXadrez extends Peça{
	
	private Cor cor;

	public PeçadeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

}
