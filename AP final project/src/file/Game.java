package file;

import java.util.ArrayList;

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
			switch (stats) {
			case -1:

				break;
			case 0:

				break;
			case 1:

				break;
			default:

				break;
			}
			if (stats == -1)
				quitGame();
			else if (stats == 0)
				userMenu(2);
			else if (stats == 1)
				gameMenu();
			break;
		case 2:
			String list[] = new String[player.getItems().size()];
			for (int i = 0; i < player.getItems().size(); i++)
				list[i] = player.getItem(i).toString();
			String item = PopUp.dropDownMessage(
					"Choose a weapon to use as your main weapon.", list);
			for (int i = 0; i < list.length; i++)
				if (list[i].equals(item)) {
					player.setDefaultItem(i);
					break;
				}
			userMenu(1);
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

	private static String[] getShopData(String matrix[][], String type) {
		ArrayList<String> shopData = new ArrayList<String>();
		for (int i = 0; i < matrix.length; i++)
			if (matrix[i][0].equals(type))
				shopData.add(matrix[i][1] + " (" + matrix[i][4] + " gold)");
		String arr[] = new String[shopData.size()];
		for (int i = 0; i < arr.length; i++)
			arr[i] = shopData.get(i);
		return arr;
	}

	private static int getLocation(String matrix[][], String item) {
		for (int i = 0; i < matrix.length; i++)
			if (item.equals(matrix[i][1]))
				return i;
		return -1;
	}

	private static void shop() {
		String matrix[][] = Save.getMatrixFromFile("Data/shop.txt");
		int shop = PopUp
				.buttonMessage(
						"Welcome to the shopping district. Where would you like to shop?",
						new String[] { "Go Back", "Witch Hut", "Bar",
								"Grocery Store", "Weapon Shop" });
		Debug.debug("shop", shop);
		switch (shop) {
		case 4:
			String shopItems[] = getShopData(matrix, "I");
			String selection = PopUp.dropDownMessage(
					"Welcome to the weapons shop. What can I get for you?",
					shopItems);
			Debug.debug("selection", selection);
			if (selection == null)
				shop();
			selection = selection.substring(0, selection.indexOf("(") - 1);
			Debug.debug("selection", selection);
			Debug.debug("Checking if player has item.");
			ArrayList<Item> playerWeapons = player.getItems();
			boolean hasWeapon = false;
			for (int i = 0; i < playerWeapons.size(); i++)
				if (playerWeapons
						.get(i)
						.toString()
						.substring(
								0,
								playerWeapons.get(i).toString().indexOf("(") - 1)
						.equals(selection)) {
					hasWeapon = true;
					break;
				}
			Debug.debug("Getting item price");
			int location = getLocation(matrix, selection);
			int cost = Integer.parseInt(matrix[location][4]);
			if (cost > player.getGold())
				PopUp.textInput("You don't have enough gold.");
			else if (hasWeapon)
				PopUp.textInput("You already have this weapon.");
			else {
				player.subtractGold(cost);
				player.addItem(new Item(matrix[location][1], Double
						.parseDouble(matrix[location][2]), Double
						.parseDouble(matrix[location][3])));
				PopUp.textMessage("Weapon Bought");
			}
			shop();
			break;
		case 3:

			break;
		case 2:

			break;
		case 1:

			break;
		case 0:
			gameMenu();
			break;
		case -1:
			quitGame();
		default:
			break;
		}
	}

	private static void actions() {
		int action = PopUp.buttonMessage("What would you like to do?",
				new String[] { "Cancel", "Explore", "Sleep", "Eat" });
		Debug.debug("action", action);
		switch (action) {
		case -1:
			Debug.debug("Player has closed program...");
			System.exit(0);
			break;
		case 0:
			Debug.debug("Player has cancelled actions menu...");
			gameMenu();
			break;
		case 1:
			int ran = (int) (Math.random() * 6) + 1;
			PopUp.textMessage("You went on a walk for " + ran + " hours.");
			int random = (int) (ran * (Math.random() * 6) + 1);
			double gold = Math.random() + 1;
			if (random >= 0 && random < 6) {
				PopUp.textMessage("You didn't find anything...Sorry.");
			} else if (random >= 6 && random < 12) {
				PopUp.textMessage("You found " + (int) (gold * random)
						+ " gold.");
				player.addGold((int) (gold * random));
			} else if (random >= 12 && random < 18) {
				PopUp.textMessage("You found " + (int) (gold * random)
						+ " gold.");
				player.addGold((int) (gold * random));
				PopUp.textMessage("You also get " + (int) ((gold * random) / 2)
						+ " experience.");
				player.addExp((int) ((gold * random) / 2));
				PopUp.textMessage("You got stung by a bee -" + random / 2
						+ " health");
				player.subtractHealth(random / 2);
			} else if (random >= 18 && random < 24) {
				PopUp.textMessage("You found " + (int) (gold * random)
						+ " gold.");
				player.addGold((int) (gold * random));
				PopUp.textMessage("You also get " + (int) ((gold * random) / 2)
						+ " experience.");
				player.addExp((int) ((gold * random) / 2));
				// fight weak enemy
			} else if (random >= 24 && random < 30) {
				PopUp.textMessage("You found " + (int) (gold * random)
						+ " gold.");
				player.addGold((int) (gold * random));
				PopUp.textMessage("You also get " + (int) ((gold * random) / 2)
						+ " experience.");
				player.addExp((int) ((gold * random) / 2));
				// fight enemy
				// find random food or drink
			} else if (random >= 30 && random <= 36) {
				PopUp.textMessage("You found " + (int) (gold * random) * 2
						+ " gold.");
				player.addGold((int) (gold * random));
				PopUp.textMessage("You also get " + (int) ((gold * random) / 2)
						+ " experience.");
				player.addExp((int) ((gold * random) / 2));
				// fight enemy
				// find random shop item
			}
			gameMenu();
			break;
		case 2:

			break;
		case 3:

			break;

		}
	}

	private static void quitGame() {
		Debug.debug("Closing game...");
		Debug.debug("Saving game...");
		Debug.debug("Finnished saving game.");
		Debug.debug("Goodbye");
		System.exit(0);
	}
}
