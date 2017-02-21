package bg.unisofia.fmi.battleshipsonline;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameServer implements Runnable {
	private int port;
	private List<Game> activeGames;
	//private List<Game> savedGames;
	private Map<String, Socket> clients;
	
	public GameServer(int port) {
		this.port = port;
		
		activeGames = new ArrayList<Game>();
		//savedGames = new ArrayList<Game>();
		clients = new ConcurrentHashMap<String, Socket>(); // choose a good data structure
	}
	
	public List<Game> getActiveGames() { return this.activeGames; }
	
	public boolean createGame(String gameName, String creatorUsername) {
		if(activeGames.stream().noneMatch(game -> game.getName().equals(gameName)))	{
			activeGames.add(new Game(gameName, creatorUsername));
			return true;
		}
		return false;
	}
	
	public boolean deleteGame(String gameName, String creatorUsername) {
		return activeGames.removeIf(game ->
										game.getName().equals(gameName) &&
										game.getCreator().equals(creatorUsername)); // only the creator of a game can delete it
	}
	
	public void addClient(Socket socket, String username) {
		clients.putIfAbsent(username, socket);
	}
	
	public void removeClient(String username) {
		clients.remove(username);
	}
	
	public boolean hasClient(String username) {
		return clients.containsKey(username);
	}
	
	@Override
	public void run() {
		System.out.println("The game server is now running");
		
		try (ServerSocket serverSocket = new ServerSocket(this.port)) {
			while(true) {
				Socket socket = serverSocket.accept();
				
				ServerProtocol protocol = new ServerProtocol(this, socket);
				
				new Thread(new ClientHandler(this, socket, protocol)).start();
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		System.out.println("The game server is no longer running");
	}
}
