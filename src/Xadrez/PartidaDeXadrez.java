package Xadrez;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Xadrez.peças.Bispo;
import Xadrez.peças.Cavalo;
import Xadrez.peças.Peao;
import Xadrez.peças.Rainha;
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
	private boolean checkMate;
	private PeçadeXadrez vulneravelEnPassant;
	private PeçadeXadrez promocao;
	
	
	
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
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public PeçadeXadrez getvulneravelEnPassant() {
		return vulneravelEnPassant;
	}
	
	public PeçadeXadrez getPromocao() {
		return promocao;
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
		
		PeçadeXadrez peçaMovida = (PeçadeXadrez)tabuleiro.peça(destino);
		
		// #movimento especial promoção
		promocao = null;
		if(peçaMovida instanceof Peao) {
			if ((peçaMovida.getCor() == Cor.BRANCA && destino.getLinha() == 0) || (peçaMovida.getCor() == Cor.PRETA && destino.getLinha() == 7)) {
				promocao = (PeçadeXadrez)tabuleiro.peça(destino);
				promocao = pecaPromovida("Q");
				
			}
		}
		
		
		check = (testeCheck(oponente(jogadorAtual))) ? true : false;
		
		
		if (testeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}
		else {
		proximoTurno();
		}
		
		// #movimento especial en passant
		if(peçaMovida instanceof Peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2 )) {
			vulneravelEnPassant = peçaMovida;
	}
		else {
			vulneravelEnPassant = null;
	}
		return (PeçadeXadrez)peçaCapturada;
	}
	
	public PeçadeXadrez pecaPromovida(String tipo) {
		if (promocao == null) {
			throw new IllegalStateException("Não a peca para ser promovida");
		}
		if (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("Q")) {
			return promocao;
		}
		
		Posição pos = promocao.getPosicaoXadrez().toPosição();
		Peça p = tabuleiro.removePeça(pos);
		peçasNoTabuleiro.remove(p);
		
		PeçadeXadrez newPeça = newPeça(tipo, promocao.getCor());
		tabuleiro.colocarPeça(newPeça, pos);
		peçasNoTabuleiro.add(newPeça);
		
		return newPeça;
	}
	
	private PeçadeXadrez newPeça(String tipo, Cor cor) {
		if (tipo.equals("B")) return new Bispo (tabuleiro, cor);
		if (tipo.equals("C")) return new Bispo (tabuleiro, cor);
		if (tipo.equals("Q")) return new Bispo (tabuleiro, cor);
		return new Torre(tabuleiro, cor);
	}
	
	private Peça movimentoPeça(Posição origem, Posição destino) {
		PeçadeXadrez p = (PeçadeXadrez)tabuleiro.removePeça(origem);
		p.acrescentarMovimentoContagem();
		Peça peçaCapturada = tabuleiro.removePeça(destino);
		tabuleiro.colocarPeça(p, destino);
		
		if(peçaCapturada != null) {
			peçasNoTabuleiro.remove(peçaCapturada);
			peçasCapturadas.add(peçaCapturada);
		}
		
		// #movimento especial Roque torre do Rei
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posição origemT = new Posição(origem.getLinha(), origem.getColuna() + 3);
			Posição destinoT = new Posição(origem.getLinha(), origem.getColuna() + 1);
			PeçadeXadrez torre = (PeçadeXadrez)tabuleiro.removePeça(origemT);
			tabuleiro.colocarPeça(torre, destinoT);
			torre.acrescentarMovimentoContagem();
		}
		
		// #movimento especial Roque torre da Rainha
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posição origemT = new Posição(origem.getLinha(), origem.getColuna() -4);
			Posição destinoT = new Posição(origem.getLinha(), origem.getColuna() - 1);
			PeçadeXadrez torre = (PeçadeXadrez)tabuleiro.removePeça(origemT);
			tabuleiro.colocarPeça(torre, destinoT);
			torre.acrescentarMovimentoContagem();
		}
		
		// #Movimento Especial en passant
		if(p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && peçaCapturada == null) {
				Posição peaoPosição;
				if (p.getCor() == Cor.BRANCA) {
					peaoPosição = new Posição(destino.getLinha() + 1, destino.getColuna());
				}
				else {
					peaoPosição = new Posição(destino.getLinha() - 1, destino.getColuna());
				}
				peçaCapturada = tabuleiro.removePeça(peaoPosição);
				peçasCapturadas.add(peçaCapturada);
				peçasNoTabuleiro.remove(peçaCapturada);
			}
		}
		
		return peçaCapturada;
		}
	
		private void desfazerMovimento(Posição origem, Posição destino, Peça peçaCapturada) {
			PeçadeXadrez p = (PeçadeXadrez)tabuleiro.removePeça(destino);
			p.diminuirMovimentoContagem();
			tabuleiro.colocarPeça(p, origem);
			
			if (peçaCapturada != null) {
				tabuleiro.colocarPeça(peçaCapturada, destino);
				peçasCapturadas.remove(peçaCapturada);
				peçasNoTabuleiro.add(peçaCapturada);	
			}
			
			// #movimento especial Roque torre do Rei
			if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
				Posição origemT = new Posição(origem.getLinha(), origem.getColuna() + 3);
				Posição destinoT = new Posição(origem.getLinha(), origem.getColuna() + 1);
				PeçadeXadrez torre = (PeçadeXadrez)tabuleiro.removePeça(destinoT);
				tabuleiro.colocarPeça(torre, origemT);
				torre.diminuirMovimentoContagem();
			}
			
			// #movimento especial Roque torre da Rainha
			if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
				Posição destinoT = new Posição(origem.getLinha(), origem.getColuna() -4);
				Posição origemT = new Posição(origem.getLinha(), origem.getColuna() - 1);
				PeçadeXadrez torre = (PeçadeXadrez)tabuleiro.removePeça(destinoT);
				tabuleiro.colocarPeça(torre, origemT);
				torre.diminuirMovimentoContagem();
		}
			// #Movimento Especial en passant
			if(p instanceof Peao) {
				if (origem.getColuna() != destino.getColuna() && peçaCapturada == vulneravelEnPassant) {
					PeçadeXadrez peao = (PeçadeXadrez)tabuleiro.removePeça(destino);
					Posição peaoPosição;
					if (p.getCor() == Cor.BRANCA) {
						peaoPosição = new Posição(3, destino.getColuna());
					}
					else {
						peaoPosição = new Posição(4, destino.getColuna());
					}
					tabuleiro.colocarPeça(peao,  peaoPosição);
				}
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
	
	private boolean testeCheckMate(Cor cor) {
		if (!testeCheck(cor)) {
			return false;
		}
		List<Peça> list = peçasNoTabuleiro.stream().filter(x -> ((PeçadeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peça p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posição origem = ((PeçadeXadrez)p).getPosicaoXadrez().toPosição();
						Posição destino = new Posição (i, j);
						Peça peçaCapturada = movimentoPeça(origem, destino);
						boolean testeCheck = testeCheck(cor);
						desfazerMovimento(origem, destino, peçaCapturada);
						if (!testeCheck) {
							return false;
						}
						}		
					}
				}
			}
		return true;
	}
	
	private void  colocarNovaPeça(char coluna, int linha, PeçadeXadrez peça) {
		tabuleiro.colocarPeça(peça, new PosicaoXadrez(coluna, linha).toPosição());
		peçasNoTabuleiro.add(peça);
	}
	
	private void setupInicial() {
		colocarNovaPeça('a', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeça('h', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocarNovaPeça('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocarNovaPeça('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocarNovaPeça('e', 1, new Rei(tabuleiro, Cor.BRANCA, this));
		colocarNovaPeça('d', 1, new Rainha(tabuleiro, Cor.BRANCA));
		colocarNovaPeça('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocarNovaPeça('f', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocarNovaPeça('a', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		colocarNovaPeça('b', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		colocarNovaPeça('c', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		colocarNovaPeça('d', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		colocarNovaPeça('e', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		colocarNovaPeça('f', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		colocarNovaPeça('g', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		colocarNovaPeça('h', 2, new Peao(tabuleiro, Cor.BRANCA, this));
		
		colocarNovaPeça('a', 8, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPeça('h', 8, new Torre(tabuleiro, Cor.PRETA));
		colocarNovaPeça('b', 8, new Cavalo(tabuleiro, Cor.PRETA));
		colocarNovaPeça('g', 8, new Cavalo(tabuleiro, Cor.PRETA));
		colocarNovaPeça('e', 8, new Rei(tabuleiro, Cor.PRETA, this));
		colocarNovaPeça('d', 8, new Rainha(tabuleiro, Cor.PRETA));
		colocarNovaPeça('f', 8, new Bispo(tabuleiro, Cor.PRETA));
		colocarNovaPeça('c', 8, new Bispo(tabuleiro, Cor.PRETA));
		colocarNovaPeça('a', 7, new Peao(tabuleiro, Cor.PRETA, this));
		colocarNovaPeça('b', 7, new Peao(tabuleiro, Cor.PRETA, this));
		colocarNovaPeça('c', 7, new Peao(tabuleiro, Cor.PRETA, this));
		colocarNovaPeça('d', 7, new Peao(tabuleiro, Cor.PRETA, this));
		colocarNovaPeça('e', 7, new Peao(tabuleiro, Cor.PRETA, this));
		colocarNovaPeça('f', 7, new Peao(tabuleiro, Cor.PRETA, this));
		colocarNovaPeça('g', 7, new Peao(tabuleiro, Cor.PRETA, this));
		colocarNovaPeça('h', 7, new Peao(tabuleiro, Cor.PRETA, this));
	}
}
