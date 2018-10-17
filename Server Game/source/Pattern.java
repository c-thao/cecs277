import java.io.Serializable;

/**
 * this is a Pattern Object which
 * contains a local String cohering
 * to a series of varying characters
 * and/or integers correlating to a
 * pattern
 * 
 * @author Chou Thao
 *
 */
public class Pattern implements Serializable {
	/**
	 * a local String for
	 * a Pattern Object
	 * 
	 */
	private String decisions;
	
	/**
	 * a constructor of a Pattern
	 * object which initializes 
	 * a local String
	 * 
	 * @param choice initial value
	 * 				 for local String
	 * 				 decisions
	 */
	public Pattern (String choice) {
		decisions = choice;
	}
	
	/**
	 * gets local String decisions
	 * 
	 * @return the local String
	 */
	public String getPattern() {
		return decisions;
	}
	
	/**
	 * gets the hash code for
	 * a Pattern object base
	 * upon its local String's
	 * hash code
	 * 
	 */
	public int hashCode() {
		return decisions.hashCode();
	}
	
	/**
	 * checks to see if an Object is
	 * of the data type Pattern as
	 * well as being the same Pattern
	 * comparing their local String
	 * returning true else return false
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
