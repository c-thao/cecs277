

import java.util.Random;

public class Enemy extends Character{
	private Item item;
	
	public Enemy (String n, String q, int h, int l, int g, Item i) {
		super(n,q,h*l,l,g);
		item = i;
	}
	
	public Item getItem() {
		return item;
	}

	public void attack(Character c) {
		int dmg = new Random().nextInt(5)+getLevel();
		c.takeDamage(dmg);
		System.out.println(getName() + " deals " + dmg + " damage to " + c.getName());
		if (c.getHp() == 0) {
			System.out.println(c.getName() + " has been vanquished");
		}
	}
	
}
