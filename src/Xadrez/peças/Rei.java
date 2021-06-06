package Xadrez.peças;

import Xadrez.Cor;
import Xadrez.PartidaDeXadrez;
import Xadrez.PeçadeXadrez;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;

public class Rei extends PeçadeXadrez {

	private PartidaDeXadrez partidaDeXadrez;
	
	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
		
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posição posição) {
		PeçadeXadrez p = (PeçadeXadrez)getTabuleiro().peça(posição);
		return p == null || p.getCor() != getCor();
	}

	private boolean testeTorreRoque(Posição posição) {
		PeçadeXadrez p = (PeçadeXadrez)getTabuleiro().peça(posição);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContadorMovimento() == 0;
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
		
		// #movimento especial Roque
		if(getContadorMovimento() ==0 && !partidaDeXadrez.getCheck()) {
			// #movimento especial Roque Torre do Rei
			Posição posT1 = new Posição(posição.getLinha(), posição.getColuna() + 3);
			if(testeTorreRoque(posT1)) {
				Posição p1 = new Posição(posição.getLinha(), posição.getColuna() + 1);
				Posição p2 = new Posição(posição.getLinha(), posição.getColuna() + 2);
				if (getTabuleiro().peça(p1) == null && getTabuleiro().peça(p2) == null) {
					mat[posição.getLinha()][posição.getColuna() + 2] = true;
				}	
			}
			// #movimento especial Roque Torre do Rainha
			Posição posT2 = new Posição(posição.getLinha(), posição.getColuna() - 4);
			if(testeTorreRoque(posT2)) {
				Posição p1 = new Posição(posição.getLinha(), posição.getColuna() - 1);
				Posição p2 = new Posição(posição.getLinha(), posição.getColuna() - 2);
				Posição p3 = new Posição(posição.getLinha(), posição.getColuna() - 3);
				if (getTabuleiro().peça(p1) == null && getTabuleiro().peça(p2) == null && getTabuleiro().peça(p3) == null) {
					mat[posição.getLinha()][posição.getColuna() - 2] = true;
							}
						}
		}
		return mat;
	}
}



