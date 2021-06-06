package Xadrez.pe�as;

import Xadrez.Cor;
import Xadrez.Pe�adeXadrez;
import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;

public class Bispo extends Pe�adeXadrez{

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
	
	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posi��o p = new Posi��o (0, 0);
		
		// NO
		p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() - 1);
		while (getTabuleiro().existePosi��o(p) && !getTabuleiro().haUmaPe�a(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTabuleiro().existePosi��o(p) &&  pe�aDoOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		// NE
		p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() + 1);
		while (getTabuleiro().existePosi��o(p) && !getTabuleiro().haUmaPe�a(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTabuleiro().existePosi��o(p) &&  pe�aDoOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
				}
		
		// SE
		p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() + 1);
		while (getTabuleiro().existePosi��o(p) && !getTabuleiro().haUmaPe�a(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().existePosi��o(p) &&  pe�aDoOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		// SO
		p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() - 1);
		while (getTabuleiro().existePosi��o(p) && !getTabuleiro().haUmaPe�a(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() - 1);
		}
    	if (getTabuleiro().existePosi��o(p) &&  pe�aDoOponente(p)) {
		    mat [p.getLinha()][p.getColuna()] = true;
		}
				
				
		return mat;
	}
}


