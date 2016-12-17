package bg.unisofia.fmi.java.kontr3;

public class Team {
	private String name;
	private String projectName;
	private int points;
	
	public Team(String aName, String aProjectName, int aPoints) {
		this.name = aName;
		this.projectName = aProjectName;
		this.points = aPoints;
	}
	
	public String getName() { return this.name; }
	public String getProjectName() { return this.projectName; }
	public int getPoints() { return this.points; }
	
	public void addPoints(int p) {
		this.points += p;
	}
	
	public boolean equals(Team other) {
		return this.name.equals(other.getName());
	}
	
	@Override
	public String toString() { return getName() + "-" + getProjectName() + "-" + getPoints(); }
}
