package JogoDaVelha;

import java.util.Scanner;

import org.omg.CORBA.VersionSpecHelper;

public class Main_original {

	public static void main(String[] args) {
		
		boolean fimJogada = false;
		Scanner sc = new Scanner(System.in);
		Player j1 = new Player(1, "Homem");
		Player j2 = new Player(2, "Maquina");
		boolean IA = true;
		
		System.out.println("Jogador 1: Você \n" +
						   "Jogador 2: Máquina \n");
		
		for (int i=0; i<9; i++) {
			
			if (i%2 == 0) {
				
				fimJogada=false;
				
				while(!fimJogada){
					System.out.println("Entre com sua jogada: ");
					fimJogada = j1.Human(sc.nextInt()-1);
				}
				
				System.out.println();
				
				//System.out.println("Jogada do jogador 1: " + Player.vetJogada.get(Player.vetJogada.size()-1));
				Player.imprimeJogada();
				
				if (Player.verificaSeGanhou()) {
					System.out.println("Jogador 1 ganhou!");
					break;
				}
				
			} else {
				
				fimJogada = false;
				
				while(!fimJogada){
					fimJogada = j2.Computer(IA);
				}
				
				//System.out.println("Jogada do Jogador 2: " + Player.vetJogada.get(Player.vetJogada.size()-1));
				Player.imprimeJogada();
				
				if (Player.verificaSeGanhou()) {
					System.out.println("Jogador 2 ganhou!");
					break;
				}
				
			}
		if (i == 8 && !Player.verificaSeGanhou()) {
			System.out.println("Empate");
		}
		//System.out.println(Player.vetJogada.get(Player.vetJogada.size()-1));
		}
		
		/*for (int i=0; i<9; i++) {
			
			if(i==2 || i==5 || i==8){
				System.out.print(Player.vetJogada.get(i) + "\n");
			} else{
				System.out.print(Player.vetJogada.get(i) + " ");
			}
		}*/
		
		//Player.imprimeJogada();
		
		sc.close();
	}

}