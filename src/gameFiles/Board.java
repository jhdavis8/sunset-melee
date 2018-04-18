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
public class Board {
	
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
		Deck.fillDeck(cardCSV);
		// More code to follow....
	}

	/**
	 * Builds links between all countries
	 */
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
	 * @return the ArrayList<Country> of all countries on the board
	 */
	public ArrayList<Country> getWorld() {
		return world;
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

	public void improveDefconStatus() {
		if (defcon < 5) defcon ++;
	}

	public void dealCards() {
		// TODO Auto-generated method stub
		
	}

	public void headlinePhase() {
		// TODO Auto-generated method stub
		
	}

	public void actionRound() {
		// TODO Auto-generated method stub
		
	}

	public void checkMilitaryOperationsStatus() {
		victoryPoints += USMilitaryOps - 5;
		victoryPoints += 5 - USSRMilitaryOps;
		
	}

	public void flipChinaCard() {
		// TODO Auto-generated method stub
		
	}

	public void advanceTurn() {
		turn ++;
	}

	public void finalScoring() {
		if (turn < 11) {
			//Nothing happens, game progresses as normal
		}
		else {
			
		}
		
	}
	
}
