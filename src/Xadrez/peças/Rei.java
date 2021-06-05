package Xadrez.peças;

import Xadrez.Cor;
import Xadrez.PeçadeXadrez;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;

public class Rei extends PeçadeXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posição posição) {
		PeçadeXadrez p = (PeçadeXadrez)getTabuleiro().peça(posição);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posição p = new Posição(0, 0);
		
		//above
		p.setValores(posição.getLinha() - 1, posição.getColuna());
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//below
		p.setValores(posição.getLinha() + 1, posição.getColuna());
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//left
		p.setValores(posição.getLinha(), posição.getColuna() - 1);
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//right
		p.setValores(posição.getLinha(), posição.getColuna() + 1);
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//no
		p.setValores(posição.getLinha() - 1, posição.getColuna() - 1);
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//ne
		p.setValores(posição.getLinha() - 1, posição.getColuna() + 1);
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
			
		//so
		p.setValores(posição.getLinha() + 1, posição.getColuna() - 1);
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//se
		p.setValores(posição.getLinha() + 1, posição.getColuna() + 1);
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}



