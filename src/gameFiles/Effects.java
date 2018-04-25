/**
 * 
 */
package gameFiles;

import java.util.Scanner;

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
	
	private static Scanner scan;
	
	private static UICore UI;
	
	public static void setCurrentBoard(Board b) {
		currentBoard = b;
	}
	
	public static void setScanner(Scanner other) {
		scan = other;	
	}
	
	public static void setUI(UICore Ui) {
		UI = Ui;
	}
	
	/**
	 * Enact (as a side effect) an effect by its ID (Note: we will change this to a bunch of private methods
	 * @param ID int ID of the effect
	 * @throws Exception 
	 */
	public static void getEffect(int ID) throws Exception {
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
			case 22:
				effectID022();
				break;
			case 26:
				effectID026();
				break;
			case 34:
				effectID034();
				break;
			default:
				System.out.println("CardID Not Found");
				throw new Exception();
		}
	}

	/**
	 * Enacts the scoring card effect by its ID
	 * @param ID int ID of the effect
	 */
	public static void getScoringEffect(int ID) throws Exception {
		switch (ID) {
			case 1:
				effectID001();
				break;
			case 2:
				effectID002();
				break;
			case 3:
				effectID003();
				break;
			default:
				System.out.println("ScoringCardID Not Found");
				throw new Exception();
		}
	}
		
	//All Effect IDs Follow
	
	/**
	 * Asia Scoring card effect
	 */
	private static void effectID001() {
		
	}
	
	/**
	 * Europe Scoring card effect	
	 */
	private static void effectID002() {
		boolean presenceUSA = false;
		boolean presenceUSSR = false;
		Side domination = Side.UNK;
		Side control = Side.UNK;
		int countryCountUSA = 0;
		int countryCountUSSR = 0;
		int bgCountUSA = 0;
		int bgCountUSSR = 0;
		int bgTotal = 0;
		int totalUSA = 0;
		int totalUSSR = 0;
		for (Country c : Map.getWorld()) {
			if (c.getContinent().contains(Continents.EEE) || c.getContinent().contains(Continents.WEE)) {
				if (c.getControllingSide() == Side.USA) {
					presenceUSA = true;
					countryCountUSA++;
					if (c.isBattleground()) {
						bgCountUSA++;
						bgTotal++;
					}
				}
				else if (c.getControllingSide() == Side.USSR) {
					presenceUSSR = true;
					countryCountUSSR++;
					if (c.isBattleground()) {
						bgCountUSSR++;
						bgTotal++;
					}
				}
			}
		}
		if (countryCountUSA > countryCountUSSR && bgCountUSA > bgCountUSSR && countryCountUSA >= 1 && bgCountUSA >= 1) {
			domination = Side.USA;
		}
		else if (countryCountUSSR > countryCountUSA && bgCountUSSR > bgCountUSA && countryCountUSSR >= 1 && bgCountUSSR >= 1) {
			domination = Side.USSR;
		}
		if (countryCountUSA > countryCountUSSR && bgCountUSA == bgTotal) control = Side.USA;
		if (countryCountUSSR > countryCountUSA && bgCountUSSR == bgTotal) control = Side.USSR;
		if (presenceUSA) totalUSA += 3;
		if (presenceUSSR) totalUSSR += 3;
		if (domination == Side.USA) totalUSA += 7;
		else if (domination == Side.USSR) totalUSSR += 7;
		if (control == Side.USA) totalUSA += 400;
		if (control == Side.USSR) totalUSSR += 400;
		currentBoard.modifyVictoryPoints(totalUSSR - totalUSA);
	}
	
	/**
	 * Middle East Scoring card effect
	 */
	private static void effectID003() {

	}
	
	/**
	 * Duck and Cover card effect
	 */
	private static void effectID004() {
		if (currentBoard.getDefcon() > 1) currentBoard.modifyDefcon(-1);
		currentBoard.modifyVictoryPoints(-1 * (5 - currentBoard.getDefcon()));
	}

	/**
	 * Romanian Abdication card effect
	 */
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
	

	/**
	 * De Gaulle Leads France card effect
	 */
	private static void effectID017() {
		Map.getCountry("FRA").modifyInfluence(-2, Side.USA);
		Map.getCountry("FRA").modifyInfluence(1, Side.USSR);
		//Cancel NATO
	}
	
	/**
	 * Independent Reds
	 */
	private static void effectID022() {
		// Show a country to pick
		String cISO = "";
		System.out.println("Select one of the countries to have its USA influence set to equal USSR:");
		for (Country  c : Map.getWorld()) {
			cISO = c.getISO();
			if (cISO.equals("YUG") || cISO.equals("ROU") || cISO.equals("BGR") || cISO.equals("HUN") || cISO.equals("CSK")) {
				System.out.print(c);
			}
		}
		// Pick a country
		String input = "";
		boolean checking = true;
		while (checking) {
			input = UI.promptUSA();
			if (input.equals("YUG") || input.equals("ROU") || input.equals("BGR") || input.equals("HUN") || input.equals("CSK")) {
				checking = false;
			}
		}
		
		// Adds USA influence
		int USInf = Map.getCountry(input).getUSSRInfluence();
		Map.getCountry(input).modifyInfluence(USInf, Side.USA);
		System.out.print(Map.getCountry(input));
	}
	
	
	/**
	 * CIA Created
	 */
	private static void effectID026() {
		System.out.println();
		for (Card c : currentBoard.getPlayer(Side.USSR).getHand()) {
			System.out.print(c);
		}
	}
	
	/**
	 * Nuclear Test Ban
	 */
	private static void effectID034() {
		Side side = Side.getValidSide(scan, "Which side played the card?");
		int vp = currentBoard.getDefcon() - 2;
		if (side.equals(Side.USA)) vp = vp * -1;
		currentBoard.modifyVictoryPoints(vp);
	}
	
	private static void effectID103() {
		if (currentBoard.getCurrentPlayer().equals(Side.USSR)) {
			currentBoard.modifyVictoryPoints(-1);
		}
	}
	
}
