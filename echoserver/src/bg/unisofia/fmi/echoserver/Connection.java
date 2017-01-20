package bg.unisofia.fmi.echoserver;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;

import javax.imageio.ImageIO;

public class Connection implements Runnable {
	private BufferedReader reader;
	private BufferedWriter writer;
	private Socket socket;
	private Server server;

	public Connection(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;
	}
	private void downloadImage(String urlString, String path) {
		Image image = null;
		try {
		    URL url = new URL(urlString);
		    image = ImageIO.read(url);
		    // Create a buffered image with transparency
		    BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		    // Draw the image on to the buffered image
		    Graphics2D bGr = bi.createGraphics();
		    bGr.drawImage(image, 0, 0, null);
		    bGr.dispose();
		    File outputfile = new File(path);
		    ImageIO.write(bi, "jpeg", outputfile);
		} catch (IOException e) {
		}
	}
	
	public void run() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String inputLine = "";
			String username = reader.readLine();
			server.getConnections().put(username, socket);
			while (!(inputLine = reader.readLine()).trim().equals("quit")) {
				inputLine = inputLine.trim();
				if (inputLine.equals("count"))
					inputLine = Integer.toString(server.getConnections().size());
				String[] commands = inputLine.split("\\s+");
				if (commands[0].equals("download")) {
					downloadImage(commands[1], commands[2]);
				}
				writer.write("to " + username + ": " + inputLine);
				writer.newLine();
				writer.flush();
			}
			writer.write("quit");
			writer.newLine();
			writer.flush();
			server.closeSocket(username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	

}