package bg.unisofia.fmi.echoserver;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
	public static void main(String args[]) {
		Server server = new Server(4444);
		new Thread(server).start();
		
		Client client = new Client("Pesho");
		new Thread(client).start();
		
		Client client2 = new Client("Gosho");
		new Thread(client2).start();

	}

}