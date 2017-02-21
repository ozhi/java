package bg.unisofia.fmi.battleshipsonline;

public class Game {
	private String name;
	private String creator;
	
	private final int boardSize = 10;
	
	private int board[][];
	
	private Ship player1Ships[];
	private Ship player2Ships[];
	
	private boolean hasStarted;
	private int activePlayers;

	public Game(String name, String creator) {
		this.name = name;
		this.creator = creator;
		
		board = new int[boardSize][boardSize];
		
		initializeShips(player1Ships);
		initializeShips(player2Ships);
		
		this.hasStarted = false;
		this.activePlayers = 0;
	}
	
	@Override
	public String toString() {
		return "[ name=" + name + " | creator=" + creator + " | " + activePlayers + "/2 players" + "]"; 
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
	
	public String getCreator() { return this.creator; }
	
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
}

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

