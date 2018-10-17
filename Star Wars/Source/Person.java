/**
 * 
 * @author Chou Thao
 *
 */
public abstract class Person extends Entity {
	/**
	 * weapon for attack
	 */
	private String weapon;
	
	/**
	 * quote which a Person says
	 */
	private String quip;
	
	/**
	 * constructor
	 * 
	 * @param n    name of this Person
	 * @param h    health of this Person
	 * @param w    weapon of this Person
	 * @param q    quote of this Person
	 */
	public Person(String n, int h, String w, String q) {
		super(n, h);
		weapon = w;
		quip = q;
	}
	
	/**
	 * outputs this Person's quote
	 */
	public void sayCatchPhrase() {
		System.out.println(this.getName() + " shouts " + quip);
	}
	
	/**
	 * abstract method to attack another 
	 * Entity
	 * 
	 * @param e    an Entity
	 */
	public abstract void attack(Entity e);
	
	
	/**
	 * returns the weapon of this Person
	 * 
	 * @return    weapon of this Person
	 */
	public String getWeapon() {
		return weapon;
	}
}
