/**
 * 
 */
package gameFiles;

/**
 * @author Mark
 *
 */
public class TurnCard extends Card implements Comparable<TurnCard> {

	public TurnCard(String n, String d, int c, int e) {
		super(n, d, c, e);
		// TODO Auto-generated constructor stub
	}

	private int value;
	private int side;
	private boolean specialCard;
	
	
	
	/* (non-Javadoc)
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

	@Override
	public String toString() {
		String output = "";
		return null;
	}

}
