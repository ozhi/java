

public class hw1_tictactoe {
	
	public static void main(String[] args) {
		
		//maybe it is a bad practice to have all classes have a 'Tictactoe' prefix since they would all be in a TictactoeGame package?
		
		TictactoeGame game = new TictactoeGame(TictactoeGameMode.PVP);
		
		game.play();
	}
}
