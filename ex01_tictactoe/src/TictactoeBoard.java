
import java.util.*;

class TictactoeBoard {
	private final int SIZE;
	private TictactoePlayer[][] board;
	private int freeCells;
	
	public TictactoeBoard() {
		this(3);
	}
	
	public TictactoeBoard(int size) {
		this.SIZE = size;
		
		this.board = new TictactoePlayer[SIZE][SIZE];
		
		for(int i = 0; i < SIZE; i++)
			for(int j = 0; j < SIZE; j++)
				board[i][j] = TictactoePlayer.NEITHER;
		
		//java.util.Arrays.fill(board, TictactoePlayer.NEITHER);
		
		this.freeCells = SIZE * SIZE;
	}
	
	public boolean isOccupied(int x, int y) {
		if(x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
			System.out.println("Error: Invalid position passed to markPositionOccupied()");
			return false; //exception should be thrown instead of returning
		}
		
		return board[x][y] != TictactoePlayer.NEITHER;
	}
	
	public void markPositionOccupied(int x, int y, TictactoePlayer player) {
		if(x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
			System.out.println("Error: Invalid position passed to markPositionOccupied()");
			return; //exception should be thrown instead of returning
		}
		
		if(board[x][y] != TictactoePlayer.NEITHER) {
			System.out.println("Error: Invalid position passed to markPositionOccupied()");
			return; //exception should be thrown instead of returning
		}
		
		this.board[x][y] = player;
		this.freeCells--;
	}
	
	public boolean isFull() {
		return (freeCells == 0);
	}
	
	public boolean hasWinningComb(TictactoePlayer player) { //perhaps this func is too long and should be separated?
		//a winning comb is a full row/col/diag of the player's symbols
		//traditionally called "three in a row"
		
		boolean hasMissingCell;
		
		//check by rows and cols
		for(int i = 0; i < SIZE; i++) {
			
			//check i-th row
			hasMissingCell = false;
			for(int j = 0; j < SIZE; j++) {
				if(board[i][j] != player) {
					hasMissingCell = true;
					break;
				}
			}
			
			if(!hasMissingCell) {
				return true;
			}
			
			//check i-th col
			hasMissingCell = false;
			for(int j = 0; j < SIZE; j++) {
				if(board[j][i] != player) {
					hasMissingCell = true;
					break;
				}
			}
			
			if(!hasMissingCell) {
				return true;
			}
		}
		
		//check by diag 1
		hasMissingCell = false;
		for(int i = 0; i < SIZE; i++) {
			if(board[i][i] != player) {
				hasMissingCell = true;
				break;
			}
		}
		
		if(!hasMissingCell) {
			return true;
		}
		
		//check by diag 2
		hasMissingCell = false;
		for(int i = 0; i < SIZE; i++) {
			if(board[i][SIZE -i -1] != player) {
				hasMissingCell = true;
				break;
			}
		}
		
		if(!hasMissingCell) {
			return true;
		}
		
		return false;
	}
	
	public void print() {
		System.out.print("\n");
		
		for(int i = 0; i < 3 * SIZE; i++) {
			
			switch(i % 3) {
				case 0:
					System.out.print("   ");
					
					for(int j = 1; j < SIZE; j++) {
						System.out.print("|   ");
					}
					
					System.out.print("\n");
					break;
				
				case 1:
					System.out.print(" " + TictactoePlayer.getSymbol(board[i/3][0]) + " ");
					
					for(int j = 1; j < SIZE; j++) {
						System.out.print("| " + TictactoePlayer.getSymbol(board[i/3][j]) + " ");
					}
					
					System.out.print("\n");
					break;
					
				case 2:
					char blank = ( (i == 3 * SIZE - 1) ? ' ' : '_' );
	
					System.out.print("" + blank + blank + blank);
					
					for(int j = 1; j < SIZE; j++) {
						System.out.print("|" + blank + blank + blank);
					}
					
					System.out.print("\n");
					break;
			}
		}

		System.out.print("\n");
	}
}