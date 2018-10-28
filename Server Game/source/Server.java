import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

/**
 * This is a Server class which hosts a socket for a
 * client to connect to. The server hosts a Computer
 * object and interacts with a client to play a game
 * of rock, paper, scissors.
 * 
 * @author Chou Thao
 *
 */
public class Server extends Thread {
	public static void main(String[] args) {
		try {
			// initializes the server
			ServerSocket server = new ServerSocket(1235);
			Socket client = null;
			
			// keeps the server on after it has been initialized
			// and wait for a client to connect
			while (true) {
				System.out.println("waiting...");
				client = server.accept();
				System.out.println("Connection successful. Please wait while your session begins.");
				Server serv = new Server(client);
				serv.start();
				System.out.println("Welcome to a game of Rock, Paper, Scissors.");
				PrintStream out = new PrintStream(client.getOutputStream());
			}
		} catch (IOException e) {
			System.out.println("Their has been an issue connecting the client.");
		}
	}
	
	/** Socket for a client. */
	private Socket client;
	
	/** Score of the game. */
	private int score;
	
	/** The Computer object. */
	private Computer james;
	
	/** The RockPaperScissor object (game). */
	private RockPaperScissor rps;
	
	/**
	 * A constructor that initializes
	 * the server and the client with
	 * a socket input sock.
	 * 
	 * @param sock
	 */
	public Server(Socket sock) {
		client = sock;
		score = 0;
		james = new Computer();
		loadFromAFile(james);
		rps = new RockPaperScissor(james);
	}
	
	/**
	 * Gets the Computer james.
	 * 
	 * @return the Computer james
	 */
	public Computer getComp() {
		return james;
	}
	
	/**
	 * Defines the run function to prompt
	 * string choices for a client.
	 */
	public void run() {
		try {
			PrintStream out = new PrintStream(client.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			while (true) {
				out.println("Please choose R, P, S, or Q.");
				String data = in.readLine();
				System.out.println("data received " + data);
				if (!data.equalsIgnoreCase("Q")) {
					score = rps.playGame(data);
					out.println("Computer choses " + rps.getComp());
					out.println(score);
				}
				else {
					out.println("Q");
					client.close();
					System.out.println("Session ended.");
					break;
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("Saving computer data...");
		saveToAFile(james);
	}
	
	/**
	 * Saves current Computer's data.
	 * 
	 * @param comp current Computer in game
	 */
	public static void saveToAFile(Computer comp) {
		try {
			FileOutputStream fileOut = new FileOutputStream("comp.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(comp);
			out.close();
			fileOut.close();
			System.out.println("\nComputer data saved correctly.");
		} catch (IOException i) {
			System.out.println("\nObject not serializable.");
		}
	}

	/**
	 * Loads a Computer object from a local file.
	 * 
	 * @param comp a base Computer object to be overwritten
	 */
	public static void loadFromAFile(Computer comp) {
		try {
			FileInputStream fileIn = new FileInputStream("comp.dat");
			System.out.println("Initializing computer data.");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			comp = (Computer) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("\nInitialization complete.");
		} catch (IOException i) {
			System.out.println("\nComputer data not found.");
		} catch (ClassNotFoundException c) {
			System.out.println("\nNo class was found for specified serialize object.");
		}
	}
}
