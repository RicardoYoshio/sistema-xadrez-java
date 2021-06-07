package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Xadrez.ExcessaoXadrez;
import Xadrez.PartidaDeXadrez;
import Xadrez.PeçadeXadrez;
import Xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PeçadeXadrez> capturada = new ArrayList<>();
		
		while(!partidaDeXadrez.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printPartida(partidaDeXadrez, capturada);
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
				
				if(peçaCapturada !=null) {
					capturada.add(peçaCapturada);
				}
				
				if(partidaDeXadrez.getPromocao() != null) {
					System.out.print("Entre com a peca promovida (B/C/T/Q: ");
						String tipo = sc.nextLine();
						partidaDeXadrez.pecaPromovida(tipo);
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
		UI.clearScreen();
		UI.printPartida(partidaDeXadrez, capturada);
	}
}
