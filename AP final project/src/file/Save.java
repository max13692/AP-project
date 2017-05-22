package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
	public static String getSaveText(int saveNum) {
		String s = "empty";
		return "Save " + saveNum + " (" + s + ")";
	}

	public static void setUpSaves() {
		try {
			Debug.debug("Creating save files...");
			new File("saves").mkdir();
			new FileWriter("saves/slot1.txt").close();
			new FileWriter("saves/slot2.txt").close();
			new FileWriter("saves/slot3.txt").close();
			Debug.debug("Created save files.");
		} catch (IOException e) {
			Debug.error("error creating save files.");
			e.printStackTrace();
		}
	}

	public static boolean checkForSaves() {
		if (!new File("saves").exists()) {
			Debug.error("Did not find saves folder, creating new folder.");
			setUpSaves();
			return false;
		} else if (!(new File("saves/slot1.txt").exists() && new File("saves/slot2.txt").exists()
				&& new File("saves/slot3.txt").exists())) {
			Debug.error("Missing one or more of the file(s).");
			setUpSaves();
			return false;
		}
		return true;
	}
private static int arrayLength(String arr[]){
	int count = 0;
	for(String i: arr)
		if(i != null)
			count++;
	return count;
}
	public static void saveMatrix(String fileName, String[][] data) {
		String text = "";
		int pos = 0;
		for(int i = 0; i < data.length;i++){
			for(int j = 0; j < data[i].length;j++)
				if(data[i][j] != null)
					if(pos == 0){
						if(text != "")
							text+=",\n";
						if(arrayLength(data[i]) > 1)
							text += "[ ";
						text += "[\"" + data[i][j] +  "\"";
						pos++;
					}
					else
						text+= ",\"" + data[i][j] + "\"";
			pos=0;
		text+="]";
		if(arrayLength(data[i]) > 1)
			text += " ]";
		}
		
		 try {
			File f1 = new File(fileName);
			f1.createNewFile();
			Debug.debug(f1.exists()+"");
			FileWriter f2 = new FileWriter(fileName);
			f2.write(text);
			f2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}

	public static String[][] getMatrixFromFile(String fileName) {
		String matrix[][] = new String[10][30];
		String text = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String str;
			while ((str = in.readLine()) != null)
				text += str;
			in.close();
			Debug.debug("text", text);
			Debug.debug("Got text from save files.");
			int x = 0;
			int y = 0;
			int pos = 0;
			int i = 0;
			while (pos < text.length()) {
				if (text.charAt(pos) != '[')
					pos++;
				else {
					if (text.indexOf(']', pos + 1) < text.indexOf('[', pos + 1)) {
						matrix[x][y] = text.substring(text.indexOf('\"', pos) + 1,
								text.indexOf('\"', text.indexOf('\"', pos) + 1));
						x++;
						pos = text.indexOf('\"', text.indexOf('\"', pos) + 1) + 1;
					} else {
						pos++;
						int endPos = text.indexOf(']', pos);
						while (pos < endPos - 1) {
							matrix[x][y] = text.substring(text.indexOf('\"', pos) + 1,
									text.indexOf('\"', text.indexOf('\"', pos) + 1));
							y++;
							pos = text.indexOf('\"', text.indexOf('\"', pos) + 1) + 1;
						}
						x++;
						y = 0;
					}
				}
			}
		} catch (IOException e) {
			Debug.error("error creating save files.");
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			Debug.error("There is an issue with the text file");
			e.printStackTrace();
			return null;
		}
		return matrix;
	}

}
