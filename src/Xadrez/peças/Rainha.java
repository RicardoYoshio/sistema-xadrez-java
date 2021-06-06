package Xadrez.peças;

import Xadrez.Cor;
import Xadrez.PeçadeXadrez;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;

public class Rainha extends PeçadeXadrez{
	
	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
	
	@Override
	public String toString() {
		return "Q";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posição p = new Posição (0, 0);
		
		// above
		p.setValores(posição.getLinha() - 1, posição.getColuna());
		while (getTabuleiro().existePosição(p) && !getTabuleiro().haUmaPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().existePosição(p) &&  peçaDoOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		// left
		p.setValores(posição.getLinha(), posição.getColuna() - 1);
		while (getTabuleiro().existePosição(p) && !getTabuleiro().haUmaPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().existePosição(p) &&  peçaDoOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
				}
		
		// left
		p.setValores(posição.getLinha(), posição.getColuna() + 1);
		while (getTabuleiro().existePosição(p) && !getTabuleiro().haUmaPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().existePosição(p) &&  peçaDoOponente(p)) {
			mat [p.getLinha()][p.getColuna()] = true;
		}
		
		// below
		p.setValores(posição.getLinha() + 1, posição.getColuna());
		while (getTabuleiro().existePosição(p) && !getTabuleiro().haUmaPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
    	if (getTabuleiro().existePosição(p) &&  peçaDoOponente(p)) {
		    mat [p.getLinha()][p.getColuna()] = true;
		}
				
    	// NO
    	p.setValores(posição.getLinha() - 1, posição.getColuna() - 1);
    	while (getTabuleiro().existePosição(p) && !getTabuleiro().haUmaPeça(p)) {
    		mat[p.getLinha()][p.getColuna()] = true;
    		p.setValores(p.getLinha() - 1, p.getColuna() - 1);
    	}
    	if (getTabuleiro().existePosição(p) &&  peçaDoOponente(p)) {
    		mat [p.getLinha()][p.getColuna()] = true;
    	}
    			
    	// NE
    	p.setValores(posição.getLinha() - 1, posição.getColuna() + 1);
    	while (getTabuleiro().existePosição(p) && !getTabuleiro().haUmaPeça(p)) {
    		mat[p.getLinha()][p.getColuna()] = true;
    		p.setValores(p.getLinha() - 1, p.getColuna() + 1);
    	}
    		if (getTabuleiro().existePosição(p) &&  peçaDoOponente(p)) {
    			mat [p.getLinha()][p.getColuna()] = true;
    			}
    			
    	// SE
    	p.setValores(posição.getLinha() + 1, posição.getColuna() + 1);
    	while (getTabuleiro().existePosição(p) && !getTabuleiro().haUmaPeça(p)) {
    		mat[p.getLinha()][p.getColuna()] = true;
    		p.setValores(p.getLinha() + 1, p.getColuna() + 1);
    	}
    		if (getTabuleiro().existePosição(p) &&  peçaDoOponente(p)) {
    			mat [p.getLinha()][p.getColuna()] = true;
    		}
    			
    	// SO
    	p.setValores(posição.getLinha() + 1, posição.getColuna() - 1);
    		while (getTabuleiro().existePosição(p) && !getTabuleiro().haUmaPeça(p)) {
    		mat[p.getLinha()][p.getColuna()] = true;
    		p.setValores(p.getLinha() + 1, p.getColuna() - 1);
    	}
    	    if (getTabuleiro().existePosição(p) &&  peçaDoOponente(p)) {
    		    mat [p.getLinha()][p.getColuna()] = true;
    		}		
    	
		return mat;
	}
}


