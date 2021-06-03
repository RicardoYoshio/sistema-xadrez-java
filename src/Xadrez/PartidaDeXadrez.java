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
	
	private void  colocarNovaPeça(char coluna, int linha, PeçadeXadrez peça) {
		tabuleiro.colocarPeça(peça, new PosicaoXadrez(coluna, linha).toPosição());
	}
	
	private void setupInicial() {
		colocarNovaPeça('b', 6, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeça('e', 8, new Rei(tabuleiro, Cor.PRETA));
		colocarNovaPeça('e', 1, new Rei(tabuleiro, Cor.BRANCA));
	}
}
