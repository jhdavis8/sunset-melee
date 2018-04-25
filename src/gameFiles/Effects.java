/**
 * 
 */
package gameFiles;

/**
 * Container for all effects for all cards
 * @author Mark Wolgin
 * @author Josh Davis
 *
 */
public class Effects {
	
	/**
	 * currentBoard to modify by effects
	 */
	private static Board currentBoard;
	
	/**
	 * Construct the effects class
	 * @param Board object to link to
	 */
	public Effects(Board b) {
		currentBoard = b;
	}
	
	public void setCurrentBoard(Board b) {
		currentBoard = b;
	}
	/**
	 * Enact (as a side effect) an effect by its ID (Note: we will change this to a bunch of private methods
	 * @param ID int ID of the effect
	 */
	public static void getEffect(int ID) {
		switch (ID) {
			case 4:
				effectID004();
				break;
			case 12:
				effectID012();
				break;
			case 17:
				effectID017();
				break;
		}
	}

	/**
	 * Enacts the scoring card effect by its ID
	 * @param ID int ID of the effect
	 */
	public static void getScoringEffect(int ID) {
		
	}
	
	//All Effect IDs FOllow

	private static void effectID001() {

	}
	
	private static void effectID002() {

	}
	
	private static void effectID003() {

	}
	
	private static void effectID004() {
		if (currentBoard.getDefcon() > 1) currentBoard.defcon--;
		currentBoard.victoryPoints -= 5 - currentBoard.defcon;
	}
	
	private static void effectID012() {
		Country t = Map.getCountry("ROU");
		int US = t.getUSInfluence();
		if (US > 0) {
			US = US * -1;
		}
		int USSR = t.getUSSRInfluence();
		if (USSR < t.getStabilityNum()) {
			USSR = t.getStabilityNum() - USSR;
		}
		
		t.modifyInfluence(US, Side.USA);
		t.modifyInfluence(USSR, Side.USSR);
	}
	
	private static void effectID017() {
		Map.getCountry("FRA").modifyInfluence(-2, Side.USA);
		Map.getCountry("FRA").modifyInfluence(1, Side.USSR);
		//Cancel NATO
	}
	
}
