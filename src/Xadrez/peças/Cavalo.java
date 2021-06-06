package Xadrez.peças;

import Xadrez.Cor;
import Xadrez.PeçadeXadrez;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;

public class Cavalo extends PeçadeXadrez{
	
	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
	
	@Override
	public String toString() {
		return "C";
	}
	
	private boolean podeMover(Posição posição) {
		PeçadeXadrez p = (PeçadeXadrez)getTabuleiro().peça(posição);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posição p = new Posição(0, 0);
		
		p.setValores(posição.getLinha() - 1, posição.getColuna() - 2);
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posição.getLinha() - 2, posição.getColuna() - 1);
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posição.getLinha() - 2, posição.getColuna() + 1);
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posição.getLinha() - 1, posição.getColuna() + 2);
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posição.getLinha() + 1, posição.getColuna() + 2);
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posição.getLinha() + 1, posição.getColuna() - 2);
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
			
		p.setValores(posição.getLinha() + 2, posição.getColuna() - 1);
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posição.getLinha() + 2, posição.getColuna() + 1);
		if (getTabuleiro().existePosição(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}


