import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

/**
 * This is the Computer class which makes an inform
 * decision base upon a Pattern object as well as
 * stores previously analyzed Pattern objects into
 * a local HashMap.
 * 
 * @author Chou Thao
 *
 */
public class Computer implements Serializable {
	/** a HashMap for storing Pattern objects */
	private HashMap<Pattern, Integer> patterns;
	
	/**
	 * The default constructor for a Computer object
	 * which instantiates a local HashMap
	 * 
	 */
	public Computer() {
		patterns = new HashMap<Pattern, Integer>();
	}
	
	/**
	 * Makes a prediction after analyzing
	 * a Pattern object relative to its
	 * HashMap's stored Pattern objects
	 * 
	 * @param plays a Pattern object to
	 * 				be analyze
	 * @return		a String base upon
	 * 				the prediction
	 */
	public String makePrediction(Pattern plays) {
		if (!patterns.isEmpty() && plays.getPattern().length() > 0) {
			String next = plays.getPattern().substring(1);
			//System.out.println("computer inspects current turns " + next);
			Pattern predictR = new Pattern(next + "R");
			Pattern predictP = new Pattern(next + "P");
			Pattern predictS = new Pattern(next + "S");
			int amntR = 0, amntP = 0, amntS = 0;
			if (patterns.containsKey(predictR)) {
				amntR = (Integer) patterns.get(predictR);
				//System.out.println("amount for R is " + amntR);
			}
			if (patterns.containsKey(predictP)) {
				amntP = (Integer) patterns.get(predictP);
				//System.out.println("amount for P is " + amntP);
			}
			if (patterns.containsKey(predictS)) {
				amntS = (Integer) patterns.get(predictS);
				//System.out.println("amount for S is " + amntS);
			}

			if (amntR >= amntP) {
				if (amntR > amntS) {
					if (amntR > amntP) {
						//System.out.println("amntR greatest");
						return "P";
					} else {
						//System.out.println("amntR & amntP greatest");
						int pick = new Random().nextInt(2);
						if (pick == 0) {
							return "P";
						} else {
							return "S";
						}
					}
				} else if (amntR == amntS) {
					if (amntR > amntP) {
						//System.out.println("amntR & amntS greatest");
						int pick = new Random().nextInt(2);
						if (pick == 0) {
							return "P";
						} else {
							return "R";
						}
					} else {
						//System.out.println("amntR & amntS & amntP equal");
						int pick = new Random().nextInt(3);
						if (pick == 0) {
							return "P";
						} else if (pick == 1) {
							return "R";
						} else {
							return "S";
						}
					}

				} else {
					//System.out.println("amntS greatest");
					return "R";
				}
			} else if (amntS >= amntP) {
				if (amntS > amntP) {
					//System.out.println("amntS greatest");
					return "R";
				} else {
					//System.out.println("amntS & amntP greatest");
					int pick = new Random().nextInt(2);
					if (pick == 0) {
						return "R";
					} else {
						return "S";
					}
				}
			} else {
				//System.out.println("amntP > amntS & amntR");
				return "S";
			}
		} else {
			//System.out.println("random choice");
			int rChoice = new Random().nextInt(3);
			if (rChoice == 0) {
				return "R";
			} else if (rChoice == 1) {
				return "P";
			} else {
				return "S";
			}
		}
	}
	
	/**
	 * Gets the value/counter associated with
	 * a Pattern from the HashMap patterns.
	 * 
	 * @param plays the pattern key
	 * @return      the value/counter of the
	 *              pattern
	 */
	public int getValue(Pattern plays) {
		if (patterns.containsKey(plays)) {
			return (Integer)patterns.get(plays);
		}
		else {
			return -1;
		}
	}
	
	/**
	 * Stores a Pattern object into a local
	 * HashMap if it does not already contain
	 * the Pattern, else increase the value
	 * of the Pattern object's Key inside the
	 * local HashMap indicating the Pattern
	 * object has been view once more.
	 * 
	 * @param plays a Pattern object to
	 * 				be store
	 */
	public void storePattern(Pattern plays) {
		if (patterns.containsKey(plays)) {
			//System.out.println("amount for pattern " + plays.getPattern()
					//+ " is incremented");
			patterns.replace(plays, patterns.get(plays),
					((Integer) patterns.get(plays) + 1));
			//System.out.println("\nit's new value is " + patterns.get(plays));
		} else {
			//System.out.println("new pattern stored");
			patterns.put(plays, 1);
		}
	}
}
