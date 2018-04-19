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
 * Class for main game functions, contains ArrayLists for countries
 * @author Mark Wolgin
 * @author Josh Davis
 *
 */
/**
 * @author Mark
 *
 */
public class Board {
	
	/**
	 * World is an ArrayList that holds all of the Countries that make up the world
	 */
	private ArrayList<Country> world;
	/**
	 * int turn is the current turn of the game
	 */
	private int turn;
	/**
	 * int actionRound is the current action round of the game
	 */
	private int actionRound;
	/**
	 * int victoryPoints hold the current victory points as a number line from [-20, 20]
	 */
	private int victoryPoints;
	/**
	 * int defcon is the current defcon level
	 */
	private int defcon;
	/**
	 * int USMilitaryOps is how many military ops the US has done in a turn
	 */
	private int USMilitaryOps;
	/**
	 * int USSRMilitaryOps is how many military ops the US has done in a turn
	 */
	private int USSRMilitaryOps;
	/**
	 * boolean playerTurn holds the current turn
	 */
	private boolean playerTurn;
	/**
	 * The file information for the card.csv
	 */
	private File cardCSV;
	/**
	 * The file information for the contries.cvs
	 */
	private File continentCSV;
	/**
	 * The Player USA
	 */
	private Player USA;
	/**
	 * The Player USSR
	 */
	private Player USSR;
	
	/**
	 * Constructor for the board object with default values
	 */
	public Board() {
		//world = new ArrayList<Country>();
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
		Map.setFile(continentCSV);
		Map.fillCountries();
		Map.connectCountries();
		Deck.fillDeck(cardCSV);
		USSR.dealCards(-1);
		// More code to follow....
	}
	
	/**
	 * Returns the country object that matches the ISO string given
	 * @param check the string with the ISO code
	 * @return Country object that matches
	 */
	public Country getCountry(String check) {
		for (Country c : Map.getWorld()) {
			if (c.getISO().equals(check)) {
				return c;
			}
		}
		return null;
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
	 * @return the int number of victory points currently on the board
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}

	/**
	 * @return the current defcon level int
	 */
	public int getDefcon() {
		return defcon;
	}

	/**
	 * Get a player from a side
	 * @param side
	 * @return the Player object associated with the side
	 */
	public Player getPlayer(Side side) {
		if (side.equals(Side.USA)) return USA;
		else return USSR;
	}

	/**
	 * Improves the Defcon staus by one
	 */
	public void improveDefconStatus() {
		if (defcon < 5) defcon ++;
	}

	/**
	 * Deals the cards to the players hand
	 */
	public void dealCards() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Runs the Headline phase
	 */
	public void headlinePhase() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Runs the Action Round
	 */
	public void actionRound() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Handles the military operations effect
	 */
	public void checkMilitaryOperationsStatus() {
		victoryPoints += USMilitaryOps - 5;
		victoryPoints += 5 - USSRMilitaryOps;
		
	}

	/**
	 * Passes the china card if it needs to be passed
	 */
	public void flipChinaCard() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Advances the turn by one and adds the cards to the deck
	 */
	public void advanceTurn() {
		turn ++;
		Deck.addCards(turn);
	}

	/**
	 * Handles the final scoring if it gets to that point.
	 */
	public void finalScoring() {
		if (turn < 11) {
			//Nothing happens, game progresses as normal
		}
		else {
			
		}
		
	}

	public int getTurn() {
		return turn;
	}
	
}
