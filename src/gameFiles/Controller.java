/**
 * 4/13/2018
 * Controller.java
 */
package gameFiles;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JDialog;

import debug.TestWindow;
import graphics.CardChoiceWindow;
import graphics.CardSelectorWindow;



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
		UIGraphic UI = new UIGraphic(game); // TODO unify constructor signatures
//		UIText UI = new UIText(game);
		UI.updateBoard(game);
		UI.updateUI();
		Effects.setUI(UI);

		
		//Actual game running code below, commented out until UI is ready!
		try {
			//TODO NOT WORKIUNG
			Effects.getEffect(998);
			UI.updateUI();
			game.setPlayerTurn(Side.USSR);
			Effects.getEffect(999);
			game.setPlayerTurn(Side.USSR);
			UI.updateUI();
		}
		catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			System.out.println("Couldn't find cards to run the effect.");
		}
		
		for (int k = 0; k < 10; k ++) {
			turn(game, UI);
			if (game.gameEnd() || (game.getPlayer(Side.USA).endedTurnWithScoringCard() || game.getPlayer(Side.USSR).endedTurnWithScoringCard())) {
				k = 11;
			}
		}
		UI.announce("Congratulations " + getVictor(game).toString() + " You have vanquished the forces of evil in the world "
				+ "to become the one true superpower in the world. "
				+ "Thank you so much for playing our game!");
		
		//UI.runUI();
		 
	}
	
	/**
	 * Initializes board object using csv files and starting influences 
	 * @param b Board obj to initialize 
	 */
	private static void initialize(Board b) {
		b.setUp("\\csv\\cards.csv", "\\csv\\countries.csv","\\csv\\location.csv");
		b.getPlayer(Side.USSR).placeInfluence(Map.getCountry("DDR"), 3);
		b.getPlayer(Side.USSR).placeInfluence(Map.getCountry("FIN"), 1);
		b.getPlayer(Side.USSR).placeInfluence(Map.getCountry("SYR"), 1);
		b.getPlayer(Side.USSR).placeInfluence(Map.getCountry("IRQ"), 1);
		b.getPlayer(Side.USSR).placeInfluence(Map.getCountry("PRK"), 3);
		b.getPlayer(Side.USA).placeInfluence(Map.getCountry("GBR"), 5);
		b.getPlayer(Side.USA).placeInfluence(Map.getCountry("CAN"), 2);
		b.getPlayer(Side.USA).placeInfluence(Map.getCountry("IRN"), 1);
		b.getPlayer(Side.USA).placeInfluence(Map.getCountry("ISR"), 1);
		b.getPlayer(Side.USA).placeInfluence(Map.getCountry("JPN"), 1);
		b.getPlayer(Side.USA).placeInfluence(Map.getCountry("AUS"), 4);
		b.getPlayer(Side.USA).placeInfluence(Map.getCountry("PHL"), 1);
		b.getPlayer(Side.USA).placeInfluence(Map.getCountry("KOR"), 1);
		b.getPlayer(Side.USA).placeInfluence(Map.getCountry("PAN"), 1);
		b.getPlayer(Side.USA).placeInfluence(Map.getCountry("ZAF"), 1);
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
	 * @param UI the UI object to use
	 */
	public static void turn(Board game, UICore UI) {
		game.modifyDefcon(1);
		game.dealCards();
		headlinePhase(game, UI);
		game.actionRounds(UI);
		game.checkMilitaryOperationsStatus();
		game.flipChinaCard();
		game.advanceTurn();
		game.finalScoring();
	}
	
	/**
	 * Returns the Side that has won the game
	 * @param b the Board object
	 * @return Side that has won
	 */
	private static Side getVictor(Board b) {
		if (b.getPlayer(Side.USA).endedTurnWithScoringCard()) {
			return Side.USSR;
		}
		else if (b.getPlayer(Side.USSR).endedTurnWithScoringCard()) {
			return Side.USA;
		}
		else if (b.getVictoryPoints() > 0) {
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
		// TODO make this work for graphic UI
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
	 * @param game Board object to use
	 * @param UI UI object to use
	 */
	public static void headlinePhase(Board game, UICore ui) {
		System.out.println("Headline Phase");
		boolean checking = true;
		int USCardChoice = 0;
		while (checking) {
			USCardChoice = ui.promptSelectCard(Side.USA);
			if (USCardChoice >= game.getPlayer(Side.USA).getHand().size()) {
				System.out.println("Integer out of bound, please input a value within the range.");
			}
			else {
				checking = false;
			}
		}
		
		int USSRCardChoice = 0;
		checking = true;
		while (checking) {   
			USSRCardChoice = ui.promptSelectCard(Side.USSR);
			if (USSRCardChoice >= game.getPlayer(Side.USSR).getHand().size()) {
				System.out.println("Integer out of bound, please input a value within the range.");
			}
			else {
				checking = false;
			}
		}
		
		
		Card usaC = game.getPlayer(Side.USA).getHand().get(USCardChoice);
		game.handleDiscard(usaC, Side.USA);
		Card ussrC = game.getPlayer(Side.USSR).getHand().get(USSRCardChoice);
		game.handleDiscard(ussrC, Side.USSR);
		
		ScoringCard usaSC = null;
		ScoringCard ussrSC = null;
		
		TurnCard usaTC = null;
		TurnCard ussrTC = null;
		
		//Fills the correct card
		if (usaC instanceof ScoringCard) {
			usaSC = (ScoringCard) usaC;
		}
		else if (usaC instanceof TurnCard) {
			usaTC = (TurnCard) usaC;
		}
		
		if (ussrC instanceof ScoringCard) {
			ussrSC = (ScoringCard) ussrC;
		}
		else if (ussrC instanceof TurnCard) {
			ussrTC = (TurnCard) ussrC;
		}
		
		
		//Plays the correct card
		if (usaSC == null && ussrSC == null) { // 		USA TurnCard and USSR TurnCard
			if ((ussrTC.getOps() > usaTC.getOps()) && (usaTC.cardNum != 103)) {
				ussrTC.runEffect();
				ui.announce("USSR Headline Effect: "+ ussrTC.getDescription(), Side.USSR);
				usaTC.runEffect();
				ui.announce("USA Headline Effect: "+ usaTC.getDescription(), Side.USA);
			}
			else if (usaTC.cardNum != 103) {
				usaTC.runEffect();
				ui.announce("USA Headline Effect: "+ usaTC.getDescription(), Side.USA);
				ussrTC.runEffect();
				ui.announce("USSR Headline Effect: "+ ussrTC.getDescription(), Side.USSR);
			}
			else {	// If the card is num 103 Defectors, the USSR cards is invalidated
				usaTC.runEffect();
			}
			
		}
		else if (usaTC == null && ussrSC == null) { // 	USA ScoringCard and USSR TurnCard
			ussrTC.runEffect();
			usaSC.runEffect();
		}
		else if (usaSC == null && ussrTC == null) { //	USA TurnCard and USSR ScoringCArd
			usaTC.runEffect();
			ussrSC.runEffect();
		}
		else { //										USA ScoringCard and USSR ScoringCard
			usaSC.runEffect();
			ussrSC.runEffect();
		}
		
	}
}
