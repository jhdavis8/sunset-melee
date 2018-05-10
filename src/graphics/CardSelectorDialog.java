package graphics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import gameFiles.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A version of CardSelectorWindow in Dialog form
 * @author Mark Wolgin
 *
 */
@Deprecated
public class CardSelectorDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Deck.fillDeck(new File(new File("").getAbsolutePath() + "\\csv\\cards.csv"));
			CardSelectorDialog dialog = new CardSelectorDialog(Deck.getDeck());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param contentPane 
	 */
	public CardSelectorDialog(ArrayList<Card> opts) {
		JTextArea cardDesBox = new JTextArea();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox<String> cardSelector = new JComboBox<String>();
		cardSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardDesBox.setText(Deck.getCard((String) cardSelector.getSelectedItem()).description);
			}
		});
		cardSelector.setBounds(12, 31, 408, 22);
		for (Card c : opts) {
			cardSelector.addItem(c.name);
		}
		contentPanel.add(cardSelector);
		
		cardDesBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cardDesBox.setLineWrap(true);
		cardDesBox.setWrapStyleWord(true);
		cardDesBox.setEditable(false);
		cardDesBox.setBounds(22, 66, 387, 139);
		contentPanel.add(cardDesBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				buttonPane.add(okButton);
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	@Deprecated
	public void actionPerformed(ActionEvent e) {
		System.out.println("Before");
		this.setVisible(true);
		System.out.println("and After");
	}
}
