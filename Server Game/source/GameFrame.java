import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This is a GameFrame class which is a GUI
 * design for the game.
 * 
 * @author Chou Thao
 *
 */
public class GameFrame extends JFrame {
	/** The userChoice. */
	private String userChoice;

	/** The panels of the GUI. */
	private JPanel panel1, panel2, panel3;

	/** The labels of the GUI. */
	private JLabel label1, label2, label3, label4, label5, label6, label7, label8;
	
	/**
	 * Default Constructor.
	 */
	public GameFrame() {
		userChoice = "";

		// panels
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel1.setLayout(new GridLayout(4, 1));
		panel2.setLayout(new GridLayout(1, 1));

		// labels
		label1 = new JLabel("Player is idle.");
		label2 = new JLabel("Computer is idle.");
		label3 = new JLabel("Current Score: T 0 W 0 L 0");
		label4 = new JLabel("Server:...");
		label3.setFont(new Font("Courier New", Font.BOLD, 24));
		label4.setFont(new Font("Courier New", Font.BOLD, 24));
		label5 = new JLabel("Rock", SwingConstants.RIGHT);
		label6 = new JLabel("Paper", SwingConstants.RIGHT);
		label7 = new JLabel("Scissors", SwingConstants.RIGHT);
		label8 = new JLabel("Quit", SwingConstants.RIGHT);
		createComponents();

		// sets size of frame
		setBounds(100, 400, 500, 700);
	}
	
	/**
	 * Gets the userChoice.
	 * 
	 * @return the String userChoice
	 */
	public String getUserChoice() {
		return userChoice;
	}
	
	/**
	 * Sets the userChoice to an empty String.
	 */
	public void resetUserChoice() {
		userChoice = "";
	}
	
	/**
	 * Sets the text of label1 with the string for
	 * userChoice
	 */
	public void updateLabel1() {
		label1.setText("Player throws " + userChoice);
	}
	
	/**
	 * Sets the text of label2 with the string
	 * input compChoice.
	 * 
	 * @param compChoice the String input to update
	 *                   the text of label2
	 */
	public void updateLabel2(String compChoice) {
		label2.setText("Computer throws " + compChoice);
	}
	
	/**
	 * Sets the text of label3 with the tally of the
	 * game with integer inputs, w, l, and t.
	 * 
	 * @param w the integer input for wins
	 * @param l the integer input for losses
	 * @param t the integer input for ties
	 */
	public void updateLabel3(int w, int l, int t) {
		label3.setText("Current Score: T " + t +
				" W " + w + " L " + l);
		}

	/**
	 * Sets the text of label4 with an String.
	 * 
	 * @param output the String to update the
	 *               text of label4
	 */
	public void updateLabel4(String output) {
		label4.setText(output);
	}
	
	/**
	 * Builds the components of the GUI adding
	 * action listeners to handle button inter-
	 * actions.
	 */
	public void createComponents() {
		// initializes all the buttons
		JButton rock = new JButton(
				new ImageIcon(new ImageIcon("src/rock.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		rock.setPreferredSize(new Dimension(100, 100));
		JButton paper = new JButton(new ImageIcon(
				new ImageIcon("src/paper.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		paper.setPreferredSize(new Dimension(100, 100));
		JButton scissors = new JButton(new ImageIcon(
				new ImageIcon("src/scissors.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		scissors.setPreferredSize(new Dimension(100, 100));
		JButton quit = new JButton(new ImageIcon(
				new ImageIcon("src/quit.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		quit.setPreferredSize(new Dimension(100, 100));

		// put all components onto the panel then frame
		panel1.add(label5);
		panel1.add(rock);
		panel1.add(label6);
		panel1.add(paper);
		panel1.add(label7);
		panel1.add(scissors);
		panel1.add(label8);
		panel1.add(quit);
		add(panel1, BorderLayout.WEST);
		add(label1, BorderLayout.CENTER);
		add(label2, BorderLayout.EAST);
		add(label3, BorderLayout.NORTH);
		add(label4, BorderLayout.SOUTH);

		// add listeners to the buttons
		rock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				userChoice = "R";
				updateLabel1();
			}
		});
		paper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				userChoice = "P";
				updateLabel1();
			}
		});
		scissors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				userChoice = "S";
				updateLabel1();
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				userChoice = "Q";
				updateLabel1();
			}
		});
	}
}
