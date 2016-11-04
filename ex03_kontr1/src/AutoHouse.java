
import java.time.LocalDate;
import java.util.*;

public class AutoHouse {
	private static final int SIZE = 100;
	private int vehiclesCount;
	private Vehicle[] parking;
	
	public AutoHouse() {
		this.parking = new Vehicle[SIZE];
		this.vehiclesCount = 0;
	}
	
	public boolean isFull() {
		return SIZE == vehiclesCount;
	}
	
	public void add(Vehicle v) throws IllegalArgumentException {
		if(this.isFull()) {
			throw new IllegalArgumentException();
			/*not the correct type of exception?
				Arguments are valid but add(Vehicle) should not be called when the AutoHouse is full
			*/	
		}
		
		parking[vehiclesCount++] = v;
	}
	
	public String toString() {
		Arrays.sort(parking); //problems in sorting
		
		String result = "";
		for(int i = 0; i < vehiclesCount /*parking.length*/; i++) {
			result += parking[i].toString() + "\n";
		}
		
		return result;
	}
	
	public void printInfo() {
		System.out.println(this.toString());
	}

	public void printFreeOn(LocalDate d) {
		String result = "";
		for(int i = 0; i < vehiclesCount /*parking.length*/; i++)
			if(d.isBefore(parking[i].getFrom()) || d.isAfter(parking[0].getTo()))
				result += parking[i].toString();
		
		System.out.println(result);
	}
}
