
import java.util.Scanner;
import java.util.*;

public class CowsBullsGame<T> {
	public CowsBullsGame(int wordLength) {
		this.wordLength = wordLength;
	}
	
	public void play() {
		word = Word.randomWord();
		
		while(true) {
			//getUserGuess();
		}
	}
	
	private Word<T> word;
		
	private int wordLength;
}
