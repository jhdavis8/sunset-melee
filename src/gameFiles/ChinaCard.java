/**
 * 
 */
package gameFiles;

/**
 * @author Mark Wolgin
 * @author Josh Davis
 * 
 * The ChianCard is so important to the game, that it needed its own values to keep track of who has it.
 */
public class ChinaCard extends TurnCard {
	/**
	 * indicated which side has the card currently
	 */
	private Side whoHasCard = null;
	
	/**
	 * Construct the China Card
	 * @param n Card Name, i.e. Europe Scoring Card.
	 * @param n Card Name, i.e. Europe Scoring Card.
	 * @param d Card Description, Information relevant to the card.
	 * @param c Card Number, The number for the card.
	 * @param e EffectID, The unique identification code for use with in game logic and the Effects class.
	 * @param cT Card Timing, Different cards get shuffled into the deck at different points in the game, such
	 * E - Early Game, M - Mid Game, L - Late Game.
	 * @param s Side, the affiliation of the card to the Super Power.
	 * @param sc Special Card, some cards are special, and should not be put back into the game after there use, and such flagged cards
	 * will have special logic to manage them.
	 * @param v Card Value, How much power a card has to affect events and actions.
	 * @param whoHas the Side that currently holds the China Card
	 */
	public ChinaCard(String n, String d, int c, int e, Side s, boolean sc, int v, String cT, Side whoHas) {
		super(n, d, c, e, s, sc, v, cT);
		whoHasCard = whoHas;
	}
	
	/**
	 * @return the side currently holding the card
	 */
	public Side getCurrentHolder() {
		return whoHasCard;
	}
	
	/**
	 * Change the current holder
	 * @param side Side to change to
	 */
	public void setCurrentHolder(Side side) {
		whoHasCard = side;
	}

}
