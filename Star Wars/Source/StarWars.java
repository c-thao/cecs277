import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Chou Thao
 *
 */
public class StarWars {
	
	/**
	 * Main program which prompts user with a menu of
	 * choices to select. After a selection is chosen
	 * one of the three choices will be perform.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Entity> Rebels = new ArrayList<Entity>();
		ArrayList<Entity> Imperials = new ArrayList<Entity>();
		
		int choice = 0;
		while (choice != 3) {
			choice = 0;
			System.out.println("Please choose an option below.");
			System.out
					.println("1. Defeat the Sith Lord.\n2. A game of riddles.\n3. Quit.");
			while (choice < 1 || choice > 3) {
				if (input.hasNextInt()) {
					choice = input.nextInt();
				}
			}
			
			if (choice == 1) {
				DefeatTheSithLord fight = new DefeatTheSithLord(Rebels, Imperials);
			} else if (choice == 2) {
				GameOfRiddles riddles = new GameOfRiddles(Rebels, Imperials);
			}
		}

	}

}
