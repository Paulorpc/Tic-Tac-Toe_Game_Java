package JogoDaVelha;

import java.util.ArrayList;
import java.util.Random;

public class Player2 {
	
	// Vetor com todas as jogadas feitas pelos jogadores
	protected static ArrayList<Integer> vetPosicoesJogadas = new ArrayList<>();
	
	// vetor com as posições livres do tabuleiro
	protected static ArrayList<Integer> vetPosicoesLivres = new ArrayList<>();
	
	// vetor com as posições jogadas pelo jogador
	protected ArrayList<Integer> vetPosicoesJogadasPlayer = new ArrayList<>();
	
	// Quem é o jogador (homem ou máquina)
	protected String player;
	
	// simbolos do jogo para j1 e j2 respectivamente
	protected String simbolo[] = {"X", "O"};
	
	// numero do jogador e oponente (j1 = 1; j2 = 2)
	private int numPlayer;
	private int numOponente;
	
	protected int vitorias;
	protected static int empates;
	
	// Construtor
	public Player2(int numPlayer, String player) {
		
		// adiciona todas posições livres no vetor se ela não foi adicionada antes
		for(int i=0; i<=8; i++)
			if (!vetPosicoesLivres.contains(i)) vetPosicoesLivres.add(i);
		
		this.player = player;
		this.vitorias = 0;
		Player2.empates = 0;
		
		this.numPlayer = numPlayer;
		
		if (numPlayer == 1) 
			numOponente = 2;
		else
			numOponente = 1;
			
	}

	public boolean Human(int j) {
		return jogada(j);
	}


	/**
	 * Método de chamada para o computador com Inteligência ou não.
	 * @param IA Ativa ou não a inteligência do computador
	 * @return Retorna true/false se conseguiu a jogada
	 */
	public boolean Computer(boolean IA) {
		if (IA)
			return jogada(IA());			
		else 
			return jogada(jogaAleatoriamente());
	}
	
	
	/**
	 * Faz a validação da jogada. Verifica se a posição existe no tabuleiro, se está vazia,
	 * e se for uma jogada válida, então registra a jogada e remove a posição do vetor de
	 * posições livre. 
	 * @param j
	 * @return
	 */
	private boolean jogada(int j){
		
		if (j < 0 || j > 8) { 
			return false;
			
		} else if (vetPosicoesJogadas.contains(j)){
			return false;
			
		} else {
			vetPosicoesJogadas.add(j);
			vetPosicoesJogadasPlayer.add(j);
			vetPosicoesLivres.remove(vetPosicoesLivres.indexOf(j));
			return true;
		}
	}
	
	
	
	/**
	 * Inteligencia Artificial para computador
	 * @return posição a ser jogada
	 */
	private int IA(){
		
		ArrayList<Integer> pos = new ArrayList<>();
		ArrayList<Integer> posCantos = new ArrayList<>();
		ArrayList<Integer> posCruz = new ArrayList<>();
		ArrayList<Integer> posDiagonal = new ArrayList<>();
		ArrayList<Integer> posCentro = new ArrayList<>();
		
		// Posições do tabuleiro
		// X | | X           | X |         |   |
		//   | |           X |   | X       | X |
		// X | | X           | X |         |   |
		posCantos.add(0); 	posCantos.add(2);	posCantos.add(6);	posCantos.add(8);
		posCruz.add(1); 	posCruz.add(3);		posCruz.add(5); 	posCruz.add(7);  
		posDiagonal.add(0); posDiagonal.add(8); posDiagonal.add(2); posDiagonal.add(6);
		posCentro.add(4);
		
		
		// Sempre verificar antes se há possibilidade de vitória
		if (vetPosicoesJogadas.size() >= 3) { 
			
			int vitOponente;
			int vitJogador;
			
			vitJogador   = verficaPossibilidadeVitoria(numPlayer);
			vitOponente  = verficaPossibilidadeVitoria(numOponente);
			
			// Se existe possibilidade de vitoria ganha/defende
			if 		(vitJogador  != -1)	return vitJogador;
			else if (vitOponente != -1)	return vitOponente;
			
		}
		
		// Verifico se jogador está armando uma jogada em diagonal e bloqueia
		if (vetPosicoesJogadas.size() >= 3) { 
			
			pos  = verificaPosicoesJogada(numOponente, "cantos");
			
			// Verifica se existe mais de um valor retornado no vetor pos para existir uma diagonal
			if ( pos.size() > 1 && ( 
					
					// e também se foi formada a diagonal
					(pos.get(0).equals(posDiagonal.get(0)) && pos.get(1).equals(posDiagonal.get(1))) ||
					(pos.get(0).equals(posDiagonal.get(2)) && pos.get(1).equals(posDiagonal.get(3))) ) ) 
			{
				return jogaAleatoriamentePosicoesPredefinidas(posCruz);

			}
		}
				
		
		// jogador 1, sai jogando sempre no centro
		if (vetPosicoesJogadas.isEmpty())
			return 4;
					
		
		// rodada 2 
		if (vetPosicoesJogadas.size() == 1) {
		
			pos  = verificaPosicoesJogada(numOponente, "centro");			
			
			// Se jogador 1 não tiver jogado no centro, joga no centro
			if ( pos.isEmpty() )				
				return 4;			
			
			// senão joga nos cantos
			else 				
				return jogaAleatoriamentePosicoesPredefinidas(posCantos);
			
		}		
		
		//Rodada 3
		if ( vetPosicoesJogadas.size() >= 2 )
			
			// verifica a melhor jogada possível
			return jogaAleatoriamentePosicoesPredefinidas( melhoresPosicoesDasJogada(numPlayer) );
		else 
			// se não tiver joga em uma posição livre
			return jogaAleatoriamentePosicoesPredefinidas(vetPosicoesLivres);

	}
	
	
	/**
	 * Verifica quais as posições que foram usadas no tabuleiro por determinado jogador
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
		
		if 		(numPlayer == 1)	i=0;
		else if (numPlayer == 2)	i=1;
		
		while (i<vetPosicoesJogadas.size()) {
			
			//compara a posição i do vetorJogada com os valores do vet pos para saber se jogou nos cantos e quais
			for(int j=0; j<pos.size(); j++)
				if (vetPosicoesJogadas.get(i) == pos.get(j))
					posicoes.add(pos.get(j));
			
			i=i+2;	
		}
		
	return posicoes;
	}	
	
	
	/**
	 * Verifica qual o simbolo de acordo com o número do jogador
	 * @param numPlayer Player 1 = 1; Player 2 = 2.
	 * @return o simbolo do jogador
	 */
	public String verificaSimbolo(int numPlayer){
		String simb = null;
		
		if 		(numPlayer == 1) 	simb = simbolo[0];
		else if (numPlayer == 2) 	simb = simbolo[1];
		
		return simb;
	}
	
	
	/**
	 * Retorna um posição aleatória vazia do tabuleiro
	 * @return
	 */
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
	
	
	
