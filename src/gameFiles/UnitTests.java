package gameFiles;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class UnitTests {
	
	/**
	 * Tests Correct Setup
	 */
	@Test
	public void correctSetUp() {
		Board game = new Board();
		Controller.callInitialize(game);
		assertEquals(Map.getCountry("CAN").getUSInfluence(), 2); //  US influnecnce should be 2 in CAN at game start
		assertEquals(Map.getCountry("GBR").getUSInfluence(), 5); //  US influnecnce should be 5 in GBR at game start
		assertEquals(Map.getCountry("FIN").getUSSRInfluence(), 1); //USSR influence should be 1 in FIN at game start
		assertEquals(Map.getCountry("DDR").getUSSRInfluence(), 3); //USSR influence should be 3 in DDR at game start
		
	}

	/**
	 * Tests basic game operations
	 */
	@Test
	public void basicGameOpperations() {
		Board game = new Board();
		Controller.callInitialize(game);
		
		ArrayList<Card> testAgaints = new ArrayList<Card>();
		testAgaints.addAll(Deck.getEarlyCards());
		testAgaints.remove(6); // pulls the China Card
		for (int k = 0; k < 11; k ++) {
			if (k == 0) {
				assertEquals(Deck.getDeck(), testAgaints);
			}
			else if (k == 4) {
				testAgaints.addAll(Deck.getMidCard());
				assertEquals(Deck.getDeck().size(), testAgaints.size());
			}
			else if (k == 7) {
				testAgaints.addAll(Deck.getLateCard());
				assertEquals(Deck.getDeck().size(), testAgaints.size());
			}
			Controller.turn(game);
		}
		
	}
	
	
	
}