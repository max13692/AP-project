package file;

public class Item {
	String name;
	double attack, defence;
	
	public Item(String[] data){
		this.name = data[0];
		this.attack = Integer.parseInt(data[1]);
		this.defence = Integer.parseInt(data[2]);
	}
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
		return name + " (attack: " + attack + " defence: " + defence + ")";
	}
}
