package graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import gameFiles.*;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * A generic window for geting county inforation from the user.
 * @author Mark Wolgin
 *
 */
@Deprecated
public class GenericInputWindow {

	private JFrame frame;
	private JTextField countryName;
	private Country country;


	/**
	 * Create the application.
	 * @param string 
	 */
	public GenericInputWindow(String string) {
		country = null;
		initialize(string);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param string 
	 */
	private void initialize(String string) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setBounds(100, 100, 450, 385);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		frame.setVisible(true);
		
		JButton confirm = new JButton("Confirm");
		JTextArea countryInput = new JTextArea();
		springLayout.putConstraint(SpringLayout.WEST, countryInput, 9, SpringLayout.WEST, frame.getContentPane());
		JTextArea countryOutput = new JTextArea();
		springLayout.putConstraint(SpringLayout.WEST, countryOutput, 7, SpringLayout.EAST, countryInput);
		springLayout.putConstraint(SpringLayout.EAST, countryOutput, -24, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, countryInput, 0, SpringLayout.SOUTH, countryOutput);
		springLayout.putConstraint(SpringLayout.NORTH, countryOutput, 110, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, countryOutput, -31, SpringLayout.SOUTH, frame.getContentPane());
		JButton select = new JButton("Select");
		springLayout.putConstraint(SpringLayout.WEST, select, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, select, -232, SpringLayout.EAST, frame.getContentPane());
		countryName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, countryInput, 0, SpringLayout.NORTH, countryName);
		springLayout.putConstraint(SpringLayout.EAST, countryInput, -6, SpringLayout.WEST, countryName);
		springLayout.putConstraint(SpringLayout.WEST, countryName, 215, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, countryName, -24, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, countryName, -6, SpringLayout.NORTH, countryOutput);
		frame.getContentPane().add(countryInput);
		frame.getContentPane().add(countryName);
		countryName.setColumns(10);
		

		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				country = Map.getCountry(countryInput.getText());
				frame.dispose();
			}
		});
		frame.getContentPane().add(countryOutput);
		

		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = countryInput.getText().toUpperCase();
				if (Map.getCountry(input) != null) {
					country = Map.getCountry(input);
					System.out.println(country);
					countryName.setText(country.getFullName());
					countryOutput.setText(country.toString());
					springLayout.putConstraint(SpringLayout.WEST, confirm, -193, SpringLayout.EAST, frame.getContentPane());
					springLayout.putConstraint(SpringLayout.SOUTH, confirm, 0, SpringLayout.SOUTH, frame.getContentPane());
					springLayout.putConstraint(SpringLayout.EAST, confirm, 0, SpringLayout.EAST, frame.getContentPane());
					frame.getContentPane().add(confirm);
				}
				else {
					countryOutput.setText("Not a valid country to place influence in!");
				}
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, select, 0, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(select);
		
		JLabel lblPromptStringHere = new JLabel(string);
		lblPromptStringHere.setFont(new Font("Tahoma", Font.PLAIN, 15));
		springLayout.putConstraint(SpringLayout.NORTH, lblPromptStringHere, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblPromptStringHere, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblPromptStringHere, -6, SpringLayout.NORTH, countryInput);
		springLayout.putConstraint(SpringLayout.EAST, lblPromptStringHere, 0, SpringLayout.EAST, countryName);
		frame.getContentPane().add(lblPromptStringHere);
		

	}

	/**
	 * Returns the selected Country
	 * @return Country
	 */
	public Country getResult() {
		while (country == null) {
			country = null;
		}
		Country c = country;
		//country = null;
		return c;
	}
}
