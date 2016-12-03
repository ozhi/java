
import java.time.*;

public abstract class Vehicle implements Comparable<Vehicle> {
	private int year;
	private String brand;
	private boolean isFree;
	private LocalDate from,to;
	
	public Vehicle(int year, String brand) {
		this.year = year;
		this.brand = brand;
		this.from = LocalDate.now();
		this.to = from.plusDays(7);
		this.isFree = false;
	}
	
	public void makeFree() {
		this.isFree = true;
	}
	
	public void occupy(LocalDate from, LocalDate to) throws IllegalArgumentException {
		if(!from.isBefore(to))
			throw new IllegalArgumentException();
		
		this.from = from;
		this.to = to;
		this.isFree = false;
	}
	
	public String toString() {
		String result =
				"   Year: " + year + 
				"   Brand: " + brand + 
				"   IsFree: " + isFree;
		return result;
	}
	
	public abstract int getPrice();
	
	public int compareTo(Vehicle other) {
		if(this.year > other.year)
			return 1;
		
		if(this.year < other.year)
			return -1;
		
		
		if(this.brand.compareTo(other.brand) > 0)
			return 1;
		
		if(this.brand.compareTo(other.brand) < 0)
			return -1;
		
		return 0;		
	}
	
	
	//getters
	public int getYear()       { return year; }
	public String getBrand()   { return brand; }
	public boolean getIsFree() { return isFree; }
	public LocalDate getFrom() { return from; }
	public LocalDate getTo()   { return to; }

	//TODO add setters
	/*
	public int getYear()       { return year; }
	public String getBrand()   { return brand; }
	public boolean getIsFree() { return isFree; }
	public LocalDate getFrom() { return from; }
	public LocalDate getTo()   { return to; }
	*/
}
