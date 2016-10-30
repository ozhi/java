
import java.util.*;

enum TictactoePlayer {
	X, O, NEITHER;
	
	public static char getSymbol(TictactoePlayer player) {
		switch(player) {
			case O:       return 'O';
			case X:       return 'X';
			case NEITHER: return ' ';
			default: return '?'; //ERROR
		}
	}
	
	public static TictactoePlayer getOtherPlayer(TictactoePlayer player) throws  IllegalArgumentException {		
		if(player == O)
			return X;
		
		if(player == X)
			return O;
		
		System.out.println("Error: Invalid player passed to getOtherPlayer()");
			throw new IllegalArgumentException();
		//return player; //exception should be thrown instead of returning
	}
}