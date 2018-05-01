package gameFiles;

import java.util.ArrayList;
import java.util.Random;

/**
 * The player class hold information on the two players.  It holds information on which side the player is, and the cards in there hand.
 * There will only ever be two players.
 * @author Mark Wolgin
 * @author Josh Davis
 */
public class Player {

	/**
	 * ArrayList of Card holding the cards available to the player on their turn.
	 * Has both scoring cards and turn cards.
	 */
	private ArrayList<Card> hand;
	
	/**
	 * This is the ENUM Side identifier
	 */
	private Side side;
	
	/**
	 * The player's current number of military operations
	 */
	private int milOps;
	
	/**
	 * Takes a Side and creates a player.
	 * @param s The Side Enum
	 */
	public Player(Side s) {
		hand = new ArrayList<Card>();
		side = s;
		milOps = 0;
	}
	
	/**
	 * Play a card from the hand
	 * @param card the Card to play
	 * @return 0 for enemy's card, 1 for own or UNK card, -1 for Scoring card
	 */
	public int playCard(Card card) {
		TurnCard tCard = null;
		if (card instanceof TurnCard) {
			tCard = (TurnCard) card;
			if (tCard.getSide().equals(side) || tCard.getSide().equals(Side.UNK)) {
				return 1;
			} 
			else {
				tCard.runEffect();
				return 0;
			}
		}
		else if (card instanceof ScoringCard) {
			card.runEffect();
		}
		return -1;
	}

	/**
	 * Deals cards to the player as determined by the turn
	 * @param turn the current turn
	 */
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
	public Card drawCard(int index) {
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
	 * Add value influence to country on side
	 * @param tCard turn card object being used to place influence
	 * @param ui UICore object to use for input
	 */
	public void placeInfluence(TurnCard tCard, UICore ui) {
		int influenceLeft = tCard.getOps();
		Country country = null;
		while (influenceLeft > 0) {
			ui.announce("You have " + influenceLeft + " influence left.");
			country = ui.promptValidInfluenceTarget(side);
			if (country.opponentHasControl(side)) {
				if (influenceLeft > 1) {
					country.modifyInfluence(1, side);
					influenceLeft -= 2;
					ui.announce("1 influence placed in " + country);
				}
			}
			else {
				country.modifyInfluence(1, side);
				influenceLeft--;
				ui.announce("1 influence placed in " + country);
			}
		}
		ui.announce("All influence expended.");
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

	/**
	 * Rolls a coup by the args given
	 * @param country Country to roll on
	 * @param value int value to apply
	 * @param currentBoard the Board object work with
	 */
	public boolean rollCoup(TurnCard tCard, UICore ui) {
		Country coupTarget = Country.getValidCountry(ui.getScanner(), "Select a country to coup:", side);
		int stabilityLimit = coupTarget.getStabilityNum() * 2;
		Random randy = new Random();
		int roll = 1 + randy.nextInt(6);
		int attempt = roll + tCard.getOps();
		int diff = attempt - stabilityLimit;
		Side enemy = side.opponent(side);
		if (attempt > stabilityLimit) {
			if (diff <= coupTarget.getInfluence(enemy)) {
				coupTarget.modifyInfluence(-1 * diff, enemy);
				ui.announce("Influence in " + coupTarget.getFullName() + " changed by " + (-1 * diff) + " for " + enemy);
			}
			else {
				int changeBy = -1 * (coupTarget.getInfluence(enemy));
				coupTarget.modifyInfluence(changeBy, enemy);
				ui.announce("Influence in " + coupTarget.getFullName() + " changed by " + changeBy + " for " + enemy);
				coupTarget.modifyInfluence(diff, side);
				ui.announce("Influence in " + coupTarget.getFullName() + " changed by " + diff + " for " + side);
			}
		} // Defcon decrementing handled in Board method
		setMilOps(getMilOps() + tCard.getOps());
		ui.announce("Country is " + coupTarget);
		return coupTarget.isBattleground(); 
	}
	
	/**
	 * @return true if the player has at least one card
	 */
	public boolean hasCards() {
		if (hand.size() <= 0) return false;
		else return true;
	}
	
	/**
	 * @return the ArrayList of cards that represents the player's hand
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	/**
	 * Adds the specified card to the player's hand
	 * @param c the card to add to the hand
	 */
	public void giveCard(Card c) {
		hand.add(c);
	}

	/**
	 * @return the milOps
	 */
	public int getMilOps() {
		return milOps;
	}

	/**
	 * @param milOps the milOps to set
	 */
	public void setMilOps(int milOps) {
		this.milOps = milOps;
	}
	
	/**
	 * Picks a random card out of the users hand.
	 * @return A random card
	 */
	public Card getRandomCard() {
		Random randy = new Random();
		return hand.get(randy.nextInt(hand.size()));
	}

}
