import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Chou Thao
 *
 */
public class GameOfRiddles {
	
	/**
	 * Constructs a GameOfRiddles and plays the game. The user is prompted
	 * for inputs on certain riddles. The game implements three riddles
	 * and if all is correct user wins.
	 * 
	 * @param Rebels
	 * @param Empire
	 */
	public GameOfRiddles(ArrayList<Entity> Rebels, ArrayList<Entity> Empire) {
		genChars(Rebels, Empire);
		boolean cont = startGame(Rebels, Empire);
		if (cont) {
			System.out
					.println("Congratulations! You have made a dent on the empire.");
		} else {
			System.out.println("You have fail the rebellion. You lose.");
		}
	}
	
	/**
	 * Initiates the game using a scanner to read files
	 * and riddles. Prompting user for input along the
	 * way and returns true if user wins.
	 * 
	 * @param pro      an array of protagonists
	 * @param antag    an array of antagonists
	 * @return         true if user solves all riddles correctly
	 */
	public static boolean startGame(ArrayList<Entity> pro,
			ArrayList<Entity> antag) {
		int pick;
		int numwrng = 0;
		Scanner input = new Scanner(System.in);
		Scanner read;
		try {
			read = new Scanner(new File("swscript.txt"));

			while (read.hasNextLine()) {
				System.out.println(read.nextLine());
			}
			read.close();

			for (int i = 1; i <= 3; i++) {
				pick = 0;
				if (numwrng > 0 && i == 3) {
					read = new Scanner(new File("defeat.txt"));
				} else {
					read = new Scanner(new File("riddle" + i + ".txt"));
				}

				while (read.hasNextLine()) {
					System.out.println(read.nextLine());
				}
				read.close();
				if (numwrng > 0 && i == 3) {
					System.out.println("Rebels have been defeat.");
					return false;
				}
				while (pick < 1 || pick > 4) {
					if (input.hasNextInt()) {
						pick = input.nextInt();
					}
				}

				if (solved(i, pick)) {
					if (i < 3) {
						antag.get(antag.size() - i).setTask("unlock");
						antag.get(antag.size() - i).doTask(
								pro.get(pro.size() - i));

					} else {
						read = new Scanner(new File("victory.txt"));
						while (read.hasNextLine()) {
							System.out.println(read.nextLine());
						}
						read.close();
					}
					System.out.println("Good job "
							+ pro.get(pro.size() - i).getName() + "\n");
				} else {
					pro.get(pro.size() - i).modifyHp(0);
					System.out.println(pro.get(pro.size() - i).getName()
							+ " has fallen.");
					System.out.println("That's one more for the bad guys.\n");
					numwrng++;
				}
			}
		} catch (FileNotFoundException e) {
			System.out
					.println("File was not found try to remember the correct location you dufus.");
		}
		return true;
	}
	
	/**
	 * Generates the characters for this game.
	 * 
	 * @param pro      an array of protagonists
	 * @param antag    an array of antagonists
	 */
	public static void genChars(ArrayList<Entity> pro, ArrayList<Entity> antag) {
		String name = "";
		String quote = "";
		Scanner input = new Scanner(System.in);

		System.out.println("Enter a name for your Jedi.");
		if (input.hasNextLine()) {
			name = input.nextLine();
		}
		System.out.println("Enter a quote for your Jedi.");
		if (input.hasNextLine()) {
			quote = input.nextLine();
		}

		System.out.println("Name is " + name);
		System.out.println("Quote is " + quote);
		pro.add(new Jedi(name, quote, "Red"));
		pro.add(new Rebel("Ed", "You're a Jedi use the force to open the door!"));
		pro.add(new Astromech("Door Opener5000", 1));

		antag.add(new SithLord(
				"Darth Escapee",
				"Feeble beings can't even use the force to open a simple door.",
				"Blue"));
		antag.add(new Computer("The computer that never lies"));
		antag.add(new Door("The door that does not open nor close"));

	}
	
	/**
	 * Checks if two integers are equivalent therefore
	 * an answer is correct.
	 * 
	 * @param correct    correct expected integer
	 * @param choice     an answer to compare to correct
	 * @return           true if choice and correct are the same
	 */
	public static boolean solved(int correct, int choice) {
		if (correct == choice) {
			System.out.println("Correct.");
			return true;

		} else {
			System.out.println("Wrong.");
			return false;
		}
	}

}
