package bg.unisofia.fmi.battleshipsonline;

//is this public/private?
class Ship {
	// should these be private?
	public int length;
	public boolean isHorizontal;
	public char startRow;
	public int startCol;
	
	public Ship(int length) {
		this.length = length;
	}
	
	public Ship(int length, boolean isHorizontal, char startRow, int startCol) {
		this.length       = length;
		this.isHorizontal = isHorizontal;
		this.startRow     = startRow;
		this.startCol     = startCol;
	}
	
}


public class Game {
	private int id;
	private final int boardSize;
	
	private int board[][];
	
	private Ship player1Ships[];
	private Ship player2Ships[];
	
	private boolean hasStarted;
	private int activePlayers;
	
	public Game(int id) {
		this(id, 10);
	}
	
	private void initializeShips(Ship ships[]) {
		if(ships != null) {
			System.out.println("Error: ships already initilized");
			return;
		}
		
		ships = new Ship[10];
		
		ships[0] = new Ship(5);
		
		ships[1] = new Ship(4);
		ships[2] = new Ship(4);
		
		ships[3] = new Ship(3);
		ships[4] = new Ship(3);
		ships[5] = new Ship(3);
		
		ships[6] = new Ship(2);
		ships[7] = new Ship(2);
		ships[8] = new Ship(2);
		ships[9] = new Ship(2);	
	}
	
	public Game(int id, int boardSize) {
		this.id = id;
		this.boardSize = boardSize;
		
		board = new int[boardSize][boardSize];
		
		initializeShips(player1Ships);
		initializeShips(player2Ships);
		
		this.hasStarted = false;
		this.activePlayers = 0;
	}
	
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
}
