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
	
	public Location (int xPos, int yPos, String nameOfLoc) {
		x = xPos;
		y = yPos;
		name = nameOfLoc;
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
		
		String[] tempLoc = new String[3];
		while (scan.hasNext()) {
			for (int k = 0; k < 3; k ++) {
				tempLoc[k] = scan.next();
			}
			loc.add(new Location(Integer.parseInt(tempLoc[0]), Integer.parseInt(tempLoc[1]), tempLoc[2]));
		}
	}
	
	public static Location getLocation(String name) {
		for (Location l : loc) {
			if (l.name.equals(name)) return l;
		}
		return null;
	}

	@Override
	public String toString() {
		String toReturn = "";
		toReturn += "Location " + this.name + " at (" + ", " + ")";
		return toReturn;
	}
	
}
