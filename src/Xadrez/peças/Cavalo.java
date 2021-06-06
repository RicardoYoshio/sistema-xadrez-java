package Xadrez.pe�as;

import Xadrez.Cor;
import Xadrez.Pe�adeXadrez;
import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;

public class Cavalo extends Pe�adeXadrez{
	
	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
	
	@Override
	public String toString() {
		return "C";
	}
	
	private boolean podeMover(Posi��o posi��o) {
		Pe�adeXadrez p = (Pe�adeXadrez)getTabuleiro().pe�a(posi��o);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posi��o p = new Posi��o(0, 0);
		
		p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() - 2);
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posi��o.getLinha() - 2, posi��o.getColuna() - 1);
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posi��o.getLinha() - 2, posi��o.getColuna() + 1);
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() + 2);
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() + 2);
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() - 2);
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
			
		p.setValores(posi��o.getLinha() + 2, posi��o.getColuna() - 1);
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posi��o.getLinha() + 2, posi��o.getColuna() + 1);
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}


