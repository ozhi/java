package bg.unisofia.fmi.snowboard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Counter {
	static int cardsAvailable;
	int money;
	static final int cardPrice = 50;
	static final int deposit = 1;
	
	public Counter() {
		cardsAvailable = 1_000;
		money = 0;
	}
	
	public synchronized void sellCard() throws IllegalStateException {
		while(cardsAvailable == 0)
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		if(cardsAvailable == 0)
			throw new IllegalStateException();
		
		cardsAvailable--;
		money += cardPrice + deposit;
	}
	
	public synchronized void returnCard() {
		money -= deposit;
		cardsAvailable++;
		
		this.notify(); // we usually use notifyAll in other scenarios
	}
	
	public static void main(String[] args) {
		System.out.println("Start of the snowbording day");		
		int n = 5_000;
		
		Counter counter = new Counter();
		int initialCardsAvailable = counter.cardsAvailable;
		
		//List<Thread> clients = new ArrayList<Thread> ();
		CountDownLatch countDownLatch = new CountDownLatch(n);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i = 0; i < n; i++) {
			/*Thread client = new Thread(new Snowboarder("Dude " + i, counter, countDownLatch));
			clients.add(client);
			client.start();
			*/
			executor.execute(new Snowboarder("Dude " + i, counter, countDownLatch));
		}
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		executor.shutdown();
		
		System.out.println( cardsAvailable == initialCardsAvailable ? "All cards returned" : "Not all cards returned" );
		
		System.out.println("money: " + counter.money);
		System.out.println("cardsAvailable: " + cardsAvailable);
		
		System.out.println("End of the snowboarding day");
	}
}
