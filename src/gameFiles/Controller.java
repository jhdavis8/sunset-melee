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
		game.setUp("C: ", "C: ");
		UIText UI = new UIText();
		UI.updateBoard(game);
		System.out.println(UI.updateUI());
		
		
		Continents[] WestE = {Continents.WEE};
		Country c = new Country("United Kingdom", "GBR", 5, 5, 0, false, WestE);
		System.out.println(c);
	}

	private void initialize(Board b) {
		
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
