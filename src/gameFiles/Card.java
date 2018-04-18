package gameFiles;

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
	public String descrition;
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
		descrition = d;
		cardNum = c;
		effectID = e;
		cardTiming = cT;
	}

	/**
	 * @return Returns the Card timing
	 */
	public String getGameTime() {
		return cardTiming;
	}
	
}
