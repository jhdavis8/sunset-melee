/**
 * 
 */
package gameFiles;

import java.util.ArrayList;

/**
 * @author Mark Wolgin
 * @author Josh Davis
 *
 */
public class Controller {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board game = new Board();
		UIText UI = new UIText();
		
		initialize(game);
		
		UI.updateBoard(game);
		System.out.println(UI.updateUI());
		
	}

	private static void initialize(Board b) {
		b.setUp("C:\\Users\\Mark\\git\\sunset-melee\\cards.csv", "C:\\Users\\Mark\\git\\sunset-melee\\countries.csv");
		b.placeInfluence(Side.USSR, "DDR", 3);
		b.placeInfluence(Side.USSR, "FIN", 1);
		b.placeInfluence(Side.USA, "GBR", 5);
		b.placeInfluence(Side.USA, "CAN", 2);
	}
	
	private boolean gameEnd(Board b) {
		return false;
		
	}
	
	private void getVictor(Board b) {
		
	}
	
	private void updateUI(Board b, UICore ui) {
		
	}
	
	private boolean playerTurn(Board b) {
		return false;
		
	}
}
