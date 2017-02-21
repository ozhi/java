package bg.unisofia.fmi.battleshipsonline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler implements Runnable {
	private GameServer gameServer;
	private Socket socket;
	private ServerProtocol protocol;
	
	private String username;
	
	public ClientHandler(GameServer gameServer, Socket socket, ServerProtocol protocol) {
		this.gameServer = gameServer;
		this.socket = socket;
		this.protocol = protocol;
		
		this.username = null; // this.username is set later when the client verifies their username
	}
	
	@Override
	public void run() {

		try(
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream())
		) {
			String clientRequest;
			do {
				clientRequest = reader.readLine();
				
				if(clientRequest != null) {
					writer.println(protocol.processClientRequest(clientRequest));
					writer.flush();
				}
				
			} while(clientRequest != null);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		if(this.username == null)
			System.out.println("A socket with no username has disconnected");
		else {
			System.out.println(this.username + " has disconnected");
			this.gameServer.removeClient(this.username);
		}
	}
}
