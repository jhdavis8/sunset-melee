/**
 * 
 */
package gameFiles;

import java.util.ArrayList;

/**
 * @author Mark
 *
 */
public class Board {
	
	private ArrayList<Card> deck;
	private ArrayList<Card> discard;
	private int turn;
	private int actionRound;
	private int victoryPoints;
	private int defcon;
	private int USMilitaryOps;
	private int USSRMilitaryOps;
	private boolean playerTurn;
	
	public Board() {
		deck = new ArrayList<Card>();
		discard = new ArrayList<Card>();
		turn = 0;
		actionRound = 0;
		victoryPoints = 0;
		defcon = 0;
		USMilitaryOps = 0;
		USSRMilitaryOps = 0;
		playerTurn = false;
	}
	
	public void setUp() {
		deck = fillDeck();
		defcon = 5;
		// More code to follow....
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
		toReturn += "REQUIRED MILITARY\nOPS[" + USMilitaryOps + ", " + USSRMilitaryOps + "]";
		toReturn += "VICTORY POINTS:" + victoryPoints + "\n";
		toReturn += "DEFCON:" + defcon + "\n";
		toReturn += "CURRENT PLAYER:";
		if (!playerTurn) toReturn += "USSR\n";
		else toReturn += "USA\n";
		
		return toReturn;
	}
}
