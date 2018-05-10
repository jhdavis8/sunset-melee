package gameFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Map object holds the list of countries and related methods for setup and use
 * @author Josh Davis
 * @author Mark Wolgin
 */
public class Map {
	
	/** 
	 * ArrayList of all countries in the world 
	 */
	private static ArrayList<Country> world;
	/**
	 * csv File of country data
	 */
	private static File continentCSV;
	
	/**
	 * Assign a csv File to the Map and construct the world ArrayList
	 * @param cCSV country csv file
	 */
	public static void setFile(File cCSV) {
		continentCSV = cCSV;
		world = new ArrayList<Country>();
	}
	
	/**
	 * Build out the country ArrayList using the assigned csv to complete fields
	 */
	public static void fillCountries() {
		Scanner scan = null;
		
		try {
			scan = new Scanner(continentCSV);
			scan.useDelimiter("[,|\n]");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		String[] tempCountry = new String[6];
		int stbNum = 0;
		boolean bg = false;
		
		while (scan.hasNext()) {
			for (int k = 0; k < tempCountry.length; k ++) {
				tempCountry[k] = scan.next();
			}
			stbNum = new Integer(tempCountry[3]);
			
			if (tempCountry[4].equals("F")) {
				bg = false;
			}
			else {
				bg = true;
			}
			
			String[] tCont = tempCountry[0].split("[.]");
			Continents[] cont = new Continents[tCont.length];
			
			for (int k = 0; k < cont.length; k ++) {
				cont[k] = Continents.valueOf(tCont[k]);
			}
			
			String[] conCountry = tempCountry[5].split("[.|\r|\n]");
			
			world.add(new Country(tempCountry[2], tempCountry[1], stbNum, 0, 0, bg, cont, conCountry));
			
		}
	}
	
	/**
	 * Returns the country object that matches the ISO string given
	 * @param check the string with the ISO code
	 * @return Country object that matches
	 */
	public static Country getCountry(String check) {
		for (Country c : Map.getWorld()) {
			if (c.getISO().equals(check)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 *  Get a country object by its full name
	 * @param check name of a country
	 * @return the country matching the given name
	 */
	public static Country getCountryByName(String check) {
		for (Country c : Map.getWorld()) {
			if (c.getFullName().equals(check)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Calls the connectCountries function on each Country object in the world
	 */
	public static void connectCountries() {
		for (Country c : world) {
			c.connectCountries(world);
		}
	}

	/**
	 * @return the ArrayList of all Country objects in the world 
	 */
	public static ArrayList<Country> getWorld() {
		return world;
	}
	
	/**
	 * Sees if a country can have influence placed on it
	 * @param c Country to check
	 * @param side Side to check for
	 * @return True if the country can have influence spread to it
	 */
	public static boolean checkCountryChoice(Country c, Side side) {
		ArrayList<Country> all = new ArrayList<Country>();
		all.add(c); 
		all.addAll(c.getConnectedCountries());
		boolean atLeastOne = false;
		for (Country cont : all) {
			if (cont.getInfluence(side) > 0) {
				atLeastOne = true;
			}
		}
		return atLeastOne;
	}
	
}
