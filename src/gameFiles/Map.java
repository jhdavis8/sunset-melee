package gameFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
	
	private static ArrayList<Country> world;
	private static File continentCSV;

	public static void setFile(File cCSV) {
		continentCSV = cCSV;
		world = new ArrayList<Country>();
	}
	
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
	
	public static void connectCountries() {
		for (Country c : world) {
			c.connectCountries(world);
		}
	}

	public static ArrayList<Country> getWorld() {
		return world;
	}
	
}
