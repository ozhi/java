package bg.unisofia.fmi.snowboard;

import java.util.concurrent.CountDownLatch;

public class Snowboarder implements Runnable {
	String name;
	Counter counter;
	CountDownLatch countDownLatch;
	
	public Snowboarder(String name, Counter counter, CountDownLatch countDownLatch) {
		this.name = name;
		this.counter = counter;
		this.countDownLatch = countDownLatch;
	}
	
	@Override
	public void run() {
		try {
			counter.sellCard();
		} catch (IllegalStateException e) {
			System.out.println("The counter is out of cards!");
			e.printStackTrace();
		}
		
		try {
			/// go snowboarding
			Thread.sleep(50);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		counter.returnCard();
		countDownLatch.countDown();
	}
}
