package JogoDaVelha;

import java.util.ArrayList;
import java.util.Random;

public class Player {
	
	protected static ArrayList<Integer> vetPosicoesJogadas = new ArrayList<>();
	private static ArrayList<Integer> vetPosicoesLivres = new ArrayList<>();
	private String simbolo[] = {"X", "O"};
	protected String player; 
	protected int numPlayer;
	protected int vitorias;
	
	
	public Player(int numPlayer, String player) {
		for(int i=0; i<=8; i++)
			if (!vetPosicoesLivres.contains(i)) vetPosicoesLivres.add(i);
			
		this.numPlayer = numPlayer;
		this.player = player;
		this.vitorias = 0;
	}

	public boolean Human(int j) {
		return jogada(j);
	}


	public boolean Computer(boolean IA) {
		if (IA)
			return jogada(melhorJogadaMaquina());			
		else 
			return jogada(jogaAleatoriamente());
	}
	
	private boolean jogada(int j){
		
		if (j < 0 || j > 8) { 
			return false;
			
		} else if (vetPosicoesJogadas.contains(j)){
			return false;
			
		} else {
			vetPosicoesJogadas.add(j);
			vetPosicoesLivres.remove(vetPosicoesLivres.indexOf(j));
			return true;
		}
	}
	
	
	private int melhorJogadaMaquina(){
		
		ArrayList<Integer> pos = new ArrayList<>();
		ArrayList<Integer> pos2 = new ArrayList<>();
		ArrayList<Integer> posCantos = new ArrayList<>();
		ArrayList<Integer> posCruz = new ArrayList<>();
		ArrayList<Integer> posDiagonal = new ArrayList<>();
		ArrayList<Integer> posCentro = new ArrayList<>();
		
		posCantos.add(0); 	posCantos.add(2);	posCantos.add(6);	posCantos.add(8);
		posCruz.add(1); 	posCruz.add(3);		posCruz.add(5); 	posCruz.add(7);  
		posDiagonal.add(0); posDiagonal.add(8); posDiagonal.add(2); posDiagonal.add(6);
		posCentro.add(4);
			
		// rodada 2 
		if (vetPosicoesJogadas.size() == 1) {
		
			// Se jogador player 1 jogar em dos cantos, maquina joga no centro (5)
			pos  = verificaPosicoesJogada(1, "cantos");
			pos2 = verificaPosicoesJogada(1, "cruz");
			
			
			if ( !pos.isEmpty() || !pos2.isEmpty() ) {
				
				return 4;				
				//if ( vetPosicoesJogadas.get(0).equals(pos.get(0)) ) return 4;
				
			} else 				
				return jogaAleatoriamentePosicoesPredefinidas(posCantos);
			
		}		
		
		//Rodada 4 - parte1
		// desta rodada para frente sempre verificar se há possibilidade de vitória
		if (vetPosicoesJogadas.size() >= 3) { 
			ArrayList<Integer> vitMaquina = new ArrayList<>();
			ArrayList<Integer> vitHumano = new ArrayList<>();
			vitMaquina  = verficaPossibilidadeVitoria("maquina");
			vitHumano   = verficaPossibilidadeVitoria("homem");
			
			// Se existe possibilidade de vitoria do jogador 1, então joga defendendo
			if (!vitMaquina.isEmpty())
				return convertePosM2V(vitMaquina);
			// Se homem  não podeia ganhar e maquina pode, então ganhe.
			else if (!vitHumano.isEmpty())
				return convertePosM2V(vitHumano);
		}
		
		// Se chegou aqui, então nenhum dos jogadores poderia ganhar
		//rodada 4 ainda. 
		if (vetPosicoesJogadas.size() == 3) {
			pos.clear();	
			
			pos  = verificaPosicoesJogada(1, "cantos");
			pos2 = verificaPosicoesJogada(1, "cruz");
			
			// Verifica se existe mais de um valor retornado no vetor pos para existir uma diagonal
			if ( pos.size() > 1 && ( 
					
					// e também se foi formada a diagonal
					(pos.get(0).equals(posDiagonal.get(0)) && pos.get(1).equals(posDiagonal.get(1))) ||
					(pos.get(0).equals(posDiagonal.get(2)) && pos.get(1).equals(posDiagonal.get(3))) ) )
				
			{
				return jogaAleatoriamentePosicoesPredefinidas(posCruz);
			
				// se jogador1 jogou em cruz (pos2>0)   
			} else if( pos2.size() > 0 && pos.size() > 0 && (
					   
					   // e jogar em cruz e num dos cantos de linha oposta
					   (pos2.get(0).equals(1) && ( pos.get(0).equals(6) || pos.get(0).equals(8) )) ||
					   (pos2.get(0).equals(3) && ( pos.get(0).equals(2) || pos.get(0).equals(8) )) ||
					   (pos2.get(0).equals(5) && ( pos.get(0).equals(0) || pos.get(0).equals(6) )) ||
					   (pos2.get(0).equals(7) && ( pos.get(0).equals(0) || pos.get(0).equals(2) ))) ) {
				
				if (vetPosicoesJogadas.contains(4)) 
					return jogaAleatoriamentePosicoesPredefinidas(posCantos);
				else
					return 4;
				
			} else
				return jogaAleatoriamentePosicoesPredefinidas(posCantos);				
									
		}
		
		//if ()
	
		else 
			return jogaAleatoriamentePosicoesPredefinidas(vetPosicoesLivres);

	}
	
	
	/**
	 * Metodo verifica quais as posições que foram usadas no tabuleiro por determinado jogador
	 * @param player O jogador que deseja vericar as posições que ele jogou
	 * @param posicao O tipo de posição que deseja verificar (cantos, cruz ou centro)
	 * @return Retorna um Array de Inteiros que indica as pocições em que o player jogou no tabuleiro
	 */
	private ArrayList<Integer> verificaPosicoesJogada(int numPlayer, String posicao){
			
		Integer i = null;
		ArrayList<Integer> pos = new ArrayList<>();
		ArrayList<Integer> posicoes = new ArrayList<>();
		
		if 		(posicao == "cantos") { pos.add(0); pos.add(2);	pos.add(6);	pos.add(8); }
		else if	(posicao == "cruz")   {	pos.add(1); pos.add(3);	pos.add(5);	pos.add(7);	}
		else if	(posicao == "centro") {	pos.add(4); }
		
		if 		(numPlayer == 0)	i=0;
		else if (numPlayer == 1)	i=1;
		
		while (i<vetPosicoesJogadas.size()) {
			
			//compara a posição i do vetorJogada com os valores do vet pos para saber se jogou nos cantos e quais
			for(int j=0; j<pos.size(); j++)
				if (vetPosicoesJogadas.get(i) == pos.get(j))
					posicoes.add(pos.get(j));
			
			i=i+2;	
		}
		
	return posicoes;
	}	
	
