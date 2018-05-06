package graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Font;

public class AnnounceWindow {

	private JFrame frmAnnocement;

	/**
	 * Create the application.
	 */
	public AnnounceWindow(String s) {
		initialize(s);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String s) {
		frmAnnocement = new JFrame();
		frmAnnocement.setFont(new Font("Tahoma", Font.PLAIN, 34));
		frmAnnocement.setTitle("Annocement");
		frmAnnocement.setBounds(100, 100, 450, 300);

		frmAnnocement.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAnnocement.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTextArea textPane = new JTextArea();
		textPane.setToolTipText("System Announcement");
		textPane.setText(s);
		textPane.setLineWrap(true);
		textPane.setEditable(false);
		frmAnnocement.setVisible(true);
		frmAnnocement.getContentPane().add(textPane, BorderLayout.CENTER);
	}
}
