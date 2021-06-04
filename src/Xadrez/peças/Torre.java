package Xadrez.pe�as;

import Xadrez.Cor;
import Xadrez.Pe�adeXadrez;
import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;

public class Torre extends Pe�adeXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
	
	@Override
	public String toString() {
		return "T";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posi��o p = new Posi��o (0, 0);
		
		// above
		p.setValores(posi��o.getLinha() -1, posi��o.getColuna());
		while (getTabuleiro().existePosi��o(p) && !getTabuleiro().haUmaPe�a(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().existePosi��o(p) &&  pe�aDoOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}
}
