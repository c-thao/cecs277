import java.util.Random;

/**
 * 
 * @author Chou Thao
 *
 */
public class Rebel extends Person implements Healable {
	/**
	 * Constructs a Rebel
	 * 
	 * @param n    name of this Rebel
	 * @param q    quote of this Rebel
	 */
	public Rebel(String n, String q) {
		super("Rebel " + n, 150, "blaster", q);
	}
	
	/**
	 * performs a task dependent on
	 * the current task assigned
	 * 
	 */
	public void doTask(Entity e) {
		if (getTask().equals("attack")) {
			attack(e);
		} else if (getTask().equals("heal")) {
			e.setTask(getTask());
			e.doTask(this);
		} else {
			System.out.println("Rebel " + getName()
					+ " has no tasks upon which to perform.");
		}
	}
	
	/**
	 * attacks another Entity in turn
	 * modifying their health accordingly
	 * 
	 */
	public void attack(Entity e) {
		Random rand = new Random();
		int atk = rand.nextInt(25);
		if (atk > e.getHp()) {
			e.modifyHp(0);
			this.sayCatchPhrase();
		} else {
			e.modifyHp(e.getHp() - atk);
		}

		if (atk == 0) {
			System.out.println(getName() + " missed.");
		} else {
			System.out.println(getName() + " attacks with a " + getWeapon()
					+ " dealing " + atk + " damage to " + e.getName() + ".");
		}
	}
	
	/**
	 * modifies this Rebel's health
	 * in turn recovering health
	 *(adding extra health to 
	 * current health)
	 * 
	 */
	public void heal(int hp) {
		this.modifyHp(hp + getHp());
	}

}
