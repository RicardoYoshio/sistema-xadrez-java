package Xadrez;

import tabuleiro.Pe�a;
import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;

public abstract class Pe�adeXadrez extends Pe�a{
	
	private Cor cor;
	private int contadorMovimento;
	

	public Pe�adeXadrez(Tabuleiro tabuleiro, Cor cor) {
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
		return PosicaoXadrez.dePosi��o(posi��o);
	}
	
	protected boolean pe�aDoOponente(Posi��o posi��o) {
		Pe�adeXadrez p = (Pe�adeXadrez) getTabuleiro().pe�a(posi��o);
		return p != null && p.getCor() != cor;
	}
}
