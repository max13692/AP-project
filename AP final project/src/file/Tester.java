package file;

public class Tester {
	public static void main(String args[]) {
		Debug.debug("Strating program...");
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
		if (name != null)
			tutorial(name.toUpperCase().charAt(0) + name.toLowerCase().substring(1, name.length()));
		else
			start();
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
			String[][] tutorial = Save.getMatrixFromFile("Data/tutorial.txt");
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
			// Start game
		}
	}
}
