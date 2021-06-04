package tabuleiro;

public abstract class Peça {
	
	protected Posição posição;
	private Tabuleiro tabuleiro;
	
	public Peça(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posição = null;
	}
	
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract boolean [][] movimentosPossiveis();
	
	public boolean movimentoPossiveis(Posição posição) {
		return movimentosPossiveis()[posição.getLinha()][posição.getColuna()];
	}
	
	public boolean exiteMovimentoPossivel() {
		boolean[][] mat = movimentosPossiveis();
		for (int i=0; i<mat.length; i++) {
			for (int j=0; j<mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	}


