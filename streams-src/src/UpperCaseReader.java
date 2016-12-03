import java.io.IOException;
import java.io.Reader;

/**
 * Stream implementation that converts all letters to upper case
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/io/Reader.html">Reader</а>
 */
public class UpperCaseReader extends Reader {
	/** this is the stream to which we attach our reader */
	private Reader in;
	
	/**
	 * The constructor expects a stream to be provided
	 * @param in the stream from which the reader will read the data
	 */
	public UpperCaseReader(Reader in) {
		this.in = in;
	}
	
	
	/**
	 * We overide only the single byte read method in this example
	 */
	@Override
	public int read() throws IOException {
		int c = in.read();
		return Character.toUpperCase(c);
	}


	/**
	 * This method is just a proxy to the input stream. No conversion to upper case will happen if you use this.
	 */
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
