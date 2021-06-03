package Xadrez;

import tabuleiro.Posi��o;

public class PosicaoXadrez {

	private char coluna;
	private int linha;
	public PosicaoXadrez(char coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new ExcessaoXadrez("Erro de Posi��o. Valores validos de a1 A h8.");
		}
		
		this.coluna = coluna;
		this.linha = linha;
	
	}
	public char getColuna() {
		return coluna;
	
	}
	public int getLinha() {
		return linha;
	}
	
	protected Posi��o toPosi��o() {
		return new Posi��o(8 - linha, coluna - 'a');
	}
	
	protected static PosicaoXadrez dePosi��o(Posi��o posi��o) {
		return new PosicaoXadrez((char)('a' - posi��o.getColuna()), 8 - posi��o.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
		
	}
}
