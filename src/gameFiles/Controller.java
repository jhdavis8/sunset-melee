/**
 * 4/13/2018
 * Controller.java
 */
package gameFiles;

import java.util.ArrayList;

/**
 * Class for running the game, includes main function
 * @author Mark Wolgin
 * @author Josh Davis
 * 
 */
public class Controller {

	/**
	 * Main function carries out the game
	 * @param args command line args
	 */
	public static void main(String[] args) {
		Board game = new Board();
		initialize(game);
		Effects.setCurrentBoard(game);
		
		UIText UI = new UIText();
		

		
		UI.updateBoard(game);
		UI.updateUI();
		UI.runUI();
	}
	
	/**
	 * Initializes board object using csv files and starting influences 
	 * @param b Board obj to initialize 
	 */
	private static void initialize(Board b) {
		b.setUp("\\csv\\cards.csv", "\\csv\\countries.csv");
		b.getPlayer(Side.USSR).placeInfluence(Map.getCountry("DDR"), 3);
		b.getPlayer(Side.USSR).placeInfluence(Map.getCountry("FIN"), 1);
		b.getPlayer(Side.USA).placeInfluence(Map.getCountry("GBR"), 5);
		b.getPlayer(Side.USA).placeInfluence(Map.getCountry("CAN"), 2);
	}
	
	/**
	 * Calls private Controller.initialize(Board b)
	 * @param b Board
	 */
	public static void callInitialize(Board b) {
		initialize(b);
	}
	
	/**
	 * Holds the structure of the progression of the game
	 * @param game Board to use
	 */
	public static void turn(Board game, UICore UI) {
		game.improveDefconStatus();
		game.dealCards();
		headlinePhase(game, UI);
		game.actionRound();
		game.checkMilitaryOperationsStatus();
		game.flipChinaCard();
		game.advanceTurn();
		game.finalScoring();
		
	}
	
	/**
	 * Checks if the game has ended
	 * @param b the Board object
	 * @return true if the game has ended by Defcon or by Vic points
	 */
	private boolean gameEnd(Board b) {
		Boolean endGame = false;
		
		if (Math.abs(b.getVictoryPoints()) >= 20) {
			endGame = true;
		}
		else if (b.getDefcon() == 0) {
			endGame = true;
		}
		
		return endGame;
	}
	
	/**
	 * Returns the Side that has won the game
	 * @param b the Board object
	 * @return Side that has won
	 */
	private Side getVictor(Board b) {
		if (b.getVictoryPoints() > 0) {
			return Side.USSR;
		}
		else if (b.getVictoryPoints() < 0) {
			return Side.USA;
		}
		else {
			return Side.UNK;
		}
	}
	
	/**
	 * Carries out the updating of the UI (printing if textUI)
	 * @param b the Board object
	 * @param ui the UI object
	 */
	private void updateUI(Board b, UICore ui) {
		
	}
	
	/**
	 * Carries out some player turn operations?
	 * @param b the Board object
	 * @return boolean?
	 */
	private boolean playerTurn(Board b) {
		return false;
	}

	/**
	 * Runs the Headline phase
	 * @param uI 
	 * @param game 
	 */
	private static void headlinePhase(Board game, UICore UI) {
		int USCardChoice = UI.promptSelectCard(Side.USA);
		int USSRCardChoice = UI.promptSelectCard(Side.USSR);
		
		Card usCard = game.getPlayer(Side.USA).getHand().get(USCardChoice);
		Card ussrCard = game.getPlayer(Side.USSR).getHand().get(USSRCardChoice);
		
		if (usCard.getOps() < ussrCard) {
			
		}
		
	}
}
