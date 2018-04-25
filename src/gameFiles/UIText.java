package gameFiles;

import java.util.Scanner;

/**
 * UI implementation solely based on command prompt
 * @author Josh Davis
 * @author Mark Wolgin
 *
 */
public class UIText implements UICore {
	

	/**
	 * The current Board
	 */
	private Board currentBoard;

	/* (non-Javadoc)
	 * @see gameFiles.UICore#updateBoard(gameFiles.Board)
	 */
	@Override
	public void updateBoard(Board b) {
		currentBoard = b;
		
	}

	/* (non-Javadoc)
	 * @see gameFiles.UICore#updateUI()
	 */
	@Override
	public void updateUI() {
		String toReturn = "MAP INFORMATION AND STATUS\n\n";
		toReturn += currentBoard;
		toReturn += "------------------------\n" + "ISO_ISO_CNE_STB_US, RA_INFLU\n";
		for (Country c : Map.getWorld()) {
			if (c.getContinent().contains(Continents.EEE) || c.getContinent().contains(Continents.WEE)) {
				toReturn += c;
			}
		}
		/*
		toReturn += "------------------------\n" + "CRD_CLS_TYP_VAL_EID\n";
		for (Card c : currentBoard.getDeck()) {
			toReturn += c;
		}
		*/
		System.out.println(toReturn);
	}
	
	/**
	 * TEMPORARY UI loop for testing only
	 */
	public void runUI() {
		Scanner scan = new Scanner(System.in);
		boolean running = true;
		String command = "";
		while (running) {
			System.out.println("Please enter a command: ");
			command = scan.nextLine();
			switch (command) {
				case ("mapstatus"):
					updateUI();
					break;
				case ("a"):
					promptPlaceInfluence(scan);
					break;
				case ("r"):
					promptRollRealignemnt(scan);
					break;
				case ("c"):
					promptRollCoup(scan);
					break;
				case ("C"):
					promptConnectedCountry(scan);
					break;
				case ("d"):
					promptDrawCard(scan);
					break;
				case ("f"):
					System.out.println("full - m(ap) c(ards)");
					command = scan.nextLine();
					switch (command) {
						case ("m"):
							for (Country c : Map.getWorld()) {
								System.out.print(c);
							}
							break;
						case ("c"):
							System.out.println("------------------------\n" + "CRD_CLS_TYP_VAL_EID\n");
							for (Card c : Deck.getDeck()) {
								System.out.print(c);
							}
							System.out.println();
							System.out.println("------------------------\n" + "CRD_CLS_TYP_VAL_EID\n");
							for (Card c : Deck.getDiscard()) {
								System.out.print(c);
							}
							break;
						default:
							System.out.println("Not valid input");
							break;
					}
					break;
				case ("p"):
					System.out.println("player -d(eal) -p(rint)");
					command = scan.nextLine();
					switch (command) {
						case ("d"):
							promptDealCard(scan);
							break;
						case ("p"):
							promptPrintPlayer(scan);
							break;
					}
					break;
				case ("P"):
					promptPlayCard(scan);
					break;
				case ("t"):
					Controller.turn(currentBoard);
					break;
				case ("q"):
					running = false;
					break;
				case ("help"):
					System.out.printf("\nmapstatus | a(dd) | c(oup) | C(onnected Countries) | d(raw) | f(ull) -c -m\n"
							+ "p(layer) -d -p | P(lay card) | r(ealignment) | t(urn) | q(uit) | help\n%n");
					break;
				default:
					System.out.println("Command unrecognized! Please try again.");
			}
		}
	}
	
	private void promptPlayCard(Scanner scan) {
		System.out.println("Entering play card testing...");
		boolean checking = true;
		String input = "";
		Side side = null;
		int cardID = 0;
		while (checking) {
			System.out.println("Please enter a Card ID:");
			input = scan.nextLine();
			try {
				cardID = Integer.parseInt(input);
				if (side != Side.UNK) {
					System.out.printf("Side is: %s%n", side);
					checking = false;
				}
				else {
					System.out.println("Not a side!");
					continue;
				}
			}
			catch (Exception e) {
				System.out.println("Not an integer...");
			}
		}
		Card card = Deck.getCard(cardID);
		System.out.println(card);
		card.runEffect();
	}

	private void promptDealCard(Scanner scan) {
		System.out.println("Entering prompt deal card...");
		boolean checking = true;
		String input = "";
		Side side = null;
		while (checking) {
			System.out.println("Please enter a side to deal to:");
			input = scan.nextLine();
			side = Side.toSide(input);
			if (side != Side.UNK) {
				System.out.printf("Side is: %s%n", side);
				checking = false;
			}
			else {
				System.out.println("Not a side!");
				continue;
			}
		}
		Player player = currentBoard.getPlayer(side);
		System.out.println("Dealing Cards...");
		player.dealCards(currentBoard.getTurn());
	}

	private void promptPrintPlayer(Scanner scan) {
		System.out.println("Entering prompt print player...");
		boolean checking = true;
		String input = "";
		Side side = null;
		while (checking) {
			System.out.println("Please enter a player side to print:");
			input = scan.nextLine();
			side = Side.toSide(input);
			if (side != Side.UNK) {
				System.out.printf("Side is: %s%n", side);
				checking = false;
			}
			else {
				System.out.println("Not a side!");
				continue;
			}
		}
		Player player = currentBoard.getPlayer(side);
		System.out.println(player);
	}

