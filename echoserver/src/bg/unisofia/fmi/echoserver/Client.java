package bg.unisofia.fmi.echoserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Runnable {	
	private String username;
	public Client(String username) {
		this.username = username;
	}
	
	public void connect(InetAddress address, int port) {

		System.out.println("CONNECTIN");
		try {
			
			Socket socket = new Socket(address, port);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			Scanner scanner = new Scanner(System.in);
			String line;
			String inputLine = "";
			writer.write(username);
			writer.newLine();
			writer.flush();
			while (!(inputLine.equals("quit"))){
				line = scanner.nextLine();
				writer.write(line);
				writer.newLine();
				writer.flush();
				
				inputLine = reader.readLine();
				System.out.println(inputLine);
			}
			socket.close();
//			scanner.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		try {
			connect(InetAddress.getLocalHost(), 4444);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getName(){
		return username;
	}

}
