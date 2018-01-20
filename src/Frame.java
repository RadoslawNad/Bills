import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/*
 * Main frame to chose persons and display information.
 */
public class Frame extends javax.swing.JFrame {
	private boolean proofB = false;
	private boolean proofR = false;
	private Frame.ButtonAction buttonAction = new Frame.ButtonAction();
	private JMenu file;
	private JMenu help;

	public Frame() {
		setLocation(400, 150);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		file = new JMenu("File");
		menuBar.add(file);

		exit = new JMenuItem("Exit");
		exit.addActionListener(buttonAction);
		file.add(exit);

		menuBar.add(javax.swing.Box.createHorizontalGlue());

		help = new JMenu("Help");
		menuBar.add(help);
		about = new JMenuItem("About program");
		about.addActionListener(buttonAction);
		help.add(about);

		JLabel label = new JLabel("Choose person: ");
		JPanel northPanel = new JPanel();

		buttonB = new JButton("Person 1");
		buttonR = new JButton("Person 2");
		buttonB.addActionListener(buttonAction);
		buttonR.addActionListener(buttonAction);

		northPanel.add(label);
		northPanel.add(buttonB);
		northPanel.add(buttonR);
		add(northPanel, "North");

		area = new JTextArea(15, 25);
		area.setFocusable(false);
		add(new javax.swing.JScrollPane(area));

		pack();
	}

	private JMenuItem exit;

	private class ButtonAction implements ActionListener {
		private ButtonAction() {
		}

		public void actionPerformed(ActionEvent e) {
			Object z = e.getSource();

			if (z == buttonB) {
				if (betFrame == null)
					betFrame = new Person1(null);
				betFrame.setVisible(true);
				if (betFrame.getConfB()) {
					sumBet = betFrame.getSum();
					area.append("Sum of money spent by Person1: " + sumBet + ".\n");
					buttonB.setBackground(Color.PINK);
					proofB = true;
					Frame.this.calculate();
				}
			}

			if (z == buttonR) {
				if (radFrame == null)
					radFrame = new Person2(null);
				radFrame.setVisible(true);
				if (radFrame.getConfR()) {
					sumRad = radFrame.getSum();
					area.append("Sum of money spent by Person2 " + sumRad + ".\n");
					buttonR.setBackground(Color.GREEN);
					proofR = true;
					Frame.this.calculate();
				}
			}
			if (z == exit) {
				System.exit(0);
			}
			if (z == about) {
				JOptionPane.showMessageDialog(null, "Program created by Radoslaw Nadolny.", "About program", 1);
			}
		}
	}

	private JMenuItem about;
	private JButton buttonB;

	private void calculate() {
		if ((proofB) && (proofR)) {
			double allSum = sumBet + sumRad;
			area.append("Total amount is: " + Double.toString(allSum) + " GBP" + "\n");
			if (sumBet > sumRad) {
				area.append("Person2 has to return Person1: " + Double.toString(allSum / 2.0D - sumRad) + " GBP");
			} else if (sumBet < sumRad) {
				area.append("Person1 has to return Person2: " + Double.toString(allSum / 2.0D - sumBet) + " GBP");
			} else if (sumBet == sumRad) {
				area.append("You spent the same amount.");
			}
		}
	}

	private JButton buttonR;
	private JTextArea area;
	private double sumBet;
	private double sumRad;
	private Person2 radFrame;
	private Person1 betFrame;
}
