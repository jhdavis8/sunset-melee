package gameFiles;

public enum Side {
	USA, USSR;
	
	public String toString(Side c) {
		switch (c) {
			case USA: return "USA";
			case USSR: return "USSR";
			default:  return "";
		}
	}
	
}
