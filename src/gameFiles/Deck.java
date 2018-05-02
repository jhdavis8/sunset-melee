/**
 * 
 */
package gameFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

/**
 * Class holding the deck of cards. All fields static.
 * @author Josh Davis
 * @author Mark Wolgin
 */
public class Deck {
	
	/**
	 * ArrayList of all cards in the game deck
	 */
	private static ArrayList<Card> ALL_CARDS;
	/**
	 * Cards currently available in the deck
	 */
	private static ArrayList<Card> deck;
	
	/**
	 * ArrayList of all discarded cards
	 */
	private static ArrayList<Card> discard;
	
	/**
	 * ArrayList of all cards that are not to be readded to the game.
	 */
	private static ArrayList<Card> dead;
	
	/**
	 * The earlyCard ArrayList
	 */
	private static ArrayList<Card> earlyCard;
	/**
	 * The midCard ArrayList
	 */
	private static ArrayList<Card> midCard;
	/**
	 * The lateCard ArrayList
	 */
	private static ArrayList<Card> lateCard;
	/**
	 * Array List of cards that are outside of the norm.
	 */
	private static ArrayList<TurnCard> outsideOfDeck;
	
	/**
	 * The boolean if the mid Cards have been added
	 */
	private static boolean addedMidCards = false;
	/**
	 * The boolean if the late Cards have been added
	 */
	private static boolean addedLateCards = false;

	
	/**
	 * Return the card specified by the Card number
	 * @param i the Card number (cardNum)
	 * @return the Card specified
	 */
	public static Card getCard(int i) {
		for (Card c : deck) {
			if (i == c.cardNum) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Return the card specified by the Card number
	 * @param i the Card number (cardNum)
	 * @return the Card specified
	 */
	public static Card getAllCard(int i) {
		for (Card c : ALL_CARDS) {
			if (i == c.cardNum) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Builds complete deck of card objects as read from the .csv
	 * @param cardCSV the Card File
	 */
	public static void fillDeck(File cardCSV) {
		deck = new ArrayList<Card>();
		ALL_CARDS = new ArrayList<Card>();
		discard = new ArrayList<Card>();
		dead = new ArrayList<Card>();
		Scanner scan = null;
		
		try {
			scan = new Scanner(cardCSV);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String[] tempCard = new String[9];
		scan.useDelimiter("[,|\n]");
		boolean sc = false;
				
		while(scan.hasNext()) {
			for (int k = 0; k < tempCard.length; k ++) {
				tempCard[k] = scan.next();
			}
			
			if (tempCard[3].equals("Y")) sc = true;
			
			if (tempCard[1].endsWith("*")) tempCard[1] = tempCard[1].substring(0, tempCard[1].length() - 1);
			
			int[] pDC = new int[3];
			String[] numbers = tempCard[8].split("[.|\n|\r]");
			for (int k = 0; k < numbers.length; k ++) {
				pDC[k] = Integer.parseInt(numbers[k]);
			}
			
			if (tempCard[4].equals("Turn")) {
				if (Integer.parseInt(tempCard[0]) == 006) {
					ALL_CARDS.add(new ChinaCard(tempCard[1], tempCard[7], Integer.parseInt(tempCard[0]), 
						 Integer.parseInt(tempCard[0]), Side.valueOf(tempCard[5]), sc, Integer.parseInt(tempCard[2]), tempCard[6], Side.USSR));
				}
				else {
					ALL_CARDS.add(new TurnCard(tempCard[1], tempCard[7], Integer.parseInt(tempCard[0]), 
							 Integer.parseInt(tempCard[0]), Side.valueOf(tempCard[5]), sc, Integer.parseInt(tempCard[2]), tempCard[6]));
				}
			}
			else {
				ALL_CARDS.add(new ScoringCard(tempCard[1], tempCard[7], Integer.parseInt(tempCard[0]),
						 Integer.parseInt(tempCard[0]), tempCard[6], pDC[0], pDC[1], pDC[2]));
			}
		}		
		fillCompositeDecks();
		addEarlyCards();
	}

	/**
	 * Fills all the smaller sub decks
	 */
	private static void fillCompositeDecks() {
		earlyCard = new ArrayList<Card>();
		midCard = new ArrayList<Card>();
		lateCard = new ArrayList<Card>();
		outsideOfDeck = new ArrayList<TurnCard>();
		String cardTiming = "";
		for (Card c : ALL_CARDS) {
			cardTiming = c.getGameTime();
			if (cardTiming.equals("E")) {
				earlyCard.add(c);
			}
			else if (cardTiming.equals("M")) {
				midCard.add(c);
			}
			else if (cardTiming.equals("L")) {
				lateCard.add(c);
			}
			else if (cardTiming.equals("O")) {
				outsideOfDeck.add((TurnCard)c);
			}
			
		}
	}
	
	/**
	 * Deal appropriate number of cards to the player
	 * @param numToDeal number of cards to deal
	 * @return the ArrayList of dealt cards
	 */
	public static ArrayList<Card> dealCards(int numToDeal) {
		ArrayList<Card> toReturn = new ArrayList<Card>();
		if (numToDeal == -1) {
			toReturn.add(Deck.getCard(6));
			deck.remove(Deck.getCard(6));
		}
		Random randy = new Random();
		int temp = 0;
		for (int k = 0; k < numToDeal; k ++) {
			temp = randy.nextInt(deck.size());
			toReturn.add(deck.get(temp));
			deck.remove(temp);
		}
		return toReturn;
	}
	
	/**
	 * Adds cards at the appropriate time
	 * @param t Turn
	 */
	public static void addCards(int t) {
		if (t > 6 && (!addedLateCards)) {
			addLateCards();
			addedLateCards = true;
		}
		else if (t > 3 && (!addedMidCards)) {
			addMidCards();
			addedMidCards = true;
		}
	}
	
	/**
	 * Adds the Early Cards
	 */
	private static void addEarlyCards() {
		deck.addAll(earlyCard);
	}
	
	/**
	 * Adds the Middle Cards
	 */
	private static void addMidCards() {
		deck.addAll(midCard);
	}
	
	/**
	 * Adds the Late Cards
	 */
	private static void addLateCards() {
		deck.addAll(lateCard);
	}
	
	/**
	 * @return the ArrayList deck of cards
	 */
	public static ArrayList<Card> getDeck() {
		return deck;
	}

	/**
	 * @return the ArrayList earlyCard
	 */
	public static ArrayList<Card> getEarlyCards() {
		return earlyCard;
	}

	/**
	 * @return the ArrayList midCard
	 */
	public static ArrayList<Card> getMidCard() {
		return midCard;
	}
	
	/**
	 * @return the ArrayList lateCard
	 */
	public static ArrayList<Card> getLateCard() {
		return lateCard;
	}
	
	/**
	 * @return The ArrayLit outsideOfDeck
	 */
	public static ArrayList<TurnCard> getOutsideOfDeckCard() {
		return outsideOfDeck;
	}
	
	/**
	 * @return the discard
	 */
	public static ArrayList<Card> getDiscard() {
		return discard;
	}
	
	/**
	 * @return the dead card list
	 */
	public static ArrayList<Card> getDead() {
		return dead;
	}

	public static void checkDeckEmpty() {
		if (deck.size() == 0) {
			// TODO reshuffling code
		}
		
	}
}
