import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class TestJukeBox {
	
	public static void main(String[] args) {
		GenHeap<Song> jukeBox = new GenHeap<Song>();
		boolean cont = true;
		initHeap(jukeBox);
		
		while (cont == true) {
			System.out.println("1. Display the list of songs"
					+ "\n2. Display current song"
					+ "\n3. Add a new song to the list"
					+ "\n4. Plays next song - removes song from list, displays new current song"
					+ "\n5. Re-rate next task-prompts user for new rating, remove and re-add to list"
					+ "\n6. Quit");
			cont = taskChoice(jukeBox, checkInt(0, 6));
		}
	}
	
	public static void initHeap(GenHeap<Song> jukeBox) {
		try {
			Scanner read = new Scanner(new File("playlist.txt"));
			while (read.hasNextLine()) {
				String song = read.nextLine();
				//System.out.println(song);
				String split[] = song.split(",", 4);
				//System.out.println(split[0] + " " + split[1] + " " + split[2] + " " + split[3]);
				jukeBox.add(new Node<Song>(new Song(split[0],split[1],split[2],Integer.parseInt(split[3]))));
			}
		} catch (FileNotFoundException e) {
			System.out.println("File songlist is no where to be found.");
		}
	}

	
	public static boolean taskChoice(GenHeap<Song> jukeBox, int choice) {
		Scanner input = new Scanner(System.in);
		switch (choice) {
		case 1:
			jukeBox.printHeap();
			break;
		case 2:
			System.out.println("current song: " + jukeBox.getAt(0).getData().toString());
			break;
		case 3:
			String title = " ",artist = " ",album = " ";
			int rating = 0;
			boolean correct = false;
			System.out.println("Please enter a new song:");
			System.out.println("title of song");
			if (input.hasNextLine()) {
				title = input.nextLine();
			}
			System.out.println("the associated artist");
			if (input.hasNextLine()) {
				artist = input.nextLine();
			}
			System.out.println("the album");
			if (input.hasNextLine()) {
				album = input.nextLine();
			}
			System.out.println("give it a rating(1-5)");
			rating = checkInt(1,5);
			jukeBox.add(new Node<Song>(new Song(title,artist,album,rating)));
			break;
		case 4:
			System.out.println("changing from current song: " + jukeBox.getAt(0).getData().toString());
			jukeBox.removeMin();
			System.out.println("now playing: " + jukeBox.getAt(0).getData().toString());
			break;
		case 5:
			boolean cor = false;
			Song current = jukeBox.getAt(0).getData();
			jukeBox.removeMin();
			Song next = jukeBox.getAt(0).getData();
			jukeBox.removeMin();
			System.out.println("Next song is " + next.toString());
			System.out.println("Please enter a new rating for the song(1-5)");
			int rate = checkInt(1,5);
			jukeBox.add(new Node<Song>(new Song(next.getTitle(),next.getArtist(),next.getAlbum(),rate)));
			jukeBox.add(new Node<Song>(current));
			break;
		case 6:
			System.out.println("Exitting juke box");
			return false;
		default:
			System.out.println("Invalid choice");
			break;
		}
		return true;
	}

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
