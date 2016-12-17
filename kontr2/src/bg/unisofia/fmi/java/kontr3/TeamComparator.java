package bg.unisofia.fmi.java.kontr3;

import java.util.Comparator;

public class TeamComparator implements Comparator<Team> {
	public int compare(Team a, Team b) {
		if(a.getPoints() < b.getPoints())
			return -1;
		if(a.getPoints() > b.getPoints())
			return -1;

		return a.getName().compareTo(b.getName());		
	}
}