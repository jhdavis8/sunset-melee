package gameFiles;

/**
 * Abstract class for all cards 
 * @author Mark Wolgin
 * @author Josh Davis
 *
 */
public abstract class Card {
	
	public String name;
	public String descrition;
	public int cardNum;
	public int effectID;
	public String cardTiming;
	
	public abstract void runEffect();
	
	public Card(String n, String d, int c, int e, String cT) {
		name = n;
		descrition = d;
		cardNum = c;
		effectID = e;
		cardTiming = cT;
	}

	public String getGameTime() {
		return cardTiming;
	}
	
}
