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
	 * boolean playerTurn holds the current turn
	 * True = USA, False = USSR
	 * TODO Change to a Side type rather than boolean
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
		turn = 0;
		actionRound = 0;
		victoryPoints = 0;
		defcon = 0;
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
		// TODO More code to follow....
	}
	
	/**
	 * @return a string formatted for the country 
	 */
	public String toString() {
		
		String toReturn = "";
		toReturn += "TURN:" + turn + ", ACTION ROUND:" + actionRound + "\n";
		toReturn += "REQUIRED MILITARY\n\tOPS[" + USA.getMilOps() + ", " + USSR.getMilOps() + "]\n";
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
	 * Adjust the current victory points (- is USA, + is USSR)
	 * @param i amount to add to victoryPoints
	 */
	public void modifyVictoryPoints(int i) {
		victoryPoints += i;
	}

	/**
	 * @return the current defcon level int
	 */
	public int getDefcon() {
		return defcon;
	}
	
	/**
	 * Adjust the current Defcon status
	 * @param i amount to add to the Defcon status
	 */
	public void modifyDefcon(int i) {
		defcon += i;
		if  (defcon >= 6) {
			defcon = 5;
		}
	}

	/**
	 * Get a player from a side
	 * @param side Side to get player for
	 * @return the Player object associated with the side
	 */
	public Player getPlayer(Side side) {
		if (side.equals(Side.USA)) return USA;
		else return USSR;
	}

	/**
	 * Deals the cards to the players hand
	 */
	public void dealCards() {
		USA.dealCards(turn);
		USSR.dealCards(turn);
	}

	/**
	 * Runs the Action Round
	 * @param ui UICore object to use
	 */
	public void actionRounds(UICore ui) {
		ui.announce("Actions rounds phase");
		int loopCount;
		if (this.getTurn() > 3) {
			loopCount = 7;
		} else {
			loopCount = 6;
		}
		int cardNum;
		playerTurn = true;
		int pickResult;
		Card card = null;
		for (int i = 1; i <= loopCount; i++) {
			actionRound = i;
			ui.announce("Entering action round " + i + "!");
			if (USSR.hasCards()) {
				cardNum = ui.promptSelectCard(Side.USSR);
				card = USSR.getHand().get(cardNum);
				pickResult = USSR.playCard(card);
				ui.promptCardChoiceResult(pickResult, card);
				this.handleDiscard(card, Side.USSR);
			}
			else {
				ui.indicateNoCards();
			}
			playerTurn = !playerTurn;
			if (USA.hasCards()) {
				cardNum = ui.promptSelectCard(Side.USA);
				card = USA.getHand().get(cardNum);
				pickResult = USA.playCard(card);
				ui.promptCardChoiceResult(pickResult, card);
				this.handleDiscard(card, Side.USA);
			}
			else {
				ui.indicateNoCards();
			}
			playerTurn = !playerTurn;
		}
	}

	/**
	 * Handles the military operations effect
	 */
	public void checkMilitaryOperationsStatus() {
		victoryPoints += USA.getMilOps() - 5;
		victoryPoints += 5 - USSR.getMilOps();
		
	}

	/**
	 * Passes the china card if it needs to be passed
	 */
	public void flipChinaCard() {
		boolean usedChinaCard = false;
		ChinaCard card = null;
		for (Card c : Deck.getDiscard()) {
			if (c.cardNum == 006) {
				usedChinaCard = true;
				card = (ChinaCard) c;
			}
		}
		if (!usedChinaCard) {
			
		}
		else {
			Side side = null;
			if  (card.getCurrentHolder().equals(Side.USA)) side = Side.USSR;
			else side = Side.USA;
			card.setCurrentHolder(side);
			getPlayer(side).giveCard(card); // Josh will impliment
			Deck.getDiscard().remove(card);
		}
		
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
			return;
		}
		else {
			try {
				Effects.getScoringEffect(2);
			}
			catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Discards the specified card from the side's hand
	 * @param card card to move to discard
	 * @param side side whose hand has the card
	 */
	public void handleDiscard(Card card, Side side) {
		getPlayer(side).getHand().remove(card);
		
		TurnCard tC = null;
		ScoringCard sC = null;
		
		if (card instanceof TurnCard) {
			tC = (TurnCard) card;
			// Turn Card
			if (tC.getSpecialCard() && tC.getSide().equals(side)) { 
				Deck.getDead().add(tC);
			}
			else {
				Deck.getDiscard().add(tC);
			}
		}
		else {
			sC = (ScoringCard) card;
			Deck.getDiscard().add(sC);
		}
	}
	
	/**
	 * @return the current turn
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * @return the world
	 */
	public ArrayList<Country> getWorld() {
		return world;
	}

	/**
	 * @return the actionRound
	 */
	public int getActionRound() {
		return actionRound;
	}

	/**
	 * @return the uSMilitaryOps
	 */
	public int getUSMilitaryOps() {
		return USA.getMilOps();
	}

	/**
	 * @return the uSSRMilitaryOps
	 */
	public int getUSSRMilitaryOps() {
		return USSR.getMilOps();
	}

	/**
	 * @return the playerTurn
	 */
	public boolean isPlayerTurn() {
		return playerTurn;
	}

	/**
	 * @return the cardCSV
	 */
	public File getCardCSV() {
		return cardCSV;
	}

	/**
	 * @return the continentCSV
	 */
	public File getContinentCSV() {
		return continentCSV;
	}

	/**
	 * @return the uSA
	 */
	public Player getUSA() {
		return USA;
	}

	/**
	 * @return the uSSR
	 */
	public Player getUSSR() {
		return USSR;
	}

	/**
	 * @return the player currently playing
	 */
	public Side getCurrentPlayer() {
		if (playerTurn) return Side.USSR;
		else return Side.USA;
	}

	/**
	 * Passes control of influence placement to the player
	 * @param tCard turnCard used to place influence
	 * @param ui UICore object to use for input
	 */
	public void placeInfluence(TurnCard tCard, UICore ui) {
		if (playerTurn) USSR.placeInfluence(tCard, ui);
		else USA.placeInfluence(tCard, ui);
	}

	public void rollCoup(TurnCard tCard, UICore ui) {
		boolean battleground;
		if (playerTurn) battleground = USSR.rollCoup(tCard, ui, defcon);
		else battleground = USA.rollCoup(tCard, ui, defcon);
		if (battleground) {
			ui.announce("ALERT: Defcon decremented!");
			this.modifyDefcon(-1);
		}
	}

	public void rollRealignment(TurnCard tCard, UIText ui) {
		if (playerTurn) USSR.rollRealignment(tCard, ui, defcon);
		else USA.rollRealignment(tCard, ui, defcon);
		
	}


}
