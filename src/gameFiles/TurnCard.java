/**
 * 
 */
package gameFiles;

/**
 * Turn Card extends Card and implements Comparable
 * @author Mark Wolgin
 * @author Josh Davis
 */
public class TurnCard extends Card implements Comparable<TurnCard> {

	
	/**
	 * Called in Board.setUp(), it takes information and returns the filled Scoring Card.
	 * @param n Card Name, i.e. Europe Scoring Card.
	 * @param d Card Description, Information relevant to the card.
	 * @param c Card Number, The number for the card.
	 * @param e EffectID, The unique identification code for use with in game logic and the Effects class.
	 * @param cT Card Timing, Different cards get shuffled into the deck at different points in the game, such
	 * E - Early Game, M - Mid Game, L - Late Game.
	 * @param s Side, the affiliation of the card to the Super Power.
	 * @param sc Special Card, some cards are special, and should not be put back into the game after there use, and such flagged cards
	 * will have special logic to manage them.
	 * @param v Card Value, How much power a card has to affect events and actions.
	 */
	public TurnCard(String n, String d, int c, int e, Side s, boolean sc, int v, String cT) {
		super(n, d, c, e, cT);
		side = s;
		specialCard = sc;
		value = v;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Value is the value of the card.
	 */
	private int value;
	
	/**
	 * Side is the affiliation of the card
	 */
	private Side side;
	
	/**
	 * Special Card tells the logic processor is the card needs to be treated differently
	 */
	private boolean specialCard;
	
	
	
	/* Calls Effects.getEffect(effectID) to get the correct game logic.
	 * (non-Javadoc)
	 * @see gameFiles.Card#runEffect()
	 */
	@Override
	public void runEffect() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * Compares the card num to another card num
	 * return 1 if this is > other
	 * return 0 if this == other
	 * return -1 else
	 */
	@Override
	public int compareTo(TurnCard arg0) {
		if (this.cardNum < arg0.cardNum) return 1;
		else if (this.cardNum == arg0.cardNum) return 0;
		else return -1;
	}
	
	/* To be used to compare card numbers to allow for sorting.
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String toReturn = "";
		
		toReturn += String.format("%03d", cardNum) + "_";
		toReturn += "TCD_" + side + "_";
		toReturn += String.format("%03d", value) + "_";
		toReturn += String.format("%03d", effectID) + "_" + name +"\n";
		
		return toReturn;
	}

}
