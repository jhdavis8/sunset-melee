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
	private int lastAr;
	private int lastTn;
	private Side lastPr;
	private ArrayList<Object> lastListOfOptions;
	
	
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
	 * @param game 
	 */
	public UIGraphic(Board game) {
		try {
			window = new Window();
			frame = new JFrame();
			lastAr = 0;
			lastTn = 0;
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
		return Window.popupButtonWindow("Please choose a card to play", hand, side);
	}

	@Override
	public String promptUSA(String message) {
		return Window.popupStringInputWindow(message, Side.USA);
	}

	@Override
	public String promptUSSR(String message) {
		return Window.popupStringInputWindow(message, Side.USSR);
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
			int sel = 0;
			ArrayList<Object> options = new ArrayList<Object>();
			options.add("Place Influence");
			options.add("Roll for Realignment");
			options.add("Attempt a coup");
			options.add("Play card event");
			sel = 1 + Window.popupButtonWindow(toReturn, options, currentBoard.getCurrentPlayer());
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
			int sel = 0;
			ArrayList<Object> options = new ArrayList<Object>();
			options.add("Place Influence");
			options.add("Roll for Realignment");
			options.add("Attempt a coup");
			sel = 1 + Window.popupButtonWindow(toReturn, options, currentBoard.getCurrentPlayer());
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
		Window.popupMessage(s, currentBoard.getCurrentPlayer());
	}

	@Override
	public Country promptValidInfluenceTarget(Side side) {
		ArrayList<Object> options = new ArrayList<Object>();
		if ((currentBoard.getActionRound() == this.lastAr) && (currentBoard.getTurn() == this.lastTn) && lastPr.equals(currentBoard.getCurrentPlayer())) {
			options = lastListOfOptions;
		}
		else {
			lastAr = currentBoard.getActionRound();
			lastTn = currentBoard.getTurn();
			lastPr = currentBoard.getCurrentPlayer();
			for (Country c : Map.getWorld()) {
				if (Map.checkCountryChoice(c, side)) {
					options.add(c);
				}
			}
			lastListOfOptions = options;
		}
		return Map.getCountryByName(Window.popupDropDownWindow("Please choose a valid Country to influence", options,  side));
	}

	@Override
	public Scanner getScanner() {
		return null;
	}

	@Override
	public Country promptExceptionInfluenceTarget(Side side) {
		ArrayList<Object> countries = new ArrayList<Object>();
		countries.addAll(Map.getWorld());
		Continents target;
		if (currentBoard.getCurrentPlayer() == Side.USA) {
			target = Continents.WEE;
		} else {
			target = Continents.EEE;
		}
		return Map.getCountryByName(Window.popupDropDownWindow("Please choose a country to influence", countries, side, target));
	}
	
	@Override
	public Country promptExceptionInfluenceTarget(Side side, ArrayList<Object> al, Continents c) {
		return Map.getCountryByName(Window.popupDropDownWindow("Please choose a country to influence", al, side, c));
	}

}
