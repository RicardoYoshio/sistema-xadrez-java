package tabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peça[][] peças;
	
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new ExcessaoTabuleiro("Erro de criação de tabuleiro: tem de ser maior que 1 linha e 1 coluna");
	}
		this.linhas = linhas;
		this.colunas = colunas;
		peças = new Peça[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public Peça peça(int linha, int coluna) {
		if (!existePosição(linha, coluna)) {
			throw new ExcessaoTabuleiro("Não existe posição de Tabuleiro");
			}
		return peças [linha][coluna];	
	}
	
	public Peça peça (Posição posição) {
		if (!existePosição(posição)) {
			throw new ExcessaoTabuleiro("Não existe posição de Tabuleiro");
		}
		return peças [posição.getLinha()][posição.getColuna()];
	}
	
	public void colocarPeça(Peça peça, Posição posição) {
		if (haUmaPeça(posição)) {
			throw new ExcessaoTabuleiro("Tem uma peça nesta posição" + posição);
		}
		peças[posição.getLinha()][posição.getColuna()] = peça;
		peça.posição = posição;		
	}
	
	private boolean existePosição (int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
		
	}
	public boolean existePosição(Posição posição) {
		return existePosição(posição.getLinha(), posição.getColuna());
	}
	
	public boolean haUmaPeça(Posição posição) {
		if (!existePosição(posição)) {
			throw new ExcessaoTabuleiro("Não existe posição de Tabuleiro");
		}
		return peça(posição) != null;
	}
}
