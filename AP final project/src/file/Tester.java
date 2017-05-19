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
			startGame();
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
			Debug.error("start",start);
			start();
			break;
		}

	}

	public static void startGame() {
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
		int selection = PopUp.buttonMessage("Have you played before "+name+ "? (and remeber how to play?)", new String[] {"Yes","No"});
		if(selection == 0) {
			//start game
		}
		else {
			int num1 = PopUp.buttonMessage("You are abanoned on a island and need to surivive...", new String[] {"Cancle", "Wait what?", "Continue"});
			Debug.debug("num1", num1);
			if(num1 == 0) {start();}
			PopUp.buttonMessage("You need to susrive as long as possible...", new String[] {"Cancle", "I'll try it", "How long?"});
			if(num1 == 0) {start();}
			PopUp.buttonMessage("You will have items you can use and tasks you can preform", new String[] {"Cancle", "Cool", "Uumm ok?"});
			if(num1 == 0) {start();}
		}
			
	}
}
}
