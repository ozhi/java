
enum TictactoeSymbol {
	X, O, NEITHER;
	
	public char toChar() {
		switch(this) {
			case O:       return 'O';
			case X:       return 'X';
			case NEITHER: return ' ';
			default: return '?'; //ERROR
		}
	}
	
	public TictactoeSymbol getOtherSymbol() throws IllegalStateException {		
		if(this.equals(O))
			return X;
		
		if(this.equals(X))
			return O;
		
		System.out.println("Error: Illegal state of TictactoeSymbol when getOtherSymbol() is called");
		throw new IllegalArgumentException();
	}
}