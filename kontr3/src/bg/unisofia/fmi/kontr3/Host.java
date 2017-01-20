package bg.unisofia.fmi.kontr3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class Host implements Runnable {
	private String hostname;
	private final int port;
	private String response;
	private int delay;
	
	private CountDownLatch countDownLatch;
	
	public Host(String hostname, int port, CountDownLatch countDownLatch) {
		this.hostname = hostname;
		this.port = port;
		this.countDownLatch  = countDownLatch;
		
		this.delay = 0;
		this.response = "no-response";
	}
	
	public String getHostname() { return this.hostname; }
	
	public void setResponse(String response) { this.response = response; }
	public String getResponse() { return this.response; }
	
	public void setDelay(int delay) { this.delay = delay; }
	public int getDelay() { return this.delay; }
	
	@Override
	public String toString() {
		return "Host: [Hostname:" + hostname + ", delay: " + delay + "]";		
	}
	
	public void run() {
		try{
			Socket client = new Socket(this.hostname, this.port);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream())); 
			
			writer.write("GET / HTTP/1.1\r\n");
			writer.write("Host: " + this.hostname + "\r\n");
			writer.write("\r\n");
			writer.flush();

			this.setResponse(reader.readLine());
			
			this.setDelay(1400); //TODO: measure actual time
			
			client.close();
			this.countDownLatch.countDown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
