package graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnnounceWindow {

	private JDialog frmAnnocement;

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
		frmAnnocement = new JDialog();
		frmAnnocement.setFont(new Font("Tahoma", Font.PLAIN, 34));
		frmAnnocement.setTitle("Annocement");
		frmAnnocement.setBounds(100, 100, 450, 331);

		frmAnnocement.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		JTextArea textPane = new JTextArea();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPane.setBounds(0, 0, 432, 228);
		textPane.setToolTipText("System Announcement");
		textPane.setText(s);
		textPane.setLineWrap(true);
		textPane.setEditable(false);
		frmAnnocement.setVisible(true);
		frmAnnocement.getContentPane().setLayout(null);
		frmAnnocement.getContentPane().add(textPane);
		
		JButton btnUnderstood = new JButton("Understood");
		btnUnderstood.setBounds(43, 241, 338, 32);
		btnUnderstood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmAnnocement.dispose();
			}
		});
		frmAnnocement.getContentPane().add(btnUnderstood);
	}
}
