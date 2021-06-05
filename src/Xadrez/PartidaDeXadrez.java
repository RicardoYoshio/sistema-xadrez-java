package Xadrez;

import Xadrez.pe�as.Rei;
import Xadrez.pe�as.Torre;
import tabuleiro.Pe�a;
import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;

public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCA;
		setupInicial();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getjogadorAtual() {
		return jogadorAtual;
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
	
	public boolean[][] movimentosPossiveis(PosicaoXadrez posi��oOrigem){
		Posi��o posi��o = posi��oOrigem.toPosi��o();
		validadePosi��oOrigem(posi��o);
		return tabuleiro.pe�a(posi��o).movimentosPossiveis();
	}
	
	
	public Pe�adeXadrez movimentoXadrez(PosicaoXadrez posi��oOrigem, PosicaoXadrez posi��oDestino) {
		Posi��o origem = posi��oOrigem.toPosi��o();
		Posi��o destino = posi��oDestino.toPosi��o();
		validadePosi��oOrigem(origem);
		validadePosi��oDestino(origem, destino);
		Pe�a pe�aCapturada = movimentoPe�a(origem, destino);
		proximoTurno();
		return (Pe�adeXadrez)pe�aCapturada;
	}
	
	private Pe�a movimentoPe�a(Posi��o origem, Posi��o destino) {
		Pe�a p = tabuleiro.removePe�a(origem);
		Pe�a pe�aCapturada = tabuleiro.removePe�a(destino);
		tabuleiro.colocarPe�a(p, destino);
		return pe�aCapturada;
		
	}
	
	private void validadePosi��oOrigem(Posi��o posi��o) {
		if (!tabuleiro.haUmaPe�a(posi��o)) {
			throw new ExcessaoXadrez("N�o existe pe�a na posi��o de Origem");
		}
		if (jogadorAtual != ((Pe�adeXadrez)tabuleiro.pe�a(posi��o)).getCor()) {
			throw new ExcessaoXadrez("A pe�a escolhida n�o � sua");
		}
		if (!tabuleiro.pe�a(posi��o). exiteMovimentoPossivel()) {
			throw new ExcessaoXadrez("N�o existe movimentos possiveis para a pe�a escolhida");
		}
	}
	
	private void validadePosi��oDestino(Posi��o origem, Posi��o destino) {
		if (!tabuleiro.pe�a(origem).movimentoPossiveis(destino)) {
			throw new ExcessaoXadrez("A pe�a escolhida n�o pode se mover para o destino");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private void  colocarNovaPe�a(char coluna, int linha, Pe�adeXadrez pe�a) {
		tabuleiro.colocarPe�a(pe�a, new PosicaoXadrez(coluna, linha).toPosi��o());
	}
	
	private void setupInicial() {
		colocarNovaPe�a('c', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('c', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('d', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('e', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('e', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('d', 1, new Rei(tabuleiro, Cor.BRANCA));

		colocarNovaPe�a('c', 7, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('c', 8, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('d', 7, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('e', 7, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('e', 8, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('d', 8, new Rei(tabuleiro, Cor.PRETA));
	}
}
