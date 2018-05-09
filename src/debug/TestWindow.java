package debug;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gameFiles.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;

public class TestWindow extends JFrame {

	private JPanel contentPane;
	private JTextField cardSelectorHeader;
	private Card currentSelectedCard;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deck.fillDeck(new File(new File("").getAbsolutePath() + "\\csv\\cards.csv"));
					TestWindow frame = new TestWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		currentSelectedCard = null;
		cardSelectorHeader = new JTextField("Card Viewer");
		JTextArea cardSelectorTextArea = new JTextArea();
		JComboBox<String> cardSelector = new JComboBox<String>();
		cardSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sel = cardSelector.getSelectedIndex();
				currentSelectedCard = Deck.getEarlyCards().get(sel);
			cardSelectorTextArea.setText(currentSelectedCard.description);	
			}
		});

		
		cardSelector.setBounds(26, 95, 350, 39);
		contentPane.add(cardSelector);

		for (Card c : Deck.getEarlyCards()) {
			cardSelector.addItem(c.cardNum + " - " + c.name);
		}
		
		cardSelectorHeader.setEditable(false);
		cardSelectorHeader.setBounds(26, 28, 236, 39);
		contentPane.add(cardSelectorHeader);
		cardSelectorHeader.setColumns(10);
		
		cardSelectorTextArea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cardSelectorTextArea.setLineWrap(true);
		cardSelectorTextArea.setWrapStyleWord(true);
		cardSelectorTextArea.setText("");
		cardSelectorTextArea.setBounds(26, 162, 350, 200);
		contentPane.add(cardSelectorTextArea);
	}
	
	public Card getCard() {
		while (currentSelectedCard == null) {
			currentSelectedCard = null;
		}
		return currentSelectedCard;
	}
}
