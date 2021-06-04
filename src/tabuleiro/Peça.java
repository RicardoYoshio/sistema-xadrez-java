package tabuleiro;

public abstract class Pe�a {
	
	protected Posi��o posi��o;
	private Tabuleiro tabuleiro;
	
	public Pe�a(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posi��o = null;
	}
	
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract boolean [][] movimentosPossiveis();
	
	public boolean movimentoPossiveis(Posi��o posi��o) {
		return movimentosPossiveis()[posi��o.getLinha()][posi��o.getColuna()];
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


