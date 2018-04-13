/**
 * 
 */
package gameFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

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
		defcon = 5;
		cardCSV = new File(f1);
		continentCSV = new File(f2);
		this.fillCountries();
		this.fillDeck();
		// More code to follow....
	}

	private void fillCountries() {
		
		Scanner scan = null;
		
		try {
			scan = new Scanner(continentCSV);
			scan.useDelimiter("[,|\n]");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		String[] tempCountry = new String[5];
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
			
			world.add(new Country(tempCountry[2], tempCountry[1], stbNum, 0, 0, bg, cont));
			
		}
	}
	
	public void placeInfluence(Side side, String cont, int value) {
		int position = 0;
		for (int k = 0; k < world.size(); k ++) {
			if (world.get(k).getISO().equals(cont)) position = k;
		}
		
		
		Country c = world.get(position);
		c.modifyInfluence(value, side);
		world.set(position, c);
		
	}
	
	public void rollCoup(Side side, String cont, int value) {
		
	}
	
	public void rollRealignment(Side side, String cont, int value) {
		
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


	/**
	 * @return The complete deck as read from the .csv
	 */
	private void fillDeck() {
		Scanner scan = null;
		
		try {
			scan = new Scanner(cardCSV);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String[] tempCard = new String[7];
		
		scan.useDelimiter("[,|\n]");
		boolean sc = false;
		
		
		while(scan.hasNext()) {
			for (int k = 0; k < tempCard.length; k ++) {
				tempCard[k] = scan.next();
			}
			
			if (tempCard[3].equals("Y")) sc = true;
			
			if (tempCard[1].endsWith("*")) tempCard[1] = tempCard[1].substring(0, tempCard[1].length() - 1);
			
			if (tempCard[4].equals("Turn")) {
				deck.add(new TurnCard(tempCard[1], tempCard[6], Integer.parseInt(tempCard[0]), 
						Integer.parseInt(tempCard[0]), Side.valueOf(tempCard[5]), sc, Integer.parseInt(tempCard[2])));
			}
			else {
				deck.add(new ScoringCard(tempCard[1], tempCard[6], Integer.parseInt(tempCard[0]), Integer.parseInt(tempCard[0])));
			}
		}		

	}
	
	public int getVictoryPoints() {
		return victoryPoints;
	}

	public ArrayList<Country> getWorld() {
		return world;
	}

	public boolean isPlayerTurn() {
		return playerTurn;
	}

	public int getDefcon() {
		return defcon;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	
}
