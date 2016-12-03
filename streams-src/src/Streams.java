import java.io.*;

public class Streams {
	public static void main(String[] args) {
		try(BufferedReader r = new BufferedReader(new FileReader("test.txt"))) {
			String line;
			while((line = r.readLine()) != null) {
				System.out.println(line);
			};
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
