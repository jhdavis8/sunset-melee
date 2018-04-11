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
	
	public abstract void runEffect();
}
