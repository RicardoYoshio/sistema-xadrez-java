package tabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Pe�a[][] pe�as;
	
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new ExcessaoTabuleiro("Erro de cria��o de tabuleiro: tem de ser maior que 1 linha e 1 coluna");
	}
		this.linhas = linhas;
		this.colunas = colunas;
		pe�as = new Pe�a[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public Pe�a pe�a(int linha, int coluna) {
		if (!existePosi��o(linha, coluna)) {
			throw new ExcessaoTabuleiro("N�o existe posi��o de Tabuleiro");
			}
		return pe�as [linha][coluna];	
	}
	
	public Pe�a pe�a (Posi��o posi��o) {
		if (!existePosi��o(posi��o)) {
			throw new ExcessaoTabuleiro("N�o existe posi��o de Tabuleiro");
		}
		return pe�as [posi��o.getLinha()][posi��o.getColuna()];
	}
	
	public void colocarPe�a(Pe�a pe�a, Posi��o posi��o) {
		if (haUmaPe�a(posi��o)) {
			throw new ExcessaoTabuleiro("Tem uma pe�a nesta posi��o" + posi��o);
		}
		pe�as[posi��o.getLinha()][posi��o.getColuna()] = pe�a;
		pe�a.posi��o = posi��o;		
	}
	
	private boolean existePosi��o (int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
		
	}
	public boolean existePosi��o(Posi��o posi��o) {
		return existePosi��o(posi��o.getLinha(), posi��o.getColuna());
	}
	
	public boolean haUmaPe�a(Posi��o posi��o) {
		if (!existePosi��o(posi��o)) {
			throw new ExcessaoTabuleiro("N�o existe posi��o de Tabuleiro");
		}
		return pe�a(posi��o) != null;
	}
}
