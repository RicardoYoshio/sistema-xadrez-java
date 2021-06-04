package Xadrez.peças;

import Xadrez.Cor;
import Xadrez.PeçadeXadrez;
import tabuleiro.Tabuleiro;

public class Rei extends PeçadeXadrez {

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



