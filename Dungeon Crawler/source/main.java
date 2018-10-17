
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		int levelNum = 1;
		Scanner input = new Scanner(System.in);
		Level dung = new Level();
		Hero mainChar = new Hero("Man", "Fire", new Point(0, 0));
		boolean cont = true;
		while (cont == true) {
			boolean load = testLoadFromAFile(mainChar);
			if (load == true) {
				mainChar = loadFromAFile(mainChar);
				mainChar.setLocation(dung.findStartLocation());
				System.out.println(mainChar.getName() + "\nHp: "
						+ mainChar.getHp() + "\nLevel: " + mainChar.getLevel()
						+ "\nBank: " + mainChar.getGold());
				System.out
						.println("Welcome back traveler.\nGlad you're back and I wish you well on continuing.");
			} else if (levelNum == 1) {
				System.out.println("\nWhat is your name traveler? ");
				mainChar = new Hero(input.nextLine(),
						"Time to go treasure hunting.",
						dung.findStartLocation());
				System.out
						.println("Well let's get you up to speed.\nUp ahead lies your journey dungeon dweler and the greatest of all peril is beyond this entrance.");
				System.out
						.println("Please be careful not to fall and always bring a stick in case of an emergency.");
				System.out
						.println("But best of all, do not forget about the good old narrator.\nSo please leave a little room in your heart and bag for my share of the loot.");

				System.out
						.println("Now onto the journey. Truth be told I have never been in there myself, but from here on out I wish you the best.\nMay you be the one and not join the countless fallen.");
			} else {
				dung.generateLevel(levelNum);
				mainChar.setLocation(dung.findStartLocation());
			}
			Game play = new Game(dung, mainChar);
			System.out.println("Do you want to continue playing? y or n");
			String nod = " ";
			while (!nod.equalsIgnoreCase("y") && !nod.equalsIgnoreCase("n")) {
				nod = input.next();
			}
			if (nod.equalsIgnoreCase("y")) {
				cont = true;
				levelNum++;
			} else {
				cont = false;
			}
		}
	}

	public static boolean testLoadFromAFile(Hero charac) {
		Hero ch = charac;
		// testing loading a hero from a file
		try {
			FileInputStream fileIn = new FileInputStream("src\\hero.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ch = (Hero) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("\nSerilized data loaded correctly");
			return true;
		} catch (IOException e) {
			System.out.println("\nSerilizied file not found");
		} catch (ClassNotFoundException c) {
			System.out
					.println("\nNo class was found for specified serialize object");
		}
		return false;
	}

	public static Hero loadFromAFile(Hero charac) {
		Hero ch = charac;
		// loading a hero from a file
		try {
			FileInputStream fileIn = new FileInputStream("src\\hero.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ch = (Hero) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("\nSerilized data loaded correctly");
			return ch;
		} catch (IOException i) {
			System.out.println("\nSerilizied file not found");
		} catch (ClassNotFoundException c) {
			System.out
					.println("\nNo class was found for specified serialize object");
		}
		return ch;
	}

}
