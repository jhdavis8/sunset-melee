package graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Choice;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import java.awt.FlowLayout;
import javax.swing.JTextArea;

import gameFiles.Card;
import gameFiles.Deck;

import java.awt.GridLayout;
import javax.swing.JLabel;

public class CardSelectorWindow {

	JDialog frame;
	private int result = -1;
	private ArrayList<Card> options;

	/**
	 * Create the application.
	 */
	public CardSelectorWindow(ArrayList<Card> opts) {
		options = opts;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setBounds(100, 100, 539, 447);
		frame.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		Choice choice = new Choice();
		choice.setBounds(14, 29, 497, 27);
		for (Card c : options) {
			choice.addItem(c.name);
		}
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(choice);
		
		Button button = new Button("Pick Card");
		button.setBounds(98, 94, 122, 40);
		button.setActionCommand("Pick Card");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				result = choice.getSelectedIndex();
				frame.dispose();
			}
		});
		frame.getContentPane().add(button);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(14, 162, 493, 180);
		textArea.setLineWrap(true);
		textArea.setEditable(false);

		Button button_1 = new Button("Refresh Description");
		button_1.setBounds(267, 94, 150, 40);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Card c = Deck.getCard(choice.getSelectedItem());
				textArea.setText(c.description);
			}
		});
		
		frame.getContentPane().add(textArea);
		frame.getContentPane().add(button_1);
	}
	
	public int getResult() {
		return result;
	}

	public void setVisable() {
		frame.setVisible(true);
	}
}
