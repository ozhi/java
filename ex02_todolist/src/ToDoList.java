
import java.time.LocalDate;
import java.util.*;
import java.io.FileWriter;
import java.nio.file.*;

public class ToDoList {
	private Task[] tasks;
	
	public ToDoList(Task[] tasks) {
		this.tasks = tasks;
	}
	
	public Task[] getAllTasksByPriority() {
		Arrays.sort(tasks); // relies on the way Task.compareTo(Task) is implemented
		return tasks;
	}
	
	public Task[] getAllTasksInProgress() {
		int resultIndex = 0;
		
		for(int i = 0; i < tasks.length; i++)
			if(tasks[i].getStatus().equals(TaskStatus.IN_PROGRESS))
				resultIndex++;
		
		Task[] result = new Task[resultIndex];
		
		resultIndex = 0;
		for(int i = 0; i < tasks.length; i++)
			if(tasks[i].getStatus().equals(TaskStatus.IN_PROGRESS))
				result[resultIndex++] = tasks[i];
		
		return result;
	}
	
	public Task[] getAllTasksInProgressDueInNextThreeDays() {
		int resultIndex = 0;
		
		for(int i = 0; i < tasks.length; i++)
			if(tasks[i].isInProgressAndDueInNextThreeDays())
				resultIndex++;
		
		Task[] result = new Task[resultIndex];
		
		resultIndex = 0;
		for(int i = 0; i < tasks.length; i++)
			if(tasks[i].isInProgressAndDueInNextThreeDays())
				result[resultIndex++] = tasks[i];
		
		return result;
	}
	
	public void importFromCSV() {
		System.out.println("Enter an import filename");
		Scanner scanner = new Scanner(System.in);
		String filename = scanner.nextLine();
	
		try(FileWriter out = new FileWriter(filename + ".txt");) {
			
			for(int i = 0; i < tasks.length; i++) {
				out.write(tasks[i].toCSV());
				out.write("\n");
			}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	//?
	    }
	}
	
	public void exportToCSV() {
		System.out.println("Enter a name for the exported file");
		Scanner scanner = new Scanner(System.in);
		String filename = scanner.nextLine();
	
		try(FileWriter out = new FileWriter(filename + ".txt");) {
			
			for(int i = 0; i < tasks.length; i++) {
				out.write(tasks[i].toCSV());
				out.write("\n");
			}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	//?
	    }
	}
}
