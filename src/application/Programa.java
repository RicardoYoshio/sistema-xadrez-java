package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Xadrez.ExcessaoXadrez;
import Xadrez.PartidaDeXadrez;
import Xadrez.Pe�adeXadrez;
import Xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<Pe�adeXadrez> capturada = new ArrayList<>();
		
		while(true) {
			try {
				UI.clearScreen();
				UI.printPartida(partidaDeXadrez, capturada);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.readPosicaoXadrez(sc);
				
				boolean[][] movimentosPossiveis = partidaDeXadrez.movimentosPossiveis(origem);
				UI.clearScreen();
				UI.printTabuleiro(partidaDeXadrez.getPe�as(), movimentosPossiveis);
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.readPosicaoXadrez(sc);
				
				Pe�adeXadrez pe�aCapturada = partidaDeXadrez.movimentoXadrez(origem, destino);
				
				if(pe�aCapturada !=null) {
					capturada.add(pe�aCapturada);
				}
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