	/**
	 * Retorna uma posição aleatória dentro de um conjunto de possibilidades predefinidas
	 * passado por parametro
	 * @param arrPos Posições predefinidas
	 * @return posição escolhida aleatoriamente
	 */
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
	
	
	/**
	 * @param numPlayer
	 * @return
	 */
	private ArrayList<Integer> melhoresPosicoesDasJogada(int numPlayer) { 
	
		Jogadas jogada = new Jogadas();
		ArrayList<ArrayList<Integer>> jogadas = new ArrayList<>();
		ArrayList<Integer> melhorJogada = new ArrayList<>();
		
		jogadas.add( jogada.DeltaCantosLargo(numPlayer) );
		jogadas.add( jogada.DeltaCantosCurto(numPlayer) );
		jogadas.add( jogada.DeltaCentral(numPlayer) );
		jogadas.add( jogada.L(numPlayer) );
		
		
		for (int j=jogadas.size()-1; j >= 0; j--)
			if (jogadas.get(j) == null)
				jogadas.remove(j);
		
		// verificar qual das possibilidades é a melhor
		for (int i=0; i< jogadas.size(); i++)
			melhorJogada.add( jogada.verificaMelhorPossibilidadeVitoria(numPlayer, jogadas.get(i)) );
		
		
		int auxPosicaoMaior = 0;
		for (int n = 0; n < melhorJogada.size(); n++){
			if(melhorJogada.get(n) > melhorJogada.get(auxPosicaoMaior)) auxPosicaoMaior = n;
		}
		
		
		if (jogadas.isEmpty())
			return vetPosicoesLivres;
		else {
			return jogadas.get( auxPosicaoMaior );
		}
		
	}
	 
	/**
	 * Verifica possibilidade de vitoria em retas, seja linha, colunas ou diagonais.
	 * 
	 * X |   | 
	 *   |   |
	 *   |   | X
	 *  
	 * @param player
	 * @return Retorna a possição de vitória
	 */
	private int verficaPossibilidadeVitoria(int numPlayer) {
		String matriz[][] = new String[3][3];
		String simb = null;
		matriz = carregaMatriz();
		
		simb = verificaSimbolo(numPlayer);
		
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
		
		if (!vet.isEmpty())
			return convertePosM2V(vet);
		else 
			return -1;
		
	}
	
		
	
	/**
	 * Converte posição do tabuleiro em formato de matriz para a forma sequencial trabalhada no vetor.
	 * @param vet posição em forma matriz ex: [1,1]
	 * @return posição numeral. Ex: 4
	 */
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

	
	/**
	 * Cria a matriz do tabuleiro com as posições dos jogadores (X e O)
	 * @return a matriz
	 */
	public static String[][] carregaMatriz(){
		
		String simbolo;
		String matriz[][] = new String[3][3];
		
		for (int i=0; i<=vetPosicoesJogadas.size()-1; i++) {
			
			// indice do vetor é par, então jogador 1 = x 
			if (i%2 == 0)
				simbolo = "X";
			else
				simbolo = "O";
			
			
			if 		(vetPosicoesJogadas.get(i) == 0) matriz[0][0] = simbolo;
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
	
	
	/**
	 * Impre o tabuleiro com as jogadas 
	 */
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
	
	
	/**
	 * Verifica se há algum jogador completou linhas, colunas ou diagonais.
	 * @return se há ou não ganhador através de um boolean
	 */
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
	
	
	/**
	 * Reseta a partida limpando o tabuleiro
	 */
	protected void resetarPartida(){
		Player2.vetPosicoesJogadas.clear();
		Player2.vetPosicoesLivres.clear();
		
		for(int i=0; i<=8; i++)
			if (!vetPosicoesLivres.contains(i)) vetPosicoesLivres.add(i);
	}

}