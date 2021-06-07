package Xadrez.peças;

import Xadrez.Cor;
import Xadrez.PartidaDeXadrez;
import Xadrez.PeçadeXadrez;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;

public class Peao extends PeçadeXadrez{

	private PartidaDeXadrez partidaDeXadrez;
	
	
	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
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
			
			//*movimento especial en passant branca
			if(posição.getLinha() == 3) {
				Posição esquerda = new Posição(posição.getLinha(), posição.getColuna() - 1);
				if(getTabuleiro().existePosição(esquerda) && peçaDoOponente(esquerda) && getTabuleiro().peça(esquerda) == partidaDeXadrez.getvulneravelEnPassant()) {
					mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				Posição direita = new Posição(posição.getLinha(), posição.getColuna() + 1);
				if(getTabuleiro().existePosição(direita) && peçaDoOponente(direita) && getTabuleiro().peça(direita) == partidaDeXadrez.getvulneravelEnPassant()) {
				mat[direita.getLinha() - 1][direita.getColuna()] = true;
				}	
			}
		}
		
		else {
			p.setValores(posição.getLinha() + 1,  posição.getColuna());
			if(getTabuleiro().existePosição(p) && !getTabuleiro().haUmaPeça(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posição.getLinha() + 2,  posição.getColuna());
			Posição p2 = new Posição(posição.getLinha() + 1, posição.getColuna());
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
			
			//*movimento especial en passant preta
			if(posição.getLinha() == 4) {
				Posição esquerda = new Posição(posição.getLinha(), posição.getColuna() - 1);
				if(getTabuleiro().existePosição(esquerda) && peçaDoOponente(esquerda) && getTabuleiro().peça(esquerda) == partidaDeXadrez.getvulneravelEnPassant()) {
					mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
			}
				Posição direita = new Posição(posição.getLinha(), posição.getColuna() + 1);
				if(getTabuleiro().existePosição(direita) && peçaDoOponente(direita) && getTabuleiro().peça(direita) == partidaDeXadrez.getvulneravelEnPassant()) {
					mat[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
			
		}
			
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}
}