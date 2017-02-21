package bg.unisofia.fmi.battleshipsonline;

public class RunServer {

	public static void main(String[] args) {
		//user args[0] as port
		new GameServer(4444).run();
	}
}
