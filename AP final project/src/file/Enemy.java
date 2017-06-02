package file;

public class Enemy {
	private String name;
	private double health, attack, defense;
	private int level;

	public Enemy(String name, double health, double attack, double defense,
			int level) {
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.level = level;
	}

	public double getAttack() {
		return attack;
	}

	public double getHealth() {
		return health;
	}

	public double getDefense(){
		return defense;
	}
	public String takeDamamge(double amount) {
		if (amount <= 0)
			return null;
		double damage = amount - (int)((Math.random() * (defense)));
		if(damage < 0)
			damage = 0;
		health -= damage;
		return "You hit " + name + " for " + damage;
	}

	public boolean addHealth(double amount) {
		if (amount <= 0)
			return false;
		health -= amount;
		return true;
	}

	public int getLevel() {
		return level;
	}

	public boolean isDead() {
		return health <= 0;
	}

	public String toString() {
		return name + " (Level: " + level + ")";
	}
}
