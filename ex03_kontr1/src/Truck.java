
public class Truck extends Vehicle {
	private int load;
	
	public Truck(int year, String brand, int load) throws IllegalArgumentException {
		super(year, brand);
		
		if(load <= 0)
			throw new IllegalArgumentException();
		
		this.load = load;
	}
	
	public int getLoad() { return load; }
	
	public int getPrice() {
		return 700 + 100 * load;
	}
	
	public String toString() {
		String result = 
				super.toString() + 
				"   Load: " + load;
		return result;
	}

}
