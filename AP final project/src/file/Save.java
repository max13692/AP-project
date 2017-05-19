package file;

import java.io.File;
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

	public static void saveData() {

	}
}
