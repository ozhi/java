package bg.unisofia.fmi.battleshipsonline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient implements Runnable {
	private InetAddress address;
	private int port;
	
	private String username;
	
	private ClientProtocol protocol;
	
	public GameClient(String username, InetAddress address, int port) {
		this.username = username;
		this.address = address;
		this.port = port;
	}

	@Override
	public void run() {
		
		try (
			Socket socket = new Socket(this.address, this.port);
			PrintWriter clientRequests = new PrintWriter(socket.getOutputStream());
			BufferedReader serverResponses = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
		) {
			this.protocol = new ClientProtocol(this.username, socket, clientRequests, serverResponses);
			
			if(!this.protocol.verifyUsername()) {
				System.out.println("You failed to connect as " + this.username + ". This username is probably in use");
			}
			
			else {	
				System.out.println("You are now connected as " + this.username);
				
				String userInput;
				do {
					System.out.print(this.username + "> ");
					userInput = stdIn.readLine();
					
					protocol.processUserInput(userInput); // throws IOException
					
				} while(userInput != null);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
