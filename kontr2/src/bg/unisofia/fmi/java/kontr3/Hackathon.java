package bg.unisofia.fmi.java.kontr3;

import java.nio.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;

public class Hackathon {

	ArrayList<Team> teams;
	
	public Hackathon() { teams = new ArrayList<Team>(); }
	
	public void addTeam(Team team) throws IllegalArgumentException {
		for(Team t : teams) {
			if(t.getName().equals(team.getName())) {
				throw new IllegalArgumentException();
			}
		}
		
		teams.add(team);		
	}
	
	public void vote(String teamName, int addPoints) throws IllegalArgumentException {
		boolean teamFound = false;
		
		for(Team t : teams) {
			if(t.getName().equals(teamName)) {
				t.addPoints(addPoints);
				teamFound = true;
				break;
			}
		}
		
		if(!teamFound) {
			throw new IllegalArgumentException();
		}
	}
		
	public List<Team> getStandings() {
		teams.sort(new TeamComparator());
		return teams;
	}
	
	public void printStandings(String filePath) throws NoSuchFileException {
		Path path = Paths.get(filePath);
		
		if(!Files.exists(path))
			throw new NoSuchFileException(path.toString());
		
		try(PrintStream ps = new PrintStream(path.toFile())) {
			
			int line = 1;
			List<Team> standings = getStandings();
			for(Team t: standings) {
				ps.print(line + t.toString());				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("main()");
		
		Hackathon h = new Hackathon();
		Team t1 = new Team("myteam", "project", 23);
		Team t2 = new Team("other", "google", 1);
		
		h.addTeam(t1);
		h.addTeam(t2);
		
		try {		
			h.printStandings("java.kontr3.standings.txt"); // pass a path, not a filename
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
