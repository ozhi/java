
import java.time.*;

public class Task implements Comparable<Task> {

	private String title;
	private String description;
	private TaskStatus status;
	private int priority;
	private LocalDate dueDate;
	
	public Task(String title, String description, TaskStatus status, int priority, LocalDate dueDate) {
		this.title = title;
		this.description = description;
		this.status = status;
		
		if(priority < 1 || priority > 5) {
			System.out.println("Error: invalid priority value passed to Task()");
			throw new IllegalArgumentException(); //unchecked exception => should not be caught?
		}
			
		this.priority = priority;
		
		this.dueDate = dueDate;		
	}
	
	public TaskStatus getStatus() {
		return this.status;
	}
	
	public LocalDate getDueDate() {
		return this.dueDate;
	}

	public boolean isInProgressAndDueInNextThreeDays() {
		return ( this.status.equals(TaskStatus.IN_PROGRESS) &&
				 this.dueDate.isBefore(LocalDate.now().plusDays(3)) &&
				 this.dueDate.isAfter(LocalDate.now()) );
	}
	
	public String toString() {
		String result =
				"\n" + this.title + "\n" +
				"   Status: " + status.toString() + "\n" +
				"   Priority: " + priority + "\n" +
				"   Due: " + dueDate.toString();
		
		return result;
	}
	
	public String toCSV() {
		String result = this.title + "," +
						status.toString() + "," +
						priority + "," +
						dueDate.toString();
		
		return result;
	}
	
	public int compareTo(Task other) {
		if(this.priority > other.priority)
			return 1;
		
		if(this.priority < other.priority)
			return -1;
		
		//tasks with older dueDates come first among tasks with the same priority
		return this.dueDate.compareTo(other.dueDate);
	}
}
