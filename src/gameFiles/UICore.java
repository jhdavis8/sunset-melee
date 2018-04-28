/**
 * 
 */
package gameFiles;

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
	
	public void announce(String s);
	
}