	/*
	private ArrayList<Integer> jogada3Cantos(String player){					
	return cantoOpostos;
	
	private ArrayList<Integer> jogadaL(String player){					
	return cantoOpostos;
	
	/*
	private ArrayList<Integer> verificaPosicoesJogadaCantosOpostos(String player){
			
		ArrayList<Integer> pos = new ArrayList<>();
		ArrayList<Integer> cantoOpostos = new ArrayList<>();
		
		pos = verificaPosicoesJogada(player, "cantos");
		
		for (int i=0; i<pos.size(); i++)
			cantoOpostos.add(8-pos.get(i));
					
	return cantoOpostos;
	}
	*/
	
	private String verificaSimbolo(String player){
		String simb = null;
		
		if 		(player == "homem") 	simb = simbolo[0];
		else if (player == "maquina") 	simb = simbolo[1];
		
		return simb;
	}
	
	private int jogaAleatoriamente(){
		
		Random jAleatoria = new Random();
		boolean posInvalida = true;
		Integer pos = null;
		
		// Gera uma posicao que ainda não foi jogada
		while (posInvalida) {
			pos = jAleatoria.nextInt(10);
			if (!vetPosicoesJogadas.contains(pos))
				posInvalida = false;
			}
	
		return pos;		
	}
	
	
	private int jogaAleatoriamentePosicoesPredefinidas(ArrayList<Integer> arrPos){
		Random jAleatoria = new Random();		
		Integer pos = null;
		boolean posInvalida = true;
		int i;
		
		// Gera uma posicao que ainda não foi jogada dentro das opções do vetor do parametro 
		while (posInvalida) {
			i = jAleatoria.nextInt(arrPos.size());
			pos = arrPos.get(i);
			if (!vetPosicoesJogadas.contains(pos))
				posInvalida = false;
			}
	
		return pos;	
	}
	
	
	
