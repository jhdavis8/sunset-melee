/**
 * 
 */
package gameFiles;

/**
 * @author Mark Wolgin
 * @author Josh Davis
 * 
 * The ChianCard is so important to the game, that it needed its own values to keep track of who has it.
 */
public class ChinaCard extends TurnCard {
	
	private Side whoHasCard = null;

	public ChinaCard(String n, String d, int c, int e, Side s, boolean sc, int v, String cT, Side whoHas) {
		super(n, d, c, e, s, sc, v, cT);
		whoHasCard = whoHas;
	}
	
	public Side getCurrentHolder() {
		return whoHasCard;
	}
	
	public void setCurrentHolder(Side side) {
		whoHasCard = side;
	}

}
