import java.util.Random;

/**
 * 
 * @author Chou Thao
 *
 */
public class SithLord extends Person implements HasForce {
	/**
	 * color of a SithLord's saber
	 */
	private String saberColor;
	
	/**
	 * constructor
	 * 
	 * @param n    name of this SithLord
	 * @param q    quote of this SithLord
	 * @param c    color of saber
	 */
	public SithLord(String n, String q, String c) {
		super(n, 250, "lightsaber", q);
		saberColor = c;
	}
	
	/**
	 * performs a task dependent
	 * on the task this SithLord
	 * possesses currently
	 * 
	 */
	public void doTask(Entity e) {
		if (getTask().equals("attack")) {
			attack(e);
		} else if (getTask().equals("use force")) {
			useForce(e);
		} else {
			System.out.println("SithLord " + getName()
					+ " has no tasks upon which to perform.");
		}
	}
	
	/**
	 * uses the force upon another Entity
	 * in effect modifying their health
	 * 
	 */
	public void useForce(Entity e) {
		Random ran = new Random();
		int frc = ran.nextInt(75);
		if (frc == 0) {
			System.out.println(getName() + " missed.");
		} else if (frc > e.getHp()) {
			e.modifyHp(0);
			sayCatchPhrase();
		} else {
			e.modifyHp(e.getHp() - frc);
		}
		System.out.println(getName() + " uses the force and does " + frc
				+ " damage to " + e.getName() + ".");
	}
	
	/**
	 * attacks another Entity in effect
	 * modifying their health
	 * 
	 */
	public void attack(Entity e) {
		Random ran = new Random();
		int atk = ran.nextInt(50);
		if (atk > e.getHp()) {
			e.modifyHp(0);
			this.sayCatchPhrase();
		} else {
			e.modifyHp(e.getHp() - atk);
		}

		if (atk == 0) {
			System.out.println(getName() + " missed.");
		} else {
			System.out.println(getName() + " attacks with a " + saberColor
					+ " saber" + " dealing " + atk + " damage to "
					+ e.getName() + ".");
		}
	}
}
