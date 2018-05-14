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
		ArrayList<Object> hand = new ArrayList<Object>();
		hand.addAll(currentBoard.getPlayer(side).getHand());
		return Window.popupButtonWindow("Please choose a card to play", hand);
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
	public void promptCardChoiceResult(int pickResult, Card card) {
		String toReturn = ("You have selected: " + card);
		if (pickResult == -1) {
			toReturn += "\nThis is a ScoringCard, and the effect has been tallied.";
		}
		else if (pickResult == 1) {
			toReturn += "\nPlease select one off the following options for how to use this card,\n";
			toReturn += "\t1: Place Influence\n\t2: Roll for Realignment in a Country"
					+ "\n\t3: Attempt a Coup in an Enemy Country\n\t4: Play the Cards Event";
			toReturn += "\nSelection: ";
			System.out.print(toReturn);
			String stringSel = "";
			int sel = 0;
			ArrayList<Object> options = new ArrayList<Object>();
			options.add("Place Influence");
			options.add("Roll for Realignment");
			options.add("Attempt a coup");
			options.add("Play card event");
			sel = 1 + Window.popupButtonWindow(toReturn, options);
			TurnCard tCard = (TurnCard) card;
			switch (sel) {
				case 1:
					currentBoard.placeInfluence(tCard, this);
					break;
				case 2:
					currentBoard.rollRealignment(tCard, this);
					break;
				case 3:
					currentBoard.rollCoup(tCard, this);
					break;
				case 4:
					this.announce("Effect Played: "+ tCard.getDescription());
					tCard.runEffect();
					break;
				default:
					System.out.println("Error");
					Exception e = new Exception();
					e.getStackTrace();
			}
		}
		else if (pickResult == 0) {
			TurnCard tCard = (TurnCard) card;
			this.announce("Effect Played: "+ tCard.getDescription());
			toReturn += "\nThis is an Enemy Card, and its effects have already been played";
			toReturn += "\nPlease select one off the following options for how to use this card,\n";
			toReturn += "\t1: Place Influence\n\t2: Roll for Realignment in a Country"
					+ "\n\t3: Attempt a Coup in an Enemy Country";
			toReturn += "\nSelection: ";
			System.out.print(toReturn);
			String stringSel = "";
			int sel = 0;
			ArrayList<Object> options = new ArrayList<Object>();
			options.add("Place Influence");
			options.add("Roll for Realignment");
			options.add("Attempt a coup");
			sel = 1 + Window.popupButtonWindow(toReturn, options);
			switch (sel) {
				case 1:
					currentBoard.placeInfluence(tCard, this);
					break;
				case 2:
					currentBoard.rollRealignment(tCard, this);
					break;
				case 3:
					currentBoard.rollCoup(tCard, this);
					break;
				default:
					System.out.println("Error");
					Exception e = new Exception();
					e.getStackTrace();
			}
		}
	}

	@Override
	public void announce(String s) {
		Window.popupMessage(s);
	}

	@Override
	public Country promptValidInfluenceTarget(Side side) {
		ArrayList<Object> options = new ArrayList<Object>();
		for (Country c : Map.getWorld()) {
			if (Map.checkCountryChoice(c, side)) {
				options.add(c);
			}
		}
		return Map.getCountryByName(Window.popupDropDownWindow("Please choose a valid Country to influence", options));
	}

	@Override
	public Scanner getScanner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country promptExceptionInfluenceTarget(Side side) {
		ArrayList<Object> countries = new ArrayList<Object>();
		countries.addAll(Map.getWorld());
		return Map.getCountryByName(Window.popupDropDownWindow("Please choose a country to influence", countries));
	}

}
