import java.util.Random;

/**
 * 
 * @author Chou Thao
 *
 */
public class Stormtropper extends Person {
	/**
	 * Constructs a Stormtropper
	 * 
	 * @param n    name of this Stormtropper
	 * @param q    quote of this Stormtropper
	 */
	public Stormtropper(String n, String q) {
		super("Stormtropper " + n, 150, "blaster", q);
	}
	
	/**
	 * performs a task dependent on
	 * the current task assigned
	 * 
	 */
	public void doTask(Entity e) {
		if (getTask().equals("attack")) {
			attack(e);
		} else {
			System.out.println("Stromtropper " + getName()
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
}
