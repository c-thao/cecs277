
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Hero extends Character {

	private ArrayList<Item> items;
	private Point location;

	public Hero(String n, String q, Point start) {
		super(n, q, 20, 1, 10);
		location = start;
		items = new ArrayList<Item>();
	}

	public void attack(Character c) {
		int dmg = new Random().nextInt(10) + getLevel() + items.size();
		c.takeDamage(dmg);
		System.out.println(getName() + " deals " + dmg + " damage to "
				+ c.getName());
		if (c.getHp() == 0) {
			System.out.println(c.getName() + " has been vanquished");
		}
	}

	public boolean pickUpItem(Item i) {
		if (items.size() < 5) {
			items.add(i);
			return true;
		} else {
			System.out.println("Oops looks like your inventory is full");
			return false;
		}
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void removeItem(Item i) {
		if (items.contains(i)) {
			items.remove(items.indexOf(i));
		} else {
			System.out.println("What item dufus.");
		}
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point p) {
		location = p;
	}

	public char goNorth(Level l) {
		Point temp = location;
		temp.setLocation(location.getX() - 1, location.getY());
		if (l.getRoom(temp) == 'n') {
			location.setLocation(location.getX() + 1, location.getY());
			return 'n';
		}
		else {
			return l.getRoom(temp);
		}
	}

	public char goSouth(Level l) {
		Point temp = location;
		temp.setLocation(location.getX() + 1, location.getY());
		if (l.getRoom(temp) == 'n') {
			location.setLocation(location.getX() - 1, location.getY());
			return 'n';
		}
		else {
			return l.getRoom(temp);
		}
	}

	public char goWest(Level l) {
		Point temp = location;
		temp.setLocation(location.getX(), location.getY() - 1);
		if (l.getRoom(temp) == 'n') {
			location.setLocation(location.getX(), location.getY() + 1);
			return 'n';
		}
		else {
			return l.getRoom(temp);
		}
	}

	public char goEast(Level l) {
		Point temp = location;
		temp.setLocation(location.getX(), location.getY() + 1);
		if (l.getRoom(temp) == 'n') {
			location.setLocation(location.getX()+1, location.getY() - 1);
			return 'n';
		}
		else {
			return l.getRoom(temp);
		}
	}

}
