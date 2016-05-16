package JogoDaVelha;

import java.util.ArrayList;
import java.util.Random;

public class Player {
	
	protected static ArrayList<Integer> vetJogada = new ArrayList<>();

	public boolean Human(int j) {
		return jogada(j);
	}


	public boolean Computer() {
		Random jAleatoria = new Random();
		return jogada(jAleatoria.nextInt(10));
	}
	
	private boolean jogada(int j){
		
		if (j < 0 || j > 8) { 
			return false;
			
		} else if (vetJogada.contains(j)){
			return false;
			
		} else {
			vetJogada.add(j);
			return true;
		}
	}
	
	private static String[][] carregaMatriz(){
		
		String simbolo;
		String matriz[][] = new String[3][3];
		
		for (int i=0; i<=vetJogada.size()-1; i++) {
			
			if (i%2 == 0){
				simbolo = "X";
			} else {
				simbolo = "O";
			}
			
			if (vetJogada.get(i) == 0) matriz[0][0] = simbolo;
			else if (vetJogada.get(i) == 1) matriz[0][1] = simbolo;
			else if (vetJogada.get(i) == 2) matriz[0][2] = simbolo;
			else if (vetJogada.get(i) == 3) matriz[1][0] = simbolo;
			else if (vetJogada.get(i) == 4) matriz[1][1] = simbolo;
			else if (vetJogada.get(i) == 5) matriz[1][2] = simbolo;
			else if (vetJogada.get(i) == 6) matriz[2][0] = simbolo;
			else if (vetJogada.get(i) == 7) matriz[2][1] = simbolo;
			else if (vetJogada.get(i) == 8) matriz[2][2] = simbolo;
			else ;
			
		}
		
		return matriz;
	}
	
	protected static void imprimeJogada(){
		
		String matriz[][] = new String[3][3];
		matriz = carregaMatriz();
		
		for (int m=0; m<=2; m++) {
			for (int n=0; n<=2; n++) {
				if (matriz[m][n] == "X" || matriz[m][n] == "O"){
					System.out.print("[" + matriz[m][n] + "] ");
				} else {
					System.out.print("[ ]" + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
		
	}
	
	protected static boolean verificaSeGanhou(){
		
		String matriz[][] = new String[3][3];
		matriz = carregaMatriz();
		
		// Verifica linhas
		for (int linha=0; linha<=2; linha++) {
			if (matriz[linha][0] == "X" && 
				matriz[linha][1] == "X" && 
				matriz[linha][2] == "X"){
				return true;
			}
			if (matriz[linha][0] == "O" && 
				matriz[linha][1] == "O" && 
				matriz[linha][2] == "O"){
				return true;
			}
		}
		
		// Verifica colunas
		for (int coluna=0; coluna<=2; coluna++) {
			if (matriz[0][coluna] == "X" && 
				matriz[1][coluna] == "X" && 
				matriz[2][coluna] == "X"){
				return true;
			}
			if (matriz[0][coluna] == "O" && 
				matriz[1][coluna] == "O" && 
				matriz[2][coluna] == "O"){
				return true;
			}
		}
		
		// Verifica diagonais
		if (matriz[0][0] == "X" && 
			matriz[1][1] == "X" && 
			matriz[2][2] == "X"){
			return true;
		}
		if (matriz[0][0] == "O" && 
			matriz[1][1] == "O" && 
			matriz[2][2] == "O"){
			return true;
		}
		if (matriz[2][0] == "X" && 
			matriz[1][1] == "X" && 
			matriz[0][2] == "X"){
			return true;
		}
		if (matriz[2][0] == "O" && 
			matriz[1][1] == "O" && 
			matriz[0][2] == "O"){
			return true;
		}
		
		return false;
	}

}