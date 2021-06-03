package application;

import java.util.Scanner;

import Xadrez.PartidaDeXadrez;
import Xadrez.PeçadeXadrez;
import Xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while(true) {
			UI.printTabuleiro(partidaDeXadrez.getPeças());
			System.out.println();
			System.out.print("Origem: ");
			PosicaoXadrez origem = UI.readPosicaoXadrez(sc);
			
			System.out.println();
			System.out.print("Destino: ");
			PosicaoXadrez destino = UI.readPosicaoXadrez(sc);
			
			PeçadeXadrez peçaCapturada = partidaDeXadrez.movimentoXadrez(origem, destino);
	}
	}
}
