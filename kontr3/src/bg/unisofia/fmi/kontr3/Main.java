package bg.unisofia.fmi.kontr3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Main {

	private final static int port = 80;
	private static List<Host> hosts = new ArrayList<>();
	
	public static void addHost(String hostname, CountDownLatch countDownLatch) {
		hosts.add(new Host(hostname, port, countDownLatch));
	}

	public static void main(String[] args) {
		System.out.println("Hello!");

		int hostsCount = -1;
		try {
			hostsCount = hostsCount = (int) Files.lines(Paths.get("./resources/hosts.txt")).count();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
    	System.out.println("Hosts counted.");
		
		//CountDownLatch countDownLatch = new CountDownLatch(hostsCount); //some hosts do not respond maybe?
		CountDownLatch countDownLatch = new CountDownLatch(4); //for testing purposes, do not wait for all reponses
		
    	try {
    			Files.lines(Paths.get("./resources/hosts.txt"))
				.forEach(line -> addHost(line, countDownLatch));
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	System.out.println("Hosts loaded.");

		ExecutorService executor = Executors.newFixedThreadPool(4);
		for(int i = 0; i < hosts.size(); i++) {
			executor.execute(hosts.get(i));
		}
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("All hosts have responded.");
		executor.shutdown();
		
		long c1 = hosts.stream().filter(host -> host.getHostname().contains(".org")).count();
		System.out.println("Hosts containing .org: " + c1);
		
		long c2 = hosts.stream().filter(host -> host.getDelay() < 2000).count();
		System.out.println("Hosts with response time <2s: " + c2);
		
		Optional<Host> fastest= hosts.stream().min((host1, host2) -> host1.getDelay() - host2.getDelay()); // no need to sort all of them
		fastest.ifPresent(host -> System.out.println("Host with fastest response: " + host));
		
		System.out.println("Sorted by delay: ");
		Stream<Host> sortedByDelay = hosts.stream().sorted((host1, host2) -> host1.getDelay() - host2.getDelay());
		sortedByDelay.forEach(System.out::println);
		
		System.out.println("Goodbye!");
	}

}
