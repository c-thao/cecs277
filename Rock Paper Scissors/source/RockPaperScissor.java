import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;

/**
 * This is an RockPaperScissor Object which plays a decisive
 * game of rock, paper, scissors with a player and a computer.
 * The computer, however, is able to make predictions base
 * upon the player's choices adding a tier of difficulty.
 * 
 * @author Chou Thao
 *
 */
public class RockPaperScissor {
	/** A Computer object for RockPaperScissor. */
	private Computer james;

	/** Three combinations of various lengths of player's turns. */
	private String player, quad, quin;
	
	/** A string for the computer. */
	private String comp;
	
	/** Placeholder data for predictions. */
	private int patThree, patFour, patFive;

	/** Placeholder data value. */
	private int trial;

	/**
	 * A constructor for RockPaperScissor which initializes the Computer.
	 * 
	 * @param comp the initial value for a Computer
	 */
	public RockPaperScissor(Computer computer) {
		james = computer;
		player = "";
		quad = "";
		quin = "";
		comp = "";
		patThree = 0;
		patFour = 0;
		patFive = 0;
		trial = 0;
	}
	
	/**
	 * Gets the String comp.
	 * 
	 * @return the String comp
	 */
	public String getComp() {
		return comp;
	}

	/**
	 * Plays a game of rock, papers, scissors pitting
	 * a player against a computer who is adaptive to
	 * the plays a player makes as the game goes along.
	 */
	public int playGame(String thrown) {
		// makes predictions for computers
		String cTri = "", cQuad = "", cQuin = "";
		cTri = james.makePrediction(new Pattern(""));
		cTri = james.makePrediction(new Pattern(player));
		cQuad = james.makePrediction(new Pattern(quad));
		cQuin = james.makePrediction(new Pattern(quin));

		// stores current String of plays into three,
		// four, and five length Patterns
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

		// update current turns played
		player = player + thrown;
		quad = quad + thrown;
		quin = quin + thrown;
		player = player.toUpperCase();
		quad = quad.toUpperCase();
		quin = quin.toUpperCase();

		// computer makes random decision
		// initially to obtain data on
		// best Pattern String length
		// to analyze
		int compChoose = new Random().nextInt(3);
		if (trial < 5) {
			if (compChoose == 0) {
				comp = cTri;
			} else if (compChoose == 1) {
				comp = cQuad;
			} else {
				comp = cQuin;
			}
		} else {
			comp = bestCompChoice(patThree, patFour, patFive, cTri, cQuad, cQuin);
		}

		// checks to see if computer or player wins
		// and outputs an int to specify
		if (comp.equalsIgnoreCase(thrown)) {
			//System.out.println("Tie.");
			return 0;
		} else if (comp.compareToIgnoreCase(thrown) == 3 || comp.compareToIgnoreCase(thrown) == -2
				|| comp.compareToIgnoreCase(thrown) == -1) {
			//System.out.println("Computer James wins.");
			if (compChoose == 0) {
				patThree++;
			} else if (compChoose == 1) {
				patFour++;
			} else {
				patFive++;
			}
			trial++;
			return -1;
		} else {
			//System.out.println("Player wins.");
			return 1;
		}
	}

	/**
	 * Decides the computer's next move to play based upon
	 * the effectiveness of the predictions from patterns
	 * of three lengths analyzed; lengths three, four,
	 * and five.
	 * 
	 * @param patThree number of pattern of size three wins
	 * @param patFour number of pattern of size four wins
	 * @param patFive number of pattern of size five wins
	 * @param cTri next choice based on analyzing a pattern of three
	 * @param cQuad next choice based on analyzing a pattern of four
	 * @param cQuin next choice based on analyzing a pattern of five
	 * @return the computer's next choice
	 */
	public String bestCompChoice(int patThree, int patFour, int patFive, String cTri, String cQuad, String cQuin) {
		if (patThree >= patFour) {
			if (patThree > patFive) {
				if (patThree > patFour) {
					return cTri;
				} else {
					int pick = new Random().nextInt(2);
					if (pick == 0) {
						return cTri;
					} else {
						return cQuad;
					}
				}
			} else if (patThree == patFive) {
				if (patThree > patFour) {
					int pick = new Random().nextInt(2);
					if (pick == 0) {
						return cTri;
					} else {
						return cQuin;
					}
				} else {
					int pick = new Random().nextInt(3);
					if (pick == 0) {
						return cTri;
					} else if (pick == 1) {
						return cQuin;
					} else {
						return cQuad;
					}
				}

			} else {
				return cQuin;
			}
		} else if (patFive >= patFour) {
			if (patFive > patFour) {
				return cQuin;
			} else {
				int pick = new Random().nextInt(2);
				if (pick == 0) {
					return cQuin;
				} else {
					return cQuad;
				}
			}
		} else {
			return cQuad;
		}
	}

}
