
enum TaskStatus {
	INITIAL, IN_PROGRESS, DONE;
	
	public String toString() {
		switch(this) {
			case INITIAL:
				return "INITIAL";
				
			case IN_PROGRESS:
				return "IN_PROGRESS";

			case DONE:
				return "DONE";

			default:
				return "ERROR";
		}
	}
}
