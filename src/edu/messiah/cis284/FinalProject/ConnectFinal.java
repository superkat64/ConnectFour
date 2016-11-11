package edu.messiah.cis284.FinalProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ConnectFinal extends JFrame {
	private JButton play;
	private JButton quit;
	private JLabel connectlbl;
	private BufferedImage image;

	public ConnectFinal() {
		super("Connect Four");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(makeMainPanel());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JPanel makeMainPanel() {
		BorderLayout mainLayout = new BorderLayout();
		JPanel panel = new JPanel();
		panel.setLayout(mainLayout);
		panel.setSize(new Dimension(400, 600));

		JPanel buttonPanel = new JPanel();

		connectlbl = new JLabel("Do you want to play?", JLabel.CENTER);
		play = new JButton("Play!");
		quit = new JButton("Quit.");

		buttonPanel.add(connectlbl);
		buttonPanel.add(play);
		buttonPanel.add(quit);

		try {
			image = ImageIO.read(this.getClass().getResource("/Logo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel imageLabel = new JLabel(new ImageIcon(image));

		play.addActionListener(new Listener());
		quit.addActionListener(new Listener());

		panel.add(imageLabel, BorderLayout.PAGE_START);
		panel.add(buttonPanel, BorderLayout.CENTER);

		return panel;
	}

	public class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == play) {
				new ConnectFour();
				setVisible(false);
				dispose();
			}

			if (event.getSource() == quit) {
				System.exit(0);
			}
		}
	}

	public static void main(String[] arg) {
		new ConnectFinal();
	}
}
