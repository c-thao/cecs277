import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;



public class testMain {

	public static void main(String[] args) {
		Computer james = new Computer();
		System.out.println("Welcome to Rock, Paper, Scissors.");
		System.out.println("Choose a difficulty:");
		System.out.println("1. Beginner\n2. Veteran");
		int dif = checkInt();
		if (dif == 2) {
			james = loadFromAFile(james);
		}
		test game = new test(james);
		game.playTest();
		
	}
	
	public static int checkInt() {
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;

		while (!valid) {
			if (in.hasNextInt()) {
				validNum = in.nextInt();
				valid = true;
			} else {
				in.next();
				System.out.print("Invalue input- Retry:");
			}
		}
		return validNum;
	}
	
	public static Computer loadFromAFile(Computer comp) {
		try {
			FileInputStream fileIn = new FileInputStream("comp.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			comp = (Computer) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("\nComputer data loaded correctly");
			return comp;
		} catch (IOException i) {
			System.out.println("\nComputer data not found");
		} catch (ClassNotFoundException c) {
			System.out
					.println("\nNo class was found for specified serialize object");
		}
		return comp;
	}

}
