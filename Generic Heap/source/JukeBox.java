import java.util.Scanner;
/**
 * a JukeBox program which
 * allows an user to inter-
 * act with a list of songs
 * contained inside an heap
 * 
 * @author Chou Thao
 *
 */
public class JukeBox {
	/**
	 * a heap of Song
	 * 
	 */
	private GenHeap<Song> jukeBox;
	/**
	 * a constructor which initializes
	 * jukeBox with a list of songs
	 * 
	 * @param songs a heap/list of songs
	 */
	public JukeBox(GenHeap<Song> songs) {
		jukeBox = songs;
		playJukeBox();
	}
	/**
	 * continuously prompts an
	 * user to pick one of 6 
	 * choices and passes the
	 * choice through another
	 * method
	 * 
	 * 
	 */
	public void playJukeBox() {
		boolean cont = true;
		while (cont == true) {
			System.out.println("1. Display the list of songs" + "\n2. Display current song"
					+ "\n3. Add a new song to the list"
					+ "\n4. Plays next song - removes song from list, displays new current song"
					+ "\n5. Re-rate next task-prompts user for new rating, remove and re-add to list" + "\n6. Quit");
			cont = songChoice(checkInt(0, 6));
		}
	}
	/**
	 * performs one of six choices
	 * dependent on the selected 
	 * choice noted by the argument
	 * 
	 * @param choice
	 * @return       true if choice 6
	 * 				 was not chosen
	 * 				 else false
	 */
	public boolean songChoice(int choice) {
		Scanner input = new Scanner(System.in);
		if (choice != 3 && choice != 6 && jukeBox.isEmpty()) {
			System.out.println("Please add a new song as the jukeBox is currently empty");
			return true;
		}
		switch (choice) {
		case 1:
			jukeBox.printHeap();
			break;
		case 2:
			System.out.println("current song: " + jukeBox.getAt(0).getData().toString());
			break;
		case 3:
			String title = " ", artist = " ", album = " ";
			int rating = 0;
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
			rating = checkInt(1, 5);
			jukeBox.add(new Node<Song>(new Song(title, artist, album, rating)));
			break;
		case 4:
			System.out.println("changing from current song: " + jukeBox.getAt(0).getData().toString());
			jukeBox.removeMin();
			if (!jukeBox.isEmpty()) {
				System.out.println("now playing: " + jukeBox.getAt(0).getData().toString());
			} else {
				System.out.println("There are no more songs to play");
			}
			break;
		case 5:
			Song current = jukeBox.removeMin().getData();
			if (!jukeBox.isEmpty()) {
				Song next = jukeBox.removeMin().getData();
				System.out.println("Next song is " + next.toString());
				System.out.println("Please enter a new rating for the song(1-5)");
				int rate = checkInt(1, 5);
				jukeBox.add(new Node<Song>(new Song(next.getTitle(), next.getArtist(), next.getAlbum(), rate)));
				jukeBox.add(new Node<Song>(current));
			} else {
				String nod = " ";
				while (!nod.equalsIgnoreCase("Y") && !nod.equalsIgnoreCase("N")) {
					System.out.println(
							"Sorry there is currently only one song" + "\nDo you wish to change its rating?(y or n)");
					nod = input.next();
				}
				if (nod.equalsIgnoreCase("Y")) {
					System.out.println("Please enter a new rating for the song(1-5)");
					int rate = checkInt(1, 5);
					jukeBox.add(new Node<Song>(new Song(current.getTitle(),current.getArtist(),current.getAlbum(),rate)));
				}
			}
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
	/**
	 * prompts an user to input an integer
	 * within an valid range
	 * 
	 * @param low  minimum int
	 * @param high maximum int
	 * @return     valid int input
	 */
	public int checkInt(int low, int high) {
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