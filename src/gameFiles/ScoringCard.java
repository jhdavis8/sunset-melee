/**
 * 
 */
package gameFiles;

/**
 * @author Mark
 *
 */
public class ScoringCard extends Card implements Comparable<ScoringCard> {

	
	
	public ScoringCard(String n, String d, int c, int e) {
		super(n, d, c, e);
		// TODO Auto-generated constructor stub
	}

	
	/* (non-Javadoc)
	 * @see gameFiles.Card#runEffect()
	 */
	@Override
	public void runEffect() {
		Effects.getScoringEffect(effectID);
	}
	
	@Override
	public String toString() {
		String toReturn = "";
		
		toReturn += String.format("%03d", cardNum) + "_";
		toReturn += "SRC_" + "xxx_xxx_";
		toReturn += String.format("%03d", effectID) + "_" + name +"\n";
		
		return toReturn;
	}


	@Override
	public int compareTo(ScoringCard arg0) {
		if (this.cardNum < arg0.cardNum) return 1;
		else if (this.cardNum == arg0.cardNum) return 0;
		else return -1;
	}

}
