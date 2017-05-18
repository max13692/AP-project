package file;
import javax.swing.JOptionPane;
/* This class will create all the pop up windows in the game.
 * We created this constructor to make it easier to make 
 * windows and not have to type a bunch of code each time.
 * 
 */
public class PopUp {
	// Final title so all pop ups have the same title
	private static final String DEFAULT_TITLE = "Default Title";
	//Make a default message with multiple button responses and any title
	public static int buttonMessage(String text, String title, String[] buttons){
		return JOptionPane.showOptionDialog(null, text, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, null);
	}
	//Make a default message with multiple button responses and the final title
	public static int buttonMessage(String text, String[] buttons){
		return JOptionPane.showOptionDialog(null, text, DEFAULT_TITLE, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, null);
	}
	//Make a default message a drop down menu with multiple responses and any title
	public static String dropDownMessage(String text, String title, String[] options){
		return (String) JOptionPane.showInputDialog(null, text,title, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}
	//Make a default message a drop down menu with multiple responses and the default title
	public static String dropDownMessage(String text, String[] options){
		return (String) JOptionPane.showInputDialog(null, text,DEFAULT_TITLE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}
	//Creates a pop up that will let you input text with any title
		public static String textInput(String text, String title){
			return (String) JOptionPane.showInputDialog(null, text, title, JOptionPane.QUESTION_MESSAGE, null, null, null);
		}
	//Creates a pop up that will let you input text with default title
	public static String textInput(String text){
		return (String) JOptionPane.showInputDialog(null, text, DEFAULT_TITLE, JOptionPane.QUESTION_MESSAGE, null, null, null);
	}
	
}
