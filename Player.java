package JogoDaVelha;

import java.util.ArrayList;
import java.util.Random;

public class Player {
	
	protected static ArrayList<Integer> vetJogada = new ArrayList<>();

	public boolean Human(int j) {
		return jogada(j);
	}


	public boolean Computer() {
		Random jAleatoria = new Random(9);
		return jogada(jAleatoria.nextInt(8-0)+8);
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

}

