import java.util.Scanner;

enum TictactoeGameMode {
	PVP, PVC, CVP, CVC;
	//First letter corresponds to PlayerO, Third letter corresponds to PlayerX
	//P = player, C = computer
}

enum TictactoePlayer {
	X, O, NEITHER;
}

enum TictactoeGameOutcome {
	WIN, TIE, LOSE;
}

class TictactoePosition {
	public TictactoeGameOutcome outcome;
	public int x, y;
	
	
	public TictactoePosition(TictactoeGameOutcome outcome, int x, int y) {
		this.outcome = outcome;
		this.x = x;
		this.y = y;
	}
}

class TictactoeGame {
	private final TictactoeGameMode mode;
	private TictactoePlayer[][] board;
	private boolean isFinished;
	private TictactoePlayer activePlayer;
	private TictactoePlayer winner;
	private short totalTurnsLeft;
	
	private char getSymbol(TictactoePlayer player) {
		switch(player) {
			case O:       return 'O';
			case X:       return 'X';
			case NEITHER: return ' ';
			default: return '?'; //ERROR
		}
	}
	
	private TictactoePlayer getOtherPlayer(TictactoePlayer player) {
		if(player == TictactoePlayer.NEITHER) {
			System.out.println("Error: Invalid player passed to getOtherPlayer()");
			return player;
		}
		
		if(player == TictactoePlayer.O)
			return TictactoePlayer.X;
		else
			return TictactoePlayer.O;
	}
	
	/*
	private boolean isFull(TictactoePlayer[][] board) {
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				if(board[i][j] == TictactoePlayer.NEITHER)
					return false;
		return true;
	}
	*/
	
	public TictactoeGame(TictactoeGameMode mode) { //TODO Overload constructor without parameters
		this.mode = mode;
		this.board = new TictactoePlayer[3][3];
		this.totalTurnsLeft = 9;

		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				this.board[i][j] = TictactoePlayer.NEITHER;
		
		this.isFinished = false;
		this.activePlayer = TictactoePlayer.O;
		this.winner = TictactoePlayer.NEITHER;
	}
	
	public void printGameMode() {
		switch(mode) {
			case PVP:
				System.out.println("You are now playing in Player vs Player mode");
				break;
			case PVC:
				System.out.println("You are now playing in Player vs Computer mode");
				break;
			case CVP:
				System.out.println("You are now playing in Computer vs Player mode");
				break;
			case CVC:
				System.out.println("You are now playing in Computer vs Computer mode");
				break;
		}			
	}
		
	public void printBoard() {
		System.out.print("\n");
		
		for(int i=0; i<9; i++) {
			if(i % 3 == 0) {
				System.out.println("   |   |   ");
			}
			
			else if(i % 3 == 1) {
				System.out.println(" " + getSymbol(board[i/3][0]) +
						         " | " + getSymbol(board[i/3][1]) +
						         " | " + getSymbol(board[i/3][2]));
			}
		
			else if(i % 3 == 2 ) {
				if(i == 8) {
					System.out.println("   |   |   ");
				} else {
					System.out.println("___|___|___");
				}
			}
		}

		System.out.print("\n");
	}
	
	public boolean isFinished() {
		return this.isFinished;
	}
	
	public TictactoePlayer getActivePlayer() {
		return this.activePlayer;
	}
	
	public TictactoePlayer getWinner() {
		return this.winner;
	}
	
