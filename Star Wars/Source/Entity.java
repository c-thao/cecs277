/**
 * 
 * @author Chou Thao
 *
 */
public abstract class Entity {
	/**
	 * name for an Entity
	 */
	private String name;
	/**
	 * health of an Entity
	 */
	private int hp;
	/**
	 * status of an Entity
	 */
	private boolean active;
	/**
	 * task of an Entity
	 */
	private String task;
	
	/**
	 * 
	 * @param n    name of this Entity
	 * @param h    health of this Entity
	 */
	public Entity(String n, int h) {
		name = n;
		hp = h;
		active = true;

	}
	
	/**
	 * abstract method in which an task 
	 * is to be perform
	 * 
	 * @param e    another entity
	 */
	public abstract void doTask(Entity e);
	
	/**
	 * Get the name of this Entity
	 * 
	 * @return    name of this Entity
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the current health of this
	 * Entity
	 * 
	 * @return    health of this Entity
	 */
	public int getHp() {
		return hp;
	}
	
	/**
	 * Get the current status of this
	 * Entity
	 * 
	 * @return    status of this Entity
	 */
	public boolean getActive() {
		if (hp == 0) {
			return false;
		} else {
			return active;
		}
	}
	
	/**
	 * Changes the health value of this
	 * Entity
	 * 
	 * @param h    health of this Entity
	 */
	public void modifyHp(int h) {
		hp = h;
	}
	
	/**
	 * Get the current task of this Entity
	 * 
	 * @return    the task of this Entity
	 */
	public String getTask() {
		return task;
	}
	
	/**
	 * Sets the task for this Entity
	 * to t
	 * 
	 * @param t    a task
	 */
	public void setTask(String t) {
		task = t;
	}

}
