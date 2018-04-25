package gameFiles;

import java.util.Scanner;

/**
 * The ENUM for Sides in the game, it is used in most classes as an identifier.
 * @author Mark Wolgin
 * @author Josh Davis
 */
public enum Side {
	USA, USSR, UNK;
	
	/** Takes a Side and returns the string representation of it.
	 * @param c Side c is a side
	 * @return The string representation of side
	 */
	public static String toString(Side c) {
		switch (c) {
			case USA: return "USA";
			case USSR: return "USSR";
			case UNK: return "UNK";
			default:  return "";
		}
	}
	
	/**
	 * Takes a String and returns a side
	 * @param s String s is any string
	 * @return Returns the Side representation of String
	 * 
	 */
	public static Side toSide(String s) {
		switch (s) {
			case ("USA"): return USA;
			case ("USSR"): return USSR;
			default: return UNK;
		}
	}
	
	public static Side getValidSide(Scanner scan, String message) {
		boolean checking = true;
		String input = "";
		Side side = null;
		while (checking) {
			System.out.println(message);
			input = scan.nextLine();
			side = Side.toSide(input);
			if (side != Side.UNK) {
				System.out.printf("Side is: %s%n", side);
				checking = false;
			}
			else {
				System.out.println("Not a side!");
				continue;
			}
		}
		return side;
	}

	public Side opponent(Side side) {
		if (side == Side.USA) return Side.USSR;
		if (side == Side.USSR) return Side.USA;
		else return Side.UNK;
	}
	
}
