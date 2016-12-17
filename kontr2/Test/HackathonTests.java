import java.nio.file.NoSuchFileException;

import org.junit.Test;

import bg.unisofia.fmi.java.kontr3.*;

public class HackathonTests {

	
	@Test
	public void addTeamTest() {
		Hackathon h = new Hackathon();
		Team t1 = new Team("myteam", "project", 23);
		Team t2 = new Team("other", "google", 1);
		
		h.addTeam(t1);
		h.addTeam(t2);
		h.vote("myteam", 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addDuplicate() {
		Hackathon h = new Hackathon();
		Team t1 = new Team("RAG", "project1", 23);
		Team t2 = new Team("RAG", "project2", 10);
		
		
		h.addTeam(t1);
		h.addTeam(t1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void voteNonExistent() {
		Hackathon h = new Hackathon();
		Team t1 = new Team("team1", "project1", 23);
		Team t2 = new Team("team2", "project2", 10);
		
		h.vote("team11", 11);
	}	
}
