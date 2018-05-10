package debug;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import gameFiles.Board;
import gameFiles.Controller;
import gameFiles.Effects;
import gameFiles.Map;
import junit.framework.TestCase;

/**
 * Trying some assertions
 * @author Josh Davis
 * @author Mark Wolgin
 */
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
		try {
			Effects.getEffect(8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//assertEquals(Map.getCountry("CUB"), new Country("Cuba", "CUB", 3, 0, 3, true, new Continents[] {Continents.SCR}, new String[] {""}));
		
	}

	/**
	 * Tests basic game operations
	 */
	@Test
	public void basicGameOpperations() {
		/*
		Board game = new Board();
		UIText ui = new UIText();
		ui.updateBoard(game);
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
			Controller.turn(game, ui);
		}
		*/
		
	}
	
	
	
}
