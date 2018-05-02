/**
 * 
 */
package gameFiles;

import java.util.Scanner;

/**
 * UICore is the base of all UIElements and Interactions with the computer.
 * @author Mark Wolgin
 * @author Josh Davis
 */
public interface UICore {
	
	/**
	 * Updates the local board
	 * @param b Board
	 */
	public void updateBoard(Board b);
	
	/**
	 * Prints out the UI
	 */
	public void updateUI();
	
	/**
	 * @param side Side to have their card selected
	 * @return the card number selected
	 */
	public int promptSelectCard(Side side);
	
	/**
	 * Prompts the USA Player for input
	 * @return Returns a string
	 */
	public String promptUSA();
	
	/**
	 * Prompts the USSR Player for input
	 * @return Returns a string
	 */
	public String promptUSSR();

	/**
	 * Tells the player they can't pick a card because their hand is empty
	 */
	public void indicateNoCards();

	/**
	 * Give the player the prompting for choosing how to use a given card.
	 * If pickResult is -1, then the card is a scoring card, so show the results.
	 * If pickResult is 0, then the card is an enemy card, show the results of its
	 * effects and give the player the choices for how to play the card value.
	 * If pickResult is 1, then the card is a friendly or unk, give options for value
	 * or for effect 
	 * @param pickResult the result of the card processing
	 * @param choice the Card chosen
	 */
	public void promptCardChoiceResult(int pickResult, Card choice);
	
	/**
	 * Asks the player to pick a country to place influence in, and makes sure its valid.
	 * @param side The current player
	 * @return Country The valid country choice.
	 */
	public Country promptValidInfluenceTarget(Side side);
	
	/**
	 * Telegraphs some message to the user.
	 * @param s String message
	 */
	public void announce(String s);

	/**
	 * Returns the scanner.
	 * @return Scanner to be used in other specific instances in code.
	 */
	public Scanner getScanner();

	/**
	 * Allows for placement of influence based on coninent not other game locig
	 * @param side Side
	 * @return a County
	 */
	public Country promptExceptionInfluenceTarget(Side side);

}
