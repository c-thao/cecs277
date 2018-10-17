import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
	// choices
	private String userChoice;

	// panels
	private JPanel panel1, panel2, panel3;

	// label
	private JLabel label1, label2, label3, label4, label5, label6, label7, label8;

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

	public String getUserChoice() {
		return userChoice;
	}
	
	public void resetUserChoice() {
		userChoice = "";
	}

	public void updateLabel1() {
		label1.setText("Player throws " + userChoice);
	}

	public void updateLabel2(String compChoice) {
		label2.setText("Computer throws " + compChoice);
	}

	public void updateLabel3(int w, int l, int t) {
		label3.setText("Current Score: T " + t +
				" W " + w + " L " + l);
		}

	public void updateLabel4(String output) {
		label4.setText(output);
	}

	public void createComponents() {
		// buttons
		JButton rock = new JButton(
				new ImageIcon(new ImageIcon("rock.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		rock.setPreferredSize(new Dimension(100, 100));
		JButton paper = new JButton(new ImageIcon(
				new ImageIcon("\\paper.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		paper.setPreferredSize(new Dimension(100, 100));
		JButton scissors = new JButton(new ImageIcon(
				new ImageIcon("\\scissors.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		scissors.setPreferredSize(new Dimension(100, 100));
		JButton quit = new JButton(new ImageIcon(
				new ImageIcon("\\quit.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		quit.setPreferredSize(new Dimension(100, 100));

		// putting components onto panel then frame
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

		// button events
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
