/**
 * 
 */
package gameFiles;

import java.util.ArrayList;

/**
 * Class for Country objects on the map
 * @author Mark Wolgin
 * @author Josh Davis
 *
 */
public class Country {
	
	private String name;
	private String ISOCode;
	private int stabilityNum;
	private int USInfluence;
	private int USSRInfluence;
	private boolean battleGround;
	private ArrayList<Continents> continents;
	
	/**
	 * Constructor for a Country object
	 * @param n name of country
	 * @param I ISO code of country
	 * @param s stability number of country
	 * @param US current US influence
	 * @param USSR current USSR influence
	 * @param b true for battleground countries
	 * @param c ArrayList of continents the country is a member of
	 */
	public Country(String n, String I, int s, int US, int USSR, boolean b, Continents[] c) {
		name = n;
		ISOCode = I;
		stabilityNum = s;
		USInfluence = US;
		USSRInfluence = USSR;
		battleGround = b;
		continents = new ArrayList<Continents>();
		for (Continents i : c) {
			continents.add(i);
		}
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
	 * 
	 * @return the string ISO code
	 */
	public String getISO() {
		return ISOCode;
	}
	
	/**
	 * 
	 * @return ArrayList of continent memberships
	 */
	public ArrayList<Continents> getContinent() {
		return continents;
	}

	public boolean opponentHasInfluence(Side side) {
		if (side.equals(Side.USA) && USSRInfluence > 0) return true;
		else if (side.equals(Side.USSR) && USInfluence > 0) return true;
		
		else return false;
	}
	
}
