import java.util.Random;
import java.io.Serializable;

public abstract class Character implements Serializable{
	
	private String name;
	private String quip;
	private int level;
	private int hp;
	private int gold;
	private int orgHp;
	
	public Character(String n, String q, int h, int l, int g) {
		name = n;
		quip = q;
		level = l;
		hp = h;
		orgHp = h;
		gold = g;
	}
	
	public abstract void attack(Character c);
	
	public String getName() {
		return name;
	}
	
	public String getQuip() {
		return quip;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getGold() {
		return gold;
	}
	
	public void increaseLevel() {
		hp = orgHp * ++level;
	}
	
	public void heal() {
		hp = orgHp;
	}
	
	public void takeDamage(int h) {
		System.out.println("Hp before dmg taken " + hp);
		if (h > hp) {
			hp = 0;
		}
		else {
			hp = hp - h;
		}
		System.out.println("Hp after dmg taken " + hp);
	}
	
	public void collectGold(int g) {
		gold = gold + g;
	}
	
	public void display() {
		System.out.println(getName() + " has " + getHp() + "hp ");
	}
	
}
