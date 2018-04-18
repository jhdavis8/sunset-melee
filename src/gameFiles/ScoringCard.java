/**
 * 
 */
package gameFiles;

/**
 * Extends Card and implements Comparable.
 * @author Mark Wolgin
 * @author Josh Davis
 */
public class ScoringCard extends Card implements Comparable<ScoringCard> {

	
	private int presence;
	private int control;
	private int domination;
	
	
	/**
	 * Called in Board.setUp(), it takes information and returns the filled Scoring Card.
	 * @param n Card Name, i.e. Europe Scoring Card.
	 * @param d Card Description, Information relevant to the card.
	 * @param c Card Number, The number for the card.
	 * @param e EffectID, The unique identification code for use with in game logic and the Effects class.
	 * @param cT Card Timing, Different cards get shuffled into the deck at different points in the game, such
	 * E - Early Game, M - Mid Game, L - Late Game.
	 */
	public ScoringCard(String n, String d, int c, int e, String cT, int p, int dom, int con) {
		super(n, d, c, e, cT);
		presence = p;
		control = con;
		domination = dom;
		
		// TODO Auto-generated constructor stub
	}

	
	/* 
	 * Calls Effects.getScoringEffect(effectID) to get the correct game logic.
	 * (non-Javadoc)
	 * @see gameFiles.Card#runEffect()
	 */
	@Override
	public void runEffect() {
		Effects.getScoringEffect(effectID);
	}
	
	/* 
	 * Used to print out important information to the UIText Class.
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String toReturn = "";
		
		toReturn += String.format("%03d", cardNum) + "_";
		toReturn += "SRC_" + "xxx_xxx_";
		toReturn += String.format("%03d", effectID) + "_" + name +"\n";
		
		return toReturn;
	}


	/* 
	 * To be used to compare card numbers to allow for sorting.
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ScoringCard arg0) {
		if (this.cardNum < arg0.cardNum) return 1;
		else if (this.cardNum == arg0.cardNum) return 0;
		else return -1;
	}

}
