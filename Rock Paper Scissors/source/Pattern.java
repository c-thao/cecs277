import java.io.Serializable;

public class Pattern implements Serializable {
	
	String decisions;
	
	public Pattern (String choice) {
		decisions = choice;
	}
	
	public String getPattern() {
		return decisions;
	}
	
	public int hashCode() {
		return decisions.hashCode();
	}
	
	
	
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
