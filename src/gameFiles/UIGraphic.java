package gameFiles;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import graphics.*;


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
	private JFrame frame;
	
	
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
			frame = new JFrame();
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
		/*
		CardSelectorWindow csw = new CardSelectorWindow(currentBoard.getPlayer(side).getHand());
		csw.setVisable();
		while (csw.getResult() == -1) {
			System.out.println("");
		}
		*/
		
		//return csw.getResult();
		String[] hand = new String[currentBoard.getPlayer(side).getHand().size()];
		ArrayList<Card> cards = currentBoard.getPlayer(side).getHand();
		for (int i = 0; i < hand.length; i++) {
			hand[i] = cards.get(i).name;
		}
		return (int) JOptionPane.showOptionDialog(frame, "Choose a card", "Choose a card",
												 JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
												 hand, hand[0]);
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
		this.announce("Player, you have no cards remaining in your hand to draw, as such your phase will be passed untill the\n"
				+ "next turn when new cards will be dealt to you.");
		
	}

	@Override
	public void promptCardChoiceResult(int pickResult, Card choice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void announce(String s) {
		AnnounceWindow aw = new AnnounceWindow(s);
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
		
		return Map.getCountry("DDR"); // TODO Don't leave it like this
		
		/*
		String[] hand = new String[currentBoard.getPlayer(side).getHand().size()];
		ArrayList<Card> cards = currentBoard.getPlayer(side).getHand();
		for (int i = 0; i < hand.length; i++) {
			hand[i] = cards.get(i).name;
		}
		return (Country) JOptionPane.showOptionDialog(frame, "Choose valid Country ISO to influence", "Choose valid Country ISO to influence",
												 JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
												 hand, hand[0]);
		*/
	}

}
