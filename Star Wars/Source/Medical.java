import java.util.Random;

/**
 * 
 * @author Chou Thao
 * 
 */
public class Medical extends Droid {
	/**
	 * constructor
	 * 
	 * @param n    name of this Medical
	 * @param nT   number of task available
	 */
	public Medical(String n, int nT) {
		super(n, 100, nT);
	}

	/**
	 * performs tasks dependent on what task it possess currently if it
	 * currently has a number of tasks greater than zero left
	 * 
	 */
	public void doTask(Entity e) {
		if (getTask().equals("heal")) {
			Random rand = new Random();
			int healing = rand.nextInt(50) + 1;
			if (getNumTasks() > 0) {
				useTask();
				if (Jedi.class.isInstance(e)) {
					Jedi healMe = (Jedi) e;
					healMe.heal(healing);
				} else if (Rebel.class.isInstance(e)) {
					Rebel healMe = (Rebel) e;
					healMe.heal(healing);
				}
				System.out.println("Medical droid heals " + e.getName()
						+ " with " + healing + " hp.");
			} else {
				System.out
						.println("Medical Droid "
								+ getName()
								+ " has run out of number of tasks available to perform.");

			}
		} else {
			System.out.println("Medical Droid " + getName()
					+ " has no tasks upon which to perform.");
		}
	}

}
