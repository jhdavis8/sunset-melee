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
	
}
