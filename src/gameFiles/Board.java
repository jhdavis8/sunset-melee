/**
 * 
 */
package gameFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Class for main game functions, contains ArrayLists for game parts
 * @author Mark Wolgin
 * @author Josh Davis
 *
 */
public class Board {
	
	private ArrayList<Card> deck;
	private ArrayList<Card> discard;
	private ArrayList<Country> world;
	private int turn;
	private int actionRound;
	private int victoryPoints;
	private int defcon;
	private int USMilitaryOps;
	private int USSRMilitaryOps;
	private boolean playerTurn;
	private File cardCSV;
	private File continentCSV;
	private Player USA;
	private Player USSR;
	
	/**
	 * Constructor for the board object with default values
	 */
	public Board() {
		deck = new ArrayList<Card>();
		discard = new ArrayList<Card>();
		world = new ArrayList<Country>();
		turn = 0;
		actionRound = 0;
		victoryPoints = 0;
		defcon = 0;
		USMilitaryOps = 0;
		USSRMilitaryOps = 0;
		playerTurn = false;
		USA = new Player(Side.USA);
		USSR = new Player(Side.USSR);
	}
	
	/**
	 * Sets up the board using two file paths
	 * @param f1 relative path to cards.csv
	 * @param f2 relative path to countries.csv
	 */
	public void setUp(String f1, String f2) {
		defcon = 5;
		String absPath = new File("").getAbsolutePath();
		cardCSV = new File(absPath + f1);
		continentCSV = new File(absPath + f2);
		this.fillCountries();
		this.connectCountries();
		this.fillDeck();
		// More code to follow....
	}

	private void connectCountries() {
		for (Country c : world) {
			c.connectCountries(world);
		}
		
	}

	/**
	 * Builds all the country objects by the csv data
	 */
	private void fillCountries() {
		
		Scanner scan = null;
		
		try {
			scan = new Scanner(continentCSV);
			scan.useDelimiter("[,|\n]");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		String[] tempCountry = new String[6];
		int stbNum = 0;
		boolean bg = false;
		
		while (scan.hasNext()) {
			for (int k = 0; k < tempCountry.length; k ++) {
				tempCountry[k] = scan.next();
			}
			stbNum = new Integer(tempCountry[3]);
			
			if (tempCountry[4].equals("F")) {
				bg = false;
			}
			else {
				bg = true;
			}
			
			String[] tCont = tempCountry[0].split("[.]");
			Continents[] cont = new Continents[tCont.length];
			
			for (int k = 0; k < cont.length; k ++) {
				cont[k] = Continents.valueOf(tCont[k]);
			}
			
			String[] conCountry = tempCountry[5].split("[.|\r|\n]");
			
			world.add(new Country(tempCountry[2], tempCountry[1], stbNum, 0, 0, bg, cont, conCountry));
			
		}
	}
	
	/**
	 * Returns the country object that matches the ISO string given
	 * @param check the string with the ISO code
	 * @return Country object that matches
	 */
	public Country getCountry(String check) {
		for (Country c : world) {
			if (c.getISO().equals(check)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Add value influence to country on side
	 * @param side side to add for
	 * @param country country to add to
	 * @param value int amount to add
	 */
	public void placeInfluence(Side side, Country country, int value) {
		country.modifyInfluence(value, side);		
	}
	
	/**
	 * Rolls a coup by the args given
	 * @param side Side to roll for
	 * @param country Country to roll on
	 * @param value int value to apply
	 */
	public void rollCoup(Side side, String country, int value) {
		// To fill tonight
	}
	
	/**
	 * rolls a realignment by the args given
	 * @param side Side to roll for
	 * @param country Country to roll on
	 * @param value int value to use
	 */
	public void rollRealignment(Side side, Country country, int value) {
		Random randy = new Random();
		int usRoll = randy.nextInt(7);   // ADD ONE for each adjacent country and if has more influence then opponent
		System.out.println("USA Rolls " + usRoll);
		int ussrRoll = randy.nextInt(7); // ADD ONE for each adjacent country and if has more influence then opponent
		System.out.println("USSR Rolls " + ussrRoll);
		int diff = 0;
		
		if (side.equals(Side.USA) && (usRoll > ussrRoll)) {
			diff = -1 * (usRoll - ussrRoll);
			country.modifyInfluence(diff, Side.USSR);
		}
		else if (side.equals(Side.USA) && (usRoll <= ussrRoll)) {
			diff = 0;
		}
		else if (side.equals(Side.USSR) && (usRoll > ussrRoll)) {
			diff = -1 * (ussrRoll - usRoll);
			country.modifyInfluence(diff, Side.USA);
		}
		else {
			diff = 0;
		}
	}
	
	/**
	 * @return a string formatted for the country 
	 */
	public String toString() {
		
		String toReturn = "";
		toReturn += "TURN:" + turn + ", ACTION ROUND:" + actionRound + "\n";
		toReturn += "REQUIRED MILITARY\n\tOPS[" + USMilitaryOps + ", " + USSRMilitaryOps + "]\n";
		toReturn += "VICTORY POINTS:" + victoryPoints + "\n";
		toReturn += "DEFCON:" + defcon + "\n";
		toReturn += "CURRENT PLAYER:";
		if (!playerTurn) toReturn += "USSR\n";
		else toReturn += "USA\n";
		
		return toReturn;
	}

	/**
	 * Builds complete deck of card objects as read from the .csv
	 */
	private void fillDeck() {
		Scanner scan = null;
		
		try {
			scan = new Scanner(cardCSV);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String[] tempCard = new String[8];
		
		scan.useDelimiter("[,|\n]");
		boolean sc = false;
		
		
		while(scan.hasNext()) {
			for (int k = 0; k < tempCard.length; k ++) {
				tempCard[k] = scan.next();
			}
			
			if (tempCard[3].equals("Y")) sc = true;
			
			if (tempCard[1].endsWith("*")) tempCard[1] = tempCard[1].substring(0, tempCard[1].length() - 1);
			
			if (tempCard[4].equals("Turn")) {
				deck.add(new TurnCard(tempCard[1], tempCard[7], Integer.parseInt(tempCard[0]), 
						Integer.parseInt(tempCard[0]), Side.valueOf(tempCard[5]), sc, Integer.parseInt(tempCard[2]), tempCard[6]));
			}
			else {
				deck.add(new ScoringCard(tempCard[1], tempCard[6], Integer.parseInt(tempCard[0]),
						Integer.parseInt(tempCard[0]), tempCard [7]));
			}
		}		

	}
	
	/**
	 * 
	 * @return the int number of victory points currently on the board
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}

	/**
	 * 
	 * @return the ArrayList<Country> of all countries on the board
	 */
	public ArrayList<Country> getWorld() {
		return world;
	}

	/**
	 * 
	 * @return the current defcon level int
	 */
	public int getDefcon() {
		return defcon;
	}

	/**
	 * 
	 * @return the ArrayList deck of cards
	 */
	public ArrayList<Card> getDeck() {
		return deck;
	}

	public Card getCard(int input) {
		for (Card c : deck) {
			if (input == c.cardNum) {
				return c;
			}
		}
		return null;
	}

	public Player getPlayer(Side side) {
		if (side.equals(Side.USA)) return USA;
		else return USSR;
	}
	
}
