package JogoDaVelha;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		boolean fimJogada = false;
		Player j1 = new Player();
		Player j2 = new Player();
		
		for (int i=0; i<9; i++) {
			if (i%2 == 0) {
				fimJogada=false;
				while(!fimJogada){
					System.out.println("Entre com sua jogada: ");
					Scanner sc = new Scanner(System.in);
					fimJogada = j1.Human(sc.nextInt());
				}
			} else {
				fimJogada = false;
				while(!fimJogada)
					fimJogada = j2.Computer();
			}
		System.out.println(Player.vetJogada.get(Player.vetJogada.size()-1));
		}
	}

}
