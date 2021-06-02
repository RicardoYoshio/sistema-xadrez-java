package Xadrez;

import Xadrez.peças.Rei;
import Xadrez.peças.Torre;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		setupInicial();
	}
	
	public PeçadeXadrez[][] getPeças() {
		PeçadeXadrez[][] mat = new PeçadeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (PeçadeXadrez) tabuleiro.peça(i, j);
			}
		}
		return mat;
	}
	
	private void setupInicial() {
		tabuleiro.colocarPeça(new Torre(tabuleiro, Cor.BRANCA), new Posição(2, 1));
		tabuleiro.colocarPeça(new Rei(tabuleiro, Cor.PRETA), new Posição(0, 4));
		tabuleiro.colocarPeça(new Rei(tabuleiro, Cor.BRANCA), new Posição(7, 4));
	}
}
