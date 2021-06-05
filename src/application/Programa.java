package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import Xadrez.ExcessaoXadrez;
import Xadrez.PartidaDeXadrez;
import Xadrez.PeçadeXadrez;
import Xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while(true) {
			try {
				UI.clearScreen();
				UI.printTabuleiro(partidaDeXadrez.getPeças());
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.readPosicaoXadrez(sc);
				
				boolean[][] movimentosPossiveis = partidaDeXadrez.movimentosPossiveis(origem);
				UI.clearScreen();
				UI.printTabuleiro(partidaDeXadrez.getPeças(), movimentosPossiveis);
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.readPosicaoXadrez(sc);
				
				
				PeçadeXadrez peçaCapturada = partidaDeXadrez.movimentoXadrez(origem, destino);
			}
			catch (ExcessaoXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();	
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
	}
	}
}
