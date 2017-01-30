package bg.unisofia.fmi.battleshipsonline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// remove unnecessary imports afterwards

import java.util.ArrayList;
import java.util.List;

public class GameServer implements Runnable {
	private int port;
	private List<Game> activeGames;
	private List<Game> savedGames;
	
	private int availableId; // the game server gives out consequent numbers as unique ids
	
	public GameServer(int port) {
		this.port = port;
		
		activeGames = new ArrayList<Game>();
		savedGames = new ArrayList<Game>();
		availableId = 1000;
	}
	
	public Game createGame() {
		Game game = new Game(availableId++);
		System.out.println("GameServer.createGame() called");		
		return game;
	}
	
	public void printAllGames() {
		System.out.println("All games on this server:");
	}
	
	class ClientHandler implements Runnable {
		private Socket socket;
		
		public ClientHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {

			try(
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter pw = new PrintWriter(socket.getOutputStream())
			) {
			
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println("Server: " + "Reqest received: " + line);
	
					pw.println("E kvo '" + line + "' we?!");
					pw.flush();
					
					System.out.println("Server: " + "Response sent back");
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		System.out.println("GameServer run() - started");
		
		int clientCount = 0;
		
		try (ServerSocket serverSocket = new ServerSocket(this.port)) {
			while(true) {
				Socket socket = serverSocket.accept();
				clientCount++;
				System.out.println("Server: Client#" + clientCount + " accepted");
				ClientHandler clientHandler = new ClientHandler(socket);
				new Thread(clientHandler).start();
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		System.out.println("GameServer run() - finished");
	}
}
