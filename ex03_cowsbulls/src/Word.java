
import java.util.*;

public abstract class Word<T> {
	
	public abstract List<T> randomWord(int wordLength);
	
	private List<T> word;
	private boolean match(Word<T> userWord) {
		return true;		
	}

}
