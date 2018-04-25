package gameFiles;

/**
 * UIGraphic is a implementation of UICore that will have a graphical interface an allow for a more
 * traditonal windows interface.
 * @author Mark Wolgin
 * @author Josh Davis
 *
 */
public class UIGraphic implements UICore {

	/**
	 * Board currentBoard is the current state of world.  A refrence point was placed here to allow for easy access.
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

	/* Will output the new informtion.
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

}
