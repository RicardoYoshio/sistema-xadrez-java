package Xadrez;

import Xadrez.peças.Rei;
import Xadrez.peças.Torre;
import tabuleiro.Peça;
import tabuleiro.Posição;
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
	
	public PeçadeXadrez[][] getPeças() {
		PeçadeXadrez[][] mat = new PeçadeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (PeçadeXadrez) tabuleiro.peça(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] movimentosPossiveis(PosicaoXadrez posiçãoOrigem){
		Posição posição = posiçãoOrigem.toPosição();
		validadePosiçãoOrigem(posição);
		return tabuleiro.peça(posição).movimentosPossiveis();
	}
	
	
	public PeçadeXadrez movimentoXadrez(PosicaoXadrez posiçãoOrigem, PosicaoXadrez posiçãoDestino) {
		Posição origem = posiçãoOrigem.toPosição();
		Posição destino = posiçãoDestino.toPosição();
		validadePosiçãoOrigem(origem);
		validadePosiçãoDestino(origem, destino);
		Peça peçaCapturada = movimentoPeça(origem, destino);
		proximoTurno();
		return (PeçadeXadrez)peçaCapturada;
	}
	
	private Peça movimentoPeça(Posição origem, Posição destino) {
		Peça p = tabuleiro.removePeça(origem);
		Peça peçaCapturada = tabuleiro.removePeça(destino);
		tabuleiro.colocarPeça(p, destino);
		return peçaCapturada;
		
	}
	
	private void validadePosiçãoOrigem(Posição posição) {
		if (!tabuleiro.haUmaPeça(posição)) {
			throw new ExcessaoXadrez("Não existe peça na posição de Origem");
		}
		if (jogadorAtual != ((PeçadeXadrez)tabuleiro.peça(posição)).getCor()) {
			throw new ExcessaoXadrez("A peça escolhida não é sua");
		}
		if (!tabuleiro.peça(posição). exiteMovimentoPossivel()) {
			throw new ExcessaoXadrez("Não existe movimentos possiveis para a peça escolhida");
		}
	}
	
	private void validadePosiçãoDestino(Posição origem, Posição destino) {
		if (!tabuleiro.peça(origem).movimentoPossiveis(destino)) {
			throw new ExcessaoXadrez("A peça escolhida não pode se mover para o destino");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private void  colocarNovaPeça(char coluna, int linha, PeçadeXadrez peça) {
		tabuleiro.colocarPeça(peça, new PosicaoXadrez(coluna, linha).toPosição());
	}
	
	private void setupInicial() {
		colocarNovaPeça('c', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeça('c', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeça('d', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeça('e', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeça('e', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeça('d', 1, new Rei(tabuleiro, Cor.BRANCA));

		colocarNovaPeça('c', 7, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPeça('c', 8, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPeça('d', 7, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPeça('e', 7, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPeça('e', 8, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPeça('d', 8, new Rei(tabuleiro, Cor.PRETA));
	}
}
