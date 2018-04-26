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
	
	/**
	 * Scanner object used
	 */
	private static Scanner scan;
	
	/**
	 * UI object used
	 */
	private static UICore UI;
	
	/**
	 * Assign a Board object to the effects
	 * @param b the Board to assign
	 */
	public static void setCurrentBoard(Board b) {
		currentBoard = b;
	}
	
	/**
	 * Assign a scanner object to effects
	 * @param other scanner to assign
	 */
	public static void setScanner(Scanner other) {
		scan = other;	
	}
	
	/**
	 * Assign a UI object to effects
	 * @param Ui UI object to assign
	 */
	public static void setUI(UICore Ui) {
		UI = Ui;
	}
	
	/**
	 * Enact an effect by its ID
	 * @param ID int ID of the effect
	 * @throws Exception exception if card ID not found (Might change this to error message) 
	 */
	public static void getEffect(int ID) throws Exception {
		switch (ID) {
			case 4:
				effectID004();
				break;
			case 5:
				effectID005();
				break;
			case 6:
				effectID006();
				break;
			case 8:
				effectID008();
				break;
			case 9:
				effectID009();
				break;
			case 10:
				effectID010();
				break;
			case 12:
				effectID012();
				break;
			case 15:
				effectID015();
				break;
			case 17:
				effectID017();
				break;
			case 18:
				effectID018();
				break;
			case 19:
				effectID019();
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
			case 35:
				effectID035();
				break;
			case 103:
				effectID103();
				break;
			default:
				System.out.println("CardID Not Found");
				throw new Exception();
		}
	}

	/**
	 * Enacts the scoring card effect by its ID
	 * @param ID int ID of the effect
	 * @throws Exception exception for CardID not found, might be changed to error message
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
	 * Five Year Plan
	 */
	private static void effectID005() {
		Card c = currentBoard.getPlayer(Side.USSR).getRandomCard();
		TurnCard tC = null;
		@SuppressWarnings("unused")
		ScoringCard sC = null;
		
		if (c instanceof TurnCard) {
			tC = (TurnCard) c;
			if (tC.getSide().equals(Side.USA)) {
				tC.runEffect();
			}
		}
		else {
			sC = (ScoringCard) c;
		}
	}

	/**
	 * The China Card
	 */
	private static void effectID006() {
		// TODO NONE without Asia
	}
	
	/**
	 * Fidel
	 */
	private static void effectID008() {
		Country t = Map.getCountry("CUB");
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
	 * Vietnam Revolts
	 */
	private static void effectID009() {
		// TODO needs to add +1 ops is all spent in S/E asia
		Map.getCountry("VNM").modifyInfluence(2, Side.USSR);
	}
	
	/**
	 * Blockade
	 */
	private static void effectID010() {
		TurnCard tC = null;
		int count = 0;
		int cardOverThree = 0;
		for (Card c : currentBoard.getPlayer(Side.USA).getHand()) {
			if (c instanceof TurnCard) {
				tC = (TurnCard) c;
				if (tC.getOps() >= 3) {
					cardOverThree ++;
					System.out.print("Card " + count + ":" + tC);
				}
			}
			count ++;
		}
		if (cardOverThree > 0) {
			System.out.println("Please pick a card to discard imediently, or type -1,"
					+ " to signify that you'll take the influence loss in West Germany");
			String input = UI.promptUSA();
			int selection = Integer.parseInt(input);
			if (selection >= 0) {
				currentBoard.handleDiscard(currentBoard.getPlayer(Side.USA).getHand().get(selection), Side.USA);
			}
			else {
				int USInf = Map.getCountry("DEU").getUSInfluence();
				Map.getCountry("DEU").modifyInfluence(USInf, Side.USA);
			}
		}
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
	 * Nasser
	 */
	private static void effectID015() {
		Map.getCountry("EGY").modifyInfluence(2, Side.USSR);
		int USInf = Map.getCountry("EGY").getInfluence(Side.USA);
		USInf = (USInf / 2) * -1;
		Map.getCountry("EGY").modifyInfluence(USInf, Side.USA);
	}

	/**
	 * De Gaulle Leads France card effect
	 */
	private static void effectID017() {
		Map.getCountry("FRA").modifyInfluence(-2, Side.USA);
		Map.getCountry("FRA").modifyInfluence(1, Side.USSR);
		// TODO Cancel NATO
	}
	
	/**
	 * Captured Nazi Scientist
	 */
	private static void effectID018() {
		// TODO No actual effect, since there is no space race.
	}


	/**
	 * Truman Doctrine
	 */
	private static void effectID019() {
		boolean isContinent = false;
		for (Country c : Map.getWorld()) {
			isContinent = false;
			for (Continents con : c.getContinent()) {
				if (con.equals(Continents.EEE) || con.equals(Continents.WEE)) {
					isContinent = true;
				}
			}
			if (isContinent && (c.getInfluence(Side.USSR) > 0)) {
				System.out.print(c);
			}
		}
		System.out.println("Please select a country to remove all influence from.  Use 3-digit ISO.");
		String input = UI.promptUSA();
		while (Country.isValidCountry(input) == false) {
			System.out.println("Not a country.");
			input = UI.promptUSA();
		}
		Country country = Map.getCountry(input);
		int USSRInf = country.getInfluence(Side.USSR);
		USSRInf = -1 * USSRInf;
		country.modifyInfluence(USSRInf, Side.USSR);
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
	
	/**
	 * Forman Resolution
	 */
	private static void effectID035() {
		// TODO Effect has no use in game ... yet, also should remove status once US plays China Card
		Map.getCountry("TWN").makeBattleground();	
	}

	/**
	 * Defectors
	 */
	private static void effectID103() {
		if (currentBoard.getCurrentPlayer().equals(Side.USSR)) {
			currentBoard.modifyVictoryPoints(-1);
		}
	}
	
}
