import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Show different usages of streams
 * For more information check the java.io package documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html
 * There you will find other interestion stream implementations
 */
public class StreamsDev {
	
	/**
	 * Class that will be used to be written to a stream (file)
	 * The class MUST implement the Serializable interface and all its members should be serializable
	 */
	static class Test implements Serializable {
		private int i;
		private float f;
		private String s;
		
		public Test(int i, float f, String s) {
			this.i = i;
			this.f = f;
			this.s = s;
		}
	
		/**
		 * We override this method so that we can pretty print the object 
		 */
		public String toString() {
			return "int: " + i + "; float: " + f + "; string: " + s;
		}
	}
	
	/**
	 * Main method. Uncomment sections to check the different functionalities 
	 * @param args command line arguments
	 */ 
	public static void main(String[] args) {
		StreamsDev streams = new StreamsDev();
//		streams.parseInput();
//		Test obj = new Test(10, 3.4f, "kabanga!");
//		streams.writeObjectToFile(obj);
//		System.out.println(streams.readObjFromFile());
//		streams.writeWithDataStream();
//		streams.readDataStream();
//		streams.writeFileWithPrintWriter();
//		streams.writeToFile();
		streams.readUpperCase();
//		String s = new String(streams.readFromFileBuffered());
//		System.out.println(s);
//		streams.writeBigFile();
//		streams.compareRead(10);
//		System.out.println(streams.readFileWithReader());
	}
	
	
	public void parseInput() {
		Scanner scan = new Scanner(System.in);
		System.out.println(scan.nextInt());
		System.out.println(scan.next());
		scan.close();
	}
	
	/** 
	 * Converts all letters to upper case during read
	 */
	public void readUpperCase() {
		try(UpperCaseReader is = new UpperCaseReader(
				new FileReader("test.txt"))) {
			int c = 0;
			while((c = is.read()) != -1) {
				System.out.print((char)c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads an object from file
	 * https://docs.oracle.com/javase/8/docs/api/java/io/ObjectInputStream.html
	 * @return de-serialized object
	 */
	public Object readObjFromFile() {
		try(ObjectInputStream is = 
				new ObjectInputStream(
						new FileInputStream("test.dat"))) {
			return is.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Serializes (writes) an object into file
	 * https://docs.oracle.com/javase/8/docs/api/java/io/ObjectOutputStream.html
	 * @param obj the object to write
	 */
	public void writeObjectToFile(Object obj) {
		try(ObjectOutputStream os = 
				new ObjectOutputStream(
						new FileOutputStream("test.dat"))) {
			os.writeObject(obj);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads structured data using DataInputStream
	 * https://docs.oracle.com/javase/8/docs/api/java/io/DataInputStream.html
	 */
	public void readDataStream() {
		try(DataInputStream is = new DataInputStream(new FileInputStream("test.dat"))) {
			System.out.println("int: " + is.readInt());
			System.out.println("float: " + is.readFloat());
			System.out.println("string: " + is.readUTF());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes structured data using DataOutputStream
	 * https://docs.oracle.com/javase/8/docs/api/java/io/DataOutputStream.html
	 */
	public void writeWithDataStream() {
		try(DataOutputStream os = new DataOutputStream(
				new FileOutputStream("test.dat"))) {
			os.writeInt(10);
			os.writeFloat(5.2f);
			os.writeUTF("potato");
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes to file using PrintWriter. A commonly used class in many implementations (networking)
	 * https://docs.oracle.com/javase/8/docs/api/java/io/PrintStream.html
	 */
	public void writeFileWithPrintWriter() {
		try(PrintWriter pw = new PrintWriter("test.txt")) {
			pw.println("Hello FMI print writer!");
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads from text file using a BufferedReader implementation that wraps a FileReader
	 * https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
	 * https://docs.oracle.com/javase/8/docs/api/java/io/FileReader.html
	 * @return file contents as String
	 */
	public String readFileWithReader() {
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
			sb.append(br.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
	/**
	 * Compare different read methods
	 * @param amount of reads
	 */
	public void compareRead(int amount) {
		long t = System.currentTimeMillis();
		for(int i=0; i < amount; ++i) {
			readFromFile();
		}
		long dt1 = System.currentTimeMillis() - t;
		t = System.currentTimeMillis();
		for(int i=0; i < amount; ++i) {
			readFromFileBufferedStream();
		}
		long dt2 = System.currentTimeMillis() - t;
		t = System.currentTimeMillis();
		for(int i=0; i < amount; ++i) {
			readFromFileBuffered();
		}
		long dt3 = System.currentTimeMillis() - t;
		System.out.println("Single byte: " + dt1 + "; BufferedStream: " + dt2 + "; Buffered read: " + dt3);
	}
	
	/**
	 * Writes a big file
	 */
	public void writeBigFile() {
		try(OutputStream os = new GZIPOutputStream(
				new FileOutputStream("test.zip"))) {
			for(int i=0; i<100000; ++i) {
				os.write("Test! ".getBytes());
			}
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read from file using buffer byte[]
	 * https://docs.oracle.com/javase/8/docs/api/java/io/ByteArrayOutputStream.html
	 * @return file contents
	 */
	public byte[] readFromFileBuffered() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try(InputStream is = new GZIPInputStream(
				new FileInputStream("test.zip"))) {
			byte[] buff = new byte[2048];
			int r = 0;
			while((r = is.read(buff)) != -1) {
				bos.write(buff, 0, r);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}
	
	/**
	 * Read from file using BufferedInputStream
	 * https://docs.oracle.com/javase/8/docs/api/java/io/BufferedInputStream.html
	 * @return file contents
	 */
	public byte[] readFromFileBufferedStream() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try(BufferedInputStream is = new BufferedInputStream(
				new FileInputStream("test.txt"))) {
			int i = 0;
			while((i = is.read()) != -1) {
				bos.write(i);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}
	
	/**
	 * Unbuffered read from file fetching one byte at a time
	 * https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html
	 * @return file contents
	 */
	public byte[] readFromFile() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try(FileInputStream is = new FileInputStream("test.txt")) {
			int i = 0;
			while((i = is.read()) != -1) {
				bos.write(i);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}
	
	/**
	 * Write to file using FileOutputStream
	 * https://docs.oracle.com/javase/8/docs/api/java/io/FileOutputStream.html
	 */
	public void writeToFile() {
		try(FileOutputStream os = new FileOutputStream("test.txt")) {
			os.write("Hello FMI!".getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
