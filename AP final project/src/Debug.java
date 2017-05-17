
public class Debug {
	public static void print(String text){
		System.out.print(text);
	}
	public static void println(String text){
		System.out.println(text);
	}
	public static void debug(String variableName, Object variable){
		System.out.println(getPath() + ": '" + getVarType(variable) + " "+ variableName + " = " + variable + "'");
	}
	public static void debug(String text){
		System.out.println(getPath() + ": '" + text + "'");
	}
	private static  String getVarType(Object obj){
		if(obj instanceof Integer){
			return "int";
		}
		else{
			return obj.getClass().toString();
		}
	}
	public static String getPath(){
		String s = Thread.currentThread().getStackTrace()[3].toString();
		return  s.substring(0, s.indexOf('('))  + " [Line:" + s.substring(s.indexOf(":")+1,s.indexOf(')',s.indexOf(":"))) + "]";
	}
}
