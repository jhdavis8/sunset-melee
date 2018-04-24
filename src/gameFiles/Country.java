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
	private String[] cCStrings; 
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
		battleGround = b;
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
	 * Returns true of the Side has influence in this country
	 * @param side USA or USSR
	 * @return Returns true of the Side has influence in this country
	 */
	public boolean userHasInfluence(Side side) {
		if (side.equals(Side.USA) && USInfluence > 0) return true;
		else if (side.equals(Side.USSR) && USSRInfluence > 0) return true;
		
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
	 * @return ArrayList<Country> The list of the connected countries
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

	public void connectCountries(ArrayList<Country> c) {
		connectedCountries = connectedCountries(c);
	}
	
	public int getUSInfluence() {
		return USInfluence;
	}

	public int getUSSRInfluence() {
		return USSRInfluence;
	}

	/**
	 * @return the stabilityNum
	 */
	public int getStabilityNum() {
		return stabilityNum;
	}
	

	
}
