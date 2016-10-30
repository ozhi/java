
enum TictactoeGameMode {
	PVP, PVC, CVP, CVC;
	/*  First letter corresponds to PlayerO, Third letter corresponds to PlayerX,
		whereP = player, C = computer
	*/
	
	/* Purpose of separate PVC and CVP modes is to choose who plays first
	 */
	
	public String toString() {
		switch(this) {
			case PVP: return "Player vs Player";
			case PVC: return "Player vs Computer";
			case CVP: return "Computer vs Player";
			case CVC: return "Computer vs Computer";
			default: return "Error: invalid TictactoeGameMode value"; //added because switch requires a default case
		}
	}
}