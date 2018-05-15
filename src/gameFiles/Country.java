/**
 * 
 */
package gameFiles;

import java.util.ArrayList;
import java.util.Scanner;

import graphics.Window;

/**
 * Class for Country objects on the map
 * @author Mark Wolgin
 * @author Josh Davis
 *
 */
public class Country {
	
	/**
	 * Name of the country
	 */
	private String name;
	/**
	 * ISO of the country
	 */
	private String ISOCode;
	/**
	 * Stability num of the country
	 */
	private int stabilityNum;
	/**
	 * US Influence amount in the country
	 */
	private int USInfluence;
	/**
	 * USSR Influence amount in the country
	 */
	private int USSRInfluence;
	/**
	 * True if the country is a battleground
	 */
	private boolean battleground;
	/**
	 * ArrayList of all continents the country is a member of
	 */
	private ArrayList<Continents> continents;
	/**
	 * String-form Array of connected countries
	 */
	private String[] cCStrings; 
	/**
	 * ArrayList of connected Country objects
	 */
	private ArrayList<Country> connectedCountries;
	
	/**
	 * Constructor for a Country object
	 * @param n name of country
	 * @param I ISO code of country
	 * @param s stability number of country
	 * @param US current US influence
	 * @param USSR current USSR influence
	 * @param b true for battleground countries
	 * @param c ArrayList of continents the country is a member of
	 * @param i List of connected countries in String
	 */
	public Country(String n, String I, int s, int US, int USSR, boolean b, Continents[] c, String[] i) {
		name = n;
		ISOCode = I;
		stabilityNum = s;
		USInfluence = US;
		USSRInfluence = USSR;
		battleground = b;
		continents = new ArrayList<Continents>();
		for (Continents cC : c) {
			continents.add(cC);
		}
		cCStrings = i;
	}
 
	/**
	 * Adjusts the influence in the country
	 * @param value amount to adjust by
	 * @param side which side to add for
	 */
	public void modifyInfluence(int value, Side side) {
		if (side == Side.USA) USInfluence += value;
		else USSRInfluence += value;
		if (USInfluence < 0) USInfluence = 0;
		if (USSRInfluence < 0) USSRInfluence = 0;
	}
	
	/**
	 * @return the string ISO code
	 */
	public String getISO() {
		return ISOCode;
	}
	
	/**
	 * @return ArrayList of continent memberships
	 */
	public ArrayList<Continents> getContinent() {
		return continents;
	}

	/**
	 * Returns true if the opposite of the side passed has influence on this country
	 * @param side USA or USSR
	 * @return	returns true if the the opposite of the side has influence in this country
	 */
	public boolean opponentHasInfluence(Side side) {
		if (side.equals(Side.USA) && USSRInfluence > 0) return true;
		else if (side.equals(Side.USSR) && USInfluence > 0) return true;
		
		else return false;
	}

	/**
	 * @param side to get opponent of
	 * @return true if the opponent of the Side has control of this country
	 */
	public boolean opponentHasControl(Side side) {
		if (side.equals(Side.USA) && (USSRInfluence - USInfluence) >= stabilityNum) return true;
		else if (side.equals(Side.USSR) && (USInfluence - USSRInfluence) >= stabilityNum) return true;
		else return false;
	}

	/**
	 * @param side USA or USSR
	 * @return true if the opponent of the Side has influence in this country
	 */
	public boolean userHasInfluence(Side side) {
		if (side.equals(Side.USA) && USInfluence > 0) return true;
		else if (side.equals(Side.USSR) && USSRInfluence > 0) return true;
		else return false;
	}
	
	/**
	 * Check if the side has control in the Country
	 * @param side side to check for
	 * @return true if the Country called is controlled by the side passed in
	 */
	public boolean userHasControl(Side side) {
		if (side.equals(Side.USA) && (USInfluence - USSRInfluence) >= stabilityNum) return true;
		else if (side.equals(Side.USSR) && (USSRInfluence - USInfluence) >= stabilityNum) return true;
		else return false;
	}

	/**
	 * Returns The list of the connected countries
	 * @param b ArrayList<Country>
	 * @return The list of the connected countries
	 */
	private ArrayList<Country> connectedCountries(ArrayList<Country> b) {
		ArrayList<Country> toReturn = new ArrayList<Country>();
		for (String s : cCStrings) {
			for (Country c : b) {
				if (c.ISOCode.equals(s)) {
					toReturn.add(c);
				}
			}
		}
		
		return toReturn;
	}
	
	/**
	 * Returns The list of the connected countries
	 * @return The ArrayList of the connected countries
	 */
	public ArrayList<Country> getConnectedCountries() {
		return connectedCountries;
	}
	
	/**
	 * @return String formatted for the country
	 */
	public String toString() {
		String toReturn = "";
		for (Continents c : continents) {
			toReturn += c.toString(c) + "_";
		}
		if (continents.size() == 1) toReturn += "..._";
		
		toReturn += ISOCode + "_[";
		toReturn += stabilityNum + "]_";
		toReturn += "[" + USInfluence + ", " + USSRInfluence + "]_[";
		
		if ((USInfluence - USSRInfluence) >= stabilityNum) toReturn += "USA]_";
		else if ((USSRInfluence - USInfluence) >= stabilityNum) toReturn += "USSR]_";
		else toReturn += "UNK]_";
		
		toReturn += name + "\n";
		
		return toReturn;
	}

