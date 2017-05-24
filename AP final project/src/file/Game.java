package file;

public class Game {
	public static Player player = null;

	public static void startGame(Player p) {
		player = p;
		Debug.debug("player",player);
		Debug.debug("Starting game.");
		gameMenu();
	}

	private static void gameMenu() {
		int game = PopUp.buttonMessage("What do you want to do?", new String[] {"Actions", "Open Shop", "View Stats","Quit"});
		Debug.debug("game", game);
		switch(game) {
		case 0:
			Debug.debug("Closing program...");
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
		}
	}

	private static void userMenu(int menuNum) {
		switch (menuNum) {
		case 1:
			// user menu 1
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
		
		
		
	}
	
	private static void actions() {
		PopUp.dropDownMessage("What would you like to do?", new String[] {"Sleep", "Explore", "Eat", "Cancel"});
	}
}
