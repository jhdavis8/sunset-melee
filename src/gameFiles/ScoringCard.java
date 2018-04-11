/**
 * 
 */
package gameFiles;

/**
 * @author Mark
 *
 */
public class ScoringCard extends Card {

	
	
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
	

}
