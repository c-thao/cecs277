/**
 * 
 * @author Chou Thao
 *
 */
public class Computer extends Entity {
	/**
	 * Constructor
	 * 
	 * @param n     name of computer
	 */
	public Computer(String n) {
		super(n, 1000);
	}
	
	/**
	 * performs a task dependent on
	 * an Entity's task
	 * 
	 */
	public void doTask(Entity e) {
		if (getTask().equals("unlock")) {
			if (getActive()) {
				modifyHp(0);
				System.out.println(this.getName()
						+ " is now unlocked.");
			} else {
				System.out.println(this.getName()
						+ " has already been unlocked.");
			}
		} else {
			System.out.println(e.getName()
					+ " has no task upon which to perform");
		}
	}

}
