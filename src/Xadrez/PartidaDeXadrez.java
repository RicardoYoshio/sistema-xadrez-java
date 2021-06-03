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
	
	private void  colocarNovaPe�a(char coluna, int linha, Pe�adeXadrez pe�a) {
		tabuleiro.colocarPe�a(pe�a, new PosicaoXadrez(coluna, linha).toPosi��o());
	}
	
	private void setupInicial() {
		colocarNovaPe�a('b', 6, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('e', 8, new Rei(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('e', 1, new Rei(tabuleiro, Cor.BRANCA));
	}
}
