package file;

import java.util.ArrayList;

public class Player {
	private String playerName;
	private double health, hunger, sanity, thirst;
	private Time time;
	private ArrayList<Item> items;
	private int gold;

	public Player(String name) {
		playerName = name;
		health = 100;
		hunger = 100;
		sanity = 100;
		thirst = 100;
		gold = 0;
		time = new Time();
		items = new ArrayList<Item>();
	}

	public String getTime() {
		return time.getTime();
	}

	public boolean isDead() {
		return health <= 0;
	}

	private void updateHealth() {
		if (health > 100)
			health = 100;
		else if (health < 0)
			health = 0;
	}

	public void addHealth(int heal) {
		health += heal;
		updateHealth();
	}

	public double getHealth() {
		return health;
	}

	private void updateHunger() {
		if (hunger > 100)
			hunger = 100;
		else if (hunger < 0)
			hunger = 0;
	}

	public void addHunger(int food) {
		hunger += food;
		updateHunger();
	}

	public double getHunger() {
		return hunger;
	}

	private void updateThirst() {
		if (thirst > 100)
			thirst = 100;
		else if (thirst < 0)
			thirst = 0;
	}

	public void addThirst(int liquid) {
		hunger += liquid;
		updateThirst();
	}

	public double getThirst() {
		return thirst;
	}

	private void updateSanity() {
		if (sanity > 100)
			sanity = 100;
		else if (sanity < 0)
			hunger = 0;
	}

	public void addSanity(int sleep) {
		hunger += sanity;
		updateSanity();
	}

	public double getsanity() {
		return sanity;
	}

	public String[][] getData() {
		return null;
	}

	public String toString() {
		return "Health: " + health + "/nHunger: " + hunger + "/nSanity: "
				+ sanity + "/nThirst: " + thirst;
	}
}
