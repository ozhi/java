package bg.unisofia.fmi.battleshipsonline;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) {
		//System.out.println("main() - started");
		
		int port = 4442; // the port should not be chosen by the user?
		int clientsCount = 1;
		
		new Thread(new GameServer(port)).start();
		
		try {
			InetAddress localHostAddress = InetAddress.getLocalHost();
		
			for(int i = 1; i <= clientsCount; i++)
				new Thread(new GameClient("player" + i, localHostAddress, port)).start();
		
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//System.out.println("main() - finished");
	}
}
