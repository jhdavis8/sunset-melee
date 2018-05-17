package gameFiles;

import java.util.Scanner;

/**
 * Abstract class for all cards 
 * @author Mark Wolgin
 * @author Josh Davis
 *
 */
public abstract class Card {
	
	/**
	 * The name of the Card
	 */
	public String name;
	/**
	 * The description of the Card
	 */
	public String description;
	/**
	 * The number of the Card
	 */
	public int cardNum;
	/**
	 * The effectId of the Card
	 */
	public int effectID;
	/**
	 * The flag that determines when the Card should be played
	 */
	public String cardTiming;
	
	/**
	 * Runs the effect based on the effectID
	 */
	public abstract void runEffect();
	
	/**
	 * The default constructor for Card
	 * @param n	The name of the Card 
	 * @param d The description of the Card
	 * @param c The number of the Card
	 * @param e The effectId of the Card
	 * @param cT The flag that determines when the Card should be played
	 */
	public Card(String n, String d, int c, int e, String cT) {
		name = n;
		description = d;
		cardNum = c;
		effectID = e;
		cardTiming = cT;
	}
	
	public String getWrapDiscription(int split) {
		Scanner disc = new Scanner(description);
		disc.useDelimiter("[ ]");
		int count = 0;
		String toReturn = "";
		String temp = "";
		while (disc.hasNext()) {
			temp = disc.next();
			if ((temp.length() + count) > split) {
				toReturn += "\n \t \t \t \t \t" + temp;
				count = temp.length();
			}
			else {
				toReturn += temp + " ";
				count += temp.length();
			}
		}
		return toReturn;
	}

	/**
	 * @return Returns the Card timing
	 */
	public String getGameTime() {
		return cardTiming;
	}
	
}
