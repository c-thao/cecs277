import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		GenHeap<Song> songs = new GenHeap<Song>();
		GenHeap<Job> tasks = new GenHeap<Job>();
		boolean cont = true;
		
		init(songs, tasks);
		
		while (cont == true) {
			System.out.println("Welcome. Please pick one of three options to get started.\n1. Use Task Program\n2. Use JukeBox\n3. Quit");
			int choice = checkInt(1,3);
			if (choice == 1) {
				TaskList taskProg = new TaskList(tasks);
			}
			else if (choice == 2) {
				JukeBox songBox = new JukeBox(songs);
			}
			else {
				System.out.println("Thanks for tuning in please come again.");
				cont = false;
			}
		}
	}
	/**
	 * initializes a heap of Song objects and
	 * another heap of Job objects with lists
	 * from a text file
	 * 
	 * @param jukeBox  a heap of Job objects
	 * @param taskList a heap of Song objects
	 */
	public static void init(GenHeap<Song> jukeBox, GenHeap<Job> taskList) {
		Scanner read;
		try {
			read = new Scanner(new File("src//playlist.txt"));
			while (read.hasNextLine()) {
				String song = read.nextLine();
				String split[] = song.split(",", 4);
				jukeBox.add(new Node<Song>(new Song(split[0],split[1],split[2],Integer.parseInt(split[3]))));
			}
		} catch (FileNotFoundException e) {
			System.out.println("File songlist is no where to be found.");
		}
		
		try {
			read = new Scanner(new File("src//taskList.txt"));
			while (read.hasNextLine()) {
				String taskDate = read.nextLine();
				String split[] = taskDate.split(",", 2);
				SimpleDateFormat d = new SimpleDateFormat("MM/dd/yyyy HH:mm");
				try {
					taskList.add(new Node<Job>(new Job(split[0], d.parse(split[1]))));
				} catch (ParseException e) {
					System.out.println("Date conversion failed.");
				}
			}
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("File taskList is no where to be found.");
		}
	}
	/**
	 * prompts an user to input an integer
	 * within an valid range
	 * 
	 * @param low  minimum int
	 * @param high maximum int
	 * @return     valid int input
	 */
	public static int checkInt(int low, int high) {
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;
		while (!valid) {
			if (in.hasNextInt()) {
				validNum = in.nextInt();
				if (validNum >= low && validNum <= high) {
					valid = true;
				} else {
					System.out.print("Invalid- Retry: ");
				}
			} else {
				// clear buffer of junk input
				in.next();
				System.out.print("Invalid input- Retry: ");
			}
		}
		return validNum;
	}

}
