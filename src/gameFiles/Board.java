/**
 * 
 */
package gameFiles;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Mark
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
	}
	
	public void setUp(String f1, String f2) {
		deck = fillDeck();
		defcon = 5;
		cardCSV = new File(f1);
		continentCSV = new File(f2);
		world = fillCountries();
		// More code to follow....
	}
	
	private ArrayList<Country> fillCountries() {
		return null;
	}

	/**
	 * @return The complete deck as read from the .csv
	 */
	private ArrayList<Card> fillDeck() {
		
		return null;
	}

	public void placeInfluence(Side side, Country cont, int value) {
		
	}
	
	public void rollCoup(Side side, Country cont, int value) {
		
	}
	
	public void rollRealignment(Side side, Country cont, int value) {
		
	}
	
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

	public ArrayList<Country> getWorld() {
		return world;
	}

	public boolean isPlayerTurn() {
		return playerTurn;
	}
	
	
	
}
