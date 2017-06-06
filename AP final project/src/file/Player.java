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
		gold = 100;
		level = 1;
		time = new Time();
		items = new ArrayList<Item>();
		items.add(new Item("Wood sword", 1, 1));
		defaultItem = 0;
	}

	public Player(String[][] data) {
		playerName = data[0][0];
		time = new Time(data[1][0]);
		health = Double.parseDouble(data[2][0]);
		hunger = Double.parseDouble(data[3][0]);
		sanity = Double.parseDouble(data[4][0]);
		thirst = Double.parseDouble(data[5][0]);
		defaultItem = Integer.parseInt(data[6][0]);
		level = Integer.parseInt(data[7][0]);
		exp = Integer.parseInt(data[8][0]);
		gold = Integer.parseInt(data[9][0]);
		items = new ArrayList<Item>();
		for (int i = 0; i < data[10].length; i++) {
			if (data[10][i] == null)
				break;
			items.add(new Item(data[10][i]));
		}
	}

	public Time getTime() {
		return time;
	}

	public boolean isDead() {
		return health <= 0 || sanity <= 0 || thirst <= 0 || hunger <= 0;
	}

	public String getDeathMessage(){
		if(health <= 0)
			return "You should have called a docter";
		if(hunger <= 0)
			return "You collapsed from lack of food, come one bro.";
		if(thirst <= 0)
			return "Bro you could have drunk anything, you died.";
		if(sanity <= 0)
			return "You trippin bro, sleep more next time.";
		Debug.error("error not dead");
		return null;
	}
	public boolean addHealth(int health) {
		if (this.health == 100 || health <= 0)
			return false;
		this.health += health;
		if (this.health > 100)
			this.health = 100;
		return true;
	}

	public boolean subtractHealth(int health) {
		if (this.health == 0 || health <= 0)
			return false;
		this.health -= health;
		if (this.health < 0)
			this.health = 0;
		return true;
	}

	public String takeDamage(double health) {
		Debug.debug("health", health);
		if (this.health == 0 || health <= 0) {
			Debug.error("Error with takeDamage");
			return null;
		}
		double damage = health - (int) ((Math.random() * getDefualtItem().getDefense() + 1));
		if (damage < 0)
			damage = 0;
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
		if (!(time.getHours() > 9 && time.getHours() < 15))
			thirst *= 2;
		this.thirst += thirst;
		if (this.thirst > 100)
			this.thirst = 100;
		return true;
	}

	public boolean subtractThirst(int thirst) {
		if (this.thirst == 0 || thirst <= 0)
			return false;
		if (time.getHours() > 9 && time.getHours() < 15)
			thirst *= 2;
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
		if (time.getHours() > 22 || time.getHours() < 5)
			sanity *= 2;
		this.sanity += sanity;
		if (this.sanity > 100)
			this.sanity = 100;
		return true;
	}

	public boolean subtractSanity(int sanity) {
		if (this.sanity == 0 || sanity <= 0)
			return false;
		if (!(time.getHours() > 22 || time.getHours() < 5))
			sanity *= 2;
		this.sanity -= sanity;
		if (this.sanity < 0)
			this.sanity = 0;
		return true;
	}

	public double getSanity() {
		return sanity;
	}

	public boolean addGold(int gold) {
		if (gold <= 0)
			return false;
		this.gold += gold;
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
		while (this.exp >= getRequiredExp()) {
			level++;
			PopUp.textMessage("You have leveled up to level " + level + "!");
			this.exp -= getRequiredExp();
		}
		return true;
	}

	public int getRequiredExp() {
		return (int) (level + 2) * 25;

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
		String matrix[][] = new String[11][20];
		matrix[0][0] = playerName;
		matrix[1][0] = time.getData() + "";
		matrix[2][0] = health + "";
		matrix[3][0] = hunger + "";
		matrix[4][0] = sanity + "";
		matrix[5][0] = thirst + "";
		matrix[6][0] = defaultItem + "";
		matrix[7][0] = level + "";
		matrix[8][0] = exp + "";
		matrix[9][0] = gold + "";
		for (int i = 0; i < items.size(); i++)
			matrix[10][i] = items.get(i).toString();
		return matrix;
	}

	public String toString() {
		return playerName + " (level: " + level + ")";
	}

}
