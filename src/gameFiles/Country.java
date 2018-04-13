/**
 * 
 */
package gameFiles;

import java.util.ArrayList;

/**
 * @author Mark
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


	public void modifyInfluence(int value, Side side) {
		if (side == Side.USA) USInfluence += value;
		else USSRInfluence += value;
	}
	
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
	
	public String getISO() {
		return ISOCode;
	}
	
	public ArrayList<Continents> getContinent() {
		return continents;
	}
	
}
