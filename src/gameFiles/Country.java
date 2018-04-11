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
	
	public Country(String n, String I, int s, int US, int USSR, boolean b, ArrayList<Continents> c) {
		name = n;
		ISOCode = I;
		stabilityNum = s;
		USInfluence = US;
		USSRInfluence = USSR;
		battleGround = b;
		continents = c;
	}
	
	public void modifyInfluence(int value, Side side) {
		
	}
	
	public String toString() {
		String toReturn = "";
		for (Continents c : continents) {
			toReturn += c.toString(c) + "_";
		}
		toReturn += ISOCode + "_";
		toReturn += "[" + USInfluence + ", " + USSRInfluence + "]_[";
		
		if ((USInfluence - USSRInfluence) > stabilityNum) toReturn += "USA]\n";
		else if ((USSRInfluence - USInfluence) > stabilityNum) toReturn += "USSR]\n";
		else toReturn += "UNK]\n";
		
		return toReturn;
	}
	
}
