import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissor {

	public static void main(String[] args) {
		Computer james = new Computer();
		String player = "", quad = "", quin = "";
		String cTri = "", cQuad = "", cQuin = "";
		String comp = "";
		boolean quit = false;
		int numTies = 0, numComp = 0, numPlay = 0;
		int patThree = 0, patFour = 0, patFive = 0;
		int trial = 0;

		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Rock, Paper, Scissors.");
		System.out.println("Choose a difficulty:");
		System.out.println("1. Beginner\n2. Veteran");
		int dif = checkInt();
		if (dif == 2) {
			james = loadFromAFile(james);
		}
		while (quit == false) {
			System.out.println("\nNext round:");
			// make prediction on patterns of
			// three, four, and five
			cTri = james.makePrediction(new Pattern(player));
			cQuad = james.makePrediction(new Pattern(quad));
			cQuin = james.makePrediction(new Pattern(quin));

			if (player.length() == 3) {
				james.storePattern(new Pattern(player));
				if (quad.length() == 4) {
					james.storePattern(new Pattern(quad));
					quad = quad.substring(1);
				}
				if (quin.length() == 5) {
					james.storePattern(new Pattern(quin));
					quin = quin.substring(1);
				}
				player = player.substring(1);
			}
			System.out.println("Please choose R, P, S, or Q.");
			String thrown = input.next();
			while (!thrown.equalsIgnoreCase("R")
					&& !thrown.equalsIgnoreCase("P")
					&& !thrown.equalsIgnoreCase("S")
					&& !thrown.equalsIgnoreCase("Q")) {

				System.out.println("Please choose R, P, S, or Q.");
				thrown = input.next();
			}
			if (!thrown.equalsIgnoreCase("Q")) {
				player = player + thrown;
				quad = quad + thrown;
				quin = quin + thrown;
				player = player.toUpperCase();
				quad = quad.toUpperCase();
				quin = quin.toUpperCase();
			} else {
				quit = true;
				saveToAFile(james);
			}

			int compChoose = new Random().nextInt(3);
			// randomly choose a computer choice
			// of prediction base upon pattern
			// length
			if (trial < 5) {
				if (compChoose == 0) {
					comp = cTri;
				} else if (compChoose == 1) {
					comp = cQuad;
				} else {
					comp = cQuin;
				}
				// choose computer choice base
				// upon number of wins depending
				// on pattern length
			} else {
				if (patThree >= patFour) {
					if (patThree > patFive) {
						if (patThree > patFour) {
							comp = cTri;
						} else {
							int pick = new Random().nextInt(2);
							if (pick == 0) {
								comp = cTri;
							} else {
								comp = cQuad;
							}
						}
					} else if (patThree == patFive) {
						if (patThree > patFour) {
							int pick = new Random().nextInt(2);
							if (pick == 0) {
								comp = cTri;
							} else {
								comp = cQuin;
							}
						} else {
							int pick = new Random().nextInt(3);
							if (pick == 0) {
								comp = cTri;
							} else if (pick == 1) {
								comp = cQuin;
							} else {
								comp = cQuad;
							}
						}

					} else {
						comp = cQuin;
					}
				} else if (patFive >= patFour) {
					if (patFive > patFour) {
						comp = cQuin;
					} else {
						int pick = new Random().nextInt(2);
						if (pick == 0) {
							comp = cQuin;
						} else {
							comp = cQuad;
						}
					}
				} else {
					comp = cQuad;
				}
			}

			System.out.println("Computer choses " + comp);

			if (comp.equalsIgnoreCase(thrown)) {
				System.out.println("Tie.");
				numTies++;
			} else if (comp.compareToIgnoreCase(thrown) == 3
					|| comp.compareToIgnoreCase(thrown) == -2
					|| comp.compareToIgnoreCase(thrown) == -1) {
				System.out.println("James wins.");
				numComp++;
				// increment pattern win for
				// respective length used
				if (compChoose == 0) {
					patThree++;
				} else if (compChoose == 1) {
					patFour++;
				} else {
					patFive++;
				}
				trial++;
			} else {
				System.out.println("Player wins.");
				numPlay++;
			}
			System.out.println("Current Score: " + " W " + numPlay + " T "
					+ numTies + " L " + numComp);
		}
		System.out.println("You have quitted the game.");
		input.close();
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

	public static void saveToAFile(Computer comp) {
		try {
			FileOutputStream fileOut = new FileOutputStream("comp.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(comp);
			out.close();
			fileOut.close();
			System.out.println("\nComputer data saved correctly");
		} catch (IOException i) {
			System.out.println("\nObject not serializable");
		}
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
		in.close();
		return validNum;
	}

}