	/**
	 * Prompt for the ConnectedCountry
	 * @param scan Scanner
	 */
	private void promptConnectedCountry(Scanner scan) {
		System.out.println("Input ISO Code...");
		boolean checking = true;
		String input = "";
		Country country = null;
		while (checking) {
			System.out.println("Please enter a country to coup:");
			input = scan.nextLine();
			country = Map.getCountry(input);
			if (country != null) {
				System.out.printf("Country is: %s%n", country);
				checking = false;
			}
			else {
				System.out.println("Not a country!");
				continue;
			}
		}
		System.out.println(Map.getCountry(country.getISO()));
		
	}

	/**
	 * Prompt the Drawing of a Card
	 * @param scan Scanner
	 */
	private void promptDrawCard(Scanner scan) {
		System.out.println("Entering draw random card...");
		boolean checking = true;
		String input = "";
		Side side = null;
		while (checking) {
			System.out.println("Please enter a side to draw a Card for:");
			input = scan.nextLine();
			side = Side.toSide(input);
			if (side != Side.UNK) {
				System.out.printf("Side is: %s%n", side);
				checking = false;
			}
			else {
				System.out.println("Not a side!");
				continue;
			}
		}
		System.out.println(currentBoard.getPlayer(side).playCard(-1));
		
	}

	/**
	 * Prompt the rolling of coup
	 * @param scan Scanner
	 */
	private void promptRollCoup(Scanner scan) {
		System.out.println("Entering coup prompt...");
		boolean checking = true;
		String input = "";
		Side side = null;
		while (checking) {
			System.out.println("Please enter a side to roll a Coup for:");
			input = scan.nextLine();
			side = Side.toSide(input);
			if (side != Side.UNK) {
				System.out.printf("Side is: %s%n", side);
				checking = false;
			}
			else {
				System.out.println("Not a side!");
				continue;
			}
		}
		checking = true;
		Country country = null;
		while (checking) {
			System.out.println("Please enter a country to coup:");
			input = scan.nextLine();
			country = Map.getCountry(input);
			if (country != null && country.opponentHasInfluence(side)) {
				System.out.printf("Country is: %s%n", side);
				checking = false;
			}
			else {
				System.out.println("Not a country!");
				continue;
			}
		}
		checking = true;
		Card card = null;
		while (checking) {
			//Need to add int checking...
			for (Card c : Deck.getDeck()) {
				System.out.print(c);
			}
			System.out.println("Please select a card:");
			input = scan.nextLine();
			card = Deck.getCard(Integer.parseInt(input));
			if (card != null && (card instanceof TurnCard)) {
				System.out.printf("Card is: %s%n", card);
				checking = false;
			}
			else {
				System.out.println("Not a valid card!");
				continue;
			}
		}
		System.out.println(country);
		checking = true;
		System.out.println("Rolling a coup...");
		currentBoard.getPlayer(side).rollRealignment(country, card.cardNum);
		System.out.println(country);
		
		
	}

	/**
	 * Prompt the roll of a realignment
	 * @param scan Scanner
	 */
	private void promptRollRealignemnt(Scanner scan) {
		//Does not account for negative influence...
		System.out.println("Entering realignemnt prompt...");
		boolean checking = true;
		String input = "";
		Side side = null;
		while (checking) {
			System.out.println("Please enter a side to realign for:");
			input = scan.nextLine();
			side = Side.toSide(input);
			if (side != Side.UNK) {
				System.out.printf("Side is: %s%n", side);
				checking = false;
			}
			else {
				System.out.println("Not a side!");
				continue;
			}
		}
		checking = true;
		Country country = null;
		while (checking) {
			System.out.println("Please enter a country to realign:");
			input = scan.nextLine();
			country = Map.getCountry(input);
			if (country != null && country.opponentHasInfluence(side)) {
				System.out.printf("Country is: %s%n", side);
				checking = false;
			}
			else {
				System.out.println("Not a country or no opponent influence!");
				continue;
			}
		}
		System.out.println(country);
		checking = true;
		System.out.println("Rolling for realignment...");
		currentBoard.getPlayer(side).rollRealignment(country, 0);
		System.out.println(country);
		/*
		int value = -1;
		while (checking) {
			System.out.println("Please enter a number of influence to add:");
			input = scan.nextLine();
			value = Integer.parseInt(input);
			if (value > 0) {
				System.out.printf("Value is: %d%n", value);
				checking = false;
			}
			else {
				System.out.println("Invalid value!");
				continue;
			}
		}
		currentBoard.placeInfluence(side, country, value);
		System.out.println(country);
		
		}
		*/
	}

	/**
	 * Carries out prompt loops for a complete influence placement
	 * @param scan Scanner object to read from
	 */
	private void promptPlaceInfluence(Scanner scan) {
		System.out.println("Entering add influence prompt...");
		boolean checking = true;
		String input = "";
		Side side = null;
		while (checking) {
			System.out.println("Please enter a side to add influence for:");
			input = scan.nextLine();
			side = Side.toSide(input);
			if (side != Side.UNK) {
				System.out.printf("Side is: %s%n", side);
				checking = false;
			}
			else {
				System.out.println("Not a side!");
				continue;
			}
		}
		checking = true;
		Country country = null;
		while (checking) {
			System.out.println("Please enter a country to add influence to:");
			input = scan.nextLine();
			country = Map.getCountry(input);
			if (country != null) {
				System.out.printf("Country is: %s%n", side);
				checking = false;
			}
			else {
				System.out.println("Not a country!");
				continue;
			}
		}
		System.out.println(country);
		checking = true;
		int value = -1;
		while (checking) {
			System.out.println("Please enter a number of influence to add:");
			input = scan.nextLine();
			value = Integer.parseInt(input);
			if (value > 0) {
				System.out.printf("Value is: %d%n", value);
				checking = false;
			}
			else {
				System.out.println("Invalid value!");
				continue;
			}
		}
		currentBoard.getPlayer(side).placeInfluence(country, value);
		System.out.println(country);
	}
	
}
