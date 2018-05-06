package graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import gameFiles.*;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame.setBounds(100, 100, 450, 300);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JButton confirm = new JButton("Confirm");
		JTextArea countryInput = new JTextArea();
		JTextArea countryOutput = new JTextArea();
		JButton select = new JButton("Select");
		countryName = new JTextField();
		
		
		

		springLayout.putConstraint(SpringLayout.NORTH, countryInput, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, countryInput, -6, SpringLayout.NORTH, select);
		springLayout.putConstraint(SpringLayout.EAST, select, -10, SpringLayout.EAST, countryInput);
		springLayout.putConstraint(SpringLayout.WEST, countryInput, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, countryInput, 210, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(countryInput);
		

		springLayout.putConstraint(SpringLayout.NORTH, countryName, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, countryName, 5, SpringLayout.EAST, countryInput);
		springLayout.putConstraint(SpringLayout.EAST, countryName, 198, SpringLayout.EAST, countryInput);
		frame.getContentPane().add(countryName);
		countryName.setColumns(10);
		

		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, confirm, -193, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, confirm, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, confirm, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(confirm);
		

		springLayout.putConstraint(SpringLayout.NORTH, countryOutput, 6, SpringLayout.SOUTH, countryName);
		springLayout.putConstraint(SpringLayout.WEST, countryOutput, 6, SpringLayout.EAST, countryInput);
		springLayout.putConstraint(SpringLayout.SOUTH, countryOutput, 0, SpringLayout.SOUTH, countryInput);
		springLayout.putConstraint(SpringLayout.EAST, countryOutput, 0, SpringLayout.EAST, countryName);
		frame.getContentPane().add(countryOutput);
		

		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = countryInput.getText().toUpperCase();
				if (Map.getCountry(input) != null) {
					country = Map.getCountry(input);
					countryName.setText(country.getFullName());
					countryOutput.setText(country.toString());
				}
				else {
					countryOutput.setText("Not a valid country to place influence in!");
				}
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, select, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, select, 0, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(select);
		
		frame.setVisible(true);
	}

	public Country getResult() {
		return country;
	}
}
