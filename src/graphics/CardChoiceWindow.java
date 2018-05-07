package graphics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gameFiles.Card;
import gameFiles.Deck;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class CardChoiceWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField cardText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Deck.fillDeck(new File(new File("").getAbsolutePath() + "\\csv\\cards.csv"));
			CardChoiceWindow dialog = new CardChoiceWindow(Deck.getAllCard(6));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CardChoiceWindow(Card c) {
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JRadioButton placeInfluence = new JRadioButton("Place Influence");
			placeInfluence.setBounds(22, 109, 223, 41);
			contentPanel.add(placeInfluence);
			buttonGroup.add(placeInfluence);
		}
		{
			JRadioButton rollRealignment = new JRadioButton("Roll Realignment");
			rollRealignment.setBounds(22, 155, 245, 41);
			contentPanel.add(rollRealignment);
			buttonGroup.add(rollRealignment);
		}
		{
			JRadioButton rollCoup = new JRadioButton("Roll Coup");
			rollCoup.setBounds(22, 201, 157, 41);
			contentPanel.add(rollCoup);
			buttonGroup.add(rollCoup);
		}
		{
			JRadioButton runEvent = new JRadioButton("Run Event");
			runEvent.setBounds(22, 246, 165, 41);
			contentPanel.add(runEvent);
			runEvent.setHorizontalAlignment(SwingConstants.TRAILING);
			buttonGroup.add(runEvent);
		}
		

		
		cardText = new JTextField();
		cardText.setEditable(false);
		cardText.setBounds(22, 28, 370, 41);
		cardText.setText(c.toString());
		contentPanel.add(cardText);
		cardText.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Accept");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(buttonGroup.getSelection().toString());
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				System.out.println(buttonGroup.getSelection());
			}
		}
	}

	public String getResult() {
		while (buttonGroup.getSelection().equals(null)) {
			continue;
		}
		return buttonGroup.getSelection().toString();
	}
}
