package Xadrez.pe�as;

import Xadrez.Cor;
import Xadrez.Pe�adeXadrez;
import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;

public class Rei extends Pe�adeXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posi��o posi��o) {
		Pe�adeXadrez p = (Pe�adeXadrez)getTabuleiro().pe�a(posi��o);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posi��o p = new Posi��o(0, 0);
		
		//above
		p.setValores(posi��o.getLinha() - 1, posi��o.getColuna());
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//below
		p.setValores(posi��o.getLinha() + 1, posi��o.getColuna());
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//left
		p.setValores(posi��o.getLinha(), posi��o.getColuna() - 1);
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//right
		p.setValores(posi��o.getLinha(), posi��o.getColuna() + 1);
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//no
		p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() - 1);
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//ne
		p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() + 1);
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
			
		//so
		p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() - 1);
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//se
		p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() + 1);
		if (getTabuleiro().existePosi��o(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}



