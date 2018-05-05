package gameFiles;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * UIGraphic is a implementation of UICore that will have a graphical interface an allow for a more
 * traditional windows interface.
 * @author Mark Wolgin
 * @author Josh Davis
 *
 */
public class UIGraphic implements UICore {

	/**
	 * Board currentBoard is the current state of world.  A reference point was placed here to allow for easy access.
	 */
	private Board currentBoard;
	/**
	 * The path the game currently resides in to allow for the image processing to work
	 */
	private String absPath;
	
	/**
	 * Will hold all paths to the images
	 */
	private ArrayList<String> paths = new ArrayList<String>();
	
	/**
	 * The window object for the game
	 */
	private Window window;
	
	/* Used to retrieve the most recent version of the Board
	 * (non-Javadoc)
	 * @see gameFiles.UICore#updateBoard(gameFiles.Board)
	 */
	@Override
	public void updateBoard(Board b) {
		currentBoard = b;
		absPath = new File("").getAbsolutePath();
	}
	
	/**
	 * Construct a UIGraphic
	 */
	public UIGraphic() {
		try {
			window = new Window();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Will output the new information.
	 * (non-Javadoc)
	 * @see gameFiles.UICore#updateUI()
	 */
	@Override
	public void updateUI() {
		window.rePaintAll(currentBoard);
	}

	@Override
	public int promptSelectCard(Side side) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String promptUSA() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String promptUSSR() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void indicateNoCards() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void promptCardChoiceResult(int pickResult, Card choice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void announce(String s) {
		System.out.println(s);
		
	}

	@Override
	public Country promptValidInfluenceTarget(Side side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scanner getScanner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country promptExceptionInfluenceTarget(Side side) {
		// TODO Auto-generated method stub
		return null;
	}

}
