package file;


public class Tester {
	public static void main(String args[]){
		Debug.debug("Strating program...");
				start();
				Debug.debug("Reached end of main method.");
			}
			
	public static void start(){
		Debug.debug("Opening Start menu...");
		int start = PopUp.buttonMessage("Welcome to the game", new String[] {"Start", "Help", "Close", "Extra"});
		Debug.debug("start", start);
		switch(start){
		case -1:
			System.exit(0);
			break;
		case 0:
			startGame();
			break;
		case 1:
			help();
			break;
		case 2:
			System.exit(0);
			break;
		case 3:
			
		break;
		default: 
			start();
			break;
		}
		
	}
	
	public static void startGame(){
		Debug.debug("Starting the game.");
		Debug.debug("Opening saves menu...");
		int saveNum = Integer.parseInt(PopUp.dropDownMessage("Choose a save:", "Saves", new String[]{Save.getSaveText(1),Save.getSaveText(2),Save.getSaveText(3)}).substring(5, 6));
		Debug.debug("saveNum", saveNum);
		String name = PopUp.textInput("What's your name?");
		
	}
	public static void help(){

		}
	
}
