package edu.messiah.cis284.FinalProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WinFrame {
	private JButton play;
	private JButton quit;
	private JLabel connectlbl;
	private int winner;

	public WinFrame(int w) {
		winner = w;
		if (winner == 1) {
			new PlayerOneWinPanel();
		}
		if (winner == 2) {
			new PlayerTwoWinPanel();
		}
	}
	
	public class PlayerOneWinPanel extends JFrame {

		public PlayerOneWinPanel(){
			super("Connect Four");
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			add(makeMainPanel());
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		}
		private JPanel makeMainPanel() {	
			BorderLayout mainLayout = new BorderLayout();
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(mainLayout);
			mainPanel.setSize(new Dimension(400, 600));

			JPanel buttonPanel = new JPanel();

			connectlbl = new JLabel("Congradulations Player One, you won! "
					+ "Do you want to play again?", JLabel.CENTER);
			play = new JButton("Play Again?");
			quit = new JButton("Quit.");

			buttonPanel.add(play);
			buttonPanel.add(quit);

			play.addActionListener(new Listener());
			quit.addActionListener(new Listener());

			mainPanel.add(connectlbl, BorderLayout.PAGE_START);
			mainPanel.add(buttonPanel, BorderLayout.CENTER);

			return mainPanel;

		}
	}
	
	public class PlayerTwoWinPanel extends JFrame {
		
		
		public PlayerTwoWinPanel(){
			super("Connect Four");
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			add(makeMainPanel());
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		}
		private JPanel makeMainPanel() {	
			BorderLayout mainLayout = new BorderLayout();
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(mainLayout);
			mainPanel.setSize(new Dimension(400, 600));

			JPanel buttonPanel = new JPanel();

			connectlbl = new JLabel("Congradulations Player Two, you won! "
					+ "Do you want to play again?", JLabel.CENTER);
			play = new JButton("Play Again?");
			quit = new JButton("Quit.");

			buttonPanel.add(play);
			buttonPanel.add(quit);

			play.addActionListener(new Listener());
			quit.addActionListener(new Listener());

			mainPanel.add(connectlbl, BorderLayout.PAGE_START);
			mainPanel.add(buttonPanel, BorderLayout.CENTER);

			return mainPanel;

		}
	}

	public class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == play) {
				new ConnectFour();
			}

			if (event.getSource() == quit) {
				System.exit(0);
			}
		}
	}
}
