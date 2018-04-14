package gameFiles;

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
	
}
