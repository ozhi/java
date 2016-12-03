
public class Motorcycle extends Vehicle {
	private int volume;
	
	public Motorcycle(int year, String brand, int volume) throws IllegalArgumentException {
		super(year, brand);
		
		if(volume <= 0)
				throw new IllegalArgumentException();
		
		this.volume = volume;
	}
	
	public int getVolume() { return volume; }

	public int getPrice() {
		return 300 + 5 * volume;
	}
	
	public String toString() {
		String result =
				super.toString() + 
				"   Volume:" + volume;
		return result;
	}
	
}
