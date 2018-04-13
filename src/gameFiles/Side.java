package gameFiles;

public enum Side {
	USA, USSR, UNK;
	
	public String toString(Side c) {
		switch (c) {
			case USA: return "USA";
			case USSR: return "USSR";
			case UNK: return "UNK";
			default:  return "";
		}
	}
	
}
