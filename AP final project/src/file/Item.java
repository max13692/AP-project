package file;


public class Item {
	private String name;
	private double attack, defense;

	public Item(String[] data) {
		try {
			this.name = data[0];
			this.attack = Integer.parseInt(data[1]);
			this.defense = Integer.parseInt(data[2]);
		} catch (NumberFormatException e) {
			Debug.error("Error trying to initialize item.");
			Debug.error(e.getMessage());
		}
	}

	public Item(String name, double attack, double defense) {
		this.name = name;
		this.attack = attack;
		this.defense = defense;
	}

	public Item(String data) {
		this.name = data.substring(0, data.indexOf('(') - 1);
		data = data.replace("attack: ", "!").replace(" defense: " , "@");
		data = data.substring(data.indexOf("!"));
		this.attack = Double.parseDouble(data.substring(data.indexOf("!")+1,data.indexOf("@")));
		this.defense = Double.parseDouble(data.substring(data.indexOf("@")+1).replace(")", ""));
	}

	public double getAttack() {
		return attack;
	}

	public double getDefense() {
		return defense;
	}

	public String[] getData() {
		return new String[] { name, "" + attack, "" + defense };
	}

	public String toString() {
		return name + " (attack: " + attack + " defense: " + defense + ")";
	}
}
