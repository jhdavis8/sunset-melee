package gameFiles;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class UnitTests {
	
	@Test
	public void correctSetUp() {
		Board game = new Board();
		Controller.callInitialize(game);
		assertEquals(game.getCountry("CAN").getUSInfluence(), 2); //  US influnecnce should be 2 in CAN at game start
		assertEquals(game.getCountry("GBR").getUSInfluence(), 5); //  US influnecnce should be 5 in GBR at game start
		assertEquals(game.getCountry("FIN").getUSSRInfluence(), 1); //USSR influence should be 1 in FIN at game start
		assertEquals(game.getCountry("DDR").getUSSRInfluence(), 3); //USSR influence should be 3 in DDR at game start
	}

	@Test
	public void basicGameOpperations() {
		Board game = new Board();
		Controller.callInitialize(game);
		
		ArrayList<Card> testAgaints = new ArrayList<Card>();
		testAgaints.addAll(Deck.getEarlyCards());
		for (int k = 0; k < 11; k ++) {
			if (k == 0) {
				assertEquals(Deck.getDeck(), testAgaints);
			}
			else if (k == 4) {
				testAgaints.addAll(Deck.getMidCard());
				assertEquals(Deck.getDeck(), testAgaints);
			}
			else if (k == 7) {
				testAgaints.addAll(Deck.getLateCard());
				assertEquals(Deck.getDeck(), testAgaints);
			}
			Controller.turn(game);
		}
		
	}
	
	
	
}
