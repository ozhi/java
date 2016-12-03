import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;


public class TestTodoList {
	
	@Test
	public void testGetAllTasksInProgressDueInNextThreeDays() {
		Task t1 = new Task("Task 1", "This is my first taks", TaskStatus.INITIAL,     3, LocalDate.of(2016, Month.OCTOBER, 28));
		Task t2 = new Task("Task 2", "This is my taks",       TaskStatus.IN_PROGRESS, 1, LocalDate.of(2016, Month.OCTOBER, 21));
		Task t3 = new Task("Task 3", "This is my taks",       TaskStatus.IN_PROGRESS, 4, LocalDate.of(2016, Month.SEPTEMBER, 01));
		Task t4 = new Task("Task 4", "This is my taks",       TaskStatus.INITIAL,     5, LocalDate.of(2016, Month.OCTOBER, 28));
		Task t5 = new Task("Task 5", "This is my taks",       TaskStatus.DONE,        2, LocalDate.of(2016, Month.DECEMBER, 4));
		Task t6 = new Task("Task 6", "This is my taks",       TaskStatus.IN_PROGRESS, 5, LocalDate.of(2016, Month.DECEMBER, 4));
		Task t7 = new Task("Task 7", "This is my taks",       TaskStatus.IN_PROGRESS, 3, LocalDate.of(2016, Month.DECEMBER, 4));
		Task t8 = new Task("Task 8", "This is my taks",       TaskStatus.INITIAL,     1, LocalDate.of(2016, Month.DECEMBER, 4));
		Task t9 = new Task("Task 9", "This is my taks",       TaskStatus.IN_PROGRESS, 1, LocalDate.of(2016, Month.DECEMBER, 4));
		
		Task[] exampleTasks = {t1, t2, t3, t4, t5, t6, t7, t8, t9};
		
		ToDoList testList = new ToDoList(exampleTasks);
		Task[] result = testList.getAllTasksInProgressDueInNextThreeDays();
		Task[] expectedResult = {t6, t7, t9};		
		
		assertEquals(result.length, expectedResult.length);
		for(int i = 0; i < result.length; i++) {
			assertEquals(result[i], expectedResult[i]);
		}
	}
	
	@Test
	public void testGetAllTasksInProgress() {
		Task t1 = new Task("Task 1", "This is my first taks", TaskStatus.INITIAL,     3, LocalDate.of(2016, Month.OCTOBER, 28));
		Task t2 = new Task("Task 2", "This is my taks",       TaskStatus.IN_PROGRESS, 1, LocalDate.of(2016, Month.OCTOBER, 21));
		Task t3 = new Task("Task 3", "This is my taks",       TaskStatus.IN_PROGRESS, 4, LocalDate.of(2016, Month.SEPTEMBER, 01));
		Task t4 = new Task("Task 4", "This is my taks",       TaskStatus.INITIAL,     5, LocalDate.of(2016, Month.OCTOBER, 28));
		Task t5 = new Task("Task 5", "This is my taks",       TaskStatus.DONE,        2, LocalDate.of(2016, Month.DECEMBER, 4));
		Task t6 = new Task("Task 6", "This is my taks",       TaskStatus.IN_PROGRESS, 5, LocalDate.of(2016, Month.DECEMBER, 4));
		Task t7 = new Task("Task 7", "This is my taks",       TaskStatus.IN_PROGRESS, 3, LocalDate.of(2016, Month.DECEMBER, 4));
		Task t8 = new Task("Task 8", "This is my taks",       TaskStatus.INITIAL,     1, LocalDate.of(2016, Month.DECEMBER, 4));
		Task t9 = new Task("Task 9", "This is my taks",       TaskStatus.IN_PROGRESS, 1, LocalDate.of(2016, Month.DECEMBER, 4));
		
		Task[] exampleTasks = {t1, t2, t3, t4, t5, t6, t7, t8, t9};
		
		ToDoList testList = new ToDoList(exampleTasks);
		Task[] result = testList.getAllTasksInProgress();
		Task[] expectedResult = {t2, t3, t6, t7, t9};		
		
		assertEquals(result.length, expectedResult.length);
		for(int i = 0; i < result.length; i++) {
			assertEquals(result[i], expectedResult[i]);
		}
	}

}
