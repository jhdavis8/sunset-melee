package gameFiles;

import java.util.ArrayList;

/**
 * @author Mark Wolgin
 * @author Josh Davis
 * 
 * The player class hold information on the two players.  It holds information on which side the player is, and the cards in there hand.
 * There will only ever be two players.
 * 
 */
public class Player {

	/**
	 * Is an ArrayList of Card, that is, it will hold the cards available to the player on there turn.
	 * It will have both scoring cards and turn cards.
	 */
	private ArrayList<Card> hand;
	
	/**
	 * This is the ENUM Side identifier
	 */
	private Side side;
	
	/**
	 * Takes a Side and creates a player.
	 * @param s The Side Enum
	 */
	public Player(Side s) {
		hand = new ArrayList<Card>();
		side = s;
	}
	
	/**
	 * It will prompt the user to pick a card from there hand and play it.
	 */
	public void playCard() {
		
	}
	
	/* 
	 * Used to print out important information to the UIText Class.
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String toReturn = "";
		toReturn += "Player: " + side + ", has the following cards:";
		
		for (Card c : hand) {
			toReturn += c + "\n";
		}
		
		return toReturn;
	}

}
