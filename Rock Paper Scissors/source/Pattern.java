import java.io.Serializable;

/**
 * This is a Pattern Class which contains a local String
 * cohering to a series of varying characters and/or
 * integers correlating to a pattern.
 * 
 * @author Chou Thao
 *
 */
public class Pattern implements Serializable {
	/** A local String for a Pattern Object */
	private String decisions;
	
	/**
	 * A constructor of a Pattern object
	 * which initializes a local String.
	 * 
	 * @param choice initial value for
	 *               local String decisions
	 */
	public Pattern (String choice) {
		decisions = choice;
	}
	
	/**
	 * Gets local String decisions.
	 * 
	 * @return the local String
	 */
	public String getPattern() {
		return decisions;
	}
	
	/**
	 * Gets the hash code for a
	 * Pattern object base upon
	 * its local String's hash
	 * code.
	 * 
	 */
	public int hashCode() {
		return decisions.hashCode();
	}
	
	/**
	 * Checks to see if an Object is
	 * of the data type Pattern as
	 * well as being the same Pattern
	 * comparing their local String
	 * returning true, else return false.
	 * 
	 * @param data the object to compare
	 * @return     true if the object is
	 *             equal to this Pattern
	 *             object
	 * 
	 */
	public boolean equals(Object data) {
		if (this == data) {
			return true;
		}
		if (!(data instanceof Pattern)) {
			return false;
		}
		Pattern dat = (Pattern)data;
		if (decisions.equals(dat.getPattern())) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
