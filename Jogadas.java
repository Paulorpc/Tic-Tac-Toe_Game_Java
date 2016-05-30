package JogoDaVelha;

import java.util.ArrayList;

public class Jogadas {
			
	private ArrayList<Integer> pos1 = new ArrayList<>();
	private ArrayList<Integer> pos2 = new ArrayList<>();
	private ArrayList<Integer> pos3 = new ArrayList<>();
	private ArrayList<Integer> pos4 = new ArrayList<>();
	private ArrayList<Integer> pos5 = new ArrayList<>();
	private ArrayList<Integer> pos6 = new ArrayList<>();
	private ArrayList<Integer> pos7 = new ArrayList<>();
	private ArrayList<Integer> pos8 = new ArrayList<>();
	
	/**
	 * Jogadas em Delta pelos cantos
	 * 
	 *  X |  | X
	 *    |  | 
	 *  X |  | 
	 * 
	 * @param numPlayer Jogador 1 = 1; jogador 2 = 2
	 * @return array com as possibilidades de jogada para defesa ou ataque
	 */
	public ArrayList<Integer> DeltaCantosLargo(int numPlayer){
		
		ArrayList<ArrayList<Integer>> pos  = new ArrayList<>();
		ArrayList<ArrayList<Integer>> possibilidadeDeJogada = new ArrayList<>();
		ArrayList<Integer> melhorPossibilidade = new ArrayList<>();
		
		clearPos();
		
		pos1.add(0); pos1.add(2); pos1.add(6);
		pos2.add(0); pos2.add(2); pos2.add(8);
		pos3.add(0); pos3.add(6); pos3.add(8);
		pos4.add(2); pos4.add(8); pos4.add(6);
		
		pos.add(pos1); pos.add(pos2); pos.add(pos3); pos.add(pos4);
		
		// Vverifica quais são possiveis jogadas
		for (int i=0; i< pos.size(); i++)
			possibilidadeDeJogada.add( verificaPosicoesJogada(numPlayer, pos.get(i)) );
		
		// Se voltou -1 então a jogada não serve, remove do vetor.
		for (int j=possibilidadeDeJogada.size()-1; j >= 0; j--)
			if (possibilidadeDeJogada.get(j).contains(-1))
				possibilidadeDeJogada.remove(j);
		
		// verificar qual das possibilidades é a melhor
		for (int i=0; i< possibilidadeDeJogada.size(); i++)
			melhorPossibilidade.add( verificaMelhorPossibilidadeVitoria(numPlayer, possibilidadeDeJogada.get(i)) );
		
		// coloca a possibilidade com mais chances de vitória em auxPosicaoMaior para retorno.
		int auxPosicaoMaior = 0;
		for (int n = 0; n < melhorPossibilidade.size(); n++){
			if(melhorPossibilidade.get(n) > melhorPossibilidade.get(auxPosicaoMaior)) auxPosicaoMaior = n;
		}

		if (possibilidadeDeJogada.isEmpty())
			return null;
		else
			return possibilidadeDeJogada.get( auxPosicaoMaior );
			
	}
	
	
	/**
	 * Jogadas em Delta curta
	 * 
	 *  X | X | 
	 *  X |   |
	 *    |   |	 
	 *   
	 * @param numPlayer Jogador 1 = 1; jogador 2 = 2
	 * @return array com as possibilidades de jogada para defesa ou ataque
	 */
	public ArrayList<Integer> DeltaCantosCurto(int numPlayer){
		
		ArrayList<ArrayList<Integer>> pos  = new ArrayList<>();
		ArrayList<ArrayList<Integer>> possibilidadeDeJogada = new ArrayList<>();
		ArrayList<Integer> melhorPossibilidade = new ArrayList<>();
		
		clearPos();
				
		pos1.add(0); pos1.add(1);	pos1.add(3);
		pos2.add(1); pos2.add(2);	pos2.add(5);
		pos3.add(6); pos3.add(7);	pos3.add(3);
		pos4.add(7); pos4.add(8);	pos4.add(5);
		
		pos.add(pos1); pos.add(pos2); pos.add(pos3); pos.add(pos4);
		
		for (int i=0; i< pos.size(); i++)
			possibilidadeDeJogada.add( verificaPosicoesJogada(numPlayer, pos.get(i)) );
		
		
		for (int j=possibilidadeDeJogada.size()-1; j >= 0; j--)
			if (possibilidadeDeJogada.get(j).contains(-1))
				possibilidadeDeJogada.remove(j);
		
		
		for (int i=0; i< possibilidadeDeJogada.size(); i++)
			melhorPossibilidade.add( verificaMelhorPossibilidadeVitoria(numPlayer, possibilidadeDeJogada.get(i)) );
		
		
		int auxPosicaoMaior = 0;
		for (int n = 0; n < melhorPossibilidade.size(); n++){
			if(melhorPossibilidade.get(n) > melhorPossibilidade.get(auxPosicaoMaior)) auxPosicaoMaior = n;
		}
		
		if (possibilidadeDeJogada.isEmpty())
			return null;
		else
			return possibilidadeDeJogada.get( auxPosicaoMaior );

	}
	
	
	/**
	 * Jogadas em Delta pelo Centro
	 * 
	 *  X |   | X
	 *    | X |
	 *    |   |
	 * 
	 * @param numPlayer Jogador 1 = 1; jogador 2 = 2
	 * @return array com as possibilidades de jogada para defesa ou ataque
	 */
	public ArrayList<Integer> DeltaCentral(int numPlayer){
		
		ArrayList<ArrayList<Integer>> pos  = new ArrayList<>();
		ArrayList<ArrayList<Integer>> possibilidadeDeJogada = new ArrayList<>();
		ArrayList<Integer> melhorPossibilidade = new ArrayList<>();
		
		clearPos();
		
		pos1.add(4); pos1.add(2);   pos1.add(0);	
		pos2.add(4); pos2.add(2);	pos2.add(8);
		pos3.add(4); pos3.add(6);	pos3.add(8);
		pos4.add(4); pos4.add(6);	pos4.add(0);
	
		pos.add(pos1); pos.add(pos2); pos.add(pos3); pos.add(pos4);
		
		for (int i=0; i< pos.size(); i++)
			possibilidadeDeJogada.add( verificaPosicoesJogada(numPlayer, pos.get(i)) );
		
		
		for (int j=possibilidadeDeJogada.size()-1; j >= 0; j--)
			if (possibilidadeDeJogada.get(j).contains(-1))
				possibilidadeDeJogada.remove(j);
		 
		
		for (int i=0; i< possibilidadeDeJogada.size(); i++)
			melhorPossibilidade.add( verificaMelhorPossibilidadeVitoria(numPlayer, possibilidadeDeJogada.get(i)) );
		
		
		int auxPosicaoMaior = 0;
		for (int n = 0; n < melhorPossibilidade.size(); n++){
			if(melhorPossibilidade.get(n) > melhorPossibilidade.get(auxPosicaoMaior)) auxPosicaoMaior = n;
		}
		
		
		if (possibilidadeDeJogada.isEmpty())
			return null;
		else
			return possibilidadeDeJogada.get( auxPosicaoMaior );
	
	}
	
	
	/**
	 * Jogadas em L
	 * 
	 *  X | X | 
	 *    | X |
	 *    |   |
	 * 
	 * @param numPlayer Jogador 1 = 1; jogador 2 = 2
	 * @return array com as possibilidades de jogada para defesa ou ataque
	 */
	public ArrayList<Integer> L(int numPlayer){
		
		ArrayList<ArrayList<Integer>> pos  = new ArrayList<>();
		ArrayList<ArrayList<Integer>> possibilidadeDeJogada = new ArrayList<>();
		ArrayList<Integer> melhorPossibilidade = new ArrayList<>();
		
		clearPos();
		
		pos1.add(1); pos1.add(4);   pos1.add(0);	
		pos2.add(1); pos2.add(4);	pos2.add(2);
		pos3.add(4); pos3.add(7);	pos3.add(6);
		pos4.add(4); pos4.add(7);	pos4.add(8);
		pos5.add(3); pos5.add(4);	pos5.add(0);
		pos6.add(3); pos6.add(4);	pos6.add(6);
		pos7.add(4); pos7.add(5);	pos7.add(2);
		pos8.add(4); pos8.add(5);	pos8.add(8);
	
		pos.add(pos1); pos.add(pos2); pos.add(pos3); pos.add(pos4);
		pos.add(pos5); pos.add(pos6); pos.add(pos7); pos.add(pos8);
		
		for (int i=0; i< pos.size(); i++)
			possibilidadeDeJogada.add( verificaPosicoesJogada(numPlayer, pos.get(i)) );
		

		for (int j=possibilidadeDeJogada.size()-1; j >= 0; j--)
			if (possibilidadeDeJogada.get(j).contains(-1))
				possibilidadeDeJogada.remove(j);
		 

		for (int i=0; i< possibilidadeDeJogada.size(); i++)
			melhorPossibilidade.add( verificaMelhorPossibilidadeVitoria(numPlayer, possibilidadeDeJogada.get(i)) );
		
		
		int auxPosicaoMaior = 0;
		for (int n = 0; n < melhorPossibilidade.size(); n++){
			if(melhorPossibilidade.get(n) > melhorPossibilidade.get(auxPosicaoMaior)) auxPosicaoMaior = n;
		}
		
		if (possibilidadeDeJogada.isEmpty())
			return null;
		else 
			return possibilidadeDeJogada.get( auxPosicaoMaior );
		
	}
	
	
	
