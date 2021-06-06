package Xadrez.pe�as;

import Xadrez.Cor;
import Xadrez.Pe�adeXadrez;
import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;

public class Peao extends Pe�adeXadrez{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posi��o p = new Posi��o (0, 0);
		
		if (getCor() == Cor.BRANCA) {
			p.setValores(posi��o.getLinha() - 1,  posi��o.getColuna());
			if(getTabuleiro().existePosi��o(p) && !getTabuleiro().haUmaPe�a(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posi��o.getLinha() - 2,  posi��o.getColuna());
			Posi��o p2 = new Posi��o(posi��o.getLinha() - 1, posi��o.getColuna());
			if (getTabuleiro().existePosi��o(p) && !getTabuleiro().haUmaPe�a(p) && getTabuleiro().existePosi��o(p2) && !getTabuleiro().haUmaPe�a(p2) && getContadorMovimento() == 0) {
					mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posi��o.getLinha() - 1,  posi��o.getColuna() - 1);
			if(getTabuleiro().existePosi��o(p) && pe�aDoOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posi��o.getLinha() - 1,  posi��o.getColuna() + 1);
			if(getTabuleiro().existePosi��o(p) && pe�aDoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		else {
				p.setValores(posi��o.getLinha() + 1,  posi��o.getColuna());
				if(getTabuleiro().existePosi��o(p) && !getTabuleiro().haUmaPe�a(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValores(posi��o.getLinha() + 2,  posi��o.getColuna());
				Posi��o p2 = new Posi��o(posi��o.getLinha() - 1, posi��o.getColuna());
				if (getTabuleiro().existePosi��o(p) && !getTabuleiro().haUmaPe�a(p) && getTabuleiro().existePosi��o(p2) && !getTabuleiro().haUmaPe�a(p2) && getContadorMovimento() == 0) {
						mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValores(posi��o.getLinha() + 1,  posi��o.getColuna() - 1);
				if(getTabuleiro().existePosi��o(p) && pe�aDoOponente(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValores(posi��o.getLinha() + 1,  posi��o.getColuna() + 1);
				if(getTabuleiro().existePosi��o(p) && pe�aDoOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
		}
	}			
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}
}