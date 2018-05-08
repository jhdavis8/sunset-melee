package graphics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gameFiles.Country;
import gameFiles.Map;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CountrySelectorDailog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField header;
	private JTextField countryInput;
	private JTextField countryName;
	
	private Country country;
	private boolean ableToReturn = false;

	/**
	 * Create the dialog.
	 */
	public CountrySelectorDailog() {
		country = null;
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			header = new JTextField("Please Input Country 3-Digit ISO");
			header.setFont(new Font("Tahoma", Font.PLAIN, 24));
			header.setEditable(false);
			header.setBounds(26, 10, 366, 39);
			contentPanel.add(header);
			header.setColumns(10);
		}
		
		countryInput = new JTextField();
		countryInput.setBounds(26, 56, 120, 39);
		contentPanel.add(countryInput);
		countryInput.setColumns(10);
		{
			countryName = new JTextField();
			countryName.setEditable(false);
			countryName.setBounds(159, 56, 233, 39);
			contentPanel.add(countryName);
			countryName.setColumns(10);
		}
		
		JTextArea countryOutput = new JTextArea();
		countryOutput.setLineWrap(true);
		countryOutput.setWrapStyleWord(true);
		countryOutput.setFont(new Font("Tahoma", Font.PLAIN, 24));
		countryOutput.setEditable(false);
		countryOutput.setBounds(26, 105, 366, 150);
		contentPanel.add(countryOutput);
		{
			JPanel buttonPane = new JPanel();
			JButton cancelButton = new JButton("Confirm");
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Refresh");

				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(countryInput.getText());
						if (Map.getCountry(countryInput.getText()) != null) {
							country = Map.getCountry(countryInput.getText());
							countryName.setText(country.getFullName());
							countryOutput.setText(country.toString());
							cancelButton.setVisible(true);						}
						else {
							countryName.setText("Not a valid country!");
							countryOutput.setText("");
							country = null;
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{

				cancelButton.setVisible(false);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ableToReturn = true;
						System.out.println(ableToReturn);
						System.out.println(country);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private boolean toBreak() {
		if (ableToReturn) {
			return true;
		}
		else return false;
	}
	
	public Country getCountry() {
		while (ableToReturn == false) { 
			if (toBreak()) break;
		}
		return country;
	}
	
}
