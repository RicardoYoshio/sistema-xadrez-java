package Xadrez;

import tabuleiro.Pe�a;
import tabuleiro.Tabuleiro;

public class Pe�adeXadrez extends Pe�a{
	
	private Cor cor;

	public Pe�adeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

}