	/*
	//what is the best turn for given board if playing as given player
	private TictactoePosition getComputerTurn(TictactoePlayer[][] board, TictactoePlayer player) {
		//unexpected behavior if a full board is given?
		
		if(isFull(board))
			return new TictactoePosition(TictactoeGameOutcome.TIE, -1, -1); //only first arg matters here
		
		if(threeInARowFound(board, player))
			return new TictactoePosition(TictactoeGameOutcome.WIN, -1, -1); //only first arg matters here
		
		if(threeInARowFound(board, getOtherPlayer(player)))
			return new TictactoePosition(TictactoeGameOutcome.LOSE, -1, -1); //only first arg matters here
		
		TictactoePosition win, tie, lose;
		win = tie = lose = null;
		
		outerLoop:
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				if(board[i][j] == TictactoePlayer.NEITHER) {
					board[i][j] = player;
					
					if(getComputerTurn(board, getOtherPlayer(player)).outcome == TictactoeGameOutcome.LOSE) {
						win = new TictactoePosition(TictactoeGameOutcome.WIN, i ,j);
						break outerLoop;
					}
					
					if(getComputerTurn(board, getOtherPlayer(player)).outcome == TictactoeGameOutcome.TIE) {
						tie = new TictactoePosition(TictactoeGameOutcome.TIE, i ,j);
					}
					
					if(getComputerTurn(board, getOtherPlayer(player)).outcome == TictactoeGameOutcome.WIN) {
						lose = new TictactoePosition(TictactoeGameOutcome.LOSE, i ,j);
					}					
					
					board[i][j] = TictactoePlayer.NEITHER;
				}
		
		if(win != null)
			return win;
		
		if(tie != null)
			return tie;
		
		if(lose != null)
			return lose;
		
		System.out.println("Error: No appropriate TictactoePosition found for computer's turn");
		return new TictactoePosition(TictactoeGameOutcome.LOSE, -1, -1);
	}
	*/
	
	public void getPlayerTurn() {
		if(totalTurnsLeft == 0) {
			System.out.println("Error: Can not getPlayerTurn(), no more turns left");
			return;
		}
		
		if(activePlayer == TictactoePlayer.NEITHER) {
			System.out.println("Error: invalid activePlayer");
			return;
		}
		
		System.out.println("Player " + getSymbol(activePlayer) + ", your turn: ");
		
		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		
		//do we need to explicitly check if x,y are actually numbers?
		while(x<0 || y<0 || x>3 || y>3 || board[x][y] != TictactoePlayer.NEITHER) {
			System.out.println("Error: Invalid input");
			System.out.println("Player " + getSymbol(activePlayer) + ", your turn: ");
			x = scanner.nextInt();
			y = scanner.nextInt();
		}
		
		this.board[x][y] = activePlayer;
		this.totalTurnsLeft--;
		
		if(threeInARowFound(this.board, activePlayer)) {
			this.isFinished = true;
			this.winner = activePlayer;
			return;
		}
		
		if(totalTurnsLeft == 0) {
			this.isFinished = true;
			this.winner = TictactoePlayer.NEITHER;
			return;
		}
		
		activePlayer = getOtherPlayer(activePlayer);
	}
	
	//checks for the three in a row in a parameter board and not this.board for the purpose of the AI strategy
	private boolean threeInARowFound(TictactoePlayer[][] board, TictactoePlayer player) {
		//check by lines
		for(int i=0; i<3; i++) {
			if(board[i][0] == player && board[i][1] == player && board[i][2] == player)
				return true;
		}
		
		//check by columns
		for(int j=0; j<3; j++) {
			if(board[0][j] == player && board[1][j] == player && board[2][j] == player)
				return true;
		}
		
		if(board[0][0] == player && board[1][1] == player && board[2][2] == player)
			return true;
		
		if(board[2][0] == player && board[1][1] == player && board[0][2] == player)
			return true;
		
		return false;				
	}
	
	//TODO
	private boolean canHaveWinner(TictactoePlayer[][] board) {
		return true;
	}
	
	public void printWinner() {
		if(!this.isFinished()) {
			System.out.println("Error: Can not printWinner(), game not finished yet");
			return;
		}
		
		if(this.winner == TictactoePlayer.NEITHER)
			System.out.println("The game was a tie!");
		
		else
			System.out.println("Player " + getSymbol(winner) + " won the game!");
	}
	
	public void play() {
		System.out.println("Welcome to Tictactoe");
		
		printGameMode();
		
		if(mode == TictactoeGameMode.PVP) {
			
			System.out.println("Instructions: input move as 'x y', 0<=x,y<=3 where x,y correspond to line and column of the board");
			
			while(!this.isFinished()) {
				printBoard();
				getPlayerTurn();
			}
			
			printBoard();			
			printWinner();
		}
		
		else {
			System.out.println("Chosen GameMode not yet supported.");
		}
	}
}

public class hw1_Tictactoe {

	public static void main(String[] args) {
		
		TictactoeGame game = new TictactoeGame(TictactoeGameMode.PVP);
		
		game.play();
	}

}
