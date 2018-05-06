/**
 * 
 */
package gameFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Mark
 *
 */
public class Location {
	
	private int x;
	private int y;
	private String name;
	private String country;
	private Side side;
	
	public Location (int xPos, int yPos, String nameOfLoc, String count, Side s) {
		x = xPos;
		y = yPos;
		name = nameOfLoc;
		country = count;
		side = s;
	}
	
	private static ArrayList<Location> loc = new ArrayList<Location>();
	
	public static void fillGameLoc(File f) {
		Scanner scan = null;
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scan.useDelimiter("[,|\n]");
		
		String[] tempLoc = new String[5];
		while (scan.hasNext()) {
			for (int k = 0; k < 5; k ++) {
				tempLoc[k] = scan.next();
			}
			//					(X							 ,Y							   , Name	   , Country   , Side				
			loc.add(new Location(Integer.parseInt(tempLoc[0]), Integer.parseInt(tempLoc[1]), tempLoc[2], tempLoc[3],
					Side.toSide(tempLoc[4].substring(0, tempLoc[4].length() - 1))));
		}
	}
	
	/**
	 * Used to return the location of a country and the side to place the 
	 * @param count String three digit iso of country
	 * @param s	Side to get
	 * @return	Where to place the icon
	 */
	public static Location getImageLocation(String count, Side s) {
		for (Location l : loc) {
			if (l.country.equals(count) && l.side.equals(s)) return l;
		}
		return null;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		String toReturn = "";
		toReturn += "Location " + this.name + " at (" + this.x + ", " + this.y + ")";
		return toReturn;
	}
	
}
