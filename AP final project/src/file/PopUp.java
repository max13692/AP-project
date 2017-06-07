package file;

import javax.swing.JOptionPane;

/* This class will create all the pop up windows in the game.
 * We created this constructor to make it easier to make 
 * windows and not have to type a bunch of code each time.
 * ---------------------------
 * Written by: Kyllan Wunder
 * ---------------------------
 */
public class PopUp {
	// Final title so all pop ups have the same title
	private static final String DEFAULT_TITLE = "Survival";
	private static final int DEFUALT_TYPE = JOptionPane.PLAIN_MESSAGE;

	/*
	 * button message is a short cut to the showOptionDialog method in the
	 * JOptionPane Class, it only takes in text, a title and the buttons.
	 */
	public static int buttonMessage(String text, String title, String[] buttons) {
		return JOptionPane.showOptionDialog(null, text, title, JOptionPane.DEFAULT_OPTION, DEFUALT_TYPE, null, buttons,
				null);
	}

	// Make a default message with multiple button responses and the final title
	public static int buttonMessage(String text, String[] buttons) {
		return JOptionPane.showOptionDialog(null, text, DEFAULT_TITLE, JOptionPane.DEFAULT_OPTION, DEFUALT_TYPE, null,
				buttons, null);
	}

	// Make a default message a drop down menu with multiple responses and any
	// title
	public static String dropDownMessage(String text, String title, String[] options) {
		return (String) JOptionPane.showInputDialog(null, text, title, DEFUALT_TYPE, null, options, options[0]);
	}

	// Make a default message a drop down menu with multiple responses and the
	// default title
	public static String dropDownMessage(String text, String[] options) {
		return (String) JOptionPane.showInputDialog(null, text, DEFAULT_TITLE, DEFUALT_TYPE, null, options, options[0]);
	}

	// Creates a pop up that will let you input text with any title
	public static String textInput(String text, String title) {
		return (String) JOptionPane.showInputDialog(null, text, title, DEFUALT_TYPE, null, null, null);
	}

	// Creates a pop up that will let you input text with default title
	public static String textInput(String text) {
		return (String) JOptionPane.showInputDialog(null, text, DEFAULT_TITLE, DEFUALT_TYPE, null, null, null);
	}

	// Creates a blank popup with just text
	public static void textMessage(String text, String title) {
		JOptionPane.showMessageDialog(null, text, title, DEFUALT_TYPE);
	}

	public static void textMessage(String text) {
		JOptionPane.showMessageDialog(null, text, DEFAULT_TITLE, DEFUALT_TYPE);
	}

}
