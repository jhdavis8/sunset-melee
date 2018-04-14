package gameFiles;

public enum Side {
	USA, USSR, UNK;
	
	public static String toString(Side c) {
		switch (c) {
			case USA: return "USA";
			case USSR: return "USSR";
			case UNK: return "UNK";
			default:  return "";
		}
	}
	
	public static Side toSide(String s) {
		switch (s) {
			case ("USA"): return USA;
			case ("USSR"): return USSR;
			case ("UNK"): return UNK;
			default: return UNK;
		}
	}
	
}
