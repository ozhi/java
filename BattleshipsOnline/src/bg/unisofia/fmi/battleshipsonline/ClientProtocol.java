package bg.unisofia.fmi.battleshipsonline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientProtocol {
	// client state
	// replace with an enum class
	private static final int MAINMENU = 7;
	private static final int INGAME = 8;
	
	private int clientState;
	
	private String username;
	private Socket socket;
	private PrintWriter clientRequests;
	private BufferedReader serverResponses;
	
	public ClientProtocol(String username, Socket socket, PrintWriter clientRequests, BufferedReader serverResponses) {
		this.username = username;
		this.socket = socket;
		this.clientRequests = clientRequests;
		this.serverResponses = serverResponses;
		
		clientState = MAINMENU;
	}

	private void printMainMenu() {
		System.out.println(
			"Available commands:" + "\n"
					+ "\t" + "list-games"              + "\n"
					+ "\t" + "create-game <game-name>" + "\n"
					+ "\t" + "join-game <game-name>" + "\n" // TODO add "join-game" to join random game
					/*
					+ "\t" + "saved-games"             + "\n"
					+ "\t" + "load-game <game-name>"   + "\n"
					*/
					+ "\t" + "delete-game <game-name>" + "\n"
		);
	}
	
	private void printGameMenu() {
		System.out.println(
			"Available commands:" + "\n"
					+ "\t" + "<x> <y>" + "\n"
					+ "\t" + "quit"    + "\n"
		);
	}
	
	public boolean verifyUsername() {
		this.clientRequests.println("username-verification" + " " + this.username);
		this.clientRequests.flush();
		
		try {
			String response = this.serverResponses.readLine();
		
			switch(response) {
				case "username-verification-success":
					return true;
				case "username-verification-fail":
					return false;
				default:
					System.out.println("Unknown server response: " + response);
					return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	public void processUserInput(String clientInput) throws IOException {
	
		String[] command = clientInput.split("\\s+");
		
		if(command[0].equals("help") && command.length == 1) {
			printMainMenu();
			return;
		}
			
		if(command[0].equals("list-games") && command.length == 1) {
			clientRequests.println("list-games");
			clientRequests.flush();
			String gamesList = serverResponses.readLine(); // if we want info passed with more than one line => should use a stream of objects, not string lines
			System.out.println(gamesList);
			return;
		}
		
		if(command[0].equals("create-game") && command.length == 2) {
			clientRequests.println("create-game" + " " + command[1]);
			clientRequests.flush();
			
			String response = serverResponses.readLine();
			
			switch(response) {
				case "create-game-success":
					System.out.println("Game " + command[1] + " created successfully");
					break;

				case "create-game-fail":
					System.out.println("Game " + command[1] + " was not created successfully. It probably already eixsts");
					break;

				default:
					System.out.println("Error: unknown server response: " + response);
			}
			return;
		}
		
		if(command[0].equals("join-game") && command.length == 2) {
			clientRequests.println("join-game" + " " + command[1]);
			clientRequests.flush();
			
			String response = serverResponses.readLine();
			if(response.equals("success"))
				System.out.println("Game " + command[1] + " created successfully");
			else if(response.equals("fail"))
				System.out.println("Game was not created successfully");
			else
				System.out.println("Error: unknown server response: " + response);
			
			switch(response) {
				case "join-game-success":
					System.out.println("Game " + command[1] + " joined successfully");
					break;
	
				case "join-game-fail":
					System.out.println("Game " + command[1] + " was not joined successfully. It is probably full or does not exist");
					break;
	
				default:
					System.out.println("Error: unknown server response: " + response);
			}
			return;
		}
		
		if(command[0].equals("delete-game") && command.length == 2) {
			clientRequests.println("delete-game" + " " + command[1]);
			clientRequests.flush();
			
			String response = serverResponses.readLine();
			switch(response) {
				case "delete-game-success":
					System.out.println("Game " + command[1] + " deleted successfully");
					break;
				
				case "delete-game-fail":
					System.out.println("Game " + command[1] + " not deleted successfully");
					break;
				
				default:
					System.out.println("Unknown server response: " + response);			
			}
			return;
		}
		
		System.out.println("Command not recognised, type 'help' to see available commands");			
	}
}
