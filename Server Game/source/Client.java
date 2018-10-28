import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JFrame;

/**
 * This is a Client class which connects to a server
 * socket and wraps a GUI design over a game of rock,
 * papers, scissors. The client hosts the player and
 * uses the gui to interact with the player. The
 * client also updates the GUI with the tally of
 * the game.
 * 
 * @author Chou Thao
 *
 */
public class Client {
	public static void main(String[] args) {
		try {
			System.out.println();
			// initializes a connection to the server
			Socket server = new Socket("localhost", 1235);
			Scanner input = new Scanner(System.in);
			Client clnt = new Client();
			BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			PrintStream out = new PrintStream(server.getOutputStream());
			boolean done = false;
			
			// creates the GUI
			GameFrame game = new GameFrame();
			game.setTitle("Rock, Paper, Scissors");
			game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			game.setVisible(true);
			
			// plays the game until the done is true
			while (!done) {
				String srvrsp = in.readLine();
				if (srvrsp != null) {
					// request user for one of four choices
					if (srvrsp.equals("Please choose R, P, S, or Q.")) {
						game.updateLabel4(srvrsp);
						while (game.getUserChoice().equals("")) {
							System.out.println();
							if (!game.getUserChoice().equals("")) {
								out.println(game.getUserChoice());
								game.resetUserChoice();
								break;
							}
						}
					} // updates tally score of game
					else if (isInteger(srvrsp)) {
						clnt.updateScore(Integer.parseInt(srvrsp));
						game.updateLabel3(clnt.getWins(), clnt.getLosses(), clnt.getTies());
					} // quits game
					else if (srvrsp.equalsIgnoreCase("Q")) {
						game.updateLabel4("You are now exiting. Your session will end momentarily.");
						done = true;
					} // computer choice 
					else if (srvrsp.substring(0,16).equalsIgnoreCase("Computer choses ")) {
						game.updateLabel2(srvrsp.substring(16));
					} // update server responses
					else {
						game.updateLabel4(srvrsp);
					}
				} else {
					game.updateLabel4("waiting for server's response");
				}
			}
			server.close();
			System.exit(1);
		} catch (IOException e) {
			System.out.println("You have lost connection to the host." + " Please contact your network administrator to reconnect.");
		}
	}
	
	/** Tally of wins, losses, and ties in the game. */
	private int wins, losses, ties;
	
	/**
	 *  Default Constructor.
	 */
	public Client() {
		wins = 0;
		losses = 0;
		ties = 0;
	}
	
	/**
	 * Updates the tally of the game depending on
	 * the integer input score.
	 * 
	 * @param score integer to update tally of the game
	 */
	public void updateScore(int score) {
		if (score == 1) {
			wins += 1;
		} else if (score == -1) {
			losses += 1;
		}else {
			ties += 1;
		}
	}
	
	/**
	 * Gets the number of ties.
	 * 
	 * @return ties
	 */
	public int getTies() {
		return ties;
	}
	
	/**
	 * Gets the number of losses.
	 * 
	 * @return losses
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * Gets the number of wins.
	 * 
	 * @return wins
	 */
	public int getWins() {
		return wins;
	}
	
	/**
	 * Checks to see if a String input s, is
	 * a valid integer.
	 * 
	 * @param s the String input to check
	 * @return  true if the String is a valid
	 *          integer, else false
	 */
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}
		catch (NumberFormatException e) {
			System.out.println("String is not a integer");
		}
		return false;
	}

}
