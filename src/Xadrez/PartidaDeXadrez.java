package Xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Xadrez.pe�as.Peao;
import Xadrez.pe�as.Rei;
import Xadrez.pe�as.Torre;
import tabuleiro.Pe�a;
import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;

public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	
	private List<Pe�a> pe�asNoTabuleiro = new ArrayList<>();
	private List<Pe�a> pe�asCapturadas = new ArrayList<>();
	
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
	
	public boolean getCheckMate() {
		return checkMate;
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
		
		if (testeCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, pe�aCapturada);
			throw new ExcessaoXadrez ("Voce n�o pode se por em Check");	
		}
		
		check = (testeCheck(oponente(jogadorAtual))) ? true : false;
		
		
		if (testeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}
		else {
		proximoTurno();
		
		}
		
		return (Pe�adeXadrez)pe�aCapturada;
	}
	
	private Pe�a movimentoPe�a(Posi��o origem, Posi��o destino) {
		Pe�adeXadrez p = (Pe�adeXadrez)tabuleiro.removePe�a(origem);
		p.acrescentarMovimentoContagem();
		Pe�a pe�aCapturada = tabuleiro.removePe�a(destino);
		tabuleiro.colocarPe�a(p, destino);
		
		if(pe�aCapturada != null) {
			pe�asNoTabuleiro.remove(pe�aCapturada);
			pe�asCapturadas.add(pe�aCapturada);
		}
		
		return pe�aCapturada;
		}
	
		private void desfazerMovimento(Posi��o Origem, Posi��o Destino, Pe�a pe�aCapturada) {
			Pe�adeXadrez p = (Pe�adeXadrez)tabuleiro.removePe�a(Destino);
			p.diminuirMovimentoContagem();
			tabuleiro.colocarPe�a(p, Origem);
			
			if (pe�aCapturada != null) {
				tabuleiro.colocarPe�a(pe�aCapturada, Destino);
				pe�asCapturadas.remove(pe�aCapturada);
				pe�asNoTabuleiro.add(pe�aCapturada);	
			}
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
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA; 
	}
	
	private Pe�adeXadrez Rei(Cor cor) {
		List<Pe�a> list = pe�asNoTabuleiro.stream().filter(x -> ((Pe�adeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Pe�a p : list) {
			if (p instanceof Rei) {
				return (Pe�adeXadrez)p;
			}
		}
		throw new IllegalStateException("Nao existe o Rei da cor " + cor + "no tabuleiro");
	}
	
	private boolean testeCheck(Cor cor) {
		Posi��o ReiPosi��o = Rei(cor).getPosicaoXadrez().toPosi��o();
		List<Pe�a> pe�asOponente = pe�asNoTabuleiro.stream().filter(x -> ((Pe�adeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Pe�a p : pe�asOponente) {
			boolean	[][] mat = p.movimentosPossiveis();
			if(mat[ReiPosi��o.getLinha()][ReiPosi��o.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testeCheckMate(Cor cor) {
		if (!testeCheck(cor)) {
			return false;
		}
		List<Pe�a> list = pe�asNoTabuleiro.stream().filter(x -> ((Pe�adeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Pe�a p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posi��o origem = ((Pe�adeXadrez)p).getPosicaoXadrez().toPosi��o();
						Posi��o destino = new Posi��o (i, j);
						Pe�a pe�aCapturada = movimentoPe�a(origem, destino);
						boolean testeCheck = testeCheck(cor);
						desfazerMovimento(origem, destino, pe�aCapturada);
						if (!testeCheck) {
							return false;
						}
						}		
					}
				}
			}
		return true;
	}
	
	private void  colocarNovaPe�a(char coluna, int linha, Pe�adeXadrez pe�a) {
		tabuleiro.colocarPe�a(pe�a, new PosicaoXadrez(coluna, linha).toPosi��o());
		pe�asNoTabuleiro.add(pe�a);
	}
	
	private void setupInicial() {
		colocarNovaPe�a('a', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('h', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('e', 1, new Rei(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('a', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('b', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('c', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('d', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('e', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('f', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('g', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocarNovaPe�a('h', 2, new Peao(tabuleiro, Cor.BRANCA));
		
		colocarNovaPe�a('a', 8, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('h', 8, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('e', 8, new Rei(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('a', 7, new Peao(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('b', 7, new Peao(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('c', 7, new Peao(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('d', 7, new Peao(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('e', 7, new Peao(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('f', 7, new Peao(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('g', 7, new Peao(tabuleiro, Cor.PRETA));
		colocarNovaPe�a('h', 7, new Peao(tabuleiro, Cor.PRETA));
	}
}
