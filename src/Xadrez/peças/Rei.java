package Xadrez.pe�as;

import Xadrez.Cor;
import Xadrez.Pe�adeXadrez;
import tabuleiro.Tabuleiro;

public class Rei extends Pe�adeXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
	
	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return mat;
	}
}



