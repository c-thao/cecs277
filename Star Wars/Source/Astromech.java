/**
 * 
 * @author Chou Thao
 *
 */
public class Astromech extends Droid {
	/**
	 * Constructor
	 * 
	 * @param n     name of this Astromech
	 * @param nT    number of tasks
	 */
	public Astromech(String n, int nT) {
		super(n, 100, nT);
	}
	
	/**
	 * performs a task dependent on
	 * what tasks it possesses currently
	 * if it currently has a number of
	 * tasks greater than zero left
	 * 
	 */
	public void doTask(Entity e){
		if (getTask().equals("unlock")) {
			if (getNumTasks() > 0) {
				useTask();
				e.setTask("unlock");
				e.doTask(this);
			}
			else {
				System.out.println(getName()
						+ " has no more tasks available to perform");
			}
		} else {
			System.out.println(getName()
					+ " has no task upon which to perform");
		}
	}
	
}