	// verifica se há duplas de um jogador que possibilite a vitória e retorna 
	private ArrayList<Integer> verficaPossibilidadeVitoria(String player) {
		String matriz[][] = new String[3][3];
		String simb = null;
		matriz = carregaMatriz();
		
		simb = verificaSimbolo(player);
		
		//Se não houver possibilidade de vitória retornará null
		ArrayList<Integer> vet = new ArrayList<>();
		
		// Verificar linhas
		for (int linha=0; linha<=2; linha++){
			if 		(matriz[linha][0] == simb && matriz[linha][1] == simb && matriz[linha][2] == null) { vet.add(linha); vet.add(2); }
			else if (matriz[linha][0] == simb && matriz[linha][2] == simb && matriz[linha][1] == null) { vet.add(linha); vet.add(1); }
			else if (matriz[linha][1] == simb && matriz[linha][2] == simb && matriz[linha][0] == null) { vet.add(linha); vet.add(0); }
		}
		
		// Verifica Colunas
		for (int coluna=0; coluna<=2; coluna++){ 
			if 		(matriz[0][coluna] == simb && matriz[1][coluna] == simb && matriz[2][coluna] == null) { vet.add(2); vet.add(coluna); }
			else if (matriz[0][coluna] == simb && matriz[2][coluna] == simb && matriz[1][coluna] == null) { vet.add(1); vet.add(coluna); }
			else if (matriz[1][coluna] == simb && matriz[2][coluna] == simb && matriz[0][coluna] == null) { vet.add(0); vet.add(coluna); }
		}
		
		// verifica diagonal
		if 		(matriz[1][1] == simb && matriz[0][0] == simb && matriz[2][2] == null) { vet.add(2); vet.add(2); }
		else if (matriz[1][1] == simb && matriz[2][2] == simb && matriz[0][0] == null) { vet.add(0); vet.add(0); }
		else if (matriz[1][1] == simb && matriz[0][2] == simb && matriz[2][0] == null) { vet.add(2); vet.add(0); }
		else if (matriz[1][1] == simb && matriz[2][0] == simb && matriz[0][2] == null) { vet.add(0); vet.add(2); }
		
		return vet;
	}
	
	// Converte posição do tabuleiro para o vetor que armazenas as jogadas.
	private int convertePosM2V(ArrayList<Integer> vet) {
		
		int pos = -1;
		
		if (!vet.isEmpty()) {
			if 		(vet.get(0).equals(0) && vet.get(1).equals(0)) pos = 0;
			else if (vet.get(0).equals(0) && vet.get(1).equals(1)) pos = 1;
			else if (vet.get(0).equals(0) && vet.get(1).equals(2)) pos = 2;
			else if (vet.get(0).equals(1) && vet.get(1).equals(0)) pos = 3;
			else if (vet.get(0).equals(1) && vet.get(1).equals(1)) pos = 4;
			else if (vet.get(0).equals(1) && vet.get(1).equals(2)) pos = 5;
			else if (vet.get(0).equals(2) && vet.get(1).equals(0)) pos = 6;
			else if (vet.get(0).equals(2) && vet.get(1).equals(1)) pos = 7;
			else if (vet.get(0).equals(2) && vet.get(1).equals(2)) pos = 8;
		}
		return pos;
	}

	
	private static String[][] carregaMatriz(){
		
		String simbolo;
		String matriz[][] = new String[3][3];
		
		for (int i=0; i<=vetPosicoesJogadas.size()-1; i++) {
			
			// indice do vetor é par, então jogador 1 = x 
			if (i%2 == 0){
				simbolo = "X";
			} else {
				simbolo = "O";
			}
			
			if (vetPosicoesJogadas.get(i) == 0) matriz[0][0] = simbolo;
			else if (vetPosicoesJogadas.get(i) == 1) matriz[0][1] = simbolo;
			else if (vetPosicoesJogadas.get(i) == 2) matriz[0][2] = simbolo;
			else if (vetPosicoesJogadas.get(i) == 3) matriz[1][0] = simbolo;
			else if (vetPosicoesJogadas.get(i) == 4) matriz[1][1] = simbolo;
			else if (vetPosicoesJogadas.get(i) == 5) matriz[1][2] = simbolo;
			else if (vetPosicoesJogadas.get(i) == 6) matriz[2][0] = simbolo;
			else if (vetPosicoesJogadas.get(i) == 7) matriz[2][1] = simbolo;
			else if (vetPosicoesJogadas.get(i) == 8) matriz[2][2] = simbolo;
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
	
	
	protected void resetarPartida(){
		Player.vetPosicoesJogadas.clear();
		Player.vetPosicoesLivres.clear();
		
		for(int i=0; i<=8; i++)
			if (!vetPosicoesLivres.contains(i)) vetPosicoesLivres.add(i);
	}

}