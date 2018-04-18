package gameFiles;

import java.util.ArrayList;
import java.util.Random;

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
	
	/**
	 * Take a card from the deck. If index is less than 0, takes a random card. Currently uses hand instead of deck.
	 * @param index
	 * @return the card taken out of the deck
	 */
	private Card drawCard(int index) {
		Random randi = new Random();
		if (index < 0) {
			int rando = randi.nextInt(hand.size());
			return hand.get(rando);
		}
		else {
			return hand.get(index);
		}
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
	
	/**
	 * Add value influence to country on side
	 * @param country country to add to
	 * @param value int amount to add
	 */
	public void placeInfluence(Country country, int value) {
		country.modifyInfluence(value, side);		
	}

	/**
	 * Rolls a coup by the args given
	 * @param country Country to roll on
	 * @param value int value to apply
	 */
	public void rollCoup(String country, int value) {
		// To fill tonight
	}
	
	/**
	 * rolls a realignment by the args given
	 * @param country Country to roll on
	 * @param value int value to use
	 */
	public void rollRealignment(Country country, int value) {
		Random randy = new Random();
		int usRoll = randy.nextInt(7);   // ADD ONE for each adjacent country and if has more influence then opponent
		System.out.println("USA Rolls " + usRoll);
		int ussrRoll = randy.nextInt(7); // ADD ONE for each adjacent country and if has more influence then opponent
		System.out.println("USSR Rolls " + ussrRoll);
		int diff = 0;
		
		if (side.equals(Side.USA) && (usRoll > ussrRoll)) {
			diff = -1 * (usRoll - ussrRoll);
			country.modifyInfluence(diff, Side.USSR);
		}
		else if (side.equals(Side.USA) && (usRoll <= ussrRoll)) {
			diff = 0;
		}
		else if (side.equals(Side.USSR) && (usRoll > ussrRoll)) {
			diff = -1 * (ussrRoll - usRoll);
			country.modifyInfluence(diff, Side.USA);
		}
		else {
			diff = 0;
		}
	}

}
