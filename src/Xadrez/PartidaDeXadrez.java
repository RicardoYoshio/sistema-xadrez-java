package Xadrez;

import Xadrez.pe�as.Rei;
import Xadrez.pe�as.Torre;
import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		setupInicial();
	}
	
	public Pe�adeXadrez[][] getPe�as() {
		Pe�adeXadrez[][] mat = new Pe�adeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (Pe�adeXadrez) tabuleiro.pe�a(i, j);
			}
		}
		return mat;
	}
	
	private void setupInicial() {
		tabuleiro.colocarPe�a(new Torre(tabuleiro, Cor.BRANCA), new Posi��o(2, 1));
		tabuleiro.colocarPe�a(new Rei(tabuleiro, Cor.PRETA), new Posi��o(0, 4));
		tabuleiro.colocarPe�a(new Rei(tabuleiro, Cor.BRANCA), new Posi��o(7, 4));
	}
}
