import java.io.IOException;
import java.io.Reader;

class CyrillicReader extends Reader {
	/** this is the stream to which we attach our reader */
	private Reader in;
	String curWord;
	
	/**
	 * The constructor expects a stream to be provided
	 * @param in the stream from which the reader will read the data
	 */
	public CyrillicReader(Reader in) {
		this.in = in;
	}
	
	private static boolean isLetter(int c) {
		return (c == 'a' ||
				c == 'b' ||
				c == 'w' ||
				c == 'g' ||
				c == 'd' ||
				c == 'e' ||
				c == 'v' ||
				c == 'z' ||
				c == 'i' ||
				c == 'j' ||
				c == 'k' ||
				c == 'l' ||
				c == 'm' ||
				c == 'n' ||
				c == 'o' ||
				c == 'p' ||
				c == 'r' ||
				c == 's' ||
				c == 't' ||
				c == 'u' ||
				c == 'f' ||
				c == 'h' ||
				c == 'c' ||
				c == '`' ||
				c == '[' ||
				c == ']' ||
				c == 'y' ||
				c == 'x' ||
				c == '\\' ||
				c == 'q');
	}
	
	private static char toCyrillic(int c) {
		switch((char)c) {
			case 'a': return 'а';
			case 'b': return 'б';
			case 'w': return 'в';
			case 'g': return 'г';
			case 'd': return 'д';
			case 'e': return 'е';
			case 'v': return 'ж';
			case 'z': return 'з';
			case 'i': return 'и';
			case 'j': return 'й';
			case 'k': return 'к';
			case 'l': return 'л';
			case 'm': return 'м';
			case 'n': return 'н';
			case 'o': return 'о';
			case 'p': return 'п';
			case 'r': return 'р';
			case 's': return 'с';
			case 't': return 'т';
			case 'u': return 'у';
			case 'f': return 'ф';
			case 'h': return 'х';
			case 'c': return 'ц';
			case '`': return 'ч';
			case '[': return 'ш';
			case ']': return 'щ';
			case 'y': return 'ъ';
			case 'x': return 'ь';
			case '\\':return 'ю';
			case 'q': return 'я';
			default: return (char)c;
		}
	}
	
	/*
	private static String toCyrillic(String word) {
		for(int i = 0; i < word.length; i++)
			word[i] = toCyrillic(word[i]);
		return word;
	}
	*/
	
	//We override only the single byte read method in this example
	@Override
	public int read() throws IOException {
		int c = in.read();
		
		if(isLetter(c)) {
			curWord += (char) c;
			return toCyrillic(c);
		}
		
		else {
			if(curWord.equals("krai"))
				return 'I';
			
			else {
				curWord = "";
				return c;
			}
		}
	}
	
	public String readWord() throws IOException {
		while(true) {
			int c = in.read();
		
			if(isLetter(c)) {
				curWord += toCyrillic((char) c);
				
				if(curWord.equals("край"))
					return null;
			}
			
			else {
				String result = curWord + toCyrillic((char) c);
				curWord = "";
				return result;
			}
		}
	}

	//This method is just a proxy to the input stream. No conversion to cyrillic will happen if you use this.
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return in.read(cbuf, off, len);
	}

	/**
	 * Its a good practice to close the underlying stream when the close method is invoked
	 */
	@Override
	public void close() throws IOException {
		in.close();
	}
}