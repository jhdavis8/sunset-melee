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
		
		Player p1 = new Player(Side.USA);
		
		System.out.println(p1);
		
		UI.updateBoard(game);
		UI.updateUI();
		UI.runUI();
	}

	private static void initialize(Board b) {
		b.setUp("\\csv\\cards.csv", "\\csv\\countries.csv");
		b.placeInfluence(Side.USSR, b.getCountry("DDR"), 3);
		b.placeInfluence(Side.USSR, b.getCountry("FIN"), 1);
		b.placeInfluence(Side.USA, b.getCountry("GBR"), 5);
		b.placeInfluence(Side.USA, b.getCountry("CAN"), 2);
	}
	
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
	
	private Side getVictor(Board b) {
		return Side.UNK;
	}
	
	private void updateUI(Board b, UICore ui) {
		
	}
	
	private boolean playerTurn(Board b) {
		return false;
	}
}
