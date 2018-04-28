package gameFiles;

/**
 * UIGraphic is a implementation of UICore that will have a graphical interface an allow for a more
 * traditional windows interface.
 * @author Mark Wolgin
 * @author Josh Davis
 *
 */
public class UIGraphic implements UICore {

	/**
	 * Board currentBoard is the current state of world.  A reference point was placed here to allow for easy access.
	 */
	private Board currentBoard;

	/* Used to retrieve the most recent version of the Board
	 * (non-Javadoc)
	 * @see gameFiles.UICore#updateBoard(gameFiles.Board)
	 */
	@Override
	public void updateBoard(Board b) {
		// TODO Auto-generated method stub

	}

	/* Will output the new information.
	 * (non-Javadoc)
	 * @see gameFiles.UICore#updateUI()
	 */
	@Override
	public void updateUI() {

	}

	@Override
	public int promptSelectCard(Side side) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String promptUSA() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String promptUSSR() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void indicateNoCards() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void promptCardChoiceResult(int pickResult, Card choice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void announce(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Country promptValidInfluenceTarget(Side side) {
		// TODO Auto-generated method stub
		return null;
	}

}
