package graphics;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;



import gameFiles.*;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Canvas;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * Holds the information for the public window.
 * @author Mark Wolgin
 * @author Josh Davis
 */
public class Window {

	/**
	 * Frame of the Window
	 */
	private JFrame frmWindowTest;

	/**
	 * The ScrollImage that holds and manages the board image
	 */
	private ScrollImage scrollImage;	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					//Controller c = new Controller();
					//c.main(args);
					window.frmWindowTest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
		this.frmWindowTest.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWindowTest = new JFrame();
		frmWindowTest.setTitle("Window Test");
		frmWindowTest.setBounds(100, 100, 791, 580);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    frmWindowTest.setSize(screenSize.width, screenSize.height);
	    frmWindowTest.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    
		frmWindowTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		scrollImage = new ScrollImage();
		frmWindowTest.getContentPane().add(scrollImage, BorderLayout.CENTER);
	}
	
	/**
	 * Produces a drop down list for selection objects
	 * @param message Message to print
	 * @param al The ArrayList of things to be put into the popUp, All Objects
	 * @return Returns a string that is the desired input
	 */
	public static String popupDropDownWindow(String message, ArrayList<?> al, Side playingSide) {
		// TODO make cool side icons
		String[] listOfStrings = new String[al.size()];
		if (al.get(0) instanceof Card) {
			Card c = null;
			for (int k = 0; k < listOfStrings.length; k ++) {
				c = (Card) al.get(k);
				listOfStrings[k] = c.name;
			}
		}
		else if (al.get(0) instanceof Country) {
			Country c = null;
			for (int k = 0; k < listOfStrings.length; k ++) {
				c = (Country) al.get(k);
				listOfStrings[k] = c.getFullName();
			}
		}
		else if (al.get(0) instanceof String) {
			String c = null;
			for (int k = 0; k < listOfStrings.length; k ++) {
				c = (String) al.get(k);
				listOfStrings[k] = c;
			}
		}
		Arrays.sort(listOfStrings);
		String path = "";
		Icon icon;
		if (playingSide == Side.USA) {
			path = "img\\board_tokens\\usa_action_round";
		} else if (playingSide == Side.USSR) {
			path = "img\\board_tokens\\ussr_action_round";
		}
		if (!path.equals("")) {
			icon = new ImageIcon(path);
		} else {
			icon = null;
		}
		String toReturn = (String) JOptionPane.showInputDialog(null, message, "Press OK to Continue", JOptionPane.QUESTION_MESSAGE,
									icon, listOfStrings, listOfStrings[0]);
		return toReturn;			
		
	}
	
	/**
	 * Produces a drop down list for countries limited by a continent
	 * @param message Message to print
	 * @param al The ArrayList of things to be put into the popUp, All Objects
	 * @param continent the continent to limit options to
	 * @return Returns a string that is the desired input
	 */
	public static String popupDropDownWindow(String message, ArrayList<?> al, Side playingSide, Continents continent) {
		// TODO make cool side icons in the dialog
		ArrayList<String> stringsInContinent = new ArrayList<String>();
		String[] listOfStrings;
		if (al.get(0) instanceof Country) {
			Country c = null;
			for (int k = 0; k < al.size(); k ++) {
				c = (Country) al.get(k);
				if (c.getContinent().contains(continent)) {
					stringsInContinent.add(c.getFullName());
				}
			}
			listOfStrings = new String[stringsInContinent.size()];
			for (int k = 0; k < stringsInContinent.size(); k++) {
				listOfStrings[k] = stringsInContinent.get(k);
			}
		} else {
			return "";
		}
		Arrays.sort(listOfStrings);
		String path = "";
		Icon icon;
		if (playingSide == Side.USA) {
			path = "img\\board_tokens\\usa_action_round";
		} else if (playingSide == Side.USSR) {
			path = "img\\board_tokens\\ussr_action_round";
		}
		if (!path.equals("")) {
			icon = new ImageIcon(path);
		} else {
			icon = null;
		}
		String toReturn = (String) JOptionPane.showInputDialog(null, message, "Press OK to Continue", JOptionPane.QUESTION_MESSAGE,
									icon, listOfStrings, listOfStrings[0]);
		return toReturn;
	}
	
	/**
	 * Produces a list of buttons for selection objects
	 * @param message Message to print
	 * @param al The ArrayList of things to be put into the popUp, All Objects
	 * @return Returns a init that is the desired input location
	 */
	public static int popupButtonWindow(String message, ArrayList<?> al) {
		String[] listOfStrings = new String[al.size()];
		if (al.get(0) instanceof Card) {
			Card c = null;
			for (int k = 0; k < listOfStrings.length; k ++) {
				c = (Card) al.get(k);
				listOfStrings[k] = c.name;
			}
		}
		else if (al.get(0) instanceof Country) {
			Country c = null;
			for (int k = 0; k < listOfStrings.length; k ++) {
				c = (Country) al.get(k);
				listOfStrings[k] = c.getFullName();
			}
		}
		else if (al.get(0) instanceof String) {
			String c = null;
			for (int k = 0; k < listOfStrings.length; k ++) {
				c = (String) al.get(k);
				listOfStrings[k] = c;
			}
		}

		int toReturn = JOptionPane.showOptionDialog(null, message, "Please Choose",
	             												JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
	             												null, listOfStrings, listOfStrings[0]);
		return toReturn;			
		
	}
	
	/**
	 * Pops up a message, called by announceUI
	 * @param message Message to be displayed
	 */
	public static void popupMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	/**
	 * Calls the repaint of all components
	 * @param b The Board
	 */
	public void rePaintAll(Board b) {
		frmWindowTest.repaint();
		scrollImage.rePaintAll(b);
	}
	
	/**
	 * Pop up a dialog to get simple user input string
	 * @param message String to show above entry box
	 * @return the user's input
	 */
	public String popupStringInputWindow(String message) {
		return JOptionPane.showInputDialog(message);
	}
}
