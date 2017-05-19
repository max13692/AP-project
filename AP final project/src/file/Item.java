package file;

public class Item {
	String name;
	double attack, defence;

	public Item(String name, double attack, double defence) {
		this.name = name;
		this.attack = attack;
		this.defence = defence;
	}

	public double getAttack() {
		return attack;
	}

	public double getDefence() {
		return defence;
	}

	public String[] getData() {
		return new String[] { name, "" + attack, "" + defence };
	}

	public String toString() {
		return name + " (a: " + attack + " d: " + defence + ")";
	}
}
