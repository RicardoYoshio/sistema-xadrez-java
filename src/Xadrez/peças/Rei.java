package Xadrez.pe�as;

import Xadrez.Cor;
import Xadrez.PartidaDeXadrez;
import Xadrez.Pe�adeXadrez;
import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;

public class Rei extends Pe�adeXadrez {

	private PartidaDeXadrez partidaDeXadrez;
	
	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
		
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posi��o posi��o) {
		Pe�adeXadrez p = (Pe�adeXadrez)getTabuleiro().pe�a(posi��o);
		return p == null || p.getCor() != getCor();
	}

	private boolean testeTorreRoque(Posi��o posi��o) {
		Pe�adeXadrez p = (Pe�adeXadrez)getTabuleiro().pe�a(posi��o);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContadorMovimento() == 0;
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
		
		// #movimento especial Roque
		if(getContadorMovimento() ==0 && !partidaDeXadrez.getCheck()) {
			// #movimento especial Roque Torre do Rei
			Posi��o posT1 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() + 3);
			if(testeTorreRoque(posT1)) {
				Posi��o p1 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() + 1);
				Posi��o p2 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() + 2);
				if (getTabuleiro().pe�a(p1) == null && getTabuleiro().pe�a(p2) == null) {
					mat[posi��o.getLinha()][posi��o.getColuna() + 2] = true;
				}	
			}
			// #movimento especial Roque Torre do Rainha
			Posi��o posT2 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() - 4);
			if(testeTorreRoque(posT2)) {
				Posi��o p1 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() - 1);
				Posi��o p2 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() - 2);
				Posi��o p3 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() - 3);
				if (getTabuleiro().pe�a(p1) == null && getTabuleiro().pe�a(p2) == null && getTabuleiro().pe�a(p3) == null) {
					mat[posi��o.getLinha()][posi��o.getColuna() - 2] = true;
							}
						}
		}
		return mat;
	}
}



