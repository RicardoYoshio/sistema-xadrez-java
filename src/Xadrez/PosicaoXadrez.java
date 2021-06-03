package Xadrez;

import tabuleiro.Posição;

public class PosicaoXadrez {

	private char coluna;
	private int linha;
	public PosicaoXadrez(char coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new ExcessaoXadrez("Erro de Posição. Valores validos de a1 A h8.");
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
	
	protected Posição toPosição() {
		return new Posição(8 - linha, coluna - 'a');
	}
	
	protected static PosicaoXadrez dePosição(Posição posição) {
		return new PosicaoXadrez((char)('a' - posição.getColuna()), 8 - posição.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
		
	}
}
