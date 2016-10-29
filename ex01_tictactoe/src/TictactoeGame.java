
import java.util.Scanner;

class TictactoeGame {
	private final TictactoeGameMode mode;
	private TictactoeBoard board;
	private boolean isFinished;
	private TictactoePlayer activePlayer;
	private TictactoePlayer winner;
	
	public TictactoeGame()                       { this(3,         TictactoeGameMode.PVP); }
	public TictactoeGame(int boardSize)          { this(boardSize, TictactoeGameMode.PVP); }
	public TictactoeGame(TictactoeGameMode mode) { this(3,         mode); }
		
	public TictactoeGame(int boardSize, TictactoeGameMode mode) {
		this.mode = mode;
		this.board = new TictactoeBoard(boardSize);
		
		this.isFinished = false;
		this.activePlayer = TictactoePlayer.O;
		this.winner = TictactoePlayer.NEITHER;
	}
		
	public boolean isFinished() { return this.isFinished; }
	
	public TictactoePlayer getActivePlayer() { return this.activePlayer; }
		
	public void printOutcome() {
		switch(winner) {
			case NEITHER:
				System.out.println("The game was a tie!");
				break;

			case O:
				System.out.println("Player O won the game, congratulations!");
				break;
		
			case X:
				System.out.println("Player X won the game, congratulations!");
				break;
			
			default:
				System.out.println("Error: invalid value of TictactoeGame.winner!");
				//throw exception
				break;
		}		
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
		
	public void processNextTurn() {
		if(board.isFull()) {
			System.out.println("Error: Can not processNextTurn(), board is full");
			return;
		}
		
		if(activePlayer == TictactoePlayer.NEITHER) {
			System.out.println("Error: invalid activePlayer");
			return; //exception should be thrown instead of returning
		}
		
		boolean isFirstInputTry = true;
		Scanner scanner = new Scanner(System.in);
		int x, y;
		
		do {
			if(!isFirstInputTry)
				System.out.println("Error: Invalid input, try again");
			
			isFirstInputTry = false;
			
			System.out.println("Player " + TictactoePlayer.getSymbol(activePlayer) + ", your turn: ");
			x = scanner.nextInt();
			y = scanner.nextInt();
			
		}while(x<0 || y<0 || x>3 || y>3 || board.isOccupied(x, y));
				
		board.markPositionOccupied(x, y, activePlayer);
		
		if(board.hasWinningComb(activePlayer)) {
			this.isFinished = true;
			this.winner = activePlayer;
			return;
		}
		
		if(board.isFull()) {
			this.isFinished = true;
			this.winner = TictactoePlayer.NEITHER;
			return;
		}
		
		activePlayer = TictactoePlayer.getOtherPlayer(activePlayer);
	}
		
	public void play() {
		System.out.println("Welcome to Tictactoe");
		
		mode.print();
		
		if(mode == TictactoeGameMode.PVP) { //switching n the mode of the game =>? this
			
			System.out.println("Instructions: input move as 'x y', 0<=x,y<=3 where x,y correspond to line and column of the board");
			
			while(!this.isFinished()) {
				board.print();
				processNextTurn();
			}
			
			board.print();			
			printOutcome();
		}
		
		else {
			System.out.println("Error: Chosen game mode not yet supported.");
		}
	}
}
