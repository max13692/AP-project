package file;

public class Enemy {
	private String name;
	private double health, attack;
	private int level;

	public Enemy(String name, double health, double attack, int level) {
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.level = level;
	}

	public double getAttack() {
		return attack;
	}

	public double getHealth() {
		return health;
	}
	
	public void addHealth(double heal) {
		health += heal;
	}
	public void updateHealth() {
		if(health < 0)
			health = 0;
	}
	public int getLevel() {
		return level;
	}

	public boolean isDead() {
		return health <= 0;
	}
	public String toString() {
		return "This " + name + " has a health of " + health
				+ " and an attack of " + attack + ".";
	}
}
