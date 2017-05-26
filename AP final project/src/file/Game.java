package file;

public class Game {
	public static Player player = null;

	public static void startGame(Player p) {
		player = p;
		Debug.debug("player", player);
		Debug.debug("Starting game.");
		gameMenu();
	}

	private static void gameMenu() {
		int game = PopUp.buttonMessage("What do you want to do?", new String[] {
				"Quit", "View Stats", "Open Shop", "Actions" });
		Debug.debug("game", game);
		switch (game) {
		case -1:
			Debug.debug("Closing program...");
			quitGame();
			break;
		case 0:
			Debug.debug("Closing program...");
			quitGame();
			System.exit(0);
			break;
		case 1:
			Debug.debug("Player has opened userMenu...");
			userMenu(1);
			break;
		case 2:
			Debug.debug("Player has opened the shop...");
			shop();
			break;
		case 3:
			Debug.debug("Player opened action menu...");
			actions();
			break;
		default:
			Debug.debug("Invalid selection...");
			gameMenu();
		}
	}

	private static String getBar(int amount) {
		String bar = "";
		final char BAR_CHAR = '|';
		final int AMOUNT_PER_CHAR = 5;
		for (int i = 0; i < amount / AMOUNT_PER_CHAR; i++)
			bar += BAR_CHAR + "";
		return bar;
	}

	private static void userMenu(int menuNum) {
		Debug.debug("menuNum", menuNum);
		switch (menuNum) {
		case 1:
			int stats = PopUp.buttonMessage(
					"Health:  " + getBar((int) player.getHealth())
							+ "\nHunger: " + getBar((int) player.getHunger())
							+ "\nThirst:   " + getBar((int) player.getHunger())
							+ "\nSanity:   " + getBar((int) player.getSanity())
							+ "\nWeapon: " + player.getDefualtItem(), "stats",
					new String[] { "Change wepeon", "Go back" });
			Debug.debug("stats", stats);
			if (stats == -1)
				quitGame();
			else if (stats == 0)
				userMenu(2);
			else if (stats == 1)
				gameMenu();
			break;
		case 2:
			// user menu 2
			break;
		case 3:
			// user menu 3
			break;
		case 4:
			// user menu 4
			break;
		case 5:
			// user menu 5
			break;
		case 6:
			// user menu 6
			break;
		default:
			Debug.error("menuNum", menuNum);
			Debug.error("This should not have this number.");
		}
	}

	private static void shop() {
		String matrix[][] = { 
				{ "I", "Wood Sword", "1.5", "1", "20" },
				{ "I", "2x4", "2.5", "1.75", "35"},
				{ "I", "Hammer", "5", "2", "50" },
				{ "I", "Crowbar", "10", "7", "100"},
				{ "I", "Shield", "5", "50", "150"},
				{ "I", "Spear", "15", "5", "200"},
				{ "I", "Chainsaw", "25", "5", "400"},
				{ "I", "Diamond Sword", "50", "10", "550"},
				{ "I", "Battle Axe", "75", "20", "700"},
				{ "I", "Energy Sword", "100", "50", "1000"},
				{ "I", "Heavenly Sword", "1000", "200", "5000"},
				{ "I", "Tactical Nuke", "10000000", "0", "1000000"},
				{ "F", "Pork", "20", "75" },
				{ "F", "Bread", "10", "50" },
				{ "F", "Apple", "5", "15" },
				{ "D", "Water", "15", "50" },
				{ "D", "Beer", "25", "100" },
				{ "D", "Apple Cyder", "15", "50" },
				{ "P", "Sanity Potion", "5", "-5", "50", "-5", "150" }, 
				{ "P", "Heal Potion", "50", "5", "-5", "5", "150" },
				{ "P", "Ultimate Potion", "25", "25", "25", "25", "350" } };
		//Split up shop into different parts?

	}

	
	private static void actions() {
		PopUp.dropDownMessage("What would you like to do?", new String[] {
				"Sleep", "Explore", "Eat", "Cancel" });
	}

	private static void quitGame() {
		Debug.debug("Closing game...");
		Debug.debug("Saving game...");
		Debug.debug("Finnished saving game.");
		Debug.debug("Goodbye");
		System.exit(0);
	}
}
