package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
	public static String getSaveText(int saveNum) {
		Debug.debug("Getting save: " + saveNum);
		String matrix[][] = getMatrixFromFile("saves/slot" + saveNum + ".txt");
		if (matrix == null)
			return "Save " + saveNum + " (Empty)";
		else
			return "Sace " + saveNum + " (Name " + matrix[0][0] + ")";
	}

	private static int arrayLength(String arr[]) {
		int count = 0;
		for (String i : arr)
			if (i != null)
				count++;
		return count;
	}

	public static void saveMatrix(String fileName, String[][] data) {
		String text = "";
		int pos = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++)
				if (data[i][j] != null)
					if (pos == 0) {
						if (text != "")
							text += ",\n";
						if (arrayLength(data[i]) > 1)
							text += "[ ";
						text += "[\"" + data[i][j] + "\"";
						pos++;
					} else
						text += ",\"" + data[i][j] + "\"";
			pos = 0;
			text += "]";
			if (arrayLength(data[i]) > 1)
				text += " ]";
		}

		try {
			File f1 = new File(fileName);
			f1.createNewFile();
			Debug.debug(f1.exists() + "");
			FileWriter f2 = new FileWriter(fileName);
			f2.write(text);
			f2.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	private static String getTextFromFile(String fileName) {
		String text = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String str;
			while ((str = in.readLine()) != null)
				text += str;
			in.close();
		} catch (IOException e) {
			Debug.error("Error reading file: " + fileName);
			e.printStackTrace();
			return null;
		}
		return text;
	}

	public static String[][] getMatrixFromFile(String fileName) {
		String tempMatrix[][] = new String[50][50];
		String text = getTextFromFile(fileName);
		Debug.debug("text", text);
		Debug.debug("Got text from save files.");
		if (text == null || text == "")
			return null;
		try {

			Debug.debug("Getting text from the file");
			int x = 0;
			int y = 0;
			int pos = 0;
			while (pos < text.length()) {
				if (text.charAt(pos) != '[')
					pos++;
				else {
					if (text.indexOf(']', pos + 1) < text.indexOf('[', pos + 1)) {
						tempMatrix[x][y] = text.substring(text.indexOf('\"', pos) + 1,
								text.indexOf('\"', text.indexOf('\"', pos) + 1));
						x++;
						pos = text.indexOf('\"', text.indexOf('\"', pos) + 1) + 1;
					} else {
						pos++;
						int endPos = text.indexOf(']', pos);
						while (pos < endPos - 1) {
							tempMatrix[x][y] = text.substring(text.indexOf('\"', pos) + 1,
									text.indexOf('\"', text.indexOf('\"', pos) + 1));
							y++;
							pos = text.indexOf('\"', text.indexOf('\"', pos) + 1) + 1;
						}
						x++;
						y = 0;
					}
				}
			}
		} catch (StringIndexOutOfBoundsException e) {
			Debug.error("File: " + fileName + " is currupt, or has an critical issue.");
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			Debug.error("There is an issue with the text file");
			e.printStackTrace();
			return null;
		}
		Debug.debug("Cleaning up the matrix.");
		int l = 0;
		int w = 0;
		int maxW = 0;
		for (int i = 0; i < tempMatrix.length; i++) {
			for (int j = 0; j < tempMatrix[i].length; j++)
				if (tempMatrix[i][j] != null)
					w++;
				else
					break;
			if (tempMatrix[i][0] == null) {
				l = i;
				break;
			}
			if (w > maxW)
				maxW = w;
			w = 0;
		}
		Debug.debug("Added values to the new array.");
		String matrix[][] = new String[l][maxW];
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				matrix[i][j] = tempMatrix[i][j];
		return matrix;
	}
}
