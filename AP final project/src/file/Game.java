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
}
