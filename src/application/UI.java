package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Xadrez.Cor;
import Xadrez.PartidaDeXadrez;
import Xadrez.Pe�adeXadrez;
import Xadrez.PosicaoXadrez;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static PosicaoXadrez readPosicaoXadrez(Scanner sc) {
		try {
		String s = sc.nextLine();
		char coluna = s.charAt(0);
		int linha = Integer.parseInt(s.substring(1));
		return new PosicaoXadrez(coluna, linha);
	}
		catch (RuntimeException e) {
			throw new InputMismatchException("Error Lendo posi��o do Xadrez, valores validos de a1 a h8.");
		}
	}
	
	public static void printPartida(PartidaDeXadrez partidaDeXadrez, List<Pe�adeXadrez> capturada) {
		printTabuleiro(partidaDeXadrez.getPe�as());
		System.out.println();
		printPe�asCapturadas(capturada);
		System.out.println("Turn : " + partidaDeXadrez.getTurno());
		System.out.println("Esperando o jogador: " + partidaDeXadrez.getjogadorAtual());
		if (partidaDeXadrez.getCheck()) {
			System.out.println("Check!");	
		}
	}
	
	public static void printTabuleiro(Pe�adeXadrez[][] pe�as) {
		for (int i = 0; i < pe�as.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pe�as.length; j++) {
				printPe�a(pe�as[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printTabuleiro(Pe�adeXadrez[][] pe�as, boolean [][] movimentosPossiveis) {
		for (int i = 0; i < pe�as.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pe�as.length; j++) {
				printPe�a(pe�as[i][j], movimentosPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPe�a(Pe�adeXadrez pe�a, boolean background) {
    	if (background) {
    		System.out.print(ANSI_BLUE_BACKGROUND);
    	
    	}
		if (pe�a == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (pe�a.getCor() == Cor.BRANCA) {
                System.out.print(ANSI_WHITE + pe�a + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + pe�a + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	private static void printPe�asCapturadas(List<Pe�adeXadrez> capturada) {
		List<Pe�adeXadrez> Branca = capturada.stream().filter(x -> x.getCor() == Cor.BRANCA).collect(Collectors.toList());
		List<Pe�adeXadrez> Preta = capturada.stream().filter(x -> x.getCor() == Cor.PRETA).collect(Collectors.toList());
		System.out.println("Pe�asCapturadas");
		System.out.print("BRANCAS ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(Branca.toArray()));
		System.out.print(ANSI_RESET);
		System.out.println("Pe�asCapturadas");
		System.out.print("PRETAS ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(Preta.toArray()));
		System.out.print(ANSI_RESET);
	}
}
