import java.util.ArrayList;
/**
 * This is a min generic
 * heap object which con-
 * tains Nodes of generic
 * datatype T
 * 
 * @author Chou Thao
 *
 * @param <T> generic datatype T
 */
public class GenHeap <T extends Comparable<T>> {
	/**
	 * An arraylist of Nodes of
	 * a generic datatype T
	 * 
	 */
	private ArrayList<Node<T>> heap;
	/**
	 * the constructor which initializes
	 * an arraylist of Nodes
	 * 
	 */
	public GenHeap() {
		heap = new ArrayList<Node<T>>();
	}
	/**
	 * gets the number of Nodes
	 * currently in the heap
	 * 
	 * @return number of Nodes
	 */
	public int getSize() {
		return heap.size();
	}
	/**
	 * returns true if the heap
	 * is empty else it returns
	 * false
	 * 
	 * @return true or false
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	/**
	 * obtain the numeric index
	 * of a Parent Node with respect
	 * to a current Node specified
	 * by an argument i
	 * 
	 * @param i the index of a child
	 * 		    Node
	 * @return  the index of a Parent
	 * 			Node
	 */
	public int getPLoc(int i) {
		return (i - 1) / 2;
	}
	/**
	 * obtain the numeric index
	 * of a child Node to the left
	 * with respect to a current
	 * parent Node specified
	 * by an argument i
	 * 
	 * @param i the index of a Parent
	 * 		    Node
	 * @return  the index of a child
	 * 			Node to the left
	 */
	public int getLCLoc(int i) {
		return 2 * i + 1;
	}
	/**
	 * obtain the numeric index
	 * of a child Node to the right
	 * with respect to a current
	 * parent Node specified
	 * by an argument i
	 * 
	 * @param i the index of a Parent
	 * 		    Node
	 * @return  the index of a child
	 * 			Node to the right
	 */
	public int getRCLoc(int i) {
		return 2 * i + 2;
	}
	/**
	 * obtain the current node
	 * at the specified index
	 * if there isn't any return
	 * null
	 * 
	 * @param i index of desired
	 * 			Node
	 * @return  desired Node or 
	 * 			null
	 */
	public Node<T> getAt(int i) {
		if (heap.get(i) == null) {
			System.out.println("Item does not exist. ");
			return null;
		} else {
			return heap.get(i);
		}
	}
	/**
	 * add into the current heap
	 * a new Node
	 * 
	 * @param n a new Node
	 */
	public void add(Node<T> n) {
		heap.add(null);
		int index = heap.size() - 1;
		while (index > 0 && getAt(getPLoc(index)).getData().compareTo(n.getData()) > 0) {
			heap.set(index, getAt(getPLoc(index)));
			index = getPLoc(index);
		}
		heap.set(index, n);
	}
	/**
	 * remove the smallest Node
	 * in comparison to all Nodes
	 * in the heap
	 * 
	 * @return the smallest Node in
	 * 		   the heap
	 */
	public Node<T> removeMin() {
		Node<T> min = heap.get(0);
		int index = heap.size() - 1;
		Node<T> last = heap.remove(index);
		if (index > 0) {
			heap.set(0, last);
			Node<T> root = heap.get(0);
			int end = heap.size() - 1;
			index = 0;
			boolean done = false;
			while (!done) {
				if (getLCLoc(index) <= end) {// left exists
					Node<T> child = getAt(getLCLoc(index));
					int childLoc = getLCLoc(index);
					if (getRCLoc(index) <= end) {// rt exists
						if (getAt(getRCLoc(index)).getData().compareTo(child.getData()) < 0) {
							child = getAt(getRCLoc(index));
							childLoc = getRCLoc(index);
						}
					}
					if (child.getData().compareTo(root.getData()) < 0) {
						heap.set(index, child);
						index = childLoc;
					} else {
						done = true;
					}
				} else {// no children
					done = true;
				}
			}
			heap.set(index, root);
		}
		return min;
	}
	/**
	 * prints out the Nodes inside the heap
	 * 
	 */
	public void printHeap() {
		for (int i = 0; i < heap.size(); i++) {
			System.out.println(heap.get(i).getData() + " ");
		}
		System.out.println();
	}
	
}

