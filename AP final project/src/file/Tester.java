package file;

public class Tester {
	public static void main(String args[]) {
		Debug.debug("Strating program...");
		Save.checkForSaves();
		start();
		Debug.debug("Reached end of main method.");
	}

	public static void start() {
		Debug.debug("Opening Start menu...");
		int start = PopUp.buttonMessage("Welcome to the game", new String[] { "Extra", "Close", "Help", "Start" });
		Debug.debug("start", start);
		switch (start) {
		case -1:
			Debug.debug("User has clicked the x button... Closing program...");
			System.exit(0);
			break;
		case 3:
			startMenu();
			break;
		case 2:
			help();
			break;
		case 1:
			Debug.debug("User has clicked the close button... Closing program...");
			System.exit(0);
			break;
		case 0:

			break;
		default:
			Debug.error("Error, 'start' should not be this value. Going back to start...");
			Debug.error("start", start);
			start();
			break;
		}

	}

	public static void startMenu() {
		Debug.debug("Starting the game.");
		Debug.debug("Opening saves menu...");
		int saveNum = Integer.parseInt(PopUp
				.dropDownMessage("Choose a save:", "Saves",
						new String[] { Save.getSaveText(1), Save.getSaveText(2), Save.getSaveText(3) })
				.substring(5, 6));
		Debug.debug("saveNum", saveNum);
		String name = PopUp.textInput("What's your name?");
		tutorial(name);
	}

	public static void help() {
		Debug.debug("Starting help method...");
		String issues[] = { "issue 1", "issue 2", "issue 3" };
		String solutions[] = { "solution 1", "solution 2", "solution 3" };
		String problem = PopUp.dropDownMessage("What do you need help with?", "Help", issues);
		Debug.debug("problem", problem);
		if (problem != null) {
			for (int i = 0; i < issues.length; i++)
				if (issues[i] == problem)
					PopUp.textMessage(solutions[i], "Help");
			help();
		} else {
			Debug.debug("Recieved null reference going back to main.");
			start();
		}
	}

	public static void tutorial(String name) {
		int selection = PopUp.buttonMessage("Have you played before " + name + "? (and remeber how to play?)",
				new String[] { "Yes", "No" });
		if (selection == 0) {
			// start game
		} else {
			Debug.debug("Opening tutorial...");
			String[][] tutorial = {
					{ "You are abanoned on a island and need to surivive...", "Cancel", "Wait what?", "Continue", "" },
					{ "You need to susrive as long as possible...", "Cancel", "How long?", "I'll try it",
							"You will need to surivive a year to win the game" },
					{ "You will have items you can use and tasks you can preform", "Cancel", "Uumm ok?", "Cool",
							"Items will allow you to directly effect your stats. While actions will indirectly effect them" },
					{ "You will also have health, hunger, thirst, and snaity stats you need to watch...", "Cancel",
							"What do those do?", "Sounds good",
							"Your stats will determin if your healthly or not and when they drop to low you could die..." },
					{ "keep all the levels up and you should be good...", "Cancel", "What if I don't?", "OK",
							"You can die if your heath gets to low." },
					{ "Watch out something might try to attact you...", "Cancel", "Could I die?", "Oh ok",
							"Yes attacks can and will kill you" },
					{ "Make sure to get enough sleep or you'll go insane...", "Cancel", "What if I already am?",
							"I will sleep enough",
							"You should get checked out if your insaine... but I'm no doctor" } };
			for (int i = 0; i < tutorial.length; i++) {
				int num = PopUp.buttonMessage(tutorial[i][0],
						new String[] { tutorial[i][1], tutorial[i][2], tutorial[i][3] });
				Debug.debug("num", num);
				Debug.debug("Pop up continues tutorial menu.");
				if (num == 0) {
					Debug.debug("User has canceled tutorial...");
					start();
				} else if (num == 1) {
					Debug.debug("User wanted to see more information.");
					PopUp.textMessage(tutorial[i][4]);
				} else if (num == -1) {
					Debug.debug("User has clicked the x button... Closing program...");
					System.exit(0);

				}
			}
		}
	}
}
