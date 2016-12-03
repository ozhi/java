
enum CarType {
	SEDAN, HATCHBACK, COMBI, CABRIO;
	
	public String toString() {
		switch(this) {
		case SEDAN: return "Sedan";
		case HATCHBACK: return "Hatchback";
		case COMBI: return "Combi";
		default: return "Cabrio";
		}
	}
}

public class Car extends Vehicle {
	private CarType type;
	private int passengers;
	
	public Car(int year, String brand, CarType type, int passengers) throws IllegalArgumentException {
		super(year, brand);
		this.type = type;
		
		if(passengers < 1 || passengers > 10)
			throw new IllegalArgumentException();
		
		this.passengers = passengers;
	}
		
	public int getPrice() {
		return 500 + 50 * passengers;
	}
	
	public CarType getType() { return type; }
	public int getPassengers() { return passengers; }
	
	public String toString() {
		String result =
				super.toString() + 
				"   Type:" + type.toString() + 
				"   Passengers: " + passengers;
		return result;
	}

}
