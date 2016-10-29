
import java.time.*;
import java.util.*;

public class UI {
	
	private static void printMenu() {
		System.out.print("\n");
		System.out.println("Изберете опция:");
		System.out.println("   1) Всички задачи, подредени по приоритет");
		System.out.println("   2) Задачи със статус IN PROCESS");
		System.out.println("   3) Задачи, които да се завършат в следващите три дни");
		System.out.println("   4) Изход");
		System.out.print("Вашият избор: ");
	}
	
	private static ToDoList getExampleToDoList() {
		Task t1 = new Task("Task 1", "This is my first taks", TaskStatus.INITIAL,     3, LocalDate.of(2016, Month.OCTOBER, 28));
		Task t2 = new Task("Task 2", "This is my taks",       TaskStatus.IN_PROGRESS, 1, LocalDate.of(2016, Month.OCTOBER, 21));
		Task t3 = new Task("Task 3", "This is my taks",       TaskStatus.IN_PROGRESS, 4, LocalDate.of(2016, Month.SEPTEMBER, 01));
		Task t4 = new Task("Task 4", "This is my taks",       TaskStatus.INITIAL,     5, LocalDate.of(2016, Month.OCTOBER, 28));
		Task t5 = new Task("Task 5", "This is my taks",       TaskStatus.DONE,        2, LocalDate.of(2016, Month.MARCH, 30));
		Task t6 = new Task("Task 6", "This is my taks",       TaskStatus.IN_PROGRESS, 5, LocalDate.of(2016, Month.OCTOBER, 30));
		Task t7 = new Task("Task 7", "This is my taks",       TaskStatus.IN_PROGRESS, 3, LocalDate.of(2016, Month.APRIL, 28));
		Task t8 = new Task("Task 8", "This is my taks",       TaskStatus.INITIAL,     1, LocalDate.of(2016, Month.APRIL, 01));
		Task t9 = new Task("Task 9", "This is my taks",       TaskStatus.IN_PROGRESS, 1, LocalDate.of(2016, Month.MAY, 28));
		
		Task[] exampleTasks = {t1, t2, t3, t4, t5, t6, t7, t8, t9};
		
		return new ToDoList(exampleTasks);
	}
	
	private static int getMenuChoice() throws java.util.InputMismatchException {
		int choice = 0;
		
		Scanner scanner = null;
		
		try{
			scanner = new Scanner(System.in);
			choice = scanner.nextInt();
		} catch(java.util.InputMismatchException e) {
			e.printStackTrace();
			scanner.nextLine();
		} finally {
			//scanner.close();
			//if above line is uncommented choice is scanned correctly only the first time!
		}
		
		if(choice < 1 || choice > 4) {
			throw new java.util.InputMismatchException();
		}
		
		return choice;
	}
	
	public static void main(String[] args) {

		System.out.println("Welcome to ToDoList");
		
		ToDoList myToDoList = getExampleToDoList(); //hardcoded tasks because no ToDoList.addTask(Task) method is implemented
		
		int menuChoice = 0;
		
		while(menuChoice != 4) {			
			boolean errorInMenuChoiceInput;
			
			do {
				printMenu();
				
				errorInMenuChoiceInput = false;
				try {
					menuChoice = getMenuChoice();
				} catch(java.util.InputMismatchException e) {
					errorInMenuChoiceInput = true;
				}
				
			}while(errorInMenuChoiceInput);
			
			switch(menuChoice) {
				case 1:
					System.out.println("\nAll tasks by priority:");
					System.out.println(Arrays.toString(myToDoList.getAllTasksByPriority()));
					break;
					
				case 2:
					System.out.println("\nTasks in progress:");
					System.out.println(Arrays.toString(myToDoList.getAllTasksInProgress()));
					break;
					
				case 3:
					System.out.println("\nTasks in progress due in the next three days:");
					System.out.println(Arrays.toString(myToDoList.getAllTasksInProgressDueInNextThreeDays()));
					break;
				
				default:
					System.out.println("Goodbye!");
					break;
			}

		}
	}

}
