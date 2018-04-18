/**
 * 
 */
package gameFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
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
	private static ArrayList<Card> deck;
	
	/**
	 * ArrayList of all discarded cards
	 */
	private static ArrayList<Card> discard;
	
	private static ArrayList<Card> earlyCard;
	private static ArrayList<Card> midCard;
	private static ArrayList<Card> lateCard;
	
	private static boolean addedMidCards = false;
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
	 * Builds complete deck of card objects as read from the .csv
	 */
	public static void fillDeck(File cardCSV) {
		deck = new ArrayList<Card>();
		ALL_CARDS = new ArrayList<Card>();
		discard = new ArrayList<Card>();
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
				ALL_CARDS.add(new TurnCard(tempCard[1], tempCard[7], Integer.parseInt(tempCard[0]), 
						 Integer.parseInt(tempCard[0]), Side.valueOf(tempCard[5]), sc, Integer.parseInt(tempCard[2]), tempCard[6]));
			}
			else {
				ALL_CARDS.add(new ScoringCard(tempCard[1], tempCard[7], Integer.parseInt(tempCard[0]),
						 Integer.parseInt(tempCard[0]), tempCard[6], pDC[0], pDC[1], pDC[2]));
			}
		}		
		fillCompositDecks();
		addEarlyCards();
	}

	private static void fillCompositDecks() {
		earlyCard = new ArrayList<Card>();
		midCard = new ArrayList<Card>();
		lateCard = new ArrayList<Card>();
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
			
		}
	}
	
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
	
	private static void addEarlyCards() {
		deck.addAll(earlyCard);
	}
	
	private static void addMidCards() {
		deck.addAll(midCard);
	}
	
	private static void addLateCards() {
		deck.addAll(lateCard);
	}
	
	/**
	 * @return the ArrayList deck of cards
	 */
	public static ArrayList<Card> getDeck() {
		return deck;
	}

	public static ArrayList<Card> getEarlyCards() {
		return earlyCard;
	}

	public static ArrayList<Card> getMidCard() {
		return midCard;
	}
	
	public static ArrayList<Card> getLateCard() {
		return lateCard;
	}
	
	
}
