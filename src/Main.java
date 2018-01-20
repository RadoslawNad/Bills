import java.awt.EventQueue;
/*
 * Small program to counting bills for two persons.
 * 
 * This is main class initiate main frame.
 */

public class Main {
	public Main() {
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				Frame frame = new Frame();
				frame.setTitle("Bills");
				frame.setDefaultCloseOperation(3);
				frame.setVisible(true);
			}
		});
	}
}
