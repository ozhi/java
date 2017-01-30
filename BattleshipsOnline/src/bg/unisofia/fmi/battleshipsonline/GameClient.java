package bg.unisofia.fmi.battleshipsonline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient implements Runnable {
	private String username;
	private InetAddress address;
	private int port;
	
	public GameClient(String username, InetAddress address, int port) {
		this.username = username;
		this.address = address;
		this.port = port;
	}
	
	@Override
	public void run() {
		//System.out.println("GameClient run() - started");

		try (
			Socket socket = new Socket(this.address, this.port);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			
			BufferedReader stdIn =
				new BufferedReader(
						new InputStreamReader(System.in))
		) {
			String clientInput;
			do {
				System.out.print(this.username + "> ");
				clientInput = stdIn.readLine();
				
				switch(clientInput) {
					case "cmd1":
					case "cmd2":
					case "cmd3":
					case "cmd4":
						pw.println(clientInput);
						pw.flush();
						String response = br.readLine();
						System.out.println("Client: " + "Response received: " + response);
						break;
					
					default:
						System.out.println("Error: Command not recognised!");
						break;
				}
				
			} while(clientInput != null);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//System.out.println("GameClient run() - finished");
	}
}
