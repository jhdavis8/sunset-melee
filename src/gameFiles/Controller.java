/**
 * 
 */
package gameFiles;

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
		// TODO Auto-generated method stub
		System.out.println("Hello, Josh");
		
		Board game = new Board();
		game.setUp();
		System.out.println(game);
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
