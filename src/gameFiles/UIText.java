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
			toReturn += c;
		}
		
		return toReturn;
	}

}
