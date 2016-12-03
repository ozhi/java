
import java.util.Scanner;

public class Main {
	
	private static void printMenu() {
		System.out.println("Welcome to Cows & Bulls");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter word length: ");
		int wordLength = scanner.nextInt();
		
		System.out.print("Enter types of objects to play with (1-digits 2-symbols)");
		int objectChoice = scanner.nextInt();
		
		switch(objectChoice) {
		case 1:
			CowsBullsGame<Integer> intGame = new CowsBullsGame<Integer>(wordLength);
			intGame.play();
			break;
			
		case 2:
			CowsBullsGame<Character> charGame = new CowsBullsGame<Character>(wordLength);
			charGame.play();
			break;
			
		default:
			System.out.println("Invalid choice");
			break;
		}
		
		scanner.close();
	}
		
	public static void main(String[] args) {
		printMenu();
	}
}
