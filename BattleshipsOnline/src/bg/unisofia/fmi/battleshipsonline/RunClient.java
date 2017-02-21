package bg.unisofia.fmi.battleshipsonline;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class RunClient {

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Please provide exactly one argument - username.");
			return;
		}

		String username = args[0];
		
		//TODO manage username correctness check better
		if(!username.matches("\\w{4,10}")) { // between 4 and 10 word characters (a-zA-z_0-9)
			System.out.println("Useranme must be of length [4;10], containing only word characters (a-zA-z_0-9)");
			return;
		}
		
		try {
			InetAddress localHostAddress = InetAddress.getLocalHost();
			new GameClient(username, localHostAddress, 4444).run(); // the port should not be chosen by the user?
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
	}
}
