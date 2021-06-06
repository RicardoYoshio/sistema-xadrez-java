package Xadrez;

import tabuleiro.Peça;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;

public abstract class PeçadeXadrez extends Peça{
	
	private Cor cor;
	private int contadorMovimento;
	

	public PeçadeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

	public int getContadorMovimento() {
		return contadorMovimento;
	}
	
	public void acrescentarMovimentoContagem() {
		contadorMovimento++;
	}
	
	public void diminuirMovimentoContagem() {
		contadorMovimento--;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.dePosição(posição);
	}
	
	protected boolean peçaDoOponente(Posição posição) {
		PeçadeXadrez p = (PeçadeXadrez) getTabuleiro().peça(posição);
		return p != null && p.getCor() != cor;
	}
}