	/**
	 * Metodo verifica quais as posições que foram usadas no tabuleiro por determinado jogador em determinada Jogada
	 * @param player jogador 1 ou 2, que será analisada as jogadas
	 * @param pos O array de posições que deseja verificar
	 * @return Retorna um Array de Inteiros que indica a posição que deve ser jogada
	 */
	public ArrayList<Integer> verificaPosicoesJogada(int numPlayer, ArrayList<Integer> pos){
			
		Integer i = null;
		ArrayList<Integer> posEncontradas = new ArrayList<>();
		ArrayList<Integer> posAux = new ArrayList<>();
		int qtdePos = 0;
		
		posAux.addAll(pos);
		
		if 		(numPlayer == 1)	i=0;
		else if (numPlayer == 2)	i=1;
		
		qtdePos = pos.size();

		// Percorre as posições do jogadas pelo jogador
		while ( i< Player2.vetPosicoesJogadas.size() ) {			
			
			//for(int j=0; j<pos.size(); j++) {
			for(int j=0; j<qtdePos; j++) {
				
				//Verifica se o player jogou em uma das posicoes do vetor da jogada
				if (Player2.vetPosicoesJogadas.get(i).equals( pos.get(j) )) {
					posEncontradas.add( pos.get(j) );
					posAux.remove(j);
					qtdePos--;
				}
				
			}
					
			i=i+2;	
		}
		
		// Se as posicoes do vetor auxiliar não estiverem livres retorna um -1
		for(int j=0; j<posAux.size(); j++) {
			if ( !Player2.vetPosicoesLivres.contains(posAux.get(j)) || posEncontradas.isEmpty() ) {
				posAux.clear();
				posAux.add(-1);
				break;
			}
		}
					
	return posAux;
	}
	
	
	/**
	 * Simula a jogada futura para saber qual possui maior chances de vitória
	 * @param numPlayer Player 1 = 1; Player 2 = 2. 
	 * @param pos Vetor com as posições da jogada
	 * @return Retorna o número de possíveis chances da jogada
	 */
	public Integer verificaMelhorPossibilidadeVitoria(int numPlayer, ArrayList<Integer> pos){
				
		ArrayList<Integer> vetJogadasAux = new ArrayList<>();
		Integer i=null;
		
		int contador = 0;
		
		if 		(numPlayer == 1)	i=0;
		else if (numPlayer == 2)	i=1;

		// Percorre as posiões do jogadas pelo jogador
		while ( i < Player2.vetPosicoesJogadas.size() ) {
			
			vetJogadasAux.add(Player2.vetPosicoesJogadas.get(i));
					
			i=i+2;	
		}
		
		for (int m = 0; m < pos.size(); m++){
			vetJogadasAux.add(pos.get(m));
		}
		
		// Verifica as possíveis posições de vitória e adiciona ao contador
		//linhas
		if(vetJogadasAux.contains(0) && vetJogadasAux.contains(1) && !Player2.vetPosicoesJogadas.contains(2)) contador++;
		if(vetJogadasAux.contains(0) && vetJogadasAux.contains(2) && !Player2.vetPosicoesJogadas.contains(1)) contador++;
		if(vetJogadasAux.contains(1) && vetJogadasAux.contains(2) && !Player2.vetPosicoesJogadas.contains(0)) contador++;
		if(vetJogadasAux.contains(0+3) && vetJogadasAux.contains(1+3) && !Player2.vetPosicoesJogadas.contains(2+3)) contador++;
		if(vetJogadasAux.contains(0+3) && vetJogadasAux.contains(2+3) && !Player2.vetPosicoesJogadas.contains(1+3)) contador++;
		if(vetJogadasAux.contains(1+3) && vetJogadasAux.contains(2+3) && !Player2.vetPosicoesJogadas.contains(0+3)) contador++;
		if(vetJogadasAux.contains(0+6) && vetJogadasAux.contains(1+6) && !Player2.vetPosicoesJogadas.contains(2+6)) contador++;
		if(vetJogadasAux.contains(0+6) && vetJogadasAux.contains(2+6) && !Player2.vetPosicoesJogadas.contains(1+6)) contador++;
		if(vetJogadasAux.contains(1+6) && vetJogadasAux.contains(2+6) && !Player2.vetPosicoesJogadas.contains(0+6)) contador++;
		
		//colunas
		if(vetJogadasAux.contains(0) && vetJogadasAux.contains(3) && !Player2.vetPosicoesJogadas.contains(6)) contador++;
		if(vetJogadasAux.contains(0) && vetJogadasAux.contains(6) && !Player2.vetPosicoesJogadas.contains(3)) contador++;
		if(vetJogadasAux.contains(3) && vetJogadasAux.contains(6) && !Player2.vetPosicoesJogadas.contains(0)) contador++;
		if(vetJogadasAux.contains(0+1) && vetJogadasAux.contains(3+1) && !Player2.vetPosicoesJogadas.contains(6+1)) contador++;
		if(vetJogadasAux.contains(0+1) && vetJogadasAux.contains(6+1) && !Player2.vetPosicoesJogadas.contains(3+1)) contador++;
		if(vetJogadasAux.contains(3+1) && vetJogadasAux.contains(6+1) && !Player2.vetPosicoesJogadas.contains(0+1)) contador++;
		if(vetJogadasAux.contains(0+2) && vetJogadasAux.contains(3+2) && !Player2.vetPosicoesJogadas.contains(6+2)) contador++;
		if(vetJogadasAux.contains(0+2) && vetJogadasAux.contains(6+2) && !Player2.vetPosicoesJogadas.contains(3+2)) contador++;
		if(vetJogadasAux.contains(3+2) && vetJogadasAux.contains(6+2) && !Player2.vetPosicoesJogadas.contains(0+2)) contador++;
		
		//diagonais
		if(vetJogadasAux.contains(0) && vetJogadasAux.contains(4) && !Player2.vetPosicoesJogadas.contains(8)) contador++;
		if(vetJogadasAux.contains(0) && vetJogadasAux.contains(8) && !Player2.vetPosicoesJogadas.contains(4)) contador++;
		if(vetJogadasAux.contains(4) && vetJogadasAux.contains(8) && !Player2.vetPosicoesJogadas.contains(0)) contador++;
		if(vetJogadasAux.contains(2) && vetJogadasAux.contains(4) && !Player2.vetPosicoesJogadas.contains(6)) contador++;
		if(vetJogadasAux.contains(2) && vetJogadasAux.contains(6) && !Player2.vetPosicoesJogadas.contains(4)) contador++;
		if(vetJogadasAux.contains(4) && vetJogadasAux.contains(6) && !Player2.vetPosicoesJogadas.contains(2)) contador++;
		
		return contador;
	}
	
	
	/**
	 * Limpa ArrayLists posição (pos)
	 */
	private void clearPos() {
		
		pos1.clear();
		pos2.clear();
		pos3.clear();
		pos4.clear();
		pos5.clear();
		pos6.clear();
		pos7.clear();
		pos8.clear();
		
	}


}
