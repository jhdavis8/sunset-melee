package gameFiles;

public enum Continents {
	EEE, WEE, FFF, NNN, SRR, SCR, UUU, ABB, MMM;
	
	/*
	 * EEE - East Europe
	 * WEE - West Europe
	 * FFF - Africa
	 * NNN - North America
	 * SRR - South America
	 * SCR - Central America
	 * UUU - South East Asia
	 * ABB - Asia
	 * MMM - Middle East
	 */
	
	public String toString(Continents c) {
		switch (c) {
			case EEE: return "EEE";
			case WEE: return "WEE";
			case FFF: return "FFF";
			case NNN: return "NNN";
			case SRR: return "SRR";
			case SCR: return "SCR";
			case UUU: return "UUU";
			case ABB: return "ABB";
			case MMM: return "MMM";
			default:  return "";
		}
	}
}
