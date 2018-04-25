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
		drawCard(-1);
	}
	
	/**
	 * Temporary Method
	 * @param t Card to draw, if -1, then it pulls randomly
	 * @return Selected card
	 */
	public Card playCard(int t) {
		return drawCard(t);
	}
	
	public void dealCards(int turn) {
		int toDraw = 0;
		if (turn > 3) {
			toDraw = 9;
		}
		else if (turn == -1) {
			hand.addAll(Deck.dealCards(-1));
		}
		else {
			toDraw = 8;
		}
		
		for (Card c : hand) {
			toDraw --;
			if (c.cardNum == 006) {
				toDraw ++;
			}
		}
		hand.addAll(Deck.dealCards(toDraw));
	}
	
	/**
	 * Take a card from the deck. If index is less than 0, takes a random card. Currently uses hand instead of deck.
	 * Card is also added to the hand.
	 * @param index Card Index
	 * @return the card taken out of the deck
	 */
	private Card drawCard(int index) {
		Random randi = new Random();
		ArrayList<Card> deck = Deck.getDeck();
		if (index < 0) {
			int rando = randi.nextInt(deck.size());
			Card result = deck.get(rando);
			hand.add(result);
			return result;
		}
		else {
			Card result = deck.get(index);
			hand.add(result);
			return result;
		}
	}
	
	/* 
	 * Used to print out important information to the UIText Class.
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String toReturn = "";
		toReturn += "Player: " + side + ", has the following cards:\n";
		
		for (Card c : hand) {
			toReturn += c;
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
	public void rollCoup(Country country, int value) {
		// To fill tonight
	}
	
	/**
	 * rolls a realignment by the args given
	 * @param country Country to roll on
	 * @param value int value to use
	 */
	public void rollRealignment(Country country, int value) {
		Random randy = new Random();
		int usRoll = 1 + randy.nextInt(6);   // ADD ONE for each adjacent country and if has more influence then opponent
		System.out.println("USA Rolls " + usRoll);
		int ussrRoll = 1 + randy.nextInt(6); // ADD ONE for each adjacent country and if has more influence then opponent
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

	public boolean hasCards() {
		if (hand.size() == 0) return false;
		else return true;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}

}
