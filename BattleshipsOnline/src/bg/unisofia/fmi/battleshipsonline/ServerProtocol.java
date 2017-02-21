package bg.unisofia.fmi.battleshipsonline;

import java.net.Socket;

public class ServerProtocol {
	// client state
	// replace with an enum class
	private static final int MAINMENU = 7;
	private static final int INGAME = 8;
	
	private int clientState;
	
	private String username;
	private Socket socket;
	private GameServer gameServer;
	
	public ServerProtocol(GameServer gameServer, Socket socket) {
		this.gameServer = gameServer;
		this.socket = socket;
		
		clientState = MAINMENU;
	}
	
	/*public void setUsername(String username) {
		this.username = username;
	}*/
	
	public String processClientRequest(String request) {
		String[] command = request.split("\\s+");
		
		if(command[0].equals("username-verification") && command.length == 2) {
			String usernameToTry = command[1];
			
			if (this.gameServer.hasClient(usernameToTry)){
				System.out.println("A client tried to connect with username " + usernameToTry + " but it is in use. Fail.");
				return "username-verification-fail";
			}
			
			else {
				this.gameServer.addClient(socket, usernameToTry);
				this.username = usernameToTry;
				System.out.println("A client has successfully connected with username " + this.username);
				return "username-verification-success";
			}
		}
		
		if(command[0].equals("list-games") && command.length == 1) {
			System.out.println("Processing a list-games request");
			return gameServer.getActiveGames().toString();
		}
		
		if(command[0].equals("create-game") && command.length == 2) {
			System.out.println("Processing a create-game request");
			
			if(gameServer.createGame(command[1], username))
				return "create-game-success";
			else
				return "create-game-fail";
		}
		
		if(command[0].equals("join-game") && command.length == 2) {
			System.out.println("Processing a join-game request");
			
			if(gameServer.createGame(command[1], username))
				return "create-game-success";
			else
				return "create-game-fail";
		}
		
		if(command[0].equals("delete-game") && command.length == 2) {
			System.out.println("Processing a delete-game request");
			
			if(gameServer.deleteGame(command[1], username))
				return "delete-game-success";
			else
				return "delete-game-fail";	
		}
	
		System.out.println("Client request not recognised:");
		
		for(String cmd : command)
			System.out.println("\t" + cmd);

		return "client-request-not-recognised";
	}
}
