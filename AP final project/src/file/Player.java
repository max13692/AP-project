package file;

import java.util.ArrayList;

public class Player {
	private String playerName;
	private double health, hunger, sanity, thirst;
	private Time time;
	private ArrayList<Item> items;
	private int defaultItem;
	private int level, exp;
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
		items.add(new Item("Wood sword", 1, 1));
		defaultItem = 0;
	}

	public String getTime() {
		return time.getTime();
	}

	public boolean isDead() {
		return health <= 0;
	}

	public boolean addHealth(int health) {
		if (this.health == 100 || health <= 0)
			return false;
		this.health += health;
		if (this.health > 100)
			this.health = 100;
		return true;
	}

	public String takeDamage(int health) {
		if (this.health == 0 || health <= 0)
			return null;
		double damage = health-Math.round((Math.random() * getDefualtItem().getDefence()+1));
		this.health -= damage;
		if (this.health < 0)
			this.health = 0;
		return "You took for '" + damage + "' you have '" + this.health + "hp'";
	}

	public double getHealth() {
		return health;
	}

	public boolean addHunger(int hunger) {
		if (this.hunger == 100 || hunger <= 0)
			return false;
		this.hunger += hunger;
		if (this.hunger > 100)
			this.hunger = 100;
		return true;
	}

	public boolean subtractHunger(int hunger) {
		if (this.hunger == 0 || hunger <= 0)
			return false;
		this.hunger -= hunger;
		if (this.hunger < 0)
			this.hunger = 0;
		return true;
	}

	public double getHunger() {
		return hunger;
	}

	public boolean addThirst(int thirst) {
		if (this.thirst == 100 || thirst <= 0)
			return false;
		this.thirst += thirst;
		if (this.thirst > 100)
			this.thirst = 100;
		return true;
	}

	public boolean subtractThirst(int thirst) {
		if (this.thirst == 0 || thirst <= 0)
			return false;
		this.thirst -= thirst;
		if (this.thirst < 0)
			this.thirst = 0;
		return true;
	}

	public double getThirst() {
		return thirst;
	}

	public boolean addSanity(int sanity) {
		if (this.sanity == 100 || sanity <= 0)
			return false;
		this.sanity += sanity;
		if (this.sanity > 100)
			this.sanity = 100;
		return true;
	}

	public boolean subtractSanity(int sanity) {
		if (this.sanity == 0 || sanity <= 0)
			return false;
		this.sanity -= sanity;
		if (this.sanity < 0)
			this.sanity = 0;
		return true;
	}

	public double getSanity() {
		return sanity;
	}

	public boolean addGold(int gold) {
		if (this.gold == 100 || gold <= 0)
			return false;
		this.gold += gold;
		if (this.gold > 100)
			this.gold = 100;
		return true;
	}

	public boolean subtractGold(int gold) {
		if (this.gold == 0 || gold <= 0)
			return false;
		this.gold -= gold;
		if (this.gold < 0)
			this.gold = 0;
		return true;
	}

	public int getGold() {
		return gold;
	}

	public boolean addExp(int exp) {
		if (exp <= 0)
			return false;
		this.exp += exp;
		if(exp >= (level+1)*2) {
			level++;
			exp -= (level+1)*2;
		}
		return true;
	}

	public int getExp() {
		return exp;
	}

	public int getLevel() {
		return level;
	}

	public boolean addItem(Item item) {
		items.add(item);
		return true;
	}

	public Item getItem(int index) {
		if (index > items.size())
			return null;
		return items.get(index);
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public boolean setDefaultItem(int itemIndex) {
		if (itemIndex >= items.size())
			return false;
		defaultItem = itemIndex;
		return true;
	}

	public Item getDefualtItem() {
		return items.get(defaultItem);
	}

	public int getDefaultItemIndex() {
		return defaultItem;
	}

	public String[][] getData() {
		return null;
	}

	public String toString() {
		return playerName + " (level: " + level + ")";
	}
}
