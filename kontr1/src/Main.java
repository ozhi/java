
public class Main {

	public static void main(String[] args) {
		AutoHouse myHouse = new AutoHouse();
	
		Car car1 = new Car(2003, "BMW", CarType.HATCHBACK, 5);
		Car car2 = new Car(1997, "Reanult", CarType.SEDAN, 5);
		Truck truck1 = new Truck(2005, "TIR", 11);
		Motorcycle bike1 = new Motorcycle(2014, "Yamaha", 200);
		
		//System.out.println(myHouse.isFull());
		
		
		Vehicle[] myVehicles = {car1, bike1, car2, truck1};
		
		car2.makeFree();
		
		for(int i = 0; i < myVehicles.length; i++)
			myHouse.add(myVehicles[i]);
		
		myHouse.printInfo();
	}

}
