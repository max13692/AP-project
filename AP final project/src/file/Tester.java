package file;

import javax.swing.JOptionPane;

public class Tester {
	public static void main(String args[]){
				start();
					PopUp.dropDownMessage("Hey boiz", "whats up", new String[] {"NO", "YEs", "coolio", "maybe"});
			}
			
	public static void start(){
		int start = PopUp.buttonMessage("Welcome to the game", new String[] {"Start", "Help", "Close", "Extra"});
		Debug.debug("start", start);
		switch(start){
		case 0:
			startGame();
		case 1:
			help();
		case 2:
			
		case 3:
		break;
		}
		
	}
	
	public static void startGame(){
		PopUp.textInput("What's your name?");
		
	}
	public static void help(){
		String choices[] = {"Wont run", "Cant load save", "Im trash"};
		String help[] = {"If the game won't run, what r u doing?", "Maybe ur just trahs?"};
			String thing = PopUp.dropDownMessage("What's the Problem?", choices );
			
			
		}
	
}
