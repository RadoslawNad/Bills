import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

/*
 * 
 * Person 2 window to type in amount of bills and summarize it.
 * 
 */

public class Person1 extends javax.swing.JDialog {
	
	private Person1.ButtonsAction buttonAction = new Person1.ButtonsAction();
	private JTextField field;
	private JTextArea area;
	private JTextArea smallArea;
	private JButton ok;
	private JButton back;
	private JButton finish;
	private JButton cancel;
	private String text;
	private ArrayList<Double> list = new ArrayList<Double>(15);
	private double sum;
	private boolean finishConf = false;

	public Person1(javax.swing.JFrame owner) {
		super(owner, "Person1", true);
		setLocationByPlatform(true);

		JPanel northPanel = new JPanel();
		javax.swing.JLabel label = new javax.swing.JLabel("Give the amount: ");
		field = new JTextField(10);

		ok = new JButton("Add");
		back = new JButton("Back");
		ok.addActionListener(buttonAction);
		back.addActionListener(buttonAction);
		northPanel.add(label);
		northPanel.add(field);
		northPanel.add(ok);
		northPanel.add(back);
		add(northPanel, "North");

		area = new JTextArea(15, 25);
		area.setFocusable(false);
		smallArea = new JTextArea(15, 7);
		smallArea.setFocusable(false);
		smallArea.setEditable(false);
		add(new javax.swing.JScrollPane(area), "Center");
		add(new javax.swing.JScrollPane(smallArea), "East");

		JPanel southPanel = new JPanel();
		finish = new JButton("Summarize");
		cancel = new JButton("Cancel");
		finish.addActionListener(buttonAction);
		cancel.addActionListener(buttonAction);
		southPanel.add(finish);
		southPanel.add(cancel);
		add(southPanel, "South");

		ok.getRootPane().setDefaultButton(ok);
		pack();
	}

	private class ButtonsAction implements java.awt.event.ActionListener {
		private ButtonsAction() {
		}

		public void actionPerformed(java.awt.event.ActionEvent ev) {
			Object z = ev.getSource();
			if (z == ok) {
				try {
					text = field.getText().trim().replace(",", ".");
					field.setText(" ");
					list.add(Double.valueOf(text));
					area.append(text + "\n");
					smallArea.append(text + " GBP" + "\n");
				} catch (NumberFormatException e) {
					e.printStackTrace();
					area.append("Only numbers can be entered 0-9.\n");
				}
			}
			if (z == back) {
				try {
					int index = list.size();
					list.remove(index - 1);
					int line = smallArea.getLineCount() - 2;
					smallArea.replaceRange("", smallArea.getLineStartOffset(line), smallArea.getLineEndOffset(line));
				} catch (ArrayIndexOutOfBoundsException iof) {
					iof.printStackTrace();
					area.append("No further data to be deleted.\n");
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			if (z == finish) {
				Person1.this.calculate();
				finishConf = true;

				setVisible(false);
			}

			if (z == cancel) {
				finishConf = false;
				setVisible(false);
			}
		}
	}

	public boolean getConfB() {
		return finishConf;
	}

	public double getSum() {
		return sum;
	}

	private void calculate() {
		for (Double s : list) {
			sum += s.doubleValue();
		}
	}
}
