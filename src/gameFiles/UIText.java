package gameFiles;

import java.util.Scanner;

/**
 * UI implementation solely based on command prompt
 * @author Josh Davis
 * @author Mark Wolgin
 *
 */
public class UIText implements UICore {
	

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
		for (Country c : currentBoard.getWorld()) {
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
				case ("q"):
					running = false;
					break;
				case ("help"):
					System.out.printf("mapstatus | a(dd) | q(uit) | help%n");
					break;
				default:
					System.out.println("Command unrecognized! Please try again.");
			}
		}
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
			country = currentBoard.getCountry(input);
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
		currentBoard.placeInfluence(side, country, value);
		System.out.println(country);
	}
	
}
