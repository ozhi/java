
import java.time.LocalDate;
import java.util.*;

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
			
}
