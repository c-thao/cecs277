/**
 * 
 * @author Chou Thao
 *
 */
public abstract class Droid extends Entity {
	/**
	 * number of task available
	 */
	private int numTasks;
	
	/**
	 * Constructs a Droid
	 * 
	 * @param n    name of Droid
	 * @param h    health of Droid
	 * @param nT   number of tasks
	 */
	public Droid(String n, int h, int nT) {
		super(n,h);
		numTasks = nT;
	}
	
	/**
	 * returns number of task available
	 * 
	 * @return    numtasks
	 */
	public int getNumTasks() {
		return numTasks;
	}
	
	/**
	 * uses a tasks limiting the
	 * number of task left
	 * 
	 */
	public void useTask() {
		if (getNumTasks() > 0) {
			numTasks--;
		}
	}
	
}
