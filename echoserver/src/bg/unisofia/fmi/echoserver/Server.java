package bg.unisofia.fmi.echoserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.TransactionRequiredException;

public class Server implements Runnable {
	private int port;
	private Map<String, Socket> connections;
	
	public Server(int port) {
		this.port = port;
		this.connections = new HashMap<>();
	}
	
	public void startServer() {
		ServerSocket socket;
		try {
			socket = new ServerSocket(port);
			while(true) {
				Socket client = socket.accept();
				new Thread(new Connection(client, this)).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Map<String, Socket> getConnections() {
		return connections;
	}
	
	public void closeSocket(String name) {
		if (connections.containsKey(name)) {
			try {
				Socket socket = connections.get(name);
				socket.close();
				connections.remove(name);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		startServer();
	}

}