package Xadrez.pe�as;

import Xadrez.Cor;
import Xadrez.Pe�adeXadrez;
import tabuleiro.Tabuleiro;

public class Torre extends Pe�adeXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
	
	@Override
	public String toString() {
		return "T";
	}
}
