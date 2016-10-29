
enum TictactoeGameMode {
	PVP, PVC, CVP, CVC;
	//First letter corresponds to PlayerO, Third letter corresponds to PlayerX
	//P = player, C = computer
	
	public void print() {
		switch(this) {
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
}
