package JogoDaVelha;

import java.util.ArrayList;


public class Jogadas {
			
	ArrayList<Integer> pos1 = new ArrayList<>();
	ArrayList<Integer> pos2 = new ArrayList<>();
	ArrayList<Integer> pos3 = new ArrayList<>();
	ArrayList<Integer> pos4 = new ArrayList<>();
	ArrayList<Integer> pos5 = new ArrayList<>();
	ArrayList<Integer> pos6 = new ArrayList<>();
	ArrayList<Integer> pos7 = new ArrayList<>();
	ArrayList<Integer> pos8 = new ArrayList<>();
	
	// Vitorias //
	
	
	/**
	 * Jogadas em Delta pelo cantos
	 * 
	 *  X |  | X
	 *    |  | 
	 *  X |  | 
	 * 
	 * @param numPlayer Jogador 1 = 1; jogador 2 = 2
	 * @return array com as possibilidades de jogada para defesa ou ataque
	 */
	@SuppressWarnings("unused")
	private ArrayList<Integer> DeltaCantosLargo(int numPlayer){
		
		ArrayList<ArrayList<Integer>> pos  = new ArrayList<>();
		ArrayList<Integer>   possibilidadeDeJogada = new ArrayList<>();
		
		clearPos();
		
		pos1.add(0); pos1.add(2); pos1.add(6);
		pos2.add(0); pos2.add(2); pos2.add(8);
		pos3.add(0); pos3.add(6); pos3.add(8);
		pos4.add(2); pos4.add(8); pos4.add(6);
		
		pos.add(pos1); pos.add(pos2); pos.add(pos3); pos.add(pos4);
		
		for (int i=0; i< pos.size(); i++)
			possibilidadeDeJogada = verificaPosicoesJogada(numPlayer, pos.get(i));
				
		return possibilidadeDeJogada;
				
	}
	
	
	/**
	 * Jogadas em Delta curta
	 * 
	 *  X | X | 
	 *  X |   |
	 *    |   |	 
	 *   
	 * @param numPlayer
	 * @return
	 */
	@SuppressWarnings("unused")
	private ArrayList<Integer> DeltaCantosCurto(int numPlayer){
		
		ArrayList<ArrayList<Integer>> pos  = new ArrayList<>();
		ArrayList<Integer>   possibilidadeDeJogada = new ArrayList<>();
		
		clearPos();
				
		pos1.add(0); pos1.add(1);	pos1.add(3);
		pos2.add(1); pos2.add(2);	pos2.add(5);
		pos3.add(6); pos3.add(7);	pos3.add(3);
		pos4.add(7); pos4.add(8);	pos4.add(5);
		
		pos.add(pos1); pos.add(pos2); pos.add(pos3); pos.add(pos4);
		
		for (int i=0; i< pos.size(); i++)
			possibilidadeDeJogada = verificaPosicoesJogada(numPlayer, pos.get(i));
				
		return possibilidadeDeJogada;
	}
	
	
	/**
	 * Jogadas em Delta pelo Centro
	 * 
	 *  X |   | X
	 *    | X |
	 *    |   |
	 * 
	 * @param numPlayer
	 * @return
	 */
	@SuppressWarnings("unused")
	private ArrayList<Integer> DeltaCentral(int numPlayer){
		
		ArrayList<ArrayList<Integer>> pos  = new ArrayList<>();
		ArrayList<Integer>   possibilidadeDeJogada = new ArrayList<>();
		
		clearPos();
		
		pos1.add(4); pos1.add(2);   pos1.add(0);	
		pos2.add(4); pos2.add(2);	pos2.add(8);
		pos3.add(4); pos3.add(6);	pos3.add(8);
		pos4.add(4); pos4.add(6);	pos4.add(0);
	
		pos.add(pos1); pos.add(pos2); pos.add(pos3); pos.add(pos4);
		
		for (int i=0; i< pos.size(); i++)
			possibilidadeDeJogada = verificaPosicoesJogada(numPlayer, pos.get(i));
				
		return possibilidadeDeJogada;
	}
	
	
	/**
	 * Jogadas em L
	 * 
	 *  X | X | 
	 *    | X |
	 *    |   |
	 * 
	 * @param numPlayer
	 * @return
	 */
	@SuppressWarnings("unused")
	private ArrayList<Integer> L(int numPlayer){
		
		ArrayList<ArrayList<Integer>> pos  = new ArrayList<>();
		ArrayList<Integer>   possibilidadeDeJogada = new ArrayList<>();
		
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
			possibilidadeDeJogada = verificaPosicoesJogada(numPlayer, pos.get(i));
				
		return possibilidadeDeJogada;
	}
	
	
	/**
	 * Metodo verifica quais as posições que foram usadas no tabuleiro por determinado jogador em determinada Jogada
	 * @param player jogador 1 ou 2, que será analisada as jogadas
	 * @param pos O array de posições que deseja verificar
	 * @return Retorna um Array de Inteiros que indica a posição que deve ser jogada
	 */
	private ArrayList<Integer> verificaPosicoesJogada(int numPlayer, ArrayList<Integer> pos){
			
		Integer i = null;
		ArrayList<Integer> posEncontradas = new ArrayList<>();
		ArrayList<Integer> posAux = new ArrayList<>();
		
		posAux = pos;
		
		if 		(numPlayer == 1)	i=0;
		else if (numPlayer == 2)	i=1;
		
		while ( i< Player.vetPosicoesJogadas.size() ) {
			
			//compara a posição i do vetorJogada com os valores do vet pos para saber se jogou nos cantos e quais
			for(int j=0; j<pos.size(); j++)
				if (Player.vetPosicoesJogadas.get(i) == pos.get(j))
					posEncontradas.add( pos.get(j) );
			
			i=i+2;	
		}
		
		if (posEncontradas.size() > 1) {
			
			for(i=0; i<posAux.size(); i++)
				for(int j=0; j<posEncontradas.size(); j++)
					if (posAux.get(i) == posEncontradas.get(j))
						posAux.remove(i);
			
		}
		
	return posAux;
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
