package gameFiles;

public class UIText implements UICore {
	

	private Board currentBoard;

	@Override
	public void updateBoard(Board b) {
		currentBoard = b;
		
	}

	@Override
	public String updateUI() {
		String toReturn = "GAME INFORMATION AND STATUS\n\n";
		toReturn += currentBoard;
		toReturn += "------------------------\n" + "ISO_ISO_CNE_STB_US, RA_INFLU\n";
		for (Country c : currentBoard.getWorld()) {
			if (c.getContinent().contains(Continents.EEE) || c.getContinent().contains(Continents.WEE)) {
				toReturn += c;
			}
		}
		toReturn += "------------------------\n" + "CRD_CLS_TYP_VAL_EID\n";
		for (Card c : currentBoard.getDeck()) {
			toReturn += c;
		}
		
		return toReturn;
	}

}
