package gameFiles;

import java.util.ArrayList;
import java.util.Scanner;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

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
		scan = new Scanner(System.in);
		Effects.setScanner(scan);
		
	}

	Scanner scan;
	
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
				case ("g"):
					promptPlayCard(scan);
					break;
				case ("h"):
					promptHeadlinePhase();
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
					promptPlayCardFromHand(scan);
					break;
				case ("r"):
					promptRollRealignemnt(scan);
					break;
				case ("t"):
					Controller.turn(currentBoard, this);
					break;
				case ("q"):
					running = false;
					break;
				case ("help"):
					System.out.printf("\nmapstatus | a(dd inifluence) | c(oup) | C(onnected Countries) | d(raw card for player) | f(ull status) -c -m | g(et card)\n"
							+ "h(eadline Phase) | p(layer commands) -d -p | P(lay card) | r(ealignment) | t(urn, advance) | q(uit) | help\n%n");
					break;
				default:
					System.out.println("Command unrecognized! Please try again.");
			}
		}
	}
	
	/**
	 * Handles text prompting for playing a card from the hand 
	 * @param scan Scanner object to read from
	 */
	private void promptPlayCardFromHand(Scanner scan) {
		System.out.println("Entering drawing card from player...");
		
		Side side = Side.getValidSide(scan, "Please enter a side");
		
		if (currentBoard.getPlayer(side).hasCards() == false) {
			System.out.println("This funciton has ended since the selected player has no cards.\n"
					+ "You can use the ... command to deal cards to a player.");
		}
		else {
			Player player = currentBoard.getPlayer(side);
			for (int k = 0; k < player.getHand().size(); k ++) {
				System.out.println("Card " + k + ":" + player.getHand().get(k));
			}
			boolean checking = true;
			String input = "";
			int cardIDInHand = 0;
			while (checking) {
				System.out.println("Please select a card by the number in the hand:");
				input = scan.nextLine();
				try {
					cardIDInHand = Integer.parseInt(input);
					checking = false;
				}
				catch (Exception e) {
					System.out.println("Not an integer...");
				}
			}
			Card card = player.getHand().get(cardIDInHand);
			card.runEffect();
		}
		
	}

	/**
	 * Handles general playing of cards by ID for testing
	 * @param scan Scanner object to read from
	 */
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

	/**
	 * Handles prompting for card dealing
	 * @param scan Scanner object to read from
	 */
	private void promptDealCard(Scanner scan) {
		System.out.println("Entering prompt deal card...");
		Side side = Side.getValidSide(scan, "Please enter a side to deal to:");
		Player player = currentBoard.getPlayer(side);
		System.out.println("Dealing Cards...");
		player.dealCards(currentBoard.getTurn());
		
	}

	/**
	 * Handles prompting for printing out the details of a player object
	 * @param scan Scanner object to read from
	 */
	private void promptPrintPlayer(Scanner scan) {
		System.out.println("Entering prompt print player...");
		Side side = Side.getValidSide(scan, "Please enter a player side to print:");
		Player player = currentBoard.getPlayer(side);
		System.out.println(player);
	}

	/**
	 * Prompt for getting info on connected countries
	 * @param scan Scanner
	 */
	private void promptConnectedCountry(Scanner scan) {
		System.out.println("Input ISO Code...");

		Country country = Country.getValidCountry(scan, "Please enter a country get its connections:");
		
		System.out.printf("%s is connected to the following Countries", country.getFullName());
		for (Country c : Map.getCountry(country.getISO()).getConnectedCountries()) {
			System.out.print(c);
		}
		
	}

	/**
	 * Prompt the Drawing of a Card
	 * @param scan Scanner
	 */
	private void promptDrawCard(Scanner scan) {
		System.out.println("Entering draw random card...");
		Side side = Side.getValidSide(scan, "Please enter a side to draw a Card for:");
		System.out.println(currentBoard.getPlayer(side).drawCard(-1));
		
	}

	/**
	 * Prompt the rolling of coup
	 * @param scan Scanner
	 */
	private void promptRollCoup(Scanner scan) {
		System.out.println("Entering coup prompt...");
		boolean checking = true;
		String input = "";
		
		Side side = Side.getValidSide(scan, "Please enter a side to roll a Coup for:");
		Country country = Country.getValidCountry(scan, "Please enter a country to coup:", side);
		
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
		currentBoard.getPlayer(side).rollCoup(country, card.cardNum, currentBoard);
		System.out.println(country);
		
		
	}

	/**
	 * Prompt the roll of a realignment
	 * @param scan Scanner
	 */
	private void promptRollRealignemnt(Scanner scan) {
		//Does not account for negative influence...
		System.out.println("Entering realignemnt prompt...");
		
		Side side = Side.getValidSide(scan, "Please enter a side to realign for:");
		Country country = Country.getValidCountry(scan, "Please enter a country to realign:", side);
		
		System.out.println(country);
		System.out.println("Rolling for realignment...");
		currentBoard.getPlayer(side).rollRealignment(country, 0);
		System.out.println(country);
	}

	/**
	 * Carries out prompt loops for a complete influence placement
	 * @param scan Scanner object to read from
	 */
	private void promptPlaceInfluence(Scanner scan) {
		System.out.println("Entering add influence prompt...");
		boolean checking = true;
		String input = "";
		
		Side side = Side.getValidSide(scan, "Please enter a side to add influence for:");
		Country country = Country.getValidCountry(scan, "Please enter a country to add influence to:");

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
	
	/**
	 * Handles prompting for the headline phase
	 */
	private void promptHeadlinePhase() {
		System.out.println("Entering Headline Phase...");
		if (currentBoard.getPlayer(Side.USA).getHand().size() == 0 ||
				currentBoard.getPlayer(Side.USSR).getHand().size() == 0) {
			System.out.println("You need to have cards in both decks before drawling.");
		}
		else {
			Controller.headlinePhase(currentBoard, this);
		}
	}
	
	@Override
	public void announce(String s) {
		System.out.println("[System Message] : " + s);
	}

	@Override
	public int promptSelectCard(Side side) {
		System.out.println("Player " + side + ", Please select a card");
		Player player = currentBoard.getPlayer(side);
		for (int k = 0; k < player.getHand().size(); k ++) {
			System.out.print("Card " + k + ":" + player.getHand().get(k));
		}
		boolean checking = true;
		String input = "";
		int cardIDInHand = 0;
		while (checking) {
			System.out.println("Please select a card by the number in the hand:");
			if (Side.USA.equals(side)) input = promptUSA();
			else input = promptUSSR();
			
			try {
				cardIDInHand = Integer.parseInt(input);
				checking = false;
			}
			catch (Exception e) {
				System.out.println("Not an integer...");
			}
		}
		return cardIDInHand;
	}

	@Override
	public String promptUSA() {
		System.out.print("USA, please input you answer: ");
		return scan.nextLine();
	}

	@Override
	public String promptUSSR() {
		System.out.print("USSR, please input you answer: ");
		return scan.nextLine();
	}

	@Override
	public void indicateNoCards() {
		System.out.println("Player, you have no cards remaining in your hand to draw, as such your phase will be passed untill the\n"
				+ "next turn when new cards will be dealt to you.");
	}

	@Override
	public void promptCardChoiceResult(int pickResult, Card card) {
		String toReturn = ("You have selected: " + card);
		if (pickResult == -1) {
			toReturn += "\nThis is a ScoringCard, and the effect has been tallied.";
		}
		else if (pickResult == 1) {
			toReturn += "\nPlease select one off the following options for how to use this card,\n";
			toReturn += "\t1: Place Influence\n\t2: Roll for Realignment in a Country"
					+ "\n\t3: Attempt a Coup in an Enemy Country\n\t4: Play the Cards Event";
			toReturn += "\nSelection: ";
			System.out.print(toReturn);
			String stringSel = "";
			int sel = 0;
			boolean checking = true;
			while (checking) {
				stringSel =scan.nextLine();
				try {
					sel = Integer.parseInt(stringSel);
					if ((sel <= 4) && (sel >= 1)) {
						checking = false;
					}
				}
				catch (Exception e) {
					System.out.println("Not a String");
				}
			}
			TurnCard tCard = (TurnCard) card;
			switch (sel) {
				case 1:
					currentBoard.placeInfluence(tCard, this);
					break;
				case 2:
					//currentBoard.rollRealignment(tCard);
					break;
				case 3:
					//currentBoard.rollCoup(tCard);
					break;
				case 4:
					tCard.runEffect();
					break;
				default:
					System.out.println("Error");
					Exception e = new Exception();
					e.getStackTrace();
			}
		}
		else if (pickResult == 0) {
			toReturn += "\nThis is an Enemy Card, and its effects have already been played";
			toReturn += "\nPlease select one off the following options for how to use this card,\n";
			toReturn += "\t1: Place Influence\n\t2: Roll for Realignment in a Country"
					+ "\n\t3: Attempt a Coup in an Enemy Country";
			toReturn += "\nSelection: ";
			System.out.print(toReturn);
			String stringSel = "";
			int sel = 0;
			boolean checking = true;
			while (checking) {
				stringSel =scan.nextLine();
				try {
					sel = Integer.parseInt(stringSel);
					if ((sel <= 3) && (sel >= 1)) {
						checking = false;
					}
				}
				catch (Exception e) {
					System.out.println("Not a String");
				}
			}
			TurnCard tCard = (TurnCard) card;
			switch (sel) {
				case 1:
					currentBoard.placeInfluence(tCard, this);
					break;
				case 2:
					//currentBoard.rollRealignment(tCard);
					break;
				case 3:
					//currentBoard.rollCoup(tCard);
					break;
				default:
					System.out.println("Error");
					Exception e = new Exception();
					e.getStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see gameFiles.UICore#promptValisdInfluenceTarget(gameFiles.Side)
	 */
	@Override
	public Country promptValidInfluenceTarget(Side side) { // TODO add some prompt messages
		boolean checking = true;
		boolean superChecking = false;
		String input = "";
		Country c = null;
		while(!superChecking) {
			while(checking) {
				input = scan.nextLine(); // TODO add text if not country
				if (Map.getCountry(input) != null) {
					c = Map.getCountry(input);
					checking = false;
				}
			}
			superChecking = this.checkCountryChoice(c, side);
		}
		return c;
	}

	private boolean checkCountryChoice(Country c, Side side) {
		ArrayList<Country> all = new ArrayList<Country>();
		all.add(c); // Check With FIN
		all.addAll(c.getConnectedCountries());
		boolean atLeastOne = false;
		for (Country cont : all) {
			if (cont.getInfluence(side) > 0) {
				atLeastOne = true;
			}
		}
		return atLeastOne;
	}
}