	/**
	 * Add a country to the connected country array (for initialization only)
	 * @param c Country object to add
	 */
	public void connectCountries(ArrayList<Country> c) {
		connectedCountries = connectedCountries(c);
	}
	
	/**
	 * @return the USA Influence in the Country
	 */
	public int getUSInfluence() {
		return USInfluence;
	}

	/**
	 * @return the USSR influence in the Country
	 */
	public int getUSSRInfluence() {
		return USSRInfluence;
	}
	
	/**
	 * Get influence for a particular side
	 * @param side Side to get influence for
	 * @return influence for specified side
	 */
	public int getInfluence(Side side) {
		if (side == Side.USA) return getUSInfluence();
		if (side == Side.USSR) return getUSSRInfluence();
		else return 0;
 	}

	/**
	 * @return the stabilityNum
	 */
	public int getStabilityNum() {
		return stabilityNum;
	}
	
	/**
	 * @return the Side currently controlling the country by influence
	 */
	public Side getControllingSide() {
		if ((USInfluence - USSRInfluence) >= stabilityNum) return Side.USA;
		else if ((USSRInfluence - USInfluence) >= stabilityNum) return Side.USSR;
		else return Side.UNK;
	}
	
	/**
	 * @return true is the country is a battleground
	 */
	public boolean isBattleground() {
		return battleground;
	}
	
	/**
	 * @return the full name of the country
	 */
	public String getFullName() {
		return name;
	}

	/**
	 * makes a county a battleground if a card requires it
	 */
	public void makeBattleground() {
		battleground = true;
	}
	
	/**
	 * Handles prompting for a valid country
	 * @param scan Scanner for input purposes
	 * @param message What to say when checking
	 * @return the valid Country chosen
	 */
	public static Country getValidCountry(Scanner scan, String message) {
		boolean checking = true;
		String input = "";
		Country country = null;
		while (checking) {
			System.out.println(message);
			input = scan.nextLine();
			country = Map.getCountry(input);
			if (country != null) {
				System.out.printf("Country is: %s%n", country);
				checking = false;
			}
			else {
				System.out.println("Not a country!");
				continue;
			}
		}
		return country;
	}
	
	/**
	 * Handles prompting with an additional constraint: enemy player must have influence
	 * @param ui Scanner to get input with
	 * @param message Message to say
	 * @param self The side that is trying a realignment or coup
	 * @return the valid Country chosen
	 */
	public static Country getValidCountry(UICore ui, String message, Side self) {
		//TODO Could move to UI, one for each option
		boolean checking = true;
		String input = "";
		Country country = null;
		if (ui instanceof UIText) {
			while (checking) {
				
				if (self.equals(Side.USA)) {
					input = ui.promptUSA(message);
				}
				else { 
					input = ui.promptUSSR(message);
				}
				
				country = Map.getCountry(input);
				if (country != null && country.opponentHasInfluence(self)) {
					System.out.printf("Country is: %s%n", country);
					checking = false;
				}
				else {
					System.out.println("Not a valid country (Opponent must have influence and must be an actual country)!");
					continue;
				}
			}
		}
		else if (ui instanceof UIGraphic) {
			ArrayList<Object> al = new ArrayList<Object>();
			for (Country c : Map.getWorld()) {
				if (c.opponentHasInfluence(self)) {
					al.add(c);
				}
			}
			country = Map.getCountryByName(Window.popupDropDownWindow(message, al, self));
		}
		return country;
	}
	
	/**
	 * @param input	String of a potential Country Code
	 * @return	True if it is a country, false otherwise
	 */
	public static boolean isValidCountry(String input) {
		boolean checking = true;
		Country country = null;
		while (checking) {
			country = Map.getCountry(input);
			if (country == null) {
				checking = false;
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param defcon The defcon level
	 * @return If the country is available to realignment or coup it returns true, else false.
	 */
	public boolean isValidDefconCountry(int defcon) {
		switch (defcon) {
			case 5:
				return true;
			case 4:
				for (Continents c : continents) {
					if ((c.equals(Continents.WEE)) || (c.equals(Continents.EEE))) {
						return false;
					}
					else return true;
				}
			case 3:
				for (Continents c : continents) {
					if ((c.equals(Continents.WEE)) || (c.equals(Continents.EEE))
							|| (c.equals(Continents.ABB)) || (c.equals(Continents.UUU))) {
						return false;
					}
					else return true;
				}
			case 2:
				for (Continents c : continents) {
					if ((c.equals(Continents.WEE)) || (c.equals(Continents.EEE))
							|| (c.equals(Continents.ABB)) || (c.equals(Continents.UUU))
							|| (c.equals(Continents.MMM))) {
						return false;
					}
					else return true;
				}
			default:
				return false;
		}
	}
	
	/**
	 * @param cont Continents
	 * @return If the continent provided is one that country is a part of it returns true.
	 */
	public boolean isCountryInContinent(Continents cont) {
		for (Continents c : continents) {
			if (c.equals(cont)) return true;
		}
		return false;
	}
}
