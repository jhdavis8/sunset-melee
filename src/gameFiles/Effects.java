/**
 * 
 */
package gameFiles;

/**
 * Container for all effects for all cards
 * @author Mark Wolgin
 * @author Josh Davis
 *
 */
public class Effects {
	
	/**
	 * currentBoard to modify by effects
	 */
	private Board currentBoard;
	
	/**
	 * Construct the effects class
	 * @param Board object to link to
	 */
	public Effects(Board b) {
		currentBoard = b;
	}
	
	/**
	 * Enact (as a side effect) an effect by its ID (Note: we will change this to a bunch of private methods
	 * @param ID int ID of the effect
	 */
	public void getEffect(int ID) {
		switch (ID) {
			case 4:
				if (currentBoard.defcon > 1) currentBoard.defcon--;
				currentBoard.victoryPoints -= 5 - currentBoard.defcon;
				break;
		}
	}
	
	/**
	 * Enacts the scoring card effect by its ID
	 * @param ID int ID of the effect
	 */
	public void getScoringEffect(int ID) {
		
	}
	
}
