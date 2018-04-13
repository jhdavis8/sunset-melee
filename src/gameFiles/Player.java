package gameFiles;

import java.util.ArrayList;

public class Player {

	private ArrayList<Card> hand;
	private Side side;
	
	public Player(Side s) {
		hand = new ArrayList<Card>();
		side = s;
	}
	
	public void playCard() {
		
	}
	
	public String toString() {
		String toReturn = "";
		toReturn += "Player: " + side + ", has the following cards:";
		
		for (Card c : hand) {
			toReturn += c + "\n";
		}
		
		return toReturn;
	}

}
