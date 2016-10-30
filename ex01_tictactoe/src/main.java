
public class main {

	/*
	 * Maybe my code has some inconsistencies as to when exceptions are thrown and what is printed
	 */
	
	public static void main(String[] args) {
		TictactoeGame game = new TictactoeGame(4, TictactoeGameMode.PVP);
		
		game.play();
	}
	
}
