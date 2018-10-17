

import java.io.*;
import java.util.*;
import java.awt.*;


public class Level implements Serializable {

	private char[][] level;

	public Level() {
		generateLevel(1);
	}

	public void generateLevel(int levelNum) {
		try {
			Scanner read = new Scanner(new File("src\\Level" + levelNum + ".txt"));
			int col = 0;
			ArrayList<String> levels = new ArrayList<String>();
			while (read.hasNextLine()) {
				String line = read.nextLine();
				String[] split = line.split(" ");
				col = split.length; // get column
				levels.add(line);
			}
			read.close();
			int row = levels.size(); // get rows
			level = new char[row][col];
			for (int i = 0; i < row; i++) {
				String[] split = levels.get(i).split(" ");
				for (int j = 0; j < col; j++) {
					level[i][j] = split[j].charAt(0);
				}
			}
		} catch (FileNotFoundException e) {
			System.out
					.println("The file you were looking for we're not as specified.");
		}
	}

	public char getRoom(Point p) {
		if (p.getX() >= level.length || p.getY() >= level[0].length
				|| p.getX() < 0 || p.getY() < 0) {
			System.out
					.println("There seems to be a wall there traveler.\nPlease refrain from getting lost and refer to the map.");
			return 'n';
		} else {
			System.out.println("Valid room.");
			return level[(int) p.getX()][(int) p.getY()];
		}
	}

	public void displayMap(Point p) {
		for (int i = 0; i < level.length; i++) {
			System.out.println();
			for (int j = 0; j < level[0].length; j++) {
				if (p.getX() == i && p.getY() == j) {
					System.out.print("* ");
				} else {
					System.out.print(level[i][j] + " ");
				}
			}
		}
	}

	public Point findStartLocation() {
		Point p = new Point(0, 0);
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[0].length; j++) {
				if (level[i][j] == ('s')) {
					return new Point(i, j);
				}
			}
		}
		return p;
	}

	public void clearRoom(Point p) {
		level[(char) p.getX()][(char) p.getY()] = 'c';
	}

}
