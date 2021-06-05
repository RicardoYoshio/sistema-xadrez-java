package Xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Xadrez.peças.Rei;
import Xadrez.peças.Torre;
import tabuleiro.Peça;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;

public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	
	private List<Peça> peçasNoTabuleiro = new ArrayList<>();
	private List<Peça> peçasCapturadas = new ArrayList<>();
	
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
	
	public boolean getCheck() {
		return check;
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
		
		if (testeCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, peçaCapturada);
			throw new ExcessaoXadrez ("Voce não pode se por em Check");	
		}
		
		check = (testeCheck(oponente(jogadorAtual))) ? true : false;
		
		proximoTurno();
		return (PeçadeXadrez)peçaCapturada;
	}
	
	private Peça movimentoPeça(Posição origem, Posição destino) {
		Peça p = tabuleiro.removePeça(origem);
		Peça peçaCapturada = tabuleiro.removePeça(destino);
		tabuleiro.colocarPeça(p, destino);
		
		if(peçaCapturada != null) {
			peçasNoTabuleiro.remove(peçaCapturada);
			peçasCapturadas.add(peçaCapturada);
		}
		
		return peçaCapturada;
		}
	
		private void desfazerMovimento(Posição Origem, Posição Destino, Peça peçaCapturada) {
			Peça p = tabuleiro.removePeça(Destino);
			tabuleiro.colocarPeça(p, Origem);
			
			if (peçaCapturada != null) {
				tabuleiro.colocarPeça(peçaCapturada, Destino);
				peçasCapturadas.remove(peçaCapturada);
				peçasNoTabuleiro.add(peçaCapturada);	
			}
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
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA; 
	}
	
	private PeçadeXadrez Rei(Cor cor) {
		List<Peça> list = peçasNoTabuleiro.stream().filter(x -> ((PeçadeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peça p : list) {
			if (p instanceof Rei) {
				return (PeçadeXadrez)p;
			}
		}
		throw new IllegalStateException("Nao existe o Rei da cor " + cor + "no tabuleiro");
	}
	
	private boolean testeCheck(Cor cor) {
		Posição ReiPosição = Rei(cor).getPosicaoXadrez().toPosição();
		List<Peça> peçasOponente = peçasNoTabuleiro.stream().filter(x -> ((PeçadeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peça p : peçasOponente) {
			boolean	[][] mat = p.movimentosPossiveis();
			if(mat[ReiPosição.getLinha()][ReiPosição.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private void  colocarNovaPeça(char coluna, int linha, PeçadeXadrez peça) {
		tabuleiro.colocarPeça(peça, new PosicaoXadrez(coluna, linha).toPosição());
		peçasNoTabuleiro.add(peça);
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
