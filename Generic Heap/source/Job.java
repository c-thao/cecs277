import java.util.Date;
/**
 * This is a Job object
 * which consists of a
 * task and due date
 * 
 * @author Chou Thao
 *
 */
public class Job implements Comparable<Job> {
	/**
	 * name of a task
	 * for a Job
	 */
	private String task;
	/**
	 * due date for a
	 * Job
	 * 
	 */
	private Date due;
	/**
	 * a constructor which init-
	 * ializes a task and due date
	 * 
	 * @param t
	 * @param d
	 */
	public Job(String t, Date d) {
		task = t;
		due = d;
	}
	/**
	 * obtain the task for a Job
	 * 
	 * @return the task of a Job
	 */
	public String getTask() {
		return task;
	}
	/**
	 * obtain the due date for
	 * a Job
	 * 
	 * @return the due date of
	 * 		   a Job
	 */
	public Date getDueDate() {
		return due;
	}
	/**
	 * obtain a Job converted
	 * into a String
	 * 
	 */
	public String toString() {
		return task + " " + due;
	}
	/**
	 * @override
	 * overrides the compareTo method
	 * and allows comparison with an-
	 * other Job object
	 * returns -1 if less than,
	 * 1 if greater than and 
	 * 0 if equal
	 * 
	 */
	public int compareTo(Job j) {
		if (due.compareTo(j.getDueDate()) < 0) {
			return -1;
		}
		else if(due.compareTo(j.getDueDate()) > 0) {
			return 1;
		}
		else {
			return task.compareTo(j.getTask());
		}
	}
	
}
