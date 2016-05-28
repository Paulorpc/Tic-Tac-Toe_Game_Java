package JogoDaVelha;

import java.util.Scanner;
import org.omg.CORBA.VersionSpecHelper;


public class Main2 {

	public static void main(String[] args) {
		
		
		// Declara��o de vars
		int numPartidas = 0;
		int menuItem = 0;
		boolean continuarJogando = true;
		boolean continuarMenu = true;
		Scanner sc = new Scanner(System.in);
		Player2 j1;
		Player2 j2;		
		String player1 = null;
		String player2 = null;
		
		
		// Sele��o do jogo	
		System.out.println("1. Homem   X Maquina\n"
						 + "2. Maquina X Homem\n"
						 + "3. Maquina X Maquina\n"
						 + "9. Sair");
	
		
		while(continuarMenu) {
						
			System.out.println("Escolha uma op��o de jogo: ");
			menuItem = sc.nextInt();
			
			if 		(menuItem == 1){
				player1 = "homem";
				player2 = "maquina";
				continuarMenu = false;
			}		
			else if (menuItem == 2) {
				player1 = "maquina";
				player2 = "homem";
				continuarMenu = false;				
			}
			else if (menuItem == 3) {
				player1 = "maquina";
				player2 = "maquina";
				continuarMenu = false;
			}
			else if (menuItem == 9) {
				System.exit(0);
			} 
			
		}
		
		j1 = new Player2(1, player1);
		j2 = new Player2(2, player2);

		while (continuarJogando) {			
			
			iniciaPartida(j1, j2);
			numPartidas++;
			
			// Placar
			System.out.println("N�mero de Partidas: " +numPartidas);
			System.out.println(j1.player + " [" + j1.vitorias +"] X "
							 + j2.player + " [" + j2.vitorias + "]");
			
			// Verifica se continuar� a jogar
			System.out.println("\n\n1. Continuar Jogando\n"
							 + "2. Sair");
			
			menuItem = sc.nextInt(); 
			
			if   (menuItem == 1) {
				continuarJogando = true;
				
				j1.resetarPartida();
				j2.resetarPartida();
				
			}	
			else if (menuItem == 2)
				continuarJogando = false;
			
			
		}			
		sc.close();
			
	}



	public static void iniciaPartida(Player2 j1, Player2 j2){
		
		boolean fimJogada = false;
		Scanner sc = new Scanner(System.in);
		boolean IA = true;
		
		System.out.println("Jogador 1: " + j1.player + "\n"  
				   		 + "Jogador 2: " + j2.player + "\n");
	
		for (int i=0; i<9; i++) {
		
			// indice par, logo Player1 joga.
			if (i%2 == 0) {
				
				fimJogada=false;
				
				// Aguarda jogada
				while(!fimJogada){
					
					if (j1.player.equals("homem")) {
						System.out.println("Entre com sua jogada: ");
						fimJogada = j1.Human(sc.nextInt()-1);
					} else {
						fimJogada = j2.Computer(IA);
					}
	
				}
				
				System.out.println();
				
				//System.out.println("Jogada do jogador 1: " + Player.vetJogada.get(Player.vetJogada.size()-1));
				Player.imprimeJogada();
				
				if (Player.verificaSeGanhou()) {
					System.out.println("Jogador 1 ganhou!");
					j1.vitorias++;
					break;
				}
			
			// indice impart, logo Player2 joga.
			} else {
				
				fimJogada = false;
				
				// Aguarda jogada
				while(!fimJogada){
					
					if (j2.player.equals("homem")) {
						System.out.println("Entre com sua jogada: ");
						fimJogada = j1.Human(sc.nextInt()-1);
					} else {
						fimJogada = j2.Computer(IA);
					}
					
				}
				
				//System.out.println("Jogada do Jogador 2: " + Player.vetJogada.get(Player.vetJogada.size()-1));
				Player.imprimeJogada();
				
				if (Player.verificaSeGanhou()) {
					System.out.println("Jogador 2 ganhou!");
					j2.vitorias++;
					break;
				}
				
			}
			
			if (i == 8 && !Player.verificaSeGanhou()) {
				System.out.println("Empate");
			}
	
		}
	}

}