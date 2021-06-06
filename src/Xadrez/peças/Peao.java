package Xadrez.peças;

import Xadrez.Cor;
import Xadrez.PeçadeXadrez;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;

public class Peao extends PeçadeXadrez{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posição p = new Posição (0, 0);
		
		if (getCor() == Cor.BRANCA) {
			p.setValores(posição.getLinha() - 1,  posição.getColuna());
			if(getTabuleiro().existePosição(p) && !getTabuleiro().haUmaPeça(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posição.getLinha() - 2,  posição.getColuna());
			Posição p2 = new Posição(posição.getLinha() - 1, posição.getColuna());
			if (getTabuleiro().existePosição(p) && !getTabuleiro().haUmaPeça(p) && getTabuleiro().existePosição(p2) && !getTabuleiro().haUmaPeça(p2) && getContadorMovimento() == 0) {
					mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posição.getLinha() - 1,  posição.getColuna() - 1);
			if(getTabuleiro().existePosição(p) && peçaDoOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posição.getLinha() - 1,  posição.getColuna() + 1);
			if(getTabuleiro().existePosição(p) && peçaDoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		else {
				p.setValores(posição.getLinha() + 1,  posição.getColuna());
				if(getTabuleiro().existePosição(p) && !getTabuleiro().haUmaPeça(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValores(posição.getLinha() + 2,  posição.getColuna());
				Posição p2 = new Posição(posição.getLinha() - 1, posição.getColuna());
				if (getTabuleiro().existePosição(p) && !getTabuleiro().haUmaPeça(p) && getTabuleiro().existePosição(p2) && !getTabuleiro().haUmaPeça(p2) && getContadorMovimento() == 0) {
						mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValores(posição.getLinha() + 1,  posição.getColuna() - 1);
				if(getTabuleiro().existePosição(p) && peçaDoOponente(p)) {
						mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValores(posição.getLinha() + 1,  posição.getColuna() + 1);
				if(getTabuleiro().existePosição(p) && peçaDoOponente(p)) {
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