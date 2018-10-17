import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

public class Server extends Thread {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(1235);
			Socket client = null;
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

	private Socket client;
	
	private int score;

	private Computer james;
	
	private RockPaperScissor rps;

	public Server(Socket sock) {
		client = sock;
		score = 0;
		james = new Computer();
		loadFromAFile(james);
		rps = new RockPaperScissor(james);
	}

	public Computer getComp() {
		return james;
	}

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
	 * saves current Computer's data
	 * 
	 * @param comp
	 *            current Computer in game
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
	 * loads a Computer object from a local file
	 * 
	 * @param comp
	 *            a base Computer object to be overwritten
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