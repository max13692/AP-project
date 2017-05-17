import java.text.SimpleDateFormat;

/*
 * The Debug class is for debugging the code during creation. 
 * This class will be used instead of System.out.println 
 * because it can be changed for our needs during the creation
 * of this project. 
 * ---------------------
 * Written by: Max Kassel
 * ---------------------
 */
public class Debug {
	// The format for the current time:
	static SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss.SSS");

	/*
	 * print takes in a String and then outputs that String to the console with
	 * no next line.
	 */
	public static void print(String text) {
		System.out.print(text);
	}

	/*
	 * println takes a String as an input then outputs that String to the
	 * console with a next line.
	 */
	public static void println(String text) {
		System.out.println(text);
	}

	/*
	 * debug takes in a String and an Object. The String will be used as the
	 * variable name and the object will be used as the value of the variable.
	 * The time, path and variable will be printed out on the line with a next
	 * line underneath it.
	 * 
	 */
	public static void debug(String variableName, Object variable) {
		System.out.println(getTime() + " " + getPath() + ": '" + getVarType(variable) + " " + variableName + " = "
				+ variable + "'");
	}

	/*
	 * debug takes in a String and outputs the time, path and the String with a
	 * next line. This is used for debug statements
	 */
	public static void debug(String text) {
		System.out.println(getTime() + " " + getPath() + ": '" + text + "'");
	}

	/*
	 * getVarType takes in an object then returns the type of variable as a
	 * String.
	 * 
	 */
	private static String getVarType(Object obj) {
		return obj.getClass().getSimpleName();
	}

	/*
	 * getPath returns the location of the call. This is only used in debug
	 * statements to find the location of the code. This might NOT work with
	 * other methods because it only works 2 method calls behind.
	 */
	private static String getPath() {
		String s = Thread.currentThread().getStackTrace()[3].toString();
		return s.substring(0, s.indexOf('(')) + " [Line:"
				+ s.substring(s.indexOf(":") + 1, s.indexOf(')', s.indexOf(":"))) + "]";
	}

	/*
	 * getTime returns the current time with the format of tf. To change format
	 * change tf.
	 */
	private static String getTime() {
		return "(" + tf.format(System.currentTimeMillis()) + ")";
	}
}
