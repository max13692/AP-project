package file;

import java.util.ArrayList;

public class Game {
	public static Player player = null;
private static int save;
	public static void startGame(Player p, int saveNum) {
		save = saveNum;
		player = p;
		Debug.debug("player", player);
		Debug.debug("Starting game.");
		gameMenu();
	}

	private static void gameMenu() {
		int game = PopUp.buttonMessage("-----Day: " + player.getTime().getDay() + "   " +player.getTime() + "-----"+"\nWhat do you want to do?",
				new String[] { "Quit", "View Stats", "Open Shop", "Actions" });
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
		if(amount <= 0)
			return "";
		final char BAR_CHAR = '|';
		String bar = BAR_CHAR+"";
		final int AMOUNT_PER_CHAR = 5;
		for (int i = 1; i < amount / AMOUNT_PER_CHAR; i++)
			bar += BAR_CHAR + "";
		return bar;
	}

	private static void userMenu(int menuNum) {
		Debug.debug("menuNum", menuNum);
		switch (menuNum) {
		case 1:
			int stats = PopUp.buttonMessage(
					"--------------" + player + "--------------" + "\nExp: "
							+ player.getExp() + "/" + player.getRequiredExp()
							+ "\nHealth:  " + getBar((int) player.getHealth())
							+ "\nHunger: " + getBar((int) player.getHunger())
							+ "\nThirst:   " + getBar((int) player.getHunger())
							+ "\nSanity:   " + getBar((int) player.getSanity())
							+ "\nWeapon: " + player.getDefualtItem()
							+ "\nGold: " + player.getGold() + "g", "stats",
					new String[] { "Change wepeon", "Go back" });
			Debug.debug("stats", stats);
			switch (stats) {
			case -1:
				quitGame();
				break;
			case 0:
				userMenu(2);
				break;
			case 1:
				gameMenu();
				break;
			default:

				break;
			}
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

	private static String[] getShopData(String matrix[][], String type, int col) {
		ArrayList<String> shopData = new ArrayList<String>();
		for (int i = 0; i < matrix.length; i++)
			if (matrix[i][0].equals(type))
				shopData.add(matrix[i][1] + " (" + matrix[i][col] + " gold)");
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
		String[] shopItems = null;
		String selection = null;
		ArrayList<Item> playerWeapons = player.getItems();
		boolean hasWeapon = false;
		int location = 0;
		int cost = 0;
		switch (shop) {
		case 4:
			shopItems = getShopData(matrix, "I", 4);
			selection = PopUp.dropDownMessage(
					"Welcome to the weapons shop. What can I get for you?",
					shopItems);
			Debug.debug("selection", selection);
			if (selection == null)
				shop();
			selection = selection.substring(0, selection.indexOf("(") - 1);
			Debug.debug("selection", selection);
			Debug.debug("Checking if player has item.");
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
			location = getLocation(matrix, selection);
			cost = Integer.parseInt(matrix[location][4]);
			if (cost > player.getGold())
				PopUp.textMessage("You don't have enough gold.");
			else if (hasWeapon)
				PopUp.textMessage("You already have this weapon.");
			else {
				player.subtractGold(cost);
				player.addItem(new Item(matrix[location][1], Double
						.parseDouble(matrix[location][2]), Double
						.parseDouble(matrix[location][3])));
				PopUp.textMessage("Weapon Bought");
			}
			break;
		case 3:
			shopItems = getShopData(matrix, "F", 3);
			selection = PopUp.dropDownMessage(
					"Welcome to the Grocery Store! What would you like?",
					shopItems);
			Debug.debug("selection", selection);
			if (selection == null)
				shop();
			selection = selection.substring(0, selection.indexOf("(") - 1);
			Debug.debug("selection", selection);
			location = getLocation(matrix, selection);
			Debug.debug("locaiton", location);
			cost = Integer.parseInt(matrix[location][3]);
			Debug.debug("cost", cost);
			if (cost > player.getGold())
				PopUp.textMessage("You don't have enough gold.");
			else {
				player.subtractGold(cost);
				player.addHunger(Integer.parseInt(matrix[location][2]));
				PopUp.textMessage("Enjoy your food!");
			}
			break;
		case 2:
			shopItems = getShopData(matrix, "D", 3);
			selection = PopUp
					.dropDownMessage(
							"Welcome to the bar! What can I serve ya today?",
							shopItems);
			Debug.debug("selection", selection);
			if (selection == null)
				shop();
			selection = selection.substring(0, selection.indexOf("(") - 1);
			Debug.debug("selection", selection);
			location = getLocation(matrix, selection);
			Debug.debug("locaiton", location);
			cost = Integer.parseInt(matrix[location][3]);
			Debug.debug("cost", cost);
			if (cost > player.getGold())
				PopUp.textMessage("You don't have enough gold.");
			else {
				player.subtractGold(cost);
				player.addThirst(Integer.parseInt(matrix[location][2]));
				PopUp.textMessage("Enjoy your drink!");
			}
			break;
		case 1:
			shopItems = getShopData(matrix, "P", 6);
			selection = PopUp.dropDownMessage(
					"Welcome to my hut! What could I brew for you today?",
					shopItems);
			Debug.debug("selection", selection);
			if (selection == null)
				shop();
			selection = selection.substring(0, selection.indexOf("(") - 1);
			Debug.debug("selection", selection);
			location = getLocation(matrix, selection);
			Debug.debug("locaiton", location);
			cost = Integer.parseInt(matrix[location][6]);
			Debug.debug("cost", cost);
			if (cost > player.getGold())
				PopUp.textMessage("You don't have enough gold.");
			else {
				player.subtractGold(cost);
				player.addHealth(Integer.parseInt(matrix[location][2]));
				player.addThirst(Integer.parseInt(matrix[location][3]));
				player.addSanity(Integer.parseInt(matrix[location][4]));
				player.addHunger(Integer.parseInt(matrix[location][5]));
				PopUp.textMessage("hehe enojoy");
			}
			break;
		case 0:
			gameMenu();
			break;
		case -1:
			quitGame();
		default:
			break;	
		}
		gameMenu();
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
			Debug.debug("Going for a walk.");
			int ran = (int) (Math.random() * 6) + 1;
			Debug.debug("ran", ran);
			PopUp.textMessage("You went on a walk for " + ran + " hours.");
			player.getTime().addTime(ran);
			player.subtractSanity(ran * 2);
			int random = (int) (ran * (Math.random() * 6) + 1);
			Debug.debug("random", random);
			if (random >= 0 && random < 5) {
				PopUp.textMessage("You didn't find anything...Sorry.");
			} else {
				if (random >= 5 && random < 30) {
					double gold = Math.random() + 1;
					Debug.debug("gold", gold);
					PopUp.textMessage("You found " + (int) (gold * random)
							+ " gold.");
					player.addGold(((int) (gold * random)));
				}
				if (random >= 12 && random <= 36) {
					double exp = Math.random() + 1;
					Debug.debug("exp", exp);
					PopUp.textMessage("You also get "
							+ (int) ((exp * random) / 2) + " experience.");
					player.addExp((int) ((exp * random) / 2));
				}
				if (random <= 5 && random < 18) {
					PopUp.textMessage("You got stung by a bee -" + random / 2
							+ " health");
					player.subtractHealth(random / 2);
				} else if (random >= 18 && random < 24) {
					fight(1);
				} else if (random >= 24 && random < 30) {
					fight(2);
					// find random food or drink
				} else if (random >= 30 && random <= 36) {
					fight(3);
					// find random shop item
				}
			}

			gameMenu();
			break;
		case 2:
			Debug.debug("Going to sleep");
			int sleepHours = (int) ((Math.random() * player.getTime()
					.getHours()) / 3);
			Debug.debug("sleepHours", sleepHours);
			int ran2 = (int) (Math.random() * 3);
			Debug.debug("ran2", ran2);
			if (sleepHours >= 5) {
				PopUp.textMessage("You slept really well.");
				player.addSanity(sleepHours * ran2);
				player.getTime().addTime(sleepHours);
			} else if (sleepHours < 5 && sleepHours >= 4) {
				PopUp.textMessage("You slept ok.");
				player.addSanity(sleepHours * ran2);
				player.getTime().addTime(sleepHours);
			} else {
				PopUp.textMessage("You didn't sleep well.");
				player.addSanity((int) (sleepHours * ran2) / 2);
				player.getTime().addTime(sleepHours);
			}
			gameMenu();
			break;
		case 3:
			String[] food = {"Berries", "Raw Meat", "Apple", "Banana", "Cooked Meat"};
			int ran3 = (int)(Math.random()*5);
			PopUp.textMessage("You found some " + food[ran3] + " and ate it");
			player.addHunger(ran3*5);
			gameMenu();
			break;
		default:
			Debug.debug("Error had happened and default was run...");
			gameMenu();
		}
	}

	private static void fight(int difficulty) {
		String names[][] = Save.getMatrixFromFile("Data/enemies.txt");
		Enemy enemy = new Enemy(names[(int) (Math.random() * names.length)][0],
				(int) (Math.random() * player.getLevel() * difficulty * 2) + 1,
				(int) (Math.random() * player.getLevel() * difficulty) + 1,
				(int) (Math.random() * player.getLevel() * difficulty) + 1,
				(int) (Math.random() * player.getLevel()) + difficulty);
		Debug.debug("enemy", enemy);
		PopUp.textMessage("You have incountered: " + enemy + "\nHealth: "
				+ getBar((int) enemy.getHealth()) + "\nAttack: "
				+ enemy.getAttack() + " Defence: " + enemy.getDefense());
		while (!player.isDead() && !enemy.isDead()) {
			PopUp.textMessage(enemy.takeDamamge(player.getDefualtItem()
					.getAttack()));
			if (enemy.isDead())
				break;
			PopUp.textMessage(player.takeDamage(enemy.getAttack()));
			Debug.debug("enemyHealth", enemy.getHealth());
			Debug.debug("playerHealth", player.getHealth());
		}
		if(enemy.isDead()) {
			PopUp.textMessage("You killed " + enemy + "!");
		player.addExp((int)(enemy.getLevel()*Math.random()*5));
		}
		if(player.isDead())
			PopUp.textMessage("YOU LOSE!!!!");
	}

	private static void quitGame() {
		Debug.debug("Closing game...");
		Debug.debug("Saving game...");
		Save.saveMatrix("saves/slot"+save+".txt",player.getData());
		Debug.debug("Finnished saving game.");
		Debug.debug("Goodbye");
		System.exit(0);
	}
}
