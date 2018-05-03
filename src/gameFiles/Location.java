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
	
	private static void fillGameLoc(File f) {
		Scanner scan = null;
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scan.useDelimiter("[,|\n]");
		
		String[] tempLoc = new String[3];
		while (scan.hasNext()) {
			
		}
	}

}
