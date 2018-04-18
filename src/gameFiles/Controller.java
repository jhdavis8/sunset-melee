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
		UIText UI = new UIText();
		
		initialize(game);
		System.out.println(game.getCountry("CAN").getUSInfluence());
		
		
		UI.updateBoard(game);
		UI.updateUI();
		UI.runUI();
		turn(game);
	}
	
	/**
	 * Initializes board object using csv files and starting influences 
	 * @param b Board obj to initialize 
	 */
	private static void initialize(Board b) {
		b.setUp("\\csv\\cards.csv", "\\csv\\countries.csv");
		b.getPlayer(Side.USSR).placeInfluence(b.getCountry("DDR"), 3);
		b.getPlayer(Side.USSR).placeInfluence(b.getCountry("FIN"), 1);
		b.getPlayer(Side.USA).placeInfluence(b.getCountry("GBR"), 5);
		b.getPlayer(Side.USA).placeInfluence(b.getCountry("CAN"), 2);
	}
	
	public static void callInitialize(Board b) {
		initialize(b);
	}
	
	private static void turn(Board game) {
		game.improveDefconStatus();
		game.dealCards();
		game.headlinePhase();
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
		return Side.UNK;
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
}
