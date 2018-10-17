/**
 * a Node object of
 * generic datatype T
 * 
 * @author Chou Thao
 *
 * @param <T>
 */
public class Node<T extends Comparable<T>> {
	/**
	 * a generic datatype T object
	 * 
	 */
	private T data;
	/**
	 * constructs a Node initializing
	 * data with an argument d
	 * 
	 * @param d a generic datatype
	 * 			T object
	 */
	public Node(T d) {
		data = d;
	}
	/**
	 * obtains the data
	 * 
	 * @return data
	 */
	public T getData() {
		return data;
	}
	/**
	 * sets the data
	 * 
	 * @param d a generic datatype
	 * 			T object
	 */
	public void setData(T d) {
		data = d;
	}
	
}