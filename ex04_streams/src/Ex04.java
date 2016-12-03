import java.io.*;

public class Ex04 {
	public static void main(String[] args) {
			
		try(CyrillicReader r = new CyrillicReader(new InputStreamReader(System.in));
			FileWriter fout = new FileWriter("cyrillic-output.txt")) {
						
			String word;
			while((word = r.readWord()) != null) {
				System.out.print(word);
				fout.write(word);
				fout.flush();
			}

		} catch(Exception e) {
			e.printStackTrace();			
		}
	}
}
