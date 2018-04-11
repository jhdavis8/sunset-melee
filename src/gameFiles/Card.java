package gameFiles;

/**
 * @author Mark Wolgin
 * @author Josh Davis
 *
 */
public abstract class Card {
	
	public String name;
	public String descrition;
	public int cardNum;
	public int effectID;
	
	public abstract void runEffect();
	
	public Card(String n, String d, int c, int e) {
		name = n;
		descrition = d;
		cardNum = c;
		effectID = e;
	}
	
}
